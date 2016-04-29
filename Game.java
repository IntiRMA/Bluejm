import ecs100.*;
public class Game{
    public static final double ArenaSize =800;
    public static final int top =0;
    public static final int left=0;
    private int numberStrong;
    private int numberStelth;
    private int numberRange;
    private double wallHP;
    private double wallStartHP;
    private String wall = "wall";
    private int level;
    private int turn=0;
    private String action="";
    Characters[] strong = new Characters[100];
    Characters[] stelth = new Characters[100];
    Characters[] range = new Characters[100];

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
        UI.setColor(Color.red.brighter());
        UI.drawString("This game is called Heist!!",200,100);
        UI.sleep(1000);
        UI.setFontSize(16);
        UI.setColor(Colore.black);
        UI.drawString("your goal is to rob a bank!",200,150);
        UI.drawString("you will get to upgrade Characterss and also get new ones, the robbery will be more dificult each level ",200,200);
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
        Store shop = new Store();
        this.numberStrong = this.shop.numStrong();
        this.numberStelth = this.shop.numStelth();
        this.numberRange = this.shop.numRange();

    }

    public void startGame(){
        for(int i =0;i<numberStrong;i++){
            String strongfig = ("strong" + Integer.toString(i));
            Characters (strongfig) = null;  
            strong.add(strongfig);
        }
        for(int k =0;k<numberStelth;k++){
            String stelthfig = ("stelth" + Integer.toString(k));
            Characters (stelthfig) = null;  
            stelth.add( stelthfig);
        }
        for(int z =0;z<numberRange;z++){
            String rangefig = ("range" + Integer.toString(z));
            Characters (rangefig) = null;  
            range.add(rangefig);
        }
        draw();
        int st = 0;
        int rg= 0;
        int stl = 0;
        while((numberStrong!=0 || numberStelth!=0 || numberRange!=0)&& this.wallHP!=0){

            if(action.equalsIgnoreCase("pressed") && this.turn==0){
                if(st<strong.length() && (position>0 && position<ArenaSize)){
                    strong[st] = new Charactes("strong",ArenaSize,this.position);
                    st++;
                    this.action="";
                }
                this.turn=1;
            }
            if(action.equalsIgnoreCase("pressed") && this.turn==1){
                if(rg<range.length() && (position>0 && position<ArenaSize)){
                    range[rg] = new Charactes("range",ArenaSize,this.position);
                    rg++;
                    this.action="";
                }
                this.turn=2;
            }
            if(action.equalsIgnoreCase("pressed") && turn==2){
                if(stl<stelth.length() && (position>0 && position<ArenaSize)){
                    stelth[stl] = new Charactes("strong",ArenaSize,this.position);
                    stl++;
                    this.action="";
                }
                this.turn=0;
            }
            for(int i=0;i<strong.length();i++){
                if(strong[i]!=null){
                    strong[i].move();
                    strong[i].atack();
                    strong[i].draw();
                }
            }
            for(int k=0;k<range.length();k++){
                if(range[k]!=null){
                    range[k].move();
                    range[k].atack();
                    range[k].draw();
                }
            }
            for(int z=0;z<stelth.length();z++){
                if(stelth[z]!=null){
                    stelth[z].move();
                    stelth[z].atack();
                    stelth[z].draw();
                }
            }
            draw();
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

    public void won(){
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
            this.wall=thi.wall + "broke";
        }
        UI.drawImage(this.wall,300,0,20,ArenaSize);
    }

    public static void main(String[] arguments){
        new Game();
    }
}
