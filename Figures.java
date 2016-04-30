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
    private int stealth;
    private double positionX;
    private double positionY;
    private String type;
    private double HP;
    private double width;
    public boolean alarm = true;
    private double alarmX = 10;
    double i=0;

    public Figures(String name, double x1, double y1){
        this.type = name;
        if (type.equals("stealthDude")) {
            this.speed = 2;
            this.strength = 3;
            this.range = Game.wallX;
            this.stealth = 9;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=72;
        }
        if (type.equals("strongDude")) {
            this.speed = 1;
            this.strength = 4;
            this.range = Game.wallX;
            this.stealth = 3;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=60;
        }
        if (type.equals("rangeDude")) {
            this.speed = 1;
            this.strength = 2;
            this.range = Game.ArenaSizex;
            this.stealth = 5;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=50;
        }
        if (type.equals("strongRange")) {
            this.speed = 1;
            this.strength = 7;
            this.range = Game.ArenaSizex;
            this.stealth = 5;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=57;
        }
        if (type.equals("stealthStrong")) {
            this.speed = 2;
            this.strength = 5;
            this.range = Game.wallX;
            this.stealth = 9;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=67;
        }
        if (type.equals("rangeStealth")) {
            this.speed = 1;
            this.strength = 2;
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
                    UI.drawImage("stealthDude.png", positionX, positionY,width, 72);
                }
                if (type.equals("strongDude")){
                    UI.drawImage("strongDude.png", positionX, positionY,width, 72);
                }
                if (type.equals("rangeDude")){
                    UI.drawImage("rangeDude.png", positionX, positionY,width, 72);
                }
                if (type.equals("strongRange")){
                    UI.drawImage("strongRange.png", positionX, positionY,width, 72);
                }
                if (type.equals("stealthStrong")){
                    UI.drawImage("stealthStrong.png", positionX, positionY,width, 72);
                }
                if (type.equals("rangeStealth")){
                    UI.drawImage("rangeStealth.png", positionX, positionY,width, 72);
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

    public void move() {
        if (this.type.equalsIgnoreCase("stealthDude")){
            if((this.positionX) != alarmX + 50){
                this.positionX = positionX - speed;
            }
        }
        if (this.type.equalsIgnoreCase("strongDude")){
            if((this.positionX) != Game.wallX){
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

    public double alarmOFF(double alarmY) {
        if (type.equals("stealthDude") || type.equals("stealthStrong")) {
            if ((positionX < alarmX + 60) && positionY >= alarmY && positionY<=alarmY+50 ) {
                i=strength;
            }
        }
        return i;
    }
}
