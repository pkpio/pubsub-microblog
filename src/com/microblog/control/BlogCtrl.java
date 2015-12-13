package com.microblog.control;

import java.util.HashMap;
import java.util.List;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.microblog.model.BlogMessage;

public class BlogCtrl implements BlogUiCtrl {
	Connection mConnection;
	Session mSession;

	String mUsername;
	HashMap<String, MessageProducer> mProducers = new HashMap<String, MessageProducer>();
	HashMap<String, MessageConsumer> mConsumers = new HashMap<String, MessageConsumer>();

	public BlogCtrl() throws JMSException {
		// Create a ConnectionFactory
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

		// Create a Connection
		mConnection = connectionFactory.createConnection();
		mConnection.start();

		// Create a Session
		mSession = mConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	@Override
	public void setUsername(String username) throws JMSException {
		mUsername = username;
		sendMessage(new BlogMessage(username, "User logged in"));
	}

	@Override
	public void subscribe(String channelName, MessageListener listener) throws JMSException {
		if (!mConsumers.containsKey(channelName)) {
			// Create the destination (Topic or Queue)
			Destination destination = mSession.createQueue(channelName);

			// Create a MessageConsumer from the Session to the Topic
			MessageConsumer consumer = mSession.createConsumer(destination);

			// Add message listener for this consumer
			consumer.setMessageListener(listener);

			// Add to list of known consumers
			mConsumers.put(channelName, consumer);
		} else {
			System.out.println("Duplicate subscription for " + channelName);
		}
	}

	@Override
	public void sendMessage(BlogMessage blogMsg) throws JMSException {
		// Create a object message for BlogMsg
		ObjectMessage objMsg = mSession.createObjectMessage(blogMsg);

		// Send message on user's channel
		mProducers.get(blogMsg.getUsername()).send(objMsg);

		// Send it for all channel names in the tag list
		List<String> tags = blogMsg.getTags();
		if (tags == null)
			return;
		for (int i = 0; i < tags.size(); i++) {
			MessageProducer producer = getProducer(tags.get(i));
			producer.send(objMsg);
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

}
