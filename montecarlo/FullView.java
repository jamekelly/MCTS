/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author James
 */
public class FullView{
    
    private JFrame frame;
    private BoardView comp;
    
    public FullView(int[][] st){
        frame = new JFrame("Full View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(30, 30, 850, 850);
        comp = new BoardView(st, 80);
        frame.getContentPane().add(comp);
        frame.setVisible(true);
    }
    
    public void setState(int[][] state){
        comp.setModel(state);
        comp.repaint();
    }

    public BoardView getComp() {
        return comp;
    }
    
    
}
