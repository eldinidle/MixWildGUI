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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;

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
    static boolean outComeBoolean;
    static mixregGUI mxr;
    InstructionsGUI instructions;
    static boolean isRandomScale = false;
    static String dataFileNameRef;
    final ImageIcon icon;
    String missingValue = "0";
    
    
    
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
       icon = new ImageIcon(getClass().getResource("/resources/mixLogo.png"));
      // defFile = new def_lib.DefinitionHelper();
      
      //subtitle ---> ouput prefix + date and time
       
       titleField.setEnabled(false);
      // subtitleField.setEnabled(false);
       continuousRadio.setEnabled(false);
       dichotomousRadio.setEnabled(false);
       //randomLocationEffects.setEnabled(false);
       newModelSubmit.setEnabled(false);
       newModelMissingValueCode.setText("-9999");
       newModelMissingValueCode.setEnabled(false);
       newModelMissingValues.setEnabled(false);
       newModelMissingValues.setSelected(true);
       noneRadio.setEnabled(false);
       randomScaleCheckBox.setEnabled(false);
       oneRLERadio.setEnabled(false);
       moreThanOneRLERadio.setEnabled(false);
       
       dataFileLabel.setToolTipText("Insert a data file in .csv format");
       fileBrowseButton.setToolTipText("Insert a data file in .csv format");
       
       filePath.setToolTipText("Insert a data file in .csv format");
       titleField.setToolTipText("Insert title for the model");
      // subtitleField.setToolTipText("Insert subtitle for the model");
       //randomLocationEffects.setToolTipText("Select the number of random location effects. Minimum value is 1");
       
      
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        dataFileLabel = new javax.swing.JLabel();
        filePath = new javax.swing.JTextField();
        fileBrowseButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        titleField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
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
        oneRLERadio = new javax.swing.JRadioButton();
        moreThanOneRLERadio = new javax.swing.JRadioButton();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Model for Stage 1 Analysis");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dataFileLabel.setText("Data File: ");
        getContentPane().add(dataFileLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, -1, -1));

        filePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filePathActionPerformed(evt);
            }
        });
        getContentPane().add(filePath, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 205, -1));

        fileBrowseButton.setText("Browse");
        fileBrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileBrowseButtonActionPerformed(evt);
            }
        });
        getContentPane().add(fileBrowseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 90, -1));

        jLabel2.setText("Title:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, -1, -1));

        titleField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleFieldActionPerformed(evt);
            }
        });
        getContentPane().add(titleField, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 205, -1));

        jLabel4.setText("Random Location Effects:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel5.setText("Stage 2 Outcome:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, 30));

        buttonGroup1.add(continuousRadio);
        continuousRadio.setText("Continuous");
        continuousRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continuousRadioActionPerformed(evt);
            }
        });
        getContentPane().add(continuousRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, -1, -1));

        buttonGroup1.add(dichotomousRadio);
        dichotomousRadio.setText("Dichotomous/Ordinal");
        dichotomousRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dichotomousRadioActionPerformed(evt);
            }
        });
        getContentPane().add(dichotomousRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, -1, -1));

        newModelSubmit.setText("Submit");
        newModelSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newModelSubmitActionPerformed(evt);
            }
        });
        getContentPane().add(newModelSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 360, 90, -1));

        newModelCancel.setText("Cancel");
        newModelCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newModelCancelActionPerformed(evt);
            }
        });
        getContentPane().add(newModelCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 360, 90, -1));

        newModel_resetButton.setText("Reset");
        newModel_resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newModel_resetButtonActionPerformed(evt);
            }
        });
        getContentPane().add(newModel_resetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 360, 90, -1));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 334, -1, -1));

        newModelMissingValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newModelMissingValuesActionPerformed(evt);
            }
        });
        getContentPane().add(newModelMissingValues, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, -1, 20));

        jLabel6.setText("Missing value code:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, -1, 30));

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
        getContentPane().add(newModelMissingValueCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, 77, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/mixLogo.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, 31));

        jLabel8.setText("Any missing values in data?");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        buttonGroup1.add(noneRadio);
        noneRadio.setText("None");
        noneRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noneRadioActionPerformed(evt);
            }
        });
        getContentPane().add(noneRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, -1, 30));

        jButton1.setText("Instructions");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(391, 9, 110, -1));

        jLabel9.setText("Before importing a data file, please go through these:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        jLabel10.setText("Random Scale?");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, -1));

        randomScaleCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomScaleCheckBoxActionPerformed(evt);
            }
        });
        getContentPane().add(randomScaleCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 520, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 144, 524, 10));

        buttonGroup2.add(oneRLERadio);
        oneRLERadio.setText("Intercept");
        getContentPane().add(oneRLERadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, -1, -1));

        buttonGroup2.add(moreThanOneRLERadio);
        moreThanOneRLERadio.setText("Intercept + Slope(s)");
        getContentPane().add(moreThanOneRLERadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, -1, -1));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 336, 516, 10));

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
        
       // fileOpen();
        
       int returnVal = fileChooser.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
       // File file = fileChooser.getSelectedFile();
        // What to do with the file, e.g. display it in a TextArea
        //textarea.read( new FileReader( file.getAbsolutePath() ), null );
        
        //Select file from the file object
        file = fileChooser.getSelectedFile();
      //get file path to display on the text box
        String fileName = file.getAbsolutePath();
        dataFileNameRef = fileName;
        
        filePath.setText(fileName);
        
        //enable other buttons here:
        titleField.setEnabled(true);
        //subtitleField.setEnabled(true);
        //randomLocationEffects.setEnabled(true);
        oneRLERadio.setEnabled(true);
        moreThanOneRLERadio.setEnabled(true);
        continuousRadio.setEnabled(true);
        dichotomousRadio.setEnabled(true);
       // randomLocationEffects.setEnabled(true);
        newModelSubmit.setEnabled(true);
        newModelMissingValues.setEnabled(true);
        newModelMissingValueCode.setEnabled(true);
        noneRadio.setEnabled(true);
        randomScaleCheckBox.setEnabled(true);
        randomScaleCheckBox.setSelected(true);
        
        newModelMissingValueCode.selectAll();
        
        System.out.println(file.getAbsolutePath());
    } else {
        System.out.println("File access cancelled by user.");
    }
        
      
    }//GEN-LAST:event_fileBrowseButtonActionPerformed

    private void continuousRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continuousRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_continuousRadioActionPerformed

    private void newModelSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newModelSubmitActionPerformed
        
        //check if missing value is set as zero by the user
        
        
    if (oneRLERadio.isSelected() == true){
            RLE = 1;
    } else if (moreThanOneRLERadio.isSelected() == true) {
            RLE = 2;
    }
        
        defFile = new DefinitionHelper(RLE, !isOutcomeContinous());
        System.out.println("RLE: " + String.valueOf(RLE));
        
        defFile.modelSelector(RLE, isOutcomeContinous());
        
        try {
            //convert csv to .dat file ...
            //defFile.
            defFile.csvToDatConverter(file);
        } catch (IOException ex) {
            Logger.getLogger(NewModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!",JOptionPane.INFORMATION_MESSAGE);
        }
        
        
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
            
           defFile.setDataFilename(extractDatFilePath()); //change this to .dat file location
           
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
        } else {
            try {
                // do nothing and go next
              defFile.setAdvancedMissingValue(missingValue);
            } catch (Exception ex) {
                Logger.getLogger(NewModel.class.getName()).log(Level.SEVERE, null, ex);
            }
                System.out.println("From defHelper | Missing Value: " + defFile.getAdvancedMissingValue());
        }      
        // Read random location effects from new Model
        //RLE = (Integer) randomLocationEffects.getValue();
        NoneVar = isOutcomeNone();
        outComeBoolean = isOutcomeContinous();
       
        System.out.println("NoneVar: " + String.valueOf(NoneVar));
        
        System.out.println(String.valueOf(isOutcomeContinous()));
        System.out.println("IsOutcomeNone: " + String.valueOf(isOutcomeNone()));
        
       // set Values in def helper
       defFile.setModelTitle(getTitle());
       System.out.println("From defHelper | Title: " + defFile.getModelTitle());
       
       //defFile.setModelSubtitle(getSubTitle());
       System.out.println("From defHelper | Subtitle: " + defFile.getModelSubtitle());
       
       if (randomScaleCheckBox.isSelected()){
           
            isRandomScale = true;
        
        } else {
            isRandomScale = false;
       
       }
     
