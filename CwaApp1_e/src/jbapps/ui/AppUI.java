package jbapps.ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.UIManager.*;
import jbapps.App;

/**
 *
 * @author you
 */
public class AppUI extends javax.swing.JFrame {

    /** Creates new form cwaApp */
    public AppUI() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

    	// get credentials from properties file
		final Properties user = new Properties();
		try {
			user.load(new FileInputStream("user.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
        userTextField = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();
        passField = new javax.swing.JPasswordField();
        openChatButton = new javax.swing.JButton();
        urlTextField = new javax.swing.JTextField();
        loggedInUserLabel = new javax.swing.JLabel();
        subscriberScrollPane2 = new javax.swing.JScrollPane();
        subscriberTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("cwac");
        setResizable(false);

        userTextField.setFont(new java.awt.Font("Verdana", 0, 12));
        userTextField.setText(user.getProperty("uid", "mydomain\\myuid"));
        userTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTextFieldActionPerformed(evt);
            }
        });

        loginButton.setText("Login");
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginButtonMouseClicked(evt);
            }
        });
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        passField.setFont(new java.awt.Font("Verdana", 0, 12));
        passField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passFieldActionPerformed(evt);
            }
        });

        openChatButton.setText("Chat!");
        openChatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openChatButtonActionPerformed(evt);
            }
        });

        urlTextField.setFont(new java.awt.Font("Verdana", 0, 12));
        urlTextField.setText(user.getProperty("url", "https://mycwa/login/url"));
        urlTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urlTextFieldActionPerformed(evt);
            }
        });

        loggedInUserLabel.setFont(new java.awt.Font("Verdana", 0, 11));
        loggedInUserLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loggedInUserLabel.setText("(-_-)");

        subscriberTable.setAutoCreateRowSorter(true);
        subscriberTable.setFont(new java.awt.Font("Verdana", 0, 11));
        subscriberTable.setInheritsPopupMenu(true);
        subscriberTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        subscriberTable.setShowVerticalLines(false);
        subscriberTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subscriberTableMouseClicked(evt);
            }
        });
        subscriberScrollPane2.setViewportView(subscriberTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loggedInUserLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                    .addComponent(subscriberScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                    .addComponent(urlTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passField, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(userTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(openChatButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(urlTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loggedInUserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subscriberScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(openChatButton)
                .addContainerGap())
        );

        loggedInUserLabel.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
    }//GEN-LAST:event_loginButtonActionPerformed

    private void openChatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openChatButtonActionPerformed
        System.err.println("openChatButtonActionPerformed");
        app.inviteIMDialog();
    }//GEN-LAST:event_openChatButtonActionPerformed

    private void urlTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_urlTextFieldActionPerformed
    }//GEN-LAST:event_urlTextFieldActionPerformed

    private void passFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passFieldActionPerformed
    }//GEN-LAST:event_passFieldActionPerformed

    private void userTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTextFieldActionPerformed
    }//GEN-LAST:event_userTextFieldActionPerformed

    private void loginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseClicked
        System.err.println("loginButtonMouseClicked");
        if (!app_started) {
            app_started = true;
            app.login();
            app.startSubcriberListDaemon();
        } else {
            app_started = false;
            app.logout();
        }
    }//GEN-LAST:event_loginButtonMouseClicked

    private void subscriberTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subscriberTableMouseClicked
        System.err.println("subscriberTableMouseClicked");
        app.setSubscriber(subscriberTable.getSelectedRow());
    }//GEN-LAST:event_subscriberTableMouseClicked

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel loggedInUserLabel;
    public javax.swing.JButton loginButton;
    private javax.swing.JButton openChatButton;
    public javax.swing.JPasswordField passField;
    private javax.swing.JScrollPane subscriberScrollPane2;
    public javax.swing.JTable subscriberTable;
    public javax.swing.JTextField urlTextField;
    public javax.swing.JTextField userTextField;
    // End of variables declaration//GEN-END:variables
    
    App app;
    boolean app_started = false;

    public void setApp(App _app){
        app = _app;
    }
}