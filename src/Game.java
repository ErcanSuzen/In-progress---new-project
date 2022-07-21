
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ERCAN
 */

class ball {
    private int x ;
    private int y;

    public ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
public class Game extends JPanel implements KeyListener, ActionListener{
    
    Timer timer = new Timer(5,this);
    private int shoot_trial_no=0;
    private int time=0;
    
    private BufferedImage Image ;
    private BufferedImage ImageTarget ;
    private BufferedImage ImageBall ;
    private ArrayList<ball> balls = new ArrayList<ball>();
    private int balldirY=10;
    private int targetX=0;
    private int targetdirX=10;
    private int ronaldoX=0;
    private int ronaldodirX=20;
    
    public boolean goal(){
       for(ball Ball : balls){
           if (new Rectangle(Ball.getX(),Ball.getY(),20,20).intersects(new Rectangle(targetX,60,150,10))){
               return true;
           }
       }
       return false;
    }

    public Game() {
        try {
            Image = ImageIO.read(new FileImageInputStream(new File("ronaldoafter.png")));
            ImageTarget = ImageIO.read(new FileImageInputStream(new File("post.jpg")));
            ImageBall = ImageIO.read(new FileImageInputStream(new File("shootball.png")));
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        setBackground(Color.white);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
              super.paint(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
              time +=5;
              g.drawImage(Image, ronaldoX, 420, Image.getWidth()/5,Image.getHeight()/5,this);
              g.drawImage(ImageTarget, targetX, 20, ImageTarget.getWidth()/3,ImageTarget.getHeight()/3,this);
              
               for (ball football : balls){
                    if(football.getY()<30){
                    balls.remove(football);
            }
    }
               for (ball Ball : balls){
                  g.drawImage(ImageBall, Ball.getX(),Ball.getY(), ImageBall.getWidth()/4,ImageBall.getHeight()/4,this); 
               }
               if (goal()){
                   timer.stop();
                   String message = "Goal... Ronaldo\n" +
                                    "Missing shoot numbers :   " + shoot_trial_no +
                                    "\nTime :  " + time/1000.0 + "sn" ;
                   
                   JOptionPane.showMessageDialog(this, message);
                   System.exit(0);
               }
               }
       
    @Override
    public void repaint() {
        super.repaint(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

   
    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_LEFT){
            if(ronaldoX<=0){
            ronaldoX=0;
            }
            else {
            ronaldoX -= ronaldodirX;
           
            }
        
        }
        else if (c == KeyEvent.VK_RIGHT){
        
            if(ronaldoX >=700) {
            ronaldoX=700;          
            }
            else {
                ronaldoX += ronaldodirX;
            }
            }
        else if (c == KeyEvent.VK_CONTROL){
            
            balls.add(new ball(ronaldoX+15,420));
            shoot_trial_no ++;
            
        }
        }
        
    

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
        targetX += targetdirX;
        
        if (targetX > 600){
            targetdirX= -targetdirX;
        }
        if (targetX<=0) {
            targetdirX=-targetdirX;
        }
        repaint();
        
        for (ball football : balls){
            football.setY(football.getY()-balldirY);
            
        }
        
    }
    
}
