/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Eman Khalil
 */
public class LevelOneStrategy implements LevelStrategy {

    
    private static final int NUM_OF_MOVING_OBJ = 10;
    private int numberOfShapes=7;

    @Override
    public int getNumberOfShapes() {
        return numberOfShapes;
    }

    
    @Override
    public int getNumOfMovingObj() {
        return NUM_OF_MOVING_OBJ;
    }

}
