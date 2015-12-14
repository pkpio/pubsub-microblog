package com.microblog.control;

import java.util.HashMap;
import java.util.List;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.google.gson.Gson;
import com.microblog.model.BlogMessage;
import com.microblog.view.BlogApp;

public class BlogCtrl implements BlogUiCtrl, MessageListener {
	BlogApp mBlogApp;
	Connection mConnection;
	Session mSession;

	String mUsername;
	HashMap<String, MessageProducer> mProducers = new HashMap<String, MessageProducer>();
	HashMap<String, MessageConsumer> mConsumers = new HashMap<String, MessageConsumer>();

	public BlogCtrl(BlogApp blogApp) throws JMSException {
		mBlogApp = blogApp;

		// Create a ConnectionFactory
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://localhost:61616");

		// Create a Connection
		mConnection = connectionFactory.createConnection();
		mConnection.start();

		// Create a Session
		mSession = mConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		System.out.println("All done!");
	}

	@Override
	public void setUsername(String username) throws JMSException {
		mUsername = username;
		sendMessage(new BlogMessage(username, "User logged in"));
	}

	@Override
	public void subscribe(String channelName) throws JMSException {
		if (!mConsumers.containsKey(channelName)) {
			System.out.println("Subscribing for channel : " + channelName);

			// Create the destination (Topic or Queue)
			Destination destination = mSession.createQueue(channelName);

			// Create a MessageConsumer from the Session to the Topic
			MessageConsumer consumer = mSession.createConsumer(destination);

			// Add message listener for this consumer
			consumer.setMessageListener(this);

			// Add to list of known consumers
			mConsumers.put(channelName, consumer);
		} else {
			System.out.println("Duplicate subscription for " + channelName);
		}
	}

	@Override
	public void sendMessage(BlogMessage blogMsg) throws JMSException {
		// Create a object message for BlogMsg
		TextMessage txtMsg = mSession.createTextMessage(new Gson().toJson(blogMsg));

		// Send message on user's channel
		MessageProducer producer = getProducer(blogMsg.getUsername());
		producer.send(txtMsg);

		// Send it for all channel names in the tag list
		List<String> tags = blogMsg.getTags();
		if (tags == null)
			return;
		for (int i = 0; i < tags.size(); i++) {
			producer = getProducer(tags.get(i));
			producer.send(txtMsg);
		}
	}

	/**
	 * Gets the producer for a channel. It will be created if never used before
	 * 
	 * @param channelName
	 * @return Producer for a channel
	 * @throws JMSException
	 */
	private MessageProducer getProducer(String channelName) throws JMSException {
		MessageProducer producer = mProducers.get(channelName);

		if (producer == null) {
			// Create the destination for Topic
			Destination destination = mSession.createQueue(channelName);

			// Create a MessageProducer from the Session to the Topic
			producer = mSession.createProducer(destination);

			// Add to list of known producers
			mProducers.put(channelName, producer);
		}

		return producer;
	}

	@Override
	public void unsubscribe(String channelName) throws JMSException {
		MessageConsumer consumer = mConsumers.remove(channelName);
		if (consumer != null)
			consumer.close();
	}

	@Override
	public void cleanup() throws JMSException {
		// -TODO- remove consumers
		// -TODO- remove producers
		mSession.close();
		mConnection.close();
	}

	@Override
	public void onMessage(Message msg) {
		if (msg instanceof TextMessage) {
			TextMessage txtMsg = (TextMessage) msg;
			try {
				BlogMessage blogMsg = new Gson().fromJson(txtMsg.getText(), BlogMessage.class);
				System.out.println("New message from : " + blogMsg.getUsername() + " on channel : "
						+ msg.getJMSDestination().toString());
				mBlogApp.addNewMessageToUI(blogMsg);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Non-text message received");
		}
	}

	@Override
	public String getUsername() {
		return mUsername;
	}

}
