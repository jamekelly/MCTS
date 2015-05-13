/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A node in the MCTS
 * @author James
 */


public class MonteNode {

    private int timesVisited;
    private double grossValue;
    private final Checkers state;
    private MonteNode parent;
    private final ArrayList<MonteNode> children = new ArrayList<>();
    private final int lastMoved;
    
    public MonteNode(Checkers state, int lastMoved) {
        this.state = state;
        this.timesVisited = 0;
        this.grossValue = 0;
        this.parent = null;
        this.lastMoved = lastMoved;
    }
    
    public void addVisit() {
        this.timesVisited++;
    }
    
    public void setParent(MonteNode parent) {
        this.parent = parent;
    }
    
    public void addChild(MonteNode child) {
        child.setParent(this);
        this.children.add(child);
    }
    
    public void updateValue(int value) {
        this.grossValue += value;
    }

    public int getTimesVisited() {
        return timesVisited;
    }

    public double getGrossValue() {
        return grossValue;
    }

    public Checkers getState() {
        return state;
    }

    public MonteNode getParent() {
        return parent;
    }
    
    public ArrayList<MonteNode> getChildren() {
        return this.children;
    }

    public boolean isTerminal() {
        return this.state.isOver();
    }
    
    public boolean isExpanded(int turn) {
        int index = 0;
            if(turn <= 1){
               ArrayList<int[]> loc2 = this.state.whoCanMove(2);
               for(int[] piece : loc2){
                   index+= state.numPossibleMoves(2, piece[0], piece[1]);
               }
            }
            else{
                ArrayList<int[]> locs = this.state.whoCanMove(1);
                for(int[] piece : locs){
                    index+= state.numPossibleMoves(1, piece[0], piece[1]);
                }
            }
        return this.children.size() == index;
    }
    
    public boolean isInChild(int[][] board) {
        for(MonteNode child : this.children) {
            if(Arrays.deepEquals(child.getState().getState(), board)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        String arr = "";
        for(int[] row : state.getState()){
            arr = arr + Arrays.toString(row) + "\n";
        }
        return arr;
    }

    public int getLastMoved() {
        return lastMoved;
    }
    
    
}
