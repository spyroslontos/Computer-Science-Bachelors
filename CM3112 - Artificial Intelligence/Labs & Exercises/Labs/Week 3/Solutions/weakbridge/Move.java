/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package weakbridge;

import search.Action;
import java.util.*;

/**
 *
 * @author steven
 */
public class Move implements Action{
    
    Map<String,Integer> costs;
    boolean fromLeft;
    String person1;
    String person2; // this person is allowed to be null, in case we only want to move 1 person across
    
    public Move(String person1, String person2, boolean fromLeft, Map<String,Integer> costs){
        this.person1=person1;
        this.person2=person2;
        this.fromLeft=fromLeft;
        this.costs=costs;
    }
    
    public int getCost() {
        if(person2==null)
            return costs.get(person1);
        else    
            return Math.max(costs.get(person1),costs.get(person2));
    }

    public boolean fromLeft(){
        return fromLeft;
    }
    
    public String getPerson1(){
        return person1;
    }
    
    public String getPerson2(){
        return person2;
    }
    
    public String toString(){
        if(person2==null){        
            String dir = fromLeft? " crosses":" comes back;";
            return person1 +dir;
        }
        else{
            String dir = fromLeft? " cross":" come back;";
            return person1 + " and " + person2 + dir;
        }
    }
}
