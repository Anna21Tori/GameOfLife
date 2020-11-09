package panels;

import tools.Cell;
import tools.DataManager;
import tools.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

public class JCanvasPanel extends JPanel {

    private DataManager dm;
    public JCanvasPanel(DataManager dm){
    this.dm = dm;
    this.setPreferredSize(new Dimension(dm.getWidth()+1, dm.getHeight()+1));
    this.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if(dm.getClickable()) {
                dm.changeStateCell(mouseEvent.getX(), mouseEvent.getY());
                repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    });
    }

    private void initMesh(Graphics2D g2){
        for(int i = 0; i < dm.getWidth(); i++){
            Line2D line = new Line2D.Double(i*dm.getSizeCell(), 0, i*dm.getSizeCell(), dm.getHeight());
            g2.draw(line);
        }

        for(int i = 0; i < dm.getHeight(); i++){
                Line2D line = new Line2D.Double(0, i*dm.getSizeCell(), dm.getWidth(), i*dm.getSizeCell());
                g2.draw(line);
            }
    }

    private void fillCells(Graphics2D g2){
        for(int i = 0; i< dm.getNumCellsHeight(); i++)
            for(int j = 0; j < dm.getNumCellsWidth(); j++) {
                if(dm.getStateCells()[i][j].getState() == 1){
                    int x = dm.getStateCells()[i][j].getX();
                    int y = dm.getStateCells()[i][j].getY();
                    g2.fillRect(x, y, dm.getSizeCell(), dm.getSizeCell());
                }

            }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        fillCells(g2);
        initMesh(g2);
    }

    @Override
    public void repaint() {
        super.repaint();
    }
}

