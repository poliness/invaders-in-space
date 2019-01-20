package Application;

/**
 * Oddzielny wątek służący do odświeżania panelu gry/menu.
 * @author Paulina Zapletal
 */
public class GameThread extends Thread {
    
    /**
     * Instancja GamePanel.
     */
    GamePanel gamePanel;
    
    /**
     * Zmienna decydująca czy przerwać działanie wątku.
     */
    volatile boolean gameRunning = true;
    
    /**
     * Konstruktor klasy GameThread
     * GamePanel, który wątek będzie aktualizował.
     * @param gameP obiekt klasy GamePanel
     */
    public GameThread(GamePanel gameP){
        gamePanel = gameP;
        start();
    }
    
    /**
     * Właściwa metoda wątku, odświeżająca co ~17ms.
     */
    @Override
    public void run(){
        while(gameRunning){
            gamePanel.update();
            try {
                sleep(17);
            } catch (InterruptedException ex) {

            }
        }
    }
    
    /**
     * Metoda zatrzymująca działanie wątku.
     */
    public void pleaseStop(){
        gameRunning = false;
    }
    
}
