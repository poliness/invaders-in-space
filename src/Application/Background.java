package Application;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Klasa Background tworzy tło w aplikacji.
 * @author Paulina Zapletal
 */
public class Background {
    
    /**
     * Współrzędna x.
     */
    private int x;
    
    /**
     * Współrzędna y.
     */
    private int y;
    
    /**
     * Szerokość.
     */
    private int width;
    
    /**
     * Wysokość.
     */
    private int height;
    
    /**
     * Zmienna przechowująca grafikę.
     */
    private BufferedImage image;
    
    /**
     * Tło w aplikacji jest obiektem na określonej pozycji.
     * Konstruktor przyjmuje dwa parametry, będące współrzędnymi obiektu.
     * Tutaj ładowana jest odpowiednia grafika z pliku będąca imitacją
     * przestrzeni kosmicznej.
     * @param x współrzędna x obiektu
     * @param y współrzędna y obiektu
     */
    public Background(int x, int y){
        this.x = x;
        this.y = y;
        try {
            image = ImageIO.read(ClassLoader.getSystemResource("Resources/space.jpg"));
        } catch (IOException ex) {
           System.out.println("Nie udalo sie wczytac obrazka");
        }
        
        width = image.getWidth();
        height = image.getHeight();
    }
    
    /**
     * Metoda aktualizuje pozycję obiektu.
     */
    public void update(){
        if(y <= -width)
            y = width;
        
        y--;
    }
    
    /**
     * Metoda zwraca wczytaną grafikę.
     * @return grafika
     */
    public BufferedImage getImage(){
        return image;
    }
    
    /**
     * Metoda zwraca współrzędną x.
     * @return współrzędna x obiektu
     */
    public int getX(){
        return x;
    }
    
    /**
     * Metoda zwraca współrzędną y.
     * @return współrzędna y obiektu
     */
    public int getY(){
        return y;
    }
}
