package Controller;

import eg.edu.alexu.csd.oop.game.GameObject;

/**
 *
 * @author Nour Mohamed
 */
public interface Iterator {
    
    public GameObject leftArrayListLastElement();
    
    public GameObject rightArrayListLastElement();

    public boolean leftArrayListHasNext();

    public boolean rightArrayListHasNext();

    public GameObject leftArrayListNext();

    public GameObject rightArrayListNext();
    
    public GameObject leftArrayListNextNext();

    public GameObject rightArrayListNextNext();

}
