package Application;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Klasa przechowuje zmienne używane w całym programie.
 * @author Paulina Zapletal
 */
public class Constants {
    
    /**
     * Wysokość i szerokość okna aplikacji.
     */
    public static int SCREEN_HEIGHT, SCREEN_WIDTH;
    
    /**
     * Zmienna przechowująca grafikę.
     */
    public static BufferedImage heart, player, bulletPlayer, bulletMonster, monster, explosion;
    
    /**
     * Tablica przechowująca klatki wybuchu.
     */
    public static BufferedImage[] blastImages = new BufferedImage[81];
    
    /**
     * Tablica przechowująca grafiki potworów.
     */
    public static BufferedImage[] monstersImages = new BufferedImage[9];
    
    /**
     * Szerokość tablicy przechowującej potwory.
     */
    public static final int MONSTER_WIDTH_COUNT = 13;
    
    /**
     * Wysokość tablicy przechowującej potwory.
     */
    public static final int MONSTER_HEIGHT_COUNT = 6;
    
    /**
     * Zmienna do debugowania.
     */
    public static final boolean _DEBUG = false;
    
    /**
     * W konstruktorze wczytywane są odpowiednie grafiki z folderu do poszczególnych
     * zmiennych. Grafika wybuchu i grafika potworów jest cięta na mniejsze.
     * @param path ścieżka do folderu
     * @throws IOException rzuca wyjątek wejścia/wyjścia
     */
    public Constants(String path) throws IOException{
        
            heart = ImageIO.read(ClassLoader.getSystemResource(path + "/Heart.png"));
            explosion = ImageIO.read(ClassLoader.getSystemResource(path + "/blast.png"));
            bulletPlayer = ImageIO.read(ClassLoader.getSystemResource(path + "/playerbullet.png"));
            bulletMonster = ImageIO.read(ClassLoader.getSystemResource(path + "/monsterbullet.png"));
            player = ImageIO.read(ClassLoader.getSystemResource(path + "/spaceship.png"));
            monster = ImageIO.read(ClassLoader.getSystemResource(path + "/monsters.png"));
        
        int width = explosion.getWidth()/9;
        int height = explosion.getHeight()/9;
        
        int p = 0;
        for(int i=0;i<9;i++){
            for(int k=0;k<9;k++){
                blastImages[p++] = explosion.getSubimage(k*width, i*height, width, height);
            }
        }
        
        width = monster.getWidth()/3;
        height = monster.getHeight()/3;
        
        p=0;
        for(int i=0;i<3;i++){
            for(int k=0;k<3;k++){
                monstersImages[p++] = monster.getSubimage(k*width, i*height, width, height);
            }
        }
        
        System.out.println("Pomyślnie wczytano obrazki");
    }
    
}
