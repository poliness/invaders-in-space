package Application;

import java.awt.Image;

/**
 * Klasa odpowiada za animację eksplozji, która pokazuje się
 * w momencie zestrzelenia potwora przez rakietę bądź trafienia 
 * pociskiem w rakietę wyrzuconym przez potwora.
 * @author Paulina Zapletal
 */
public class Animation {
    
    /**
     * Tablica przechowująca grafiki - kolejne klatki wybuchu.
     */
    private Image[] frames;
    
    /**
     * Indeks aktualnej grafiki - klatki.
     */
    private int currentFrame;
    
    /**
     * Czas rozpoczęcia aktualnej animacji.
     */
    private long startTime;
    
    /**
     * Czas przejścia pomiędzy kolejnymi klatkami.
     */
    private long delay;
    
    /**
     * Flaga sprawdzająca czy funkcja przeszła przez wszystkie klatki animacji 
     * dokładnie raz.
     */
    private boolean playedOnce;
    
    /**
     * Flaga sprawdzająca czy funkcja przeszła przez wszystkie klatki animacji. 
     */
    private boolean hasEnded = false;
    
    /**
     * Metoda przypisuje tablicę obiektów typu Image przekazaną w parametrze.
     * Ustawia indeks na 0, czyli na pierwszy element tablicy.
     * Do zmiennej startTime przypisuje czas rozpoczęcia bieżącej animacji.
     * @param frames tablica przechowująca obiekty typu Image 
     */
    public void setFrames(Image[] frames)
    {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
    }
    
    /**
     * Metoda ustawia czas przejścia pomiędzy kolejnymi klatkami.
     * @param d czas przejścia pomiędzy kolejnymi klatkami
     */
    public void setDelay(long d){
        delay = d;
    }
    
    /**
     * Metoda ustawia indeks aktualnie wskazywanej klatki.
     * @param i indeks klatki
     */
    public void setFrame(int i){
        currentFrame = i;
    }
    
    /**
     * Metoda aktualizuje animację.
     * Przechodzi przez wszystkie klatki tworząc efekt wybuchu.
     */
    public void updateAnimation()
    {
        if(currentFrame == 0) 
            hasEnded = false;
        long elapsed = (System.nanoTime()-startTime)/1000000;
       
        if(elapsed>delay)
        {
            currentFrame++;
            startTime = System.nanoTime();
        }
       
        if(currentFrame == frames.length){
            currentFrame = 0;
            playedOnce = true;
            hasEnded = true;
        }
    }
    
    /**
     * Metoda zwraca pojedynczą klatkę.
     * @return aktualnie wskazywana klatka
     */
    public Image getImage(){
        if(currentFrame == frames.length) 
            currentFrame--;
        return frames[currentFrame];
    }
    
    /**
     * Metoda zwraca indeks akutalnie wskazywanej klatki.
     * @return indeks aktualnie wskazywanej klatki
     */
    public int getFrame(){
        return currentFrame;
    }
    
    /**
     * Metoda zwraca prawdę lub fałsz w przypadku przejścia przez wszystkie
     * klatki dokładnie raz.
     * @return prawda lub fałsz
     */
    public boolean playedOnce(){
        return playedOnce;
    }
    
    /**
     * Metoda zwraca prawdę lub fałsz w przypadku zakończenia przejścia przez
     * wszystkie klatki.
     * @return prawda lub fałsz
     */
    public boolean hasEnded(){
        return hasEnded;
    }
    
}