/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package searchSolution;

import search.*;
import eightpuzzle.EightPuzzleState;
import java.util.*;

/**
 *
 * @author steven
 */
public class DepthFirstSearch {

    Stack<Node> frontier;
    
    public DepthFirstSearch(State initialState){
        frontier = new Stack();
        frontier.push(new Node(initialState,null,null));
    }
    
     public Node findPathToGoal(){
        while(!frontier.isEmpty()){
            Node n = frontier.pop();
            //System.out.println("-");
            if(n.getState().isGoal()){
                /*
                 * We have found the goal. From the representation of the node
                 * we can easily retrieve the path that has led us from the
                 * initial state to this solution
                 */                 
                return n;                       
            }
            else{
                List<Action> actions = n.getState().getLegalActions();                
                for(Action action:actions){
                    State child = n.getState().doAction(action);    
                    /* 
                     * Cycle checking: to avoid going in circles, we check if we 
                     * have already encountered this state on the path from the initial
                     * state to the current state
                     */ 
                    if(!n.alreadyExplored(child)){                               
                        frontier.push(new Node(child,n,action));
                        //System.out.println("+");
                    }
                }                
            }
        }
        
        /*
         * We have explored the entire tree, but found no goal state. This 
         * means that the problem does not have a solution.
         */ 
        return null;
    }
    
    public static void testEightPuzzle(){
        int [][] v = {{1,4,7},{2,5,8},{0,3,6}};
//        int [][] v = {{1,0,2},{3,4,5},{6,7,8}};
//        int [][] v = {{1,2,5},{3,4,0},{6,7,8}};
        EightPuzzleState st = new EightPuzzleState(v);
        DepthFirstSearch dps = new DepthFirstSearch(st);
        Node goal = dps.findPathToGoal();
        List<Action> actions = goal.getActions();
        System.out.println("The actions taken to find a solution:" + actions);        
        System.out.println("The sequence of states that was encountered:");        
        List<State> solution = goal.getPath();
        if(solution!=null){
            for(State st0:solution)
                st0.printState();
        }
        System.out.println("The cost of this solution:" + goal.getCost());   
    } 
     
    public static void main(String [] args){       
        testEightPuzzle();
    }
}
