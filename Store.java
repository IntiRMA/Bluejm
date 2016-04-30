
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
    public int numStrongStealthRange;
    public int Cash=5000;
    int top =50;
    public boolean draw=false;
    /**      */
    public Store(){
       UI.clearGraphics();
       UI.repaintGraphics();
        //UI.addButton("Quit", UI::quit);

    }
    //drawing the store
    public void drawStore(){
        int height=120;
        int width=100;

        UI.setMouseListener(this::doMouse);
        UI.clearGraphics();
        UI.setColor(Color.black);
        UI.fillRect(0,0,700,700+top);
        UI.setColor(Color.blue);
        UI.setFontSize(50);
        UI.drawString("Store",300, 20+top);
        UI.setFontSize(20);
        UI.drawRect(50,50+top,width,120);
         UI.drawString("Octavio",50, 60+top+130);
        UI.drawRect(50,200+top,width,height);
        UI.drawString("Dan",50, 210+top+130);
        UI.drawRect(250,50+top,width,height);
        UI.drawString("Inti",250, 60+top+130);
        UI.drawRect(250,200+top,width,height);
        UI.drawString("Sloth",250, 210+top+130);
        UI.drawRect(400,50+top,width,height);
        UI.drawString("Scylar",400, 60+top+130);
        UI.drawRect(400,200+top,width,height);
        UI.drawString("Tyaan",400, 210+top+130);
        UI.drawRect(250,350+top,width,height);
        UI.drawString("Alexander The Great",250, 360+top+130);
        UI.fillRect(50, 500+top, 100,50);
        
        UI.setColor(Color.white);
        
        UI.setFontSize(20);
        UI.drawString("Play!",75,500+top+25);
        UI.setColor(Color.blue);

        UI.drawString("Cash: $"+Cash, 520, 50);
        UI.drawString("stealth: "+numStealth,520,100);
        UI.drawString("strong: "+numStrong,520,150);
        UI.drawString("StrongRange: "+numStrongRange,520,250);
        UI.drawString("StealthRange: "+numStealthRange,520,300);
        UI.drawString("StrongStealth: "+numStrongStealth,520,350);
        UI.drawString("range: "+numRange,520,200);
        UI.sleep(40);

    }

    public void doMouse(String action, double x, double y){
        double xClick=0;
        double yClick=0;
        if(action.equals("clicked")){
            xClick= x;
            yClick=y;

        }
        if(xClick>50 && xClick<150 && yClick>50+top && yClick<50+120+top && Cash>49){
            //stealth
            numStealth=numStealth+1;
            Cash=Cash-50;

        }else if(xClick>50 && xClick<150 && yClick>200 +top && yClick<200+120+top && Cash>499 && numStealth>0&& numRange>0){
            //stealth&&range
            numStealthRange=numStealthRange+1;
            Cash=Cash-500;
            numRange=numRange-1;
            numStealth=numStealth-1;

        }else if(xClick>250 && xClick<350 && yClick>50+top && yClick<50+120+top && Cash>49){
            //strong
            numStrong=numStrong+1;
            Cash=Cash-50;

        }else if(xClick>250 && xClick<350 && yClick>200+top && yClick<200+120+top &&Cash>800 &&numStrong>0 && numStealth>0){
            //Strong+Stealth
            numStrongStealth=numStrongStealth+1;
            Cash=Cash-800;
            numStrong=numStrong-1;
            numStealth=numStealth-1;

        }else if(xClick>400 && xClick<500 && yClick>50+top && yClick<50+120+top && Cash>49){
            //Range
            numRange=numRange+1;
            Cash=Cash-50;
        }else if(xClick>400 && xClick<550 && yClick>200+top && yClick<200+120+top && Cash>1199 &&numStrong>0 && numRange>0){
            //Strong and range
            numStrongRange=numStrongRange+1;
            Cash=Cash-1200;
            numStrong=numStrong-1;
            numRange=numRange+1;

        }
        else if(xClick>250 && xClick<350 && yClick>350+top && yClick<350+120+top&& Cash>3000 && numStrong>0 &&numStealth>0 &&numRange>0){
            numStrongStealthRange=numStrongStealthRange+1;

           
        }else if(xClick>50 && xClick<150 && yClick>500+top && yClick<550+top){
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
    public int stealthRange(){
        return numStealthRange;
    }
    public int strongStealth(){
        return numStrongStealth;
    }
    public int strongRange(){
        return numStrongRange;
    }
    public int strongStealthRange(){
        return numStrongStealthRange;
    }

    /*public static void main(String[] args){
    Store obj = new Store();
    }  */  

}
