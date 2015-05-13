/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author James
 */
public class FullView{
    
    private JFrame frame;
    private BoardView comp;
    private JLabel xLabel;
    private JLabel yLabel;
    private JPanel panel;
    private JTextField move;
    private JLabel x1 = new JLabel("X: ");
    private JLabel y1 = new JLabel("Y: ");
    
    public FullView(int[][] st){
        frame = new JFrame("Full View");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(30, 30, 700, 700);
        comp = new BoardView(st, 80);
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        xLabel = new JLabel("");
        yLabel = new JLabel("");
        move = new JTextField(2);
        panel.add(x1);panel.add(xLabel);panel.add(y1);panel.add(yLabel);panel.add(move);
        frame.add(panel, BorderLayout.NORTH);
        frame.getContentPane().add(comp, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    public void setState(int[][] state){
        comp.setModel(state);
        comp.repaint();
    }

    public BoardView getComp() {
        return comp;
    }
    
    public void setXLabel(int x){
        this.xLabel.setText(String.valueOf(x));
    }
    
    public void setYLabel(int y){
        this.yLabel.setText(String.valueOf(y));
    }
    
    public JTextField getTextMove(){
        return this.move;
    }
    
    public String getMove(){
        return this.move.getText();
    }
    
    public int getXLabel(){
        return Integer.parseInt(this.xLabel.getText());
    }
    
    public int getYLabel(){
        return Integer.parseInt(this.yLabel.getText());
    }
    
    
}
