package Application;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Klasa stanowi pewnego rodzaju bazę dla wybranych innych klas, które 
 * po niej dziedziczą.
 * Tutaj zadeklarowane są zmienne odpowiadające za pozycję obiektów, zmienne, 
 * do których przypisywane są odpowiednie cechy jak np. wielkość, szybkość 
 * poruszania się i jaką grafiką są reprezentowane.
 * @author Paulina Zapletal
 */
public class Entity {
    
    /**
     * Współrzędna x obiektu.
     */
    protected int x;
    
    /**
     * Współrzędna y obiektu.
     */
    protected int y;
    
    /**
     * Szerokość obiektu.
     */
    protected int width;
    
    /**
     * Wysokość obiektu.
     */
    protected int height;
    
    /**
     * Szybkość z jaką porusza się obiekt.
     */
    protected int speed;
    
    /**
     * Oczekiwana pozycja x - ta do której dąży obiekt przy każdym 
     * odświeżeniu pozycji.
     */
    protected int desiredX;
    
    /**
     * Oczekiwana pozycja y - ta do której dąży obiekt przy każdym 
     * odświeżeniu pozycji.
     */
    protected int desiredY;
    
    /**
     * Zmienna przechowująca grafikę.
     */
    protected BufferedImage image;
    
    /**
     * Obiekt odpowiadający za animację eksplozji.
     */
    protected Animation animation;
    
    /**
     * Zmienna przechowująca ciało obiektu (potrzebne do kolizji).
     */
    protected Rectangle body;
    
    
    /**
     * Konstruktor klasy przypisuje zmiennym odpowiednie wartości przekazane
     * w parametrach.
     * @param x współrzędna x obiektu
     * @param y współrzędna y obiektu
     * @param speed szybkość z jaką obiekt się porusza
     */
    public Entity(int x, int y, int speed){
        this.x = desiredX = x;
        this.y = desiredY = y;
        this.speed = speed;
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
    
    /**
     * Metoda przypisuje zmiennej x wartość z paramteru.
     * @param x współrzędna x obiektu
     */
    public void setX(int x){
        this.x = x;
    }
    
    /**
     * Metoda przypisuje zmiennej y wartość z parametru.
     * @param y współrzędna y obiektu
     */
    public void setY(int y){
        this.y = y;
    }
    
    /**
     * Metoda zwraca szybkość z jaką porusza się obiekt.
     * @return szybkość obiektu
     */
    public int getSpeed(){
        return speed;
    }
    
    /**
     * Metoda przypisuje oczekiwaną wartość współrzędnej x.
     * @param x wpółrzędna x obiektu
     */
    public void setDesiredX(int x){
        desiredX = x;
    }
    
    /**
     * Metoda przypisuje oczekiwaną wartość współrzędnej y.
     * @param y współrzędna y obiektu
     */
    public void setDesiredY(int y){
        desiredY = y;
    }
    
    /**
     * Metoda zwraca oczekiwaną wartość współrzędnej x.
     * @return oczekiwana wartość współrzędnej x
     */
    public int getDesiredX(){
        return desiredX;
    }
    
    /**
     * Metoda zwraca oczekiwaną wartość współrzędnej y.
     * @return oczekiwana wartość współrzędnej y
     */
    public int getDesiredY(){
        return desiredY;
    }
    
    /**
     * Metoda zwraca grafikę.
     * @return grafika
     */
    public BufferedImage getImage(){
        return image;
    }
    
    /**
     * Metoda sprawdza czy obiekt nie znalazł się poza ramką, w której
     * działa aplikacja.
     * @return prawda lub fałsz
     */
    public boolean isOutOfScreen(){
        return x<-width || x>Constants.SCREEN_WIDTH 
                || y<-height || y>Constants.SCREEN_HEIGHT;                        
    }
    
    /**
     * Metoda zwraca ciało obiektu wykorzystywane przy kolizjach.
     * @return ciało obiektu
     */
    public Rectangle getBody(){
        return body;
    }
    
    /**
     * Metoda aktualizuje pozycję ciała obiektu, jego współrzędną x i y.
     */
    public void update(){
        body.x = x;
        body.y = y;
    }
    
    /**
     * Metoda zwraca obiekt odpowiedzialny za animację eksplozji.
     * @return animacja eksplozji
     */
    public Animation getAnimation(){
        return animation;
    }
    
}
