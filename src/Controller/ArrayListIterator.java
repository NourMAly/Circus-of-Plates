package Controller;

import eg.edu.alexu.csd.oop.game.GameObject;

/**
 *
 * @author Nour Mohamed
 */
public class ArrayListIterator implements Iterator{

    Container container = new Container();
    
    @Override
    public boolean leftArrayListHasNext() {
        return (!container.getLeftArrayList().isEmpty());
    }

    @Override
    public boolean rightArrayListHasNext() {
        return (!container.getRightArrayList().isEmpty());

    }

    @Override
    public GameObject leftArrayListNext() {
        return (container.getLeftArrayList().get(container.getLeftArrayList().size() - 2));
    }

    @Override
    public GameObject rightArrayListNext() {
        return (container.getRightArrayList().get(container.getRightArrayList().size() - 2));
    }

    @Override
    public GameObject leftArrayListNextNext() {
        return (container.getLeftArrayList().get(container.getLeftArrayList().size() - 3));
    }

    @Override
    public GameObject rightArrayListNextNext() {
        return (container.getRightArrayList().get(container.getRightArrayList().size() - 3));
    }

    @Override
    public GameObject leftArrayListLastElement() {
        return(container.getLeftArrayList().get(container.getLeftArrayList().size() -1));
    }

    @Override
    public GameObject rightArrayListLastElement() {
        return(container.getRightArrayList().get(container.getRightArrayList().size() -1));
    }
    
}
