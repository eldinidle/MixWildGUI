/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
// ...
 */
package mixregui;

import com.sun.glass.events.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import def_lib.DefinitionHelper;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author adityaponnada
 */
public class NewModel extends javax.swing.JFrame {
    

    //Object declarations
    
    JFileChooser fileChooser; 
    File file;
    static String[] variableArray;
    static int RLE;
    static boolean NoneVar;
    static mixregGUI mxr;
    InstructionsGUI instructions;
    static boolean isRandomScale = false;
    
    /**
     *
     */
    public static DefinitionHelper defFile;
     
    /**
     * Creates new form NewModel
     */
    public NewModel() {
        initComponents();
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
       fileChooser = new JFileChooser();
       instructions = new InstructionsGUI();
      // defFile = new def_lib.DefinitionHelper();
      
      //subtitle ---> ouput prefix + date and time
       
       titleField.setEnabled(false);
       subtitleField.setEnabled(false);
       continuousRadio.setEnabled(false);
       dichotomousRadio.setEnabled(false);
       randomLocationEffects.setEnabled(false);
       newModelSubmit.setEnabled(false);
       newModelMissingValueCode.setText("-9999");
       newModelMissingValueCode.setEnabled(false);
       newModelMissingValues.setEnabled(false);
       newModelMissingValues.setSelected(true);
       noneRadio.setEnabled(false);
       randomScaleCheckBox.setEnabled(false);
       
       dataFileLabel.setToolTipText("Insert a data file in .csv format");
       fileBrowseButton.setToolTipText("Insert a data file in .csv format");
       
       filePath.setToolTipText("Insert a data file in .csv format");
       titleField.setToolTipText("Insert title for the model");
       subtitleField.setToolTipText("Insert subtitle for the model");
       randomLocationEffects.setToolTipText("Select the number of random location effects. Minimum value is 1");
       
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        dataFileLabel = new javax.swing.JLabel();
        filePath = new javax.swing.JTextField();
        fileBrowseButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        titleField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        subtitleField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        randomLocationEffects = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        continuousRadio = new javax.swing.JRadioButton();
        dichotomousRadio = new javax.swing.JRadioButton();
        newModelSubmit = new javax.swing.JButton();
        newModelCancel = new javax.swing.JButton();
        newModel_resetButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        newModelMissingValues = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        newModelMissingValueCode = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        noneRadio = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        randomScaleCheckBox = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Model for Stage 1 Analysis");

        dataFileLabel.setText("Data File: ");

        filePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filePathActionPerformed(evt);
            }
        });

        fileBrowseButton.setText("Browse");
        fileBrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileBrowseButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Title:");

        titleField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Output/Definition File:");

        subtitleField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subtitleFieldActionPerformed(evt);
            }
        });

        jLabel4.setText("Random Location Effects:");

        randomLocationEffects.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jLabel5.setText("Stage 2 Outcome Type:");

        buttonGroup1.add(continuousRadio);
        continuousRadio.setText("Continuous");
        continuousRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continuousRadioActionPerformed(evt);
            }
        });

        buttonGroup1.add(dichotomousRadio);
        dichotomousRadio.setText("Dichotomous/Ordinal");
        dichotomousRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dichotomousRadioActionPerformed(evt);
            }
        });

        newModelSubmit.setText("Submit");
        newModelSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newModelSubmitActionPerformed(evt);
            }
        });

        newModelCancel.setText("Cancel");
        newModelCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newModelCancelActionPerformed(evt);
            }
        });

        newModel_resetButton.setText("Reset");
        newModel_resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newModel_resetButtonActionPerformed(evt);
            }
        });

        newModelMissingValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newModelMissingValuesActionPerformed(evt);
            }
        });

        jLabel6.setText("Missing value code:");

        newModelMissingValueCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newModelMissingValueCodeActionPerformed(evt);
            }
        });
        newModelMissingValueCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                newModelMissingValueCodeKeyTyped(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/mixLogo.png"))); // NOI18N

        jLabel8.setText("Add missing value code?");

        buttonGroup1.add(noneRadio);
        noneRadio.setText("None");
        noneRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noneRadioActionPerformed(evt);
            }
        });

        jButton1.setText("Instructions!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setText("Please go through the following");

        jLabel10.setText("Random Scale?");

        randomScaleCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomScaleCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(dataFileLabel)
                        .addGap(17, 17, 17)
                        .addComponent(filePath, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(fileBrowseButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel2)
                        .addGap(28, 28, 28)
                        .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel3)
                        .addGap(16, 16, 16)
                        .addComponent(subtitleField, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4)
                        .addGap(20, 20, 20)
                        .addComponent(randomLocationEffects, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(randomScaleCheckBox))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel5)
                        .addGap(15, 15, 15)
                        .addComponent(continuousRadio)
                        .addGap(5, 5, 5)
                        .addComponent(dichotomousRadio)
                        .addGap(10, 10, 10)
                        .addComponent(noneRadio))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel6)
                        .addGap(16, 16, 16)
                        .addComponent(newModelMissingValueCode, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addComponent(jLabel1)))
                        .addGap(15, 15, 15)
                        .addComponent(newModelMissingValues))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel7)
                        .addGap(82, 82, 82)
                        .addComponent(newModelCancel)
                        .addGap(28, 28, 28)
                        .addComponent(newModel_resetButton)
                        .addGap(36, 36, 36)
                        .addComponent(newModelSubmit))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel9))
                    .addComponent(jButton1))
                .addGap(12, 12, 12)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(dataFileLabel))
                    .addComponent(filePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileBrowseButton))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2))
                    .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3))
                    .addComponent(subtitleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4))
                    .addComponent(randomLocationEffects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(randomScaleCheckBox))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(continuousRadio)
                    .addComponent(dichotomousRadio)
                    .addComponent(noneRadio))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(newModelMissingValueCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1))
                    .addComponent(newModelMissingValues))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newModelCancel)
                            .addComponent(newModel_resetButton)
                            .addComponent(newModelSubmit)))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filePathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filePathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filePathActionPerformed

    private void fileBrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileBrowseButtonActionPerformed
        //JFileChooser fileChooser = new JFileChooser();
        //fileChooser.showOpenDialog(null);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Data files","csv");
        
        fileChooser.setFileFilter(filter);
        
        fileOpen();
        
        //Select file from the file object
        file = fileChooser.getSelectedFile();
        
        //get file path to display on the text box
        String fileName = file.getAbsolutePath();
        
        filePath.setText(fileName);
        
        //enable other buttons here:
        titleField.setEnabled(true);
        subtitleField.setEnabled(true);
        randomLocationEffects.setEnabled(true);
        continuousRadio.setEnabled(true);
        dichotomousRadio.setEnabled(true);
        randomLocationEffects.setEnabled(true);
        newModelSubmit.setEnabled(true);
        newModelMissingValues.setEnabled(true);
        newModelMissingValueCode.setEnabled(true);
        noneRadio.setEnabled(true);
        randomScaleCheckBox.setEnabled(true);
        
        newModelMissingValueCode.selectAll();
        
    }//GEN-LAST:event_fileBrowseButtonActionPerformed

    private void subtitleFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subtitleFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subtitleFieldActionPerformed

    private void continuousRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continuousRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_continuousRadioActionPerformed

    private void newModelSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newModelSubmitActionPerformed
        
        RLE = (Integer) randomLocationEffects.getValue();
        
        defFile = new DefinitionHelper(RLE, !isOutcomeContinous());
        System.out.println("RLE: " + String.valueOf(RLE));
        
        defFile.modelSelector(RLE, isOutcomeContinous());
        
        System.out.println("MODEL SELECTOR: " + String.valueOf(defFile.getSelectedModel()));
        
        if (filePath.getText().toString().equals("")){
        
        JOptionPane.showMessageDialog(null, "Please upload a datafile to start your analysis", "Caution!", JOptionPane.INFORMATION_MESSAGE);
        } else {
        
        try {
            
            // Read file contents
            Scanner inputStream = new Scanner(file);
            
           
            // Read variable names from row 1
            String variableNames = inputStream.next();
            
            variableArray = variableNames.split(",");
            
           // save all variables in an array
            
            String[] varTemp = getVariableNames();
            
           defFile.setDataFilename(file.getAbsolutePath());
           
           defFile.setDataVariableCount(String.valueOf(variableArray.length));
           System.out.println("From defHelper | Variable count: " + defFile.getDataVariableCount());
        
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewModel.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (Exception ex) {
                Logger.getLogger(NewModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        
         if (newModelMissingValueCode.isEnabled()) {

            try {
                defFile.setAdvancedMissingValue(newModelMissingValueCode.getText().toString());
                System.out.println("From defHelper | Missing Value: " + defFile.getAdvancedMissingValue());
//                tryCount = 1;
            } catch (Exception ex) {
                //catchCount = 1;
                Logger.getLogger(advancedOptions.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE);
            }
            
            NewModel.defFile.setOutputPrefix("Output_" + getOutPutFileName());
            System.out.println("From defHelper | Output file name: " + NewModel.defFile.getOutputPrefix());

        } else {
            // do nothing and go next

        }
        
       
        // Read random location effects from new Model
        //RLE = (Integer) randomLocationEffects.getValue();
        NoneVar = isOutcomeNone();
       
        System.out.println("NoneVar: " + String.valueOf(NoneVar));
        
        System.out.println(String.valueOf(isOutcomeContinous()));
        System.out.println("IsOutcomeNone: " + String.valueOf(isOutcomeNone()));
        
       // set Values in def helper
       defFile.setModelTitle(getTitle());
       System.out.println("From defHelper | Title: " + defFile.getModelTitle());
       
       defFile.setModelSubtitle(getSubTitle());
       System.out.println("From defHelper | Subtitle: " + defFile.getModelSubtitle());
       
       if (randomScaleCheckBox.isSelected()){
           
            isRandomScale = true;
        
        } else {
            isRandomScale = false;
       
       }
        
       mxr = new mixregGUI();
       mxr.isSubmitClicked();
       mxr.setVisible(true);
       //Update ID, stage one and stage two variable comboboxes
       mxr.updateComboBoxes();
      
       
        this.dispose();
        }
    }//GEN-LAST:event_newModelSubmitActionPerformed

    private void newModelCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newModelCancelActionPerformed
        // TODO add your handling code here:
        //Close(NewModel);
        this.dispose();
    }//GEN-LAST:event_newModelCancelActionPerformed

    private void titleFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_titleFieldActionPerformed

    private void newModel_resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newModel_resetButtonActionPerformed
        // TODO add your handling code here:
        
        filePath.setText("");
        titleField.setText("");
        titleField.setEnabled(false);
        subtitleField.setEditable(false);
        subtitleField.setText("");
        randomLocationEffects.setValue(1);
        randomLocationEffects.setEnabled(false);
        continuousRadio.setEnabled(false);
        dichotomousRadio.setEnabled(false);
        randomLocationEffects.setEnabled(false);
        newModelSubmit.setEnabled(false);
        
        
    }//GEN-LAST:event_newModel_resetButtonActionPerformed

    private void newModelMissingValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newModelMissingValuesActionPerformed
        // TODO add your handling code here:
        
        if (newModelMissingValues.isSelected() == true) {
            newModelMissingValueCode.setEnabled(true);
            newModelMissingValueCode.setText("-9999");
        } else {
            newModelMissingValueCode.setEnabled(false);
        }
        
    }//GEN-LAST:event_newModelMissingValuesActionPerformed

    private void newModelMissingValueCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newModelMissingValueCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newModelMissingValueCodeActionPerformed

    private void noneRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noneRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noneRadioActionPerformed

    private void newModelMissingValueCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newModelMissingValueCodeKeyTyped
        // TODO add your handling code here:
        
        char vchar = evt.getKeyChar();
        if (!((Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACKSPACE) || (vchar == KeyEvent.VK_DELETE) || (vchar == KeyEvent.VK_MINUS))){
            evt.consume();
        }
        
    }//GEN-LAST:event_newModelMissingValueCodeKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        instructions.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void randomScaleCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomScaleCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_randomScaleCheckBoxActionPerformed

    private void dichotomousRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dichotomousRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dichotomousRadioActionPerformed

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
            java.util.logging.Logger.getLogger(NewModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewModel().setVisible(true);
            }
        });
    }

    
    //Open data file
    private void fileOpen() {
    int returnVal = fileChooser.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        // What to do with the file, e.g. display it in a TextArea
        //textarea.read( new FileReader( file.getAbsolutePath() ), null );
        
        System.out.println(file.getAbsolutePath());
    } else {
        System.out.println("File access cancelled by user.");
    }
} 
  
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton continuousRadio;
    private javax.swing.JLabel dataFileLabel;
    private javax.swing.JRadioButton dichotomousRadio;
    private javax.swing.JButton fileBrowseButton;
    private javax.swing.JTextField filePath;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton newModelCancel;
    private javax.swing.JTextField newModelMissingValueCode;
    private javax.swing.JCheckBox newModelMissingValues;
    private javax.swing.JButton newModelSubmit;
    private javax.swing.JButton newModel_resetButton;
    private javax.swing.JRadioButton noneRadio;
    private javax.swing.JSpinner randomLocationEffects;
    private javax.swing.JCheckBox randomScaleCheckBox;
    private javax.swing.JTextField subtitleField;
    private javax.swing.JTextField titleField;
    // End of variables declaration//GEN-END:variables



