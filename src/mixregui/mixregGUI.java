/*
 * MixWild, a program to model subject-level slope and variance on continuous or ordinal outcomes
    Copyright (C) 2018 Genevieve Dunton & Donald Hedeker

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

import com.opencsv.CSVReader;
import java.awt.Desktop;
import java.net.URL;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import def_lib.MixLibrary;
import def_lib.ModelBuilder;
import def_lib.SuperUserMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.FilenameUtils;
import java.io.Serializable;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import org.apache.commons.io.FileUtils;

/**
 * Main class for the program that is used to manipulate regressors
 *
 * @author adityaponnada
 */
public class mixregGUI extends javax.swing.JFrame implements Serializable {

    public static mixregGUI mxr;

    // Declarations from old java
    public File file;

    static String[] variableArray;

    static int RLE;
    static boolean NoneVar;
    static boolean outComeBoolean;
    static boolean isRandomScale = false;
    static String dataFileNameRef;
    final ImageIcon icon;
    final ImageIcon bigIcon;
    static int iconPositionX;
    static int iconPositionY;
    String missingValue = "0";
    static int revealHiddenTabs;
    boolean validDataset = true;
    boolean isNewModalConfigSubmitted;
    boolean isUpdateStage2ConfigClicked;
    boolean isStageOneSubmitted;
    boolean isStageTwoSubmitted;

//    public static DefinitionHelper defFile;
    public static MixLibrary defFile;
    public static ModelBuilder modelBuilder;

    public static MixRegGuiStates MXRStates;

    public static SystemLogger logger;
    public String sessionFolderName;

    public int getRLE() {
        return RLE;
    }

    //get the variable names from the data file
    public static String[] getVariableNames() {

        return variableArray;
    }

    //get data file name when created
    public static String getDataFileName() {

        return dataFileNameRef;
    }

    //get title from the text box
    public String getTitle() {
        return titleField.getText();
    }

    public boolean getMissingValuePresent() {
        return missingValuePresent.isSelected();
    }

    public boolean getMissingValueAbsent() {
        return missingValueAbsent.isSelected();
    }

    public String getNewModelMissingValueCode() {
        return newModelMissingValueCode.getText();
    }

    public boolean getStageOneContinuousRadio() {
        return stageOneContinuousRadio.isSelected();
    }

    public boolean getStageOneDichotomousRadio() {
        return stageOneDichotomousRadio.isSelected();
    }

    public boolean getStageOneOrdinalRadio() {
        return stageOneOrdinalRadio.isSelected();
    }

    public boolean getOneRLERadio() {
        return oneRLERadio.isSelected();
    }

    public boolean getMoreThanOneRLERadio() {
        return moreThanOneRLERadio.isSelected();
    }

    public boolean getRandomScaleSelectionYes() {
        return randomScaleSelectionYes.isSelected();
    }

    public boolean getRandomScaleSelectionNo() {
        return randomScaleSelectionNo.isSelected();
    }

    public boolean getIncludeStageTwoYes() {
        return includeStageTwoYes.isSelected();
    }

    public boolean getIncludeStageTwoNo() {
        return includeStageTwoNo.isSelected();
    }

    public boolean getStageTwoSingleLevel() {
        return stageTwoSingleLevel.isSelected();
    }

    public boolean getStageTwoMultiLevel() {
        return stageTwoMultiLevel.isSelected();
    }

    public boolean getStageTwoContinuousRadio() {
        return stageTwoContinuousRadio.isSelected();
    }

    public boolean getStageTwoDichotomousRadio() {
        return stageTwoDichotomousRadio.isSelected();
    }

    public boolean getCountRadio() {
        return countRadio.isSelected();
    }

    public boolean getMultinomialRadio() {
        return multinomialRadio.isSelected();
    }

    public String getSeedTextBox() {
        return seedTextBox.getText();
    }

//check if the outcome type is selected as continuos or dichotomous
    public boolean isOutcomeContinous() {

        boolean selection = true;

        if (stageTwoContinuousRadio.isSelected() == true) {

            selection = true;
            System.out.println("Outcome selected at Newmodel: " + String.valueOf(selection));
        } else if (stageTwoDichotomousRadio.isSelected() == true) {

            selection = false;
            System.out.println("Outcome selected at Newmodel: " + String.valueOf(selection));
        }

        System.out.println("Outcome selected at Newmodel: " + String.valueOf(selection));
        return selection;
    }

    public boolean isOutcomeNone() {
        System.out.println("In isOutcomeNone NewModel");

        boolean selection = false;

        if (includeStageTwoNo.isSelected() == true) {
            selection = true;
            System.out.println("In isOutcomeNone true");
        } else if (includeStageTwoYes.isSelected() == false) {

            selection = false;
        }
        return selection;
    }

    public boolean getNoneVar() {
        return NoneVar;

    }

    public boolean getOutComeType() {

        return outComeBoolean;
    }

    public MixLibrary getDefFile() {

        return defFile;
    }

    public String extractDatFileName() {

        String fileLoc = file.getAbsolutePath();
        String fileName = fileLoc.substring(fileLoc.lastIndexOf(File.separator) + 1);
        int iend = fileName.indexOf(".");

        if (iend != -1) {
            fileName = fileName.substring(0, iend);
        }

        return fileName;
    }

    public String extractDatFilePath() {

        String csvPath = file.getAbsolutePath();
        String datPath = FilenameUtils.getFullPath(csvPath)
                + defFile.getUtcDirPath()
                + FilenameUtils.getBaseName(csvPath) + ".dat";
        return datPath;
    }

    public void getDataFromCSV() throws FileNotFoundException, IOException {

        Object[] columnnames;

        CSVReader CSVFileReader = new CSVReader(new FileReader(file));
        List myEntries = CSVFileReader.readAll();
        columnnames = (String[]) myEntries.get(0);

        // validation: check first row should be column names (every column name contains letters)
        for (int i = 0; i < columnnames.length; i++) {
            String colname = (String) columnnames[i];
            // check if colname contains just numbers
            if (colname.matches("[0-9]+")) {
                validDataset = false;
                JOptionPane.showMessageDialog(null, "The first row of .csv file should be column names in letters.",
                        "Dataset Error", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            validDataset = true;
        }

        outerloop:
        if (validDataset) {
            DefaultTableModel tableModel = new DefaultTableModel(columnnames, myEntries.size() - 1) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            };
            int rowcount = tableModel.getRowCount();

            for (int x = 0; x < rowcount + 1; x++) {
                int columnnumber = 0;
                // if x = 0 this is the first row...skip it... data used for columnnames
                if (x > 0) {
                    for (String thiscellvalue : (String[]) myEntries.get(x)) {
                        if (thiscellvalue.length() == 0) {
                            validDataset = false;
                            JOptionPane.showMessageDialog(null, "The .csv file should contain no blanks. Please insert a number for all missing values (e.g., -999)."
                                    + "\n" + "Missing value codes should be numeric only.",
                                    "Dataset Error", JOptionPane.INFORMATION_MESSAGE);
                            break outerloop;
                        }
                        if (!isNumeric(thiscellvalue)) {
                            validDataset = false;
                            JOptionPane.showMessageDialog(null, "The .csv file should contain only numeric values, except for the headers in the first row."
                                    + "\n" + "Missing value codes should be numeric only.",
                                    "Dataset Error", JOptionPane.INFORMATION_MESSAGE);
                            break outerloop;
                        }
                        tableModel.setValueAt(thiscellvalue, x - 1, columnnumber);
                        columnnumber++;
                    }
                }
            }
            validDataset = true;

            dataTable.setModel(tableModel);
            dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            dataTable.setAutoscrolls(true);
        }

    }

    public void printFileName() {
        String path = file.getName();

        printedFileName.setText(path);

    }

    public String randomLocationEffects() {

        String randLocString = "";

        if (oneRLERadio.isSelected()) {
            randLocString = "Intercept";

        } else if (moreThanOneRLERadio.isSelected()) {
            randLocString = "Intercept + Slope";

        }

        return randLocString;
    }

    public String stageOneOutcomeTypeString() {

        String outcomeTypeText = "";

        if (stageOneContinuousRadio.isSelected()) {
            outcomeTypeText = "Continuous";
        } else if (stageOneDichotomousRadio.isSelected()) {
            outcomeTypeText = "Dichotomous";
        } else if (stageOneOrdinalRadio.isSelected()) {
            outcomeTypeText = "Ordinal";
        }
        return outcomeTypeText;

    }

    public String stageTwoOutcomeTypeString() {

        String outcomeTypeText = "";

        if (stageTwoContinuousRadio.isSelected()) {

            outcomeTypeText = "Continuous";
        } else if (stageTwoDichotomousRadio.isSelected()) {

            outcomeTypeText = "Dichotomous";
        } else if (includeStageTwoNo.isSelected()) {

            outcomeTypeText = "None";
        }
        return outcomeTypeText;

    }

    public String stageTwoModelTypeString() {

        String modelTypeText = "";

        if (stageTwoSingleLevel.isSelected()) {

            modelTypeText = "Single-level";
        } else if (stageTwoMultiLevel.isSelected()) {

            modelTypeText = "Multi-level";
        }
        return modelTypeText;

    }

    public String numResamplingString() {

        String numResamplingText;
        numResamplingText = advancedOptions_view.getResamplingRate();
        return numResamplingText;

    }

    private boolean validateFields() {
        boolean allFieldsEntered = true;
        System.out.println("FIELD VALIDATE: " + "Called");

        if (filePath.getText().trim().length() == 0) {
            allFieldsEntered = false;
            JOptionPane.showMessageDialog(null, "Please select a .csv file for your analysis", "Missing information!", JOptionPane.INFORMATION_MESSAGE, icon);
            System.out.println("FIELD VALIDATE: " + "File path missing");
        }
        //radio buttons for random location effects
        if (buttonGroup2.getSelection() == null) {
            allFieldsEntered = false;
            JOptionPane.showMessageDialog(null, "Please select random location effects", "Missing information!", JOptionPane.INFORMATION_MESSAGE, icon);
            System.out.println("FIELD VALIDATE: " + "RLE not selected");
        }
        //for stage 2 outcome type
        if (buttonGroup3.getSelection() == null & includeStageTwoYes.isSelected()) {
            allFieldsEntered = false;
            JOptionPane.showMessageDialog(null, "Please select a stage 2 outcome type", "Missing information!", JOptionPane.INFORMATION_MESSAGE, icon);
            System.out.println("FIELD VALIDATE: " + "Stage 2 outcome missing");
        }
        //For missing values, yes or no.
        if (buttonGroup4.getSelection() == null) {
            allFieldsEntered = false;
            JOptionPane.showMessageDialog(null, "Please confirm if your data has missing values", "Missing information!", JOptionPane.INFORMATION_MESSAGE, icon);
            System.out.println("FIELD VALIDATE: " + "Missing value missing");
        }

        if (missingValuePresent.isSelected() && newModelMissingValueCode.getText().trim().length() == 0) {
            allFieldsEntered = false;
            JOptionPane.showMessageDialog(null, "Please don't leave the missing code value as blank", "Missing information!", JOptionPane.INFORMATION_MESSAGE, icon);
            System.out.println("FIELD VALIDATE: " + "Missing value blank");
        }
        System.out.println("FIELD VALIDATE: " + "about to exit");
        return allFieldsEntered;
    }

    private String generateSeed() {
        String seed;

        Random rnd = new Random();

        int rndSeed = rnd.nextInt(65535) + 1;

        seed = String.valueOf(rndSeed);

        return seed;
    }

    // Original declarations
    // NewModel newModel;
    advancedOptions advancedOptions_view;
    stageOneRegs stage_1_regs;
    stageTwoRegs stage_2_regs;
    def_lib.SuperUserMenu superUserMenuLaunch;

    // i represents number of random location effects selected in new model
    int i;

    int levelOneRegSize = 0;
    int levelTwoRegSize = 0;
    int stageTwoLevelOneRegSize = 0;
    int stageTwoLevelTwoRegSize = 0;
    int levelOneDisaggSize = 0;

    int SUPERUSER_KEY = 0;

    String[] variableNamesCombo;

    DefaultComboBoxModel<String> IDList;

    DefaultComboBoxModel<String> StageOneList;

    DefaultComboBoxModel<String> StageTwoList;

    DefaultListModel<String> savedVariablesStageOne;

    ArrayList<ArrayList<JCheckBox>> levelOneBoxes;

    ArrayList<ArrayList<JCheckBox>> levelTwoBoxes;

    ArrayList<ArrayList<JCheckBox>> stageTwoBoxes;

    ArrayList<ArrayList<JCheckBox>> stageTwoLevelOneGridBoxes;

    ArrayList<ArrayList<JCheckBox>> stageTwoLevelTwoGridBoxes;

    ArrayList<ArrayList<JCheckBox>> disaggVarianceBoxes;

    public static int IDpos;
    public static int stageOnePos;
    public static int stageTwoPos;

    boolean scaleChecked = false;
    boolean randomChecked = false;
    boolean isIDChanged = false;
    boolean isStageOneOutcomeChanged = false;
    boolean isStageTwoOutcomeChanged = false;

    boolean suppressed = false;
    boolean outcomeNone = false;
    boolean addStageOneCHecked = false;
    boolean addStageTwoChecked = false;

    ArrayList<String> levelOneSelected;
    ArrayList<String> levelTwoSelected;
    ArrayList<String> stageTwoSelected;
    ArrayList<String> stageTwoLevelOneSelected;
    ArrayList<String> stageTwoLevelTwoSelected;

    static ActionListener actionListener;

    int stageOneClicked = 0;

    JFileChooser fileChooser = new JFileChooser();
    int selectedModel;
    String defFilePath;

    String[] dataValues;

    boolean outComeType;

    static String outPutStageTwo;

    private void updateMixRegGUI() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        // newModel = new NewModel();
//        advancedOptions_view = new advancedOptions();
        //instructions = new InstructionsGUI();
        variableNamesCombo = getVariableNames();
        outcomeNone = getNoneVar();
        outComeType = getOutComeType();

        IDList = new DefaultComboBoxModel<String>();
        StageOneList = new DefaultComboBoxModel<String>();
        StageTwoList = new DefaultComboBoxModel<String>();
        NoAssociationRadio.setSelected(true);
        stage_1_regs = new stageOneRegs();
        stage_2_regs = new stageTwoRegs();

        i = getRLE();
        System.out.println(String.valueOf(i));

//        stageOneTabs.setEnabledAt(2, false);
        suppressIntCheckBox.setVisible(true);
        suppressIntCheckBox.setSelected(false);
        suppressIntCheckBox.setEnabled(true);

        //Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/mixLogo.png"));
        //setIconImage(image);
        if (i == MixLibrary.STAGE_ONE_RLE_SLOPE) {

            NoAssociationRadio.setText("Yes");

            LinearAssociationRadio.setText("No");
            QuadraticAssociationRadio.setVisible(false);

            associationLabel.setText("Association of random location & scale?"); //Switch to this when i > 1
            LinearAssociationRadio.setSelected(true);

            // if random location effects are more than one, change the table column names
            level2_BSVar.setVisible(false);
            level1_BSVar.setText("Random Slope");
            level1_WSVar.setText("Scale");
            //level2_BSVar.setText("Loc. eff.");
            level2_WSVar.setText("Scale");

        }

        stageOneLevelOnePanel.setLayout(new BorderLayout());
        stageOneLevelTwoPanel.setLayout(new BorderLayout());
        stageTwoLevelOnePanel.setLayout(new BorderLayout());
        stageTwoLevelTwoPanel.setLayout(new BorderLayout());

        System.out.println("Right before");
        if (isOutcomeNone() == true) {
            System.out.println("In isOutcomeNone MixReg");
            startStageTwo.setText("Run Stage 1");
            System.out.println(startStageTwo.getText());
            //stageOneTabs.set
//            stageOneTabs.setEnabledAt(2, false);
        } else if (isOutcomeNone() == false) {
            startStageTwo.setText("Configure Stage 2");
        }
        System.out.println("Right after");

        if (outComeType == false) {

            outcomeCatButton.setEnabled(true);
            outcomeCatButton.setVisible(true);
            //outComeText.setVisible(true);
            outCategoryDisplay.setVisible(true);
            //outComeText.setEditable(false);
            outCategoryDisplay.setEnabled(true);
            System.out.println("outCatButton Enabled: " + String.valueOf(isOutcomeContinous()));
            jPanel5.setEnabled(true);

        } else if (outComeType == true) {

            outcomeCatButton.setVisible(false);
            //outComeText.setVisible(false);
            System.out.println("outCatButton Enabled: " + String.valueOf(isOutcomeContinous()));
            jPanel5.setVisible(false);

        }

        try {
            if (defFile.getAdvancedUseRandomScale() == "1") {
                NoAssociationRadio.setEnabled(false);
                LinearAssociationRadio.setEnabled(false);
                QuadraticAssociationRadio.setEnabled(false);
            }
        } catch (Exception eoe) {
            System.out.println("No Random Scale Option Set");
            SystemLogger.LOGGER.log(Level.SEVERE, eoe.toString() + "{0}", SystemLogger.getLineNum());
        }
    }

    private void setFirstTabStatus(boolean turnOn) {
        dataFileLabel.setVisible(turnOn);
        filePath.setVisible(turnOn);
        fileBrowseButton.setVisible(turnOn);

        titleViewLabel.setVisible(turnOn);
        titleField.setVisible(turnOn);

        DatasetLabel.setVisible(turnOn);
        missingViewLabel.setVisible(turnOn);
        missingValueAbsent.setVisible(turnOn);
        missingValuePresent.setVisible(turnOn);

        missingCodeViewLabel.setVisible(turnOn);
        newModelMissingValueCode.setVisible(turnOn);

        stageOneOutcomeViewLabel.setVisible(turnOn);
        stageOneContinuousRadio.setVisible(turnOn);
        stageOneDichotomousRadio.setVisible(turnOn);
        stageOneOrdinalRadio.setVisible(turnOn);
        stageOneOutcomeHelpButton.setVisible(turnOn);

        rleViewLabel.setVisible(turnOn);
        oneRLERadio.setVisible(turnOn);
        moreThanOneRLERadio.setVisible(turnOn);

        randomScaleViewLabel.setVisible(turnOn);
        randomScaleSelectionYes.setVisible(turnOn);
        randomScaleSelectionNo.setVisible(turnOn);

        includeStageTwoLabel.setVisible(turnOn);
        includeStageTwoYes.setVisible(turnOn);
        includeStageTwoNo.setVisible(turnOn);
        stageTwoDescription.setVisible(turnOn);

        stageTwoModelTypeLabel.setVisible(turnOn);
        stageTwoSingleLevel.setVisible(turnOn);
        stageTwoMultiLevel.setVisible(turnOn);

        stageTwoOutcomeTypeLabel.setVisible(turnOn);
        stageTwoContinuousRadio.setVisible(turnOn);
        stageTwoDichotomousRadio.setVisible(turnOn);
        countRadio.setVisible(turnOn);
        multinomialRadio.setVisible(turnOn);

        setSeedLabel.setVisible(turnOn);
        seedTextBox.setVisible(turnOn);
        seedHelpButton.setVisible(turnOn);

        newModel_resetButton.setVisible(turnOn);
        newModelSubmit.setVisible(turnOn);
        updateStage2ConfigButton.setVisible(turnOn);

        stageOneModelGiantLabel.setVisible(turnOn);
        stageTwoModelGiantLabel.setVisible(turnOn);

        jSeparator16.setVisible(turnOn);
        jSeparator12.setVisible(turnOn);
        jSeparator8.setVisible(turnOn);

        showHiddenBigIconLabel(!turnOn);

        guiStatesLoadButtonModalConfig.setVisible(turnOn);
        guiStatesSaveButtonModalConfig.setVisible(turnOn);

        datasetHelpButton.setVisible(turnOn);
        datasetMissingValuesHelpButton.setVisible(turnOn);
        stageOneRLEHelpButton.setVisible(turnOn);
        stageOneRSHelpButton.setVisible(turnOn);
        stageTwoModelTypeHelpButton.setVisible(turnOn);
        stageTwoOutcomeTypeHelpButton.setVisible(turnOn);
    }

