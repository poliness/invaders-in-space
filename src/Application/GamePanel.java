package Application;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.*;
import static Application.Constants.*;
import java.awt.Font;

/**
 * Klasa GamePanel dziedziczy z klasy JPanel i implementuje interfejs KeyListener.
 * Zawiera najważniejsze metody, odpowiedzialą za rysowanie komponentów, 
 * inicjalizującą grę, reagującą na wciśnięcie klawisza na klawiaturze, sprawdzającą
 * kolizje obiektów oraz aktualizującą cały panel.
 * @author Paulina Zapletal
 */
public class GamePanel extends JPanel implements KeyListener {
    
    /**
     * Instancja klasy Player - w aplikacji jest to rakieta.
     */
    private Player player;
    
    /**
     * Instancja klasy GameThread - oddzielny wątęk odświeżający panel.
     */
    private GameThread gameThread;
    
    /**
     * Tablica przechowująca obiekty klasy Monster, w aplikacji są to potwory.
     */
    private Monster [][] monsterArray = new Monster[MONSTER_HEIGHT_COUNT][MONSTER_WIDTH_COUNT];
    
    /**
     * Lista przechowująca obiekty klasy Bullet, w aplikacji są to pociski.
     */
    public LinkedList<Bullet> bulletList = new LinkedList<>();
    
    /**
     * Lista przechowująca obiekty klasy Explosion, w aplikacji są to wybuchy.
     */
    public LinkedList<Explosion> explosionsList = new LinkedList<>();
    
    /**
     * Tablica przechowująca obiekty klasy Heart, w aplikacji są to życia 
     * rakiety.
     */
    private Heart[] hearts = new Heart[5];
    
    /**
     * Obiekty klasy Background, tworzą tło w aplikacji.
     */
    private Background background1, background2;
    
    
    /**
     * Zmienna przechowująca liczbę zdobytych punktów.
     */
    private int score = 0;
    
    /**
     * Zmienna przechowująca czas, w którym aplikacja została uruchomiona.
     */
    private long gameStartTime;
    
    /**
     * Zmienna informujące kiedy nastąpiła ostatnia aktualizacja.
     */
    private long monsterLastUpdateTime = System.currentTimeMillis();
    private long bulletLastUpdateTime = System.currentTimeMillis();
    private long lastMonsterBulletTime = System.currentTimeMillis();
    
    /**
     * Flaga służąca do sterowania pomiędzy grą a menu.
     */
    public boolean _gameMode = false;
     
    
    /**
     * Konstruktor klasy GamePanel, ustawia parametry panelu.
     */
    public GamePanel(){
        setSize(600,600);
        setFocusable(true);
        
        addKeyListener(this);
        setFocusable(true);
        setVisible(true);
    }
    
    /**
     * Metoda inicjalizująca grę.
     * Wywoływana zostaje metoda restart(), odpowiadająca za tworzenie nowych
     * obiektów, tworzone jest nowe tło oraz nowy wątek.
     */
    public void init(){
        
        restart();
        
        background1 = new Background(0,0);
        background2 = new Background(0,620);
        
        gameThread = new GameThread(this);
    }
    
    /**
     * Metoda restartująca grę,
     * tworzony jest nowy obiekt klasy Player (rakieta) na pozycji początkowej,
     * punkty są zerowane, 
     * na nowo tworzone są obiekty klasy Monster, obiekty klasy Heart.
     * Metoda uruchamia grę od nowa.
     */
    public void restart(){
        
        player = new Player(250,500,30);
        score = 0;
        
        for(int i=1; i<MONSTER_HEIGHT_COUNT+1; i++){
            for(int j = 1; j < MONSTER_WIDTH_COUNT+1; j++){
                Monster monster = new Monster((j-1)*43+10, i*37+20,1);
                monsterArray[i-1][j-1] = monster;
            }
        }
        
        for(int i=0; i<5; i++){
            hearts[i] = new Heart((i*heart.getWidth()),0);
        }
        
        gameStartTime = System.currentTimeMillis();
    }
    
