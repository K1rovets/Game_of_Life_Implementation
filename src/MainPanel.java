import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

public class MainPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
    private final int xWidth = 1400;
    private final int yHeight = 800;
    private final int size = 20;
    private final int xNumber = xWidth / size;
    private final int yNumber = yHeight / size;

    private final int[][] cellGrid = new int[xNumber][yNumber];
    private final int[][] nextCellGrid = new int[xNumber][yNumber];

    private boolean isPressed = false;
    private boolean stop = false;
    private boolean start = true;

    private final Timer timer;

    private int toSurvive_1 = 3;
    private int toSurvive_2 = 2;
    private int toLive = 3;

    public MainPanel() {
        setSize(xWidth, yHeight);
        setLayout(null);
        setBackground(Color.BLACK);

        this.addMouseMotionListener(this);
        addMouseListener(this);

        timer = new Timer(80, this);
        timer.start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        grid(graphics);
        randomGrid();
        display(graphics);
    }

    public void setToSurvive_1(int modifier) {
        toSurvive_1 = modifier;
    }

    public void setToSurvive_2(int modifier) {
        toSurvive_2 = modifier;
    }

    public void setToLive(int modifier) {
        toLive = modifier;
    }

    public void reset() {
        for (int[] ints : nextCellGrid) {
            Arrays.fill(ints, 0);
        }
    }

    //Private methods ====================================================================

    private void grid(Graphics graphics) {
        for (int i = 0; i < cellGrid.length; i++) {
            graphics.drawLine(0, i * size, xWidth, i * size); // to draw rows
            graphics.drawLine(i * size, 0, i*size, yHeight); //to draw columns
        }
    }

    private void randomGrid() {
        if (start) {
            for (int x = 0; x < cellGrid.length; x++) {
                for (int y = 0; y < cellGrid[x].length; y++) {
                    if ( (int) (Math.random() * 5) == 0) {
                        nextCellGrid[x][y] = 1;
                    }
                }
            }
            start = false;
        }

    }

    private void display(Graphics graphics) {
        graphics.setColor(new Color(138,43,226));
        copyArray();
        for (int x = 0; x < cellGrid.length; x++) {
            for (int y = 0; y < cellGrid[x].length; y++) {
                if (cellGrid[x][y] == 1) {
                    graphics.fillRect(x * size, y * size, size, size);
                }
            }
        }
    }

    private void copyArray() {
        for (int x = 0; x < cellGrid.length; x++) {
            System.arraycopy(nextCellGrid[x], 0, cellGrid[x], 0, cellGrid[x].length);
        }
    }

    private int check(int x, int y) {
        int alive = 0;
        alive += cellGrid[(x + xNumber - 1) % xNumber] [(y + yNumber - 1) % yNumber]; // upper left
        alive += cellGrid[(x + xNumber) % xNumber]     [(y + yNumber - 1) % yNumber]; // up
        alive += cellGrid[(x + xNumber + 1) % xNumber] [(y + yNumber -1) % yNumber]; // upper right

        alive += cellGrid[(x + xNumber + 1) % xNumber] [(y + yNumber) % yNumber]; // right
        alive += cellGrid[(x + xNumber - 1) % xNumber] [(y + yNumber) % yNumber]; // left

        alive += cellGrid[(x + xNumber - 1) % xNumber] [(y + yNumber + 1) % yNumber]; // down left
        alive += cellGrid[(x + xNumber) % xNumber]     [(y + yNumber + 1) % yNumber]; // down
        alive += cellGrid[(x + xNumber + 1) % xNumber] [(y + yNumber + 1) % yNumber]; // down right
        return alive;
    }

    //Interface methods ====================================================================

    @Override
    public void actionPerformed(ActionEvent e) {
        int neighbour;
        for (int x = 0; x < cellGrid.length; x++) {
            for (int y = 0; y < cellGrid[x].length; y++) {
                neighbour = check(x, y);

                if (neighbour == toSurvive_1 && cellGrid[x][y] == 1) {
                    nextCellGrid[x][y] = 1;
                }
                else if (neighbour == toSurvive_2 && cellGrid[x][y] == 1) {
                    nextCellGrid[x][y] = 1;
                }
                else if (neighbour == toLive && cellGrid[x][y] == 0) {
                    nextCellGrid[x][y] = 1;
                }
                else {
                    nextCellGrid[x][y] = 0;
                }
            }
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3 && !stop) {
            timer.stop();
            stop = true;
        }

        else if (e.getButton() == MouseEvent.BUTTON3 && stop) {
            timer.start();
            stop = false;
        }

        if(e.getButton() == MouseEvent.BUTTON2) {
            reset();
        }

        int x = e.getX() / size;
        int y = e.getY() / size;

        if (e.getButton() == MouseEvent.BUTTON1) {
            isPressed = true;
            if (cellGrid[x][y] == 0) {
                nextCellGrid[x][y] = 1;
            }
            else if (cellGrid[x][y] == 1) {
                nextCellGrid[x][y] = 0;
            }
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            isPressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        int x = e.getX() / size;
        int y = e.getY() / size;

        if (isPressed) {
            if (cellGrid[x][y] == 0) {
                nextCellGrid[x][y] = 1;
            }
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
