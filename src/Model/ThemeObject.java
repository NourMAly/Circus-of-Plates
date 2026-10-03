package Model;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Nour Mohamed
 */
public class ThemeObject implements GameObject{

    private static final int MAX = 1;
    private BufferedImage[] spriteImages;
    private int x;
    private int y;
    private boolean visible;
    
    public ThemeObject(int x, int y, String imageName) {
        this.x = x;
        this.y = y;
        this.spriteImages = new BufferedImage[MAX];
        this.visible = true;
        spriteImages[0]=drawTheme(imageName);
    }
    
    public BufferedImage drawTheme(String imageName) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(imageName));
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
       
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
    }

    @Override
    public int getWidth() {
        return spriteImages[0].getWidth();
    }

    @Override
    public int getHeight() {
        return spriteImages[0].getHeight();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }
    
}
