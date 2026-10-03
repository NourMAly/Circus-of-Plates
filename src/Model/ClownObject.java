package Model;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Nour Mohamed
 */
public class ClownObject implements GameObject {

    private static final int MAX = 1;
    private BufferedImage[] clown= new BufferedImage[MAX];
    private static int x=1014/ 3;
    private static int y=(int) (720*0.57);
    private boolean visible;
    private static ClownObject clownObject=null;
    
    private ClownObject() {
        this.clown = draw("clown.png");
        this.visible = true;
    }
    public static ClownObject getInstance(){
        if(clownObject==null)
        {
            return clownObject=new ClownObject();
        }
        else
        {
            return clownObject;
        }
    }

    public BufferedImage[] draw(String imageName) {
        BufferedImage[] image = new BufferedImage[1];
        try {
            image[0] = ImageIO.read(getClass().getClassLoader().getResourceAsStream(imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        //clown doesnot move up or down
    }

    @Override
    public int getWidth() {
        return clown[0].getWidth();
    }

    @Override
    public int getHeight() {
        return clown[0].getHeight();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return clown;
    }

}
