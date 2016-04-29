
import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/** <description of class Store>
 */
public class Store{
    private String type;
    public int numStealth=0;
    public int numStrong=0;
    public int numRange=0;
    public int Cash=500;
    public boolean draw=false;
    /**      */
    public Store(){
        UI.initialise();
        //UI.addButton("Quit", UI::quit);
        

        drawStore();

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
            UI.setFontSize(20);
            UI.drawString("Cash: $"+Cash, 600, 50);
            UI.drawString("stealth: "+numStealth,600,100);
            UI.drawString("strong: "+numStrong,600,150);
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
            numStealth=numStealth+1;
            Cash=Cash-50;

        }else if(xClick>50 && xClick<150 && yClick>200 && yClick<200+120 && Cash>49){
            
        }else if(xClick>250 && xClick<350 && yClick>50 && yClick<50+120 && Cash>49){
            numStrong=numStrong+1;
            Cash=Cash-50;
           
        }else if(xClick>250 && xClick<350 && yClick>200 && yClick<200+120){

        }else if(xClick>400 && xClick<500 && yClick>50 && yClick<50+120){
             numRange=numRange+1;
            Cash=Cash-50;
        }else if(xClick>400 && xClick<550 && yClick>200 && yClick<200+120){}
        else if(xClick>250 && xClick<350 && yClick>350 && yClick<350+120){
            draw= true;
        }
        if(draw==false){
            drawStore();
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
