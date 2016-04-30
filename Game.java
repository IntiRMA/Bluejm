import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Game{
    public static final double ArenaSize =600;
    public static final double wallX =300;
    public static final double ArenaSizex =1000;
    public static final int top =0;
    public static final int left=0;
    private int numberStrong;
    private int numberStealth;
    private int numberRange;
    private double wallHP=600;
    private double wallStartHP=600;
    private String wall = "wall";
    private int level=1;
    private int turn=0;
    private double position;
    private String action="";
    private long currentTime;
    private double defx =250;
    private double defy =0;

    Figures[] strong = new Figures[100];
    Figures[] stealth = new Figures[100];
    Figures[] range = new Figures[100];
    DefenceCharacters[] cops = new DefenceCharacters [100];

    public Game(){
        UI.initialise();
        intro();

        UI.setKeyListener(this::controls);
    }

    public void mouse(String ev,double x,double y){

        if(ev.equalsIgnoreCase("pressed")){
            if(y<ArenaSize){
                this.position = y;
            }else{
                this.position=0;
            }
        }
        if(this.position<150){
            this.position=50;
        }else if(this.position<300){
            this.position=200;
        }else if(this.position<450){
            this.position=350;
        }else if(this.position<600){
            this.position=450;
        }
        this.action = ev;
    }

    public void intro(){
        UI.setFontSize(25);
        UI.setColor(Color.red);
        UI.drawString("This game is called Heist!!",200,100);
        UI.sleep(1000);
        UI.setFontSize(16);
        UI.setColor(Color.black);
        UI.drawString("your goal is to rob a bank!",200,150);
        UI.drawString("you will get to upgrade Figuress and also get new ones, the robbery will be more dificult each level ",200,200);
        UI.setColor(Color.red.brighter());
        UI.setFontSize(25);
        UI.sleep(1000);
        UI.drawString("When you are ready press SPACE",200,300);
    }

    public void controls(String key){
        if(key.equalsIgnoreCase("space")){
            store();
        }
    }

    public void store(){
        boolean T =false;
        Store shop = new Store();
        while (T==false){
            shop.drawStore();
            this.numberStrong = shop.strong();
            this.numberStealth = shop.stealth();
            this.numberRange = shop.range();

            T=shop.bool();

        }

        startGame();
    }

    public void startGame(){
        draw();
        int st = 0;
        int rg= 0;
        int stl = 0;
        int c = 0;
        int numCops=0;
        boolean D=false;
        UI.setMouseListener(this::mouse);
        Calendar cal = Calendar.getInstance();
        this.currentTime = cal.getTimeInMillis();
        while((this.numberStrong!=0 || this.numberStealth!=0 || this.numberRange!=0)&& this.wallHP!=0){
            if(D){
                draw();
            }

            if(action.equalsIgnoreCase("pressed") && this.turn==0){
                if(st<numberStrong && (position>0 && position<ArenaSize)){
                    strong[st] = new Figures("strongDude",ArenaSizex-100,this.position);
                    st++;
                    this.action="";
                }
                this.turn=1;
            }

            if(action.equalsIgnoreCase("pressed") && this.turn==1){
                if(rg<numberRange && (position>0 && position<ArenaSize)){
                    range[rg] = new Figures("rangeDude",ArenaSizex-100,this.position);
                    rg++;
                    this.action="";
                }
                this.turn=2;
            }
            if(action.equalsIgnoreCase("pressed") && turn==2){
                if(stl<numberStealth && (position>0 && position<ArenaSize)){
                    stealth[stl] = new Figures("stealthDude",ArenaSizex-100,this.position);
                    stl++;
                    this.action="";
                }
                this.turn=0;
            }
            for(int i=0;i<numberStrong;i++){
                if(strong[i]!=null){
                    strong[i].move();
                    strong[i].attack();
                    strong[i].draw();
                }
            }
            for(int k=0;k<numberRange;k++){
                if(range[k]!=null){
                    range[k].move();
                    range[k].attack();
                    range[k].draw();
                }
            }
            for(int z=0;z<numberStealth;z++){
                if(stealth[z]!=null){
                    stealth[z].move();
                    stealth[z].attack();
                    stealth[z].draw();
                }
            }
            cal = Calendar.getInstance();
            if(((D==false )|| (cal.getTimeInMillis() - this.currentTime)>= 5000) && c<100){
                long time = (cal.getTimeInMillis() - this.currentTime);
                UI.println(time);
                cops[c] = new DefenceCharacters (("level" + Integer.toString(level)),this.defx,this.defy);
                c++;
                this.defy+=100;
                if(this.defy>=ArenaSize){
                    this.defy=0;
                    this.defx-=50;
                    if(this.defx<=0){
                        this.defx=250;
                    }
                }
                numCops++;
                if(D){
                    this.currentTime = cal.getTimeInMillis();
                }
            }

            for(int a=0;a<numCops;a++){
                cops[a].draw();
            }

            D=true;
            UI.sleep(40);
        }

        nextLevel();

    }

    public void nextLevel(){
        if(this.level<6){
            this.level++;
            for(int i=0;i<numberStrong;i++){
                strong[i]=null;
            }
            for(int k=0;k<numberRange;k++){
                range[k]=null;
            }
            for(int z=0;z<numberStealth;z++){
                stealth[z]=null;

            }
            for(int c=0;c<100;c++){
                cops[c]=null;

            }
            store();
        }else{
            won();
        }
    }

    public void won(){
        UI.drawString("YOU WIN!!!",200,350);
        UI.sleep(1000);
        UI.drawString("to play again press SPACE",200,450);
    }

    public void lose(){
        UI.drawString("YOU LOOSE!!!",200,350);
        UI.sleep(1000);
        UI.drawString("to play again press SPACE",200,450);
    }

    public void draw(){
        SwingUtilities.invokeLater(()->{
                UI.setImmediateRepaint(false);
                UI.setColor(Color.black);
                UI.setLineWidth(2);
                UI.clearGraphics();
                UI.drawRect(left,top,ArenaSizex,ArenaSize);
                drawWall();
                UI.repaintAllGraphics();
            });
    }

    public void drawWall(){
        if (this.wallHP == this.wallStartHP/2){
            this.wall=this.wall + "broke";
        }
        UI.drawImage(this.wall + ".jpg",wallX,0,20,ArenaSize);
    }

    public static void main(String[] arguments){
        new Game();
    }
}
