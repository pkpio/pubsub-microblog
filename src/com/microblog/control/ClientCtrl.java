package com.microblog.control;

import java.util.List;

import com.microblog.view.BlogApp;

public class ClientCtrl {
	private BlogApp sub;

	/**
	 * Constructor with subscriptionPanel object
	 * 
	 * @param sub
	 */
	public ClientCtrl(BlogApp sub) {
		this.sub = sub;
	}

	/**
	 * method to be invoked by process listening to queue TODO replace String
	 * from Message Object
	 * 
	 * @param message
	 */

	public void updateMessagesPanel(String message) {
		String msg = "MESSAGE";
		String user = "USER";
		String tags = "TAGS";
		/**
		 * update the messages list , send message, user and tags list as parm
		 */
		sub.addNewMessageToUI(msg, user, tags);
	}

	/**
	 * Method to be invoked to refresh subscription list textPanel, must be
	 * invoked by process after add or remove subscription is called
	 * 
	 * @param subscriptionList
	 */
	public void updateSubscriptionPanel(List<String> subscriptionList) {
		sub.updateSubscriptionList(subscriptionList);
	}

	/**
	 * invoked by Un SUbscribe button action
	 * 
	 * @param tags
	 */
	public void removeSubscription(String tags) {
		// TODO code to remove subscription
	}

	/**
	 * invoked by SUbscribe button action
	 * 
	 * @param tags
	 */
	public void addSubscription(String tags) {
		// TODO code to add new subscription
	}

	/**
	 * invoked by send message button action
	 * 
	 * @param message
	 */
	public void sendMessage(String message,String tags) {
		// TODO code to send message to queue

	}
}
