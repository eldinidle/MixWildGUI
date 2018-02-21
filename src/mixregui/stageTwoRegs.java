/*
 * MixWild, a program to model subject-level slope and variance on continuous or ordinal outcomes
    Copyright (C) 2018 (not sure what name goes here?)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

	Principal Investigators:
	Genevieve Dunton, PhD MPH
	University of Southern California
	dunton@usc.edu
	
	Donald Hedeker, PhD
	University of Chicago
	DHedeker@health.bsd.uchicago.edu
 */
package mixregui;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import static mixregui.stageOneRegs.varList;
import static mixregui.stageOneRegs.variableNamesList;

/**
 *
 * @author adityaponnada
 */
public class stageTwoRegs extends javax.swing.JFrame {

    //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    stageOneRegs stageOne;
    NewModel newModel2;
    mixregGUI mixregGUIStageTwo;

    static DefaultListModel<String> stageTwoListModel;
    static DefaultListModel<String> stageTwoLevelTwo;

    static boolean isStageTwoSubmitClicked = false;

    final ImageIcon icon;

    /**
     * Creates new form stageTwoRegs
     */
    public stageTwoRegs() {
        initComponents();
        this.setResizable(false);
        //create list models

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        newModel2 = new NewModel();
        icon = new ImageIcon(getClass().getResource("/resources/mixLogo.png"));

        if (!isStageTwoSubmitClicked) {
            stageTwoListModel = new DefaultListModel<String>();
            stageTwoLevelTwo = new DefaultListModel<String>();

        } else {
            StageTwoAllVariables.setModel(stageTwoListModel);
            StageTwoLevelTwoVariables.setModel(stageTwoLevelTwo);

        }

        stageTwoSubmitButton.setEnabled(false);

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
        stageTwoAddButton = new javax.swing.JButton();
        stageTwoRemoveButton = new javax.swing.JButton();
        stageTwoResetButton = new javax.swing.JButton();
        stageTwoSubmitButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        StageTwoAllVariables = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        StageTwoLevelTwoVariables = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        stageTwoCancel = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Stage 2 regressors");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Variables");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel2.setText("Level 2 (Time Invariant)");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, -1, -1));

        stageTwoAddButton.setText("Add");
        stageTwoAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stageTwoAddButtonActionPerformed(evt);
            }
        });
        getContentPane().add(stageTwoAddButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 85, 89, -1));

        stageTwoRemoveButton.setText("Remove");
        stageTwoRemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stageTwoRemoveButtonActionPerformed(evt);
            }
        });
        getContentPane().add(stageTwoRemoveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 120, 89, -1));

        stageTwoResetButton.setText("Reset");
        stageTwoResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stageTwoResetButtonActionPerformed(evt);
            }
        });
        getContentPane().add(stageTwoResetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 460, 96, -1));

        stageTwoSubmitButton.setText("Submit");
        stageTwoSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stageTwoSubmitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(stageTwoSubmitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 460, 96, -1));

        jScrollPane1.setViewportView(StageTwoAllVariables);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 55, 179, 372));

        jScrollPane2.setViewportView(StageTwoLevelTwoVariables);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 55, 162, 177));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/mixLogo.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 460, -1, 36));

        stageTwoCancel.setText("Cancel");
        stageTwoCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stageTwoCancelActionPerformed(evt);
            }
        });
        getContentPane().add(stageTwoCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 460, 90, -1));

        jButton1.setText("?");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 155, 41, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stageTwoSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stageTwoSubmitButtonActionPerformed
        //dispose closes the window
        //mixregGUI.
        isStageTwoSubmitClicked = true;

        mixregGUIStageTwo = newModel2.getMixReg();
        // mixregGUIStageTwo.updateStageTwoGrid_version2(stageTwoLevelTwo);

        //mixregGUIStageTwo.setSele
        mixregGUIStageTwo.updateStageTwoGrid_tab2(stageTwoLevelTwo);

        this.dispose();
    }//GEN-LAST:event_stageTwoSubmitButtonActionPerformed

    private void stageTwoAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stageTwoAddButtonActionPerformed

        //Add items to the model and then copy it to the UI list
        if (!StageTwoAllVariables.isSelectionEmpty()) {

            stageTwoLevelTwo.addElement(StageTwoAllVariables.getSelectedValue());
            StageTwoLevelTwoVariables.setModel(stageTwoLevelTwo);
            stageTwoListModel.remove(StageTwoAllVariables.getSelectedIndex());

            for (int k = 0; k < stageTwoListModel.size(); k++) {
                System.out.println("VarList: " + String.valueOf(stageTwoListModel.getElementAt(k)));

            }
            stageTwoSubmitButton.setEnabled(true);

        } else {
            JOptionPane.showMessageDialog(null, "Please select a variable for stage two.", "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }


    }//GEN-LAST:event_stageTwoAddButtonActionPerformed

    private void stageTwoRemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stageTwoRemoveButtonActionPerformed

        //Remove an item from the model
        if (!StageTwoLevelTwoVariables.isSelectionEmpty()) {
            stageTwoSubmitButton.setEnabled(true);

            if (!stageTwoListModel.contains(StageTwoLevelTwoVariables.getSelectedValue())) {

                stageTwoListModel.addElement(StageTwoLevelTwoVariables.getSelectedValue());

            }

            stageTwoLevelTwo.remove(StageTwoLevelTwoVariables.getSelectedIndex());

        } else {
            JOptionPane.showMessageDialog(null, "Please select a variable from stage two.", "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);

        }

    }//GEN-LAST:event_stageTwoRemoveButtonActionPerformed

    private void stageTwoResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stageTwoResetButtonActionPerformed
        stageTwoLevelTwo.clear();
    }//GEN-LAST:event_stageTwoResetButtonActionPerformed

    private void stageTwoCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stageTwoCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_stageTwoCancelActionPerformed

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
            java.util.logging.Logger.getLogger(stageTwoRegs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(stageTwoRegs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(stageTwoRegs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(stageTwoRegs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new stageTwoRegs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> StageTwoAllVariables;
    private javax.swing.JList<String> StageTwoLevelTwoVariables;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton stageTwoAddButton;
    private javax.swing.JButton stageTwoCancel;
    private javax.swing.JButton stageTwoRemoveButton;
    private javax.swing.JButton stageTwoResetButton;
    private javax.swing.JButton stageTwoSubmitButton;
    // End of variables declaration//GEN-END:variables

    public void updateStageTwoVariables(DefaultListModel<String> defaultListModel) {

        //updates variables in stage two list (to add regressors)
        //Add model to stage two variables, to display items  
        StageTwoAllVariables.setModel(defaultListModel);

        StageTwoAllVariables.setSelectedIndex(2);
    }

    public void updateStageTwoWithoutStageOne() {
        int idIndex = mixregGUI.getIDFieldPosition();
        int stageOneIndex = mixregGUI.getStageOneDVFieldPosition();
        int stageTwoIndex = mixregGUI.getStageTwoDVFieldPosition();

        stageTwoListModel.removeAllElements();

        for (int j = 0; j < variableNamesList.length; j++) {
            if (j == idIndex || j == stageOneIndex || j == stageTwoIndex) {
                //do nothing               
            } else {
                stageTwoListModel.addElement(variableNamesList[j]);
            }
        }

        StageTwoAllVariables.setModel(stageTwoListModel);
        StageTwoAllVariables.setSelectedIndex(1);

    }

    public void updateStageTwoAgain() {

        //StageTwoAllVariables.setModel(StageTwoAllVariables.getModel());
        StageTwoAllVariables.setModel(stageTwoListModel);

    }

}
