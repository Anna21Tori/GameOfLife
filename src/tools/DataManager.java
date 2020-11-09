package tools;

public class DataManager {


    private int width, height;
    private int sizeCell;
    private int numCellsWidth;
    private int numCellsHeight;
    private Boolean clickable;
    private Cell [][] stateCells;

    public DataManager(int width, int height, int sizeCell) {
        this.width = width;
        this.height = height;
        this.sizeCell = sizeCell;
        initNumCells();
        initArrayStateCells();
        this.clickable = false;
    }

    public void initArrayStateCells(){
         this.stateCells = new Cell[this.numCellsHeight][this.numCellsWidth];

         for(int i = 0; i< this.numCellsHeight; i++)
             for(int j = 0; j < this.numCellsWidth; j++)
                 this.stateCells[i][j] = new Cell(j*this.sizeCell, i*this.sizeCell, 0);
    }

    public void initNumCells(){
        this.numCellsWidth = this.width/sizeCell;
        this.numCellsHeight = this.height/sizeCell;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSizeCell() {
        return sizeCell;
    }

    public int getNumCellsWidth() {
        return numCellsWidth;
    }

    public int getNumCellsHeight() {
        return numCellsHeight;
    }

    public Cell[][] getStateCells() {
        return stateCells;
    }

    public void changeStateCell(int MouseX, int MouseY){

        for(int i = 0; i< this.getNumCellsHeight(); i++){
            for (int j = 0; j < this.getNumCellsWidth(); j++){
                int x = this.getStateCells()[i][j].getX();
                int y = this.getStateCells()[i][j].getY();

                if( Math.abs(x - MouseX) <= this.getSizeCell() && Math.abs(x + this.getSizeCell() -MouseX) <= this.getSizeCell()){
                    if( Math.abs(y - MouseY) <= this.getSizeCell() && Math.abs(y + this.getSizeCell() -MouseY) <= this.getSizeCell()){
                        if(this.getStateCells()[i][j].getState() == 1)
                            this.getStateCells()[i][j].dead();
                        else
                            this.getStateCells()[i][j].alive();
                    }
                }

            }
        }

    }

    public void setClickable(Boolean clickable) {
        this.clickable = clickable;
    }

    public Boolean getClickable() {
        return clickable;
    }

    public int [][] getBlock(){
        int [][] block = {
                {1,1},
                {1,1}
        };

        return block;
    }

    public int [][] getBeehive(){
        int [][] beehive = {
                {0, 1, 1, 0},
                {1, 0, 0, 1},
                {0, 1, 1, 0}
        };

        return beehive;
    }

    public int [][] getLoaf(){
        int [][] loaf = {
                {0, 1, 1, 0},
                {1, 0, 0, 1},
                {0, 1, 0, 1},
                {0, 0, 1, 0}
        };

        return loaf;
    }

    public int [][] getBoat(){
        int [][] boat = {
                {1, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        };

        return boat;
    }

    public int [][] getBlinker(){
        int [][] blinker = {
                {1},
                {1},
                {1}
        };

        return blinker;
    }

    public int [][] getToad(){
        int [][] toad = {
                {0, 1, 1, 1},
                {1, 1, 1, 0}
        };

        return toad;
    }

    public int [][] getBeacon(){
        int [][] beacon = {
                {1, 1, 0, 0},
                {1, 0, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 1}
        };

        return beacon;
    }

    public int [][] getGlider(){
        int [][] glider = {
                {0, 0, 1},
                {1, 0, 1},
                {0, 1, 1}
        };

        return glider;
    }

    public int [][] getLightweightSpaceship(){
        int [][] lightweightSpaceship = {
                {1, 0, 0, 1, 0},
                {0, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {0, 1, 1, 1, 1}
        };

        return lightweightSpaceship;
    }
}
