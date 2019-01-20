package Application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Klasa odpowiada za stworzenie okna aplikacji.
 * Dziedziczy z klasy JFrame.
 * @author Paulina Zapletal
 */
public class MainFrame extends JFrame {
    
    /**
     * Instancja klasy GamePanel.
     */
    public GamePanel gpanel;
    
    /**
     * Konstruktor ustawia wielkość okna, tworzy obiekt typu GamePanel, gdzie 
     * zaimplementowana jest mechanika gry, dołącza go do okna i gra jest
     * uruchamiana.
     */
    public MainFrame(){
        super("InvadersInSpace");
        
        setSize(600,600);
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        try {
            new Constants("Resources");
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        gpanel = new GamePanel();
        Constants.SCREEN_WIDTH = gpanel.getWidth();
        Constants.SCREEN_HEIGHT = gpanel.getHeight();
        
        
        add(gpanel);

        gpanel.requestFocusInWindow();
     
        gpanel.init();
    }
    
    public static void main(String[] args){
        new MainFrame();
    }
}
