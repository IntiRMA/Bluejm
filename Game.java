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
    private int numberStealthStrong;
    private int numberStrongRange;
    private int numberRangeStealth;
    private double wallHP;
    private double wallStartHP;
    public double alarmHP;
    public double alarmStartHP;
    private String wall = "level";
    public static int level=1;
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
    private int stealthStrongLeft=0;
    private int strongRangeLeft=0;
    private int rangeStealthLeft=0;
    private int alarmy;
    private boolean ALARM=true;
    private String stat ="";
    private static final int numCops=4;
    Store shop = new Store();
    boolean firstRound=true;
    int loopStrong = 0;
    int loopStealth = 0;
    int loopRange = 0;
    int loopStealthStrong= 0;
    int loopStrongRange= 0;
    int loopRangeStealth= 0;

    Figures[] strong = new Figures[50];
    Figures[] stealth = new Figures[50];
    Figures[] range = new Figures[50];
    Figures[] stealthStrong = new Figures[50];
    Figures[] strongRange = new Figures[50];
    Figures[] rangeStealth = new Figures[50];
    DefenceCharacters[] cop = new DefenceCharacters [4];

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
        UI.drawString("An evil overlord has been Stealing memes and hiding them deep in an underground vault for his eyes only",100,200);
        UI.drawString("you will get to upgrade Figures and also get new ones, the robbery will be more dificult each level ",100,250);
        UI.drawString("Figure out the best combinations to beat the game and return the memes to the people! ",100,300);
        UI.setColor(Color.red.brighter());
        UI.setFontSize(25);
        UI.sleep(1000);
        UI.drawString("When you are ready press SPACE",200,350);
    }

    public void controls(String key){
        if(key.equalsIgnoreCase("space")&&firstRound){
            level=1;
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
        if(key.equalsIgnoreCase("4")){
            this.type="rangeStealth";
        }
        if(key.equalsIgnoreCase("5")){
            this.type= "strongRange";
        }
        if(key.equalsIgnoreCase("6")){
            this.type= "stealthStrong";
        }

    }

    public void store(){
        firstRound=false;
        boolean T =false;
        while (T==false){
            shop.drawStore();
            this.numberStrong = shop.strong();
            this.numberStealth = shop.stealth();
            this.numberRange = shop.range();
            this.numberStealthStrong=shop.strongStealth();
            this.numberStrongRange=shop.strongRange();
            this.numberRangeStealth=shop.stealthRange();
            this.strongLeft = this.numberStrong;
            this.stealthLeft = this.numberStealth;
            this.rangeLeft = this.numberRange;
            this.stealthStrongLeft= this.numberStealthStrong;
            this.strongRangeLeft= this.numberStrongRange;
            this.rangeStealthLeft= this.numberRangeStealth;
            T=shop.bool();
            if(T){
                break;
            }
        }
        Store.draw=false;
        startGame();
    }

    public void startGame(){
        draw();
        int st = 0;
        int rg= 0;
        int stl = 0;
        int stst=0;
        int sr=0;
        int rs=0;
        int c = 0;
        boolean D=false;
        boolean lv =false;
        alarm();
        if(level==1){
            this.wallHP=100;
            this.wallStartHP=100;
            this.alarmHP=100;
            this.alarmStartHP =100;
        }else{
            this.wallHP=100*level;
            this.wallStartHP=100*level;
            this.alarmHP=100*level;
            this.alarmStartHP=100*level;
        }
        UI.setMouseListener(this::mouse);
        UI.setKeyListener(this::controls);
        Calendar cal = Calendar.getInstance();
        Calendar calen = Calendar.getInstance();
        Calendar ninj = Calendar.getInstance();
        Calendar ninj2 = Calendar.getInstance();
        Calendar gun = Calendar.getInstance();

        time = calen.getTimeInMillis();
        long timeninj = ninj.getTimeInMillis();
        long timeninj2 = ninj2.getTimeInMillis();
        long timegun = gun.getTimeInMillis();
        long timern = timegun;
        long timecop = timegun;
        loopStrong = this.numberStrong;
        loopStealth = this.numberStealth;
        loopRange = this.numberRange;
        loopStealthStrong= this.numberStealthStrong;
        loopStrongRange= this.numberStrongRange;
        loopRangeStealth= this.numberRangeStealth;

        while((this.numberStrong!=0 || this.numberStealth!=0 || this.numberRange!=0 ||this.numberStealthStrong!=0 || this.numberStrongRange!=0 || this.numberRangeStealth!=0 )&& this.wallHP!=0){
            //UI.setKeyListener(this::controls);

            if(D){
                draw();
            }

            if(action.equalsIgnoreCase("pressed") && this.type.equals("strong")){
                if(st<loopStrong && (position>0 && position<ArenaSize)){
                    if(strong[st]==null){
                        strong[st] = new Figures("strongDude",ArenaSizex-100,this.position);
                        st++;
                        this.strongLeft--;
                        this.action="";
                    }
                }
            }
            if(action.equalsIgnoreCase("pressed") && this.type.equals("range")){
                if(rg<loopRange && (position>0 && position<ArenaSize)){
                    if(range[rg]==null){
                        range[rg] = new Figures("rangeDude",ArenaSizex-100,this.position);
                        rg++;
                        this.rangeLeft--;
                        this.action="";
                    }
                }
            }
            if(action.equalsIgnoreCase("pressed") && this.type.equals("stealth")){
                if(stl<loopStealth && (position>0 && position<ArenaSize)){
                    if(stealth[stl]==null){
                        stealth[stl] = new Figures("stealthDude",ArenaSizex-100,this.position);
                        stl++;
                        this.stealthLeft--;
                        this.action="";
                    }
                }
            }
            if(action.equalsIgnoreCase("pressed") && this.type.equals("strongRange")){
                if(sr<loopStrongRange && (position>0 && position<ArenaSize)){
                    if(strongRange[sr]==null){
                        strongRange[sr] = new Figures("strongRange",ArenaSizex-100,this.position);
                        sr++;
                        this.strongRangeLeft--;
                        this.action="";
                    }
                }
            }
            if(action.equalsIgnoreCase("pressed") && this.type.equals("rangeStealth")){
                if(rs<loopRangeStealth && (position>0 && position<ArenaSize)){
                    if(rangeStealth[rs]==null){
                        rangeStealth[rs] = new Figures("rangeStealth",ArenaSizex-100,this.position);
                        rs++;
                        this.rangeStealthLeft--;
                        this.action="";
                    }
                }
            }
            if(action.equalsIgnoreCase("pressed") && this.type.equals("stealthStrong")){
                if(stst<loopStealthStrong && (position>0 && position<ArenaSize)){
                    if(stealthStrong[stst]==null){
                        stealthStrong[stst] = new Figures("stealthStrong",ArenaSizex-100,this.position);
                        stst++;
                        this.stealthStrongLeft--;
                        this.action="";
                    }
                }
            }
            calen=Calendar.getInstance();
            long time2 = calen.getTimeInMillis();
            for(int i=0;i<loopStrong;i++){
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
            for(int k=0;k<loopRange;k++){
                if(range[k]!=null){
                    //range[k].move();
                    range[k].draw();
                }
            }

            ninj=Calendar.getInstance();
            long time4 = ninj.getTimeInMillis();
            for(int z=0;z<loopStealth;z++){
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

            ninj2=Calendar.getInstance();
            long time11 = ninj2.getTimeInMillis();
            for(int b=0;b<loopStealthStrong;b++){{
                    if(stealthStrong[b]!=null){
                        if(ALARM){
                            stealthStrong[b].move();
                            if(time11 - timeninj2>=500){
                                timeninj2=time11;
                                alarmHP-=stealthStrong[b].alarmOFF(alarmy);
                                if(alarmHP<=0){
                                    ALARM=false;
                                }
                            }
                            stealthStrong[b].draw();

                        }
                        if(ALARM==false){
                            stealthStrong[b].move();

                            if(time11 - time>=500){
                                this.wallHP-=stealthStrong[b].rangeAttack();
                                time=time11;
                            }
                            stealthStrong[b].draw();

                        }
                    }
                }
            }

            long time9 = calen.getTimeInMillis();
            for(int e=0;e<loopStrongRange;e++){
                if(strongRange[e]!=null){
                    timegun = time3;
                    double damage = strongRange[e].attackCop();
                    double HP=0;
                    for(int a=0;a<numCops;a++){

                        if(this.cop[a]!=null){

                            if((strongRange[e].getY())<(this.cop[a].getY()+20)&&(strongRange[e].getY())>(this.cop[a].getY()-20)){
                                HP = this.cop[a].shot(damage/14);

                            }
                            if(this.cop[a].HP<=0){
                                this.cop[a].erase();
                                this.cop[a]=null;

                            }
                        }else{
                            continue;
                        }

                    }

                    strongRange[e].move();
                    if(time9 - time>=500){
                        this.wallHP-=strongRange[e].rangeAttack();
                        time=time9;
                    }
                    strongRange[e].draw();
                }
            }

            long time10 = calen.getTimeInMillis();
            for(int h=0;h<loopRangeStealth;h++){
                if(rangeStealth[h]!=null){

                    if(ALARM){
                        if(time10 - timern>=500){
                            alarmHP-=rangeStealth[h].alarmOFF(alarmy);
                            if(alarmHP<=0){
                                ALARM=false;
                            }
                            timern=time10;
                        }

                    }else{
                        double damage = rangeStealth[h].attackCop();
                        double HP=0;
                        for(int a=0;a<numCops;a++){

                            if(this.cop[a]!=null){

                                if((rangeStealth[h].getY())<(this.cop[a].getY()+20)&&(rangeStealth[h].getY())>(this.cop[a].getY()-20)){
                                    HP = this.cop[a].shot(damage/100);

                                }
                                if(this.cop[a].HP<=0){
                                    this.cop[a].erase();
                                    this.cop[a]=null;

                                }
                            }else{
                                continue;
                            }

                        }

                    }
                    rangeStealth[h].draw();

                }
            }

            if(ALARM){
                defy=90;
                defx=430;
                for(int a=0;a<numCops;a++){

                    if(this.cop[a]==null){
                        this.cop[a] = new DefenceCharacters (("level" + Integer.toString(level)),this.defx,this.defy);

                    }
                    defy+=75;
                    defx-=50;
                }
                defy=90;
                defx=430;
            }

            for(int a=0;a<numCops;a++){

                if(this.cop[a]!=null){
                    this.cop[a].draw();
                }

            }

            for(int k=0;k<loopRange;k++){

                if(range[k]!=null){

                    timegun = time3;
                    double damage = range[k].attackCop();
                    double HP=1;
                    for(int a=0;a<numCops;a++){

                        if(this.cop[a]!=null){

                            if((range[k].getY())<(this.cop[a].getY()+20)&&(range[k].getY())>(this.cop[a].getY()-20)){
                                HP = this.cop[a].shot(damage);

                            }
                            if(this.cop[a].HP<=0){
                                this.cop[a].erase();
                                this.cop[a]=null;

                            }
                        }else{
                            continue;
                        }

                    }
                }
            }

            //cops attack
            cal = Calendar.getInstance();
            long time5 = cal.getTimeInMillis();
            if(time5 - timecop >= 500){
                timecop=time5;
                for(int a=0;a<numCops;a++){
                    if(this.cop[a]!=null){
                        for(int i=0;i<loopStrong;i++){
                            if(strong[i]!=null){
                                boolean hit = this.cop[a].attack(strong[i].getX(),strong[i].getY(),strong[i].stealthRatio());
                                double HP =1;
                                if(hit){
                                    HP = strong[i].hit();

                                }
                                if (this.strong[i].HP<=0){
                                    strong[i].erase();
                                    strong[i]=null;
                                    numberStrong--;
                                }
                            }
                        }
                        for (int e=0;e<loopStrongRange;e++){
                            if(strongRange[e]!=null){
                                boolean hit = this.cop[a].attack(strongRange[e].getX(),strongRange[e].getY(),strongRange[e].stealthRatio());
                                double HP =1;
                                if(hit){
                                    HP = strongRange[e].hit();

                                }
                                if (this.strongRange[e].HP<=0){
                                    strongRange[e].erase();
                                    strongRange[e]=null;
                                    numberStrongRange--;
                                }
                            }
                        }

                        for(int k=0;k<loopRange;k++){
                            if(range[k]!=null){
                                boolean hit = this.cop[a].attack(range[k].getX(),range[k].getY(),range[k].stealthRatio());
                                double HP =1;
                                if(hit){
                                    HP = range[k].hit();

                                }
                                if (this.range[k].HP<=0){
                                    range[k].erase();
                                    range[k]=null;
                                    numberRange--;
                                }
                            }
                        }
                        for(int z=0;z<loopStealth;z++){
                            if(stealth[z]!=null){
                                boolean hit = this.cop[a].attack(stealth[z].getX(),stealth[z].getY(),stealth[z].stealthRatio());
                                double HP =1;
                                if(hit){
                                    HP = stealth[z].hit();

                                }
                                if (this.stealth[z].HP<=0){
                                    stealth[z].erase();
                                    stealth[z]=null;
                                    numberStealth--;
                                }
                            }
                        }
                        for(int h=0;h<loopRangeStealth;h++){
                            if(rangeStealth[h]!=null){
                                boolean hit = this.cop[a].attack(rangeStealth[h].getX(),rangeStealth[h].getY(),rangeStealth[h].stealthRatio());
                                double HP =1;
                                if(hit){
                                    HP = rangeStealth[h].hit();

                                }
                                if (this.rangeStealth[h].HP<=0){
                                    rangeStealth[h].erase();
                                    rangeStealth[h]=null;
                                    numberRangeStealth--;
                                }
                            }
                        }
                        for(int b=0;b<loopStealthStrong;b++){
                            if(stealthStrong[b]!=null){
                                boolean hit = this.cop[a].attack(stealthStrong[b].getX(),stealthStrong[b].getY(),stealthStrong[b].stealthRatio());
                                double HP =1;
                                if(hit){
                                    HP = stealthStrong[b].hit();

                                }
                                if (this.stealthStrong[b].HP<=0){
                                    stealthStrong[b].erase();
                                    stealthStrong[b]=null;
                                    numberStealthStrong--;
                                }
                            }
                        }
                    }
                }
            }

            D=true;
            if((this.numberStrong<=0 && this.numberStealth<=0 && this.numberRange<=0 && this.numberStealthStrong<=0 && this.numberStrongRange<=0 && this.numberRangeStealth<=0)&& this.wallHP>0){
                UI.clearGraphics();
                lose();
                break;
            }

            if(wallHP<=0){
                lv=true;
                break;
            }

            if(this.alarmHP<=0){
                this.numberStealth=0;
                for(int z=0;z<loopStealth;z++){
                    if(stealth[z]!=null){
                        stealth[z].erase();
                        stealth[z]=null;
                    }
                }
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
            for(int i=0;i<loopStrong;i++){
                if(strong[i]!=null){
                    strong[i].erase();
                }
            }
            for(int k=0;k<loopRange;k++){
                if(range[k]!=null){
                    range[k].erase();
                }
            }
            for(int a=0;a<numCops;a++){
                if(this.cop[a]!=null){
                    this.cop[a].erase();
                }
            }

            for(int i=0;i<loopStrong;i++){
                strong[i]=null;
            }

            for(int k=0;k<loopRange;k++){
                range[k]=null;
            }
            for(int c=0;c<numCops;c++){
                cop[c]=null;

            }
            for(int b=0;b<loopStealthStrong;b++){
                stealthStrong[b]=null;
            }

            for(int e=0;e<loopStrongRange;e++){
                strongRange[e]=null;
            }

            for(int h=0;h<loopRangeStealth;h++){
                rangeStealth[h]=null;
            }

            for(int z=0;z<loopStealth;z++){
                if(stealth[z]!=null){
                    stealth[z].erase();
                    stealth[z]=null;
                }
            }
            UI.clearGraphics();
            this.stat="";
            defy=0;
            defx=250;
            UI.repaintAllGraphics();
            Store.Cash+=(500*level)/1.111111111111;
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
        alarmy = 300;
        ALARM = true;
    }

    public void won(){
        for(int i=0;i<loopStrong;i++){
            if(strong[i]!=null){
                strong[i].erase();
            }
        }
        for(int k=0;k<loopRange;k++){
            if(range[k]!=null){
                range[k].erase();
            }
        }
        for(int a=0;a<numCops;a++){
            if(cop[a]!=null){
                cop[a].erase();
            }
        }
        for(int i=0;i<loopStrong;i++){
            strong[i]=null;
        }
        for(int k=0;k<loopRange;k++){
            range[k]=null;
        }
        for(int c=0;c<numCops;c++){
            cop[c]=null;

        }
        for(int b=0;b<loopStealthStrong;b++){
            stealthStrong[b]=null;
        }

        for(int e=0;e<loopStrongRange;e++){
            strongRange[e]=null;
        }

        for(int h=0;h<loopRangeStealth;h++){
            rangeStealth[h]=null;
        }
        //UI.clearGraphics();
        //UI.eraseRect(0,0,1200,900);
        UI.clearGraphics();
        UI.sleep(40);
        UI.repaintAllGraphics();
        UI.clearGraphics();
        UI.setColor(Color.red);
        UI.drawString("YOU WIN!!!",200,350);
        UI.sleep(1000);
        UI.repaintAllGraphics();
        firstRound=true;
        UI.drawString("to play again press SPACE",200,450);
        Store.numStealth=0;
        Store.numStrong=0;
        Store.numRange=0;
        Store.numStrongStealth=0;
        Store.numStrongRange=0;
        Store.numStealthRange=0;
        Store.numStrongStealthRange=0;
        Store.Cash=500;
    }

    public void lose(){
        for(int i=0;i<loopStrong;i++){
            if(strong[i]!=null){
                strong[i].erase();
            }
        }
        for(int k=0;k<loopRange;k++){
            if(range[k]!=null){
                range[k].erase();
            }
        }
        for(int a=0;a<numCops;a++){
            if(cop[a]!=null){
                cop[a].erase();
            }
        }
        for(int i=0;i<loopStrong;i++){
            strong[i]=null;
        }
        for(int k=0;k<loopRange;k++){
            range[k]=null;
        }
        for(int c=0;c<numCops;c++){
            cop[c]=null;

        }
        for(int b=0;b<loopStealthStrong;b++){
            stealthStrong[b]=null;
        }

        for(int e=0;e<loopStrongRange;e++){
            strongRange[e]=null;
        }

        for(int h=0;h<loopRangeStealth;h++){
            rangeStealth[h]=null;
        }
        //UI.clearGraphics();
        //UI.eraseRect(0,0,1200,900);
        UI.clearGraphics();
        UI.sleep(40);
        UI.repaintAllGraphics();
        UI.clearGraphics();
        UI.setColor(Color.red);
        UI.drawString("YOU LOSE!!!",200,350);
        UI.sleep(1000);
        UI.repaintAllGraphics();
        firstRound=true;
        UI.drawString("to play again press SPACE",200,450);
        Store.numStealth=0;
        Store.numStrong=0;
        Store.numRange=0;
        Store.numStrongStealth=0;
        Store.numStrongRange=0;
        Store.numStealthRange=0;
        Store.numStrongStealthRange=0;
        Store.Cash=500;
    }

    public void draw(){
        SwingUtilities.invokeLater(()->{
                UI.setImmediateRepaint(false);
                UI.setColor(Color.black);
                UI.setLineWidth(2);
                UI.clearGraphics();
                UI.setColor(Color.green.darker());
                UI.fillRect(0, 0, 1200, 1000);
                UI.drawRect(left,top,ArenaSizex,ArenaSize);
                drawWall();

                UI.setColor(Color.black);
                UI.setFontSize(15);
                UI.drawString("Press 1",40, ArenaSize + 20);
                UI.drawImage("stealthDude.png",20, ArenaSize + 40 ,72,90);
                UI.setColor(Color.red.brighter());
                UI.drawString(( Integer.toString(this.stealthLeft) + "X"),40,ArenaSize + 150);

                UI.setColor(Color.black);
                UI.drawString("Press 2",190,ArenaSize + 20);
                UI.drawImage("strongDude.png",170,ArenaSize + 40,60,90);
                UI.setColor(Color.red.brighter());
                UI.drawString(( Integer.toString(this.strongLeft) + "X"),190,ArenaSize + 150);

                UI.setColor(Color.black);
                UI.drawString("Press 3",340,ArenaSize + 20);
                UI.drawImage("rangeDude.png",320,ArenaSize + 40,50,90);
                UI.setColor(Color.red.brighter());
                UI.drawString(( Integer.toString(this.rangeLeft) + "X"),340,ArenaSize + 150);

                UI.setColor(Color.black);
                UI.drawString("Press 4",490,ArenaSize + 20);
                UI.drawImage("rangeStealth.png",470,ArenaSize + 40,51.5,90);
                UI.setColor(Color.red.brighter());
                UI.drawString(( Integer.toString(this.rangeStealthLeft) + "X"),490,ArenaSize + 150);

                UI.setColor(Color.black);
                UI.drawString("Press 5",640,ArenaSize + 20);
                UI.drawImage("strongRange.png",620,ArenaSize + 40,57,90);
                UI.setColor(Color.red.brighter());
                UI.drawString(( Integer.toString(this.strongRangeLeft) + "X"),640,ArenaSize + 150);

                UI.setColor(Color.black);
                UI.drawString("Press 6",790,ArenaSize + 20);
                UI.drawImage("stealthStrong.png",770,ArenaSize + 40,67,90);
                UI.setColor(Color.red.brighter());
                UI.drawString(( Integer.toString(this.stealthStrongLeft) + "X"),790,ArenaSize + 150);

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
                if(this.alarmHP<this.alarmStartHP&&this.alarmHP!=0){
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
            UI.drawImage(this.wall + "halfbroke" + Integer.toString(level) + ".png",0,0,ArenaSizex,ArenaSize);
        }
        if (this.wallHP <= this.wallStartHP/4){
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
