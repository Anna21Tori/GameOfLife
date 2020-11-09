package tools;

import panels.JCanvasPanel;


import java.text.DecimalFormat;
import java.util.Random;

public class Utils {

    private final DecimalFormat df = new DecimalFormat("###.##");
    private DataManager dm;
    private JCanvasPanel canvasPanel;


    public Utils(DataManager dm, JCanvasPanel canvasPanel) {
        this.dm = dm;
        this.canvasPanel = canvasPanel;
    }

    public void init(){
        canvasPanel.repaint();
    }

   public void CA(){

       for(int i = 0; i< dm.getNumCellsHeight(); i++) {
           for (int j = 0; j < dm.getNumCellsWidth(); j++) {

               int sum = sumCells(i, j);
               int state = getNextState(sum, dm.getStateCells()[i][j].getState());
               dm.getStateCells()[i][j].setNextState(state);

           }
       }

       refreshState();
       canvasPanel.repaint();
   }

   private int sumCells(int i, int j){
        int sum = 0;
       for( int x = i-1 ;x <= i+1; x++){
           for( int y = j-1 ;y<= j+1;y++){

               if(x == i && y == j){
                   sum += 0;
               }else{
                   int pomX = x;
                   int pomY = y;

                   if(pomX < 0)
                        pomX = dm.getNumCellsHeight() -1;
                   else if(pomX >= dm.getNumCellsHeight())
                        pomX = 0;

                   if(pomY < 0)
                         pomY = dm.getNumCellsWidth() -1;
                   else if (pomY >= dm.getNumCellsWidth())
                        pomY = 0;

                   sum += dm.getStateCells()[pomX][pomY].getState();
               }
           }
       }
       return sum;
   }

   private int getNextState(int sum, int currentState){

        if( currentState == 0) {
            if (sum == 3)
                return 1;
        }else if( currentState == 1) {
            if (sum > 3 || sum < 2)
                return 0;
        }

        return currentState;
   }

   private void refreshState(){
       for(int i = 0; i< dm.getNumCellsHeight(); i++)
           for(int j = 0; j < dm.getNumCellsWidth(); j++){
               dm.getStateCells()[i][j].refreshState();
           }
   }

   public void reset(){
       for(int i = 0; i< dm.getNumCellsHeight(); i++) {
           for (int j = 0; j < dm.getNumCellsWidth(); j++) {
               dm.getStateCells()[i][j].dead();

           }
       }
       canvasPanel.repaint();
   }

   public void setClickable(Boolean state){
        dm.setClickable(state);
   }

    public void setRandomCells(int num){
        Random generator = new Random();
        int minX = 0;
        int maxX = dm.getNumCellsHeight()-1;
        int minY = 0;
        int maxY = dm.getNumCellsWidth()-1;
        int randomX;
        int randomY;
        reset();
        for( int i = 0; i< num ;i++) {
            do {
                randomX = generator.nextInt((maxX - minX) + 1) + minX;
                randomY = generator.nextInt((maxY - minY) + 1) + minY;
            }while(dm.getStateCells()[randomX][randomY].getState() == 1);

            dm.getStateCells()[randomX][randomY].alive();
        }
        canvasPanel.repaint();
    }

    public int getRandomX(){
        Random generator = new Random();
        int minX = 0;
        int maxX = dm.getNumCellsHeight()-1;
        int randomX = generator.nextInt((maxX - minX) + 1) + minX;

        return randomX;
    }

    public int getRandomY(){
        Random generator = new Random();
        int minY = 0;
        int maxY = dm.getNumCellsWidth()-1;
        int randomY = generator.nextInt((maxY - minY) + 1) + minY;

        return randomY;
    }

    public void generatePattern(int [][] pattern){

        int x = getRandomX();
        int y = getRandomY();

        for(int i = 0; i < pattern.length; i++){
            for(int j = 0 ; j < pattern[0]. length; j++){

                int n =  x + i;
                int m =  y + j;

                if(n >= dm.getNumCellsHeight())
                    n = -1 + i;


                else if (m >= dm.getNumCellsWidth())
                    m = -1 + j;

                dm.getStateCells()[n][m].setState(pattern[i][j]);
            }
        }
        canvasPanel.repaint();
    }
}
