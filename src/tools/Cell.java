package tools;

public class Cell {

    private int x;
    private int y;
    private int nextState;
    private int state;

    public Cell(int x, int y, int state) {
        this.x = x;
        this.y = y;
        this.state = state;
        this.nextState = 0;
    }

    public void setNextState(int nextState) {
        this.nextState = nextState;
    }

    public void refreshState(){
        state = nextState;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getState() {
        return state;
    }

    public void dead(){
        this.state = 0;
    }

    public void alive(){
        this.state = 1;
    }

    public void setState(int state) {
        this.state = state;
    }
}