    /**
     * Jedna z najważniejszych metod w programie, odpowiada za aktualizację 
     * pozycji wszystkich obiektów znajdujących się w panelu, dzięki temu
     * aplikacja działa płynnie.
     * Tło odświeżane jest cały czas, natomiast rozgrywka, gdy flaga
     * _gameMode jest ustawiona na true.
     * Na końcu wywoływana jest metoda sprawdzająca kolizje.
     */
    public void update(){
        repaint();
        
        background1.update();
        background2.update();
        
        if(_gameMode){
            
            player.updatePosition();

            long time_now = System.currentTimeMillis();
            if(time_now >= (monsterLastUpdateTime + 700)){
                for(int i = 0; i < MONSTER_HEIGHT_COUNT; i++){
                    for(int j=0; j<MONSTER_WIDTH_COUNT; j++){
                        monsterArray[i][j].updatePosition();
                    }
                }
                monsterLastUpdateTime = System.currentTimeMillis();
            }

            if(time_now >= (bulletLastUpdateTime + 50)){
                for(int i = 0; i < bulletList.size(); i++){
                    Bullet b = bulletList.get(i);
                    b.updatePosition();
                }
                bulletLastUpdateTime = System.currentTimeMillis();
            }

            if(time_now >= (lastMonsterBulletTime + 1500)){

                ArrayList<Monster> shooters = new ArrayList<>();

                for(int j = 0; j < MONSTER_WIDTH_COUNT; j++){
                    for(int i = MONSTER_HEIGHT_COUNT-1; i >= 0; i--){
                        Monster m = monsterArray[i][j];
                        if(m.isAlive()){
                            shooters.add(m);
                            break;
                        }

                    }
                }

                if(shooters.size() > 0){

                    Random random = new Random();
                    Monster m = shooters.get(random.nextInt(shooters.size()));

                    Bullet b = new Bullet(m.getX(),m.getY(),1,false);

                    bulletList.add(b);
                }

                lastMonsterBulletTime = System.currentTimeMillis();
            }

            for(int i = 0; i < explosionsList.size(); i++){
                Explosion e = explosionsList.get(i);
                e.getAnimation().updateAnimation();
            }

            for(Iterator<Explosion>iter = explosionsList.listIterator(); iter.hasNext(); ){
                Explosion e = iter.next();
                if(e.getAnimation().hasEnded())
                    iter.remove();
            }


            for(Iterator<Bullet>iter = bulletList.listIterator(); iter.hasNext(); ){
                Bullet b = iter.next();
                if(b.isOutOfScreen()){
                    iter.remove();
                }
            }

            checkCollisions();
        
        }
    }
    
    /**
     * Metoda sprawdza kolizje.
     * Na początku sprawdzana jest kolizja pomiędzy potworem a rakietą,
     * ponieważ potwory cały czas schodzą w dół i może zdarzyć się tak, 
     * że rakieta nie zdąży zabić ich wszystkich. Wtedy zostaje stworzona
     * animacja wybuchu i gra się kończy poprzez wywołanie funkcji GameOver().
     * Następnie sprawdzana jest kolizja pomiędzy pociskiem rakiety a potworem,
     * za którą zwiększane są punkty gracza.
     * Potem sprawdzane są kolizje dla pocisków potworów i rakiety, za które 
     * odejmowane są życia rakiecie.
     * Na końcu sprawdzane jest, który potwór żyje, a który nie, 
     * nieżyjące znikają.
     * Jeżeli wszystkie są martwe - gra kończy się.
     */
    private void checkCollisions(){
        
        LinkedList<Bullet> bulletsToDelete = new LinkedList<>();
        
        for(int i=0; i<MONSTER_HEIGHT_COUNT; i++){
            for(int j = 0; j < MONSTER_WIDTH_COUNT; j++){
                Monster m = monsterArray[i][j];
                if(m.getBody().intersects(player.getBody()))
                {
                    Explosion ex = new Explosion(player.getX()-20,player.getY()-20);
                    explosionsList.add(ex);
                    gameOver();
                    break;
                }
                for(int p = 0; p < bulletList.size(); p++){
                    Bullet b = bulletList.get(p);
                    if(b.IsPlayer() && m.isAlive())
                    {
                        if(m.getBody().intersects(b.getBody())){
                            bulletsToDelete.add(b);
                            m.kill();
                            Explosion ex = new Explosion(m.getX()-20,m.getY()-20);
                            explosionsList.add(ex);
                            score++;
                        }
                    }
                    else if(!b.IsPlayer())
                    {
                        if(player.getBody().intersects(b.getBody())){
                            bulletsToDelete.add(b);
                            player.setHealth(player.getHealth()-1);
                            
                            Explosion ex = new Explosion(player.getX()-20,player.getY()-20);
                            explosionsList.add(ex);
                            if(player.getHealth() <= 0)
                                gameOver();
                        }
                    }
                }
                bulletList.removeAll(bulletsToDelete);
            }
        }
        
        boolean anyAlive = false;
        
        for(int i=0; i<MONSTER_HEIGHT_COUNT; i++){
            for(int j = 0; j < MONSTER_WIDTH_COUNT; j++){
                Monster m = monsterArray[i][j];
                if(m.isAlive()){
                    anyAlive = true;
                    break;
                }
                    
            }
        }
        
        if(!anyAlive)
            gameOver();
    }
    
