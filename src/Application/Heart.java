package Application;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Klasa  tworzy obiekty - serca, stanowiące liczbę żyć rakiety.
 * @author Paulina Zapletal
 */
public class Heart {
    
    /**
     * Współrzędna x.
     */
    private int x;
    
    /**
     * Współrzędna y.
     */
    private int y;
    
    /**
     * Zmienna przechowująca grafikę obiektu.
     */
    private Image heart;
    
    /**
     * Konstruktor tworzy obiekt, określa jego współrzędne i przypisuje
     * odpowiednią grafikę.
     * @param x współrzędna x obiektu
     * @param y współrzędna y obiektu
     */
    public Heart(int x, int y){
        this.x = x;
        this.y = y;
        heart = Constants.heart;
         
    }
    
    /**
     * Metoda zwraca współrzędną x obiektu.
     * @return współrzędna x
     */
    public int getX(){
        return x;
    }
    
    /**
     * Metoda zwraca współrzędną y obiektu.
     * @return współrzędna y
     */
    public int getY(){
        return y;
    }
    
    /**
     * Metoda zwraca grafikę obiektu.
     * @return grafika
     */
    public Image getImage(){
        return heart;
    }
      
}