    /**
     * Creates new form mixregGUI
     */
    public mixregGUI() {
        initComponents();
        // adjust the frame size to fit screen resolution
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        setBounds(0, 0, stageOneTabs.getWidth(), screenSize.height);
        setBounds(0, 0, stageOneTabs.getWidth(), 700);
        setVisible(true);

        MXRStates = new MixRegGuiStates();
        advancedOptions_view = new advancedOptions();

        icon = new ImageIcon(getClass().getResource("/resources/MixWildLogoTiny.png"));
        bigIcon = new ImageIcon(getClass().getResource("/resources/mixwild_logo-red_large.png"));

        stageOneContinuousRadio.setSelected(true);
        stageOneDichotomousRadio.setEnabled(false);
        stageOneOrdinalRadio.setEnabled(false);

        countRadio.setEnabled(false);
        multinomialRadio.setEnabled(false);

        stageTwoSingleLevel.setEnabled(true);
        stageTwoSingleLevel.setSelected(true);
        stageTwoMultiLevel.setEnabled(true);

        //updateMixRegGUI();
        //this.setResizable(false);
        // hide components for user operating in order
        setFirstTabStatus(false);

        // hide tabs
//        stageOneTabs.setEnabledAt(1, false);
//        stageOneTabs.setEnabledAt(2, false);
//        stageOneTabs.setEnabledAt(3, false);
//        stageOneTabs.setEnabledAt(4, false);
//        stageOneTabs.setEnabledAt(5, false);
//        stageOneTabs.setEnabledAt(6, false);
//        stageOneTabs.setEnabledAt(7, false);
        stageOneTabs.remove(jPanel1);
        stageOneTabs.remove(jPanel12);
        stageOneTabs.remove(jPanel3);
        stageOneTabs.remove(jPanel4);
        stageOneTabs.remove(jPanel2);
        stageOneTabs.remove(jPanel6);
        stageOneTabs.remove(jPanel14);

        //updateMixRegGUI();
        //this.setResizable(false);
        // TODO: Fix superuser menu code
        //superUserMenu.setVisible(SUPERUSER_KEY > 2);
//       IDpos = IDvariableCombo.getSelectedIndex();
//       stageOnePos = StageOneVariableCombo.getSelectedIndex();
//       stageTwoPos = stageTwoOutcome.getSelectedIndex();
//        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        includeStageTwoGroup = new javax.swing.ButtonGroup();
        randomScaleSelectionGroup = new javax.swing.ButtonGroup();
        stageOneOutcomeGroup = new javax.swing.ButtonGroup();
        stageTwoLevelGroup = new javax.swing.ButtonGroup();
        parentPanel = new javax.swing.JPanel();
        stageOneTabs = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        fileBrowseButton = new javax.swing.JButton();
        filePath = new javax.swing.JTextField();
        dataFileLabel = new javax.swing.JLabel();
        titleViewLabel = new javax.swing.JLabel();
        titleField = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        rleViewLabel = new javax.swing.JLabel();
        oneRLERadio = new javax.swing.JRadioButton();
        moreThanOneRLERadio = new javax.swing.JRadioButton();
        randomScaleViewLabel = new javax.swing.JLabel();
        stageTwoOutcomeTypeLabel = new javax.swing.JLabel();
        stageTwoContinuousRadio = new javax.swing.JRadioButton();
        stageTwoDichotomousRadio = new javax.swing.JRadioButton();
        missingValueAbsent = new javax.swing.JRadioButton();
        missingValuePresent = new javax.swing.JRadioButton();
        missingViewLabel = new javax.swing.JLabel();
        missingCodeViewLabel = new javax.swing.JLabel();
        newModelMissingValueCode = new javax.swing.JTextField();
        seedTextBox = new javax.swing.JTextField();
        setSeedLabel = new javax.swing.JLabel();
        newModel_resetButton = new javax.swing.JButton();
        newModelSubmit = new javax.swing.JButton();
        stageOneModelGiantLabel = new javax.swing.JLabel();
        stageTwoModelGiantLabel = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        stageOneOutcomeViewLabel = new javax.swing.JLabel();
        stageOneContinuousRadio = new javax.swing.JRadioButton();
        stageOneDichotomousRadio = new javax.swing.JRadioButton();
        stageOneOrdinalRadio = new javax.swing.JRadioButton();
        randomScaleSelectionYes = new javax.swing.JRadioButton();
        randomScaleSelectionNo = new javax.swing.JRadioButton();
        includeStageTwoLabel = new javax.swing.JLabel();
        includeStageTwoYes = new javax.swing.JRadioButton();
        includeStageTwoNo = new javax.swing.JRadioButton();
        stageTwoModelTypeLabel = new javax.swing.JLabel();
        stageTwoSingleLevel = new javax.swing.JRadioButton();
        stageTwoMultiLevel = new javax.swing.JRadioButton();
        multinomialRadio = new javax.swing.JRadioButton();
        countRadio = new javax.swing.JRadioButton();
        hiddenBigIconLabel = new javax.swing.JLabel();
        guiStatesLoadButtonModalConfig = new javax.swing.JButton();
        guiStatesSaveButtonModalConfig = new javax.swing.JButton();
        loadModelByBrowseButton = new javax.swing.JButton();
        updateStage2ConfigButton = new javax.swing.JButton();
        newDataSetButton = new javax.swing.JButton();
        DatasetLabel = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        datasetHelpButton = new javax.swing.JLabel();
        seedHelpButton = new javax.swing.JLabel();
        datasetMissingValuesHelpButton = new javax.swing.JLabel();
        stageOneOutcomeHelpButton = new javax.swing.JLabel();
        stageOneRLEHelpButton = new javax.swing.JLabel();
        stageOneRSHelpButton = new javax.swing.JLabel();
        stageTwoDescription = new javax.swing.JLabel();
        stageTwoModelTypeHelpButton = new javax.swing.JLabel();
        stageTwoOutcomeTypeHelpButton = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        resetButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        level1_MeanReg = new javax.swing.JLabel();
        level1_WSVar = new javax.swing.JLabel();
        level2_MeanReg = new javax.swing.JLabel();
        level2_BSVar = new javax.swing.JLabel();
        level2_WSVar = new javax.swing.JLabel();
        level1_BSVar = new javax.swing.JLabel();
        stageOneLevelOnePanel = new javax.swing.JPanel();
        levelOneGrid = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        stageOneLevelTwoPanel = new javax.swing.JPanel();
        levelTwoGrid = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        StageOneVariableCombo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        IDvariableCombo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        addStageOneButton = new javax.swing.JButton();
        advancedOptionsButton = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JSeparator();
        associationPanel = new javax.swing.JPanel();
        associationLabel = new javax.swing.JLabel();
        NoAssociationRadio = new javax.swing.JRadioButton();
        LinearAssociationRadio = new javax.swing.JRadioButton();
        QuadraticAssociationRadio = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        randomLocationEffectsLabel = new javax.swing.JLabel();
        stageTwoOutcomePrintLabel = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JSeparator();
        randomLocationEffectsLabel1 = new javax.swing.JLabel();
        stageTwoOutcomePrintLabel1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        startStageTwo = new javax.swing.JButton();
        guiStatesSaveButtonStageOne = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        addStageTwoTabTwo = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        stageTwoLevelTwoPanel = new javax.swing.JPanel();
        stageTwoRegsGridLvl2 = new javax.swing.JPanel();
        runTabTwoStageOneTwo = new javax.swing.JButton();
        suppressIntCheckBox = new javax.swing.JCheckBox();
        stageTwoOutcome = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 2), new java.awt.Dimension(0, 2), new java.awt.Dimension(32767, 2));
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        outcomeCatButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        outCategoryDisplay = new javax.swing.JTextPane();
        guiStatesSaveButtonStageTwo = new javax.swing.JButton();
        jSeparator20 = new javax.swing.JSeparator();
        stageTwoLevelOnePanel = new javax.swing.JPanel();
        stageTwoRegsGridLvl1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        stageOneOutcomeStageTwoConfigLabel = new javax.swing.JLabel();
        stageOneModelStageTwoConfigLabel = new javax.swing.JLabel();
        stageTwoOutcomeStageTwoConfigLabel = new javax.swing.JLabel();
        stageTwoModelTypeStageTwoConfigLabel = new javax.swing.JLabel();
        numResamplingStageTwoConfigLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        stageOneModelStageTwoConfigLabel1 = new javax.swing.JLabel();
        stageOneOutcomeStageTwoConfigLabel1 = new javax.swing.JLabel();
        stageTwoModelTypeStageTwoConfigLabel1 = new javax.swing.JLabel();
        stageTwoOutcomeStageTwoConfigLabel1 = new javax.swing.JLabel();
        numResamplingStageTwoConfigLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        stageOneOutput = new javax.swing.JTextArea();
        jButton8 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stageTwoOutput = new javax.swing.JTextArea();
        saveStage2OutButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        equationArea = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        printedFileName = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        userGuideDownload = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        exampleDataDownload = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jScrollPane6.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MixWILD");
        setMaximumSize(new java.awt.Dimension(1200, 800));
        setMinimumSize(new java.awt.Dimension(1150, 670));
        setPreferredSize(new java.awt.Dimension(824, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        parentPanel.setLayout(new java.awt.CardLayout());
        getContentPane().add(parentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1224, -1));

        stageOneTabs.setToolTipText("");
        stageOneTabs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        stageOneTabs.setFocusable(false);
        stageOneTabs.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        stageOneTabs.setMinimumSize(new java.awt.Dimension(1000, 800));
        stageOneTabs.setPreferredSize(new java.awt.Dimension(1302, 1064));

        jPanel13.setMinimumSize(new java.awt.Dimension(1000, 800));
        jPanel13.setPreferredSize(new java.awt.Dimension(1297, 1032));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fileBrowseButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        fileBrowseButton.setText("Change Dataset");
        fileBrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileBrowseButtonActionPerformed(evt);
            }
        });
        jPanel13.add(fileBrowseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 40, -1, -1));

        filePath.setEditable(false);
        filePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filePathActionPerformed(evt);
            }
        });
        jPanel13.add(filePath, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 45, 254, -1));

        dataFileLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        dataFileLabel.setText("CSV file path: ");
        dataFileLabel.setToolTipText("");
        jPanel13.add(dataFileLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 45, -1, -1));

        titleViewLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        titleViewLabel.setText("Title (optional):");
        jPanel13.add(titleViewLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, -1, -1));

        titleField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleFieldActionPerformed(evt);
            }
        });
        jPanel13.add(titleField, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 254, -1));
        jPanel13.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 455, 770, 11));

        rleViewLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        rleViewLabel.setText("Specify random location effects:");
        rleViewLabel.setToolTipText("");
        jPanel13.add(rleViewLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, -1, -1));

        buttonGroup2.add(oneRLERadio);
        oneRLERadio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        oneRLERadio.setText("Intercept only");
        oneRLERadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneRLERadioActionPerformed(evt);
            }
        });
        jPanel13.add(oneRLERadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 226, -1, -1));

        buttonGroup2.add(moreThanOneRLERadio);
        moreThanOneRLERadio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        moreThanOneRLERadio.setText("Intercept and slope(s)");
        moreThanOneRLERadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moreThanOneRLERadioActionPerformed(evt);
            }
        });
        jPanel13.add(moreThanOneRLERadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 226, -1, -1));

        randomScaleViewLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        randomScaleViewLabel.setText("Include estimates of random scale:");
        randomScaleViewLabel.setToolTipText("");
        jPanel13.add(randomScaleViewLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 264, -1, -1));

        stageTwoOutcomeTypeLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        stageTwoOutcomeTypeLabel.setText("Stage 2 outcome:");
        stageTwoOutcomeTypeLabel.setToolTipText("");
        jPanel13.add(stageTwoOutcomeTypeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 385, -1, -1));

        buttonGroup3.add(stageTwoContinuousRadio);
        stageTwoContinuousRadio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        stageTwoContinuousRadio.setText("Continuous");
        stageTwoContinuousRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stageTwoContinuousRadioActionPerformed(evt);
            }
        });
        jPanel13.add(stageTwoContinuousRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 383, -1, -1));

        buttonGroup3.add(stageTwoDichotomousRadio);
        stageTwoDichotomousRadio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        stageTwoDichotomousRadio.setText("Dichotomous/Ordinal");
        stageTwoDichotomousRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stageTwoDichotomousRadioActionPerformed(evt);
            }
        });
        jPanel13.add(stageTwoDichotomousRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(691, 383, -1, -1));

        buttonGroup4.add(missingValueAbsent);
        missingValueAbsent.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        missingValueAbsent.setText("No");
        missingValueAbsent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                missingValueAbsentActionPerformed(evt);
            }
        });
        jPanel13.add(missingValueAbsent, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 114, -1, 20));

        buttonGroup4.add(missingValuePresent);
        missingValuePresent.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        missingValuePresent.setText("Yes");
        missingValuePresent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                missingValuePresentActionPerformed(evt);
            }
        });
        jPanel13.add(missingValuePresent, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 114, 60, 20));

        missingViewLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        missingViewLabel.setText("Does your data contain missing values?");
        missingViewLabel.setToolTipText("");
        jPanel13.add(missingViewLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 114, -1, -1));

        missingCodeViewLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        missingCodeViewLabel.setText("What is your missing data coded as?");
        missingCodeViewLabel.setToolTipText("");
        jPanel13.add(missingCodeViewLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 145, -1, -1));

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
        jPanel13.add(newModelMissingValueCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 145, 80, -1));

        seedTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seedTextBoxActionPerformed(evt);
            }
        });
        jPanel13.add(seedTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, 80, -1));

        setSeedLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        setSeedLabel.setText("Set a seed for Stage 2 resampling (optional):");
        setSeedLabel.setToolTipText("<html><p>A seed is a number used to initialize a random number generator.</p>\n<p>Different seeds produce different sequences of random numbers.</p>\n<p>In the context of two-stage models, a seed is helpful for replicating models with identical results.</p>");
        jPanel13.add(setSeedLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, 320, -1));

        newModel_resetButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        newModel_resetButton.setText("Reset");
        newModel_resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newModel_resetButtonActionPerformed(evt);
            }
        });
        jPanel13.add(newModel_resetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 540, 120, 35));

        newModelSubmit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        newModelSubmit.setText("Continue");
        newModelSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newModelSubmitActionPerformed(evt);
            }
        });
        jPanel13.add(newModelSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 540, 120, 35));

        stageOneModelGiantLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        stageOneModelGiantLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        stageOneModelGiantLabel.setText("Stage 1 Model");
        jPanel13.add(stageOneModelGiantLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 230, -1, -1));

        stageTwoModelGiantLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        stageTwoModelGiantLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        stageTwoModelGiantLabel.setText("Stage 2 Model");
        jPanel13.add(stageTwoModelGiantLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 367, -1, -1));
        jPanel13.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 770, 12));

        stageOneOutcomeViewLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        stageOneOutcomeViewLabel.setText("Stage 1 outcome:");
        stageOneOutcomeViewLabel.setToolTipText("");
        jPanel13.add(stageOneOutcomeViewLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 196, -1, -1));

        stageOneOutcomeGroup.add(stageOneContinuousRadio);
        stageOneContinuousRadio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        stageOneContinuousRadio.setText("Continuous");
        stageOneContinuousRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stageOneContinuousRadioActionPerformed(evt);
            }
        });
        jPanel13.add(stageOneContinuousRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 192, -1, -1));

        stageOneOutcomeGroup.add(stageOneDichotomousRadio);
        stageOneDichotomousRadio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        stageOneDichotomousRadio.setText("Dichotomous");
        stageOneDichotomousRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stageOneDichotomousRadioActionPerformed(evt);
            }
        });
        jPanel13.add(stageOneDichotomousRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(691, 192, -1, -1));

        stageOneOutcomeGroup.add(stageOneOrdinalRadio);
        stageOneOrdinalRadio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        stageOneOrdinalRadio.setText("Ordinal");
        stageOneOrdinalRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stageOneOrdinalRadioActionPerformed(evt);
            }
        });
        jPanel13.add(stageOneOrdinalRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(802, 192, -1, -1));

        randomScaleSelectionGroup.add(randomScaleSelectionYes);
        randomScaleSelectionYes.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        randomScaleSelectionYes.setText("Yes");
        randomScaleSelectionYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomScaleSelectionYesActionPerformed(evt);
            }
        });
        jPanel13.add(randomScaleSelectionYes, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, -1, -1));

        randomScaleSelectionGroup.add(randomScaleSelectionNo);
        randomScaleSelectionNo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        randomScaleSelectionNo.setText("No");
        randomScaleSelectionNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomScaleSelectionNoActionPerformed(evt);
            }
        });
        jPanel13.add(randomScaleSelectionNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 260, -1, -1));

        includeStageTwoLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        includeStageTwoLabel.setText("Include Stage 2 model:");
        includeStageTwoLabel.setToolTipText("");
        jPanel13.add(includeStageTwoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 315, -1, -1));

        includeStageTwoGroup.add(includeStageTwoYes);
        includeStageTwoYes.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        includeStageTwoYes.setText("Yes");
        includeStageTwoYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                includeStageTwoYesActionPerformed(evt);
            }
        });
        jPanel13.add(includeStageTwoYes, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 311, -1, -1));

        includeStageTwoGroup.add(includeStageTwoNo);
        includeStageTwoNo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        includeStageTwoNo.setText("No");
        includeStageTwoNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                includeStageTwoNoActionPerformed(evt);
            }
        });
        jPanel13.add(includeStageTwoNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(643, 311, -1, -1));

        stageTwoModelTypeLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        stageTwoModelTypeLabel.setText("Stage 2 model type:");
        stageTwoModelTypeLabel.setToolTipText("");
        jPanel13.add(stageTwoModelTypeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, -1, -1));

        stageTwoLevelGroup.add(stageTwoSingleLevel);
        stageTwoSingleLevel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        stageTwoSingleLevel.setText("Single level");
        stageTwoSingleLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stageTwoSingleLevelActionPerformed(evt);
            }
        });
        jPanel13.add(stageTwoSingleLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 346, -1, -1));

        stageTwoLevelGroup.add(stageTwoMultiLevel);
        stageTwoMultiLevel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        stageTwoMultiLevel.setText("Multilevel");
        stageTwoMultiLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stageTwoMultiLevelActionPerformed(evt);
            }
        });
        jPanel13.add(stageTwoMultiLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(691, 346, -1, -1));

        buttonGroup3.add(multinomialRadio);
        multinomialRadio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        multinomialRadio.setText("Multinomial");
        multinomialRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multinomialRadioActionPerformed(evt);
            }
        });
        jPanel13.add(multinomialRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(919, 383, -1, -1));

        buttonGroup3.add(countRadio);
        countRadio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        countRadio.setText("Count");
        countRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countRadioActionPerformed(evt);
            }
        });
        jPanel13.add(countRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(852, 383, -1, -1));

        hiddenBigIconLabel.setFocusable(false);
        hiddenBigIconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hiddenBigIconLabelMouseClicked(evt);
            }
        });
        jPanel13.add(hiddenBigIconLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 22, -1, -1));

        guiStatesLoadButtonModalConfig.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        guiStatesLoadButtonModalConfig.setText("Load Model");
        guiStatesLoadButtonModalConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guiStatesLoadButtonModalConfigActionPerformed(evt);
            }
        });
        jPanel13.add(guiStatesLoadButtonModalConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 540, 120, 35));

        guiStatesSaveButtonModalConfig.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        guiStatesSaveButtonModalConfig.setText("Save Model");
        guiStatesSaveButtonModalConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guiStatesSaveButtonModalConfigActionPerformed(evt);
            }
        });
        jPanel13.add(guiStatesSaveButtonModalConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 540, 120, 35));

        loadModelByBrowseButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        loadModelByBrowseButton.setText("Start with Previous Model");
        loadModelByBrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadModelByBrowseButtonActionPerformed(evt);
            }
        });
        jPanel13.add(loadModelByBrowseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 258, -1, -1));

        updateStage2ConfigButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        updateStage2ConfigButton.setText("Update Stage 2");
        updateStage2ConfigButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStage2ConfigButtonActionPerformed(evt);
            }
        });
        jPanel13.add(updateStage2ConfigButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 540, -1, 35));

        newDataSetButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        newDataSetButton.setText("Start with New CSV File");
        newDataSetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newDataSetButtonActionPerformed(evt);
            }
        });
        jPanel13.add(newDataSetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 258, -1, -1));

        DatasetLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        DatasetLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        DatasetLabel.setText("Dataset");
        jPanel13.add(DatasetLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 97, -1, -1));
        jPanel13.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 12, 175, -1));

        jLabel34.setText("  ");
        jPanel13.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 183, 164, -1));
        jPanel13.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 299, 770, 11));

        datasetHelpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon4 - Copy.png"))); // NOI18N
        datasetHelpButton.setToolTipText("<html><pre>Is your dataset MixWILD friendly?\n    1) You should always use a .csv file.\n    2) You should ensure that missing values are not blanks.\n    3) Missing value codes should be numeric only.</p>\n    4) Make sure your missing value code is the same as your dataset.\n    5) Please ensure that the data is sorted by IDs.\n    6) The first row in the .csv file should be column names.<pre>");
        datasetHelpButton.setMaximumSize(new java.awt.Dimension(16, 16));
        datasetHelpButton.setMinimumSize(new java.awt.Dimension(16, 16));
        datasetHelpButton.setPreferredSize(new java.awt.Dimension(16, 16));
        jPanel13.add(datasetHelpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 46, -1, -1));

        seedHelpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon4 - Copy.png"))); // NOI18N
        seedHelpButton.setToolTipText("<html><pre>A seed is a number used to initialize a random number generator. Different seeds produce \ndifferent sequences of random numbers.In the context of two-stage models, a seed is helpful for\nreplicating models with identical results.<pre>");
        seedHelpButton.setMaximumSize(new java.awt.Dimension(16, 16));
        seedHelpButton.setMinimumSize(new java.awt.Dimension(16, 16));
        seedHelpButton.setPreferredSize(new java.awt.Dimension(16, 16));
        jPanel13.add(seedHelpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 422, -1, -1));

        datasetMissingValuesHelpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon4 - Copy.png"))); // NOI18N
        datasetMissingValuesHelpButton.setToolTipText("<html><pre>Click on missing values if there are any in your dataset;\nspecify the missing value code in the box (e.g., '-999').<pre>");
        datasetMissingValuesHelpButton.setMaximumSize(new java.awt.Dimension(16, 16));
        datasetMissingValuesHelpButton.setMinimumSize(new java.awt.Dimension(16, 16));
        datasetMissingValuesHelpButton.setPreferredSize(new java.awt.Dimension(16, 16));
        jPanel13.add(datasetMissingValuesHelpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 116, -1, -1));

        stageOneOutcomeHelpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon4 - Copy.png"))); // NOI18N
        stageOneOutcomeHelpButton.setToolTipText("<html><pre>(To be implemented in MixWILD 2.0) Currently, you are restricted to continuous Stage 1 outcomes.\nDichotomous and ordinal outcomes will run ordered logistic regressions at Stage 1.\nNote that random scale is not available for dichotomous outcomes.<pre>");
        stageOneOutcomeHelpButton.setMaximumSize(new java.awt.Dimension(16, 16));
        stageOneOutcomeHelpButton.setMinimumSize(new java.awt.Dimension(16, 16));
        stageOneOutcomeHelpButton.setPreferredSize(new java.awt.Dimension(16, 16));
        jPanel13.add(stageOneOutcomeHelpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 198, -1, -1));

        stageOneRLEHelpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon4 - Copy.png"))); // NOI18N
        stageOneRLEHelpButton.setToolTipText("<html><pre>\nSelect “Intercept only”, and the model assumes the mean of the response does not differ between\nsubjects as a result of some covariate and engages the MixRegLS model, allowing users to\nspecify covariates for WS and BS variances. \n\nSelect “Intercept and slope(s)”, this will be MixRegMLS model. Adding slopes can have more than 2 random location effects, but estimation time is increased with each additional random effect.<pre>");
        stageOneRLEHelpButton.setMaximumSize(new java.awt.Dimension(16, 16));
        stageOneRLEHelpButton.setMinimumSize(new java.awt.Dimension(16, 16));
        stageOneRLEHelpButton.setPreferredSize(new java.awt.Dimension(16, 16));
        jPanel13.add(stageOneRLEHelpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 232, -1, -1));

        stageOneRSHelpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon4 - Copy.png"))); // NOI18N
        stageOneRSHelpButton.setToolTipText("<html><pre>Random scale parameters allow subjects to have individual estimates of the \nwithin-subject variance, and this is the distinguishing feature of a mixed-eefects locatio nscale model.\nFor random scale models, a linear or quadratic association is also possible.<pre>");
        stageOneRSHelpButton.setMaximumSize(new java.awt.Dimension(16, 16));
        stageOneRSHelpButton.setMinimumSize(new java.awt.Dimension(16, 16));
        stageOneRSHelpButton.setPreferredSize(new java.awt.Dimension(16, 16));
        jPanel13.add(stageOneRSHelpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 266, -1, -1));

        stageTwoDescription.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon4 - Copy.png"))); // NOI18N
        stageTwoDescription.setToolTipText("<html><pre>(To be implemented in MixWILD 2.0) Currently, you are restricted to single level models and\ncontinuous and dichotomous/ordinal Stage 2 outcomes. Multilevel models allow for additional estimation at\nStage 2 using random intercept mixed effects model in place of the standard single level model, similar to\nStage 1. Continuous outcomes will run a linear regression at Stage 2. Dichotomous and ordinal outcomes \nwill run an ordered logistic regression at Stage 2.<pre>");
        stageTwoDescription.setMaximumSize(new java.awt.Dimension(16, 16));
        stageTwoDescription.setMinimumSize(new java.awt.Dimension(16, 16));
        stageTwoDescription.setPreferredSize(new java.awt.Dimension(16, 16));
        jPanel13.add(stageTwoDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 317, -1, -1));

        stageTwoModelTypeHelpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon4 - Copy.png"))); // NOI18N
        stageTwoModelTypeHelpButton.setToolTipText("<html><pre>Multilevel models allow for additional estimation at stage 2 \nusing random intercept mixed effect model.<pre>");
        stageTwoModelTypeHelpButton.setMaximumSize(new java.awt.Dimension(16, 16));
        stageTwoModelTypeHelpButton.setMinimumSize(new java.awt.Dimension(16, 16));
        stageTwoModelTypeHelpButton.setPreferredSize(new java.awt.Dimension(16, 16));
        jPanel13.add(stageTwoModelTypeHelpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 352, -1, -1));

        stageTwoOutcomeTypeHelpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon4 - Copy.png"))); // NOI18N
        stageTwoOutcomeTypeHelpButton.setToolTipText("<html><pre>This stage 2 outcome can be a subject-level or 2-level outcome, and can be of four different outcome types:\ncontinuous (normal), dichotomous/ordinal, count, or nominal.<pre>");
        stageTwoOutcomeTypeHelpButton.setMaximumSize(new java.awt.Dimension(16, 16));
        stageTwoOutcomeTypeHelpButton.setMinimumSize(new java.awt.Dimension(16, 16));
        stageTwoOutcomeTypeHelpButton.setPreferredSize(new java.awt.Dimension(16, 16));
        jPanel13.add(stageTwoOutcomeTypeHelpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 387, -1, -1));

        stageOneTabs.addTab("Model Configuration", jPanel13);

        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 700));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 700));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        resetButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        resetButton.setText("Clear Stage 1");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        jPanel1.add(resetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 530, 140, 35));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setText("Stage 1 Regressors");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 18, -1, -1));

        level1_MeanReg.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        level1_MeanReg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        level1_MeanReg.setText("Mean");
        level1_MeanReg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(level1_MeanReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, 40, -1));
        level1_MeanReg.getAccessibleContext().setAccessibleName("");

        level1_WSVar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        level1_WSVar.setText("WS Variance");
        jPanel1.add(level1_WSVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 70, -1, -1));

        level2_MeanReg.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        level2_MeanReg.setText("Mean");
        jPanel1.add(level2_MeanReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 280, 40, -1));

        level2_BSVar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        level2_BSVar.setText("BS Variance");
        jPanel1.add(level2_BSVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 280, 90, -1));

        level2_WSVar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        level2_WSVar.setText("WS Variance");
        jPanel1.add(level2_WSVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 280, -1, -1));

        level1_BSVar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        level1_BSVar.setText("BS Variance");
        level1_BSVar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(level1_BSVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, 90, -1));

        stageOneLevelOnePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Level-1"));

        levelOneGrid.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout stageOneLevelOnePanelLayout = new javax.swing.GroupLayout(stageOneLevelOnePanel);
        stageOneLevelOnePanel.setLayout(stageOneLevelOnePanelLayout);
        stageOneLevelOnePanelLayout.setHorizontalGroup(
            stageOneLevelOnePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(levelOneGrid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        stageOneLevelOnePanelLayout.setVerticalGroup(
            stageOneLevelOnePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(levelOneGrid, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
        );

        levelOneGrid.getAccessibleContext().setAccessibleName("Level-1");

        jPanel1.add(stageOneLevelOnePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 540, 170));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 540, 10));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 50, -1, 140));

        stageOneLevelTwoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Level-2"));

        levelTwoGrid.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout stageOneLevelTwoPanelLayout = new javax.swing.GroupLayout(stageOneLevelTwoPanel);
        stageOneLevelTwoPanel.setLayout(stageOneLevelTwoPanelLayout);
        stageOneLevelTwoPanelLayout.setHorizontalGroup(
            stageOneLevelTwoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(levelTwoGrid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        stageOneLevelTwoPanelLayout.setVerticalGroup(
            stageOneLevelTwoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(levelTwoGrid, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
        );

        jPanel1.add(stageOneLevelTwoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 540, 180));

        StageOneVariableCombo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        StageOneVariableCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        StageOneVariableCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                StageOneVariableComboItemStateChanged(evt);
            }
        });
        StageOneVariableCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StageOneVariableComboActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Stage 1 Outcome:");

        IDvariableCombo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        IDvariableCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        IDvariableCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IDvariableComboItemStateChanged(evt);
            }
        });
        IDvariableCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDvariableComboActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("ID Variable:");

        addStageOneButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        addStageOneButton.setText("Configure Stage 1 Regressors ...");
        addStageOneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStageOneButtonActionPerformed(evt);
            }
        });

        advancedOptionsButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        advancedOptionsButton.setText("Options ...");
        advancedOptionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                advancedOptionsButtonActionPerformed(evt);
            }
        });

        associationLabel.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        associationLabel.setText("<html>Specify the relationship between the <br>mean and WS variance.<br></html>");

        buttonGroup1.add(NoAssociationRadio);
        NoAssociationRadio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        NoAssociationRadio.setText("No Association");
        NoAssociationRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoAssociationRadioActionPerformed(evt);
            }
        });

        buttonGroup1.add(LinearAssociationRadio);
        LinearAssociationRadio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        LinearAssociationRadio.setText("Linear Association");
        LinearAssociationRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LinearAssociationRadioActionPerformed(evt);
            }
        });

        buttonGroup1.add(QuadraticAssociationRadio);
        QuadraticAssociationRadio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        QuadraticAssociationRadio.setText("Quadratic Association");
        QuadraticAssociationRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuadraticAssociationRadioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout associationPanelLayout = new javax.swing.GroupLayout(associationPanel);
        associationPanel.setLayout(associationPanelLayout);
        associationPanelLayout.setHorizontalGroup(
            associationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(associationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(associationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(associationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addGroup(associationPanelLayout.createSequentialGroup()
                        .addGroup(associationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NoAssociationRadio)
                            .addComponent(LinearAssociationRadio)
                            .addComponent(QuadraticAssociationRadio))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        associationPanelLayout.setVerticalGroup(
            associationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(associationPanelLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(associationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(NoAssociationRadio)
                .addGap(12, 12, 12)
                .addComponent(LinearAssociationRadio)
                .addGap(12, 12, 12)
                .addComponent(QuadraticAssociationRadio))
        );

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel7.add(randomLocationEffectsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 34, -1, -1));
        jPanel7.add(stageTwoOutcomePrintLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 55, -1, -1));

        jLabel21.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel21.setText("Selected Model Configuration");
        jPanel7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 212, -1));
        jPanel7.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 28, 165, 6));

        randomLocationEffectsLabel1.setText("Stage 1 model:");
        jPanel7.add(randomLocationEffectsLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 34, -1, -1));

        stageTwoOutcomePrintLabel1.setText("State 1 outcome:");
        jPanel7.add(stageTwoOutcomePrintLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 55, -1, -1));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(associationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(IDvariableCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(StageOneVariableCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addStageOneButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator10)
                                .addComponent(jLabel2)
                                .addComponent(jSeparator9)
                                .addComponent(jLabel1)
                                .addComponent(jSeparator7)
                                .addComponent(advancedOptionsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator11)))))
                .addGap(10, 10, 10))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(IDvariableCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(StageOneVariableCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addStageOneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(advancedOptionsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(associationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 310, 560));
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, -1, -1));

        startStageTwo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        startStageTwo.setText("Configure Stage 2");
        startStageTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startStageTwoActionPerformed(evt);
            }
        });
        jPanel1.add(startStageTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 530, 160, 35));

        guiStatesSaveButtonStageOne.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        guiStatesSaveButtonStageOne.setText("Save Model");
        guiStatesSaveButtonStageOne.setMaximumSize(new java.awt.Dimension(99, 25));
        guiStatesSaveButtonStageOne.setMinimumSize(new java.awt.Dimension(99, 25));
        guiStatesSaveButtonStageOne.setPreferredSize(new java.awt.Dimension(99, 25));
        guiStatesSaveButtonStageOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guiStatesSaveButtonStageOneActionPerformed(evt);
            }
        });
        jPanel1.add(guiStatesSaveButtonStageOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 530, 140, 35));

        stageOneTabs.addTab("Stage 1 Configuration", jPanel1);

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel14.setText(" Main Effects");
        jPanel12.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 110, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setText("Stage 2 Interactions");
        jPanel12.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 18, 190, 20));

        jLabel15.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel15.setText("Random Location");
        jPanel12.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 70, 140, -1));

        addStageTwoTabTwo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        addStageTwoTabTwo.setText("Configure Stage 2 Regressors ...");
        addStageTwoTabTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStageTwoTabTwoActionPerformed(evt);
            }
        });
        jPanel12.add(addStageTwoTabTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 235, 260, 40));

        jLabel17.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel17.setText("Random Scale");
        jPanel12.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(799, 70, 110, 20));

        jLabel18.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel18.setText("Location X Scale");
        jPanel12.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 70, 130, 20));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator6.setOpaque(true);
        jPanel12.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 320, 1, 140));

        stageTwoLevelTwoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Level-2"));

        stageTwoRegsGridLvl2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        stageTwoRegsGridLvl2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout stageTwoLevelTwoPanelLayout = new javax.swing.GroupLayout(stageTwoLevelTwoPanel);
        stageTwoLevelTwoPanel.setLayout(stageTwoLevelTwoPanelLayout);
        stageTwoLevelTwoPanelLayout.setHorizontalGroup(
            stageTwoLevelTwoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stageTwoRegsGridLvl2, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
        );
        stageTwoLevelTwoPanelLayout.setVerticalGroup(
            stageTwoLevelTwoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stageTwoRegsGridLvl2, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
        );

        stageTwoRegsGridLvl2.getAccessibleContext().setAccessibleParent(stageTwoRegsGridLvl2);

        jPanel12.add(stageTwoLevelTwoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 300, 610, 170));
        stageTwoLevelTwoPanel.getAccessibleContext().setAccessibleName("Level-2 Stage-2");
        stageTwoLevelTwoPanel.getAccessibleContext().setAccessibleDescription("");

        runTabTwoStageOneTwo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        runTabTwoStageOneTwo.setText("Run Stage 1 and 2");
        runTabTwoStageOneTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runTabTwoStageOneTwoActionPerformed(evt);
            }
        });
        jPanel12.add(runTabTwoStageOneTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 530, 160, 35));

        suppressIntCheckBox.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        suppressIntCheckBox.setText("Suppress Scale X Random Interaction");
        suppressIntCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suppressIntCheckBoxActionPerformed(evt);
            }
        });
        jPanel12.add(suppressIntCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 480, -1, -1));

        stageTwoOutcome.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        stageTwoOutcome.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stageTwoOutcomeItemStateChanged(evt);
            }
        });
        stageTwoOutcome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stageTwoOutcomeActionPerformed(evt);
            }
        });
        jPanel12.add(stageTwoOutcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 195, 260, 30));

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel22.setText("Stage 2 Outcome:");
        jPanel12.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 210, -1));

        jButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton1.setText("Clear Stage 2");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 530, 140, 35));
        jPanel12.add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 570, -1, 80));
        jPanel12.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 610, 10));
        jPanel12.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 660, -1, -1));
        jPanel12.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 262, 220, 0));

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        outcomeCatButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        outcomeCatButton.setText("Check outcome categories");
        outcomeCatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outcomeCatButtonActionPerformed(evt);
            }
        });

        jScrollPane5.setViewportView(outCategoryDisplay);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(outcomeCatButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(outcomeCatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel12.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 285, 260, 250));

        guiStatesSaveButtonStageTwo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        guiStatesSaveButtonStageTwo.setText("Save Model");
        guiStatesSaveButtonStageTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guiStatesSaveButtonStageTwoActionPerformed(evt);
            }
        });
        jPanel12.add(guiStatesSaveButtonStageTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 530, 130, 35));

        jSeparator20.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator20.setOpaque(true);
        jPanel12.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, 1, 140));

        stageTwoLevelOnePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Level-1"));

        stageTwoRegsGridLvl1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        stageTwoRegsGridLvl1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout stageTwoLevelOnePanelLayout = new javax.swing.GroupLayout(stageTwoLevelOnePanel);
        stageTwoLevelOnePanel.setLayout(stageTwoLevelOnePanelLayout);
        stageTwoLevelOnePanelLayout.setHorizontalGroup(
            stageTwoLevelOnePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stageTwoRegsGridLvl1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
        );
        stageTwoLevelOnePanelLayout.setVerticalGroup(
            stageTwoLevelOnePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stageTwoRegsGridLvl1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
        );

        jPanel12.add(stageTwoLevelOnePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 610, 170));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setText("Selected Model Configuration");
        jPanel12.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 25, -1, -1));
        jLabel3.getAccessibleContext().setAccessibleName("");

        jPanel12.add(stageOneOutcomeStageTwoConfigLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 70, -1, -1));
        jPanel12.add(stageOneModelStageTwoConfigLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));
        jPanel12.add(stageTwoOutcomeStageTwoConfigLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 110, -1, -1));
        jPanel12.add(stageTwoModelTypeStageTwoConfigLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 90, -1, -1));
        jPanel12.add(numResamplingStageTwoConfigLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 130, -1, -1));
        jPanel12.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 260, 10));
        jPanel12.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 41, 165, 10));

        jLabel33.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel33.setText(" Main Effects");
        jPanel12.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 280, 110, -1));

        jLabel35.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel35.setText("Random Location");
        jPanel12.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 280, 140, -1));

        jLabel36.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel36.setText("Random Scale");
        jPanel12.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(799, 280, 110, 20));

        jLabel37.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel37.setText("Location X Scale");
        jPanel12.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 280, 130, 20));

        stageOneModelStageTwoConfigLabel1.setText("Stage 1 model:");
        jPanel12.add(stageOneModelStageTwoConfigLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        stageOneOutcomeStageTwoConfigLabel1.setText("Stage 1 outcome:");
        jPanel12.add(stageOneOutcomeStageTwoConfigLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        stageTwoModelTypeStageTwoConfigLabel1.setText("Stage 2 model type:");
        jPanel12.add(stageTwoModelTypeStageTwoConfigLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));

        stageTwoOutcomeStageTwoConfigLabel1.setText("Stage 2 outcome:");
        jPanel12.add(stageTwoOutcomeStageTwoConfigLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, -1, -1));

        numResamplingStageTwoConfigLabel1.setText("Number of resamples (stage 2):");
        jPanel12.add(numResamplingStageTwoConfigLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        stageOneTabs.addTab("Stage 2 Configuration", jPanel12);

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        stageOneOutput.setColumns(20);
        stageOneOutput.setFont(new java.awt.Font("Courier New", 0, 11)); // NOI18N
        stageOneOutput.setRows(5);
        jScrollPane2.setViewportView(stageOneOutput);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton8.setText("Save Results As ...");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Results from stage 1 analysis");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(544, 544, 544)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(334, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        stageOneTabs.addTab("Stage 1 Results", jPanel3);

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setPreferredSize(new java.awt.Dimension(833, 366));

        stageTwoOutput.setColumns(20);
        stageTwoOutput.setFont(new java.awt.Font("Courier New", 0, 11)); // NOI18N
        stageTwoOutput.setRows(5);
        jScrollPane1.setViewportView(stageTwoOutput);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                .addContainerGap())
        );

        saveStage2OutButton.setText("Save Results As ...");
        saveStage2OutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveStage2OutButtonActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("Results from stage 2 analysis");
        jLabel11.setToolTipText("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(saveStage2OutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)))
                .addContainerGap(335, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveStage2OutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        stageOneTabs.addTab("Stage 2 Results", jPanel4);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("Your resulting model equation");

        equationArea.setColumns(20);
        equationArea.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        equationArea.setRows(5);
        jScrollPane8.setViewportView(equationArea);

        jLabel23.setText("You can copy this model equation directly into Latex, Word or any other text editor.");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(162, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(367, 367, 367)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(486, 486, 486))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel23)
                .addContainerGap(461, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1210, 660));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/mixLogo.png"))); // NOI18N
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 550, -1, 40));

        stageOneTabs.addTab("View Model", jPanel2);

        dataTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(dataTable);

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setText("Imported data file:");

        printedFileName.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        printedFileName.setText("filename");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(31, 31, 31)
                        .addComponent(printedFileName)))
                .addContainerGap(188, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(printedFileName))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        stageOneTabs.addTab("View Data", jPanel6);

        jLabel19.setText("Stage Two Outcome:");

        jLabel24.setText("Selected Outcome");

        jLabel25.setText("Stage One Outcome:");

        jLabel26.setText("Selected Outcome");

        jLabel27.setText("Selected Model Type at S1 with Model at S2");

        jLabel28.setText("Number of Samples:");

        jTextField1.setText("Selected Samples");

        jLabel30.setText("Cutoff:");

        jTextField2.setText("0.00000");

        jButton3.setText("?");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)
                        .addGap(30, 30, 30)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel27)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(844, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel24))
                .addGap(59, 59, 59)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(439, Short.MAX_VALUE))
        );

        stageOneTabs.addTab("Postestimation", jPanel14);

        userGuideDownload.setText("Download MixWild User Guide");
        userGuideDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userGuideDownloadActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel31.setText("Supplement Resources");

        exampleDataDownload.setText("Download MixWILD Example Dataset");
        exampleDataDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exampleDataDownloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(userGuideDownload)
                    .addComponent(exampleDataDownload))
                .addGap(868, 868, 868))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(userGuideDownload)
                .addGap(18, 18, 18)
                .addComponent(exampleDataDownload)
                .addGap(1496, 1496, 1496))
        );

        stageOneTabs.addTab("Help", jPanel15);

        getContentPane().add(stageOneTabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveStage2OutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveStage2OutButtonActionPerformed
        try {
            // TODO add your handling code here:

            saveStageTwoOutput();
        } catch (IOException ex) {
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
            SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
        }
    }//GEN-LAST:event_saveStage2OutButtonActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        saveStageOneOutput();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void outcomeCatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outcomeCatButtonActionPerformed
        // TODO add your handling code here:
        //get the categories of the stage two outcome variable
        System.out.println("Inside click for categories");

        ArrayList<String> ColumnsCustom = new ArrayList<>();
        ArrayList<String> UniqueList = new ArrayList<>();

        String dataFileName = getDataFileName();
        File file = new File(dataFileName);
        //        //first get the column
        BufferedReader br = null;
        String line = "";
        String commaSplitter = ",";
        //

        try {
            br = new BufferedReader(new FileReader(dataFileName));
            line = br.readLine(); //consumes the first row
            while ((line = br.readLine()) != null) {
                String[] Columns = line.split(commaSplitter);

                int index = stageTwoOutcome.getSelectedIndex();
                ColumnsCustom.add(Columns[index]);

            }

            System.out.println("COLUMN:");
            for (int k = 0; k < ColumnsCustom.size(); k++) {

                System.out.println(ColumnsCustom.get(k));
            }

            //            if (defFile.getAdvancedMissingValue().contains(".")){
            //            String strippedMissingVal = defFile.getAdvancedMissingValue().substring(0,defFile.getAdvancedMissingValue().indexOf('.'));
            //            }
            //
            //count the unique ones
            for (int x = 0; x < ColumnsCustom.size(); x++) {
                if (UniqueList.contains(ColumnsCustom.get(x))) {
                    //do nothing
                } else if (ColumnsCustom.get(x).equals(defFile.getAdvancedMissingValueCode()) && !ColumnsCustom.get(x).equals("0")) { //compare if the category is a missing value, then don't consider it as a category
                    //do nothing

                } else {
                    UniqueList.add(ColumnsCustom.get(x));
                }

            }

            //sort UniqueList First
            ArrayList<Integer> UniqueIntegers = new ArrayList<>();

            for (int x = 0; x < UniqueList.size(); x++) {

                UniqueIntegers.add(Integer.valueOf(UniqueList.get(x)));

            }

            Collections.sort(UniqueIntegers);

            System.out.println("Number of unique categories: " + String.valueOf(UniqueList.size()));

            outCategoryDisplay.setText(UniqueList.size() + " Categories:\n");
            //            for (int index = 0; index < UniqueList.size(); index++) {
            //                //numberOfCategories.setT
            //                //numberOfCategories.setText(numberOfCategories.getText() +"<html><br></html>" + String.valueOf(index + 1) + ":" + UniqueList.get(index) + "<html><br></html>");
            //                outCategoryDisplay.setText(outCategoryDisplay.getText() + String.valueOf(index + 1) + ") " + UniqueList.get(index) + "\n");
            //
            //            }

            for (int index = 0; index < UniqueIntegers.size(); index++) {
                //numberOfCategories.setT
                //numberOfCategories.setText(numberOfCategories.getText() +"<html><br></html>" + String.valueOf(index + 1) + ":" + UniqueList.get(index) + "<html><br></html>");
                outCategoryDisplay.setText(outCategoryDisplay.getText() + String.valueOf(index + 1) + ") " + String.valueOf(UniqueIntegers.get(index)) + "\n");

            }

            String[] outcomeCats = new String[UniqueIntegers.size()];

            for (int pos = 0; pos < outcomeCats.length; pos++) {
                outcomeCats[pos] = String.valueOf(UniqueIntegers.get(pos));
                System.out.println("STAGE 2 TESTING OUTCOME CATEGORIES: " + outcomeCats[pos]);
            }

        } catch (FileNotFoundException e) {
            SystemLogger.LOGGER.log(Level.SEVERE, e.toString() + "{0}", SystemLogger.getLineNum());
            e.printStackTrace();
            Logger.getLogger(getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, e.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        } catch (IOException e) {
            SystemLogger.LOGGER.log(Level.SEVERE, e.toString() + "{0}", SystemLogger.getLineNum());
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    SystemLogger.LOGGER.log(Level.SEVERE, e.toString() + "{0}", SystemLogger.getLineNum());
                    e.printStackTrace();
                }
            }
        }

    }//GEN-LAST:event_outcomeCatButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SystemLogger.LOGGER.log(Level.INFO, "Clear Stage Two");

        stageTwoLevelTwoPanel.removeAll();
        stageTwoLevelTwoPanel.revalidate();
        stageTwoLevelTwoPanel.repaint();

        stage_2_regs.stageTwoLevelTwo.clear();
        updateStageTwoLevelTwoGrid(stage_2_regs.stageTwoLevelTwo);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void stageTwoOutcomeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stageTwoOutcomeItemStateChanged
        // TODO add your handling code here:
        stageTwoPos = stageTwoOutcome.getSelectedIndex();
        System.out.println("STAGE TWO OUTCOME CHANGED: " + String.valueOf(stageTwoPos));
        isStageTwoOutcomeChanged = true;
    }//GEN-LAST:event_stageTwoOutcomeItemStateChanged

    private void suppressIntCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suppressIntCheckBoxActionPerformed
        update_trigger_suppressIntCheckBox();
    }//GEN-LAST:event_suppressIntCheckBoxActionPerformed

    private void runTabTwoStageOneTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runTabTwoStageOneTwoActionPerformed
        SystemLogger.LOGGER.log(Level.INFO, "Run Stage One and Two");
        isStageTwoSubmitted = true;
        update_trigger_runTabTwoStageOneTwo();

    }//GEN-LAST:event_runTabTwoStageOneTwoActionPerformed

    private void addStageTwoTabTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStageTwoTabTwoActionPerformed
        // TODO add your handling code here:

        if (stage_2_regs.isVisible()) { //if it is already open and visible in the background

            // stage_1_regs.set
            stage_2_regs.setFocusable(true);

        } else {
            //stage_1_regs.revalidate();
            //stage_1_regs.repaint();
            // stage_1_regs.removeAll();

            if (addStageTwoChecked == true) {

                stage_2_regs.setVisible(true);
                stage_2_regs.updateStageTwoAgain();
                //            stage_2_regs.updateStageTwoWithoutStageOne();
            } else {
                stage_2_regs.setVisible(true);
                stage_2_regs.updateStageTwoWithoutStageOne();
            }
        }

        addStageTwoChecked = true;

        //        stage_2_regs = new stageTwoRegs();
        //stageOneTabs.setSelectedIndex(1);
        //        stage_2_regs.setVisible(true);
        //        //stage_2_regs.updateStageTwoVariables(getSavedVariables());
        //        stage_2_regs.updateStageTwoWithoutStageOne();
    }//GEN-LAST:event_addStageTwoTabTwoActionPerformed

    private void startStageTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startStageTwoActionPerformed
        SystemLogger.LOGGER.log(Level.INFO, "Submit stage one configurations");
        isStageOneSubmitted = true;
        update_trigger_StartStageTwo();
    }//GEN-LAST:event_startStageTwoActionPerformed

    private void LinearAssociationRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LinearAssociationRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LinearAssociationRadioActionPerformed

    private void NoAssociationRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoAssociationRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoAssociationRadioActionPerformed

    private void advancedOptionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_advancedOptionsButtonActionPerformed
        // TODO add your handling code here:
        //advancedOptions_view = new advancedOptions();

        advancedOptions_view.setVisible(true);
    }//GEN-LAST:event_advancedOptionsButtonActionPerformed

    private void addStageOneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStageOneButtonActionPerformed
        stageOneClicked = 1;

        IDpos = IDvariableCombo.getSelectedIndex();
        stageOnePos = StageOneVariableCombo.getSelectedIndex();
        stageTwoPos = stageTwoOutcome.getSelectedIndex();

        if (stage_1_regs.isVisible()) { //if it is already open and visible in the background

            // stage_1_regs.set
            stage_1_regs.setFocusable(true);
            stageOneClicked = 1;
            addStageTwoTabTwo.setEnabled(true);

        } else {
            //stage_1_regs.revalidate();
            //stage_1_regs.repaint();
            // stage_1_regs.removeAll();

            stageOneClicked = 1;
            addStageTwoTabTwo.setEnabled(true);

            //            if (levelOneRegSize == 0 && levelTwoRegSize ==0){
            //
            //            //refresh as normal
            //            } else {
            //
            //
            //            }
            if (addStageOneCHecked == true) {

                stage_1_regs.setVisible(true);
                stage_1_regs.updateStageOneAgain();
//                stage_1_regs.updateAllVariables();
            } else {
                stage_1_regs.setVisible(true);
                stage_1_regs.updateAllVariables();
            }
        }

        addStageOneCHecked = true;

    }//GEN-LAST:event_addStageOneButtonActionPerformed

    private void IDvariableComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDvariableComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDvariableComboActionPerformed

    private void IDvariableComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_IDvariableComboItemStateChanged
        // TODO add your handling code here:

        IDpos = IDvariableCombo.getSelectedIndex();
        System.out.println("ID CHANGED: " + String.valueOf(IDpos));
        isIDChanged = true;
    }//GEN-LAST:event_IDvariableComboItemStateChanged

    private void StageOneVariableComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StageOneVariableComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StageOneVariableComboActionPerformed

    private void StageOneVariableComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_StageOneVariableComboItemStateChanged
        // TODO add your handling code here:
        stageOnePos = StageOneVariableCombo.getSelectedIndex();
        System.out.println("STAGE ONE OUTCOME CHANGED: " + String.valueOf(stageOnePos));
        isStageOneOutcomeChanged = true;
    }//GEN-LAST:event_StageOneVariableComboItemStateChanged

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        SystemLogger.LOGGER.log(Level.INFO, "Clear Stage One");

        IDvariableCombo.setSelectedIndex(0);
        StageOneVariableCombo.setSelectedIndex(1);
        stageTwoOutcome.setSelectedIndex(2);

        buttonGroup1.clearSelection();

        //addStageTwoTabTwo.setEnabled(false);
        stageOneLevelTwoPanel.removeAll();
        stageOneLevelTwoPanel.revalidate();
        stageOneLevelTwoPanel.repaint();

        stageOneLevelOnePanel.removeAll();
        stageOneLevelOnePanel.revalidate();
        stageOneLevelOnePanel.repaint();

        stage_1_regs.updateAllVariables();

        stage_1_regs.levelOneList.clear();
        stage_1_regs.levelTwoList.clear();
        updateStageOneLevelTwoGrid(stage_1_regs.levelTwoList);
        updateStageOneLevelOneGrid(stage_1_regs.levelOneList);

    }//GEN-LAST:event_resetButtonActionPerformed

    private void userGuideDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userGuideDownloadActionPerformed

        // user open filechooser and select save path
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("MixWild_User_Guide.pdf"));
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File dest = fileChooser.getSelectedFile();

            // copy file from resources to user selected save path
            InputStream stream = null;
            try {
                URL inputUrl = getClass().getResource("/resources/UserGuide.pdf");
                FileUtils.copyURLToFile(inputUrl, dest);
            } catch (IOException e) {
                SystemLogger.LOGGER.log(Level.SEVERE, e.toString() + "{0}", SystemLogger.getLineNum());
                e.printStackTrace();
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (Exception e) {
                        SystemLogger.LOGGER.log(Level.SEVERE, e.toString() + "{0}", SystemLogger.getLineNum());
                        e.printStackTrace();
                    }
                }
            }
        }
    }//GEN-LAST:event_userGuideDownloadActionPerformed

    private void exampleDataDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exampleDataDownloadActionPerformed

        // user open filechooser and select save path
        JFileChooser filechooser_sample = new JFileChooser();
        filechooser_sample.setSelectedFile(new File("Mixwild_example_data.csv"));
        int option = filechooser_sample.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File dest = filechooser_sample.getSelectedFile();

            // copy file from resources to user selected save path
            InputStream stream = null;
            try {
                URL inputUrl = getClass().getResource("/resources/ExampleData.csv");
                FileUtils.copyURLToFile(inputUrl, dest);
            } catch (IOException e) {
                SystemLogger.LOGGER.log(Level.SEVERE, e.toString() + "{0}", SystemLogger.getLineNum());
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        SystemLogger.LOGGER.log(Level.SEVERE, e.toString() + "{0}", SystemLogger.getLineNum());
                    }
                }
            }
        }
    }//GEN-LAST:event_exampleDataDownloadActionPerformed

    private void guiStatesSaveButtonStageOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guiStatesSaveButtonStageOneActionPerformed
        SystemLogger.LOGGER.log(Level.INFO, "Saving GUI states");
        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        MXRStates.writeAllStates(this);
    }//GEN-LAST:event_guiStatesSaveButtonStageOneActionPerformed

    private void guiStatesSaveButtonStageTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guiStatesSaveButtonStageTwoActionPerformed
        SystemLogger.LOGGER.log(Level.INFO, "Saving GUI states");
        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        MXRStates.writeAllStates(this);
    }//GEN-LAST:event_guiStatesSaveButtonStageTwoActionPerformed

    private void QuadraticAssociationRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuadraticAssociationRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QuadraticAssociationRadioActionPerformed

    private void stageTwoOutcomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stageTwoOutcomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stageTwoOutcomeActionPerformed

    private void newDataSetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newDataSetButtonActionPerformed

        importDataSet();

        if (validDataset) {
            MXRStates = new MixRegGuiStates(this, advancedOptions_view);
            updateGuiView(MXRStates);

            newDataSetButton.setVisible(false);
            loadModelByBrowseButton.setVisible(false);

            jSeparator8.setVisible(true);
            jLabel34.setVisible(true);
            loadModelByBrowseButton.setEnabled(false);
            loadModelByBrowseButton.setVisible(false);

            showHiddenBigIconLabel(false);
            dataFileLabel.setVisible(true);
            filePath.setVisible(true);
            fileBrowseButton.setVisible(true);
            datasetHelpButton.setVisible(true);
        }
    }//GEN-LAST:event_newDataSetButtonActionPerformed

    private void updateStage2ConfigButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStage2ConfigButtonActionPerformed
        isUpdateStage2ConfigClicked = true;

        includeStageTwoYes.setEnabled(true);
        includeStageTwoNo.setEnabled(true);

        isNewModalConfigSubmitted = false;
        newModelSubmit.setVisible(true);
        newModelSubmit.setEnabled(true);
        updateStage2ConfigButton.setVisible(false);

        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        updateGuiView(MXRStates);
    }//GEN-LAST:event_updateStage2ConfigButtonActionPerformed

    private void loadModelByBrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadModelByBrowseButtonActionPerformed
        // choose saved progress file
        MXRStates = new MixRegGuiStates();
        MXRStates.readAllStates(this);
        // hide and show fields
        newDataSetButton.setVisible(false);
        loadModelByBrowseButton.setVisible(false);
        dataFileLabel.setVisible(true);
        filePath.setVisible(true);
        fileBrowseButton.setVisible(true);
        // update view with saved states
        updateGuiView(MXRStates);
        loadModelByBrowseButton.setEnabled(false);
        loadModelByBrowseButton.setVisible(false);

        stageOneTabs.setSelectedIndex(0);
    }//GEN-LAST:event_loadModelByBrowseButtonActionPerformed

    private void guiStatesSaveButtonModalConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guiStatesSaveButtonModalConfigActionPerformed
        if (sessionFolderName != null) {
            SystemLogger.LOGGER.log(Level.INFO, "Saving GUI states");
        }
        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        MXRStates.writeAllStates(this);
    }//GEN-LAST:event_guiStatesSaveButtonModalConfigActionPerformed

    private void guiStatesLoadButtonModalConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guiStatesLoadButtonModalConfigActionPerformed
        if (sessionFolderName != null) {
            SystemLogger.LOGGER.log(Level.INFO, "Loading GUI states");
        }

        MXRStates = new MixRegGuiStates();
        MXRStates.readAllStates(this);
        updateGuiView(MXRStates);
    }//GEN-LAST:event_guiStatesLoadButtonModalConfigActionPerformed

    private void hiddenBigIconLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hiddenBigIconLabelMouseClicked
        // TODO add your handling code here:
        revealHiddenTabs++;
        if (revealHiddenTabs > 4) {
            stageOneTabs.setEnabledAt(7, true);
            stageOneTabs.setEnabledAt(8, true);
            superUserMenuLaunch = new SuperUserMenu();
            superUserMenuLaunch.setLocationRelativeTo(mxr);
            superUserMenuLaunch.setVisible(true);
        }
    }//GEN-LAST:event_hiddenBigIconLabelMouseClicked

    private void countRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countRadioActionPerformed
        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        updateGuiView(MXRStates);
    }//GEN-LAST:event_countRadioActionPerformed

    private void multinomialRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multinomialRadioActionPerformed
        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        updateGuiView(MXRStates);
    }//GEN-LAST:event_multinomialRadioActionPerformed

    private void stageTwoMultiLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stageTwoMultiLevelActionPerformed
        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        updateGuiView(MXRStates);
    }//GEN-LAST:event_stageTwoMultiLevelActionPerformed

    private void stageTwoSingleLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stageTwoSingleLevelActionPerformed
        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        updateGuiView(MXRStates);
    }//GEN-LAST:event_stageTwoSingleLevelActionPerformed

    private void includeStageTwoNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_includeStageTwoNoActionPerformed
        // TODO add your handling code here:
        this.includeStageTwoNo.setSelected(true);
        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        updateGuiView(MXRStates);
    }//GEN-LAST:event_includeStageTwoNoActionPerformed

    private void includeStageTwoYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_includeStageTwoYesActionPerformed
        // TODO add your handling code here:
        this.includeStageTwoYes.setSelected(true);
        String seedVal = generateSeed();
        seedTextBox.setText(seedVal);

        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        updateGuiView(MXRStates);
    }//GEN-LAST:event_includeStageTwoYesActionPerformed

    private void randomScaleSelectionNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomScaleSelectionNoActionPerformed
        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        updateGuiView(MXRStates);
    }//GEN-LAST:event_randomScaleSelectionNoActionPerformed

    private void randomScaleSelectionYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomScaleSelectionYesActionPerformed
        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        updateGuiView(MXRStates);
    }//GEN-LAST:event_randomScaleSelectionYesActionPerformed

    private void stageOneOrdinalRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stageOneOrdinalRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stageOneOrdinalRadioActionPerformed

    private void stageOneDichotomousRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stageOneDichotomousRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stageOneDichotomousRadioActionPerformed

    private void stageOneContinuousRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stageOneContinuousRadioActionPerformed
        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        updateGuiView(MXRStates);
    }//GEN-LAST:event_stageOneContinuousRadioActionPerformed

    private void newModelSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newModelSubmitActionPerformed

        isNewModalConfigSubmitted = true;
        // System.getProperty("os.name");
        //        String osName = System.getProperty("os.name");
        //        System.out.println("YOUR OPERATING SYSTEM IS: " + osName);
        //        if (osName.contains("Windows")){
        //            System.out.println("YES THE OS IS WINDOWS");
        //        } else {
        //            System.out.println("NO THE OS IS NOT WINDOWS");
        //        }

        updateGuiView_trigger_NewModelSubmit();
        SystemLogger.LOGGER.log(Level.INFO, "Submit new model");
    }//GEN-LAST:event_newModelSubmitActionPerformed

    private void newModel_resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newModel_resetButtonActionPerformed
        if (sessionFolderName != null) {
            SystemLogger.LOGGER.log(Level.INFO, "Reset MixWild");
        }
        confirmReset resetBox = new confirmReset(mxr, false);
        resetBox.setLocationRelativeTo(mxr);
        resetBox.setVisible(true);

        /*
        filePath.setText("");
        titleField.setText("");
        titleField.setEnabled(false);
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();
        buttonGroup4.clearSelection();
        randomScaleCheckBox.setSelected(false);
        newModelMissingValueCode.setText("");
        seedTextBox.setText("");

        variableArray = null;
        isRandomScale = false;
        dataFileNameRef = null;
        missingValue = "0";
        defFile = null;
        modelBuilder = null;

        outPutStageTwo = null;

        resetButtonActionPerformed(evt);
        jButton1ActionPerformed(evt);
         */
        //updateMixRegGUI();
    }//GEN-LAST:event_newModel_resetButtonActionPerformed

    private void seedTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seedTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seedTextBoxActionPerformed

    private void newModelMissingValueCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newModelMissingValueCodeKeyTyped
        // TODO add your handling code here:

        char vchar = evt.getKeyChar();
        //        if (!((Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACKSPACE) || (vchar == KeyEvent.VK_DELETE) || (vchar == KeyEvent.VK_MINUS))) {
        //            evt.consume();
        //        }
        if (!((Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE) || (vchar == KeyEvent.VK_MINUS) || (vchar == '.'))) {
            evt.consume();
        }
    }//GEN-LAST:event_newModelMissingValueCodeKeyTyped

    private void newModelMissingValueCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newModelMissingValueCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newModelMissingValueCodeActionPerformed

    private void missingValuePresentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_missingValuePresentActionPerformed
        // TODO add your handling code here:

        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        updateGuiView(MXRStates);
    }//GEN-LAST:event_missingValuePresentActionPerformed

    private void missingValueAbsentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_missingValueAbsentActionPerformed
        // TODO add your handling code here:
        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        updateGuiView(MXRStates);
    }//GEN-LAST:event_missingValueAbsentActionPerformed

    private void stageTwoDichotomousRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stageTwoDichotomousRadioActionPerformed
        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        updateGuiView(MXRStates);
        /*if (includeStageTwoNo.isSelected()){
            //Do nothing
        } else {
            setSeedLabel.setVisible(true);
            seedTextBox.setVisible(true);
            seedHelpButton.setVisible(true);
            String seedVal = generateSeed();
            seedTextBox.setText(seedVal);
        }*/
    }//GEN-LAST:event_stageTwoDichotomousRadioActionPerformed

    private void stageTwoContinuousRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stageTwoContinuousRadioActionPerformed
        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        updateGuiView(MXRStates);
        /*if (includeStageTwoNo.isSelected()){
            //Do nothing

        } else {
            setSeedLabel.setVisible(true);
            seedTextBox.setVisible(true);
            seedHelpButton.setVisible(true);
            String seedVal = generateSeed();
            seedTextBox.setText(seedVal);
        }*/
    }//GEN-LAST:event_stageTwoContinuousRadioActionPerformed

    private void moreThanOneRLERadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moreThanOneRLERadioActionPerformed
        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        updateGuiView(MXRStates);
    }//GEN-LAST:event_moreThanOneRLERadioActionPerformed

    private void oneRLERadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneRLERadioActionPerformed
        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        updateGuiView(MXRStates);
    }//GEN-LAST:event_oneRLERadioActionPerformed

    private void titleFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_titleFieldActionPerformed

    private void filePathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filePathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filePathActionPerformed

    private void fileBrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileBrowseButtonActionPerformed

        importDataSet();
        MXRStates = new MixRegGuiStates(this, advancedOptions_view);
        updateGuiView(MXRStates);

        //        loadModelByBrowseButton.setEnabled(false);
        //        loadModelByBrowseButton.setVisible(false);
        //        goBackButton.setVisible(false);
    }//GEN-LAST:event_fileBrowseButtonActionPerformed

    // **********************update********************
    private void updateGuiView(MixRegGuiStates mxrStates) {
        // this method is to update Gui View with Gui state data saved in MixRegGuiStates
        // Three different kinds of methods are defined:
        //     1. verify: examine necessary verification 
        //     2. all basic states: update all basic states that may or may not trigger other state change        
        //     3. trigger: update states that are triggered by method Two (e.g. visible and enabled states)       

        // Verify: Data file path exists or not
        boolean FilepathValid;
        FilepathValid = updateGuiView_verify_FilePath(mxrStates);

        if (FilepathValid) {
            // update browse triggered fields
            updateGuiView_trigger_browse(true);
            // update missing value trigger fields
            if (mxrStates.missingValuePresent) {
                missingValuePresent.setSelected(true);
                newModelMissingValueCode.setText(mxrStates.newModelMissingValueCode);
            } else if (mxrStates.missingValueAbsent) {
                missingValueAbsent.setSelected(true);
            }
            updateGuiView_trigger_missingvaluefield();
            // update Data View tab
            updateGuiView_trigger_dataview();
            // Update basic GUI States: Model Configuration Tab
            updateGuiView_TabOneStates(mxrStates);
            // Trigger: stage 1 config completed
            updateGuiView_trigger_stageOneConfig();
            // Trigger: Include Stage 2 or not
            updateGuiView_trigger_IncludeStageTwo();
            // Triger: Click to modify stage 2 config or not
            updateGuiVIew_trigger_updateStage2Config();
            // Trigger: New model submitted or not
            isNewModalConfigSubmitted = mxrStates.isNewModalConfigSubmitted;
            if (isNewModalConfigSubmitted == true) {
                updateGuiView_trigger_NewModelSubmit();
            }
        } else {
            return;
        }

        if (isNewModalConfigSubmitted == true) {
            if (!isUpdateStage2ConfigClicked) {
                update_StageOneStates(mxrStates);
            }
            update_StageTwoStates(mxrStates);
        }

        //hide load button temprarily
        guiStatesLoadButtonModalConfig.setVisible(false);

    }

    private boolean updateGuiView_verify_FilePath(MixRegGuiStates mxrStates) {
        dataFileNameRef = mxrStates.filepath;
        filePath.setText(mxrStates.filepath);
        file = new File(mxrStates.filepath);

        if (file.exists()) {
//            setFirstTabStatus(true);
            if (!checkTabExistinJTabbedPane(stageOneTabs, "View Data")) {
                int helpTabIdx = stageOneTabs.indexOfTab("Help");
                stageOneTabs.insertTab("View Data", null, jPanel6, null, helpTabIdx);
            }
            return true;
        } else {
            setFirstTabStatus(false);
//            stageOneTabs.setEnabledAt(6, false);
            JOptionPane.showMessageDialog(null, "To load the configuration, please place the originial dataset using this path:"
                    + "\r\n" + filePath.getText() + "\r\n\r\n" + "OR" + "\r\n\r\n" + "To start a new analysis, please choose a new dataset",
                    "Error: Wrong File Path", JOptionPane.INFORMATION_MESSAGE, icon);
            return false;
        }
    }

    private void updateGuiView_TabOneStates(MixRegGuiStates mxrStates) {
        titleField.setText(mxrStates.title);
        sessionFolderName = mxrStates.sessionFolderName;

        if (mxrStates.stageOneContinuousRadio) {
            stageOneContinuousRadio.setSelected(true);
        } else if (mxrStates.stageOneDichotomousRadio) {
            stageOneDichotomousRadio.setSelected(true);
        } else if (mxrStates.stageOneOrdinalRadio) {
            stageOneOrdinalRadio.setSelected(true);
        }

        if (mxrStates.oneRLERadio) {
            oneRLERadio.setSelected(true);
        } else if (mxrStates.moreThanOneRLERadio) {
            moreThanOneRLERadio.setSelected(true);
        }

        if (mxrStates.randomScaleSelectionYes) {
            randomScaleSelectionYes.setSelected(true);
        } else if (mxrStates.randomScaleSelectionNo) {
            randomScaleSelectionNo.setSelected(true);
        }

        if (mxrStates.includeStageTwoYes) {
            includeStageTwoYes.setSelected(true);
        } else if (mxrStates.includeStageTwoNo) {
            includeStageTwoNo.setSelected(true);
        }

        if (mxrStates.stageTwoSingleLevel) {
            stageTwoSingleLevel.setSelected(true);
        } else if (mxrStates.stageTwoMultiLevel) {
            stageTwoMultiLevel.setSelected(true);
        }

        if (mxrStates.continuousRadio) {
            stageTwoContinuousRadio.setSelected(true);
        } else if (mxrStates.dichotomousRadio) {
            stageTwoDichotomousRadio.setSelected(true);
        } else if (mxrStates.countRadio) {
            countRadio.setSelected(true);
        } else if (mxrStates.multinomialRadio) {
            multinomialRadio.setSelected(true);
        }

        seedTextBox.setText(mxrStates.seedTextBox);
    }

    private void updateGuiView_trigger_MissingValue() {
        if (missingValueAbsent.isSelected()) {
            newModelMissingValueCode.setEnabled(false);
            newModelMissingValueCode.setText("");
        }
    }

    private void updateGuiView_trigger_IncludeStageTwo() {
        if (includeStageTwoNo.isSelected()) {
            setSeedLabel.setVisible(false);
            seedTextBox.setVisible(false);
            seedHelpButton.setVisible(false);
            seedTextBox.setText("0");
            stageTwoSingleLevel.setVisible(false);
            stageTwoMultiLevel.setVisible(false);
            stageTwoContinuousRadio.setVisible(false);
            stageTwoDichotomousRadio.setVisible(false);
            countRadio.setVisible(false);
            multinomialRadio.setVisible(false);
            stageTwoModelTypeLabel.setVisible(false);
            stageTwoOutcomeTypeLabel.setVisible(false);
            stageTwoModelGiantLabel.setVisible(false);
            stageTwoModelTypeHelpButton.setVisible(false);
            stageTwoOutcomeTypeHelpButton.setVisible(false);

            newModelSubmit.setVisible(true);
            newModelSubmit.setEnabled(true);

//            updateStage2ConfigButton.setVisible(true);
        } else if (includeStageTwoYes.isSelected()) {
            setSeedLabel.setVisible(true);
            seedTextBox.setVisible(true);
            seedHelpButton.setVisible(true);
//            String seedVal = generateSeed();
//            seedTextBox.setText(seedVal);
            seedTextBox.setEnabled(true);
            stageTwoSingleLevel.setVisible(true);
            stageTwoSingleLevel.setEnabled(true);
            stageTwoMultiLevel.setVisible(true);
            stageTwoMultiLevel.setEnabled(true);
            stageTwoContinuousRadio.setVisible(true);
            stageTwoContinuousRadio.setEnabled(true);
            stageTwoDichotomousRadio.setVisible(true);
            stageTwoDichotomousRadio.setEnabled(true);
            countRadio.setVisible(true);
            multinomialRadio.setVisible(true);
            stageTwoModelTypeLabel.setVisible(true);
            stageTwoOutcomeTypeLabel.setVisible(true);
            stageTwoModelGiantLabel.setVisible(true);
            stageTwoModelTypeHelpButton.setVisible(true);
            stageTwoOutcomeTypeHelpButton.setVisible(true);

            if ((stageTwoLevelGroup.getSelection() != null) && (buttonGroup3.getSelection() != null)) {
                newModelSubmit.setVisible(true);
                newModelSubmit.setEnabled(true);
            } else {
//                newModelSubmit.setVisible(false);
                newModelSubmit.setEnabled(false);
            }

//            updateStage2ConfigButton.setVisible(true);
        }
    }

    private void update_StageOneStates(MixRegGuiStates mxrStates) {
        IDpos = mxrStates.IDpos;
        IDvariableCombo.setSelectedIndex(IDpos);

        stageOnePos = mxrStates.stageOnePos;
        StageOneVariableCombo.setSelectedIndex(stageOnePos);

        stageTwoPos = mxrStates.stageTwoPos;
        stageTwoOutcome.setSelectedIndex(stageTwoPos);

        stageOneClicked = mxrStates.stageOneClicked;
        addStageOneCHecked = mxrStates.addStageOneCHecked;
        stageOneRegs.varList = mxrStates.varList;
        stageOneRegs.levelOneList = mxrStates.levelOneList;
        stageOneRegs.levelTwoList = mxrStates.levelTwoList;
        update_trigger_StageOneRegConfig();
        stage_1_regs.getAllVariablesList().removeAll();
        stage_1_regs.getAllVariablesList().setModel(mxrStates.varList);
        stage_1_regs.getAllVariablesList().setSelectedIndex(0);
        stage_1_regs.getStageOneLevelOneList().removeAll();
        stage_1_regs.getStageOneLevelOneList().setModel(mxrStates.levelOneList);
        stage_1_regs.getStageOneLevelTwoList().removeAll();
        stage_1_regs.getStageOneLevelTwoList().setModel(mxrStates.levelTwoList);

        stageOneRegs.isSubmitClicked = mxrStates.isStageOneRegSubmitClicked;
        if (stageOneRegs.isSubmitClicked == true) {
            stage_1_regs.getEnabledStageOneSubmitButton(true);
            //update boxes

//            levelOneBoxes = mxrStates.levelOneBoxes;
//            disaggVarianceBoxes = mxrStates.disaggVarianceBoxes;
//            levelTwoBoxes = mxrStates.levelTwoBoxes;
            if (mxrStates.levelOneList.getSize() > 0) {
                update_StageOneLevelOneBoxes(stageOneRegs.levelOneList, mxrStates.StageOneLevelOneBoxesSelection, mxrStates.disaggVarianceBoxesSelection);
            }
            if (mxrStates.levelTwoList.getSize() > 0) {
                update_StageOneLevelTwoBoxes(stageOneRegs.levelTwoList, mxrStates.StageOneLevelTwoBoxesSelection);
            }
        }

        //update advanced options
        advancedOptions_view.setMeanSubmodelCheckBox(mxrStates.meanSubmodelCheckBox);
        advancedOptions_view.setBSVarianceCheckBox(mxrStates.BSVarianceCheckBox);
        advancedOptions_view.setWSVarianceCheckBox(mxrStates.WSVarianceCheckBox);
        advancedOptions_view.setCenterRegressorsCheckBox(mxrStates.centerRegressorsCheckBox);
        advancedOptions_view.setDiscardSubjectsCheckBox(mxrStates.discardSubjectsCheckBox);
        advancedOptions_view.setResampleCheckBox(mxrStates.resampleCheckBox);
        advancedOptions_view.setAdaptiveQuadritureCheckBox(mxrStates.adaptiveQuadritureCheckBox);
        advancedOptions_view.setRun32BitCheckBox(mxrStates.run32BitCheckBox);
        advancedOptions_view.setConvergenceCriteria(mxrStates.convergenceCriteria);
        advancedOptions_view.setQuadriturePoints(mxrStates.quadriturePoints);
        advancedOptions_view.setMaximumIterations(mxrStates.maximumIterations);
        advancedOptions_view.setRidgeSpinner(mxrStates.ridgeSpinner);
        advancedOptions_view.setResampleSpinner(mxrStates.resampleSpinner);
        advancedOptions_view.update_trigger_AdvancedOptionsSubmit();
        advancedOptions_view.update_trigger_resampleCheckBox();
//        advancedOptions_view.update_trigger_run32BitCheckBox();
        NoAssociationRadio.setSelected(mxrStates.NoAssociationRadio);
        LinearAssociationRadio.setSelected(mxrStates.LinearAssociationRadio);
        QuadraticAssociationRadio.setSelected(mxrStates.QuadraticAssociationRadio);

        isStageOneSubmitted = mxrStates.isStageOneSubmitted;
        levelTwoSelected = mxrStates.levelTwoSelected;
        if (isStageOneSubmitted == true) {
            update_trigger_StartStageTwo();
        }

    }

