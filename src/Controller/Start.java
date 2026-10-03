package Controller;

import eg.edu.alexu.csd.oop.game.GameEngine;
import java.awt.Color;

/**
 *
 * @author Nour Mohamed
 */
public class Start {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game=new Game(1014,720);
        GameEngine.start("circus of plates", game, Color.darkGray);
    }
    
}
