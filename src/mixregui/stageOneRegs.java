/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mixregui;

import java.awt.Dimension;
import java.awt.List;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author adityaponnada
 */
public class stageOneRegs extends javax.swing.JFrame {

    // declare required jFrame
    NewModel newModel2;

    mixregGUI mixregStageOne;

    stageTwoRegs stageTwo;

    static String[] variableNamesList;

    public static DefaultListModel<String> varList;

    static DefaultListModel<String> levelOneList;

    static DefaultListModel<String> levelTwoList;

    static boolean isSubmitClicked = false;
    
    final ImageIcon icon;
    
    

    /**
     * Creates new form stageOneRegs
     */
    public stageOneRegs() {
        initComponents();
        this.setResizable(false);

        newModel2 = new NewModel();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        //get variable names from the data set
        //variableNamesList = newModel2.getVariableNames();
        variableNamesList = NewModel.getVariableNames();
        
        icon = new ImageIcon(getClass().getResource("/resources/mixLogo.png"));
        

        if (!isSubmitClicked) {
            varList = new DefaultListModel<String>();
            levelOneList = new DefaultListModel<String>();
            levelTwoList = new DefaultListModel<String>();

        } else {
            AllVariablesList.removeAll();
            AllVariablesList.setModel(varList);
            AllVariablesList.setSelectedIndex(0);
            StageOneLevelOneList.removeAll();
            StageOneLevelOneList.setModel(levelOneList);
            StageOneLevelTwoList.removeAll();
            StageOneLevelTwoList.setModel(levelTwoList);
        }

        stageOneSubmitButton.setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        stageOneSubmitButton = new javax.swing.JButton();
        stageOneResetButton = new javax.swing.JButton();
        removeLevelButton = new javax.swing.JButton();
        levelOneAddButton = new javax.swing.JButton();
        removeLevelTwoButton = new javax.swing.JButton();
        addLevelTwoButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        AllVariablesList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        StageOneLevelOneList = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        StageOneLevelTwoList = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        stageOneCancel = new javax.swing.JButton();

        label1.setText("label1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Stage 1 Regressors");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Variables");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 37, -1, -1));

        jLabel2.setText("Level-1 (Time Varying)");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 37, -1, -1));

        jLabel3.setText("Level-2 (Time Invariant)");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(486, 257, -1, -1));

        stageOneSubmitButton.setText("Submit");
        stageOneSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stageOneSubmitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(stageOneSubmitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 470, 80, -1));

        stageOneResetButton.setText("Reset");
        stageOneResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stageOneResetButtonActionPerformed(evt);
            }
        });
        getContentPane().add(stageOneResetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 470, 89, -1));

        removeLevelButton.setText("Remove");
        removeLevelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeLevelButtonActionPerformed(evt);
            }
        });
        getContentPane().add(removeLevelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 112, 90, -1));

        levelOneAddButton.setText("Add");
        levelOneAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                levelOneAddButtonActionPerformed(evt);
            }
        });
        getContentPane().add(levelOneAddButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 77, 93, -1));

        removeLevelTwoButton.setText("Remove");
        removeLevelTwoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeLevelTwoButtonActionPerformed(evt);
            }
        });
        getContentPane().add(removeLevelTwoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 332, 90, -1));

        addLevelTwoButton.setText("Add");
        addLevelTwoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLevelTwoButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addLevelTwoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 297, 93, -1));

        jScrollPane1.setViewportView(AllVariablesList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 59, 198, 388));

        jScrollPane2.setViewportView(StageOneLevelOneList);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(353, 59, 287, 164));

        jScrollPane3.setViewportView(StageOneLevelTwoList);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(353, 284, 287, 163));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/mixLogo.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 467, -1, 34));

        stageOneCancel.setText("Cancel");
        stageOneCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stageOneCancelActionPerformed(evt);
            }
        });
        getContentPane().add(stageOneCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 470, 80, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stageOneSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stageOneSubmitButtonActionPerformed

        for (int k = 0; k < varList.size(); k++){
        System.out.println("VarList Submitted: " + String.valueOf(varList.getElementAt(k)));
        
        }
        
        
        stageTwo = new stageTwoRegs();
        isSubmitClicked = true;

        //get the instance of the mixReg model declared
        mixregStageOne = newModel2.getMixReg();

        //update regressors on stage one regressors window
        // mixregStageOne.updateRegressors(getSelectedLevelOneVars(), getSelectedLevelTwoVars());
        //mixregStageOne.updateLevelOneRegGrid(levelOneList);
        mixregStageOne.updateLevelTwoGrid_version2(levelTwoList);
        mixregStageOne.updateLevelOneGrid_version2(levelOneList);

        this.dispose();
    }//GEN-LAST:event_stageOneSubmitButtonActionPerformed

    private void levelOneAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_levelOneAddButtonActionPerformed

        if (!AllVariablesList.isSelectionEmpty()){
        levelOneList.addElement(AllVariablesList.getSelectedValue());
        StageOneLevelOneList.setModel(levelOneList);
        //remove the variable once it is added to levelOne regressors
        varList.remove(AllVariablesList.getSelectedIndex());
        
        for (int k = 0; k < varList.size(); k++){
        System.out.println("VarList: " + String.valueOf(varList.getElementAt(k)));
        
        }

        stageOneSubmitButton.setEnabled(true);
        } else {
            
            JOptionPane.showMessageDialog(null, "Please select a variable for level one.", "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        
        }
        

    }//GEN-LAST:event_levelOneAddButtonActionPerformed

    private void addLevelTwoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLevelTwoButtonActionPerformed

        if (!AllVariablesList.isSelectionEmpty()){
        stageOneSubmitButton.setEnabled(true);

        levelTwoList.addElement(AllVariablesList.getSelectedValue());
        StageOneLevelTwoList.setModel(levelTwoList);

        varList.remove(AllVariablesList.getSelectedIndex());
        
        
        
        
        } else {
        JOptionPane.showMessageDialog(null, "Please select a variable for level two.", "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }
        

    }//GEN-LAST:event_addLevelTwoButtonActionPerformed

    private void removeLevelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeLevelButtonActionPerformed

        
        if (!StageOneLevelOneList.isSelectionEmpty()){
            
            stageOneSubmitButton.setEnabled(true);
            
            //add an if condition here
            if(!varList.contains(StageOneLevelOneList.getSelectedValue())){
            
            varList.addElement(StageOneLevelOneList.getSelectedValue());
            
            }

        

        AllVariablesList.setModel(varList);

        levelOneList.remove(StageOneLevelOneList.getSelectedIndex());
        
        } else {
            JOptionPane.showMessageDialog(null, "Please select a variable from level one.", "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        
        }
        

    }//GEN-LAST:event_removeLevelButtonActionPerformed

    private void removeLevelTwoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeLevelTwoButtonActionPerformed

        
        if (!StageOneLevelTwoList.isSelectionEmpty()){
        stageOneSubmitButton.setEnabled(true);
        
         if(!varList.contains(StageOneLevelTwoList.getSelectedValue())){
            
            varList.addElement(StageOneLevelTwoList.getSelectedValue());
            
            }

        //varList.addElement(StageOneLevelTwoList.getSelectedValue());

        AllVariablesList.setModel(varList);

        levelTwoList.remove(StageOneLevelTwoList.getSelectedIndex());
        } else {
        
        JOptionPane.showMessageDialog(null, "Please select a variable from level two.", "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }
        
    }//GEN-LAST:event_removeLevelTwoButtonActionPerformed

    private void stageOneResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stageOneResetButtonActionPerformed

        //clear lists on reset
        updateAllVariables();

        levelOneList.clear();
        levelTwoList.clear();
    }//GEN-LAST:event_stageOneResetButtonActionPerformed

    private void stageOneCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stageOneCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_stageOneCancelActionPerformed

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
            java.util.logging.Logger.getLogger(stageOneRegs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(stageOneRegs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(stageOneRegs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(stageOneRegs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new stageOneRegs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> AllVariablesList;
    private javax.swing.JList<String> StageOneLevelOneList;
    private javax.swing.JList<String> StageOneLevelTwoList;
    private javax.swing.JButton addLevelTwoButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private java.awt.Label label1;
    private javax.swing.JButton levelOneAddButton;
    private javax.swing.JButton removeLevelButton;
    private javax.swing.JButton removeLevelTwoButton;
    private javax.swing.JButton stageOneCancel;
    private javax.swing.JButton stageOneResetButton;
    private javax.swing.JButton stageOneSubmitButton;
    // End of variables declaration//GEN-END:variables

    public void updateAllVariables() {
        
       // mixregGUI.g
       
       int idIndex = mixregGUI.getIDFieldPosition();
       int stageOneIndex = mixregGUI.getStageOneDVFieldPosition();
       
       varList.removeAllElements();

        for (int j = 0; j < variableNamesList.length; j++) {
            if (j == idIndex || j == stageOneIndex){
            //do nothing               
            } else {
            varList.addElement(variableNamesList[j]);
            }           
        }

        AllVariablesList.setModel(varList);
        AllVariablesList.setSelectedIndex(0);
    }
    
    public void updateStageOneAgain(){
    
   
    AllVariablesList.setModel(AllVariablesList.getModel());
    
    }

    public DefaultListModel<String> getListModel() {

        System.out.println("inside getListModel()");

        return varList;
    }

    public DefaultComboBoxModel<String> getSelectedLevelOneVars() {

        DefaultComboBoxModel<String> levelOneCombo = new DefaultComboBoxModel();

        for (int j = 0; j < levelOneList.getSize(); j++) {

            levelOneCombo.addElement(levelOneList.getElementAt(j));

        }

        return levelOneCombo;

    }

    public DefaultComboBoxModel<String> getSelectedLevelTwoVars() {

        DefaultComboBoxModel<String> levelTwoCombo = new DefaultComboBoxModel();

        for (int j = 0; j < levelTwoList.getSize(); j++) {

            levelTwoCombo.addElement(levelTwoList.getElementAt(j));

        }
        return levelTwoCombo;

    }

    
}
