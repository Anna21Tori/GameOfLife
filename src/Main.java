import tools.DataManager;
import tools.Utils;
import panels.JCanvasPanel;
import panels.ToolsPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Main extends JFrame {

    private JPanel mainPanel;
    private JPanel mapPanel;
    private ToolsPanel toolsPanel;
    private JCanvasPanel canvas;
    private DataManager dm;
    private Utils utils;
    private final int width = 1000;
    private final int height= 1000;

    private void layoutApp(){
        //==============================================================================================================


        mainPanel = new JPanel();


        mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));

        //==============================================================================================================
        try {
            toolsPanel.addTools();
        }catch (Exception e){
            System.out.println(e);
        }
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(canvas, BorderLayout.NORTH);
        mainPanel.add(toolsPanel, BorderLayout.SOUTH);
       // mainPanel.add(hist, BorderLayout.WEST);
        //==============================================================================================================

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setContentPane(mainPanel);
        this.setSize(new Dimension(width+1, height));
        this.setLocationRelativeTo(null);
    }

    public Main(String title) {

        super(title);


        this.dm = new DataManager(900, 500, 20);
        canvas = new JCanvasPanel(this.dm);

        utils = new Utils(dm, canvas);
        toolsPanel = new ToolsPanel(this.utils, this.dm);
        this.layoutApp();
        utils.init();

    }

    public static void main(String[] args) {

        Main mw = new Main("Mapa - Anna Dybel");
        mw.setVisible(true);

    }

}

