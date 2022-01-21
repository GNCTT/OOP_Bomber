package uet.oop.bomberman.state;

public class State {

    private boolean canMove;
    private char nameState;
    private int x;
    private int y;

    public State() {

    }

    public State(boolean canMove, char nameState) {
        this.canMove = canMove;
        this.nameState = nameState;
    }

    public char getNameState() {
        return nameState;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public void setNameState(char nameState) {
        this.nameState = nameState;
    }

}
