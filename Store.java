
import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
import javax.swing.*;

/** <description of class Store>
 */
public class Store{
    private String type;
    public static int numStealth;
    public static int numStrong;
    public static int numRange;
    public static int numStrongStealth;
    public static int numStrongRange;
    public static int numStealthRange;
    public static int numStrongStealthRange;
    public static int Cash=5000;
    int top =50;
    public static boolean draw=false;
    /**      */
    public Store(){
        //UI.clearGraphics();
        UI.repaintGraphics();
        UI.setMouseListener(this::doMouse);
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
        UI.drawString("stealth: "+this.numStealth,520,100);
        UI.drawString("strong: "+this.numStrong,520,150);
        UI.drawString("StrongRange: "+this.numStrongRange,520,250);
        UI.drawString("StealthRange: "+this.numStealthRange,520,300);
        UI.drawString("StrongStealth: "+this.numStrongStealth,520,350);
        UI.drawString("range: "+this.numRange,520,200);
        UI.repaintGraphics();
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
            this.numStealth=this.numStealth+1;
            Cash=Cash-50;

        }else if(xClick>50 && xClick<150 && yClick>200 +top && yClick<200+120+top && Cash>499 && this.numStealth>0&& this.numRange>0){
            //stealth&&range
            this.numStealthRange=this.numStealthRange+1;
            Cash=Cash-500;
            this.numRange=this.numRange-1;
            this.numStealth=this.numStealth-1;

        }else if(xClick>250 && xClick<350 && yClick>50+top && yClick<50+120+top && Cash>49){
            //strong
            this.numStrong=this.numStrong+1;
            Cash=Cash-50;

        }else if(xClick>250 && xClick<350 && yClick>200+top && yClick<200+120+top &&Cash>800 &&this.numStrong>0 && this.numStealth>0){
            //Strong+Stealth
            this.numStrongStealth=this.numStrongStealth+1;
            Cash=Cash-800;
            this.numStrong=this.numStrong-1;
            this.numStealth=this.numStealth-1;

        }else if(xClick>400 && xClick<500 && yClick>50+top && yClick<50+120+top && Cash>49){
            //Range
            this.numRange=this.numRange+1;
            Cash=Cash-50;
        }else if(xClick>400 && xClick<550 && yClick>200+top && yClick<200+120+top && Cash>1199 &&this.numStrong>0 && this.numRange>0){
            //Strong and range
            this.numStrongRange=this.numStrongRange+1;
            Cash=Cash-1200;
            this.numStrong=this.numStrong-1;
            this.numRange=this.numRange+1;

        }
        else if(xClick>250 && xClick<350 && yClick>350+top && yClick<350+120+top&& Cash>3000 && this.numStrong>0 &&this.numStealth>0 &&this.numRange>0){
            this.numStrongStealthRange=this.numStrongStealthRange+1;

           
        }else if(xClick>50 && xClick<150 && yClick>500+top && yClick<550+top){
            draw= true;
        }
        UI.repaintGraphics();
    }

    public boolean bool(){
        return draw;
    }

    public int strong(){
        return this.numStrong;
    }

    public int range(){
        return this.numRange;
    }

    public int stealth(){
        return this.numStealth;
    }
    public int stealthRange(){
        return this.numStealthRange;
    }
    public int strongStealth(){
        return this.numStrongStealth;
    }
    public int strongRange(){
        return this.numStrongRange;
    }
    public int strongStealthRange(){
        return this.numStrongStealthRange;
    }

    /*public static void main(String[] args){
    Store obj = new Store();
    }  */  

}
