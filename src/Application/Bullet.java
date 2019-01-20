package Application;

import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Klasa dziedziczy z klasy Entity. Tworzy obiekty będące pociskami w aplikacji.
 * @author Paulina Zapletal
 */
public class Bullet extends Entity {
    
    /**
     * Flaga sprawdzająca czy pocisk należy do gracza - rakiety.
     */
    private boolean isPlayer;
    
    /**
     * W konstruktorze przypisywana jest odpowiednia grafika po ustaleniu czy
     * pocisk należy do gracza czy do potwora. Określane są współrzędne obiektu,
     * szybkość z jaką pocisk będzie się poruszać oraz określane jest jego ciało
     * potrzebne do kolizji.
     * @param x współrzędna x obiektu
     * @param y współrzędna y obiektu
     * @param speed szybkość obiektu
     * @param player prawda lub fałsz czy pocisk należy do gracza
     */
    public Bullet(int x, int y, int speed, boolean player){
        super(x,y,speed);
        
        isPlayer = player;
        
        if(player){
            image = Constants.bulletPlayer;
        }
        else{
            image = Constants.bulletMonster;
        }
            
        width = image.getWidth();
        height = image.getHeight();
        
        body = new Rectangle();
        body.setBounds(x, y, width, height);
    }
    
    /**
     * Metoda odpowiada za aktualizację pozycji pocisku w zależności od tego czy
     * należy on do gracza czy do potwora.
     */
    public void updatePosition(){
        if(isPlayer)
            y-=10;
        else
            y+=7;
        
        super.update();
    }
    
    /**
     * Metoda zwraca prawdę w przypadku gdy pocisk został wystrzelony przez
     * rakietę.
     * @return prawda lub fałsz
     */
    public boolean IsPlayer(){
        return isPlayer;
    }
   
}
