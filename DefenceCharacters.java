/* Code for Assignment ?? 
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

public class DefenceCharacters{

    //fields
    private double positionX;
    private double positionY;
    private String type;
    private double damage;

    public void Character(String name, double x2, double y2, double damage1){
        this.type = name;
        this.positionX = x2;
        this.positionY = y2;
        if (type.equals("level1")) {
            this.damage = damage1;
        }
        if (type.equals("level2")) {
            this.damage = damage1;
        }
        if (type.equals("level3")) {
            this.damage = damage1;
        }
        if (type.equals("level4")) {
            this.damage = damage1;
        }
        if (type.equals("level5")) {
            this.damage = damage1;
        }
        if (type.equals("level6")) {
            this.damage = damage1;
        }
    }

    public void draw(){
        if (type.equals("level1")){
            UI.drawImage("defenceCharacter.jpg", positionX, positionY, 57, 100);
        }
        if (type.equals("level2")){
            UI.drawImage("defenceCharacter.jpg", positionX, positionY, 57, 100);
        }
        if (type.equals("level3")){
            UI.drawImage("defenceCharacter.jpg", positionX, positionY, 57, 100);
        }
        if (type.equals("level4")){
            UI.drawImage("defenceCharacter.jpg", positionX, positionY, 57, 100);
        }
        if (type.equals("level5")){
            UI.drawImage("defenceCharacter.jpg", positionX, positionY, 57, 100);
        }
        if (type.equals("level6")){
            UI.drawImage("defenceCharacter.jpg", positionX, positionY, 57, 100);
        }
    }
    
    public void attack() {
        
    }

    public void surrender() {
        if (type.equals("level1")){
            UI.eraseImage("defenceCharacter.jpg", positionX, positionY);
        }
        if (type.equals("level2")){
            UI.eraseImage("defenceCharacter.jpg", positionX, positionY);
        }
        if (type.equals("level3")){
            UI.eraseImage("defenceCharacter.jpg", positionX, positionY);
        }
        if (type.equals("level4")){
            UI.eraseImage("defenceCharacter.jpg", positionX, positionY);
        }
        if (type.equals("level5")){
            UI.eraseImage("defenceCharacter.jpg", positionX, positionY);
        }
        if (type.equals("level6")){
            UI.eraseImage("defenceCharacter.jpg", positionX, positionY);
        }
    }

}
