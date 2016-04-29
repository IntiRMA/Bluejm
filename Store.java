
import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
import javax.swing.*;

/** <description of class Store>
 */
public class Store{
    private String type;
    public int numStealth=0;
    public int numStrong=0;
    public int numRange=0;
    public int numStrongStealth;
    public int numStrongRange;
    public int numStealthRange;
    public int Cash=5000;
    public boolean draw=false;
    /**      */
    public Store(){
        UI.initialise();
        //UI.addButton("Quit", UI::quit);

       
    }
    //drawing the store
    public void drawStore(){
        int height=120;
        int width=100;
        
        UI.setMouseListener(this::doMouse);
        UI.clearGraphics();
        UI.setColor(Color.black);
        UI.fillRect(0,0,700,700);
        UI.setColor(Color.blue);
        UI.setFontSize(50);
        UI.drawString("Heist",300, 20);
        UI.drawRect(50,50,width,120);
        UI.drawRect(50,200,width,height);
        UI.drawRect(250,50,width,height);
        UI.drawRect(250,200,width,height);
        UI.drawRect(400,50,width,height);
        UI.drawRect(400,200,width,height);
        UI.drawRect(250,350,width,height);
        UI.fillRect(50, 500, 100,50);
        UI.setFontSize(20);
        
        UI.drawString("Cash: $"+Cash, 600, 50);
        UI.drawString("stealth: "+numStealth,600,100);
        UI.drawString("strong: "+numStrong,600,150);
        UI.drawString("StrongRange: "+numStrongRange,600,250);
        UI.drawString("StealthRange: "+numStealthRange,600,300);
        UI.drawString("StrongStealth: "+numStrongStealth,600,350);
        UI.drawString("range: "+numRange,600,200);
        UI.sleep(40);

    }
    public void doMouse(String action, double x, double y){
        double xClick=0;
        double yClick=0;
        if(action.equals("clicked")){
            xClick= x;
            yClick=y;

        }
        if(xClick>50 && xClick<150 && yClick>50 && yClick<50+120 && Cash>49){
            //stealth
            numStealth=numStealth+1;
            Cash=Cash-50;

        }else if(xClick>50 && xClick<150 && yClick>200 && yClick<200+120 && Cash>499 && numStealth>0&& numRange>0){
            //stealth&&range
            numStealthRange=numStealthRange+1;
            Cash=Cash-500;
            numRange=numRange-1;
            numStealth=numStealth-1;

        }else if(xClick>250 && xClick<350 && yClick>50 && yClick<50+120 && Cash>49){
            //strong
            numStrong=numStrong+1;
            Cash=Cash-50;

        }else if(xClick>250 && xClick<350 && yClick>200 && yClick<200+120 &&Cash>800 &&numStrong>0 && numStealth>0){
            //Strong+Stealth
            numStrongStealth=numStrongStealth+1;
            Cash=Cash-800;
            numStrong=numStrong-1;
            numStealth=numStealth-1;

        }else if(xClick>400 && xClick<500 && yClick>50 && yClick<50+120&& Cash>49){
            //Range
            numRange=numRange+1;
            Cash=Cash-50;
        }else if(xClick>400 && xClick<550 && yClick>200 && yClick<200+120 && Cash>1199 &&numStrong>0 && numRange>0){
            //Strong and range
            numStrongRange=numStrongRange+1;
            Cash=Cash-1200;
            numStrong=numStrong-1;
            numRange=numRange+1;
            
        }
        else if(xClick>250 && xClick<350 && yClick>350 && yClick<350+120){
           
        }else if(xClick>50 && xClick<150 && yClick>500 && yClick<550){
            draw= true;
        }
       
    }

    public boolean bool(){
        return draw;
    }

    public int strong(){
        return numStrong;
    }

    public int range(){
        return numRange;
    }

    public int stealth(){
        return numStealth;
    }

    /*public static void main(String[] args){
    Store obj = new Store();
    }  */  

}
