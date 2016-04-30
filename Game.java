import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Game{
    public static final double ArenaSize =400;
    public static final double wallX =300;
    public static final double ArenaSizex =900;
    public static final int top =0;
    public static final int left=0;
    private int numberStrong;
    private int numberStealth;
    private int numberRange;
    private double wallHP;
    private double wallStartHP;
    public double alarmHP;
    private String wall = "wall";
    private int level=1;
    private String type = "strong";
    private double position;
    private String action="";
    private long currentTime;
    private long time;
    private double defx =250;
    private double defy =0;
    private int rangeLeft=0;
    private int strongLeft=0;
    private int stealthLeft=0;
    private int alarmy;
    private boolean ALARM;
    private String stat ="";
    int numCops=0;

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
        if(this.position<100){
            this.position=1;
        }else if(this.position<200){
            this.position=100;
        }else if(this.position<300){
            this.position=200;
        }else if(this.position<400){
            this.position=300;
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
        if(key.equalsIgnoreCase("1")){
            this.type="stealth";
        }
        if(key.equalsIgnoreCase("2")){
            this.type="strong";
        }
        if(key.equalsIgnoreCase("3")){
            this.type="range";
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
            this.strongLeft = this.numberStrong;
            this.stealthLeft = this.numberStealth;
            this.rangeLeft = this.numberRange;
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
        numCops=0;
        boolean D=false;
        boolean lv =false;
        alarm();
        if(level==1){
            this.wallHP=100;
            this.wallStartHP=100;
            this.alarmHP=10;
        }
        UI.setMouseListener(this::mouse);
        UI.setKeyListener(this::controls);
        Calendar cal = Calendar.getInstance();
        Calendar calen = Calendar.getInstance();
        time = calen.getTimeInMillis();
        this.currentTime = cal.getTimeInMillis();
        while((this.numberStrong!=0 || this.numberStealth!=0 || this.numberRange!=0)&& this.wallHP!=0){
            UI.setKeyListener(this::controls);
            if(D){
                draw();
            }
            if(action.equalsIgnoreCase("pressed") && this.type.equals("strong")){
                if(st<numberStrong && (position>0 && position<ArenaSize)){
                    strong[st] = new Figures("strongDude",ArenaSizex-100,this.position);
                    st++;
                    this.strongLeft--;
                    this.action="";
                }
            }
            if(action.equalsIgnoreCase("pressed") && this.type.equals("range")){
                if(rg<numberRange && (position>0 && position<ArenaSize)){
                    range[rg] = new Figures("rangeDude",ArenaSizex-100,this.position);
                    rg++;
                    this.rangeLeft--;
                    this.action="";
                }
            }
            if(action.equalsIgnoreCase("pressed") && this.type.equals("stealth")){
                if(stl<numberStealth && (position>0 && position<ArenaSize)){
                    stealth[stl] = new Figures("stealthDude",ArenaSizex-100,this.position);
                    stl++;
                    this.stealthLeft--;
                    this.action="";
                }
            }
            calen=Calendar.getInstance();
            long time2 = calen.getTimeInMillis();
            for(int i=0;i<numberStrong;i++){
                if(strong[i]!=null){
                    strong[i].move();
                    if(time2 - time>=500){
                        this.wallHP-=strong[i].attack();
                        //time=time2;
                    }
                    strong[i].draw();
                }
            }
            for(int k=0;k<numberRange;k++){
                if(range[k]!=null){
                    range[k].move();
                    //                     if(time2 - time>=500){
                    //                         wallHP-=range[k].attack();
                    //                         
                    //                     }
                    range[k].draw();
                }
            }
            for(int z=0;z<numberStealth;z++){
                if(stealth[z]!=null){
                    stealth[z].move();
                    if(time2 - time>=500){
                        time=time2;
                        alarmHP-=stealth[z].alarmOFF(alarmy);
                        if(alarmHP<=0){
                            ALARM=false;
                        }
                    }
                    stealth[z].draw();
                }
            }
            cal = Calendar.getInstance();
            if(((D==false )|| (cal.getTimeInMillis() - this.currentTime)>= 5000) && c<100 && ALARM){
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
            if((this.numberStrong==0 && this.numberStealth==0 && this.numberRange==0)&& this.wallHP!=0){
                lose();
                break;
            }
            if(wallHP<=0){
                lv=true;
                break;
            }
            UI.sleep(40);
        }
        if(lv){
            nextLevel();
        }
    }

    public void nextLevel(){
        if(this.level<6){
            UI.clearGraphics();
            this.level++;
            for(int i=0;i<numberStrong;i++){
                if(strong[i]!=null){
                    strong[i].erase();
                }
            }
            for(int k=0;k<numberRange;k++){
                if(range[k]!=null){
                    range[k].erase();
                }
            }
            for(int z=0;z<numberStealth;z++){
                if(stealth[z]!=null){
                    stealth[z].erase();
                }

            }
            for(int a=0;a<numCops;a++){
                if(cops[a]!=null){
                    cops[a].erase();
                }
            }

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
            UI.repaintGraphics();

            //             UI.sleep(10);
            //             UI.drawString("NEXT LEVEL",400,200);
            //             UI.sleep(5000);
            store();
        }else{
            won();
        }
    }

    public void alarm() {
        double alarmPosition = Math.random();
        if (alarmPosition > 0 && alarmPosition < 0.25){
            alarmy = 0;
        }
        else if (alarmPosition > 0.25 && alarmPosition < 0.5) {
            alarmy = 100;
        }
        else if (alarmPosition > 0.5 && alarmPosition < 0.75) {
            alarmy = 200;
        }
        else if (alarmPosition > 0.75 && alarmPosition < 1) {
            alarmy = 300;
        }
        ALARM = true;
    }

    public void won(){
        UI.clearGraphics();
        UI.drawString("YOU WIN!!!",200,350);
        UI.sleep(1000);
        UI.drawString("to play again press SPACE",200,450);
    }

    public void lose(){
        UI.clearGraphics();
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
                UI.setColor(Color.black);
                UI.setFontSize(15);
                UI.drawString("for this character press 1",0,ArenaSize + 20);
                UI.drawImage("stealthDude.png",50,ArenaSize + 40,50,100);
                UI.setColor(Color.red.brighter());
                UI.drawString(( Integer.toString(this.stealthLeft) + "X"),70,ArenaSize + 160);

                UI.setColor(Color.black);
                UI.drawString("for this character press 2",200,ArenaSize + 20);
                UI.drawImage("strongDude.png",225,ArenaSize + 40,150,100);
                UI.setColor(Color.red.brighter());
                UI.drawString(( Integer.toString(this.strongLeft) + "X"),290,ArenaSize + 160);

                UI.setColor(Color.black);
                UI.drawString("for this character press 3",400,ArenaSize + 20);
                UI.drawImage("rangeDude.png",450,ArenaSize + 40,50,100);
                UI.setColor(Color.red.brighter());
                UI.drawString(( Integer.toString(this.rangeLeft) + "X"),470,ArenaSize + 160);

                UI.setColor(Color.red.brighter());
                UI.drawString("WALL HP",ArenaSizex+9,12);
                UI.setColor(Color.black);
                UI.setLineWidth(1);
                UI.drawRect(ArenaSizex+9,19,51,16);
                UI.setColor(Color.green.brighter());
                UI.fillRect(ArenaSizex+10,20,wallHP/2,15);

                UI.setColor(Color.green.brighter());
                UI.drawString("ALARM STATUS",ArenaSizex+9,50);
                UI.setColor(Color.black);
                UI.setLineWidth(1);
                UI.drawRect(ArenaSizex+9,59,51,16);
                UI.setColor(Color.red.brighter());
                UI.fillRect(ArenaSizex+10,60,alarmHP*5,15);
                if(this.alarmHP<10&&this.alarmHP!=0){
                    this.stat="disabling";
                }
                if(this.alarmHP<=0){
                    UI.setColor(Color.green.brighter());
                    this.stat="disabled";
                }
                UI.drawString(this.stat,ArenaSizex+65,73.5);
                if(ALARM){
                    UI.setColor(Color.red);
                    UI.fillOval(10, alarmy, 50, 50);
                    UI.setColor(Color.black);
                }else{
                    UI.setColor(Color.green.brighter());
                    UI.fillOval(10, alarmy, 50, 50);
                    UI.setColor(Color.black);
                }
                UI.repaintAllGraphics();
            });
    }

    public void drawWall(){
        if (this.wallHP == this.wallStartHP/2){
            this.wall=this.wall + "broke";
        }
        UI.drawImage(this.wall + ".png",wallX,0,20,ArenaSize);
    }

    public static void main(String[] arguments){
        new Game();
    }
}
