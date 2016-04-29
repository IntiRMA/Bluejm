import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
public class Game{
    public static final double ArenaSize =800;
    public static final int top =0;
    public static final int left=0;
    private int numberStrong;
    private int numberStealth;
    private int numberRange;
    private double wallHP=600;
    private double wallStartHP=600;
    private String wall = "wall";
    private int level;
    private int turn=0;
    private double position;
    private String action="";
    Figures[] strong = new Figures[100];
    Figures[] stealth = new Figures[100];
    Figures[] range = new Figures[100];

    public Game(){
        UI.initialise();
        intro();
        UI.setMouseListener(this::mouse);
        UI.setKeyListener(this::controls);
    }

    public void mouse(String ev,double x,double y){

        if(ev.equalsIgnoreCase("pressed")){
            this.position = y;
        }
        if(this.position<200){
            position=50;
        }else if(this.position<400){
            position=250;
        }else if(this.position<600){
            position=450;
        }else if(this.position<800){
            position=650;
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
            this.level=1;
        }
    }

    public void store(){
        //         boolean T =false;
        //         Store shop = new Store();
        //         while (T==false){
        //             shop.drawStore();
        //             T=shop.bool();
        //             this.numberStrong = shop.strong();
        //             this.numberStealth = shop.stealth();
        //             this.numberRange = shop.range();
        //         }
        this.numberStrong = 5;
        this.numberStealth = 5;
        this.numberRange = 5;

        startGame();

    }

    public void startGame(){
        /* for(int i =0;i<numberStrong;i++){
        strong[i] = null;  
        }
        for(int k =0;k<numberStealth;k++){
        String stealthfig = ("stealth" + Integer.toString(k));
        Figures (stealthfig) = null;  
        stealth.add( stealthfig);
        }
        for(int z =0;z<numberRange;z++){
        String rangefig = ("range" + Integer.toString(z));
        Figures (rangefig) = null;  
        range.add(rangefig);
        }*/
        draw();
        int st = 0;
        int rg= 0;
        int stl = 0;
        boolean D=false;
        while((this.numberStrong!=0 || this.numberStealth!=0 || this.numberRange!=0)&& this.wallHP!=0){
            if(D){
                draw();
            }
            D=true;

            if(action.equalsIgnoreCase("pressed") && this.turn==0){
                if(st<numberStrong && (position>0 && position<ArenaSize)){
                    strong[st] = new Figures("strongDude",ArenaSize-100,this.position);
                    st++;
                    this.action="";
                }
                this.turn=1;
            }
            if(action.equalsIgnoreCase("pressed") && this.turn==1){
                if(rg<numberRange && (position>0 && position<ArenaSize)){
                    range[rg] = new Figures("rangeDude",ArenaSize-100,this.position);
                    rg++;
                    this.action="";
                }
                this.turn=2;
            }
            if(action.equalsIgnoreCase("pressed") && turn==2){
                if(stl<numberStealth && (position>0 && position<ArenaSize)){
                    stealth[stl] = new Figures("stealthDude",ArenaSize-100,this.position);
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
            //
            UI.sleep(20);
        }

        nextLevel();

    }

    public void nextLevel(){
        if(this.level<6){
            this.level++;
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
        UI.clearGraphics();
        UI.drawRect(left,top,ArenaSize,ArenaSize);
        drawWall();
        UI.repaintAllGraphics();
    }

    public void drawWall(){
        if (this.wallHP == this.wallStartHP/2){
            this.wall=this.wall + "broke";
        }
        UI.drawImage(this.wall + ".jpg",300,0,20,ArenaSize);
    }

    public static void main(String[] arguments){
        new Game();
    }
}
