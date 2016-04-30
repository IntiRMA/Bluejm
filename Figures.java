/* Code for Assignment ?? 
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
import javax.swing.*;

public class Figures{

    //fields
    private int speed;
    private double strength;
    private double range;
    private double stealth;
    private double positionX;
    private double positionY;
    private String type;
    private double HP;
    private double width;
    public boolean alarm = true;
    private double alarmX = 10;
    private double stealthRat=0;
    double i=0;

    public Figures(String name, double x1, double y1){
        this.type = name;
        if (type.equals("stealthDude")) {
            this.speed = 2;
            this.strength = 3;
            this.range = Game.wallX;
            this.stealth = 4;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=72;
        }
        if (type.equals("strongDude")) {
            this.speed = 1;
            this.strength = 4;
            this.range = Game.wallX;
            this.stealth = 1;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=60;
        }
        if (type.equals("rangeDude")) {
            this.speed = 1;
            this.strength = 2;
            this.range = Game.ArenaSizex;
            this.stealth = 2;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=50;
        }
        if (type.equals("strongRange")) {
            this.speed = 1;
            this.strength = 7;
            this.range = Game.ArenaSizex;
            this.stealth = 1.5;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=57;
        }
        if (type.equals("stealthStrong")) {
            this.speed = 2;
            this.strength = 5;
            this.range = Game.wallX;
            this.stealth = 4;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=67;
        }
        if (type.equals("rangeStealth")) {
            this.speed = 1;
            this.strength = 3;
            this.range = Game.ArenaSizex;;
            this.stealth = 9;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=51.5;
        }
    }

    public void draw(){
        SwingUtilities.invokeLater(()->{
                if (type.equals("stealthDude")){
                    UI.drawImage("stealthDude.png", positionX, positionY,width, 90);
                }
                if (type.equals("strongDude")){
                    UI.drawImage("strongDude.png", positionX, positionY,width, 90);
                }
                if (type.equals("rangeDude")){
                    UI.drawImage("rangeDude.png", positionX, positionY,width, 90);
                }
                if (type.equals("strongRange")){
                    UI.drawImage("strongRange.png", positionX, positionY,width, 90);
                }
                if (type.equals("stealthStrong")){
                    UI.drawImage("stealthStrong.png", positionX, positionY,width, 90);
                }
                if (type.equals("rangeStealth")){
                    UI.drawImage("rangeStealth.png", positionX, positionY,width, 90);
                }

            });
    }

    public void erase(){
        if (type.equals("stealthDude")){
            UI.eraseImage("stealthDude.png", positionX, positionY);
        }
        if (type.equals("strongDude")){
            UI.eraseImage("strongDude.png", positionX, positionY);
        }
        if (type.equals("rangeDude")){
            UI.eraseImage("rangeDude.png", positionX, positionY);
        }
        if (type.equals("strongRange")){
            UI.eraseImage("strongRange.png", positionX, positionY);
        }
        if (type.equals("stealthStrong")){
            UI.eraseImage("stealthStrong.png", positionX, positionY);
        }
        if (type.equals("rangeStealth")){
            UI.eraseImage("rangeStealth.png", positionX, positionY);
        }
    }
    
    public double stealthRatio(){
        stealthRat=1/stealth;
        return stealthRat;
    }
    
    public void move() {
        if (this.type.equalsIgnoreCase("stealthDude")){
            if(this.positionX>=50){
                this.positionX = positionX - speed;
            }
        }
        if (this.type.equalsIgnoreCase("strongDude")){
            if((this.positionY==90 &&this.positionX>=480)){
                this.positionX = positionX - speed;
            }else if((this.positionY==162 &&this.positionX>=430)){
                this.positionX = positionX - speed;
            }else if((this.positionY==234 &&this.positionX>=380)){
                this.positionX = positionX - speed;
            }else if((this.positionY==306 &&this.positionX>=330)){
                this.positionX = positionX - speed;
            }
        }
    }

    public double attack() {
        if ((this.positionX < range + 10)) {
            return strength;
        }
        else {
            return 0;
        }
    }
    
    public double attackCop() {
        if (type.equals("rangeDude") || type.equals("strongRange")){
            return strength;
        }
        else {
            return 0;
        }
    }
    
    public double hit(){
        this.HP-=2;
        return this.HP;
    }
    
    public double getX() {
        return positionX;
    }
    
    public double getY() {
        return positionY;
    }

    public double alarmOFF(double alarmY) {
        if (type.equals("stealthDude") || type.equals("stealthStrong")) {
            if ((positionX < alarmX + 60) && positionY >= alarmY && positionY<=alarmY+50 ) {
                i=strength;
            }
        }
        return i;
    }
}
