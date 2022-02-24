package uet.oop.bomberman.AI_f;

import uet.oop.bomberman.BombermanGame;

import java.util.ArrayList;

public class State {

    private char State_name;
    private int x;
    private int y;
    private char direction;
    private State parent;
    private ArrayList<State> children;

    public State() {

    }

    public State(char State_name, int x, int y) {
        this.State_name = State_name;
        this.x = x;
        this.y = y;
    }

    public State(char State_name, int x, int y, char direction, State parent) {
        this.State_name = State_name;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.parent = parent;
    }


    public char getState_name() {
        return State_name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getDirection() {
        return direction;
    }

    public State getParent() {
        return parent;
    }

    public ArrayList<State> getChildren() {
        ArrayList<State> child = new ArrayList<>();
        char N_name = BombermanGame.map.getChar(x, y -1);
        char E_name = BombermanGame.map.getChar(x + 1, y);
        char S_name = BombermanGame.map.getChar(x, y + 1);
        char W_name = BombermanGame.map.getChar(x - 1, y);
        if (N_name == ' ') {
            State N_State = new State(' ', x, y - 1, 'N', this);
            child.add(N_State);
        }
        if (E_name == ' ') {
            State E_State = new State(' ', x + 1, y, 'E', this);
            child.add(E_State);
        }
        if (S_name == ' ') {
            State S_State = new State(' ', x, y + 1, 'S', this);
            child.add(S_State);
        }
        if (W_name == ' ') {
            State W_State = new State(' ', x - 1, y, 'W', this);
            child.add(W_State);
        }
        children = child;
        return children;
    }

    public void setState_name(char state_name) {
        State_name = state_name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public void setParent(State parent) {
        this.parent = parent;
    }

    public void setChildren(ArrayList<State> children) {
        this.children = children;
    }
}
