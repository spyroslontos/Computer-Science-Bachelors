/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eightpuzzle;

import search.Action;
import search.State;

/**
 *
 * @author steven
 */
public class MoveRight implements Action{

    public int getCost() {
        return 1;
    }

    public String toString(){
        return "move right";
    }
    
}
