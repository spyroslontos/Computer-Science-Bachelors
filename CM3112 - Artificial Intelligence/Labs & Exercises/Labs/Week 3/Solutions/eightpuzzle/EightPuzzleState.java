/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eightpuzzle;

import search.*;
import java.util.*;

/**
 *
 * @author steven
 */
public class EightPuzzleState implements State {

    private int[][] values;
    private int rowBlank;
    private int colBlank;

    private static MoveLeft left = new MoveLeft();
    private static MoveRight right = new MoveRight();
    private static MoveUp up = new MoveUp();
    private static MoveDown down = new MoveDown();
    
    public EightPuzzleState(int[][] v) {
        try {
            values = new int[3][3];
            if (v.length != 3)
                throw new Exception();
            Set<Integer> otherValues = new HashSet();
            for (int row = 0; row <= 2; row++) {
                if (v[row].length != 3)
                    throw new Exception();
                for (int col = 0; col <= 2; col++) {
                    if (v[row][col] >= 0 && v[row][col] <= 8 && !otherValues.contains(v[row][col])) {
                        values[row][col] = v[row][col];
                        otherValues.add(v[row][col]);

                        if (v[row][col] == 0) {
                            rowBlank = row;
                            colBlank = col;
                        }
                    }
                    else
                        throw new Exception();
                }
            }
        }
        catch (Exception e) {
            System.err.println("Invalid state");
            values = null;
        }
    }

    public List<Action> getLegalActions() {
        ArrayList<Action> res = new ArrayList();        
        if(isLegal(left))
            res.add(left);
        if(isLegal(right))
            res.add(right);
        if(isLegal(up))
            res.add(up);
        if(isLegal(down))
            res.add(down);
        return res;
    }

    public State doAction(Action action){
        EightPuzzleState res = new EightPuzzleState(values);
        if(action instanceof MoveLeft)            
            res.moveBlank(rowBlank,colBlank-1);            
        else if(action instanceof MoveRight)
            res.moveBlank(rowBlank,colBlank+1);        
        else if(action instanceof MoveUp)
            res.moveBlank(rowBlank-1,colBlank);       
        else if(action instanceof MoveDown)
            res.moveBlank(rowBlank+1,colBlank);        
        else
            return null;
        return res;
    }
    
    public boolean isGoal(){
        for(int row=0;row<=2;row++){
            for(int col=0;col<=2;col++){
                if(values[row][col]!=3*row + col)
                    return false;
            }
        }
        return true;
    }
  
    public int getEstimatedDistanceToGoal(){
        int distance = 0;
        for(int row=0;row<=2;row++){
            for(int col=0;col<=2;col++){
                if(values[row][col]!=3*row + col)
                    distance++;
            }
        }
        return distance;
    }
    
    public boolean isLegal(Action action){
        if(action instanceof MoveLeft)
            return colBlank!=0;
        else if(action instanceof MoveRight)
            return colBlank!=2;
        else if(action instanceof MoveUp)
            return rowBlank!=0;
        else if(action instanceof MoveDown)
            return rowBlank!=2;
        else
            return false;
    }
    
          
    public void moveBlank(int row, int col) {                        
        int tmp = values[row][col];
        values[row][col] = values[rowBlank][colBlank];
        values[rowBlank][colBlank] = tmp;
        rowBlank=row;
        colBlank=col;
    }
    
    public void printState(){
        System.out.println("-------------");
        for(int row=0;row<=2;row++){
        System.out.println("| "+values[row][0]+" | "+values[row][1]+" | "+values[row][2]+" |");
        System.out.println("-------------");
        }        
    }
    
    public boolean equals(Object st){
        if(!(st instanceof EightPuzzleState))
            return false;
        else{
            EightPuzzleState st0 = (EightPuzzleState)st;
            for(int row=0;row<=2;row++){
                for(int col=0;col<=2;col++){
                    if(st0.values[row][col]!=values[row][col])
                        return false;
                }
            }
            return true;
        }    
    }
    
    public int hashCode(){
        int sum = 0;
        for(int i=0;i<values.length;i++){
            for(int j=0;j<values.length;j++){
                sum += Math.round(Math.pow(11,i) * Math.pow(13,j) * values[i][j]);
            }
        }
        return sum;
    }
    
    public static void main(String [] args){
        //simple test
        int [][] v = {{0,1,2},{3,4,5},{6,7,8}};
        EightPuzzleState st = new EightPuzzleState(v);
        st.printState();
        System.out.println(st.isGoal());
        List<Action> actions = st.getLegalActions();
        for(Action ac:actions){
            System.out.println();
            System.out.println(ac);
            st.doAction(ac).printState();
        }
    }
}
