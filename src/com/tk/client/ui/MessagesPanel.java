package com.tk.client.ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.management.monitor.CounterMonitorMBean;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.activemq.store.jpa.JPAMessageStore;

import com.tk.client.control.ClientCtrl;

/**
 *
 * @author Ram
 */
public class MessagesPanel extends javax.swing.JPanel {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private MicroBlogApp sub;
	private ClientCtrl control;

	/**
	 * Creates new form Messages
	 */
	public MessagesPanel(MicroBlogApp sub, ClientCtrl control) {
		this.sub = sub;
		this.control = control;
		initComponents();
	}

	private void initComponents() {
		// Scroll pane for messages list
		messageTextAreaScrollPane = new javax.swing.JScrollPane();
		// Text area for messages
		messagesListTextArea = new javax.swing.JTextArea();
		// scroll pane for new message text area
		newMessageScrollPane = new javax.swing.JScrollPane();
		// text are to add new message
		newMessageTextArea = new javax.swing.JTextArea();
		tagsForMessage = new javax.swing.JTextField();

		sendMessageButton = new javax.swing.JButton();
		// Label to display welcome+username
		welcomeMessageLabel = new javax.swing.JLabel();
		// Label to on top of messageList text area
		messageListLabel = new javax.swing.JLabel();
		// Text field for tags
		enterTagsTextField = new javax.swing.JTextField();
		subscribeButton = new javax.swing.JButton();
		unSubscribeButton = new javax.swing.JButton();
		// scrollpane for text area which displays the subscription list
		subscriptionListScrollPane = new javax.swing.JScrollPane();
		// List of tags user has been subscribed to, can be set to invisible if
		// not required
		subscritionListTextArea = new javax.swing.JTextArea();
		// Label above aubscriptiion list text area
		subscriptionListLabel = new javax.swing.JLabel();

		messagesListTextArea.setEditable(false);
		messagesListTextArea.setColumns(20);
		messagesListTextArea.setRows(5);
		messageTextAreaScrollPane.setViewportView(messagesListTextArea);

		newMessageTextArea.setColumns(20);
		newMessageTextArea.setRows(10);
		newMessageScrollPane.setViewportView(newMessageTextArea);

		sendMessageButton.setText("Send Message");
		sendMessageButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sendMessageButtonActionPerformed(evt);
			}
		});

		welcomeMessageLabel.setFont(new java.awt.Font("DIN Condensed", 1, 14)); // NOI18N
		welcomeMessageLabel.setText("Welcome " + sub.userName);

		messageListLabel.setText("Messages of your interest");

		enterTagsTextField.setToolTipText("Enter Tags here");

		subscribeButton.setText("Subscribe");
		subscribeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				subscribeButtonActionPerformed(evt);
			}
		});

		unSubscribeButton.setText("Un-Subscribe");
		unSubscribeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				unSubscribeButtonActionPerformed(evt);
			}
		});
		JPanel complexpanel = new JPanel();
		complexpanel.setLayout(new GridLayout(2, 1));
		JPanel tagsPanel = new JPanel();
		FlowLayout tagLayout = new FlowLayout();
		tagLayout.setHgap(10);
		tagsPanel.setLayout(tagLayout);
		tagsPanel.setAlignmentX(LEFT_ALIGNMENT);
		tagsPanel.add(new JLabel("Message tags"));
		tagsPanel.add(tagsForMessage);
		complexpanel.add(newMessageScrollPane);

		complexpanel.add(tagsPanel);

		subscritionListTextArea.setEditable(false);
		subscritionListTextArea.setColumns(20);
		subscritionListTextArea.setRows(5);
		subscriptionListScrollPane.setViewportView(subscritionListTextArea);

		subscriptionListLabel.setText("Your Subscriptions");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addComponent(messageTextAreaScrollPane)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(subscriptionListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 185,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
						.addGroup(layout.createSequentialGroup().addGap(11, 11, 11).addComponent(welcomeMessageLabel)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup().addComponent(

								complexpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 292,
								javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(sendMessageButton)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197,
										Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup().addComponent(subscribeButton)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(unSubscribeButton)).addComponent(enterTagsTextField,
														javax.swing.GroupLayout.PREFERRED_SIZE, 241,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap())
						.addGroup(layout.createSequentialGroup().addComponent(messageListLabel)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(subscriptionListLabel).addGap(19, 19, 19)))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(18, 18, 18).addComponent(welcomeMessageLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(messageListLabel).addComponent(subscriptionListLabel))
				.addGap(2, 2, 2)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addComponent(messageTextAreaScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400,
								Short.MAX_VALUE)
						.addComponent(subscriptionListScrollPane))
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
						.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(14, 14, 14)
										.addComponent(enterTagsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
												javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(unSubscribeButton).addComponent(subscribeButton)))
								.addComponent(complexpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 70,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addComponent(sendMessageButton)))));
	}

	private void sendMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {

		if (!this.newMessageTextArea.getText().isEmpty() && !this.tagsForMessage.getText().isEmpty()) {
			control.sendMessage(newMessageTextArea.getText().trim(),tagsForMessage.getText().trim());
		} else
			JOptionPane.showMessageDialog(sub, "Either of Message and Tags can not be empty in order to send a message",
					"Error", JOptionPane.ERROR_MESSAGE);
	}

	private void subscribeButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if (!this.enterTagsTextField.getText().isEmpty()) {
			control.addSubscription(this.enterTagsTextField.getText().trim());
		} else
			JOptionPane.showMessageDialog(sub, "Please mention one or more tags", "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void unSubscribeButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if (!this.enterTagsTextField.getText().isEmpty()) {
			control.removeSubscription(this.enterTagsTextField.getText().trim());
		} else
			JOptionPane.showMessageDialog(sub, "Please mention one or more tags", "Error", JOptionPane.ERROR_MESSAGE);
	}

	// Variables declaration - do not modify
	private javax.swing.JTextField enterTagsTextField;
	private javax.swing.JScrollPane messageTextAreaScrollPane;
	private javax.swing.JScrollPane newMessageScrollPane;
	private javax.swing.JScrollPane subscriptionListScrollPane;
	private javax.swing.JLabel messageListLabel;
	public javax.swing.JTextArea messagesListTextArea;
	private javax.swing.JTextArea newMessageTextArea;
	private javax.swing.JButton sendMessageButton;
	private javax.swing.JButton subscribeButton;
	private javax.swing.JLabel subscriptionListLabel;
	public javax.swing.JTextArea subscritionListTextArea;
	private javax.swing.JButton unSubscribeButton;
	private javax.swing.JLabel welcomeMessageLabel;

	private javax.swing.JTextField tagsForMessage;
	// End of variables declaration
}
