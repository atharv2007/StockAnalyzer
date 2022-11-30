package stock.view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

public class SwingViewFrame extends JFrame implements ActionListener, ItemListener, ListSelectionListener {
  private JPasswordField pfield;
  private JButton pButton;
  private JLabel pDisplay;
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;

  private JLabel checkboxDisplay;
  private JLabel radioDisplay;
  private JLabel comboboxDisplay;
  private JLabel colorChooserDisplay;
  private JLabel fileOpenDisplay;
  private JLabel fileSaveDisplay;
  private JLabel inputDisplay;
  private JLabel optionDisplay;

  private JList<String> listOfStrings;
  private JList<Integer> listOfIntegers;


    public SwingViewFrame(){
        super();
        setTitle("Swing features");
        setSize(400, 400);


        mainPanel = new JPanel();
        //for elements to be arranged vertically within this panel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        //scroll bars around this main panel
        mainScrollPane = new JScrollPane(mainPanel);
        add(mainScrollPane);

        JPanel dialogBoxesPanel = new JPanel();
        dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
        dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(dialogBoxesPanel);

        //file open
        JPanel fileopenPanel = new JPanel();
        fileopenPanel.setLayout(new FlowLayout());
        dialogBoxesPanel.add(fileopenPanel);
        JButton fileOpenButton = new JButton("Open a file");
        fileOpenButton.setActionCommand("Open file");
        fileOpenButton.addActionListener(this);
        fileopenPanel.add(fileOpenButton);
        fileOpenDisplay = new JLabel("File path will appear here");
        fileopenPanel.add(fileOpenDisplay);

        //file save
        JPanel filesavePanel = new JPanel();
        filesavePanel.setLayout(new FlowLayout());
        dialogBoxesPanel.add(filesavePanel);
        JButton fileSaveButton = new JButton("Save a file");
        fileSaveButton.setActionCommand("Save file");
        fileSaveButton.addActionListener(this);
        filesavePanel.add(fileSaveButton);
        fileSaveDisplay = new JLabel("File path will appear here");
        filesavePanel.add(fileSaveDisplay);
    }

  /**
     * Invoked when an action occurs.
     *
     * @param arg0 the event to be processed
     */
  @Override
  public void actionPerformed(ActionEvent arg0) {
      switch (arg0.getActionCommand()) {
          case "Open file": {
              final JFileChooser fchooser = new JFileChooser(".");
              FileNameExtensionFilter filter = new FileNameExtensionFilter(
                      "CSV Files", "csv");
              fchooser.setFileFilter(filter);
              int retvalue = fchooser.showOpenDialog(SwingViewFrame.this);
              if (retvalue == JFileChooser.APPROVE_OPTION) {
                  File f = fchooser.getSelectedFile();
                  fileOpenDisplay.setText(f.getAbsolutePath());
              }
          }
          break;
          case "Save file": {
              final JFileChooser fchooser = new JFileChooser(".");
              int retvalue = fchooser.showSaveDialog(SwingViewFrame.this);
              if (retvalue == JFileChooser.APPROVE_OPTION) {
                  File f = fchooser.getSelectedFile();
                  fileSaveDisplay.setText(f.getAbsolutePath());
              }
          }
          break;
      }
  }

  /**
     * Invoked when an item has been selected or deselected by the user.
     * The code written for this method performs the operations
     * that need to occur when an item is selected (or deselected).
     *
     * @param e the event to be processed
     */
  @Override
  public void itemStateChanged(ItemEvent e) {
  }

  /**
     * Called whenever the value of the selection changes.
     *
     * @param e the event that characterizes the change.
     */
  @Override
  public void valueChanged(ListSelectionEvent e) {
  }
}
