package Controller;

import Model.ClownObject;
import Model.ImageObject;
import Model.ThemeObject;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import frontend.DiffLevelWindow;
import frontend.StartWindow;
import frontend.Theme1;
import frontend.Theme2;
import frontend.Theme3;
import frontend.ThemesWindow;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Nour Mohamed
 */
public class Game implements World {

    private StartWindow startWindow;
    private static int leftCounter = 0;
    private static int rightCounter = 0;
    private ArrayListIterator arrayListIterator = new ArrayListIterator();
    private ThemesWindow themeWindow = null;
    private DiffLevelWindow diffLevelWindow = null;
    private int theme;
    private int diffLevel;
    private ArrayList<GameObject> leftList = new ArrayList<>();
    private ArrayList<GameObject> rightList = new ArrayList<>();
    private final List<GameObject> constant;
    private final List<GameObject> moving;
    private final List<GameObject> control;
    private long startTime;
    private int score;
    private int width;
    private int height;
    private static int MAX_TIME = 10 * 60 * 1000;
    private static int DEFAULT_SEPEED = 10;
    private static int DEFAULT_CONTROL_SEPEED = 20;
    private static int NUM_OF_MOVING_OBJ = 10; //edit these variables for each level diffuculty and refresh has same logic and code

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        this.startTime = System.currentTimeMillis();
        this.score = 0;
        this.constant = new LinkedList<>();
        this.moving = new LinkedList<>();
        this.control = new LinkedList<>();
        setGUI();
        //setTheme();
        createGameObject();

    }

    public StartWindow getStartWindow() {
        return startWindow;
    }

    public void setGUI() {
        startWindow = new StartWindow(width, height);
        startWindow.setVisible(true);
        /*if (diffLevelWindow.isVisible()==true) {
            themeWindow = startWindow.getThemeWindow();
            theme = themeWindow.getTheme();
        }
        /*if (diffLevelWindow.isVisible()==false) {
            diffLevelWindow = themeWindow.getDiffLevelWindow();
            diffLevel = diffLevelWindow.getDiffcultyLevel();
        }*/
    }

    public void setTheme() {
        switch (theme) {
            case 1 -> {
                Theme1 theme1 = new Theme1();
                this.width = theme1.getWidth();
                this.height = theme1.getHeight();
                theme1.setVisible(true);
                //add ifs for levels diff
            }
            case 2 -> {
                Theme2 theme2 = new Theme2();
                this.width = theme2.getWidth();
                this.height = theme2.getHeight();
                theme2.setVisible(true);
                //add ifs for levels diff
            }
            case 3 -> {
                Theme3 theme3 = new Theme3();
                this.width = theme3.getWidth();
                this.height = theme3.getHeight();
                theme3.setVisible(true);
                //add ifs for levels diff
            }
            default -> {
            }
        }
    }

    private void createGameObject() {
        constant.add(new ThemeObject(0, 0, "theme1.png"));
        ClownObject clown = ClownObject.getInstance();
        control.add(clown);
        for (int i = 0; i < NUM_OF_MOVING_OBJ; i++) {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height / 2);
            int imgNum = (int) (Math.random() * 7+ 1);
            String imagePath = String.valueOf(imgNum);
            moving.add(new ImageObject(x, y, imagePath, clown));
        }
        //edit the bomb picture
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return this.constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return this.moving;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return this.control;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    private boolean intersectLeft(GameObject o1, GameObject o2) {
        int clownY = 0;// = o2.getY();
        if(leftCounter==0)
        {
            clownY = o2.getY();
        }
        else if(leftCounter>0)
        {
            clownY = o2.getY();
            clownY-=(leftCounter*25);
        }
        int plateStart = o1.getX(), plateEnd = o1.getX() + o1.getWidth();
        return ((o1.getY() == clownY) && ((plateEnd >= o2.getX()) && (plateStart <= (o2.getX() - 60 + o2.getWidth() / 2))));
    }

    private boolean intersectRight(GameObject o1, GameObject o2) {
        //int clownY = o2.getY();
        int clownY = 0;// = o2.getY();
        if(rightCounter==0)
        {
            clownY = o2.getY();
        }
        else if(rightCounter>0)
        {
            clownY = o2.getY();
            clownY-=(rightCounter*25);
        }
        int plateStart = o1.getX(), plateEnd = o1.getX() + o1.getWidth();
        return ((o1.getY() == clownY) && ((plateEnd >= o2.getX() + 190) && (plateStart <= (o2.getX() + o2.getWidth() - 40))));
    }
    
    @Override
    public boolean refresh() {
        boolean intersectLeft = false, intersectRight = false;
        ArrayList rightArrayList = arrayListIterator.container.getRightArrayList();
        ArrayList leftArrayList = arrayListIterator.container.getLeftArrayList();
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
        ClownObject clown = (ClownObject) control.get(0);
        for (int i = 0; i < moving.size(); i++) {
            GameObject o = moving.get(i);
            o.setY((o.getY() + 1));    //move Down
            if (o.getY() == getHeight()) {
                o.setY(-1 * (int) (Math.random() * getHeight()));
                o.setX((int) (Math.random() * getWidth()));
            }
            //o.setX(o.getX() + (Math.random() > 0.5 ? 2 : -2));  //vibration
            if (!timeout) {
                //if (counter == 0) {
                    intersectLeft = intersectLeft(o, clown);
                    
                    intersectRight = intersectRight(o, clown);
                /*} else {
                    intersectLeft = intersectLeftWithPlates(o, clown);
                    intersectRight = intersectRightWithPlates(o, clown);
                }*/
            }
            if (intersectLeft) {
                leftCounter++;
                arrayListIterator.container.getLeftArrayList().add(o);
                ImageObject clonedPlate = (ImageObject) moving.get(moving.indexOf(o));
                ImageObject clonedObj = new ImageObject(o.getWidth(), o.getHeight(), clonedPlate.getName(), clown);
                moving.remove(o);
                moving.add(clonedObj);
                control.add(o);
                leftList.add(o);
                ImageObject obj = (ImageObject) o;
                obj.setHorizontial(true);
                obj.setVertical(true, "left");
                GameObject object = moving.get(moving.size() - 1);
                object.setY(-1 * (int) (Math.random() * getHeight()));
                object.setX((int) (Math.random() * getWidth()));
            }
            if (intersectRight) {
                rightCounter++;
                arrayListIterator.container.getRightArrayList().add(o);
                ImageObject clonedPlate = (ImageObject) moving.get(moving.indexOf(o));
                ImageObject clonedObj = new ImageObject(o.getWidth(), o.getHeight(), clonedPlate.getName(), clown);
                ImageObject obj = (ImageObject) o;
                obj.setHorizontial(true);
                obj.setVertical(true, "right");
                moving.remove(o);
                moving.add(clonedObj);
                control.add(o);
                rightList.add(o);
                GameObject object = moving.get(moving.size() - 1);
                object.setY(-1 * (int) (Math.random() * getHeight()));
                object.setX((int) (Math.random() * getWidth()));
            }
            if (rightArrayList.size() >= 3 && rightList.size() >= 3 && control.size() >= 4) {
                ImageObject lastElement = (ImageObject) arrayListIterator.rightArrayListLastElement();
                ImageObject next = (ImageObject) arrayListIterator.rightArrayListNext();
                ImageObject nextNext = (ImageObject) arrayListIterator.rightArrayListNextNext();
                if (isColorMatching(lastElement.getName(), next.getName()) && isColorMatching(nextNext.getName(), next.getName()) && isColorMatching(nextNext.getName(), lastElement.getName())) {
                    score += 30;
                    control.remove(rightList.get(rightList.size() - 1));
                    control.remove(rightList.get(rightList.size() - 2));
                    control.remove(rightList.get(rightList.size() - 3));
                    rightCounter-=3;
                    rightArrayList.remove(rightList.get(rightList.size() - 1));
                    rightArrayList.remove(rightList.get(rightList.size() - 2));
                    rightArrayList.remove(rightList.get(rightList.size() - 3));
                    rightList.remove(rightList.size() - 1);
                    rightList.remove(rightList.size() - 1);
                    rightList.remove(rightList.size() - 1);
                }
            }
            if (leftArrayList.size() >= 3 && leftList.size() >= 3 && control.size() >= 4) {
                ImageObject lastElement = (ImageObject) arrayListIterator.leftArrayListLastElement();
                ImageObject next = (ImageObject) arrayListIterator.leftArrayListNext();
                ImageObject nextNext = (ImageObject) arrayListIterator.leftArrayListNextNext();
                if (isColorMatching(lastElement.getName(), next.getName()) && isColorMatching(nextNext.getName(), next.getName()) && isColorMatching(nextNext.getName(), lastElement.getName())) {
                    score += 30;
                    control.remove(leftList.get(leftList.size() - 1));
                    control.remove(leftList.get(leftList.size() - 2));
                    control.remove(leftList.get(leftList.size() - 3));
                    leftCounter-=3;
                    leftArrayList.remove(leftList.get(leftList.size() - 1));
                    leftArrayList.remove(leftList.get(leftList.size() - 2));
                    leftArrayList.remove(leftList.get(leftList.size() - 3));
                    leftList.remove(leftList.size() - 1);
                    leftList.remove(leftList.size() - 1);
                    leftList.remove(leftList.size() - 1);
                }
            }
            if (leftArrayList.size() >= 1 && leftList.size() >= 1 && control.size() >= 2) {
                ImageObject lastElement = (ImageObject) arrayListIterator.leftArrayListLastElement(); //bomb
                if (lastElement.getName().equals("4")) {
                    if (score == 0) {
                        score = 0;
                    } else {
                        score -= 10;
                    }
                    control.remove(leftList.get(leftList.size() - 1)); //remove the bomb
                    leftCounter-=1;
                    if ((leftList.size() - 1)>0) {
                        control.remove(leftList.get(leftList.size() - 2));
                        leftCounter-=1;
                        leftArrayList.remove(leftList.get(leftList.size() - 1));
                        leftArrayList.remove(leftList.get(leftList.size() - 2));
                        leftList.remove(leftList.size() - 1);
                        leftList.remove(leftList.size() - 1);
                    }
                    else if ((leftList.size() - 1)==0) {
                        leftArrayList.remove(leftList.get(leftList.size() - 1));
                        leftList.remove(leftList.size() - 1);
                    }
                }
            }
            if (rightArrayList.size() >= 1 && rightList.size() >= 1 && control.size() >= 2) {
                ImageObject lastElement = (ImageObject) arrayListIterator.rightArrayListLastElement(); //bomb
                if (lastElement.getName().equals("4")) {
                    if (score == 0) {
                        score = 0;
                    } else {
                        score -= 10;
                    }
                    control.remove(rightList.get(rightList.size() - 1));
                    rightCounter-=1;
                    if ((rightList.size() - 1)>0) {
                        control.remove(rightList.get(rightList.size() - 2));
                        rightCounter-=1;
                        rightArrayList.remove(rightList.get(rightList.size() - 1));
                        rightArrayList.remove(rightList.get(rightList.size() - 2));
                        rightList.remove(rightList.size() - 1);
                        rightList.remove(rightList.size() - 1);
                    }
                    else if ((rightList.size() - 1)==0) {
                        rightArrayList.remove(rightList.get(rightList.size() - 1));
                        rightList.remove(rightList.size() - 1);
                    }
                }
            }
        }
        return !timeout;
    }

    public boolean isColorMatching(String n1, String n2) {
        if ((n1.equals("1") && n2.equals("1")) || (n1.equals("1") && n2.equals("5")) || (n1.equals("5") && n2.equals("1")) || (n1.equals("5") && n2.equals("5"))) {
            return true;
        }
        if ((n1.equals("2") && n2.equals("2")) || (n1.equals("2") && n2.equals("6")) || (n1.equals("6") && n2.equals("2")) || (n1.equals("6") && n2.equals("6"))) {
            return true;
        }
        if ((n1.equals("3") && n2.equals("3")) || (n1.equals("3") && n2.equals("7")) || (n1.equals("7") && n2.equals("3")) || (n1.equals("7") && n2.equals("7"))) {
            return true;
        }
        if ((n1.equals("8") && n2.equals("8")) || (n1.equals("8") && n2.equals("10")) || (n1.equals("10") && n2.equals("8")) || (n1.equals("10") && n2.equals("10"))) {
            return true;
        }
        if ((n1.equals("9") && n2.equals("9")) || (n1.equals("9") && n2.equals("11")) || (n1.equals("11") && n2.equals("9")) || (n1.equals("11") && n2.equals("11"))) {
            return true;
        }
        if ((n1.equals("12") && n2.equals("12")) || (n1.equals("12") && n2.equals("13")) || (n1.equals("13") && n2.equals("12")) || (n1.equals("13") && n2.equals("13"))) {
            return true;
        }
        return false;
    }

    @Override
    public String getStatus() {
        return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000);// update status
    }

    @Override
    public int getSpeed() {
        return DEFAULT_SEPEED;
    }

    @Override
    public int getControlSpeed() {
        return DEFAULT_CONTROL_SEPEED;
    }
}