    /**
     * Metoda kończy grę, przestawia flagę _gamemMode, dzięki temu wyświetla 
     * się menu, lista ekslopzji i pocisków zostaje wyczyszczona.
     */
    public void gameOver(){
        _gameMode = false;
        explosionsList.clear();
        bulletList.clear();
    }
    
    /**
     * Metoda odpowiada za rysowanie poszczególnych obiektów w panelu.
     * Na początku rysowane jest tło, następnie (jeżeli aplikajca ma ustawioną 
     * flagę _gameMode na false) menu, gdzie rysowane są odpowiednie łańcuchy 
     * tekstowe, jeżeli _gameMode ustawiony jest na true, to rysowane są punkty,
     * czas, ilość żyć, grafika rakiety oraz potwórów, pocisków, eksplozji.
     * @param g obiekt klasy Graphics
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawImage(background1.getImage(),background1.getX(), background1.getY(), null);
        g2d.drawImage(background2.getImage(),background2.getX(), background2.getY(), null);
        
        if(!_gameMode){
            g2d.setColor(Color.blue);
            g2d.setFont(new Font("Comic Sans MS",Font.BOLD,35));
            
            g2d.drawString("InvadersInSpace", SCREEN_WIDTH/2-140, SCREEN_HEIGHT/2-100);
            
            g2d.setColor(Color.white);
            g2d.setFont(new Font("Comic Sans MS",Font.BOLD,35));

            g2d.drawString("Press SPACE to play", SCREEN_WIDTH/2-175, SCREEN_HEIGHT/2);
            
            
            g2d.setFont(new Font("Comic Sans MS",Font.BOLD,25));
            g2d.drawString("Score: " + score, SCREEN_WIDTH/2-55, SCREEN_HEIGHT/2+50);
        }
        
        
        if(_gameMode){
            
            g2d.setColor(Color.white);
            g2d.setFont(new Font("Comic Sans MS",Font.BOLD,20));

            g2d.drawString("Score: " + score, SCREEN_WIDTH - 140, 20);
            g2d.drawString("Time: " + (System.currentTimeMillis()-gameStartTime)/1000, SCREEN_WIDTH - 250, 20);

            g2d.drawImage(player.getImage(),player.getX(), player.getY(),null);
            g2d.setColor(Color.red);
            if(_DEBUG)
                g2d.drawRect(player.getBody().x, player.getBody().y, player.getBody().width, player.getBody().height);



            for(int i = 0; i < MONSTER_HEIGHT_COUNT; i++){
                for(int j=0; j < MONSTER_WIDTH_COUNT; j++){
                    Monster m = monsterArray[i][j];
                    if(m.isAlive()){
                        g2d.drawImage(m.getImage(),m.getX(),m.getY(),null);
                        if(_DEBUG)
                            g2d.drawRect(m.getBody().x, m.getBody().y, m.getBody().width, m.getBody().height);
                    }
                }
            }

            for( int i = 0; i < bulletList.size(); i++){
                Bullet b = bulletList.get(i);
                g2d.drawImage(b.getImage(),b.getX(), b.getY(),null);
                if(_DEBUG)
                    g2d.drawRect(b.getBody().x, b.getBody().y, b.getBody().width, b.getBody().height);
            }

            for(int i = 0; i < explosionsList.size(); i++){
                Explosion e = explosionsList.get(i);
                g2d.drawImage(e.getAnimation().getImage(),e.getX(), e.getY(),null);
            }

            for(int i = player.getHealth()-1; i >= 0; i--){
                g2d.drawImage(hearts[i].getImage(),hearts[i].getX(), hearts[i].getY(),null);
            }
        }   
  
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Funkcja odpowiada za efekty naciśnięcia odpowiednich klawiszy na
     * klawiaturze, strzałki w lewo, strzałki w prawo (poruszanie się w lewo 
     * i prawo rakiety) oraz naciśnięcia spacji (w trakcie rozgrywki - strzelanie
     * pocisków przez rakietę, w menu - rozpoczęcie nowej gry).
     * @param e obiekt klasy KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                player.setDesiredX(player.getDesiredX() - player.getSpeed());
                break;
                
            case KeyEvent.VK_RIGHT:
                player.setDesiredX(player.getDesiredX() + player.getSpeed());
                break;
                
            case KeyEvent.VK_SPACE:
                if(_gameMode){
                    Bullet bullet = new Bullet(player.getX()+17,player.getY(),1,true);
                    bulletList.add(bullet);   
                }
                else{
                    _gameMode = true;
                    restart();
                }
                    
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
}