// **********************update********************
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        SystemLogger.LOGGER.log(Level.INFO, "Program Start");
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Oyaha".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mixregGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mixregGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mixregGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mixregGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //imageViewr-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mxr = new mixregGUI();
                mxr.setLocationRelativeTo(null);
                mxr.setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DatasetLabel;
    private javax.swing.JComboBox<String> IDvariableCombo;
    private javax.swing.JRadioButton LinearAssociationRadio;
    private javax.swing.JRadioButton NoAssociationRadio;
    private javax.swing.JRadioButton QuadraticAssociationRadio;
    private javax.swing.JComboBox<String> StageOneVariableCombo;
    private javax.swing.JButton addStageOneButton;
    private javax.swing.JButton addStageTwoTabTwo;
    private javax.swing.JButton advancedOptionsButton;
    private javax.swing.JLabel associationLabel;
    private javax.swing.JPanel associationPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JRadioButton countRadio;
    private javax.swing.JLabel dataFileLabel;
    public static javax.swing.JTable dataTable;
    private javax.swing.JLabel datasetHelpButton;
    private javax.swing.JLabel datasetMissingValuesHelpButton;
    private javax.swing.JTextArea equationArea;
    private javax.swing.JButton exampleDataDownload;
    private javax.swing.JButton fileBrowseButton;
    private javax.swing.JTextField filePath;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton guiStatesLoadButtonModalConfig;
    private javax.swing.JButton guiStatesSaveButtonModalConfig;
    private javax.swing.JButton guiStatesSaveButtonStageOne;
    private javax.swing.JButton guiStatesSaveButtonStageTwo;
    private javax.swing.JLabel hiddenBigIconLabel;
    private javax.swing.ButtonGroup includeStageTwoGroup;
    private javax.swing.JLabel includeStageTwoLabel;
    private javax.swing.JRadioButton includeStageTwoNo;
    private javax.swing.JRadioButton includeStageTwoYes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton8;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel level1_BSVar;
    private javax.swing.JLabel level1_MeanReg;
    private javax.swing.JLabel level1_WSVar;
    private javax.swing.JLabel level2_BSVar;
    private javax.swing.JLabel level2_MeanReg;
    private javax.swing.JLabel level2_WSVar;
    private javax.swing.JPanel levelOneGrid;
    private javax.swing.JPanel levelTwoGrid;
    private javax.swing.JButton loadModelByBrowseButton;
    private javax.swing.JLabel missingCodeViewLabel;
    private javax.swing.JRadioButton missingValueAbsent;
    private javax.swing.JRadioButton missingValuePresent;
    private javax.swing.JLabel missingViewLabel;
    private javax.swing.JRadioButton moreThanOneRLERadio;
    private javax.swing.JRadioButton multinomialRadio;
    private javax.swing.JButton newDataSetButton;
    private javax.swing.JTextField newModelMissingValueCode;
    private javax.swing.JButton newModelSubmit;
    private javax.swing.JButton newModel_resetButton;
    private javax.swing.JLabel numResamplingStageTwoConfigLabel;
    private javax.swing.JLabel numResamplingStageTwoConfigLabel1;
    private javax.swing.JRadioButton oneRLERadio;
    private javax.swing.JTextPane outCategoryDisplay;
    private javax.swing.JButton outcomeCatButton;
    private javax.swing.JPanel parentPanel;
    public static javax.swing.JLabel printedFileName;
    public static javax.swing.JLabel randomLocationEffectsLabel;
    public static javax.swing.JLabel randomLocationEffectsLabel1;
    private javax.swing.ButtonGroup randomScaleSelectionGroup;
    private javax.swing.JRadioButton randomScaleSelectionNo;
    private javax.swing.JRadioButton randomScaleSelectionYes;
    private javax.swing.JLabel randomScaleViewLabel;
    private javax.swing.JButton resetButton;
    private javax.swing.JLabel rleViewLabel;
    private javax.swing.JButton runTabTwoStageOneTwo;
    private javax.swing.JButton saveStage2OutButton;
    private javax.swing.JLabel seedHelpButton;
    private javax.swing.JTextField seedTextBox;
    private javax.swing.JLabel setSeedLabel;
    private javax.swing.JRadioButton stageOneContinuousRadio;
    private javax.swing.JRadioButton stageOneDichotomousRadio;
    private javax.swing.JPanel stageOneLevelOnePanel;
    private javax.swing.JPanel stageOneLevelTwoPanel;
    private javax.swing.JLabel stageOneModelGiantLabel;
    private javax.swing.JLabel stageOneModelStageTwoConfigLabel;
    private javax.swing.JLabel stageOneModelStageTwoConfigLabel1;
    private javax.swing.JRadioButton stageOneOrdinalRadio;
    private javax.swing.ButtonGroup stageOneOutcomeGroup;
    private javax.swing.JLabel stageOneOutcomeHelpButton;
    private javax.swing.JLabel stageOneOutcomeStageTwoConfigLabel;
    private javax.swing.JLabel stageOneOutcomeStageTwoConfigLabel1;
    private javax.swing.JLabel stageOneOutcomeViewLabel;
    public static javax.swing.JTextArea stageOneOutput;
    private javax.swing.JLabel stageOneRLEHelpButton;
    private javax.swing.JLabel stageOneRSHelpButton;
    private javax.swing.JTabbedPane stageOneTabs;
    private javax.swing.JRadioButton stageTwoContinuousRadio;
    private javax.swing.JLabel stageTwoDescription;
    private javax.swing.JRadioButton stageTwoDichotomousRadio;
    private javax.swing.ButtonGroup stageTwoLevelGroup;
    private javax.swing.JPanel stageTwoLevelOnePanel;
    private javax.swing.JPanel stageTwoLevelTwoPanel;
    private javax.swing.JLabel stageTwoModelGiantLabel;
    private javax.swing.JLabel stageTwoModelTypeHelpButton;
    private javax.swing.JLabel stageTwoModelTypeLabel;
    private javax.swing.JLabel stageTwoModelTypeStageTwoConfigLabel;
    private javax.swing.JLabel stageTwoModelTypeStageTwoConfigLabel1;
    private javax.swing.JRadioButton stageTwoMultiLevel;
    private javax.swing.JComboBox<String> stageTwoOutcome;
    public static javax.swing.JLabel stageTwoOutcomePrintLabel;
    public static javax.swing.JLabel stageTwoOutcomePrintLabel1;
    private javax.swing.JLabel stageTwoOutcomeStageTwoConfigLabel;
    private javax.swing.JLabel stageTwoOutcomeStageTwoConfigLabel1;
    private javax.swing.JLabel stageTwoOutcomeTypeHelpButton;
    private javax.swing.JLabel stageTwoOutcomeTypeLabel;
    public static javax.swing.JTextArea stageTwoOutput;
    private javax.swing.JPanel stageTwoRegsGridLvl1;
    private javax.swing.JPanel stageTwoRegsGridLvl2;
    private javax.swing.JRadioButton stageTwoSingleLevel;
    private javax.swing.JButton startStageTwo;
    private javax.swing.JCheckBox suppressIntCheckBox;
    private javax.swing.JTextField titleField;
    private javax.swing.JLabel titleViewLabel;
    private javax.swing.JButton updateStage2ConfigButton;
    private javax.swing.JButton userGuideDownload;
    // End of variables declaration//GEN-END:variables

    public void isSubmitClicked() {
        IDvariableCombo.setSelectedIndex(0);
        StageOneVariableCombo.setSelectedIndex(1);
        stageTwoOutcome.setSelectedIndex(2);

        buttonGroup1.clearSelection();

        //addStageTwoTabTwo.setEnabled(false);
        stageOneLevelTwoPanel.removeAll();
        stageOneLevelTwoPanel.revalidate();
        stageOneLevelTwoPanel.repaint();

        stageOneLevelOnePanel.removeAll();
        stageOneLevelOnePanel.revalidate();
        stageOneLevelOnePanel.repaint();

        parentPanel.removeAll();
        parentPanel.add(stageOneTabs);
        parentPanel.repaint();
        parentPanel.revalidate();
    }

    //Updates IDs and outcome variables list
    public void updateComboBoxes() {

        for (int j = 0; j < variableNamesCombo.length; j++) {
            IDList.addElement(variableNamesCombo[j]);
            StageOneList.addElement(variableNamesCombo[j]);
            StageTwoList.addElement(variableNamesCombo[j]);
        }

        IDvariableCombo.setModel(IDList);
        IDvariableCombo.setSelectedIndex(0);

        StageOneVariableCombo.setModel(StageOneList);
        StageOneVariableCombo.setSelectedIndex(1);

        stageTwoOutcome.setModel(StageTwoList);
        stageTwoOutcome.setSelectedIndex(2);

    }

    //Open a web link from the software
    public static void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            SystemLogger.LOGGER.log(Level.SEVERE, e.toString() + "{0}", SystemLogger.getLineNum());
            e.printStackTrace();
        }
    }

    //get a list of variables remaininag from stage 1 
    public DefaultListModel<String> getSavedVariables() {

        int index = stageTwoOutcome.getSelectedIndex();

        //DefaultListModel<String> tempModel = stage_1_regs.getListModel();
        DefaultListModel<String> tempModel = stage_1_regs.getListModel();

        tempModel.removeElement(stageTwoOutcome.getSelectedItem());

        savedVariablesStageOne = tempModel;

        return savedVariablesStageOne;

    }

    //get ID variable selected by the user
    public int getIDVariable() {
        // String ID;

        int pos = IDvariableCombo.getSelectedIndex();

        return pos;
    }

    //Get the position of ID variable in the data file
    public static int getIDFieldPosition() {

        return IDpos;
    }

    //get Stage One DV variable selected by the user
    public String getStageOneDV() {
        String StageOneDV;

        StageOneDV = StageOneVariableCombo.getItemAt(StageOneVariableCombo.getSelectedIndex());

        return StageOneDV;

    }

    //Get stage 1 dv position in data file
    public static int getStageOneDVFieldPosition() {

        return stageOnePos;
    }

    //get Stage Two variable selected by the user
    public String getStageTwoDV() {
        String StageTwoDV;

        StageTwoDV = stageTwoOutcome.getItemAt(stageTwoOutcome.getSelectedIndex());

        return StageTwoDV;
    }

    //Get stage 2 dv position in data file
    public static int getStageTwoDVFieldPosition() {

        return stageTwoPos;
    }

    public boolean getNoAssociationRadio() {
        return NoAssociationRadio.isSelected();
    }

    public boolean getLinearAssociationRadio() {
        return LinearAssociationRadio.isSelected();
    }

    public boolean getQuadraticAssociationRadio() {
        return QuadraticAssociationRadio.isSelected();
    }

    public boolean getSuppressIntCheckBox() {
        return suppressIntCheckBox.isSelected();
    }

    //Update level 1 table with regressors
    public void updateStageOneLevelOneGrid(DefaultListModel<String> defaultListModel) {

        levelOneSelected = new ArrayList<String>();

        JScrollPane scrollpanel = new JScrollPane(levelOneGrid);

        int regSize = defaultListModel.getSize();
        levelOneRegSize = regSize;
        levelOneDisaggSize = regSize;

        levelOneGrid.removeAll();

        levelOneGrid.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        //constraints.weightx = 1.0;
        constraints.anchor = GridBagConstraints.NORTH;
        //constraints.gridwidth = 4;

        GridBagConstraints separatorConstraint = new GridBagConstraints();
        separatorConstraint.weightx = 1.0;
        separatorConstraint.fill = GridBagConstraints.HORIZONTAL;
        separatorConstraint.gridwidth = GridBagConstraints.REMAINDER;
        separatorConstraint.gridx = 0;

        constraints.insets = new Insets(3, 0, 5, 25);
        separatorConstraint.insets = new Insets(0, 0, 0, 0);
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;

        levelOneBoxes = new ArrayList<>();
        disaggVarianceBoxes = new ArrayList<>();

        for (int j = 0; j < regSize; j++) {
            constraints.gridx = 0;
            constraints.anchor = GridBagConstraints.LINE_END;
            levelOneSelected.add(defaultListModel.getElementAt(j));
            levelOneGrid.add(new JLabel(levelOneSelected.get(j)), constraints);

            levelOneBoxes.add(j, new ArrayList<JCheckBox>());

            for (int k = 0; k < 3; k++) {
                int row = j;
                int column = k;

                constraints.gridx++;
                constraints.anchor = GridBagConstraints.CENTER;
                levelOneBoxes.get(j).add(k, new JCheckBox());
                levelOneGrid.add(levelOneBoxes.get(j).get(k), constraints);
                levelOneBoxes.get(j).get(k).addActionListener(actionListener);
                levelOneBoxes.get(j).get(k).addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        AbstractButton abstractButton = (AbstractButton) e.getSource();
                        boolean selected = abstractButton.getModel().isSelected();
                        if (selected) {
                            System.out.println("Checkbox selected");
                            disaggVarianceBoxes.get(row).get(column).setEnabled(true);
                            disaggVarianceBoxes.get(row).get(column).setSelected(false);
                            System.out.println(disaggVarianceBoxes.size());
                        } else {
                            disaggVarianceBoxes.get(row).get(column).setEnabled(false);
                            disaggVarianceBoxes.get(row).get(column).setSelected(false);
                        }

                    }
                });

            }

            constraints.gridy++;
            constraints.gridx = 0;
            constraints.anchor = GridBagConstraints.LINE_END;

            levelOneGrid.add(new JLabel("Disaggregate?"), constraints);
            disaggVarianceBoxes.add(j, new ArrayList<JCheckBox>());

            for (int k = 0; k < 3; k++) {
                constraints.gridx++;
                constraints.anchor = GridBagConstraints.CENTER;

                disaggVarianceBoxes.get(j).add(k, new JCheckBox());

                levelOneGrid.add(disaggVarianceBoxes.get(j).get(k), constraints);
                disaggVarianceBoxes.get(j).get(k).setEnabled(false);

            }

            constraints.gridy++;
            //constraints.gridx = 0;
            separatorConstraint.gridy = separatorConstraint.gridy + 3;
            //System.out.println("before seperator");
            levelOneGrid.add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint);
            //System.out.println("after seperator");
            constraints.gridy++;

        }

        stageOneLevelOnePanel.removeAll();
        stageOneLevelOnePanel.revalidate();
        stageOneLevelOnePanel.repaint();

        stageOneLevelOnePanel.add(scrollpanel);
        revalidate();

    }

    //Update level 2 table with regressors
    public void updateStageOneLevelTwoGrid(DefaultListModel<String> defaultListModel) {

        //levelTwoGrid.setVisible(true);
        JScrollPane scrollpanel = new JScrollPane(levelTwoGrid);
        levelTwoSelected = new ArrayList<String>();

        int regSize = defaultListModel.getSize();
        levelTwoRegSize = regSize;

        levelTwoGrid.removeAll();

        levelTwoGrid.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        // constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.NORTH;
        //constraints.gridwidth = 4;

        GridBagConstraints separatorConstraint = new GridBagConstraints();
        separatorConstraint.weightx = 1.0;
        separatorConstraint.fill = GridBagConstraints.HORIZONTAL;
        separatorConstraint.gridwidth = GridBagConstraints.REMAINDER;
        separatorConstraint.gridx = 0;

        constraints.insets = new Insets(3, 0, 5, 25);
        separatorConstraint.insets = new Insets(0, 0, 0, 0);
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;

        levelTwoBoxes = new ArrayList<ArrayList<JCheckBox>>();
        //disaggVarianceBoxes = new ArrayList<ArrayList<JCheckBox>>();

        for (int j = 0; j < regSize; j++) {
            constraints.gridx = 0;
            constraints.anchor = GridBagConstraints.LINE_END;
            levelTwoSelected.add(defaultListModel.getElementAt(j));
            levelTwoGrid.add(new JLabel(levelTwoSelected.get(j)), constraints);
            //levelTwoGrid.add(new JLabel(defaultListModel.getElementAt(j)), constraints);

            levelTwoBoxes.add(j, new ArrayList<JCheckBox>());

            for (int k = 0; k < 3; k++) {

                constraints.gridx++;
                constraints.anchor = GridBagConstraints.CENTER;
                levelTwoBoxes.get(j).add(k, new JCheckBox());
                levelTwoGrid.add(levelTwoBoxes.get(j).get(k), constraints);
            }

            if (isRandomScale) {

                levelTwoBoxes.get(j).get(2).setEnabled(true);

            } else {

                levelTwoBoxes.get(j).get(2).setEnabled(false);

            }

            if (i == MixLibrary.STAGE_ONE_RLE_SLOPE) {
                levelTwoBoxes.get(j).get(1).setVisible(false);

            } else {

                levelTwoBoxes.get(j).get(1).setVisible(true);
                levelTwoBoxes.get(j).get(1).setEnabled(true);

            }

            constraints.gridy++;

            separatorConstraint.gridy = separatorConstraint.gridy + 2;
            // System.out.println("before seperator");
            levelTwoGrid.add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint);
            // System.out.println("after seperator");
            constraints.gridy++;

        }

        stageOneLevelTwoPanel.removeAll();
        stageOneLevelTwoPanel.revalidate();
        stageOneLevelTwoPanel.repaint();

        stageOneLevelTwoPanel.add(scrollpanel);
        revalidate();

    }

    //Update stage 2 table with selected regressors
    public void updateStageTwoLevelTwoGrid(DefaultListModel<String> defaultListModel) {

        JScrollPane scrollpanel = new JScrollPane(stageTwoRegsGridLvl2);
        stageTwoLevelTwoSelected = new ArrayList<String>();

        int regSize = defaultListModel.getSize();
        stageTwoLevelTwoRegSize = regSize;

        stageTwoRegsGridLvl2.removeAll();

        stageTwoRegsGridLvl2.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        // constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        //constraints.gridwidth = 4;

        GridBagConstraints separatorConstraint = new GridBagConstraints();
        separatorConstraint.weightx = 1.0;
        separatorConstraint.fill = GridBagConstraints.HORIZONTAL;
        separatorConstraint.gridwidth = GridBagConstraints.REMAINDER;
        separatorConstraint.gridx = 0;

        constraints.insets = new Insets(3, 0, 5, 25);
        separatorConstraint.insets = new Insets(0, 0, 0, 0);
        constraints.weightx = 1;

        stageTwoLevelTwoGridBoxes = new ArrayList<ArrayList<JCheckBox>>();
        //disaggVarianceBoxes = new ArrayList<ArrayList<JCheckBox>>();

        for (int j = 0; j < regSize; j++) {
            int row = j;
            constraints.gridx = 1;
            constraints.anchor = GridBagConstraints.FIRST_LINE_START;
            stageTwoLevelTwoSelected.add(defaultListModel.getElementAt(j));
            JLabel testLabel = new JLabel(stageTwoLevelTwoSelected.get(j));
//            System.out.print("labelll  "+testLabel.isVisible());
            stageTwoRegsGridLvl2.add(testLabel, constraints);

            //stageTwoGrid.add(new JLabel(defaultListModel.getElementAt(j)), constraints);
            stageTwoLevelTwoGridBoxes.add(j, new ArrayList<JCheckBox>());

            for (int k = 0; k < 4; k++) {

                if (k == 1) {

                    constraints.gridx = constraints.gridx + 5;

                } else {
                    constraints.gridx++;
                }

                constraints.anchor = GridBagConstraints.CENTER;
                stageTwoLevelTwoGridBoxes.get(j).add(k, new JCheckBox());

                stageTwoRegsGridLvl2.add(stageTwoLevelTwoGridBoxes.get(j).get(k), constraints);
            }

            constraints.gridy++;

            separatorConstraint.gridy = separatorConstraint.gridy + 2;

            stageTwoRegsGridLvl2.add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint);
            //System.out.println("after seperator");
            constraints.gridy++;

            stageTwoLevelTwoGridBoxes.get(row).get(1).setEnabled(false);
            stageTwoLevelTwoGridBoxes.get(row).get(2).setEnabled(false);
            stageTwoLevelTwoGridBoxes.get(row).get(3).setEnabled(false);

            stageTwoLevelTwoGridBoxes.get(j).get(0).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    AbstractButton abstractButton = (AbstractButton) e.getSource();
                    boolean selected = abstractButton.getModel().isSelected();
                    if (selected) {
                        System.out.println("Checkbox selected");
                        //disaggVarianceBoxes.get(row).get(column).setEnabled(true);
                        stageTwoLevelTwoGridBoxes.get(row).get(1).setEnabled(true);
                        stageTwoLevelTwoGridBoxes.get(row).get(1).setSelected(false);
                        stageTwoLevelTwoGridBoxes.get(row).get(2).setEnabled(true);
                        stageTwoLevelTwoGridBoxes.get(row).get(2).setSelected(false);
                        randomChecked = false;
                        scaleChecked = false;
//                        System.out.println(disaggVarianceBoxes.size());
                    } else {
                        //disaggVarianceBoxes.get(row).get(column).setEnabled(false);
                        stageTwoLevelTwoGridBoxes.get(row).get(1).setEnabled(false);
                        stageTwoLevelTwoGridBoxes.get(row).get(1).setSelected(false);
                        stageTwoLevelTwoGridBoxes.get(row).get(2).setEnabled(false);
                        stageTwoLevelTwoGridBoxes.get(row).get(2).setSelected(false);
                        stageTwoLevelTwoGridBoxes.get(row).get(3).setSelected(false);
                        stageTwoLevelTwoGridBoxes.get(row).get(3).setEnabled(false);
                        randomChecked = false;
                        scaleChecked = false;
                        suppressIntCheckBox.setEnabled(false);
                        suppressIntCheckBox.setSelected(false);

                    }

                }
            });

            stageTwoLevelTwoGridBoxes.get(j).get(1).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    AbstractButton abstractButton = (AbstractButton) e.getSource();
                    boolean selected = abstractButton.getModel().isSelected();
                    if (selected) {
                        scaleChecked = true;
                        if (randomChecked == true) {
                            if (!suppressed) {
                                stageTwoLevelTwoGridBoxes.get(row).get(3).setEnabled(true);
                                stageTwoLevelTwoGridBoxes.get(row).get(3).setSelected(false);
                            }
                        }

                    } else {
                        scaleChecked = false;
                        if (!suppressed) {
                            stageTwoLevelTwoGridBoxes.get(row).get(3).setEnabled(false);
                            stageTwoLevelTwoGridBoxes.get(row).get(3).setSelected(false);
                        }
                    }
                }
            });

            stageTwoLevelTwoGridBoxes.get(j).get(2).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    AbstractButton abstractButton = (AbstractButton) e.getSource();
                    boolean selected = abstractButton.getModel().isSelected();

                    if (selected) {
                        randomChecked = true;

                        if (scaleChecked == true) {
                            if (!suppressed) {
                                stageTwoLevelTwoGridBoxes.get(row).get(3).setEnabled(true);
                                stageTwoLevelTwoGridBoxes.get(row).get(3).setSelected(false);
                            }
                        }

                    } else {
                        randomChecked = false;
                        if (!suppressed) {
                            stageTwoLevelTwoGridBoxes.get(row).get(3).setEnabled(false);
                            stageTwoLevelTwoGridBoxes.get(row).get(3).setSelected(false);
                        }

                    }
                }
            });

            stageTwoLevelTwoGridBoxes.get(j).get(3).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    AbstractButton abstractButton = (AbstractButton) e.getSource();
                    boolean selected = abstractButton.getModel().isSelected();

                    randomChecked = false;
                    scaleChecked = false;

                    suppressIntCheckBox.setEnabled(true);

                }
            });

        }

        stageTwoLevelTwoPanel.removeAll();
        stageTwoLevelTwoPanel.add(scrollpanel);
