/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to perform MCTS on a game of checkers
 * @author James
 */
public class MonteCarlo {
    
    private final double constant = 1 / Math.sqrt(2);
    private final int i;
    
    public MonteCarlo(int i){
        this.i = i;
    }
    
    
    
    /**
     * Method that searches the tree and picks the best child node
     * Performs MCTS on root
     * @param root
     * @return
     * @throws Exception
     * @throws NoSuchMethodException 
     */
    public MonteNode searchForOne(MonteNode root) throws Exception, NoSuchMethodException {
        for(int k = 0;k < i;k++) {
            MonteNode next = this.explore(root);
            int value = this.playout(next);
            this.backPropagate(next, value);
        }
        MonteNode bn = this.chooseBestNode(root, 0);
        return bn;
    }
    
   
    
    /**
     * Method that updates the values of nodes in the tree
     * @param node
     * @param value 
     */
    public void backPropagate(MonteNode node, int value) {
        
        if(node.getLastMoved() <= 1)
            value = -value;
        while(node != null) {
            node.addVisit();
            node.updateValue(value);
            node = node.getParent();
            value = -value;
        }
    }
    
    /**
     * Method that returns either a new child node for par,
     * or returns par if it is terminal
     * @param par
     * @return 
     */
    public MonteNode explore(MonteNode par) {
        while(!par.isTerminal()) {
            if(!par.isExpanded(par.getLastMoved())) {
                return this.expand(par);
            }
            else {
                par = this.chooseBestNode(par, constant);
            }
        }
        return par;
    }
    
    /**
     * Simulates a game of random moves
     * @param monte
     * @return
     * @throws Exception 
     */
    public int playout(MonteNode monte) throws Exception {
        return monte.getState().play(monte.getLastMoved());
    }
    
    /**
     * Chooses an unvisited child of par to add to the tree
     * @param par
     * @return the new child node 
     */
    public MonteNode expand(MonteNode par) {
        Checkers check = new Checkers();
        MonteNode newKid = null;
        ArrayList<int[]> men;
        if(par.getLastMoved() <= 1){    
            men = par.getState().whoCanMove(2);
        }
        else{
            men = par.getState().whoCanMove(1);
        }
        for(int[] piece : men) {
            ArrayList<int[][]> posBoards = null;
            if(par.getLastMoved() <= 1){
                posBoards = par.getState().tryMoves(2, piece[0], piece[1]);
            }
            else{
                posBoards = par.getState().tryMoves(1, piece[0], piece[1]);
            }
            for (int[][] posBoard : posBoards) {
                if (!par.isInChild(posBoard) && posBoard != null)  {
                    check.setState(posBoard);
                    if(par.getLastMoved() <= 1){
                        newKid = new MonteNode(check, 2);}
                    else{
                        newKid = new MonteNode(check, 1);
                    }
                    par.addChild(newKid);
                    return newKid;
                }
            }
        }
        System.out.println("BAD");
        return newKid;
    }

    /**
     * Chooses the best node to use as a move
     * @param par
     * @param cons
     * @return 
     */
    public MonteNode chooseBestNode(MonteNode par, double cons) {
        MonteNode bestNode = null;
        double gameValue = -i - 1;
        double numer = 2 * Math.log(par.getTimesVisited());
        ArrayList<MonteNode> kids = par.getChildren();
        for(int i = 0;i < kids.size();i++){
            MonteNode kid = kids.get(i);
            double possible = (kid.getGrossValue() / kid.getTimesVisited()) +
                    (cons * Math.sqrt(numer / kid.getTimesVisited()));
            if(possible > gameValue) {
                gameValue = possible;
                bestNode = kid;
            }
        }
        return bestNode;
    }
    
    /**
     * runs a simulation a certain number of times,prints the results 
     * @param numberOfRuns
     * @throws Exception 
     */
    
    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        MonteCarlo monte1 = new MonteCarlo(100);
        Checkers c = new Checkers();
        MonteNode s = new MonteNode(c, 0);
        FullView fv = new FullView(c.getState());
        Ref ref = new Ref(monte1,fv);
        ref.playMCTS(s, monte1);
    }
}
