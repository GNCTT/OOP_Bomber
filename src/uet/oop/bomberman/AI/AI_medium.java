package uet.oop.bomberman.AI;

import uet.oop.bomberman.Map.Map;
import uet.oop.bomberman.state.State;

public class AI_medium {

    private State initState;
    private State goalState;

    public AI_medium() {

    }



    public static void main(String[] args) {
        Map mapp = new Map(1);
        mapp.createMap();
        String s = "  ";
        String[] strings = mapp.get_lineTiles();
        ////

        System.out.println(strings[1]);
    }



}
