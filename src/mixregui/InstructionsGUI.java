/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mixregui;

import javax.swing.JFrame;

/**
 *
 * @author adityaponnada
 */
public class InstructionsGUI extends javax.swing.JFrame {
    
    NewModel nm;

    /**
     * Creates new form InstructionsGUI
     */
    public InstructionsGUI() {
        initComponents();
        //nm = new NewModel();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        proceedButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Please follow these instructions ...");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 36, -1, -1));

        jLabel2.setText("- You should always use a .csv file");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 95, -1, -1));

        jLabel3.setText("- You should ensure that missing values are not blanks");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 129, -1, -1));

        proceedButton.setText("Got it");
        proceedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proceedButtonActionPerformed(evt);
            }
        });
        getContentPane().add(proceedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 229, -1, -1));

        jLabel4.setText("- Please ensure that the data is sorted by IDs");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 195, -1, -1));

        jLabel5.setText("- Missing value codes should be numeric only");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 161, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void proceedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proceedButtonActionPerformed
        // TODO add your handling code here:
       // nm.setVisible(true);
       this.dispose();
        
        
    }//GEN-LAST:event_proceedButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InstructionsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InstructionsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InstructionsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InstructionsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InstructionsGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton proceedButton;
    // End of variables declaration//GEN-END:variables
}