//get the number of random location effects    
public int getRLE(){
    return RLE;
    }


//get the variable names from the data file
public static String[] getVariableNames(){

return variableArray;
}

//get the instance of the model mixReg declared in newModel
public mixregGUI getMixReg(){

return mxr;
}


//get title from the text box
public String getTitle(){

    String titleString = titleField.getText().toString();

    return titleString;
    
}

//get subtitle from the text box
public String getSubTitle(){

       String SubTitleString = subtitleField.getText().toString();

       return SubTitleString;
    
}



//check if the outcome type is selected as continuos or dichotomous
public boolean isOutcomeContinous(){
    
    boolean selection = true;
    
    if (continuousRadio.isSelected() == true){
    
    selection = true;
            }
    else if (dichotomousRadio.isSelected() == true){
    
    selection = false;
    }

    return selection;
}

public boolean isOutcomeNone(){
    System.out.println("In isOutcomeNone NewModel");

    boolean selection = false;
    
    if (noneRadio.isSelected() == true){
        selection = true;
        System.out.println("In isOutcomeNone true");
    } else if (noneRadio.isSelected() == false){
    
        selection = false;
    }
    return selection;
}

public boolean getNoneVar(){
return NoneVar;

}


public DefinitionHelper getDefFile(){

return defFile;
}

public String getOutPutFileName() {

      String outPut;

        outPut = subtitleField.getText().toString();

        return outPut;
      
      //return "";
    }

}
