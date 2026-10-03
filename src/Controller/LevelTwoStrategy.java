/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Eman Khalil
 */
public class LevelTwoStrategy implements LevelStrategy  {
    
    private static final int NUM_OF_MOVING_OBJ = 20;
    private int numberOfShapes=11;

    @Override
    public int getNumberOfShapes() {
        return numberOfShapes;
    }
   
    @Override
    public int getNumOfMovingObj() {
        return NUM_OF_MOVING_OBJ;
    }
}
