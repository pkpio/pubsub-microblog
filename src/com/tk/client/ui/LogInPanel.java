package com.tk.client.ui;

import javax.swing.JOptionPane;

/**
 *
 * @author Ram
 */
public class LogInPanel extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SubscriptionApplication sub;

	/**
	 * Creates new form LogInPanel
	 */
	public LogInPanel(SubscriptionApplication sub) {
		this.sub = sub;
		initComponents();
	}

	private void initComponents() {

		userName = new javax.swing.JTextField();
		logInButton = new javax.swing.JButton();

		logInButton.setText("LogIn");
		logInButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logInButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(279, 279, 279)
						.addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 189,
								javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(logInButton)
				.addContainerGap(241, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(205, 205, 205)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(logInButton)).addContainerGap(366, Short.MAX_VALUE)));
	}

	/**
	 * proceed only of the username is not empty , Note there is no alert for
	 * empty username
	 * 
	 * @param evt
	 */
	private void logInButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if (!this.userName.getText().isEmpty())
			sub.displayMessage(this.userName.getText());
		else
			JOptionPane.showMessageDialog(sub, "User name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
	}

	// Variables declaration - do not modify
	private javax.swing.JButton logInButton;
	private javax.swing.JTextField userName;
	// End of variables declaration
}