//        stageTwoLevelOnePanel.add(jSeparator6);
        stageTwoLevelTwoPanel.revalidate();
        stageTwoLevelTwoPanel.repaint();

//        revalidate();
//        repaint();
    }

    public int countLevelOneBeta() {

        int levelOneBeta = 0;

        for (int p = 0; p < levelOneRegSize; p++) {

            if (levelOneBoxes.get(p).get(0).isSelected()) {

                levelOneBeta = levelOneBeta + 1;
            }
        }

        return levelOneBeta;

    }

    public int countLevelOneDicompMean() {
        int levelOneDisagg = 0;

        for (int p = 0; p < levelOneRegSize; p++) {

            if (disaggVarianceBoxes.get(p).get(0).isSelected()) {
                levelOneDisagg = levelOneDisagg + 1;
            }

        }

        return levelOneDisagg;
    }

    public int countLevelOneDicompBS() {
        int levelOneDisagg = 0;

        for (int p = 0; p < levelOneRegSize; p++) {

            if (disaggVarianceBoxes.get(p).get(1).isSelected()) {
                levelOneDisagg = levelOneDisagg + 1;
            }

        }

        return levelOneDisagg;
    }

    public int countLevelOneDicompWS() {
        int levelOneDisagg = 0;

        for (int p = 0; p < levelOneRegSize; p++) {

            if (disaggVarianceBoxes.get(p).get(2).isSelected()) {
                levelOneDisagg = levelOneDisagg + 1;
            }

        }

        return levelOneDisagg;
    }

    public int countLevelTwoBeta() {

        int levelTwoBeta = 0;

        for (int p = 0; p < levelTwoRegSize; p++) {

            if (levelTwoBoxes.get(p).get(0).isSelected()) {

                levelTwoBeta = levelTwoBeta + 1;
            }
        }

        return levelTwoBeta;

    }

    //setStageTwoFixedCount()
    public int countStageTwoBeta() {

        int stageTwoBeta = 0;

        for (int p = 0; p < stageTwoLevelTwoRegSize; p++) {

            if (stageTwoLevelTwoGridBoxes.get(p).get(0).isSelected()) {

                stageTwoBeta = stageTwoBeta + 1;
            }
        }

        return stageTwoBeta;

    }

    public int countLevelOneAlpha() {

        int levelOneAlpha = 0;

        for (int p = 0; p < levelOneRegSize; p++) {

            if (levelOneBoxes.get(p).get(1).isSelected()) {

                levelOneAlpha = levelOneAlpha + 1;
            }
        }

        return levelOneAlpha;

    }

    public int countLevelTwoAlpha() {

        int levelTwoAlpha = 0;

        for (int p = 0; p < levelTwoRegSize; p++) {

            if (levelTwoBoxes.get(p).get(1).isSelected()) {

                levelTwoAlpha = levelTwoAlpha + 1;
            }
        }

        return levelTwoAlpha;

    }

    //setStageTwoLocRanInteractions
    public int countStageTwoAlpha() {

        int stageTwoAlpha = 0;

        for (int p = 0; p < stageTwoLevelTwoRegSize; p++) {

            if (stageTwoLevelTwoGridBoxes.get(p).get(1).isSelected()) {

                stageTwoAlpha = stageTwoAlpha + 1;
            }
        }

        return stageTwoAlpha;

    }

    public int countLevelOneTau() {

        int levelOneTau = 0;

        for (int p = 0; p < levelOneRegSize; p++) {

            if (levelOneBoxes.get(p).get(2).isSelected()) {

                levelOneTau = levelOneTau + 1;
            }
        }

        return levelOneTau;

    }

    public int countLevelTwoTau() {

        int levelTwoTau = 0;

        for (int p = 0; p < levelTwoRegSize; p++) {

            if (levelTwoBoxes.get(p).get(2).isSelected()) {

                levelTwoTau = levelTwoTau + 1;
            }
        }

        return levelTwoTau;

    }

    //setStageTwoScaleInteractions
    public int countStageTwoTau() {

        int stageTwoTau = 0;

        for (int p = 0; p < stageTwoLevelTwoRegSize; p++) {

            if (stageTwoLevelTwoGridBoxes.get(p).get(2).isSelected()) {

                stageTwoTau = stageTwoTau + 1;
            }
        }

        return stageTwoTau;

    }

    public int countStageTwoInteractions() {

        int stageTwoInter = 0;

        for (int p = 0; p < stageTwoLevelTwoRegSize; p++) {

            if (stageTwoLevelTwoGridBoxes.get(p).get(3).isSelected()) {

                stageTwoInter = stageTwoInter + 1;
            }
        }

        return stageTwoInter;

    }

    public void disableLevelTwoRandomLocation() {

    }

    public String[] getMeanFieldRegressorLabels_levelOne() {
        System.out.println("*********************************");
        System.out.println("Mean-positions From level 1 (positions)");

        String fieldLabel;

        String[] regressorLabels = new String[levelOneRegSize];
        int index = 0;

        ArrayList<String> position = new ArrayList<>();

        for (int p = 0; p < levelOneRegSize; p++) {

            if (levelOneBoxes.get(p).get(0).isSelected() && !disaggVarianceBoxes.get(p).get(0).isSelected()) {
                regressorLabels[index] = levelOneSelected.get(p);
                fieldLabel = levelOneSelected.get(p);
                System.out.println("From inside mixRegGUI | Level One Regressor Fields (Mean): " + regressorLabels[index]);
                index++;

                int posIndex = 0;

                for (int q = 0; q < variableNamesCombo.length; q++) {

                    if (variableNamesCombo[q].equals(fieldLabel)) {
                        //position[index] = String.valueOf(q + 1);
                        position.add(String.valueOf(q + 1));
                        System.out.println("Regressor position test: " + String.valueOf(q + 1));
                        System.out.println("From inside mixRegGUI | Position of this regressor: " + position.get(posIndex));
                        System.out.println("Position array: " + position);
                        posIndex++;

                    }

                }
            }

        }
        System.out.println("Position Aray Size here: " + String.valueOf(position.size()));

        String[] positionArray = new String[position.size()];

        for (int pos = 0; pos < positionArray.length; pos++) {
            positionArray[pos] = position.get(pos);
            System.out.println("positionArrayElements: " + positionArray[pos]);

        }

        System.out.println("Converted array size | position: " + String.valueOf(positionArray.length));
        System.out.println("Converted array elements | positions: " + Arrays.toString(positionArray));
        System.out.println("*********************************");

        return positionArray;

    }

    public String[] getMeanFieldRegressorLabels_levelTwo() {
        System.out.println("*********************************");
        System.out.println("Mean-positions From level 2 (positions)");

        String fieldLabel;

        String[] regressorLabels = new String[levelTwoRegSize];
        int index = 0;

        ArrayList<String> position = new ArrayList<>();

        for (int p = 0; p < levelTwoRegSize; p++) {

            if (levelTwoBoxes.get(p).get(0).isSelected()) {
                regressorLabels[index] = levelTwoSelected.get(p);
                fieldLabel = levelTwoSelected.get(p);
                System.out.println("From inside mixRegGUI | Level Two Regressor Fields (Mean) level2: " + regressorLabels[index]);
                index++;

                int posIndex = 0;

                for (int q = 0; q < variableNamesCombo.length; q++) {

                    if (variableNamesCombo[q].equals(fieldLabel)) {
                        //position[index] = String.valueOf(q + 1);
                        position.add(String.valueOf(q + 1));
                        System.out.println("Regressor position test: " + String.valueOf(q + 1));
                        System.out.println("From inside mixRegGUI | Position of this regressor in level2: " + position.get(posIndex));
                        System.out.println("Position array: " + position);
                        posIndex++;

                    }

                }
            }

        }
        System.out.println("Position Aray Size here: " + String.valueOf(position.size()));

        String[] positionArray = new String[position.size()];

        for (int pos = 0; pos < positionArray.length; pos++) {
            positionArray[pos] = position.get(pos);
            System.out.println("positionArrayElements: " + positionArray[pos]);

        }

        System.out.println("Converted array size | position: " + String.valueOf(positionArray.length));
        System.out.println("Converted array elements | positions: " + Arrays.toString(positionArray));
        System.out.println("*********************************");

        return positionArray;
    }

    public String[] fieldModelMeanArray() {
        System.out.println("*********************************");
        System.out.println("Mean-positions From lstage 1 (positions)");

        int arraySize = getMeanFieldRegressorLabels_levelOne().length + getMeanFieldRegressorLabels_levelTwo().length;
        String[] meanModel = new String[arraySize];

        for (int pos = 0; pos < arraySize; pos++) {
            if (pos >= 0 && pos < getMeanFieldRegressorLabels_levelOne().length) {
                meanModel[pos] = getMeanFieldRegressorLabels_levelOne()[pos];

            } else if (pos >= getMeanFieldRegressorLabels_levelOne().length && pos < arraySize) {

                meanModel[pos] = getMeanFieldRegressorLabels_levelTwo()[pos - getMeanFieldRegressorLabels_levelOne().length];
            }

        }

        System.out.println("Inside mixRegGUI | Mean-nModel STAGE 1: " + Arrays.toString(meanModel));
        System.out.println("Inside mixRegGui | Mean-Model STAGE 1 Size: " + meanModel.length);
        System.out.println("*********************************");

        return meanModel;
    }

    public String[] getMeanDecompFieldRegressorLabels_levelOne() {

        System.out.println("*********************************");
        System.out.println("Mean+Disagg.-positions From level 1 (positions)");

        String fieldLabel;

        String[] regressorLabels = new String[levelOneRegSize];
        int index = 0;

        ArrayList<String> position = new ArrayList<>();

        for (int p = 0; p < levelOneRegSize; p++) {

            if (levelOneBoxes.get(p).get(0).isSelected() && disaggVarianceBoxes.get(p).get(0).isSelected()) {
                regressorLabels[index] = levelOneSelected.get(p);
                fieldLabel = levelOneSelected.get(p);
                System.out.println("From inside mixRegGUI | Level One Regressor Fields (Mean + Disagg.): " + regressorLabels[index]);
                index++;

                int posIndex = 0;

                for (int q = 0; q < variableNamesCombo.length; q++) {

                    if (variableNamesCombo[q].equals(fieldLabel)) {
                        //position[index] = String.valueOf(q + 1);
                        System.out.println("The current position to add in ArrayList is: " + String.valueOf(posIndex));
                        position.add(String.valueOf(q + 1));
                        System.out.println("Regressor position test: " + String.valueOf(q + 1));
                        System.out.println("From inside mixRegGUI | Position of this regressor: " + position.get(posIndex) + "@" + String.valueOf(posIndex));
                        System.out.println("Position array: " + position);
                        posIndex++;

                    }

                }
            }

        }
        System.out.println("Position Aray Size here: " + String.valueOf(position.size()));
        System.out.println("Position ArrayList of Means: " + Arrays.toString(position.toArray()));

        for (int testPos = 0; testPos < position.size(); testPos++) {
            System.out.println("This ArrayList contains: " + position.get(testPos));
        }

        String[] positionArray = new String[position.size()];

        for (int pos = 0; pos < positionArray.length; pos++) {
            positionArray[pos] = position.get(pos);
            System.out.println("positionArrayElements: " + positionArray[pos]);

        }

        System.out.println("Converted array size | position: " + String.valueOf(positionArray.length));
        System.out.println("Converted array elements | positions: " + Arrays.toString(positionArray));
        System.out.println("*********************************");

        return positionArray;

    }

    public String[] getBSFieldRegressorLabels_levelOne() {
        System.out.println("*********************************");
        System.out.println("BS-positions From level 1 (positions)");

        String fieldLabel;

        String[] regressorLabels = new String[levelOneRegSize];
        int index = 0;
        ArrayList<String> position = new ArrayList<>();

        for (int p = 0; p < levelOneRegSize; p++) {

            if (levelOneBoxes.get(p).get(1).isSelected() && !disaggVarianceBoxes.get(p).get(1).isSelected()) {
                regressorLabels[index] = levelOneSelected.get(p);
                fieldLabel = levelOneSelected.get(p);
                System.out.println("From inside mixRegGUI | Level One Regressor Fields (BS + Disagg.): " + regressorLabels[index]);
                index++;
                int posIndex = 0;

                for (int q = 0; q < variableNamesCombo.length; q++) {

                    if (variableNamesCombo[q].equals(fieldLabel)) {
                        position.add(String.valueOf(q + 1));
                        System.out.println("Regressor position test: " + String.valueOf(q + 1));
                        System.out.println("From inside mixRegGUI | Position of this regressor: " + position.get(posIndex));
                        System.out.println("Position array: " + position);
                        posIndex++;

                    }
                }
            }
        }
        System.out.println("Position Aray Size here: " + String.valueOf(position.size()));

        String[] positionArray = new String[position.size()];

        for (int pos = 0; pos < positionArray.length; pos++) {
            positionArray[pos] = position.get(pos);
            System.out.println("positionArrayElements: " + positionArray[pos]);

        }

        System.out.println("Converted array size | position: " + String.valueOf(positionArray.length));
        System.out.println("Converted array elements | positions: " + Arrays.toString(positionArray));
        System.out.println("*********************************");

        return positionArray;

    }

    public String[] getBSFieldRegressorLabels_levelTwo() {

        System.out.println("*********************************");
        System.out.println("BS-positions From level 2 (positions)");

        String fieldLabel;

        String[] regressorLabels = new String[levelTwoRegSize];
        int index = 0;
        ArrayList<String> position = new ArrayList<>();

        for (int p = 0; p < levelTwoRegSize; p++) {

            if (levelTwoBoxes.get(p).get(1).isSelected()) {
                regressorLabels[index] = levelTwoSelected.get(p);
                fieldLabel = levelTwoSelected.get(p);
                System.out.println("From inside mixRegGUI | Level two Regressor Fields level 2 (BS + Disagg.): " + regressorLabels[index]);
                index++;
                int posIndex = 0;

                for (int q = 0; q < variableNamesCombo.length; q++) {

                    if (variableNamesCombo[q].equals(fieldLabel)) {
                        position.add(String.valueOf(q + 1));
                        System.out.println("Regressor position test: " + String.valueOf(q + 1));
                        System.out.println("From inside mixRegGUI | Position of this regressor: " + position.get(posIndex));
                        System.out.println("Position array: " + position);
                        posIndex++;

                    }
                }
            }
        }
        System.out.println("Position Aray Size here: " + String.valueOf(position.size()));

        String[] positionArray = new String[position.size()];

        for (int pos = 0; pos < positionArray.length; pos++) {
            positionArray[pos] = position.get(pos);
            System.out.println("positionArrayElements: " + positionArray[pos]);

        }

        System.out.println("Converted array size | position: " + String.valueOf(positionArray.length));
        System.out.println("Converted array elements | positions: " + Arrays.toString(positionArray));
        System.out.println("*********************************");

        return positionArray;

    }

    public String[] fieldModelBSArray() {

        System.out.println("*********************************");
        System.out.println("BS-positions From stage 1 (positions)");

        int arraySize = getBSFieldRegressorLabels_levelOne().length + getBSFieldRegressorLabels_levelTwo().length;
        String[] meanModel = new String[arraySize];

        for (int pos = 0; pos < arraySize; pos++) {
            if (pos >= 0 && pos < getBSFieldRegressorLabels_levelOne().length) {
                meanModel[pos] = getBSFieldRegressorLabels_levelOne()[pos];

            } else if (pos >= getBSFieldRegressorLabels_levelOne().length && pos < arraySize) {

                meanModel[pos] = getBSFieldRegressorLabels_levelTwo()[pos - getBSFieldRegressorLabels_levelOne().length];
            }

        }

        System.out.println("Inside mixRegGUI | BS-Model STAGE 1: " + Arrays.toString(meanModel));
        System.out.println("Inside mixRegGui | BS-Model STAGE 1 Size: " + meanModel.length);
        System.out.println("*********************************");

        return meanModel;
    }

    public String[] getBSDecompFieldRegressorLabels_levelOne() {

        System.out.println("*********************************");
        System.out.println("BS+Disagg-positions From level 1 (positions)");

        String fieldLabel;

        String[] regressorLabels = new String[levelOneRegSize];
        int index = 0;
        ArrayList<String> position = new ArrayList<>();

        for (int p = 0; p < levelOneRegSize; p++) {

            if (levelOneBoxes.get(p).get(1).isSelected() && disaggVarianceBoxes.get(p).get(1).isSelected()) {
                regressorLabels[index] = levelOneSelected.get(p);
                fieldLabel = levelOneSelected.get(p);
                System.out.println("From inside mixRegGUI | Level One Regressor Fields (BS): " + regressorLabels[index]);
                index++;
                int posIndex = 0;

                for (int q = 0; q < variableNamesCombo.length; q++) {

                    if (variableNamesCombo[q].equals(fieldLabel)) {
                        position.add(String.valueOf(q + 1));
                        System.out.println("Regressor position test: " + String.valueOf(q + 1));
                        System.out.println("From inside mixRegGUI | Position of this regressor: " + position.get(posIndex));
                        System.out.println("Position array: " + position);
                        posIndex++;

                    }
                }
            }
        }
        System.out.println("Position Aray Size here: " + String.valueOf(position.size()));

        String[] positionArray = new String[position.size()];

        for (int pos = 0; pos < positionArray.length; pos++) {
            positionArray[pos] = position.get(pos);
            System.out.println("positionArrayElements: " + positionArray[pos]);

        }

        System.out.println("Converted array size | position: " + String.valueOf(positionArray.length));
        System.out.println("Converted array elements | positions: " + Arrays.toString(positionArray));
        System.out.println("*********************************");

        return positionArray;

    }

    public String[] getWSFieldRegressorLabels_levelOne() {
        System.out.println("*********************************");
        System.out.println("WS-positions From level 1 (positions)");

        String[] regressorLabels = new String[levelOneRegSize];
        String fieldLabel;

        int index = 0;
        ArrayList<String> position = new ArrayList<>();

        for (int p = 0; p < levelOneRegSize; p++) {

            if (levelOneBoxes.get(p).get(2).isSelected() && !disaggVarianceBoxes.get(p).get(2).isSelected()) {
                regressorLabels[index] = levelOneSelected.get(p);
                fieldLabel = levelOneSelected.get(p);
                System.out.println("From inside mixRegGUI | Level one Regressor Fields (WS): " + regressorLabels[index]);
                index++;
                int posIndex = 0;

                for (int q = 0; q < variableNamesCombo.length; q++) {

                    if (variableNamesCombo[q].equals(fieldLabel)) {
                        position.add(String.valueOf(q + 1));
                        System.out.println("Regressor position test: " + String.valueOf(q + 1));
                        System.out.println("From inside mixRegGUI | Position of this regressor: " + position.get(posIndex));
                        System.out.println("Position array: " + position);
                        posIndex++;

                    }
                }
            }

        }

        System.out.println("Position Aray Size here: " + String.valueOf(position.size()));

        String[] positionArray = new String[position.size()];

        for (int pos = 0; pos < positionArray.length; pos++) {
            positionArray[pos] = position.get(pos);
            System.out.println("positionArrayElements: " + positionArray[pos]);

        }

        System.out.println("Converted array size | position: " + String.valueOf(positionArray.length));
        System.out.println("Converted array elements | positions: " + Arrays.toString(positionArray));
        System.out.println("*********************************");

        return positionArray;

    }

    public String[] getWSFieldRegressorLabels_levelTwo() {
        System.out.println("*********************************");
        System.out.println("WS-positions From level 2 (positions)");

        String[] regressorLabels = new String[levelTwoRegSize];
        String fieldLabel;

        int index = 0;
        ArrayList<String> position = new ArrayList<>();

        for (int p = 0; p < levelTwoRegSize; p++) {

            if (levelTwoBoxes.get(p).get(2).isSelected()) {
                regressorLabels[index] = levelTwoSelected.get(p);
                fieldLabel = levelTwoSelected.get(p);
                System.out.println("From inside mixRegGUI | Level 2 Regressor Fields (WS): " + regressorLabels[index]);
                index++;
                int posIndex = 0;

                for (int q = 0; q < variableNamesCombo.length; q++) {

                    if (variableNamesCombo[q].equals(fieldLabel)) {
                        position.add(String.valueOf(q + 1));
                        System.out.println("Regressor position test: " + String.valueOf(q + 1));
                        System.out.println("From inside mixRegGUI | Position of this regressor: " + position.get(posIndex));
                        System.out.println("Position array: " + position);
                        posIndex++;

                    }
                }
            }

        }

        System.out.println("Position Aray Size here: " + String.valueOf(position.size()));

        String[] positionArray = new String[position.size()];

        for (int pos = 0; pos < positionArray.length; pos++) {
            positionArray[pos] = position.get(pos);
            System.out.println("positionArrayElements: " + positionArray[pos]);

        }

        System.out.println("Converted array size | position: " + String.valueOf(positionArray.length));
        System.out.println("Converted array elements | positions: " + Arrays.toString(positionArray));
        System.out.println("*********************************");

        return positionArray;

    }

    public String[] fieldModelWSArray() {
        System.out.println("*********************************");
        System.out.println("WS-positions From stage 1 (positions)");

        int arraySize = getWSFieldRegressorLabels_levelOne().length + getWSFieldRegressorLabels_levelTwo().length;
        String[] meanModel = new String[arraySize];

        for (int pos = 0; pos < arraySize; pos++) {
            if (pos >= 0 && pos < getWSFieldRegressorLabels_levelOne().length) {
                meanModel[pos] = getWSFieldRegressorLabels_levelOne()[pos];

            } else if (pos >= getWSFieldRegressorLabels_levelOne().length && pos < arraySize) {

                meanModel[pos] = getWSFieldRegressorLabels_levelTwo()[pos - getWSFieldRegressorLabels_levelOne().length];
            }

        }

        System.out.println("Inside mixRegGUI | WS-Model STAGE 1: " + Arrays.toString(meanModel));
        System.out.println("Inside mixRegGui | WS-Model STAGE 1 Size: " + meanModel.length);
        System.out.println("*********************************");

        return meanModel;
    }

    public String[] getWSDecompFieldRegressorLabels_levelOne() {

        System.out.println("*********************************");
        System.out.println("WS+Disagg-positions From level 1 (positions)");
        String[] regressorLabels = new String[levelOneRegSize];
        String fieldLabel;

        int index = 0;
        ArrayList<String> position = new ArrayList<>();

        for (int p = 0; p < levelOneRegSize; p++) {

            if (levelOneBoxes.get(p).get(2).isSelected() && disaggVarianceBoxes.get(p).get(2).isSelected()) {
                regressorLabels[index] = levelOneSelected.get(p);
                fieldLabel = levelOneSelected.get(p);
                System.out.println("From inside mixRegGUI | Level One Regressor Fields (WS): " + regressorLabels[index]);
                index++;
                int posIndex = 0;

                for (int q = 0; q < variableNamesCombo.length; q++) {

                    if (variableNamesCombo[q].equals(fieldLabel)) {
                        position.add(String.valueOf(q + 1));
                        System.out.println("Regressor position test: " + String.valueOf(q + 1));
                        System.out.println("From inside mixRegGUI | Position of this regressor: " + position.get(posIndex));
                        System.out.println("Position array: " + position);
                        posIndex++;

                    }
                }
            }

        }

        System.out.println("Position Aray Size here: " + String.valueOf(position.size()));

        String[] positionArray = new String[position.size()];

        for (int pos = 0; pos < positionArray.length; pos++) {
            positionArray[pos] = position.get(pos);
            System.out.println("positionArrayElements: " + positionArray[pos]);

        }

        System.out.println("Converted array size | position: " + String.valueOf(positionArray.length));
        System.out.println("Converted array elements | positions: " + Arrays.toString(positionArray));
        System.out.println("*********************************");

        return positionArray;

    }

    public String[] getModelMeanLabelsLevelOne() {
        System.out.println("*********************************");
        System.out.println("Means-Labels From level 1 (Labels)");

        String fieldLabel;

        ArrayList<String> regressorLabels = new ArrayList<String>();

        int index = 0;

        for (int p = 0; p < levelOneRegSize; p++) {

            if (levelOneBoxes.get(p).get(0).isSelected() && !disaggVarianceBoxes.get(p).get(0).isSelected()) {

                regressorLabels.add(levelOneSelected.get(p));
                fieldLabel = levelOneSelected.get(p);
                System.out.println("From inside mixRegGUI | LEVEL ONE Regressor Fields (Mean): " + regressorLabels.get(index));
                index++;
            }
        }

        String[] regLabels = new String[regressorLabels.size()];

        for (int pos = 0; pos < regLabels.length; pos++) {
            regLabels[pos] = regressorLabels.get(pos);
            System.out.println("Reg_LABEL: " + regLabels[pos]);

        }

        System.out.println("From inside mixRegGUI | LEVEL ONE MEAN REGRESSORS: " + Arrays.toString(regLabels));
        System.out.println("*********************************");
        return regLabels;
    }

    public String[] getModelMeanLabelsLevelTwo() {
        System.out.println("*********************************");
        System.out.println("Means-Labels From level 2 (Labels)");

        String fieldLabel;

        ArrayList<String> regressorLabels = new ArrayList<String>();
        int index = 0;

        for (int p = 0; p < levelTwoRegSize; p++) {

            if (levelTwoBoxes.get(p).get(0).isSelected()) {
                regressorLabels.add(levelTwoSelected.get(p));
                fieldLabel = levelTwoSelected.get(p);
                System.out.println("From inside mixRegGUI | LEVEL TWO Regressor Fields (Mean): " + regressorLabels.get(index));
                index++;

            }
        }

        String[] regLabels = new String[regressorLabels.size()];

        for (int pos = 0; pos < regLabels.length; pos++) {
            regLabels[pos] = regressorLabels.get(pos);
            System.out.println("Reg_LABEL: " + regLabels[pos]);

        }

        System.out.println("From inside mixRegGUI | LEVEL TWO MEAN REGRESSORS: " + Arrays.toString(regLabels));
        System.out.println("*********************************");
        return regLabels;
    }

    public String[] ModelMeansLabelsArray() {
        System.out.println("*********************************");
        System.out.println("Means-Labels From stage 1 (Labels)");

        int arraySize = getModelMeanLabelsLevelOne().length + getModelMeanLabelsLevelTwo().length;
        String[] meanModel = new String[arraySize];

        for (int pos = 0; pos < arraySize; pos++) {
            if (pos >= 0 && pos < getModelMeanLabelsLevelOne().length) {
                meanModel[pos] = getModelMeanLabelsLevelOne()[pos];

            } else if (pos >= getModelMeanLabelsLevelOne().length && pos < arraySize) {

                meanModel[pos] = getModelMeanLabelsLevelTwo()[pos - getModelMeanLabelsLevelOne().length];
            }

        }

        System.out.println("Inside mixRegGUI | Means-Model STAGE 1 labels: " + Arrays.toString(meanModel));
        System.out.println("*********************************");

        return meanModel;
    }

    public String[] getModelBSLabelsLevelOne() {
        System.out.println("*********************************");
        System.out.println("BS-Labels From level 1 (Labels)");

        String fieldLabel;

        ArrayList<String> regressorLabels = new ArrayList<String>();
        int index = 0;

        for (int p = 0; p < levelOneRegSize; p++) {

            if (levelOneBoxes.get(p).get(1).isSelected() && !disaggVarianceBoxes.get(p).get(1).isSelected()) {
                regressorLabels.add(levelOneSelected.get(p));
                fieldLabel = levelOneSelected.get(p);
                System.out.println("From inside mixRegGUI | LEVEL ONE Regressor Fields (BS): " + regressorLabels.get(index));
                index++;

            }
        }

        String[] regLabels = new String[regressorLabels.size()];

        for (int pos = 0; pos < regLabels.length; pos++) {
            regLabels[pos] = regressorLabels.get(pos);
            System.out.println("Reg_LABEL: " + regLabels[pos]);

        }

        System.out.println("From inside mixRegGUI | LEVEL ONE BS REGRESSORS: " + Arrays.toString(regLabels));
        System.out.println("*********************************");
        return regLabels;
    }

    public String[] getModelBSLabelsLevelTwo() {
        System.out.println("*********************************");
        System.out.println("BS-Labels From level 2 (Labels)");

        ArrayList<String> regressorLabels = new ArrayList<String>();
        int index = 0;

        for (int p = 0; p < levelTwoRegSize; p++) {

            if (levelTwoBoxes.get(p).get(1).isSelected()) {
                regressorLabels.add(levelTwoSelected.get(p));

                System.out.println("From inside mixRegGUI | LEVEL TWO Regressor Fields (BS): " + regressorLabels.get(index));
                index++;

            }
        }

        String[] regLabels = new String[regressorLabels.size()];

        for (int pos = 0; pos < regLabels.length; pos++) {
            regLabels[pos] = regressorLabels.get(pos);
            System.out.println("Reg_LABEL: " + regLabels[pos]);

        }

        System.out.println("From inside mixRegGUI | LEVEL TWO BS REGRESSORS: " + Arrays.toString(regLabels));
        System.out.println("*********************************");
        return regLabels;
    }

    public String[] ModelBSLabelsArray() {
        System.out.println("*********************************");
        System.out.println("BS-Labels From stage 1 (Labels)");

        int arraySize = getModelBSLabelsLevelOne().length + getModelBSLabelsLevelTwo().length;
        String[] meanModel = new String[arraySize];

        for (int pos = 0; pos < arraySize; pos++) {
            if (pos >= 0 && pos < getModelBSLabelsLevelOne().length) {
                meanModel[pos] = getModelBSLabelsLevelOne()[pos];

            } else if (pos >= getModelBSLabelsLevelOne().length && pos < arraySize) {

                meanModel[pos] = getModelBSLabelsLevelTwo()[pos - getModelBSLabelsLevelOne().length];
            }

        }

        System.out.println("Inside mixRegGUI | BS-Model STAGE 1 labels: " + Arrays.toString(meanModel));
        System.out.println("*********************************");

        return meanModel;
    }

    public String[] getModelWSLabelsLevelOne() {
        System.out.println("*********************************");
        System.out.println("WS-Labels From Level one (Labels)");

        ArrayList<String> regressorLabels = new ArrayList<String>();
        int index = 0;

        for (int p = 0; p < levelOneRegSize; p++) {

            if (levelOneBoxes.get(p).get(2).isSelected() && !disaggVarianceBoxes.get(p).get(2).isSelected()) {
                regressorLabels.add(levelOneSelected.get(p));
                System.out.println("From inside mixRegGUI | LEVEL ONE Regressor Fields (WS): " + regressorLabels.get(index));
                index++;

            }
        }

        String[] regLabels = new String[regressorLabels.size()];

        for (int pos = 0; pos < regLabels.length; pos++) {
            regLabels[pos] = regressorLabels.get(pos);
            System.out.println("Reg_LABEL: " + regLabels[pos]);

        }

        System.out.println("From inside mixRegGUI | LEVEL ONE WS REGRESSORS: " + Arrays.toString(regLabels));
        System.out.println("*********************************");
        return regLabels;
    }

    public String[] getModelWSLabelsLevelTwo() {
        System.out.println("*********************************");
        System.out.println("WS-Labels From Level two (Labels)");

        ArrayList<String> regressorLabels = new ArrayList<String>();
        int index = 0;

        ArrayList<String> position = new ArrayList<>();

        for (int p = 0; p < levelTwoRegSize; p++) {

            if (levelTwoBoxes.get(p).get(2).isSelected()) {
                regressorLabels.add(levelTwoSelected.get(p));
                System.out.println("From inside mixRegGUI | LEVEL TWO Regressor Fields (WS): " + regressorLabels.get(index));
                index++;

            }
        }

        String[] regLabels = new String[regressorLabels.size()];

        for (int pos = 0; pos < regLabels.length; pos++) {
            regLabels[pos] = regressorLabels.get(pos);
            System.out.println("Reg_LABEL: " + regLabels[pos]);

        }

        System.out.println("From inside mixRegGUI | LEVEL TWO WS REGRESSORS: " + Arrays.toString(regLabels));
        System.out.println("*********************************");
        return regLabels;
    }

    public String[] ModelWSLabelsArray() {
        System.out.println("*********************************");
        System.out.println("WS Labels From stage one (Labels)");

        int arraySize = getModelWSLabelsLevelOne().length + getModelWSLabelsLevelTwo().length;
        String[] meanModel = new String[arraySize];

        for (int pos = 0; pos < arraySize; pos++) {
            if (pos >= 0 && pos < getModelWSLabelsLevelOne().length) {
                meanModel[pos] = getModelWSLabelsLevelOne()[pos];

            } else if (pos >= getModelWSLabelsLevelOne().length && pos < arraySize) {

                meanModel[pos] = getModelWSLabelsLevelTwo()[pos - getModelWSLabelsLevelOne().length];
            }

        }

        System.out.println("Inside mixRegGUI | WS-Model STAGE 1 labels: " + Arrays.toString(meanModel));
        System.out.println("*********************************");

        return meanModel;
    }

    public String[] getDecompMeanLabelsLevelOne() {
        System.out.println("*********************************");
        System.out.println("Mean+Disagg. Labels From Level one (Labels)");

        ArrayList<String> regressorLabels = new ArrayList<String>();

        int index = 0;

        for (int p = 0; p < levelOneRegSize; p++) {

            if (levelOneBoxes.get(p).get(0).isSelected() && disaggVarianceBoxes.get(p).get(0).isSelected()) {

                regressorLabels.add(levelOneSelected.get(p));
                System.out.println("From inside mixRegGUI | LEVEL ONE Regressor Fields (Mean + Disagg): " + regressorLabels.get(index));
                index++;
            }
        }

        String[] regLabels = new String[regressorLabels.size()];

        for (int pos = 0; pos < regLabels.length; pos++) {
            regLabels[pos] = regressorLabels.get(pos);
            System.out.println("Reg_LABEL: " + regLabels[pos]);

        }

        System.out.println("From inside mixRegGUI | LEVEL ONE MEAN + DISAGG REGRESSORS: " + Arrays.toString(regLabels));
        System.out.println("*********************************");
        return regLabels;
    }

    public String[] getDecompBSLabelsLevelOne() {
        System.out.println("*********************************");
        System.out.println("BS+Disagg. Labels From Level one (Labels)");

        ArrayList<String> regressorLabels = new ArrayList<String>();

        int index = 0;

        for (int p = 0; p < levelOneRegSize; p++) {

            if (levelOneBoxes.get(p).get(1).isSelected() && disaggVarianceBoxes.get(p).get(1).isSelected()) {

                regressorLabels.add(levelOneSelected.get(p));
                System.out.println("From inside mixRegGUI | LEVEL ONE Regressor Fields (Mean + Disagg.): " + regressorLabels.get(index));
                index++;
            }
        }

        String[] regLabels = new String[regressorLabels.size()];

        for (int pos = 0; pos < regLabels.length; pos++) {
            regLabels[pos] = regressorLabels.get(pos);
            System.out.println("Reg_LABEL: " + regLabels[pos]);

        }

        System.out.println("From inside mixRegGUI | LEVEL ONE BS + DISAGG REGRESSORS: " + Arrays.toString(regLabels));
        System.out.println("*********************************");
        return regLabels;
    }

    public String[] getDecompWSLabelsLevelOne() {
        System.out.println("*********************************");
        System.out.println("WS + Disagg. Labels From level One (Labels)");

        ArrayList<String> regressorLabels = new ArrayList<String>();

        int index = 0;

        for (int p = 0; p < levelOneRegSize; p++) {

            if (levelOneBoxes.get(p).get(2).isSelected() && disaggVarianceBoxes.get(p).get(2).isSelected()) {

                regressorLabels.add(levelOneSelected.get(p));
                System.out.println("Stage-Two/mixRegGUI/Regressor-Fields-(Mean + Disagg.): " + regressorLabels.get(index));
                index++;
            }
        }

        String[] regLabels = new String[regressorLabels.size()];

        for (int pos = 0; pos < regLabels.length; pos++) {
            regLabels[pos] = regressorLabels.get(pos);
            System.out.println("Reg_LABEL: " + regLabels[pos]);

        }

        System.out.println("LEVEL-ONE/MIXREGGUI/WS + DISAGG-REGRESSORS= " + Arrays.toString(regLabels));
        System.out.println("*********************************");
        return regLabels;
    }

    public String getOutcomeLabel() {
        String outcome;

        outcome = StageOneVariableCombo.getSelectedItem().toString();
        System.out.println("Stage-Two/MixRegGUI/Outcome-variable-Label: " + outcome);

        return outcome;

    }

    public int getStageOneOutcome() {
        int stageOneOutcome = 1;

        if (getStageOneContinuousRadio()) {
            stageOneOutcome = 1;
        } else if (getStageOneDichotomousRadio() || getStageOneOrdinalRadio()) {
            stageOneOutcome = 2;
        }

        return stageOneOutcome;
    }

    public String getStageTwoOutcomePosition() {
        String position;
        int pos;

        String outcome = stageTwoOutcome.getSelectedItem().toString();
        pos = stageTwoOutcome.getSelectedIndex();

        position = String.valueOf(pos + 1);

        return position;

    }

    public String getStageTwoOutcomeLabel() {
        String position;
        int pos;

        String outcome = stageTwoOutcome.getSelectedItem().toString();
        pos = stageTwoOutcome.getSelectedIndex();

        position = String.valueOf(pos + 1);

        return outcome;

    }

    public String[] getFixedFieldRegressors_StageTwo() {
        System.out.println("*********************************");
        System.out.println("Fixed From Stage Two (positions)");

        String fieldLabel;

        String[] regressorLabels = new String[stageTwoLevelTwoRegSize];
        int index = 0;

        ArrayList<String> position = new ArrayList<>();

        for (int p = 0; p < stageTwoLevelTwoRegSize; p++) {

            if (stageTwoLevelTwoGridBoxes.get(p).get(0).isSelected()) {
                regressorLabels[index] = stageTwoLevelTwoSelected.get(p);
                fieldLabel = stageTwoLevelTwoSelected.get(p);
                System.out.println("Stage-Two/mixRegGUI/Regressor-Fields-(Fixed): " + regressorLabels[index]);
                index++;

                int posIndex = 0;

                for (int q = 0; q < variableNamesCombo.length; q++) {

                    if (variableNamesCombo[q].equals(fieldLabel)) {
                        //position[index] = String.valueOf(q + 1);
                        position.add(String.valueOf(q + 1));
                        System.out.println("Regressor position test: " + String.valueOf(q + 1));
                        System.out.println("From inside mixRegGUI | Position of this regressor: " + position.get(posIndex));
                        System.out.println("Position array: " + position);
                        posIndex++;

                    }

                }
            }

        }
        System.out.println("Position Aray Size here: " + String.valueOf(position.size()));

        String[] positionArray = new String[position.size()];

        for (int pos = 0; pos < positionArray.length; pos++) {
            positionArray[pos] = position.get(pos);
            System.out.println("positionArrayElements: " + positionArray[pos]);

        }

        System.out.println("Converted array size | position: " + String.valueOf(positionArray.length));
        System.out.println("Converted array elements | positions: " + Arrays.toString(positionArray));
        System.out.println("*********************************");

        return positionArray;

    }

    public String[] getRanLocFieldRegressors_StageTwo() {
        System.out.println("*********************************");
        System.out.println("Loc Ran  From Stage Two (position)");

        String fieldLabel;

        String[] regressorLabels = new String[stageTwoLevelTwoRegSize];
        int index = 0;

        ArrayList<String> position = new ArrayList<>();

        for (int p = 0; p < stageTwoLevelTwoRegSize; p++) {

            if (stageTwoLevelTwoGridBoxes.get(p).get(1).isSelected()) {
                regressorLabels[index] = stageTwoLevelTwoSelected.get(p);
                fieldLabel = stageTwoLevelTwoSelected.get(p);
                System.out.println("Stage-Two/mixRegGUI/Regressor-Fields-(LocRan): " + regressorLabels[index]);
                index++;

                int posIndex = 0;

                for (int q = 0; q < variableNamesCombo.length; q++) {

                    if (variableNamesCombo[q].equals(fieldLabel)) {
                        //position[index] = String.valueOf(q + 1);
                        position.add(String.valueOf(q + 1));
                        System.out.println("Regressor position test: " + String.valueOf(q + 1));
                        System.out.println("From inside mixRegGUI | Position of this regressor: " + position.get(posIndex));
                        System.out.println("Position array: " + position);
                        posIndex++;

                    }

                }
            }

        }
        System.out.println("Position Aray Size here: " + String.valueOf(position.size()));

        String[] positionArray = new String[position.size()];

        for (int pos = 0; pos < positionArray.length; pos++) {
            positionArray[pos] = position.get(pos);
            System.out.println("positionArrayElements: " + positionArray[pos]);

        }

        System.out.println("Converted array size | position: " + String.valueOf(positionArray.length));
        System.out.println("Converted array elements | positions: " + Arrays.toString(positionArray));
        System.out.println("*********************************");

        return positionArray;

    }

    public String[] getScaleFieldRegressors_StageTwo() {
        System.out.println("*********************************");
        System.out.println("Scale From Stage Two (Positions)");

        String fieldLabel;

        String[] regressorLabels = new String[stageTwoLevelTwoRegSize];
        int index = 0;

        ArrayList<String> position = new ArrayList<>();

        for (int p = 0; p < stageTwoLevelTwoRegSize; p++) {

            if (stageTwoLevelTwoGridBoxes.get(p).get(2).isSelected()) {
                regressorLabels[index] = stageTwoLevelTwoSelected.get(p);
                fieldLabel = stageTwoLevelTwoSelected.get(p);
                System.out.println("Stage-Two/mixRegGUI/Regressor Fields (Scale): " + regressorLabels[index]);
                index++;

                int posIndex = 0;

                for (int q = 0; q < variableNamesCombo.length; q++) {

                    if (variableNamesCombo[q].equals(fieldLabel)) {
                        //position[index] = String.valueOf(q + 1);
                        position.add(String.valueOf(q + 1));
                        System.out.println("Regressor position test: " + String.valueOf(q + 1));
                        System.out.println("Stage-two/mixRegGUI/Position of this regressor: " + position.get(posIndex));
                        System.out.println("Position array: " + position);
                        posIndex++;

                    }

                }
            }

        }
        System.out.println("Position Aray Size here: " + String.valueOf(position.size()));

        String[] positionArray = new String[position.size()];

        for (int pos = 0; pos < positionArray.length; pos++) {
            positionArray[pos] = position.get(pos);
            System.out.println("positionArrayElements: " + positionArray[pos]);

        }

        System.out.println("Converted array size | position: " + String.valueOf(positionArray.length));
        System.out.println("Converted array elements | positions: " + Arrays.toString(positionArray));
        System.out.println("*********************************");

        return positionArray;

    }

    public String[] getModelFixedLabelsStageTwo() {
        System.out.println("*********************************");
        System.out.println("Fixed Labels From Stage Two (labels)");

        String fieldLabel;

        ArrayList<String> regressorLabels = new ArrayList<String>();
        int index = 0;

        for (int p = 0; p < stageTwoLevelTwoRegSize; p++) {

            if (stageTwoLevelTwoGridBoxes.get(p).get(0).isSelected()) {
                regressorLabels.add(stageTwoLevelTwoSelected.get(p));
                fieldLabel = stageTwoLevelTwoSelected.get(p);
                System.out.println("Stage-Two/mixRegGUI/Regressor-Fields-(FIXED): " + regressorLabels.get(index));
                index++;

            }
        }

        String[] regLabels = new String[regressorLabels.size()];

        for (int pos = 0; pos < regLabels.length; pos++) {
            regLabels[pos] = regressorLabels.get(pos);
            System.out.println("Reg_LABEL: " + regLabels[pos]);

        }

        System.out.println("STAGE-TWO/MIXREGGUI/MEAN-REGRESSORS= " + Arrays.toString(regLabels));
        System.out.println("*********************************");
        return regLabels;
    }

    public String[] getModelLocRanLabelsStageTwo() {
        System.out.println("*********************************");
        System.out.println("Loc Ran Labels From Stage Two (Labels)");

        String fieldLabel;

        ArrayList<String> regressorLabels = new ArrayList<String>();
        int index = 0;

        for (int p = 0; p < stageTwoLevelTwoRegSize; p++) {

            if (stageTwoLevelTwoGridBoxes.get(p).get(1).isSelected()) {
                regressorLabels.add(stageTwoLevelTwoSelected.get(p));
                fieldLabel = stageTwoLevelTwoSelected.get(p);
                System.out.println("STAGE-TWO/MIXREGGUI/Regressor-Fields-(LOC RAN): " + regressorLabels.get(index));
                index++;

            }
        }

        String[] regLabels = new String[regressorLabels.size()];

        for (int pos = 0; pos < regLabels.length; pos++) {
            regLabels[pos] = regressorLabels.get(pos);
            System.out.println("Reg_LABEL: " + regLabels[pos]);

        }

        System.out.println("STAGE-TWO/MXREGGUI/LOC-RAN-REGRESSORS= " + Arrays.toString(regLabels));
        System.out.println("*********************************");
        return regLabels;
    }

    public String[] getModelScaleLabelsStageTwo() {
        System.out.println("*********************************");
        System.out.println("Scale Labels From Stage Two (Labels)");

        String fieldLabel;

        ArrayList<String> regressorLabels = new ArrayList<String>();
        int index = 0;

        for (int p = 0; p < stageTwoLevelTwoRegSize; p++) {

            if (stageTwoLevelTwoGridBoxes.get(p).get(2).isSelected()) {
                regressorLabels.add(stageTwoLevelTwoSelected.get(p));
                fieldLabel = stageTwoLevelTwoSelected.get(p);
                System.out.println("Stage-Two/mixRegGUI/Regressor-Fields-(SCALE): " + regressorLabels.get(index));
                index++;
            }
        }

        String[] regLabels = new String[regressorLabels.size()];

        for (int pos = 0; pos < regLabels.length; pos++) {
            regLabels[pos] = regressorLabels.get(pos);
            System.out.println("Reg_LABEL: " + regLabels[pos]);

        }

        System.out.println("STAGE-TWO/MIXREGGUI/SCALE-REGRESSORS= " + Arrays.toString(regLabels));
        System.out.println("*********************************");
        return regLabels;
    }

    public String[] getInteractionFieldRegressors_StageTwo() {
        System.out.println("*********************************");
        System.out.println("Interactions From Stage Two (Positions)");

        String fieldLabel;

        String[] regressorLabels = new String[stageTwoLevelTwoRegSize];
        int index = 0;

        ArrayList<String> position = new ArrayList<>();

        for (int p = 0; p < stageTwoLevelTwoRegSize; p++) {

            if (stageTwoLevelTwoGridBoxes.get(p).get(3).isSelected()) {
                regressorLabels[index] = stageTwoLevelTwoSelected.get(p);
                fieldLabel = stageTwoLevelTwoSelected.get(p);
                System.out.println("Stage-two/mixRegGUI/Regressor-Fields-(INTERACTION): " + regressorLabels[index]);
                index++;

                int posIndex = 0;

                for (int q = 0; q < variableNamesCombo.length; q++) {

                    if (variableNamesCombo[q].equals(fieldLabel)) {
                        //position[index] = String.valueOf(q + 1);
                        position.add(String.valueOf(q + 1));
                        System.out.println("Stage-Two/Regressor-position-test: " + String.valueOf(q + 1));
                        System.out.println("Stage-Two/mixRegGUI | Position of this regressor: " + position.get(posIndex));
                        System.out.println("Stage-Two/Position array: " + position);
                        posIndex++;

                    }

                }
            }

        }
        System.out.println("Stage-Two/Position Aray Size here: " + String.valueOf(position.size()));

        String[] positionArray = new String[position.size()];

        for (int pos = 0; pos < positionArray.length; pos++) {
            positionArray[pos] = position.get(pos);
            System.out.println("Stage-Two/positionArrayElements: " + positionArray[pos]);

        }

        System.out.println("Stage-Two/Converted array size | position: " + String.valueOf(positionArray.length));
        System.out.println("Stage-Two/Converted array elements | positions: " + Arrays.toString(positionArray));
        System.out.println("*********************************");

        return positionArray;

    }

    public String[] getModelInteractionLabelsStageTwo() {
        System.out.println("*********************************");
        System.out.println("Interaction Labels From Stage Two (Labels)");

        String fieldLabel;

        ArrayList<String> regressorLabels = new ArrayList<String>();
        int index = 0;

        for (int p = 0; p < stageTwoLevelTwoRegSize; p++) {

            if (stageTwoLevelTwoGridBoxes.get(p).get(3).isSelected()) {
                regressorLabels.add(stageTwoLevelTwoSelected.get(p));
                fieldLabel = stageTwoLevelTwoSelected.get(p);
                System.out.println("STAGE-TWO/MIXREGGUI/Regressor-Fields-(INTERACTIONS)= " + regressorLabels.get(index));
                index++;
            }
        }

        String[] regLabels = new String[regressorLabels.size()];

        for (int pos = 0; pos < regLabels.length; pos++) {
            regLabels[pos] = regressorLabels.get(pos);
            System.out.println("Reg_LABEL: " + regLabels[pos]);

        }

        System.out.println("STAGE-TWO/MIXREGGUI/INTERACTIONS-REGRESSORS= " + Arrays.toString(regLabels));
        System.out.println("*********************************");
        return regLabels;
    }

    public static void produceStageTwoOutput(File filename) throws FileNotFoundException, IOException {

        String outputFileName = FilenameUtils.removeExtension(getDataFileName()) + "_output_1" + ".out";
        //read file here
        FileReader reader = new FileReader(outputFileName);

    }

    public void produceStageOneOutput() throws FileNotFoundException {

    }

    public void saveStageTwoOutput() throws IOException {

        FileFilter filter = new FileNameExtensionFilter("TEXT FILE", "txt");

        JFileChooser saver = new JFileChooser("./");
        saver.setFileFilter(filter);
        int returnVal = saver.showSaveDialog(this);
        File file = saver.getSelectedFile();
        BufferedWriter writer = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                writer = new BufferedWriter(new FileWriter(file));
                writer.write(stageTwoOutput.getText());
                writer.close();
                JOptionPane.showMessageDialog(this, "Stage 2 output was Saved Successfully!",
                        "Success!", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                SystemLogger.LOGGER.log(Level.SEVERE, e.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(this, "Stage 2 output could not be Saved!",
                        "Error!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void saveStageOneOutput() {
        FileFilter filter = new FileNameExtensionFilter("TEXT FILE", "txt");

        JFileChooser saver = new JFileChooser("./");
        saver.setFileFilter(filter);
        int returnVal = saver.showSaveDialog(this);
        File file = saver.getSelectedFile();
        BufferedWriter writer = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                writer = new BufferedWriter(new FileWriter(file));
                writer.write(stageOneOutput.getText());
                writer.close();
                JOptionPane.showMessageDialog(this, "Stage 1 output was Saved Successfully!",
                        "Success!", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                SystemLogger.LOGGER.log(Level.SEVERE, e.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(this, "Stage 1 output could not be Saved!",
                        "Error!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // TODO: Where is this used?
//    public void runMixedModels() {
//
//        String absoluteJavaPath = System.getProperty("user.dir");
//        String defFileName = executableModel(selectedModel);
//        try {
//            try {
//                copyExecutable(defFilePath, selectedModel); //get the def file path after it is saved
//                Process p = Runtime.getRuntime().exec("cmd /c dir && cd " + defFilePath + " && dir && "
//                        + defFileName); // does it save it in the same directory //@Eldin: This is where it is copying it twice.
//                //@Eldin: This is where we may want to keep the terminal open in the background.
//
//                p.waitFor();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//                String line = reader.readLine();
//                while (line != null) {
//                    System.out.println(line);
//                    line = reader.readLine();
//                }
//            } catch (FileNotFoundException fnfe1) {
//                SystemLogger.LOGGER.log(Level.SEVERE, fnfe1.toString() + "{0}", SystemLogger.getLineNum());
//                System.out.println("File not found Exception");
//            } catch (IOException e1) {
//                SystemLogger.LOGGER.log(Level.SEVERE, e1.toString() + "{0}", SystemLogger.getLineNum());
//                System.out.println("IO Exception");
//            }
//
//            try {
//                Process p = Runtime.getRuntime().exec("cmd /c dir && cd " + defFilePath + " && del /f " + defFileName);
//                p.waitFor();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//                String line = reader.readLine();
//                while (line != null) {
//                    System.out.println(line);
//                    line = reader.readLine();
//                }
//            } catch (FileNotFoundException fnfe1) {
//                SystemLogger.LOGGER.log(Level.SEVERE, fnfe1.toString() + "{0}", SystemLogger.getLineNum());
//                System.out.println("File not found Exception 2");
//            } catch (IOException e1) {
//                SystemLogger.LOGGER.log(Level.SEVERE, e1.toString() + "{0}", SystemLogger.getLineNum());
//                System.out.println("IO Exception 2 ");
//            }
//
//            JOptionPane.showMessageDialog(null, defFilePath);
//
//        } catch (Exception ex) {
//            SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Failed");
//        }
//
//    }
    // TODO: Old, update
//    private String executableModel(int modelSelection) {
//        switch (modelSelection) {
//            case DefinitionHelper.MIXREGLS_MIXREG_KEY:
//                return "mixregls_mixreg.exe";
//            case DefinitionHelper.MIXREGLS_MIXOR_KEY:
//                return "mixregls_mixor.exe";
//            case DefinitionHelper.MIXREGMLS_MIXREG_KEY:
//                return "mixregmls_mixreg.exe";
//            case DefinitionHelper.MIXREGMLS_MIXOR_KEY:
//                return "mixregmls_mixor.exe";
//
//            default:
//                return "mixregls_mixreg.exe";
//        }
//    }
    // TODO: What is this? Not compatible with macOS
//    private void copyExecutable(String absoluteDirectoryPath, int modelSelection) throws FileNotFoundException, IOException {
//        String modelPath;
//        String executableName = executableModel(modelSelection);
//        switch (modelSelection) {
//            case DefinitionHelper.MIXREGLS_MIXREG_KEY:
//                modelPath = "resources/Windows/mixregls_mixreg.exe";
//                break;
//            case DefinitionHelper.MIXREGLS_MIXOR_KEY:
//                modelPath = "resources/Windows/mixregls_mixor.exe";
//                break;
//            case DefinitionHelper.MIXREGMLS_MIXREG_KEY:
//                modelPath = "resources/Windows/mixregmls_mixreg.exe";
//                break;
//            case DefinitionHelper.MIXREGMLS_MIXOR_KEY:
//                modelPath = "resources/Windows/mixregmls_mixor.exe";
//                break;
//            default:
//                modelPath = "resources/Windows/mixregls_mixreg.exe";
//                break;
//        }
//        InputStream stream = getClass().getClassLoader().getResourceAsStream(modelPath);
//
//        OutputStream outputStream
//                = new FileOutputStream(new File(absoluteDirectoryPath + executableName));
//
//        int read;
//        byte[] bytes = new byte[4096];
//
//        while ((read = stream.read(bytes)) > 0) {
//            outputStream.write(bytes, 0, read);
//        }
//        stream.close();
//        outputStream.close();
//    }
    public void runMixRegLS_mixor() {

    }

    public int getStagetwoOutcomeCats() {
        ArrayList<String> ColumnsCustom = new ArrayList<>();
        ArrayList<String> UniqueList = new ArrayList<>();

        String dataFileName = getDataFileName();
        File file = new File(dataFileName);
        //first get the column
        BufferedReader br = null;
        String line = "";
        String commaSplitter = ",";
        //
        try {
            br = new BufferedReader(new FileReader(dataFileName));
            line = br.readLine(); //consumes the first row
            while ((line = br.readLine()) != null) {
                String[] Columns = line.split(commaSplitter);
                int index = stageTwoOutcome.getSelectedIndex();
                ColumnsCustom.add(Columns[index]);
            }
            System.out.println("COLUMN:");
            for (int k = 0; k < ColumnsCustom.size(); k++) {

                System.out.println(ColumnsCustom.get(k));
            }
            //count the unique ones
            for (int x = 0; x < ColumnsCustom.size(); x++) {
                if (UniqueList.contains(ColumnsCustom.get(x))) {
                    //do nothing
                } else if (ColumnsCustom.get(x).equals(defFile.getAdvancedMissingValueCode()) && !ColumnsCustom.get(x).equals("0")) { //compare if the category is a missing value, then don't consider it as a category
                    //do nothing

                } else {
                    UniqueList.add(ColumnsCustom.get(x));
                }
            }
            System.out.println("Number of unique categories: " + String.valueOf(UniqueList.size()));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Logger.getLogger(getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, e.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        } catch (IOException e) {
            SystemLogger.LOGGER.log(Level.SEVERE, e.toString() + "{0}", SystemLogger.getLineNum());
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    SystemLogger.LOGGER.log(Level.SEVERE, e.toString() + "{0}", SystemLogger.getLineNum());
                    e.printStackTrace();
                }
            }
        }
        return UniqueList.size();
    }

    public String[] getStageTwoOutcomeValues() {

        System.out.println("Inside getStageTwoOutcomeValues");

        ArrayList<String> ColumnsCustom = new ArrayList<>();
        ArrayList<String> UniqueList = new ArrayList<>();
        ArrayList<Integer> UniqueIntegers = new ArrayList<>();

        String dataFileName = getDataFileName();
        File file = new File(dataFileName);
        //first get the column
        BufferedReader br = null;
        String line = "";
        String commaSplitter = ",";
        //
        try {
            br = new BufferedReader(new FileReader(dataFileName));
            line = br.readLine(); //consumes the first row
            while ((line = br.readLine()) != null) {
                String[] Columns = line.split(commaSplitter);
                int index = stageTwoOutcome.getSelectedIndex();
                ColumnsCustom.add(Columns[index]);
            }
            //System.out.println("COLUMN:");
            for (int k = 0; k < ColumnsCustom.size(); k++) {
                System.out.println(ColumnsCustom.get(k));
            }
            //count the unique ones
            for (int x = 0; x < ColumnsCustom.size(); x++) {
                if (UniqueList.contains(ColumnsCustom.get(x))) {
                    //do nothing
                } else if (ColumnsCustom.get(x).equals(defFile.getAdvancedMissingValueCode()) && !ColumnsCustom.get(x).equals("0")) { //compare if the category is a missing value, then don't consider it as a category
                    //do nothing

                } else {
                    UniqueList.add(ColumnsCustom.get(x));
                }
            }

            for (int x = 0; x < UniqueList.size(); x++) {

                UniqueIntegers.add(Integer.valueOf(UniqueList.get(x)));

            }

            Collections.sort(UniqueIntegers);

            System.out.println("Number of unique categories: " + String.valueOf(UniqueList.size()));

            outCategoryDisplay.setText(UniqueList.size() + " Categories:\n");
//            for (int index = 0; index < UniqueList.size(); index++) {
//                //numberOfCategories.setT
//                //numberOfCategories.setText(numberOfCategories.getText() +"<html><br></html>" + String.valueOf(index + 1) + ":" + UniqueList.get(index) + "<html><br></html>");
//                outCategoryDisplay.setText(outCategoryDisplay.getText() + String.valueOf(index + 1) + ") " + UniqueList.get(index) + "\n");
//
//            }

            for (int index = 0; index < UniqueIntegers.size(); index++) {
                //numberOfCategories.setT
                //numberOfCategories.setText(numberOfCategories.getText() +"<html><br></html>" + String.valueOf(index + 1) + ":" + UniqueList.get(index) + "<html><br></html>");
                outCategoryDisplay.setText(outCategoryDisplay.getText() + String.valueOf(index + 1) + ") " + String.valueOf(UniqueIntegers.get(index)) + "\n");

            }

            // System.out.println("Number of unique categories: " + String.valueOf(UniqueList.size()));
        } catch (FileNotFoundException e) {
            SystemLogger.LOGGER.log(Level.SEVERE, e.toString() + "{0}", SystemLogger.getLineNum());
            e.printStackTrace();
            Logger.getLogger(getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, e.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        } catch (IOException e) {
            SystemLogger.LOGGER.log(Level.SEVERE, e.toString() + "{0}", SystemLogger.getLineNum());
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    SystemLogger.LOGGER.log(Level.SEVERE, e.toString() + "{0}", SystemLogger.getLineNum());
                    e.printStackTrace();
                }
            }
        }

        String[] outcomeCats = new String[UniqueIntegers.size()];

        for (int pos = 0; pos < outcomeCats.length; pos++) {
            outcomeCats[pos] = String.valueOf(UniqueIntegers.get(pos));
            System.out.println("STAGE 2 OUTCOME CATEGORIES: " + outcomeCats[pos]);
        }
        System.out.println("Before return getStageTwoOutcomeValues");
        return outcomeCats;
    }

    public void importDataSet() {
        //JFileChooser fileChooser = new JFileChooser();
        //fileChooser.showOpenDialog(null);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Data files", "csv");
        fileChooser.setFileFilter(filter);
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

            try {
                getDataFromCSV();
                printFileName();
                System.out.println("NEW MODEL DATA READ");
                if (validDataset) {
                    stageOneTabs.insertTab("View Data", null, jPanel6, null, 1);
                }
            } catch (IOException ex) {
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

        } else {
            System.out.println("File access cancelled by user.");
            try {
                System.out.println(file.getAbsolutePath());
            } catch (NullPointerException ex) {
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
            }
        }
    }

    private void updateGuiView_trigger_NewModelSubmit() {
        System.out.println("Model submitted" + " called");

        if (validateFields() == true) {
            System.out.print("condition is true");
            updateGuiView_trigger_NewModelSubmit_TabChange();
            updateStage2ConfigButton.setVisible(true);
            guiStatesSaveButtonModalConfig.setVisible(true);
            guiStatesSaveButtonStageOne.setVisible(true);
            guiStatesSaveButtonStageTwo.setVisible(true);

            if (isOutcomeNone() == true) {
                startStageTwo.setText("Run Stage 1");
            } else if (isOutcomeNone() == false) {
                startStageTwo.setText("Configure Stage 2");
            }

            if (oneRLERadio.isSelected() == true) {
                RLE = MixLibrary.STAGE_ONE_RLE_LOCATION;
            } else if (moreThanOneRLERadio.isSelected() == true) {
                RLE = MixLibrary.STAGE_ONE_RLE_SLOPE;
            } else {
                isNewModalConfigSubmitted = false;
            }

//            defFile = new DefinitionHelper(RLE, !isOutcomeContinous());
//            modelBuilder = new ModelBuilder(defFile);
            // Include stage 2 or not
            if (getIncludeStageTwoYes() == true) {
                defFile = new MixLibrary(getStageOneOutcome(), RLE, getRandomScaleSelection(), getStageTwoModelType(), getStageTwoOutcomeType());
            }
            if (getIncludeStageTwoNo() == true) {
                defFile = new MixLibrary(getStageOneOutcome(), RLE, getRandomScaleSelection());
            }

            System.out.println("RLE: " + String.valueOf(RLE));

//            defFile.modelSelector(RLE, isOutcomeContinous());
            if (missingValuePresent.isSelected()) {

                System.out.println("NEW MODEL | MISSING VALUE = " + newModelMissingValueCode.getText());

                try {
                    System.out.println("NEW MODEL | MISSING VALUE = " + newModelMissingValueCode.getText());
                    defFile.setAdvancedMissingValueCode(String.valueOf(newModelMissingValueCode.getText()));

                    System.out.println("From defHelper | Missing Value: " + defFile.getAdvancedMissingValueCode());
                } catch (Exception ex) {
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE);
                }

            } else if (missingValueAbsent.isSelected()) {
                try {
                    defFile.setAdvancedMissingValueCode(String.valueOf(missingValue));
                    System.out.println("From defHelper | Missing Value: " + defFile.getAdvancedMissingValueCode());
                } catch (Exception ex) {
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE);
                }
                //do nothing
            }

            try {
                //convert csv to .dat file ...
                //defFile.
                if (sessionFolderName == null) {
                    defFile.csvToDatConverter(file);
                    sessionFolderName = defFile.getUtcDirPath();

                    // create logger after session folder created
                    SystemLogger.logPath = FilenameUtils.getFullPath(dataFileNameRef) + sessionFolderName;
                    logger = new SystemLogger();

                } else {
                    defFile.setUtcDirPath(sessionFolderName);

                    SystemLogger.logPath = FilenameUtils.getFullPath(dataFileNameRef) + sessionFolderName;
                    logger = new SystemLogger();
                }

            } catch (IOException ex) {
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE);
            }

//            System.out.println("MODEL SELECTOR: " + String.valueOf(defFile.getSelectedModel()));
            if (filePath.getText().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Please upload a .csv file to start your analysis", "Caution!", JOptionPane.INFORMATION_MESSAGE);
            } else {

                try {

                    // Read file contents
                    Scanner inputStream = new Scanner(file);

                    // Read variable names from row 1
                    String variableNames = inputStream.next();
                    variableArray = variableNames.split(",");
                    System.out.println("Variables are: " + Arrays.toString(variableArray));
                    // save all variables in an array
                    String[] varTemp = getVariableNames();
                    defFile.setSharedDataFilename(extractDatFilePath());
                    defFile.setAdvancedVariableCount(String.valueOf(variableArray.length));
                    System.out.println("From defHelper | Variable count: " + defFile.getAdvancedVariableCount());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                } catch (Exception ex) {
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                }

                // Read random location effects from new Model
                //RLE = (Integer) randomLocationEffects.getValue();
                NoneVar = isOutcomeNone();
                outComeBoolean = isOutcomeContinous();
                System.out.println("NoneVar: " + String.valueOf(NoneVar));
                System.out.println(String.valueOf(isOutcomeContinous()));
                System.out.println("IsOutcomeNone: " + String.valueOf(isOutcomeNone()));
                // set Values in def helper
                defFile.setSharedModelTitle(getTitle());
                System.out.println("From defHelper | Title: " + defFile.getSharedModelTitle());
                //defFile.setModelSubtitle(getSubTitle());
                System.out.println("From defHelper | Subtitle: " + defFile.getSharedModelSubtitle());

                //Check if the randome scale is checked or not
                if (randomScaleSelectionYes.isSelected()) {
                    isRandomScale = true;
                } else if (randomScaleSelectionNo.isSelected()) {
                    isRandomScale = false;
                } else {
                    // randomScaleCheckBox
                }

                defFile.setSharedModelSubtitle("Created with MixWILD GUI");
                System.out.println("From defHelper | Subtitle: " + defFile.getSharedModelSubtitle());

                //set advanced options defaults and assign values to defition library
                try {
                    defFile.setAdvancedMeanIntercept(String.valueOf(0));
                    System.out.println("From defHelper | Mean SubModel Checked?: " + defFile.getAdvancedMeanIntercept());
                    //tryCount = 1;

                } catch (Exception ex) {
                    //catchCount = 1;
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    Logger.getLogger(advancedOptions.class
                            .getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }
                try {
                    defFile.setAdvancedRandomIntercept(String.valueOf(0));
//                    defFile.setModelBetweenInt(String.valueOf(0));
                    System.out.println("From defHelper | BS SubModel Checked?: " + defFile.getAdvancedRandomIntercept());
//                    System.out.println("From defHelper | BS SubModel Checked?: " + defFile.getModelBetweenInt());

                } catch (Exception ex) {
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    Logger.getLogger(advancedOptions.class
                            .getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }
                try {
                    defFile.setAdvancedScaleIntercept(String.valueOf(0));
//                    defFile.setModelWithinInt(String.valueOf(0));
                    System.out.println("From defHelper | WS SubModel Checked?: " + defFile.getAdvancedScaleIntercept());
//                    System.out.println("From defHelper | WS SubModel Checked?: " + defFile.getModelWithinInt());
                    //tryCount = 1;

                } catch (Exception ex) {
                    //catchCount = 1;
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    Logger.getLogger(advancedOptions.class
                            .getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }
                try {
                    defFile.setAdvancedAdaptiveQuad(String.valueOf(1));
                    System.out.println("From defHelper | Adaptive Quadriture Checked?: " + defFile.getAdvancedAdaptiveQuad());
                    //tryCount = 1;

                } catch (Exception ex) {
                    //catchCount = 1;
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    Logger.getLogger(advancedOptions.class
                            .getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }
                try {
                    defFile.setAdvancedConvergenceCriteria(String.valueOf(0.00001));
                    System.out.println("From defHelper | Convergence: " + defFile.getAdvancedConvergenceCriteria());
                    //tryCount = 1;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                    Logger.getLogger(advancedOptions.class
                            .getName()).log(Level.SEVERE, null, ex);
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    //catchCount = 1;
                }
                try {
                    defFile.setAdvancedQuadPoints(String.valueOf(11));
                    System.out.println("From defHelper | Quadriture Points: " + defFile.getAdvancedQuadPoints());
                    //tryCount = 1;

                } catch (Exception ex) {
                    //catchCount = 1;
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    Logger.getLogger(advancedOptions.class
                            .getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }
                try {
                    defFile.setAdvancedMaxIterations(String.valueOf(200));
                    System.out.println("From defHelper | Maximum Iteraions: " + defFile.getAdvancedMaxIterations());
                    //tryCount = 1;

                } catch (Exception ex) {
                    //catchCount = 1;
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    Logger.getLogger(advancedOptions.class
                            .getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }
                try {
                    defFile.setAdvancedInitialRidge(String.valueOf(0.15));
                    System.out.println("From defHelper | Ridge: " + defFile.getAdvancedInitialRidge());
                    //tryCount = 1;

                } catch (Exception ex) {
                    //catchCount = 1;
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    Logger.getLogger(advancedOptions.class
                            .getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }
                try {
                    defFile.setAdvancedRandomScaleCutoff("0");
                    System.out.println("CUT OFF: " + defFile.getAdvancedRandomScaleCutoff());
                } catch (Exception ex) {
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }
                try {
                    defFile.setAdvancedDiscardNoVariance("0");
                    System.out.println("DISCARD SUBJECTS: " + defFile.getAdvancedDiscardNoVariance());

                } catch (Exception ex) {
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    Logger.getLogger(advancedOptions.class
                            .getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }
                try {
                    defFile.setAdvancedCenterScaleVariables(String.valueOf(0));
                    System.out.println("From defHelper | Scale Regressor: " + defFile.getAdvancedCenterScaleVariables());

                } catch (Exception ex) {
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    Logger.getLogger(advancedOptions.class
                            .getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }
                try {
                    defFile.setAdvancedResampleCount("500");
                    System.out.println("From defHelper | Resample count: " + defFile.getAdvancedResampleCount());

                } catch (Exception ex) {
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    Logger.getLogger(advancedOptions.class
                            .getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }

                try {
                    defFile.setAdvancedResamplingSeed(seedTextBox.getText());
                    System.out.println("From defHelper | SEED: " + defFile.getAdvancedResamplingSeed());
                } catch (Exception ex) {
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }

                defFile.setSharedOutputPrefix(extractDatFileName() + "_Output");
                System.out.println("From defHelper | Output file name: " + defFile.getSharedOutputPrefix());

                if (randomScaleSelectionYes.isSelected()) {
                    try {
                        defFile.setAdvancedUseRandomScale("0");
                        System.out.println("IS RANDOM SCALE INCLUDED: " + defFile.getAdvancedUseRandomScale());
                    } catch (Exception ex) {
                        SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                        Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                    }

                } else {

                    try {
                        defFile.setAdvancedUseRandomScale("1");
                        System.out.println("IS RANDOM SCALE INCLUDED: " + defFile.getAdvancedUseRandomScale());
                    } catch (Exception ex) {
                        SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                        Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                    }

                }

                if (isOutcomeNone()) {
                    try {
                        // defFile.setAdvancedUseStageTwo("1");
                        System.out.println("DROP SECOND STAGE?: " + defFile.getAdvancedUseStageTwo());
                    } catch (Exception ex) {
                        SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                        Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                    }

                } else {

                    try {
                        // defFile.setAdvancedUseStageTwo("0");
                        System.out.println("DROP SECOND STAGE?: " + defFile.getAdvancedUseStageTwo());
                    } catch (Exception ex) {
                        SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                        Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                    }

                }

                //set conditions here:
                if (newModelMissingValueCode.getText().equals("0") || newModelMissingValueCode.getText().equals("00") || newModelMissingValueCode.getText().equals("000")) {
                    //show message alert here:
                    JOptionPane.showMessageDialog(null, "Invalid missing value code, 0 implies there are no missing values. Please use some other value. E.g., -999", "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                } else {
                    // TODO: fix this stuff xyz

                    setVisible(true);
//                    randomLocationEffectsLabel.setText("Random location effects: " + randomLocationEffects());
                    Color darkGreen = new Color(0, 100, 0);
                    randomLocationEffectsLabel.setText(randomLocationEffects());
                    randomLocationEffectsLabel.setForeground(darkGreen);
                    stageTwoOutcomePrintLabel.setText(stageOneOutcomeTypeString());
                    stageTwoOutcomePrintLabel.setForeground(darkGreen);

                    if (!isUpdateStage2ConfigClicked) {
                        isSubmitClicked();
                        //Update ID, stage one and stage two variable comboboxes
                        updateMixRegGUI();
                        updateComboBoxes();

                    }
                    stageOneTabs.setSelectedIndex(1);
                    newModelSubmit.setEnabled(false);
//                    newModelSubmit.setVisible(false);
                    fileBrowseButton.setEnabled(false);
                    fileBrowseButton.setVisible(false);
                    filePath.setEnabled(false);
                    titleField.setEnabled(false);
                    oneRLERadio.setEnabled(false);
                    moreThanOneRLERadio.setEnabled(false);
                    //randomScaleCheckBox.setEnabled(false);
                    randomScaleSelectionYes.setEnabled(false);
                    randomScaleSelectionNo.setEnabled(false);
                    stageTwoContinuousRadio.setEnabled(false);
                    stageTwoDichotomousRadio.setEnabled(false);
                    includeStageTwoNo.setEnabled(false);
                    includeStageTwoYes.setEnabled(false);
                    //noneRadio.setEnabled(false);
                    missingValuePresent.setEnabled(false);
                    missingValueAbsent.setEnabled(false);
                    newModelMissingValueCode.setEnabled(false);
                    seedTextBox.setEnabled(false);
                    seedHelpButton.setVisible(false);
                    stageOneContinuousRadio.setEnabled(false);
                    stageTwoSingleLevel.setEnabled(false);
                }

            }
        } else {

            System.out.println("VALIDATION OF FIELDS: " + String.valueOf(false));

        } //To change body of generated methods, choose Tools | Templates.
    }

    private void update_trigger_StartStageTwo() {

        int tryCount = 0;
        int catchCount = 0;

        // *********************************************************************
        // Test printing statements counting mean regressors
        System.out.println("Total selected mean regressors in level one: " + String.valueOf(countLevelOneBeta()));
        System.out.println("Total selected BS Variances in level one: " + String.valueOf(countLevelOneAlpha()));
        System.out.println("Total selected WS Variances in level one: " + String.valueOf(countLevelOneTau()));
        System.out.println("Total selected disagg. variance in level one: " + String.valueOf(countLevelOneDicompMean()));

        System.out.println("Total selected mean regressors in level two: " + String.valueOf(countLevelTwoBeta()));
        System.out.println("Total selected BS variances in level two: " + String.valueOf(countLevelTwoAlpha()));
        System.out.println("Total selected WS variances in level two: " + String.valueOf(countLevelTwoTau()));

        // Reads selected ID variable and outcome variable from the first two comboboxes
        String[] idOutcome = {String.valueOf(IDvariableCombo.getSelectedIndex() + 1), String.valueOf(StageOneVariableCombo.getSelectedIndex() + 1)};

        try {
            tryCount = 1;
            defFile.setSharedIdAndStageOneOutcomeFields(idOutcome);
            System.out.println("From defHelper | ID and Outcome indices: " + Arrays.toString(defFile.getSharedIdAndStageOneOutcomeFields()));
        } catch (Exception ex) {
            catchCount = 1;
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
            SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }

        try {
            tryCount = 1;
            defFile.setSharedModelStageOneOutcomeLabel(getOutcomeLabel());
            System.out.println("From defHelper | Outcome variable Stage One LABEL: " + defFile.getSharedModelStageOneOutcomeLabel());
        } catch (Exception ex) {
            catchCount = 1;
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
            SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }

        // i is the number of random location effects selected by the users
        if (i == MixLibrary.STAGE_ONE_RLE_LOCATION) {

            try {
                tryCount = 1;
                int MeanCount = countLevelOneBeta() + countLevelTwoBeta() - countLevelOneDicompMean(); //check this ======================
                // count total mean regressors in level one and level two
                defFile.setAdvancedMeanRegressorCount(String.valueOf(MeanCount));
                System.out.println("From mixRegGUI | Stage 1 Model Mean Count: " + String.valueOf(MeanCount));
                System.out.println("From defHelper | Stage 1 Model Mean Count: " + defFile.getAdvancedMeanRegressorCount().toString());
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                tryCount = 1;
                int betweenCount = countLevelOneAlpha() + countLevelTwoAlpha() - countLevelOneDicompBS();
                defFile.setAdvancedRandomRegressorCount(String.valueOf(betweenCount));
                System.out.println("From defHelper | Model Between Count: " + defFile.getAdvancedRandomRegressorCount().toString());
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                tryCount = 1;
                int withinCount = countLevelOneTau() + countLevelTwoTau() - countLevelOneDicompWS();
                defFile.setAdvancedScaleRegressorCount(String.valueOf(withinCount));
                System.out.println("From defHelper | Model Within Count: " + defFile.getAdvancedScaleRegressorCount());
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            //Number of disaggregate means
            try {
                tryCount = 1;
                defFile.setAdvancedDecomposeMeanRegressorCount(String.valueOf(countLevelOneDicompMean()));
                System.out.println("From defHelper | Stage 1 Decomp Model Mean Count: " + defFile.getAdvancedDecomposeMeanRegressorCount().toString());
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            //Number of disaggregate BS Variance
            try {
                tryCount = 1;
                defFile.setAdvancedDecomposeRandomRegressorCount(String.valueOf(countLevelOneDicompBS()));
                System.out.println("From defHelper | Stage 1 BS Variance Disagg. Regressor Count: " + defFile.getAdvancedDecomposeRandomRegressorCount().toString());
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            //Number of disaggregate WS Variance
            try {
                tryCount = 1;
                defFile.setAdvancedDecomposeScaleRegressorCount(String.valueOf(countLevelOneDicompWS()));
                System.out.println("From defHelper | Stage 1 WS Variance Disagg Regressor Count: " + defFile.getAdvancedDecomposeScaleRegressorCount().toString());
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            // ---- Check if the association radio buttons have been selected (Advanced effect of mean) ----
            //count field array sizes
            if (NoAssociationRadio.isSelected()) {

                try {
                    defFile.setAdvancedRandomScaleAssociation(String.valueOf(0));
                    System.out.println("From defHelper | Stage 1 Advanced effects of mean on WS variance (No Association): " + defFile.getAdvancedRandomScaleAssociation());
                } catch (Exception ex) {
                    catchCount = 1;
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }

            } else if (LinearAssociationRadio.isSelected()) {
                try {
                    defFile.setAdvancedRandomScaleAssociation(String.valueOf(1));
                    System.out.println("From defHelper | Stage 1 Advanced effects of mean on WS variance (Linear Association): " + defFile.getAdvancedRandomScaleAssociation());
                } catch (Exception ex) {
                    catchCount = 1;
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }

            } else if (QuadraticAssociationRadio.isSelected()) {
                try {
                    defFile.setAdvancedRandomScaleAssociation(String.valueOf(2));
                    System.out.println("From defHelper | Stage 1 Advanced effects of mean on WS variance (Quadratic Association): " + defFile.getAdvancedRandomScaleAssociation());
                } catch (Exception ex) {
                    catchCount = 1;
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }

            } // field array counting ends

            // get selected regressor labels and read them into defFile
            try {
                defFile.setSharedModelMeanRegressorLabels(ModelMeansLabelsArray());
//                System.out.println("From defHelper | Stage 1 MEAN REGRESSOR LABELS): " + Arrays.toString(defFile.getSharedModelMeanRegressorLabels()));
//                defFile.setSharedModelMeanRegressorLabelsLevelOne(getModelMeanLabelsLevelOne());
//                System.out.println("From defHelper | LEVEL 1 MEAN REGRESSOR LABELS): " + Arrays.toString(defFile.getSharedModelMeanRegressorLabelsLevelOne()));
//                defFile.setSharedModelMeanRegressorLabelsLevelTwo(getModelMeanLabelsLevelTwo());
//                System.out.println("From defHelper | LEVEL 2 MEAN REGRESSOR LABELS): " + Arrays.toString(defFile.getSharedModelMeanRegressorLabelsLevelTwo()));
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                defFile.setSharedModelRandomRegressorLabels(ModelBSLabelsArray());
//                System.out.println("From defHelper | Stage 1 BS REGRESSOR LABELS): " + Arrays.toString(defFile.getSharedModelRandomRegressorLabels()));
//                defFile.setSharedModelRandomRegressorLabelsLevelOne(getModelBSLabelsLevelOne());
//                System.out.println("From defHelper | LEVEL 1 BS REGRESSOR LABELS): " + Arrays.toString(defFile.getLabelModelBSRegressorsLevelOne()));
//                defFile.setSharedModelRandomRegressorLabelsLevelTwo(getModelBSLabelsLevelTwo());
//                System.out.println("From defHelper | LEVEL 2 BS REGRESSOR LABELS): " + Arrays.toString(defFile.getLabelModelBSRegressorsLevelTwo()));
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                defFile.setSharedModelScaleRegressorLabels(ModelWSLabelsArray());
                System.out.println("From defHelper | Stage 1 WS REGRESSOR LABELS): " + Arrays.toString(defFile.getSharedModelScaleRegressorLabels()));
                defFile.setLabelModelWSRegressorsLevelOne(getModelWSLabelsLevelOne());
                System.out.println("From defHelper | LEVEL 1 WS REGRESSOR LABELS): " + Arrays.toString(defFile.getLabelModelWSRegressorsLevelOne()));
                defFile.setLabelModelWSRegressorsLevelTwo(getModelWSLabelsLevelTwo());
                System.out.println("From defHelper | LEVEL 2 WS REGRESSOR LABELS): " + Arrays.toString(defFile.getLabelModelWSRegressorsLevelTwo()));
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            // Reads the variable names of variables that have been selected as mean regressors
            try {
                defFile.setSharedModelMeanRegressorFields(fieldModelMeanArray());
                System.out.println("From defHelper | #Stage One Mean Regressors: " + defFile.getSharedModelMeanRegressorFields().length);
                System.out.println("From defHelper | Stage One Mean Regressors Selected: " + Arrays.toString(defFile.getSharedModelMeanRegressorFields()));
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            // Reads the variable names of variables that have been selected as BS Variances
            try {
                defFile.setSharedModelRandomRegressorFields(fieldModelBSArray());
                System.out.println("From defHelper | #Stage One BS Regressors: " + defFile.getSharedModelRandomRegressorFields().length);
                System.out.println("From defHelper | Stage One BS Var. Regressors Selected: " + Arrays.toString(defFile.getSharedModelRandomRegressorFields()));
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            // Reads the variable names of variables that have been selected as WS Variances
            try {
                defFile.setSharedModelScaleRegressorFields(fieldModelWSArray());
                System.out.println("From defHelper | #Stage One WS Regressors: " + defFile.getSharedModelScaleRegressorFields().length);
                System.out.println("From defHelper | Stage One WS Var. Regressors Selected: " + Arrays.toString(defFile.getSharedModelScaleRegressorFields()));
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                defFile.setSharedModelDecomposeMeanRegressorFields(getMeanDecompFieldRegressorLabels_levelOne());
                System.out.println("From defHelper | #Stage One Mean + Disagg. Regressors: " + defFile.getSharedModelDecomposeMeanRegressorFields().length);
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                defFile.setSharedModelDecomposeRandomRegressorFields(getBSDecompFieldRegressorLabels_levelOne());
                System.out.println("From defHelper | #Stage One BS + Disagg. Regressors: " + defFile.getSharedModelDecomposeRandomRegressorFields().length);
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                defFile.setSharedModelDecomposeScaleRegressorFields(getWSDecompFieldRegressorLabels_levelOne());
                System.out.println("From defHelper | #Stage One WS + Disagg. Regressors: " + defFile.getSharedModelDecomposeScaleRegressorFields().length);
            } catch (Exception ex) {
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                defFile.setSharedModelDecomposeMeanRegressorLabels(getDecompMeanLabelsLevelOne());
                System.out.println("From defHelper | Model Decomp + Mean Labels: " + Arrays.toString(defFile.getSharedModelDecomposeMeanRegressorLabels()));
            } catch (Exception ex) {
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                defFile.setSharedModelDecomposeRandomRegressorLabels(getDecompBSLabelsLevelOne());
                System.out.println("From defHelper | Model Decomp + BS Labels: " + Arrays.toString(defFile.getSharedModelDecomposeRandomRegressorLabels()));
            } catch (Exception ex) {
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                defFile.setSharedModelDecomposeScaleRegressorLabels(getDecompWSLabelsLevelOne());
                System.out.println("From defHelper | Model Decomp + WS Labels: " + Arrays.toString(defFile.getSharedModelDecomposeScaleRegressorLabels()));
            } catch (Exception ex) {
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            //******************************************************************
        } else if (i == MixLibrary.STAGE_ONE_RLE_SLOPE) {

            try {
                tryCount = 1;
                int MeanCount = countLevelOneBeta() + countLevelTwoBeta() - countLevelOneDicompMean();

                // count total mean regressors in level one and level two
                defFile.setAdvancedMeanRegressorCount(String.valueOf(MeanCount));
                System.out.println("From defHelper | Stage 1 Model Mean Count: " + defFile.getAdvancedMeanRegressorCount().toString());
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                int LocRanCount = countLevelOneAlpha() + countLevelTwoAlpha() - countLevelOneDicompBS();
                // count total random location regressors in level one and level two
                defFile.setAdvancedRandomRegressorCount(String.valueOf(LocRanCount));
                System.out.println("From defHelper | Stage 1 Model Loc Ran Count: " + defFile.getAdvancedRandomRegressorCount().toString());
                tryCount = 1;
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                tryCount = 1;
                int ScaleCount = countLevelOneTau() + countLevelTwoTau() - countLevelOneDicompWS();
                // count total scale regressors in level one and level two
                defFile.setAdvancedScaleRegressorCount(String.valueOf(ScaleCount));
                System.out.println("From defHelper | Stage 1 Model Scale Count: " + defFile.getAdvancedScaleRegressorCount().toString());
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                tryCount = 1;

                //Disagg means count
                defFile.setAdvancedDecomposeMeanRegressorCount(String.valueOf(countLevelOneDicompMean()));
                System.out.println("From defHelper | Stage 1 Decomp Model Mean Count: " + defFile.getAdvancedDecomposeMeanRegressorCount().toString());
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                tryCount = 1;
                //Disagg Random Location count
                defFile.setAdvancedDecomposeRandomRegressorCount(String.valueOf(countLevelOneDicompBS()));
                System.out.println("From defHelper | Stage 1 Decomp Model Loc Random Count: " + defFile.getAdvancedDecomposeRandomRegressorCount().toString());
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                tryCount = 1;
                //Disagg scale count
                defFile.setAdvancedDecomposeScaleRegressorCount(String.valueOf(countLevelOneDicompWS()));
                System.out.println("From defHelper | Stage 1 Decomp Scale Count: " + defFile.getAdvancedDecomposeScaleRegressorCount().toString());
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            //Check if the effect of mean on WS variances options have been selected
            if (NoAssociationRadio.isSelected()) {

                try {
                    defFile.setAdvancedRandomScaleAssociation(String.valueOf(1));
                    System.out.println("From defHelper | Stage 1 Association of random location & scale?: " + defFile.getAdvancedRandomScaleAssociation());
                } catch (Exception ex) {
                    catchCount = 1;
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }

            } else if (LinearAssociationRadio.isSelected()) {
                try {
                    defFile.setAdvancedRandomScaleAssociation(String.valueOf(0));
                    System.out.println("From defHelper | Stage 1 Association of random location & scale?: " + defFile.getAdvancedRandomScaleAssociation());
                } catch (Exception ex) {
                    catchCount = 1;
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }
            }

            try {
                defFile.setSharedModelMeanRegressorLabels(ModelMeansLabelsArray());
                System.out.println("From defHelper | Stage 1 MEAN REGRESSOR LABELS): " + Arrays.toString(defFile.getSharedModelMeanRegressorLabels()));
//                defFile.setSharedModelMeanRegressorLabelsLevelOne(getModelMeanLabelsLevelOne());
//                System.out.println("From defHelper | LEVEL 1 MEAN REGRESSOR LABELS): " + Arrays.toString(defFile.getSharedModelMeanRegressorLabelsLevelOne()));
//                defFile.setSharedModelMeanRegressorLabelsLevelTwo(getModelMeanLabelsLevelTwo());
//                System.out.println("From defHelper | LEVEL 2 MEAN REGRESSOR LABELS): " + Arrays.toString(defFile.getSharedModelMeanRegressorLabelsLevelTwo()));
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                defFile.setSharedModelRandomRegressorLabels(ModelBSLabelsArray());
                System.out.println("From defHelper | Stage 1 LocRan REGRESSOR LABELS): " + Arrays.toString(defFile.getSharedModelRandomRegressorLabels()));
                defFile.setLabelModelLocRanRegressorsLevelOne(getModelBSLabelsLevelOne());
                System.out.println("From defHelper | LEVEL 1 LocRan REGRESSOR LABELS): " + Arrays.toString(defFile.getLabelModelLocRanRegressorsLevelOne()));
                defFile.setLabelModelLocRanRegressorsLevelTwo(getModelBSLabelsLevelTwo());
                System.out.println("From defHelper | LEVEL 2 LocRan REGRESSOR LABELS): " + Arrays.toString(defFile.getLabelModelLocRanRegressorsLevelTwo()));
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                defFile.setSharedModelScaleRegressorLabels(ModelWSLabelsArray());
                System.out.println("From defHelper | Stage 1 Scale REGRESSOR LABELS): " + Arrays.toString(defFile.getSharedModelScaleRegressorLabels()));
                defFile.setLabelModelScaleRegressorsLevelOne(getModelWSLabelsLevelOne());
                System.out.println("From defHelper | LEVEL 1 Scale REGRESSOR LABELS): " + Arrays.toString(defFile.getLabelModelScaleRegressorsLevelOne()));
                defFile.setLabelModelScaleRegressorsLevelTwo(getModelWSLabelsLevelTwo());
                System.out.println("From defHelper | LEVEL 2 Scale REGRESSOR LABELS): " + Arrays.toString(defFile.getLabelModelScaleRegressorsLevelTwo()));
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            // count field labels
            try {
                // get variable names from selected mean regressors
                defFile.setSharedModelMeanRegressorFields(fieldModelMeanArray());
                System.out.println("From defHelper | #Stage One Mean Regressors: " + defFile.getSharedModelMeanRegressorFields().length); //check this ===============
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                // get variable names from selected random location regressors
                defFile.setSharedModelRandomRegressorFields(fieldModelBSArray());
                System.out.println("From defHelper | #Stage One BS(RanLoc) Regressors: " + defFile.getSharedModelRandomRegressorFields().length);
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                // get variable names from selected scale regressors
                defFile.setSharedModelScaleRegressorFields(fieldModelWSArray());
                System.out.println("From defHelper | #Stage One WS(Scale) Regressors: " + defFile.getSharedModelScaleRegressorFields().length);
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                defFile.setSharedModelDecomposeMeanRegressorFields(getMeanDecompFieldRegressorLabels_levelOne());
                System.out.println("From defHelper | #Stage One Mean + Disagg. Regressors: " + defFile.getSharedModelDecomposeMeanRegressorFields().length);
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                defFile.setSharedModelDecomposeRandomRegressorFields(getBSDecompFieldRegressorLabels_levelOne());
                //defFile.setSharedModelDecomposeRandomRegressorFields(fieldModelBSArray());
                System.out.println("From defHelper | #Stage One BS(RanLoc) + Disagg. Regressors: " + defFile.getSharedModelDecomposeRandomRegressorFields().length);
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }
            try {
                defFile.setSharedModelDecomposeScaleRegressorFields(getWSDecompFieldRegressorLabels_levelOne());
                System.out.println("From defHelper | #Stage One WS(Scale) + Disagg. Regressors: " + defFile.getSharedModelDecomposeScaleRegressorFields().length);
            } catch (Exception ex) {
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }
            try {
                defFile.setSharedModelDecomposeMeanRegressorLabels(getDecompMeanLabelsLevelOne());
                System.out.println("From defHelper | Model Decomp + Mean Labels: " + Arrays.toString(defFile.getSharedModelDecomposeMeanRegressorLabels()));
            } catch (Exception ex) {
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }
            try {
                defFile.setSharedModelDecomposeRandomRegressorLabels(getDecompBSLabelsLevelOne());
                System.out.println("From defHelper | Model Decomp + LocRan Labels: " + Arrays.toString(defFile.getSharedModelDecomposeRandomRegressorLabels()));
            } catch (Exception ex) {
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }
            try {
                defFile.setSharedModelDecomposeScaleRegressorLabels(getDecompWSLabelsLevelOne());
                System.out.println("From defHelper | Model Decomp + Scale Labels: " + Arrays.toString(defFile.getSharedModelDecomposeScaleRegressorLabels()));
            } catch (Exception ex) {
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }

        if (outcomeNone == true) {

            if (!checkTabExistinJTabbedPane(stageOneTabs, "View Model")) {
                int viewModelTabIdx = stageOneTabs.indexOfTab("View Data");
                stageOneTabs.insertTab("View Model", null, jPanel2, null, viewModelTabIdx);
            }
            if (!checkTabExistinJTabbedPane(stageOneTabs, "Stage 1 Results")) {
                int stage1ResultTabIdx = stageOneTabs.indexOfTab("View Model");
                stageOneTabs.insertTab("Stage 1 Results", null, jPanel3, null, stage1ResultTabIdx);
            }
            if (catchCount == 0) {
                int defTry = 0;
                int defCatch = 0;
                try {
                    List<String> defFileOutput;

                    defFile.writeStageOneOnlyDefFileToFolder();

                    //defFileOutput = defFile.buildStageOneOnlyDefinitonList();
                    System.out.println("From defHelper | Stage 1 def file created successfully!");

                } catch (Exception ex) {
                    defCatch = 1;
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                    System.out.println("From defHelper | Stage 1 def file failed!");
                }

                if (defCatch == 0) {
                    stageOneTabs.setSelectedIndex(3);
//                    stageOneTabs.setEnabledAt(5, false);
                }

            } else {

                //stageOneTabs.setSelectedIndex(1);
                //System.out.println("outcome not true!!!!");
            }

        } else {
            stageOneTabs.setSelectedIndex(2);
            System.out.println("outcome not none!!!!");
        }

        try {
            produceStageOneOutput();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
            SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
        }

//        if (outcomeNone == true) {
//            stageOneTabs.setEnabledAt(2, false);
//            stageOneTabs.setEnabledAt(4, false);
//        }
        Color darkGreen = new Color(0, 100, 0);
        stageOneModelStageTwoConfigLabel.setText(randomLocationEffects());
        stageOneModelStageTwoConfigLabel.setForeground(darkGreen);
        stageOneOutcomeStageTwoConfigLabel.setText(stageOneOutcomeTypeString());
        stageOneOutcomeStageTwoConfigLabel.setForeground(darkGreen);
        stageTwoOutcomeStageTwoConfigLabel.setText(stageTwoOutcomeTypeString());
        stageTwoOutcomeStageTwoConfigLabel.setForeground(darkGreen);
        stageTwoModelTypeStageTwoConfigLabel.setText(stageTwoModelTypeString());
        stageTwoModelTypeStageTwoConfigLabel.setForeground(darkGreen);
        numResamplingStageTwoConfigLabel.setText(numResamplingString());
        numResamplingStageTwoConfigLabel.setForeground(darkGreen);
    }

    private void update_trigger_StageOneRegConfig() {
        if (stageOneClicked == 1) {
            addStageTwoTabTwo.setEnabled(true);
            if (addStageOneCHecked == true) {
                stage_1_regs.updateStageOneAgain();
            } else {
                stage_1_regs.updateAllVariables();
            }

        }
    }

    private void update_StageOneLevelOneBoxes(DefaultListModel<String> defaultListModel,
            boolean[][] StageOneLevelOneBoxesSelection,
            boolean[][] disaggVarianceBoxesSelection) {

        levelOneSelected = new ArrayList<String>();

        JScrollPane scrollpanel = new JScrollPane(levelOneGrid);

        int regSize = defaultListModel.getSize();
        levelOneRegSize = regSize;
        levelOneDisaggSize = regSize;

        levelOneGrid.removeAll();

        levelOneGrid.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        //constraints.weightx = 1.0;
        constraints.anchor = GridBagConstraints.NORTH;
        //constraints.gridwidth = 4;

        GridBagConstraints separatorConstraint = new GridBagConstraints();
        separatorConstraint.weightx = 1.0;
        separatorConstraint.fill = GridBagConstraints.HORIZONTAL;
        separatorConstraint.gridwidth = GridBagConstraints.REMAINDER;
        separatorConstraint.gridx = 0;

        constraints.insets = new Insets(3, 0, 5, 25);
        separatorConstraint.insets = new Insets(0, 0, 0, 0);
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;

        levelOneBoxes = new ArrayList<>();
        disaggVarianceBoxes = new ArrayList<>();

        for (int j = 0; j < regSize; j++) {
            constraints.gridx = 0;
            constraints.anchor = GridBagConstraints.LINE_END;
            levelOneSelected.add(defaultListModel.getElementAt(j));
            levelOneGrid.add(new JLabel(levelOneSelected.get(j)), constraints);

            levelOneBoxes.add(j, new ArrayList<JCheckBox>());

            for (int k = 0; k < 3; k++) {
                int row = j;
                int column = k;

                constraints.gridx++;
                constraints.anchor = GridBagConstraints.CENTER;
                levelOneBoxes.get(j).add(k, new JCheckBox());

                if (StageOneLevelOneBoxesSelection[j][k] == true) {
                    levelOneBoxes.get(j).get(k).setSelected(true);
//                    disaggVarianceBoxes.get(j).get(k).setEnabled(true);
                }
                levelOneGrid.add(levelOneBoxes.get(j).get(k), constraints);

                levelOneBoxes.get(j).get(k).addActionListener(actionListener);
                levelOneBoxes.get(j).get(k).addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        AbstractButton abstractButton = (AbstractButton) e.getSource();
                        boolean selected = abstractButton.getModel().isSelected();
                        if (selected) {
                            System.out.println("Checkbox selected");
                            disaggVarianceBoxes.get(row).get(column).setEnabled(true);
                            disaggVarianceBoxes.get(row).get(column).setSelected(false);
                            System.out.println(disaggVarianceBoxes.size());
                        } else {
                            disaggVarianceBoxes.get(row).get(column).setEnabled(false);
                            disaggVarianceBoxes.get(row).get(column).setSelected(false);
                        }

                    }
                });

            }

            constraints.gridy++;
            constraints.gridx = 0;
            constraints.anchor = GridBagConstraints.LINE_END;

            levelOneGrid.add(new JLabel("Disaggregate?"), constraints);
            disaggVarianceBoxes.add(j, new ArrayList<JCheckBox>());

            for (int k = 0; k < 3; k++) {
                constraints.gridx++;
                constraints.anchor = GridBagConstraints.CENTER;

                disaggVarianceBoxes.get(j).add(k, new JCheckBox());
                if (disaggVarianceBoxesSelection[j][k] == true) {
                    disaggVarianceBoxes.get(j).get(k).setSelected(true);
                }
                levelOneGrid.add(disaggVarianceBoxes.get(j).get(k), constraints);
                disaggVarianceBoxes.get(j).get(k).setEnabled(false);

                if (levelOneBoxes.get(j).get(k).isSelected() == true) {
                    disaggVarianceBoxes.get(j).get(k).setEnabled(true);
                }

            }

            constraints.gridy++;
            //constraints.gridx = 0;
            separatorConstraint.gridy = separatorConstraint.gridy + 3;
            //System.out.println("before seperator");
            levelOneGrid.add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint);
            //System.out.println("after seperator");
            constraints.gridy++;

        }

        stageOneLevelOnePanel.removeAll();

        stageOneLevelOnePanel.revalidate();

        stageOneLevelOnePanel.repaint();

        stageOneLevelOnePanel.add(scrollpanel);

        revalidate();

    }

    private void update_StageOneLevelTwoBoxes(DefaultListModel<String> defaultListModel, boolean[][] StageOneLevelTwoBoxesSelection) {

        //levelTwoGrid.setVisible(true);
        JScrollPane scrollpanel = new JScrollPane(levelTwoGrid);
        levelTwoSelected = new ArrayList<String>();

        int regSize = defaultListModel.getSize();
        levelTwoRegSize = regSize;

        levelTwoGrid.removeAll();

        levelTwoGrid.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        // constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.NORTH;
        //constraints.gridwidth = 4;

        GridBagConstraints separatorConstraint = new GridBagConstraints();
        separatorConstraint.weightx = 1.0;
        separatorConstraint.fill = GridBagConstraints.HORIZONTAL;
        separatorConstraint.gridwidth = GridBagConstraints.REMAINDER;
        separatorConstraint.gridx = 0;

        constraints.insets = new Insets(3, 0, 5, 25);
        separatorConstraint.insets = new Insets(0, 0, 0, 0);
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;

        levelTwoBoxes = new ArrayList<ArrayList<JCheckBox>>();
        //disaggVarianceBoxes = new ArrayList<ArrayList<JCheckBox>>();

        for (int j = 0; j < regSize; j++) {
            constraints.gridx = 0;
            constraints.anchor = GridBagConstraints.LINE_END;
            levelTwoSelected.add(defaultListModel.getElementAt(j));
            levelTwoGrid.add(new JLabel(levelTwoSelected.get(j)), constraints);
            //levelTwoGrid.add(new JLabel(defaultListModel.getElementAt(j)), constraints);

            levelTwoBoxes.add(j, new ArrayList<JCheckBox>());

            for (int k = 0; k < 3; k++) {

                constraints.gridx++;
                constraints.anchor = GridBagConstraints.CENTER;
                levelTwoBoxes.get(j).add(k, new JCheckBox());
                // hoho
                if (StageOneLevelTwoBoxesSelection[j][k] == true) {
                    levelTwoBoxes.get(j).get(k).setSelected(true);
//                    System.out.print("********"+j+k+"********");
                }

                levelTwoGrid.add(levelTwoBoxes.get(j).get(k), constraints);
            }

            if (isRandomScale) {

                levelTwoBoxes.get(j).get(2).setEnabled(true);

            } else {

                levelTwoBoxes.get(j).get(2).setEnabled(false);

            }

            if (i == MixLibrary.STAGE_ONE_RLE_SLOPE) {
                levelTwoBoxes.get(j).get(1).setVisible(false);

            } else {

                levelTwoBoxes.get(j).get(1).setVisible(true);
                levelTwoBoxes.get(j).get(1).setEnabled(true);

            }

            constraints.gridy++;

            separatorConstraint.gridy = separatorConstraint.gridy + 2;
            // System.out.println("before seperator");
            levelTwoGrid.add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint);
            // System.out.println("after seperator");
            constraints.gridy++;

        }

        stageOneLevelTwoPanel.removeAll();
        stageOneLevelTwoPanel.revalidate();
        stageOneLevelTwoPanel.repaint();

        stageOneLevelTwoPanel.add(scrollpanel);
        revalidate();

    }

    private void update_trigger_runTabTwoStageOneTwo() {
        // TODO add your handling code here:
        // tryCount counts the number of successful DefinitionHelper function calls
        //catchCount counts number of exceptions in reading values to derHelper.
        //Prevents UI from moving forward in case of an exception
        int tryCount = 0;
        int catchCount = 0;

        // *********************************************************************
        // Test printing statements counting mean regressors
        System.out.println("Total selected mean regressors in level one: " + String.valueOf(countLevelOneBeta()));
        System.out.println("Total selected BS Variances in level one: " + String.valueOf(countLevelOneAlpha()));
        System.out.println("Total selected WS Variances in level one: " + String.valueOf(countLevelOneTau()));
        System.out.println("Total selected disagg. variance in level one: " + String.valueOf(countLevelOneDicompMean()));

        System.out.println("Total selected mean regressors in level two: " + String.valueOf(countLevelTwoBeta()));
        System.out.println("Total selected BS variances in level two: " + String.valueOf(countLevelTwoAlpha()));
        System.out.println("Total selected WS variances in level two: " + String.valueOf(countLevelTwoTau()));

        System.out.println("Total selected mean regressors in stage two: " + String.valueOf(countStageTwoBeta()));
        System.out.println("Total selected BS Variances in stage two: " + String.valueOf(countStageTwoAlpha()));
        System.out.println("Total selected WS Variances in stage two: " + String.valueOf(countStageTwoTau()));

        //**********************************************************************
        // Reads selected ID variable and outcome variable from the first two comboboxes
        //String[] idOutcome = {String.valueOf(IDvariableCombo.getSelectedIndex() + 1), String.valueOf(StageOneVariableCombo.getSelectedIndex() + 1)};
        try {
            defFile.setStageTwoOutcomeField(getStageTwoOutcomePosition());
            System.out.println("From defHelper | Outcome variable Position STAGE TWO: " + defFile.getStageTwoOutcomeField());
        } catch (Exception ex) {
            catchCount = 1;
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
            SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }

        try {
            defFile.setStageTwoOutcomeLabel(getStageTwoOutcomeLabel());
            System.out.println("From defHelper | Outcome variable label STAGE TWO: " + defFile.getStageTwoOutcomeLabel());
        } catch (Exception ex) {
            catchCount = 1;
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
            SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }

        // *********************************************************************
        // i is the number of random location effects selected by the users
        if (i == MixLibrary.STAGE_ONE_RLE_LOCATION) {

            if (NoAssociationRadio.isSelected()) {

                try {
                    defFile.setAdvancedRandomScaleAssociation(String.valueOf(0));
                    System.out.println("From defHelper | Stage 1 Advanced effects of mean on WS variance (No Association): " + defFile.getAdvancedRandomScaleAssociation());
                } catch (Exception ex) {
                    catchCount = 1;
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }

            } else if (LinearAssociationRadio.isSelected()) {
                try {
                    defFile.setAdvancedRandomScaleAssociation(String.valueOf(1));
                    System.out.println("From defHelper | Stage 1 Advanced effects of mean on WS variance (Linear Association): " + defFile.getAdvancedRandomScaleAssociation());
                } catch (Exception ex) {
                    catchCount = 1;
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }

            } else if (QuadraticAssociationRadio.isSelected()) {
                try {
                    defFile.setAdvancedRandomScaleAssociation(String.valueOf(2));
                    System.out.println("From defHelper | Stage 1 Advanced effects of mean on WS variance (Quadratic Association): " + defFile.getAdvancedRandomScaleAssociation());
                } catch (Exception ex) {
                    catchCount = 1;
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }

            } // field array counting ends

        } else if (i == MixLibrary.STAGE_ONE_RLE_SLOPE) {

            //Check if the effect of mean on WS variances options have been selected
            if (NoAssociationRadio.isSelected()) {

                try {
                    defFile.setAdvancedRandomScaleAssociation(String.valueOf(1));
                    System.out.println("From defHelper | Stage 1 Association of random location & scale?: " + defFile.getAdvancedRandomScaleAssociation());
                } catch (Exception ex) {
                    catchCount = 1;
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }

            } else if (LinearAssociationRadio.isSelected()) {
                try {
                    defFile.setAdvancedRandomScaleAssociation(String.valueOf(0));
                    System.out.println("From defHelper | Stage 1 Association of random location & scale?: " + defFile.getAdvancedRandomScaleAssociation());
                } catch (Exception ex) {
                    catchCount = 1;
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                    SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                }
            }
        }

        try {
            defFile.setAdvancedStageTwoFixedRegressorCount(String.valueOf(countStageTwoBeta()));
            System.out.println("From defHelper | STAGE TWO FIXED COUNT: " + defFile.getAdvancedStageTwoFixedRegressorCount());
        } catch (Exception ex) {
            catchCount = 1;
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
            SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }

        try {
            defFile.setAdvancedStageTwoThetaRegressorCount(String.valueOf(countStageTwoAlpha()));
            System.out.println("From defHelper | STAGE TWO LOC. RANDOM COUNT: " + defFile.getAdvancedStageTwoThetaRegressorCount().toString());
        } catch (Exception ex) {
            catchCount = 1;
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
            SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }

        try {
            defFile.setAdvancedStageTwoOmegaRegressorCount(String.valueOf(countStageTwoTau()));
            System.out.println("From defHelper | STAGE TWO SCALE COUNT: " + defFile.getAdvancedStageTwoOmegaRegressorCount().toString());
        } catch (Exception ex) {
            catchCount = 1;
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
            SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }

        if (suppressed == false) {

            try {
                defFile.setAdvancedStageTwoInteractionRegressorCount(String.valueOf(countStageTwoInteractions()));
                System.out.println("From defHelper | STAGE TWO INTERACTIONS COUNT: " + defFile.getAdvancedStageTwoInteractionRegressorCount().toString());
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

        }

        try {
            defFile.setStageTwoFixedFields(getFixedFieldRegressors_StageTwo());
            System.out.println("From defHelper | STAGE TWO  FIXED REGRESSOR Positions: " + Arrays.toString(defFile.getStageTwoFixedFields()));
        } catch (Exception ex) {
            catchCount = 1;
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
            SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }

        try {
            defFile.setStageTwoThetaFields(getRanLocFieldRegressors_StageTwo());
            System.out.println("From defHelper | STAGE TWO  LOC RAN REGRESSOR Positions: " + Arrays.toString(defFile.getStageTwoThetaFields()));
        } catch (Exception ex) {
            catchCount = 1;
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
            SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }

        try {
            defFile.setStageTwoOmegaFields(getScaleFieldRegressors_StageTwo());
            System.out.println("From defHelper | STAGE TWO  SCALE REGRESSOR Positions: " + Arrays.toString(defFile.getStageTwoOmegaFields()));
        } catch (Exception ex) {
            catchCount = 1;
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
            SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }

        if (suppressed == false) {

            try {
                defFile.setStageTwoInteractionFields(getInteractionFieldRegressors_StageTwo());
                System.out.println("From defHelper | STAGE TWO  INTERACTIONS REGRESSOR Positions: " + Arrays.toString(defFile.getStageTwoInteractionFields()));
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }

            try {
                defFile.setStageTwoInteractionLabels(getModelInteractionLabelsStageTwo());
                System.out.println("From defHelper | STAGE TWO  INTERACTIONS REGRESSORS: " + Arrays.toString(defFile.getStageTwoInteractionLabels()));
            } catch (Exception ex) {
                catchCount = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }

        try {
            defFile.setStageTwoFixedLabels(getModelFixedLabelsStageTwo());
            System.out.println("From defHelper | STAGE TWO  MEAN REGRESSORS: " + Arrays.toString(defFile.getStageTwoFixedLabels()));
        } catch (Exception ex) {
            catchCount = 1;
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
            SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }

        try {
            defFile.setStageTwoThetaLabels(getModelLocRanLabelsStageTwo());
            System.out.println("From defHelper | STAGE TWO  LOC RAN REGRESSORS: " + Arrays.toString(defFile.getStageTwoThetaLabels()));
        } catch (Exception ex) {
            catchCount = 1;
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
            SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }

        try {
            defFile.setStageTwoOmegaLabels(getModelScaleLabelsStageTwo());
            System.out.println("From defHelper | STAGE TWO  SCALE REGRESSORS: " + Arrays.toString(defFile.getStageTwoOmegaLabels()));
        } catch (Exception ex) {
            catchCount = 1;
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
            SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }
//        if (outComeType == false) {
//            try {
//                defFile.setStageTwoOutcomeCatCount(String.valueOf(getStagetwoOutcomeCats()));
//                System.out.println("From defHelper | STAGE TWO OUTCOME CATEGORY NUMBERS: " + defFile.getStageTwoOutcomeCatCount());
//            } catch (Exception ex) {
//                catchCount = 1;
//                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
//                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
//                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
//            }
//
//            try {
//                defFile.setStageTwoOutcomeCatLabel(getStageTwoOutcomeValues());
//                System.out.println("From defHelper | STAGE TWO OUTCOME CATEGORY VALUES: " + Arrays.toString(defFile.getStageTwoOutcomeCatLabel()));
//            } catch (Exception ex) {
//                catchCount = 1;
//                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
//                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
//                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
//            }
//
//        } else {
//
//            // do nothing ...
//        }

        if (catchCount == 0) {
            int defTry = 0;
            int defCatch = 0;
            try {
                List<String> defFileOutput;

                defFile.writeDefFileToFolder();
                defFileOutput = defFile.buildDefinitionList();
                System.out.println("From defHelper | Stage 1 def file created successfully!");
                //modelBuilder(defFile);
//                modelBuilder = new ModelBuilder(defFile);
                //                modelEquationTextArea.setText(modelBuilder.meanEquation());
                //                testEq.setText(modelBuilder.meanEquation());
                // TODO THIS // equationArea.setText(modelBuilder.meanEquation());

                //modelBuilder.saveWildFile(defFile);
                //                modelBuilder.meanEquation();
            } catch (Exception ex) {
                defCatch = 1;
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
                System.out.println("From defHelper | Stage 1 def file failed!");
            }

            if (defCatch == 0) {
                stageOneTabs.setSelectedIndex(3);//todo: get output as soon as it is ready
            }

        } else {

            // do nothing
        }
        if (!checkTabExistinJTabbedPane(stageOneTabs, "View Model")) {
            int viewModelTabIdx = stageOneTabs.indexOfTab("View Data");
            stageOneTabs.insertTab("View Model", null, jPanel2, null, viewModelTabIdx);
        }
        if (!checkTabExistinJTabbedPane(stageOneTabs, "Stage 2 Results")) {
            int stage2TabIdx = stageOneTabs.indexOfTab("View Model");
            stageOneTabs.insertTab("Stage 2 Results", null, jPanel4, null, stage2TabIdx);
        }
        if (!checkTabExistinJTabbedPane(stageOneTabs, "Stage 1 Results")) {
            int stage1ResultTabIdx = stageOneTabs.indexOfTab("Stage 2 Results");
            stageOneTabs.insertTab("Stage 1 Results", null, jPanel3, null, stage1ResultTabIdx);
        }

    }

    private void update_StageTwoStates(MixRegGuiStates mxrStates) {

        // stage 2 outcome (done in update_StageTwoStates)
        // Configure stage 2 regressors
        stageTwoRegs.stageTwoListModel = mxrStates.stageTwoListModel;
        stageTwoRegs.stageTwoLevelTwo = mxrStates.stageTwoLevelTwo;
        stage_2_regs.getStageTwoAllVariables().removeAll();
        stage_2_regs.getStageTwoAllVariables().setModel(mxrStates.stageTwoListModel);
        stage_2_regs.getStageTwoAllVariables().setSelectedIndex(0);
        stage_2_regs.getStageTwoLevelTwoVariables().removeAll();
        stage_2_regs.getStageTwoLevelTwoVariables().setModel(mxrStates.stageTwoLevelTwo);

        // boxes
        stageTwoRegs.isStageTwoSubmitClicked = mxrStates.isStageTwoSubmitClicked;
        if (stageTwoRegs.isStageTwoSubmitClicked == true) {
            stage_2_regs.setEnabledStageTwoSubmitButton(true);
            update_StageTwoLevelTwoBoxes(stageTwoRegs.stageTwoLevelTwo, mxrStates.stageTwoGridBoxesSelection);
            suppressIntCheckBox.setSelected(mxrStates.suppressIntCheckBox);
            update_trigger_suppressIntCheckBox();
        }

        // suppress scale X random Interaction
        // run stage 1 and 2
//
//        isStageOneSubmitted = mxrStates.isStageOneSubmitted;
//        if (isStageOneSubmitted == true) {
//            update_trigger_StartStageTwo();
//        }
    }

    private void update_StageTwoLevelTwoBoxes(DefaultListModel<String> defaultListModel, boolean[][] stageTwoGridBoxesSelection) {

        JScrollPane scrollpanel = new JScrollPane(stageTwoRegsGridLvl2);
        stageTwoLevelTwoSelected = new ArrayList<String>();

        int regSize = defaultListModel.getSize();
        stageTwoLevelTwoRegSize = regSize;

        stageTwoRegsGridLvl2.removeAll();

        stageTwoRegsGridLvl2.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        // constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        //constraints.gridwidth = 4;

        GridBagConstraints separatorConstraint = new GridBagConstraints();
        separatorConstraint.weightx = 1.0;
        separatorConstraint.fill = GridBagConstraints.HORIZONTAL;
        separatorConstraint.gridwidth = GridBagConstraints.REMAINDER;
        separatorConstraint.gridx = 0;

        constraints.insets = new Insets(3, 0, 5, 25);
        separatorConstraint.insets = new Insets(0, 0, 0, 0);
        constraints.weightx = 1;

        stageTwoLevelTwoGridBoxes = new ArrayList<ArrayList<JCheckBox>>();
        //disaggVarianceBoxes = new ArrayList<ArrayList<JCheckBox>>();

        for (int j = 0; j < regSize; j++) {
            int row = j;
            constraints.gridx = 1;
            constraints.anchor = GridBagConstraints.FIRST_LINE_START;
            stageTwoLevelTwoSelected.add(defaultListModel.getElementAt(j));
            stageTwoRegsGridLvl2.add(new JLabel(stageTwoLevelTwoSelected.get(j)), constraints);

            //stageTwoGrid.add(new JLabel(defaultListModel.getElementAt(j)), constraints);
            stageTwoLevelTwoGridBoxes.add(j, new ArrayList<JCheckBox>());

            for (int k = 0; k < 4; k++) {

                if (k == 1) {

                    constraints.gridx = constraints.gridx + 5;

                } else {
                    constraints.gridx++;
                }

                constraints.anchor = GridBagConstraints.CENTER;
                stageTwoLevelTwoGridBoxes.get(j).add(k, new JCheckBox());
                if (stageTwoGridBoxesSelection[j][k] == true) {
                    stageTwoLevelTwoGridBoxes.get(j).get(k).setSelected(true);
                }
                stageTwoRegsGridLvl2.add(stageTwoLevelTwoGridBoxes.get(j).get(k), constraints);
            }

            constraints.gridy++;

            separatorConstraint.gridy = separatorConstraint.gridy + 2;

            stageTwoRegsGridLvl2.add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint);
            //System.out.println("after seperator");
            constraints.gridy++;

//            stageTwoLevelTwoGridBoxes.get(row).get(1).setEnabled(false);
//            stageTwoLevelTwoGridBoxes.get(row).get(2).setEnabled(false);
//            stageTwoLevelTwoGridBoxes.get(row).get(3).setEnabled(false);
            stageTwoLevelTwoGridBoxes.get(j).get(0).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    AbstractButton abstractButton = (AbstractButton) e.getSource();
                    boolean selected = abstractButton.getModel().isSelected();
                    if (selected) {
                        System.out.println("Checkbox selected");
                        //disaggVarianceBoxes.get(row).get(column).setEnabled(true);
                        stageTwoLevelTwoGridBoxes.get(row).get(1).setEnabled(true);
                        stageTwoLevelTwoGridBoxes.get(row).get(1).setSelected(false);
                        stageTwoLevelTwoGridBoxes.get(row).get(2).setEnabled(true);
                        stageTwoLevelTwoGridBoxes.get(row).get(2).setSelected(false);
                        randomChecked = false;
                        scaleChecked = false;
//                        System.out.println(disaggVarianceBoxes.size());
                    } else {
                        //disaggVarianceBoxes.get(row).get(column).setEnabled(false);
                        stageTwoLevelTwoGridBoxes.get(row).get(1).setEnabled(false);
                        stageTwoLevelTwoGridBoxes.get(row).get(1).setSelected(false);
                        stageTwoLevelTwoGridBoxes.get(row).get(2).setEnabled(false);
                        stageTwoLevelTwoGridBoxes.get(row).get(2).setSelected(false);
                        stageTwoLevelTwoGridBoxes.get(row).get(3).setSelected(false);
                        stageTwoLevelTwoGridBoxes.get(row).get(3).setEnabled(false);
                        randomChecked = false;
                        scaleChecked = false;
                        suppressIntCheckBox.setEnabled(false);
                        suppressIntCheckBox.setSelected(false);

                    }

                }
            });

            stageTwoLevelTwoGridBoxes.get(j).get(1).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    AbstractButton abstractButton = (AbstractButton) e.getSource();
                    boolean selected = abstractButton.getModel().isSelected();
                    if (selected) {
                        scaleChecked = true;
                        if (randomChecked == true) {
                            if (!suppressed) {
                                stageTwoLevelTwoGridBoxes.get(row).get(3).setEnabled(true);
                                stageTwoLevelTwoGridBoxes.get(row).get(3).setSelected(false);
                            }
                        }

                    } else {
                        scaleChecked = false;
                        if (!suppressed) {
                            stageTwoLevelTwoGridBoxes.get(row).get(3).setEnabled(false);
                            stageTwoLevelTwoGridBoxes.get(row).get(3).setSelected(false);
                        }
                    }
                }
            });

            stageTwoLevelTwoGridBoxes.get(j).get(2).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    AbstractButton abstractButton = (AbstractButton) e.getSource();
                    boolean selected = abstractButton.getModel().isSelected();

                    if (selected) {
                        randomChecked = true;

                        if (scaleChecked == true) {
                            if (!suppressed) {
                                stageTwoLevelTwoGridBoxes.get(row).get(3).setEnabled(true);
                                stageTwoLevelTwoGridBoxes.get(row).get(3).setSelected(false);
                            }
                        }

                    } else {
                        randomChecked = false;
                        if (!suppressed) {
                            stageTwoLevelTwoGridBoxes.get(row).get(3).setEnabled(false);
                            stageTwoLevelTwoGridBoxes.get(row).get(3).setSelected(false);
                        }

                    }
                }
            });

            stageTwoLevelTwoGridBoxes.get(j).get(3).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    AbstractButton abstractButton = (AbstractButton) e.getSource();
                    boolean selected = abstractButton.getModel().isSelected();

                    randomChecked = false;
                    scaleChecked = false;

                    suppressIntCheckBox.setEnabled(true);

                }
            });

        }

        stageTwoLevelTwoPanel.removeAll();
        stageTwoLevelTwoPanel.revalidate();
        stageTwoLevelTwoPanel.repaint();

        stageTwoLevelTwoPanel.add(scrollpanel);
        revalidate();

    }

    private void update_trigger_suppressIntCheckBox() {
        if (suppressIntCheckBox.isSelected()) {

            suppressed = true;

            try {
                defFile.setAdvancedStageTwoInteractionRegressorCount("-1");
            } catch (Exception ex) {
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
            }

            for (int p = 0; p < stageTwoLevelTwoRegSize; p++) {
                stageTwoLevelOneGridBoxes.get(p).get(3).setSelected(false);
                stageTwoLevelOneGridBoxes.get(p).get(3).setEnabled(false);
                stageTwoLevelTwoGridBoxes.get(p).get(3).setSelected(false);
                stageTwoLevelTwoGridBoxes.get(p).get(3).setEnabled(false);
            }
            try {
                defFile.setStageTwoInteractionFields(new String[0]);
                defFile.setStageTwoInteractionLabels(new String[0]);

            } catch (Exception ex) {
                Logger.getLogger(mixregGUI.class
                        .getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
            }

        } else {
            suppressed = false;

            try {
                defFile.setAdvancedStageTwoInteractionRegressorCount("0");
            } catch (Exception ex) {
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
            }

            for (int p = 0; p < stageTwoLevelTwoRegSize; p++) {

                if (stageTwoLevelTwoGridBoxes.get(p).get(1).isSelected() && stageTwoLevelTwoGridBoxes.get(p).get(2).isSelected()) {
                    stageTwoLevelTwoGridBoxes.get(p).get(3).setEnabled(true);
                }

                if (stageTwoLevelOneGridBoxes.get(p).get(1).isSelected() && stageTwoLevelOneGridBoxes.get(p).get(2).isSelected()) {
                    stageTwoLevelOneGridBoxes.get(p).get(3).setEnabled(true);
                }

            }

        }

    }

    private void updateGuiView_trigger_dataview() {
        try {
            getDataFromCSV();
            printFileName();
            System.out.println("NEW MODEL DATA READ");
        } catch (IOException ex) {
            Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
            SystemLogger.LOGGER.log(Level.SEVERE, ex.toString() + "{0}", SystemLogger.getLineNum());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Caution!", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }

    void updateStageTwoLevelOneGrid(DefaultListModel<String> defaultListModel) {

        JScrollPane scrollpanel = new JScrollPane(stageTwoRegsGridLvl1);
        stageTwoLevelOneSelected = new ArrayList<String>();

        int regSize = defaultListModel.getSize();
        stageTwoLevelOneRegSize = regSize;
        System.out.println("Eldin says: stage2lvl1 reg size is: " + stageTwoLevelOneRegSize);

        stageTwoRegsGridLvl1.removeAll();

        stageTwoRegsGridLvl1.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        // constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        //constraints.gridwidth = 4;

        GridBagConstraints separatorConstraint = new GridBagConstraints();
        separatorConstraint.weightx = 1.0;
        separatorConstraint.fill = GridBagConstraints.HORIZONTAL;
        separatorConstraint.gridwidth = GridBagConstraints.REMAINDER;
        separatorConstraint.gridx = 0;

        constraints.insets = new Insets(3, 0, 5, 25);
        separatorConstraint.insets = new Insets(0, 0, 0, 0);
        constraints.weightx = 1;

        stageTwoLevelOneGridBoxes = new ArrayList<ArrayList<JCheckBox>>();
        //disaggVarianceBoxes = new ArrayList<ArrayList<JCheckBox>>();

        for (int j = 0; j < regSize; j++) {
            int row = j;
            constraints.gridx = 1;
            constraints.anchor = GridBagConstraints.FIRST_LINE_START;
            stageTwoLevelOneSelected.add(defaultListModel.getElementAt(j));
            JLabel testLabel = new JLabel(stageTwoLevelOneSelected.get(j));
//            System.out.print("labelll  "+testLabel.isVisible());
            stageTwoRegsGridLvl1.add(testLabel, constraints);

            //stageTwoGrid.add(new JLabel(defaultListModel.getElementAt(j)), constraints);
            stageTwoLevelOneGridBoxes.add(j, new ArrayList<JCheckBox>());

            for (int k = 0; k < 4; k++) {

                if (k == 1) {

                    constraints.gridx = constraints.gridx + 5;

                } else {
                    constraints.gridx++;
                }

                constraints.anchor = GridBagConstraints.CENTER;
                stageTwoLevelOneGridBoxes.get(j).add(k, new JCheckBox());

                stageTwoRegsGridLvl1.add(stageTwoLevelOneGridBoxes.get(j).get(k), constraints);
            }

            constraints.gridy++;

            separatorConstraint.gridy = separatorConstraint.gridy + 2;

            stageTwoRegsGridLvl1.add(new JSeparator(JSeparator.HORIZONTAL), separatorConstraint);
            //System.out.println("after seperator");
            constraints.gridy++;

            stageTwoLevelOneGridBoxes.get(row).get(1).setEnabled(false);
            stageTwoLevelOneGridBoxes.get(row).get(2).setEnabled(false);
            stageTwoLevelOneGridBoxes.get(row).get(3).setEnabled(false);

            stageTwoLevelOneGridBoxes.get(j).get(0).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    AbstractButton abstractButton = (AbstractButton) e.getSource();
                    boolean selected = abstractButton.getModel().isSelected();
                    if (selected) {
                        System.out.println("Checkbox selected");
                        //disaggVarianceBoxes.get(row).get(column).setEnabled(true);
                        stageTwoLevelOneGridBoxes.get(row).get(1).setEnabled(true);
                        stageTwoLevelOneGridBoxes.get(row).get(1).setSelected(false);
                        stageTwoLevelOneGridBoxes.get(row).get(2).setEnabled(true);
                        stageTwoLevelOneGridBoxes.get(row).get(2).setSelected(false);
                        randomChecked = false;
                        scaleChecked = false;
//                        System.out.println(disaggVarianceBoxes.size());
                    } else {
                        //disaggVarianceBoxes.get(row).get(column).setEnabled(false);
                        stageTwoLevelOneGridBoxes.get(row).get(1).setEnabled(false);
                        stageTwoLevelOneGridBoxes.get(row).get(1).setSelected(false);
                        stageTwoLevelOneGridBoxes.get(row).get(2).setEnabled(false);
                        stageTwoLevelOneGridBoxes.get(row).get(2).setSelected(false);
                        stageTwoLevelOneGridBoxes.get(row).get(3).setSelected(false);
                        stageTwoLevelOneGridBoxes.get(row).get(3).setEnabled(false);
                        randomChecked = false;
                        scaleChecked = false;
                        suppressIntCheckBox.setEnabled(false);
                        suppressIntCheckBox.setSelected(false);

                    }

                }
            });

            stageTwoLevelOneGridBoxes.get(j).get(1).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    AbstractButton abstractButton = (AbstractButton) e.getSource();
                    boolean selected = abstractButton.getModel().isSelected();
                    if (selected) {
                        scaleChecked = true;
                        if (randomChecked == true) {
                            if (!suppressed) {
                                stageTwoLevelOneGridBoxes.get(row).get(3).setEnabled(true);
                                stageTwoLevelOneGridBoxes.get(row).get(3).setSelected(false);
                            }
                        }

                    } else {
                        scaleChecked = false;
                        if (!suppressed) {
                            stageTwoLevelOneGridBoxes.get(row).get(3).setEnabled(false);
                            stageTwoLevelOneGridBoxes.get(row).get(3).setSelected(false);
                        }
                    }
                }
            });

            stageTwoLevelOneGridBoxes.get(j).get(2).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    AbstractButton abstractButton = (AbstractButton) e.getSource();
                    boolean selected = abstractButton.getModel().isSelected();

                    if (selected) {
                        randomChecked = true;

                        if (scaleChecked == true) {
                            if (!suppressed) {
                                stageTwoLevelOneGridBoxes.get(row).get(3).setEnabled(true);
                                stageTwoLevelOneGridBoxes.get(row).get(3).setSelected(false);
                            }
                        }

                    } else {
                        randomChecked = false;
                        if (!suppressed) {
                            stageTwoLevelOneGridBoxes.get(row).get(3).setEnabled(false);
                            stageTwoLevelOneGridBoxes.get(row).get(3).setSelected(false);
                        }

                    }
                }
            });

            stageTwoLevelOneGridBoxes.get(j).get(3).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    AbstractButton abstractButton = (AbstractButton) e.getSource();
                    boolean selected = abstractButton.getModel().isSelected();

                    randomChecked = false;
                    scaleChecked = false;

                    suppressIntCheckBox.setEnabled(true);

                }
            });

        }

        stageTwoLevelOnePanel.removeAll();
        stageTwoLevelOnePanel.add(scrollpanel);
