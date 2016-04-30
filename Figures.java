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
    private int range;
    private int stelth;
    private double positionX;
    private double positionY;
    private String type;
    private double HP;
    private double width;

    public Figures(String name, double x1, double y1){
        this.type = name;
        if (type.equals("stealthDude")) {
            this.speed = 2;
            this.strength = 1;
            this.range = 80;
            this.stelth = 9;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=71;
        }
        if (type.equals("strongDude")) {
            this.speed = 1;
            this.strength = 4;
            this.range = 80;
            this.stelth = 3;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=118;
        }
        if (type.equals("rangeDude")) {
            this.speed = 1;
            this.strength = 2;
            this.range = 300;
            this.stelth = 5;
            this.positionX = x1;
            this.positionY = y1;
            this.HP = 100;
            this.width=51.1;
        }
    }

    public void draw(){
        SwingUtilities.invokeLater(()->{
                if (type.equals("stealthDude")){
                    UI.drawImage("stealthDude.jpg", positionX, positionY,width, 100);
                }
                if (type.equals("strongDude")){
                    UI.drawImage("strongDude.jpg", positionX, positionY,width, 100);
                }
                if (type.equals("rangeDude")){
                    UI.drawImage("rangeDude.jpg", positionX, positionY,width, 100);
                }
            });
    }

    public void move() {
        if((this.positionX - width/2) > Game.wallX){
            this.positionX = positionX - speed;
        }
    }

    public double attack() {
        if (positionX < range) {
            return strength;
        }
        else {
            return 0;
        }
    }

    public void die() {
        if (type.equals("stealthDude")){
            UI.eraseImage("stealthDude.jpg", positionX, positionY);
        }
        if (type.equals("strongDude")){
            UI.eraseImage("strongDude.jpg", positionX, positionY);
        }
        if (type.equals("rangeDude")){
            UI.eraseImage("rangeDude.jpg", positionX, positionY);
        }
    }

}
