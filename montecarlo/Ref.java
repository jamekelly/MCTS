/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/**
 *
 * @author James
 */
public class Ref {
    
    private final MonteCarlo mc;
    private final FullView fv;
    
    
        
    /*public MonteNode startGUI(MonteNode root, MonteCarlo m1, MonteCarlo m2) throws Exception{
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(30, 30, 850, 850);
        BoardView board = new BoardView(root.getState().getState(), 80);
        window.getContentPane().add(board);
        window.setVisible(true);
        return this.playAgainstMCTS(root, board, m1, m2);
    }*/
    
    public Ref(MonteCarlo mc, FullView fv){
        this.mc = mc;
        this.fv = fv;
    }
    
    /*public MonteNode startGUI(MonteNode root, MonteCarlo m1) throws Exception{
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(30, 30, 850, 850);
        BoardView board = new BoardView(root.getState().getState(), 80);
        window.getContentPane().add(board);
        
        window.setVisible(true);
        return this.playAgainstMCTS(root, board, m1);
    }*/

    /*public MonteNode playAgainstMCTS(MonteNode root, BoardView comp, MonteCarlo m1, MonteCarlo m2) throws Exception, NoSuchMethodException {
        System.out.println("ROOT OF SEARCH --- " + root.getLastMoved() + " just moved");
        comp.setModel(root.getState().getState());
        comp.repaint();
        if(root.isTerminal()){
            return root;
        } else if(root.getLastMoved() <= 1){
            root = m1.searchForOne(root);
            return this.playAgainstMCTS(root, comp, m1, m2);
        } else if(root.getLastMoved() > 1){
            root = m2.searchForOne(root);
            return this.playAgainstMCTS(root, comp, m1, m2);
        }
        return root;
    }*/
    
    /*public MonteNode playAgainstMCTS(MonteNode root, BoardView comp, MonteCarlo m1) throws Exception, NoSuchMethodException {
        
        System.out.println("ROOT OF SEARCH --- " + root.getLastMoved() + " just moved");
        comp.setModel(root.getState().getState());
        comp.repaint();
        if(root.isTerminal()){
            return root;
        } else if(root.getLastMoved() <= 1){
            root = m1.searchForOne(root);
            return this.playAgainstMCTS(root, comp, m1);
        } else if(root.getLastMoved() > 1){
            /* -- When uncommented, user plays the AI -- 
            root = root.getState().userMove();

            /* -- When 2 lines below are uncommented, 1's move is chosen randomly -- 
            //Checkers c = new Checkers(root.getState().makeGoodMove());
            //root = new MonteNode(c, 1);

            return this.playAgainstMCTS(root, comp, m1);
        }
        return root;
    }*/
    
    public void playMCTS(final MonteNode root, MonteCarlo m1) throws Exception, NoSuchMethodException {
        
        //System.out.println("ROOT OF SEARCH --- " + root.getLastMoved() + " just moved");
        fv.setState(root.getState().getState());
        //comp.repaint();
        if(root.isTerminal()){
            return;
        } else if(root.getLastMoved() <= 1){
            MonteNode roo = m1.searchForOne(root);
            this.playMCTS(roo, m1);
        } else if(root.getLastMoved() > 1){
            /* -- When uncommented, user plays the AI -- */
            if (this.fv.getComp().getMouseListeners().length > 0){
                this.fv.getComp().removeMouseListener(this.fv.getComp().getMouseListeners()[0]);
            }
            this.fv.getComp().addMouseListener(new MouseAdapter() {
                
                public void mouseClicked(MouseEvent e){
                    int xpt = e.getX() / fv.getComp().getSquareSize();
                    int ypt = e.getY() / fv.getComp().getSquareSize();
                    System.out.println("X: " + xpt + " Y: " + ypt);
                    MonteNode roo = root.getState().userMove(xpt, ypt);
                    try {
                        Ref.this.playMCTS(roo, m1);
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        
    }
    
    /*public void run(int numberOfRuns, BoardView comp, MonteCarlo m1, MonteCarlo m2) throws Exception{
        double twoWins = 0;
        MonteNode roo = new MonteNode(new Checkers(),0);
        for(double k = 0; k < numberOfRuns; k++){
            System.out.println("\n\n\n\n" + (k / numberOfRuns) * 100 + "% done \n\n\n");
            MonteNode w = this.playAgainstMCTS(roo, comp, m1, m2);
            System.out.println(w);
            if(w.getState().getValue() == 1)
                twoWins++;
        }
        System.out.println("--------- RESULTS -----------");
        System.out.println("Computer won " + (twoWins / numberOfRuns) * 100 + "% of the time");
    }*/

   
    
    
}
