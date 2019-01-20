package Application;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Klasa dziedziczy z klasy Entity.
 * Tworzy eksplozję w momencie uderzenia pocisku w rakietę bądź potwora.
 * @author Paulina Zapletal
 */
public class Explosion extends Entity {
    
    /**
     * Tablica przechowująca grafiki wybuchu.
     */
   BufferedImage[] blastImages;
    
   /**
    * Konstruktor przyjmuje 2 parametry określające położenie obiektu.
    * Do tablicy przechowującej grafiki wybuchu zostają wczytane odpowiednie
    * obrazki.
    * Następnie zostaje stworzony obiekt klasy Animation, który odpowiada
    * za animację eksplozji. 
    * @param x współrzędna x obiektu
    * @param y współrzędna y obiektu
    */
    public Explosion(int x, int y){
        super(x,y,0);
        
        blastImages = Constants.blastImages;
        
        animation = new Animation();
        animation.setFrames(blastImages);
        animation.setDelay(20);
    }
}