//        stageTwoLevelOnePanel.add(jSeparator20);
        stageTwoLevelOnePanel.revalidate();
        stageTwoLevelOnePanel.repaint();

//        revalidate();
//        repaint();
    }

    public int getRandomScaleSelection() {
        int randomScaleSelection = MixLibrary.STAGE_ONE_SCALE_NO;

        if (getRandomScaleSelectionYes() == true) {
            randomScaleSelection = MixLibrary.STAGE_ONE_SCALE_YES;
        } else {
            randomScaleSelection = MixLibrary.STAGE_ONE_SCALE_NO;
        }

        return randomScaleSelection;
    }

    public int getStageTwoModelType() {
        int stageTwoModelType = MixLibrary.STAGE_TWO_MODEL_TYPE_SINGLE;

        if (getStageTwoSingleLevel() == true) {
            stageTwoModelType = MixLibrary.STAGE_TWO_MODEL_TYPE_SINGLE;
        } else if (getStageTwoMultiLevel() == true) {
            stageTwoModelType = MixLibrary.STAGE_TWO_MODEL_TYPE_MULTILEVEL;
        }

        return stageTwoModelType;

    }

    public int getStageTwoOutcomeType() {
        int stageTwoOutcomeType = MixLibrary.STAGE_TWO_OUTCOME_NONE;

        if (getStageTwoContinuousRadio() == true) {
            stageTwoOutcomeType = MixLibrary.STAGE_TWO_OUTCOME_NORMAL;
        } else if (getStageTwoDichotomousRadio() == true) {
            stageTwoOutcomeType = MixLibrary.STAGE_TWO_OUTCOME_ORDINAL;
        }

        return stageTwoOutcomeType;

    }

    private void updateGuiVIew_trigger_updateStage2Config() {
        boolean turnOn = true;

        if (isUpdateStage2ConfigClicked == true) {
            turnOn = false;
//            if (includeStageTwoYes.isSelected()) {
//                seedTextBox.setEnabled(true);
//                seedHelpButton.setEnabled(true);
//                seedTextBox.setEnabled(true);
//                stageTwoSingleLevel.setEnabled(true);
//                stageTwoMultiLevel.setEnabled(true);
//                stageTwoContinuousRadio.setEnabled(true);
//                stageTwoDichotomousRadio.setEnabled(true);
//                countRadio.setEnabled(true);
//                multinomialRadio.setEnabled(true);}
        }
        titleField.setEnabled(turnOn);
        missingValueAbsent.setEnabled(turnOn);
        missingValuePresent.setEnabled(turnOn);
        newModelMissingValueCode.setEnabled(turnOn);
        stageOneContinuousRadio.setEnabled(turnOn);
        oneRLERadio.setEnabled(turnOn);
        moreThanOneRLERadio.setEnabled(turnOn);
        randomScaleSelectionYes.setEnabled(turnOn);
        randomScaleSelectionNo.setEnabled(turnOn);

        guiStatesSaveButtonModalConfig.setVisible(turnOn);
        guiStatesSaveButtonStageOne.setVisible(turnOn);
        guiStatesSaveButtonStageTwo.setVisible(turnOn);
    }

    private boolean checkTabExistinJTabbedPane(JTabbedPane tabPane, String view_Model) {
        int count = tabPane.getTabCount();
        boolean exist = false;
        for (int i = 0; i < count; i++) {
            if (view_Model == tabPane.getTitleAt(i)) {
                exist = true;
            }
        }

        return exist;
    }

    private void updateGuiView_trigger_NewModelSubmit_TabChange() {
        // add tabs
//        stageOneTabs.insertTab("Postestimation", null, jPanel14, null, 1);

        if (!checkTabExistinJTabbedPane(stageOneTabs, "Stage 1 Configuration")) {
            stageOneTabs.insertTab("Stage 1 Configuration", null, jPanel1, null, 1);
        }

        int stage2TabIdx = stageOneTabs.indexOfTab("Stage 1 Configuration");
        if (!checkTabExistinJTabbedPane(stageOneTabs, "Stage 2 Configuration") && (isOutcomeNone() == false)) {
            stageOneTabs.insertTab("Stage 2 Configuration", null, jPanel12, null, stage2TabIdx + 1);
        }

        // remove tabs
        if (checkTabExistinJTabbedPane(stageOneTabs, "Stage 2 Configuration") && (isOutcomeNone() == true)) {
            stageOneTabs.remove(jPanel12);
        }
        if (checkTabExistinJTabbedPane(stageOneTabs, "Stage 2 Results") && (isOutcomeNone() == true)) {
            stageOneTabs.remove(jPanel4);
        }
    }

    private void updateGuiView_trigger_browse(boolean turnOn) {
        titleViewLabel.setVisible(turnOn);
        titleField.setVisible(turnOn);

        DatasetLabel.setVisible(turnOn);
        missingViewLabel.setVisible(turnOn);
        missingValueAbsent.setVisible(turnOn);
        missingValuePresent.setVisible(turnOn);
        datasetHelpButton.setVisible(turnOn);
        datasetMissingValuesHelpButton.setVisible(turnOn);

        if (turnOn) {
            hiddenBigIconLabel.setIcon(null);
        }

        guiStatesSaveButtonModalConfig.setVisible(turnOn);
        newModel_resetButton.setVisible(turnOn);

    }

    private void updateGuiView_trigger_missingvaluefield() {

        if (missingValuePresent.isSelected()) {
            missingCodeViewLabel.setVisible(true);
            newModelMissingValueCode.setVisible(true);
            newModelMissingValueCode.setText("-999");
            newModelMissingValueCode.selectAll();

            stageOneOutcomeViewLabel.setVisible(true);
            stageOneContinuousRadio.setVisible(true);
            stageOneDichotomousRadio.setVisible(true);
            stageOneOrdinalRadio.setVisible(true);
            stageOneOutcomeHelpButton.setVisible(true);

            rleViewLabel.setVisible(true);
            oneRLERadio.setVisible(true);
            moreThanOneRLERadio.setVisible(true);
            stageOneRLEHelpButton.setVisible(true);
            stageOneRSHelpButton.setVisible(true);

            randomScaleViewLabel.setVisible(true);
            randomScaleSelectionYes.setVisible(true);
            randomScaleSelectionNo.setVisible(true);

            jSeparator16.setVisible(true);
            jSeparator12.setVisible(true);

            stageOneModelGiantLabel.setVisible(true);
        } else if (missingValueAbsent.isSelected()) {
            missingCodeViewLabel.setVisible(false);
            newModelMissingValueCode.setVisible(false);
            newModelMissingValueCode.setText("");

            stageOneOutcomeViewLabel.setVisible(true);
            stageOneContinuousRadio.setVisible(true);
            stageOneDichotomousRadio.setVisible(true);
            stageOneOrdinalRadio.setVisible(true);
            stageOneOutcomeHelpButton.setVisible(true);

            rleViewLabel.setVisible(true);
            oneRLERadio.setVisible(true);
            moreThanOneRLERadio.setVisible(true);
            stageOneRLEHelpButton.setVisible(true);
            stageOneRSHelpButton.setVisible(true);

            randomScaleViewLabel.setVisible(true);
            randomScaleSelectionYes.setVisible(true);
            randomScaleSelectionNo.setVisible(true);

            jSeparator16.setVisible(true);
            jSeparator12.setVisible(true);

            stageOneModelGiantLabel.setVisible(true);
        }

    }

    private void updateGuiView_trigger_stageOneConfig() {
        if ((stageOneOutcomeGroup.getSelection() != null) && (buttonGroup2.getSelection() != null) && (randomScaleSelectionGroup.getSelection() != null)) {
            includeStageTwoLabel.setVisible(true);
            includeStageTwoYes.setVisible(true);
            includeStageTwoNo.setVisible(true);
            stageTwoDescription.setVisible(true);
            stageTwoModelGiantLabel.setVisible(true);
        }
    }

    private void showHiddenBigIconLabel(boolean turnOn) {
        if (turnOn) {
            hiddenBigIconLabel.setIcon(bigIcon);
        } else {
            hiddenBigIconLabel.setIcon(null);

        }
    }

    private boolean isNumeric(String thiscellvalue) {
        return thiscellvalue.matches("-?\\d+(\\.\\d+)?");
    }
}
