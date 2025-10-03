/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package search;

import eightpuzzle.EightPuzzleState;
import java.util.*;

/**
 *
 * @author steven
 */
public class Astar {

    PriorityQueue<AstarNode> frontier;
    
    public Astar(State initialState){
        frontier = new PriorityQueue();
        frontier.add(new AstarNode(initialState,null,null));
    }
    
    public Node findPathToGoal(){
        while(!frontier.isEmpty()){
            Node n = frontier.poll();
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
                        frontier.add(new AstarNode(child,n,action));
                    }
                }
            }
        }
        
        /*
         * We have explored the entire tree, but found no goal state. This 
         * means that the problem does not have a solution.
         */ 
        System.out.println("No solution found.");
        return null;
    }
 
     public static void main(String[] args) {
        int[][] v = {{1, 4, 7}, {2, 5, 8}, {0, 6, 3}};
//        int [][] v = {{1,0,2},{3,4,5},{6,7,8}};
//        int [][] v = {{1,2,5},{3,4,0},{6,7,8}};
        EightPuzzleState st = new EightPuzzleState(v);
        Astar as = new Astar(st);
        Node goal = as.findPathToGoal();
        List<Action> actions = goal.getActions();
        System.out.println("The actions taken to find a solution:" + actions);
        System.out.println("The sequence of states that was encountered:");
        List<State> solution = goal.getPath();
        if (solution != null) {
            for (State st0 : solution)
                st0.printState();
        }
        System.out.println("The cost of this solution:" + goal.getCost());
    }
    
}
