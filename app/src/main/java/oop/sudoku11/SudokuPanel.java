package oop.sudoku11;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class SudokuPanel extends JPanel {

    private SudokuPuzzle puzzle;
    private SudokuPuzzle key;
    private int currentlySelectedCol;
    private int currentlySelectedRow;
    private int usedWidth;
    private int usedHeight;
    private int fontSize;

    public SudokuPanel(String difficulty) {
        SudokuGenerator object = new SudokuGenerator();
        this.setPreferredSize(new Dimension(540, 450));
        this.addMouseListener(new SudokuPanelMouseAdapter());
        this.puzzle = object.getPuz();
        this.key = object.getKey();
        currentlySelectedCol = -1;
        currentlySelectedRow = -1;
        usedWidth = 0;
        usedHeight = 0;
        fontSize = 26;
    }

    public void newSudokuPuzzle(SudokuPuzzle puzzle, SudokuPuzzle key) {
        this.puzzle = puzzle;
        this.key = key;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(1.0f, 1.0f, 1.0f));

        int slotWidth = this.getWidth() / puzzle.getNumColumns();
        int slotHeight = this.getHeight() / puzzle.getNumRows();

        usedWidth = (this.getWidth() / puzzle.getNumColumns()) * puzzle.getNumColumns();
        usedHeight = (this.getHeight() / puzzle.getNumRows()) * puzzle.getNumRows();

        g2d.fillRect(0, 0, usedWidth, usedHeight);

        for(int row = 0; row < puzzle.getNumRows(); row++) {
            for(int col = 0; col < puzzle.getNumColumns(); col++) {
                int row2 = currentlySelectedRow;
                int col2 = currentlySelectedCol;
                    if (puzzle.getValue(row, col) == puzzle.getValue(row2, col2) && puzzle.getValue(row, col) != ""
                    && col != col2 && row == row2) {
                        g2d.setColor(new Color(1.0f, 1.0f, 1.0f));
                        g2d.fillRect(col * slotWidth, row * slotHeight, slotWidth, slotHeight);
                        g2d.setColor(new Color(0.0f, 0.0f, 1.0f, 0.3f));
                        g2d.fillRect(col * slotWidth, row * slotHeight, slotWidth, slotHeight);
                    }
            }
        }
        
        for(int col = 0; col < puzzle.getNumColumns(); col++) {
            for(int row = 0; row < puzzle.getNumRows(); row++) {
                int row2 = currentlySelectedRow;
                int col2 = currentlySelectedCol;
                    if (puzzle.getValue(row, col) == puzzle.getValue(row2, col2) && puzzle.getValue(row, col) != ""
                    && row != row2 && col == col2) {
                        g2d.setColor(new Color(1.0f, 1.0f, 1.0f));
                        g2d.fillRect(col * slotWidth, row * slotHeight, slotWidth, slotHeight);
                        g2d.setColor(new Color(0.0f, 0.0f, 1.0f, 0.3f));
                        g2d.fillRect(col * slotWidth, row * slotHeight, slotWidth, slotHeight);
                    }
            }
        }

        // buat ngubah warna ubin yang diteken
        if (currentlySelectedCol != -1 && currentlySelectedRow != -1) {
            g2d.setColor(new Color(1.0f, 1.0f, 1.0f));
            g2d.fillRect(currentlySelectedCol * slotWidth, currentlySelectedRow * slotHeight, slotWidth, slotHeight);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(currentlySelectedCol * slotWidth, currentlySelectedRow * slotHeight, slotWidth, slotHeight);
        }

        g2d.setColor(new Color(0.0f, 0.0f, 0.0f));
        for (int x = 0; x <= usedWidth; x += slotWidth) {
            if ((x / slotWidth) % puzzle.getBoxWidth() == 0) {
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(x, 0, x, usedHeight);
            } else {
                g2d.setStroke(new BasicStroke(1));
                g2d.drawLine(x, 0, x, usedHeight);
            }
        }
        // this will draw the right most line
        // g2d.drawLine(usedWidth - 1, 0, usedWidth - 1,usedHeight);
        for (int y = 0; y <= usedHeight; y += slotHeight) {
            if ((y / slotHeight) % puzzle.getBoxHeight() == 0) {
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(0, y, usedWidth, y);
            } else {
                g2d.setStroke(new BasicStroke(1));
                g2d.drawLine(0, y, usedWidth, y);
            }
        }
        // this will draw the bottom line
        // g2d.drawLine(0, usedHeight - 1, usedWidth, usedHeight - 1);

        // biar text nya di tengah kotak
        Font f = new Font("Times New Roman", Font.PLAIN, fontSize);
        g2d.setFont(f);
        FontRenderContext fContext = g2d.getFontRenderContext();
        for (int row = 0; row < puzzle.getNumRows(); row++) {
            for (int col = 0; col < puzzle.getNumColumns(); col++) {
                //System.out.println("Dari Panel");
                //System.out.println(puzzle.getValue(row, col) + " " + key.getValue(row, col));
                if (!puzzle.isSlotAvailable(row, col)) {
                    int textWidth = (int) f.getStringBounds(puzzle.getValue(row, col), fContext).getWidth();
                    int textHeight = (int) f.getStringBounds(puzzle.getValue(row, col), fContext).getHeight();
                    if(puzzle.isSlotMutable(row, col)) {
                        if(puzzle.getValue(row, col) == key.getValue(row, col)) {
                            g2d.setColor(Color.GREEN);
                        } else {
                            g2d.setColor(Color.RED);
                        }
                    } else {
                        g2d.setColor(Color.BLACK);
                    }
                    g2d.drawString(puzzle.getValue(row, col), (col * slotWidth) + ((slotWidth / 2) - (textWidth / 2)),
                            (row * slotHeight) + ((slotHeight / 2) + (textHeight / 2)));
                }
            }
        }
    }

    public void messageFromNumActionListener(String buttonValue) {
        if (currentlySelectedCol != -1 && currentlySelectedRow != -1) {
            puzzle.makeMove(currentlySelectedRow, currentlySelectedCol, buttonValue, true);
            repaint();
        }
    }

    public class NumActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            messageFromNumActionListener(((JButton) e.getSource()).getText());
        }
    }

    private class SudokuPanelMouseAdapter extends MouseInputAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                int slotWidth = usedWidth / puzzle.getNumColumns();
                int slotHeight = usedHeight / puzzle.getNumRows();
                currentlySelectedRow = e.getY() / slotHeight;
                currentlySelectedCol = e.getX() / slotWidth;
                e.getComponent().repaint();
            }
        }
    }
}
