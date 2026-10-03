package Model;

/**
 *
 * @author Nour Mohamed
 */
public class ShapeFactory implements Factory {

    @Override
    public Shape createShape(String name) {
        if(name.equalsIgnoreCase("PLATE"))
        {
            Plate plate=new Plate();
            return plate;
        }
        if(name.equalsIgnoreCase("BOMB"))
        {
            Bomb bomb=new Bomb();
            return bomb;
        }
        if(name.equalsIgnoreCase("RECTANGLE"))
        {
            Rectangle rectangle=new Rectangle();
            return rectangle;
        }
        return null;
    }
}
