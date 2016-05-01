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
    public double HP;
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
            this.stealth = 3.4;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 25;
            this.width=72;
        }
        if (type.equals("strongDude")) {
            this.speed = 1;
            this.strength = 4;
            this.range = Game.wallX;
            this.stealth = 1;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 200;
            this.width=60;
        }
        if (type.equals("rangeDude")) {
            this.speed = 1;
            this.strength = 0.1;
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
            this.stealth = 1;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 300;
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
            this.stealth = 7;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 200;
            this.width=51.5;
        }
    }

    public void draw(){
        SwingUtilities.invokeLater(()->{
                double num = 2;
                int extra=0;
                if (type.equals("stealthDude")){
                    UI.drawImage("stealthDude.png", positionX, positionY,width, 90);
                    extra=15;
                    num=0.5;
                }
                if (type.equals("strongDude")){
                    UI.drawImage("strongDude.png", positionX, positionY,width, 90);
                    num=4;
                    extra=10;
                }
                if (type.equals("rangeDude")){
                    UI.drawImage("rangeDude.png", positionX, positionY,width, 90);
                }
                if (type.equals("strongRange")){
                    UI.drawImage("strongRange.png", positionX, positionY,width, 90);
                    num=6;
                }
                if (type.equals("stealthStrong")){
                    UI.drawImage("stealthStrong.png", positionX, positionY,width, 90);
                }
                if (type.equals("rangeStealth")){
                    UI.drawImage("rangeStealth.png", positionX, positionY,width, 90);
                    num=4;
                }
                UI.setColor(Color.black);
                UI.drawRect(this.positionX-1 + extra,this.positionY - 21,51,11);
                UI.setColor(Color.red.brighter());
                UI.fillRect(this.positionX + extra,this.positionY - 20,HP/num,10);

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
        if (this.type.equalsIgnoreCase("stealthDude")||this.type.equalsIgnoreCase("stealthStrong")){
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
        if (this.type.equalsIgnoreCase("strongRange")){
            if((this.positionY==90 &&this.positionX>=630)){
                this.positionX = positionX - speed;
            }else if((this.positionY==162 &&this.positionX>=580)){
                this.positionX = positionX - speed;
            }else if((this.positionY==234 &&this.positionX>=530)){
                this.positionX = positionX - speed;
            }else if((this.positionY==306 &&this.positionX>=480)){
                this.positionX = positionX - speed;
            }
        }
    }

    public double attack() {
        double dam=0;
        if((this.positionY==90 &&this.positionX<=490)){
            dam=this.strength;
        }else if((this.positionY==162 &&this.positionX<=440)){
            dam=this.strength;
        }else if((this.positionY==234 &&this.positionX<=390)){
            dam=this.strength;
        }else if((this.positionY==306 &&this.positionX<=340)){
            dam=this.strength;
        }
        return dam;
    }
    public double rangeAttack(){
        double dam=0;
        if((this.positionY==90 &&this.positionX<=690)){
            dam=this.strength;
        }else if((this.positionY==162 &&this.positionX<=640)){
            dam=this.strength;
        }else if((this.positionY==234 &&this.positionX<=590)){
            dam=this.strength;
        }else if((this.positionY==306 &&this.positionX<=540)){
            dam=this.strength;
        }
        return dam;
    }

    public double attackCop() {
        return strength;
    }

    public double hit(){
        if(Game.level==1){
        this.HP-=0.5;
    }else if(Game.level==2){
        this.HP-=1;
    }else if(Game.level==3){
        this.HP-=1.5;
    }else if(Game.level==4){
        this.HP-=2;
    }else if(Game.level==5){
        this.HP-=2.5;
    }else if(Game.level==6){
        this.HP-=3;
    }
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
        if(type.equals("rangeStealth")){
          if ( positionY >= alarmY && positionY<=alarmY+50) {
                i=strength;
            }
        }
        return i;
    }
}
