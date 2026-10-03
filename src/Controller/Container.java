package Controller;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.ArrayList;

/**
 *
 * @author Nour Mohamed
 */
public class Container {
    private ArrayList<GameObject> leftArrayList=new ArrayList<>();
    private ArrayList<GameObject> rightArrayList=new ArrayList<>();
    
    public ArrayList<GameObject> getLeftArrayList(){
        return leftArrayList;
    }
    public ArrayList<GameObject> getRightArrayList(){
        return rightArrayList;
    }
}
