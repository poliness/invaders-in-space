package Application;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * Klasa Moster dziedziczy z klasy Entity.
 * Tworzy obiekty będące potworami w aplikacji.
 * @author Paulina Zapletal
 */
public class Monster extends Entity {
    
    /**
     * Zmienna określająca czy potwór żyje.
     */
    private boolean isAlive = true;
    
    /**
     * Zmienna określająca o ile kroków będą się przesuwać potwory, odpowiednio
     * w lewo i w prawo przy schodzeniu w dół.
     */
    private int step = 3;
    
    /**
     * Zmienna strująca poruszaniem się w lewo lub w prawo przy
     * schodzeniu w dół.
     */
    private boolean increasing = false;
    
    /**
     * Konstruktor tworzy obiekt typu Monster.
     * Tutaj przypisywane są odpowiednie grafiki w sposób losowy.
     * Zmienne width i height pobierają odpowiednio szerokość i wysokość 
     * wczytanej grafiki.
     * Są one nizbędne do określenia ciała obiektu wykorzystywanego w kolizjach 
     * obiektów.
     * @param x współrzędna x 
     * @param y współrzędna y 
     * @param speed szybkość z jaką potwór się porusza
     */
    public Monster(int x, int y, int speed){
        super(x,y,speed);
        
        image = Constants.monstersImages[new Random().nextInt(Constants.monstersImages.length)];
        
        width = image.getWidth();
        height = image.getHeight();
        
        body = new Rectangle();
        body.setBounds(x, y, width, height);
    }
    
    /**
     * Metoda aktualizuje pozycję potwora.
     * W zależności od flagi increasing oraz zmiennej step potwory przy 
     * schodzeniu w dół poruszają się po skosie, 6 kroków w lewo, 
     * następnie 6 kroków w prawo.
     */
    public void updatePosition(){
        if(increasing)
            step++;
        else
            step--;
        if(step == 0)
            increasing = true;
        else if(step == 6)
            increasing = false;
        
        if(increasing)
            x--;
        else
            x++;
        y++;
        super.update();
    }
    
    /**
     * Metoda zostaje wywołana w momencie zestrzelenia potwora, 
     * odpowiada za jego zabicie, czyli przestawianie flagi isAlive na false.
     */
    public void kill(){
        isAlive = false;
    }
    
    /**
     * Metoda sprawdza czy potwór żyje.
     * @return prawda lub fałsz
     */
    public boolean isAlive(){
        return isAlive;
    }  
}
