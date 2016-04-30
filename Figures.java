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
    private int strength;
    private double range;
    private int stealth;
    private double positionX;
    private double positionY;
    private String type;
    private double HP;
    private double width;
    public boolean alarm = true;
    private double alarmX = 10;

    public Figures(String name, double x1, double y1){
        this.type = name;
        if (type.equals("stealthDude")) {
            this.speed = 2;
            this.strength = 1;
            this.range = Game.wallX;
            this.stealth = 9;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=71;
        }
        if (type.equals("strongDude")) {
            this.speed = 1;
            this.strength = 4;
            this.range = Game.wallX;
            this.stealth = 3;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=118;
        }
        if (type.equals("rangeDude")) {
            this.speed = 1;
            this.strength = 2;
            this.range = Game.ArenaSizex;
            this.stealth = 5;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=51.1;
        }
        if (type.equals("strongRange")) {
            this.speed = 1;
            this.strength = 7;
            this.range = Game.ArenaSizex;
            this.stealth = 5;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=51.1;
        }
        if (type.equals("stealthStrong")) {
            this.speed = 2;
            this.strength = 5;
            this.range = Game.wallX;
            this.stealth = 9;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=51.1;
        }
        if (type.equals("rangeStealth")) {
            this.speed = 1;
            this.strength = 2;
            this.range = Game.ArenaSizex;;
            this.stealth = 9;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=51.1;
        }
    }

    public void draw(){
        SwingUtilities.invokeLater(()->{
                if (type.equals("stealthDude")){
                    UI.drawImage("stealthDude.png", positionX, positionY,width, 100);
                }
                if (type.equals("strongDude")){
                    UI.drawImage("strongDude.png", positionX, positionY,width, 100);
                }
                if (type.equals("rangeDude")){
                    UI.drawImage("rangeDude.png", positionX, positionY,width, 100);
                }
                if (type.equals("strongRange")){
                    UI.drawImage("strongRange.png", positionX, positionY,width, 100);
                }
                if (type.equals("stealthStrong")){
                    UI.drawImage("stealthStrong.png", positionX, positionY,width, 100);
                }
                if (type.equals("rangeStealth")){
                    UI.drawImage("rangeStealth.png", positionX, positionY,width, 100);
                }
            });
    }

    public void move() {
        if((this.positionX) != Game.wallX){
            this.positionX = positionX - speed;
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

    public boolean alarmOFF(double alarmY) {
        if (type.equals("stealthDude") || type.equals("stealthStrong")) {
            if (positionX > alarmX && positionX < alarmX+50 && positionY > alarmY && positionY < alarmY+50) {
                UI.eraseOval(10, 0, 50, 50);
                UI.eraseOval(10, 100, 50, 50);
                UI.eraseOval(10, 200, 50, 50);
                UI.eraseOval(10, 300, 50, 50);
            }
            else{
                alarm=true;
            }
            return strength;
        }
    }

    public void die() {
        if (type.equals("stealthDude")){
            UI.eraseImage("stealthDude.png", positionX, positionY);
        }
        if (type.equals("strongDude")){
            UI.eraseImage("strongDude.png", positionX, positionY);
        }
        if (type.equals("rangeDude")){
            UI.eraseImage("rangeDude.png", positionX, positionY);
        }
    }

}
