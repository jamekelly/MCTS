/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author James
 */
public class BoardView extends JComponent{
    
    private int width;
    private int height;
    private int[][] model;
    private int size;
    
    public BoardView(int[][] model, int squareSize){
        this.model = model;
        this.width = model.length * squareSize;
        this.height = model.length * squareSize;
        this.size = squareSize;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //g.drawRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        for(int i = 0; i< width;i += this.size * 2){
            for(int j = 0; j < height; j += this.size * 2){
                g.fillRect(i, j, width / model.length, height / model.length);
            }
        }
        for(int i = width / model.length; i < width; i += this.size * 2){
            for(int j = height / model.length; j < height; j += this.size * 2){
                g.fillRect(i, j, width / model.length, height / model.length);
            }
        }
        g.setColor(Color.BLACK);
        for(int i = width/model.length;i<width;i += this.size * 2){
            for(int j = 0;j<height;j+=this.size * 2){
                g.fillRect(i, j, width/model.length, height/model.length);
            }
        }
        for(int i = 0; i < width; i+=this.size * 2){
            for(int j = height/model.length;j<height;j+=this.size * 2){
                g.fillRect(i, j, width/model.length, height/model.length);
            }
        }
        for(int i = 0; i < model.length; i++){
            for(int j = 0; j < model[i].length;j++){
                if(model[j][i] == 1){
                    g.setColor(Color.BLACK);
                    g.fillOval(i * this.size + this.size / 10, j * this.size + this.size / 10, this.size - 20, this.size - 20);
                } else if(model[j][i] == 2){
                    g.setColor(Color.RED);
                    g.fillOval(i * this.size + this.size / 10, j * this.size + this.size / 10, this.size - 20, this.size - 20);
                } else if(model[j][i] == 3){
                    g.setColor(Color.BLACK);
                    g.fillOval(i * this.size + this.size / 10, j * this.size + this.size / 10, this.size - 20, this.size - 20);
                    g.setColor(Color.WHITE);
                    g.drawOval(i * this.size + 20, j * this.size + 20, this.size - 40, this.size - 40);
                } else if(model[j][i] == 4){
                    g.setColor(Color.RED);
                    g.fillOval(i * this.size + this.size / 10, j * this.size + this.size / 10, this.size - 20, this.size - 20);
                    g.setColor(Color.BLACK);
                    g.drawOval(i * this.size + 20, j * this.size + 20, this.size - 40, this.size - 40);
                }
            }
        }
    }
    
    public void setModel(int[][] model){
        this.model = model;
    }
    
}
