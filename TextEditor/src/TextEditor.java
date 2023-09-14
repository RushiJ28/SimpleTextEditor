import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TextEditor implements ActionListener {

    //Declaring properties of text editor
    JFrame frame;

    JMenuBar menuBar;

    JMenu file, edit;

    JMenuItem newFile, openFile, saveFile;

    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;


    TextEditor(){
        //initialize frame
        frame = new JFrame();

        //initialize menu-bar
        menuBar = new JMenuBar();

        //initialize text area
        textArea = new JTextArea();

        //initialize menu titles
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //add menu titles to menu-bar
        menuBar.add(file);
        menuBar.add(edit);

        //initialize file items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //add actionListener to the file menu
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //add file-menu items in file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initialize edit items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //add actionListener to edit menu
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //add edit items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //add menuBar to frame
        frame.setJMenuBar(menuBar);

        //create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        //add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        //create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);
        //set dimension of frame
        frame.setBounds(100, 100, 500, 500);
        frame.setVisible(true);
        frame.setLayout(null);

    }
    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //perform cut operation
        if(e.getSource() == cut) textArea.cut();

        //perform copy operation
        if(e.getSource() == copy) textArea.copy();

        //perform paste operation
        if(e.getSource() == paste) textArea.paste();

        //perform select All operation
        if(e.getSource() == selectAll) textArea.selectAll();

        //perform close operation
        if(e.getSource() == close) System.exit(0);

        //perform openFile operation
        if(e.getSource() == openFile) {
            //Open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            //if we clicked on open button
            if(chooseOption == JFileChooser.APPROVE_OPTION) {
                //getting selected file
                File file = fileChooser.getSelectedFile();
                //get the path of selected file
                String filePath = file.getPath();
                try {
                    FileReader fileReader = new FileReader(filePath);
                    //initialize buffer reader
                    BufferedReader bufferReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //read contents of file line by line
                    while ((intermediate = bufferReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        }
        if(e.getSource() == saveFile) {
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //check if we clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION) {
                //create a new file with choose directory path and fine name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize buffer writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write content of the text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(e.getSource() == newFile) {
            TextEditor newTextEditor = new TextEditor();
        }
    }
}