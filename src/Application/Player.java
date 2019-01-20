 package Application;

import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Klasa tworzy obiekt gracza, który w aplikacji jest reprezentowany
 * jako rakieta. 
 * Klasa dziedziczy z klasy Entity.
 * @author Paulina Zapletal
 */
public class Player extends Entity {
    
    /**
     * Liczba żyć gracza.
     */
    private int health = 5;
     
    /**
     * Konstruktor tworzy obiekt - rakietę.
     * Tutaj przypisywana jest odpowiednia grafika.
     * Zmienne width i height pobierają odpowiednio szerokość i wysokość 
     * wczytanej grafiki.
     * Są one nizbędne do określenia ciała obiektu potrzebnego do kolizji.
     * @param x pozycja x rakiety
     * @param y pozycja y rakiety
     * @param speed szybkość z jaką porusza się rakieta
     */
    public Player(int x, int y, int speed){
        super(x,y,speed);
         
        image = Constants.player;
        
        width = image.getWidth();
        height = image.getHeight();
        
        body = new Rectangle();
        body.setBounds(x, y, width, height);   
    }
    
    /**
     * Metoda zwraca liczbę żyć rakiety.
     * @return ilość żyć rakiety
     */
    public int getHealth(){
        return health;
    }
    
    /**
     * Metoda przypisuje wartość podaną w argumencie do zmiennej 
     * określającej ilość żyć rakiety.
     * @param health ilość żyć rakiety
     */
    public void setHealth(int health){
        
        this.health = health;
    }
   
    /**
     * Metoda kontrolująca czy obiekt przy poruszaniu się nie przekracza
     * granic okna aplikacji.
     * @param x współrzędna x rakiety
     */
    @Override
    public void setDesiredX(int x){
        desiredX = x;
        if(desiredX < 0)
            desiredX = 0;
        if(desiredX + width > Constants.SCREEN_WIDTH)
            desiredX = Constants.SCREEN_WIDTH - width;
    }
    
    /**
     * Metoda określa oczekiwaną wartość współrzednej y.
     * @param y współrzędna y rakiety
     */
    @Override
    public void setDesiredY(int y){
        desiredY = y;
    }
    
    /**
     * Metoda aktualizująca pozycję gracza - rakiety.
     * Dzięki niej obiekt porusza się płynnie w lewo i w prawo.
     */
    public void updatePosition(){
        
        double xDistance = desiredX - this.x;
        double yDistance = desiredY - this.y;

        double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
   
        if (distance > 1) {
            this.x += xDistance * 0.05;
            this.y += yDistance * 0.05;
        }
        else{
            this.x = Math.round(this.desiredX);
            this.y = Math.round(this.desiredY);
        }
   
        super.update();
    }
}
