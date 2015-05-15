/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    
    public Ref(MonteCarlo mc, FullView fv){
        this.mc = mc;
        this.fv = fv;
    }
    
    

    public void playAgainstMCTS(MonteNode root, MonteCarlo m1, MonteCarlo m2) throws Exception, NoSuchMethodException {
        fv.setState(root.getState().getState());
        if(root.getLastMoved() <= 1){
            MonteNode roo = m1.searchForOne(root);
            this.playAgainstMCTS(roo, m1, m2);
        } else if(root.getLastMoved() > 1){
            MonteNode roo = m2.searchForOne(root);
            this.playAgainstMCTS(root, m1, m2);
        }
    }
    
    
    public void playMCTS(final MonteNode root, MonteCarlo m1) throws Exception, NoSuchMethodException {
        
        if(root.getLastMoved() <= 1){
            MonteNode roo = m1.searchForOne(root);
            fv.setState(roo.getState().getState());
            this.playMCTS(roo, m1);
        } else if(root.getLastMoved() > 1){
            if (this.fv.getComp().getMouseListeners().length > 0){
                this.fv.getComp().removeMouseListener(this.fv.getComp().getMouseListeners()[0]);
                this.fv.getTextMove().removeActionListener(this.fv.getTextMove().getActionListeners()[0]);
            }
            this.fv.getComp().addMouseListener(new MouseAdapter() {
                
                public void mouseClicked(MouseEvent e){
                    int xpt = e.getX() / fv.getComp().getSquareSize();
                    int ypt = e.getY() / fv.getComp().getSquareSize();
                    Ref.this.fv.setXLabel(xpt);
                    Ref.this.fv.setYLabel(ypt);
                }
            });
            this.fv.getTextMove().addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent ae){
                    int x = Ref.this.fv.getXLabel();
                    int y = Ref.this.fv.getYLabel();
                    String mv = Ref.this.fv.getMove();
                    MonteNode roo = root.getState().userMove(x, y, mv);
                    fv.setState(roo.getState().getState());
                    Ref.this.fv.getTextMove().setText("");
                    try{
                        Ref.this.playMCTS(roo, m1);
                    }  catch(Exception ex){}
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
