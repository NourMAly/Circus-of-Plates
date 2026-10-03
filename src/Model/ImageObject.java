package Model;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.image.BufferedImage;

/**
 *
 * @author Nour Mohamed
 */
public class ImageObject implements GameObject {

    private static final int MAX = 1;
    private static RecycleFlyWeight flyWeight = new RecycleFlyWeight();

    private BufferedImage[] spriteImages;
    private int x;
    private int y;
    private boolean visible;
    private String name;
    private boolean horizontial;
    private boolean vertical;
    private ClownObject clown;
    private String listName;

    public ImageObject(int x, int y, String imageName, ClownObject clown) {
        this.x = x;
        this.name = imageName;
        this.y = y;
        this.spriteImages = new BufferedImage[MAX];
        this.visible = true;
        spriteImages[0] = flyWeight.getImage(imageName);
        this.clown = clown;
        spriteImages[0] = flyWeight.getImage(imageName);
    }

    public void setVertical(boolean vertical, String listName) {
        this.vertical = vertical;
        this.listName = listName;
    }

    public void setHorizontial(boolean horizontial) {
        this.horizontial = horizontial;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        if (vertical) {
            if (listName.equalsIgnoreCase("RIGHT")) {
                this.x = clown.getX() + 150;
            }
            if (listName.equalsIgnoreCase("LEFT")) {
                this.x = clown.getX()+16;
            }
        } else {
            this.x = x;
        }
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        if (horizontial) {
        } else {
            this.y = y;
        }
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

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

}
