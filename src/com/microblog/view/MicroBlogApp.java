package com.microblog.view;

import java.awt.GridLayout;

import com.microblog.control.ClientCtrl;

/**
 *
 * @author Ram
 */
public class MicroBlogApp extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LogInPanel loginPanel;
	private MessagesPanel messagePanel;
	public String userName;
	ClientCtrl control;

	public MicroBlogApp() {
		initComponents();
	}

	private void initComponents() {

		loginPanel = new LogInPanel(this);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		GridLayout layout = new GridLayout(2, 1);
		getContentPane().setLayout(layout);
		this.getContentPane().add(loginPanel);

		pack();
	}

	void displayMessage(String userName) {
		this.userName = userName;
		control = new ClientCtrl(this);
		messagePanel = new MessagesPanel(this, control);
		this.remove(loginPanel);
		this.getContentPane().add(messagePanel);
		this.revalidate();
		this.repaint();
	}

	/**
	 * Entry point of the app
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		new MicroBlogApp().begin();
	}

	void begin() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MicroBlogApp().setVisible(true);
			}
		});
	}

	/**
	 * Call back from control to update message
	 * 
	 * @param message
	 * @param user
	 * @param tags
	 */
	public void updateMessages(String message, String user, String tags) {
		String msg = "From : " + user + "\n" + "Message: " + message + "\n" + "Tags : " + tags + "\n"
				+ "========================================\n";
		this.messagePanel.messagesListTextArea.append(msg);

	}

	/**
	 * call back from control to update subscription list
	 * 
	 * @param subscriptionList
	 */
	public void updateSubscription(String subscriptionList) {
		this.messagePanel.subscritionListTextArea.setText(subscriptionList);

	}

}
