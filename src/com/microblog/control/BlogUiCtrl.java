package com.microblog.control;

import javax.jms.JMSException;

import com.microblog.model.BlogMessage;

public interface BlogUiCtrl {

	/**
	 * Set username of the user
	 * 
	 * @param username
	 */
	public void setUsername(String username) throws JMSException;

	/**
	 * Subscribe to a channel
	 * 
	 * @param channelName
	 *            Channel name
	 */
	public void subscribe(String channelName) throws JMSException;

	/**
	 * Send a blog message. Channel name(s) are derived from tags
	 * 
	 * @param blogMsg
	 *            Blog message
	 */
	public void sendMessage(BlogMessage blogMsg) throws JMSException;

	/**
	 * Unsubscribe from a channel
	 * 
	 * @param channelName
	 *            Channel name
	 */
	public void unsubscribe(String channelName) throws JMSException;

	/**
	 * Cleanup connections. To be done while closing the app.
	 */
	public void cleanup() throws JMSException;

	/**
	 * Get username of the user
	 * 
	 * @return
	 */
	public String getUsername();

}
