import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Game{
    public static final double ArenaSize =500;
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
    private String wall = "level";
    private int level=1;
    private String type = "strong";
    private double position;
    private String action="";
    private long currentTime;
    private long time;
    private double defx =430;
    private double defy =90;
    private int rangeLeft=0;
    private int strongLeft=0;
    private int stealthLeft=0;
    private int alarmy;
    private boolean ALARM;
    private String stat ="";
    int numCops=0;
    Store shop = new Store();

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
        if(this.position<162){
            this.position=90;
        }else if(this.position<234){
            this.position=162;
        }else if(this.position<306){
            this.position=234;
        }else {
            this.position=306;
        }
        this.action = ev;
    }

    public void intro(){
        UI.setColor(Color.black);
        UI.fillRect(0,0,1000,700);
        UI.setFontSize(75);
        UI.setColor(Color.blue);
        UI.drawString("Heist!!",200,100);
        UI.sleep(1000);
        UI.setFontSize(16);

        UI.drawString("your goal is to rob a bank, different characters have different attributes for you to discover!",100,150);
        UI.drawString("Evil warlord jakku has been Stealing memes and hiding them deep in an underground vault for his eyes only",100,200);
        UI.drawString("you will get to upgrade Figures and also get new ones, the robbery will be more dificult each level ",100,250);
        UI.drawString("Figure out the best combinations to beat the game and return the memes to the people! ",100,300);
        UI.setColor(Color.red.brighter());
        UI.setFontSize(25);
        UI.sleep(1000);
        UI.drawString("When you are ready press SPACE",200,350);
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
        Store.draw=false;
        startGame();
    }

    public void startGame(){
        draw();
        int st = 0;
        int rg= 0;
        int stl = 0;
        int c = 0;
        numCops=4;
        boolean D=false;
        boolean lv =false;
        alarm();
        if(level==1){
            this.wallHP=100;
            this.wallStartHP=100;
            this.alarmHP=100;
        }else{
            this.wallHP=100*level;
            this.wallStartHP=100*level;
            this.alarmHP=100*level;
        }
        UI.setMouseListener(this::mouse);
        UI.setKeyListener(this::controls);
        Calendar cal = Calendar.getInstance();
        Calendar calen = Calendar.getInstance();
        Calendar ninj = Calendar.getInstance();
        Calendar gun = Calendar.getInstance();

        time = calen.getTimeInMillis();
        long timeninj = ninj.getTimeInMillis();
        long timegun = gun.getTimeInMillis();

        while((this.numberStrong!=0 || this.numberStealth!=0 || this.numberRange!=0)&& this.wallHP!=0){
            UI.setKeyListener(this::controls);
            if(D){
                draw();
            }
            if(action.equalsIgnoreCase("pressed") && this.type.equals("strong")){
                if(st<numberStrong && (position>0 && position<ArenaSize)){
                    if(strong[st]==null){
                        strong[st] = new Figures("strongDude",ArenaSizex-100,this.position);
                        st++;
                        this.strongLeft--;
                        this.action="";
                    }
                }
            }
            if(action.equalsIgnoreCase("pressed") && this.type.equals("range")){
                if(rg<numberRange && (position>0 && position<ArenaSize)){
                    if(range[rg]==null){
                        range[rg] = new Figures("rangeDude",ArenaSizex-100,this.position);
                        rg++;
                        this.rangeLeft--;
                        this.action="";
                    }
                }
            }
            if(action.equalsIgnoreCase("pressed") && this.type.equals("stealth")){
                if(stl<numberStealth && (position>0 && position<ArenaSize)){
                    if(stealth[stl]==null){
                        stealth[stl] = new Figures("stealthDude",ArenaSizex-100,this.position);
                        stl++;
                        this.stealthLeft--;
                        this.action="";
                    }
                }
            }
            calen=Calendar.getInstance();
            long time2 = calen.getTimeInMillis();
            for(int i=0;i<numberStrong;i++){
                if(strong[i]!=null){
                    strong[i].move();
                    if(time2 - time>=500){
                        this.wallHP-=strong[i].attack();
                        time=time2;
                    }
                    strong[i].draw();
                }
            }
            gun=Calendar.getInstance();
            long time3 = gun.getTimeInMillis();
            for(int k=0;k<numberRange;k++){
                if(range[k]!=null){
                    range[k].move();
                    //                     if(time3 - timegun>=500){
                    //                         wallHP-=range[k].attack();
                    //                         
                    //                     }
                    range[k].draw();
                }
            }
            ninj=Calendar.getInstance();
            long time4 = ninj.getTimeInMillis();
            for(int z=0;z<numberStealth;z++){
                if(stealth[z]!=null){
                    stealth[z].move();
                    if(time4 - timeninj>=500){
                        timeninj=time4;
                        alarmHP-=stealth[z].alarmOFF(alarmy);
                        if(alarmHP<=0){
                            ALARM=false;
                        }
                    }
                    stealth[z].draw();
                }
            }
            cal = Calendar.getInstance();
            if(ALARM){
                defy=90;
                defx=430;
                for(int a=0;a<numCops;a++){
                    if(cops[a]==null){
                        cops[a] = new DefenceCharacters (("level" + Integer.toString(level)),this.defx,this.defy);

                    }
                    defy+=75;
                    defx-=50;
                }
                defy=90;
                defx=430;
            }

            for(int a=0;a<numCops;a++){
                if(cops[a]!=null){
                    cops[a].draw();
                }
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
            UI.clearGraphics();
            defy=0;
            defx=250;
            UI.repaintAllGraphics();
            Store.Cash+=500*level;
            Store.numStealth=0;
            Store.numStrong=0;
            Store.numRange=0;
            Store.numStrongStealth=0;
            Store.numStrongRange=0;
            Store.numStealthRange=0;
            Store.numStrongStealthRange=0;

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
                UI.fillRect(ArenaSizex+10,20,wallHP/(2*level),15);

                UI.setColor(Color.green.brighter());
                UI.drawString("ALARM STATUS",ArenaSizex+9,50);
                UI.setColor(Color.black);
                UI.setLineWidth(1);
                UI.drawRect(ArenaSizex+9,59,51,16);
                UI.setColor(Color.red.brighter());
                UI.fillRect(ArenaSizex+10,60,alarmHP/(2*level),15);
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
        if (this.wallHP <= this.wallStartHP/2){
            UI.drawImage(this.wall + "broke" + Integer.toString(level) + ".png",0,0,ArenaSizex,ArenaSize);
        }
        if((this.wallHP > this.wallStartHP/2)){
            UI.drawImage(this.wall + Integer.toString(level) + ".png",0,0,ArenaSizex,ArenaSize);
        }
    }

    public static void main(String[] arguments){
        new Game();
    }
}
