package oop.sudoku11;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Sudoku extends JFrame {

    private JPanel buttonSelectionPanel;
    private SudokuPanel sPanel;

    public Sudoku(String difficulty) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sudoku11");
        this.setMinimumSize(new Dimension(800, 600));

        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu level = new JMenu("Difficulty");
        JMenu about = new JMenu("About");
        JMenu help = new JMenu("Help");

        JMenuItem restart = new JMenuItem("Restart");
        JMenuItem clear = new JMenuItem("Clear");
        JMenuItem exit = new JMenuItem("Exit");

        JMenuItem easy = new JMenuItem("Easy");
        JMenuItem medium = new JMenuItem("Medium");
        JMenuItem hard = new JMenuItem("Hard");

        //restart.addActionListener(new NewGameListener("easy"));

        easy.addActionListener(new NewGameListener("easy"));
        medium.addActionListener(new NewGameListener("medium"));
        hard.addActionListener(new NewGameListener("hard"));

        file.add(restart);
        file.add(clear);
        file.add(exit);

        level.add(easy);
        level.add(medium);
        level.add(hard);
        
        menuBar.add(file);
        menuBar.add(level);
        menuBar.add(about);
        menuBar.add(help);

        this.setJMenuBar(menuBar);

        JPanel windowPanel = new JPanel();
        windowPanel.setLayout(new FlowLayout());
        windowPanel.setPreferredSize(new Dimension(800, 600));

        buttonSelectionPanel = new JPanel();
        buttonSelectionPanel.setPreferredSize(new Dimension(200, 500));

        sPanel = new SudokuPanel("easy");

        windowPanel.add(sPanel);
        windowPanel.add(buttonSelectionPanel);
        this.add(windowPanel);

        rebuildInterface("easy");
    }

    public void rebuildInterface(String difficulty) {
        SudokuGenerator object = new SudokuGenerator();
        SudokuPuzzle generatedPuzzle = object.generateRandomSudoku(difficulty);
        SudokuPuzzle generatedKey = object.getKey();
        sPanel.newSudokuPuzzle(generatedPuzzle, generatedKey);
        buttonSelectionPanel.removeAll();
        for (String value : generatedPuzzle.getValidValues()) {
            JButton b = new JButton(value);
            b.setPreferredSize(new Dimension(60, 60));
            b.addActionListener(sPanel.new NumActionListener());
            buttonSelectionPanel.add(b);
        }
        sPanel.repaint();
        buttonSelectionPanel.revalidate();
        buttonSelectionPanel.repaint();
    }

    private class NewGameListener implements ActionListener {

        private String difficulty;

        public NewGameListener(String difficulty) {
            this.difficulty = difficulty;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            rebuildInterface(difficulty);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Sudoku frame = new Sudoku("easy");
                frame.setVisible(true);
                /*
                String difficulty = args[0];
                if (difficulty.equals("easy")) {
                    Sudoku frame = new Sudoku("easy");
                    frame.setVisible(true);
                } else if (difficulty.equals("medium")){
                    Sudoku frame = new Sudoku("medium");
                    frame.setVisible(true);
                } else if (difficulty.equals("hard")){
                    Sudoku frame = new Sudoku("hard");
                    frame.setVisible(true);
                } else {
                    System.out.println("Masukkan tingkat kesulitan");
                    System.out.println("java Sudoku easy");
                    System.out.println("java Sudoku medium");
                    System.out.println("java Sudoku hard");
                }
                */
            }
        });
    }
}