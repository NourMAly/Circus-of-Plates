package Model;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nour Mohamed
 */
public final class RecycleFlyWeight {

    private ShapeFactory shapeFactory = new ShapeFactory();
    private static Map<String, BufferedImage> images = new HashMap<String, BufferedImage>();

    public BufferedImage getImage(String key) { //key is a number
        //key = getKeyName(key); //to make sure name of image matches the ones in resources
        if (!images.containsKey(key)) {
            String shapeType = getShapeName(key);
            Shape shape = shapeFactory.createShape(shapeType);
            BufferedImage image = shape.draw("shape" + key + ".png");
            images.put(key, image);
        }
        return images.get(key);
    }

    public String getShapeName(String key) {
        if (key.equals("1") || key.equals("2") || key.equals("3") || key.equals("8") || key.equals("9") || key.equals("12")) {
            return "Plate";
        }
        if (key.equals("4")) {
            return "Bomb";
        }
        if (key.equals("5") || key.equals("6") || key.equals("7") || key.equals("10") || key.equals("11") || key.equals("13")) {
            return "Rectangle";
        }
        //add other shapes
        return null;
    }

    public String getKeyName(String key) {
        if (key.equalsIgnoreCase("SHAPE1")) {
            return "shape1";
        } else if (key.equalsIgnoreCase("SHAPE2")) {
            return "shape2";
        } else if (key.equalsIgnoreCase("SHAPE3")) {
            return "shape3";
        } else if (key.equalsIgnoreCase("SHAPE4")) {
            return "shape4";
        } else if (key.equalsIgnoreCase("SHAPE5")) {
            return "shape5";
        } else if (key.equalsIgnoreCase("SHAPE6")) {
            return "shape6";
        } else if (key.equalsIgnoreCase("SHAPE7")) {
            return "shape7";
        } else if (key.equalsIgnoreCase("SHAPE8")) {
            return "shape8";
        } else if (key.equalsIgnoreCase("SHAPE9")) {
            return "shape9";
        } else if (key.equalsIgnoreCase("SHAPE10")) {
            return "shape10";
        } else if (key.equalsIgnoreCase("SHAPE11")) {
            return "shape11";
        } else if (key.equalsIgnoreCase("SHAPE12")) {
            return "shape12";
        } else if (key.equalsIgnoreCase("SHAPE13")) {
            return "shape13";
        }
        return null;
    }
}
