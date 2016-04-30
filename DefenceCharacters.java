
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

public class DefenceCharacters{

    //fields
    private double positionX;
    private double positionY;
    private String type;
    private double damage;
    double HP = 100;

    public DefenceCharacters(String name, double x2, double y2 ){
        this.type = name;
        this.positionX = x2;
        this.positionY = y2;
        if (type.equals("level1")) {
            this.damage = 5;
        }
        if (type.equals("level2")) {
            this.damage = 10;
        }
        if (type.equals("level3")) {
            this.damage = 15;
        }
        if (type.equals("level4")) {
            this.damage = 20;
        }
        if (type.equals("level5")) {
            this.damage = 25;
        }
        if (type.equals("level6")) {
            this.damage = 30;
        }
    }

    public void draw(){
        SwingUtilities.invokeLater(()->{
                if (type.equals("level1")){
                    UI.drawImage("defenceCharacter.png", positionX, positionY, 50, 100);
                }
                if (type.equals("level2")){
                    UI.drawImage("defenceCharacter.png", positionX, positionY, 50, 100);
                }
                if (type.equals("level3")){
                    UI.drawImage("defenceCharacter.png", positionX, positionY, 50, 100);
                }
                if (type.equals("level4")){
                    UI.drawImage("defenceCharacter.png", positionX, positionY, 50, 100);
                }
                if (type.equals("level5")){
                    UI.drawImage("defenceCharacter.png", positionX, positionY, 50, 100);
                }
                if (type.equals("level6")){
                    UI.drawImage("defenceCharacter.png", positionX, positionY, 50, 100);
                }
                UI.setColor(Color.black);
                UI.drawRect(this.positionX-1,this.positionY - 21,51,11);
                UI.setColor(Color.green.brighter());
                UI.fillRect(this.positionX,this.positionY - 20,HP/2,10);

            });
    }

    public void erase(){
        if (type.equals("level1")){
            UI.eraseImage("defenceCharacter.png", positionX, positionY);
        }
        if (type.equals("level2")){
            UI.eraseImage("defenceCharacter.png", positionX, positionY);
        }
        if (type.equals("level3")){
            UI.eraseImage("defenceCharacter.png", positionX, positionY);
        }
        if (type.equals("level4")){
            UI.eraseImage("defenceCharacter.png", positionX, positionY);
        }
        if (type.equals("level5")){
            UI.eraseImage("defenceCharacter.png", positionX, positionY);
        }
        if (type.equals("level6")){
            UI.eraseImage("defenceCharacter.png", positionX, positionY);
        }
        UI.eraseRect(this.positionX-1,this.positionY - 19,51,11);

    }

    public double getY(){
        return positionY;
    }

    public boolean attack(double x,double y,double st){
        boolean hit=false;
        if(y<positionY + 10 && y>positionY - 10&& x > positionX){
            double num = Math.random();
            if(num<=st){
                hit=true;
            }
        }else{
            hit=false;
        }
        return hit;
    }

    public double shot(double sht){
        this.HP-=sht;
        return this.HP;
    }



}