//       mxr = new mixregGUI();
//       mxr.isSubmitClicked();
//       mxr.setVisible(true);
//       //Update ID, stage one and stage two variable comboboxes
//       mxr.updateComboBoxes();
       
       NewModel.defFile.setModelSubtitle("Created with MixWILD GUI");
       System.out.println("From defHelper | Subtitle: " + NewModel.defFile.getModelSubtitle());
       
       //set advanced options defaults
        try {
            NewModel.defFile.setModelFixedInt(String.valueOf(1));
            System.out.println("From defHelper | Mean SubModel Checked?: " + NewModel.defFile.getModelFixedInt());
            //tryCount = 1;
        } catch (Exception ex) {
            //catchCount = 1;
            Logger.getLogger(advancedOptions.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }
        
        try {
            NewModel.defFile.setModelRandomInt(String.valueOf(1));
            System.out.println("From defHelper | BS SubModel Checked?: " + NewModel.defFile.getModelRandomInt());
            
        } catch (Exception ex) {
            
            Logger.getLogger(advancedOptions.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }
        
        try {
            NewModel.defFile.setModelScaleInt(String.valueOf(1));
            System.out.println("From defHelper | WS SubModel Checked?: " + NewModel.defFile.getModelScaleInt());
            //tryCount = 1;
        } catch (Exception ex) {
            //catchCount = 1;
            Logger.getLogger(advancedOptions.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }
        
        try {
            NewModel.defFile.setAdvancedAdaptiveQuad(String.valueOf(1));
            System.out.println("From defHelper | Adaptive Quadriture Checked?: " + NewModel.defFile.getAdvancedAdaptiveQuad());
            //tryCount = 1;
        } catch (Exception ex) {
            //catchCount = 1;
            Logger.getLogger(advancedOptions.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }
        
        try {
            NewModel.defFile.setAdvancedConvergence(String.valueOf(0.001));
            System.out.println("From defHelper | Convergence: " + NewModel.defFile.getAdvancedConvergence());
            //tryCount = 1;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            Logger.getLogger(advancedOptions.class.getName()).log(Level.SEVERE, null, ex);
            //catchCount = 1;
        }
        
        try {
            NewModel.defFile.setAdvancedQuadPoints(String.valueOf(11));
            System.out.println("From defHelper | Quadriture Points: " + NewModel.defFile.getAdvancedQuadPoints());
            //tryCount = 1;
        } catch (Exception ex) {
            //catchCount = 1;
            Logger.getLogger(advancedOptions.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }
        
        try {
            NewModel.defFile.setAdvancedMaxIteration(String.valueOf(100));
            System.out.println("From defHelper | Maximum Iteraions: " + NewModel.defFile.getAdvancedMaxIteration());
            //tryCount = 1;
        } catch (Exception ex) {
            //catchCount = 1;
            Logger.getLogger(advancedOptions.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }
        
        try {
            NewModel.defFile.setAdvancedRidge(String.valueOf(0.15));
            System.out.println("From defHelper | Ridge: " + NewModel.defFile.getAdvancedRidge());
            //tryCount = 1;
        } catch (Exception ex) {
            //catchCount = 1;
            Logger.getLogger(advancedOptions.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }
        
            NewModel.defFile.setOutputPrefix(extractDatFileName() + "_Output");
            System.out.println("From defHelper | Output file name: " + NewModel.defFile.getOutputPrefix());
       
        
            //set conditions here:
            if (newModelMissingValueCode.getText().equals("0") || newModelMissingValueCode.getText().equals("00") || newModelMissingValueCode.getText().equals("000")){
            //show message alert here:
            JOptionPane.showMessageDialog(null, "Invalid missing value code, 0 implies there are no missing values. Please use some other value. E.g., -9999", "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            
            } else {
                 mxr = new mixregGUI();
                 mxr.isSubmitClicked();
                 mxr.setVisible(true);
                //Update ID, stage one and stage two variable comboboxes
                mxr.updateComboBoxes();
                this.dispose();
            }
            
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
       // subtitleField.setEditable(false);
       // subtitleField.setText("");
        //randomLocationEffects.setValue(1);
        //randomLocationEffects.setEnabled(false);
        continuousRadio.setEnabled(false);
        dichotomousRadio.setEnabled(false);
        //randomLocationEffects.setEnabled(false);
        newModelSubmit.setEnabled(false);
        newModelMissingValues.setEnabled(false);
        newModelMissingValueCode.setEnabled(false);
        noneRadio.setSelected(false);
        noneRadio.setEnabled(false);
        randomScaleCheckBox.setSelected(false);
        randomScaleCheckBox.setEnabled(false);

    }//GEN-LAST:event_newModel_resetButtonActionPerformed

    private void newModelMissingValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newModelMissingValuesActionPerformed
        // TODO add your handling code here:
        
        if (newModelMissingValues.isSelected() == true) {
            newModelMissingValueCode.setEnabled(true);
            newModelMissingValueCode.setText("-9999");
            //missingValue = newModelMissingValueCode.getText();
            
//            try {
//                NewModel.defFile.setAdvancedMissingValue(newModelMissingValueCode.getText().toString());
//                System.out.println("From defHelper | MissingValue when present: " + NewModel.defFile.getAdvancedMissingValue());
//            } catch (Exception ex) {
//                Logger.getLogger(NewModel.class.getName()).log(Level.SEVERE, null, ex);
//                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
//            }  
        } else {
            
            newModelMissingValueCode.setEnabled(false);
//            //newModelMissingValueCode. 
//            //missingValue = "0";
//            
//            try {
//                NewModel.defFile.setAdvancedMissingValue(missingValue);
//                System.out.println("From defHelper | MissingValue when unchecked: " + NewModel.defFile.getAdvancedMissingValue());
//            } catch (Exception ex) {
//                Logger.getLogger(NewModel.class.getName()).log(Level.SEVERE, null, ex);
//                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
//            }  
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
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton continuousRadio;
    private javax.swing.JLabel dataFileLabel;
    private javax.swing.JRadioButton dichotomousRadio;
    private javax.swing.JButton fileBrowseButton;
    private javax.swing.JTextField filePath;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JRadioButton moreThanOneRLERadio;
    private javax.swing.JButton newModelCancel;
    private javax.swing.JTextField newModelMissingValueCode;
    private javax.swing.JCheckBox newModelMissingValues;
    private javax.swing.JButton newModelSubmit;
    private javax.swing.JButton newModel_resetButton;
    private javax.swing.JRadioButton noneRadio;
    private javax.swing.JRadioButton oneRLERadio;
    private javax.swing.JCheckBox randomScaleCheckBox;
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

public static String getDataFileName(){

return dataFileNameRef;
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
//public String getSubTitle(){
//
//       String SubTitleString = subtitleField.getText().toString();
//
//       return SubTitleString;
//    
//}



//check if the outcome type is selected as continuos or dichotomous
public boolean isOutcomeContinous(){
    
    boolean selection = true;
    
    if (continuousRadio.isSelected() == true){
    
    selection = true;
    System.out.println("Outcome selected at Newmodel: " + String.valueOf(selection));
            }
    else if (dichotomousRadio.isSelected() == true){
    
    selection = false;
    System.out.println("Outcome selected at Newmodel: " + String.valueOf(selection));
    }
    
    System.out.println("Outcome selected at Newmodel: " + String.valueOf(selection));
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

public boolean getOutComeType(){

return outComeBoolean;
}


public DefinitionHelper getDefFile(){

return defFile;
}

//public String getOutPutFileName() {
//
//      String outPut;
//
//        outPut = subtitleField.getText().toString();
//
//        return outPut;
//      
//      //return "";
//    }


public String extractDatFileName(){

    String fileLoc = file.getAbsolutePath();
    String fileName = fileLoc.substring(fileLoc.lastIndexOf(File.separator) + 1);
    int iend = fileName.indexOf(".");
    
    if (iend != -1){
    fileName = fileName.substring(0, iend);
    }

    return fileName;
}

public String extractDatFilePath(){

String csvPath = file.getAbsolutePath();
String datPath = FilenameUtils.removeExtension(csvPath) + ".dat";


return datPath;
}



}
