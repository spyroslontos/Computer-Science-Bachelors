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
public class BreadthFirstSearchMultiplePathPruning {

    LinkedList<Node> frontier;
    Set<State> closed;
    static int counter = 0;

    public BreadthFirstSearchMultiplePathPruning(State initialState) {
        frontier = new LinkedList();
        closed = new HashSet();
        frontier.add(new Node(initialState, null, null));
    }

    public Node findPathToGoal() {
        counter = 0;
        while (!frontier.isEmpty()) {
            Node n = frontier.removeFirst();
            /* 
             * Multiple path pruning: avoid that states are considered
             * more than once.
             */
            if (!closed.contains(n.getState())) {


                counter++;
                closed.add(n.getState());
                if (n.getState().isGoal()) {
                    /*
                     * We have found the goal. From the representation of the node
                     * we can easily retrieve the path that has led us from the
                     * initial state to this solution
                     */
                    return n;
                } else {
                    List<Action> actions = n.getState().getLegalActions();
                    for (Action action : actions) {
                        State child = n.getState().doAction(action);
                        if (!closed.contains(n.getState())) {
                            frontier.add(new Node(child, n, action));
                        }
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

    public static void testEightPuzzle() {
//        int[][] v = {{1, 4, 7}, {2, 5, 8}, {0, 3, 6}};
//        int [][] v = {{1,0,2},{3,4,5},{6,7,8}};
//        int[][] v = {{1, 2, 5}, {3, 4, 0}, {6, 7, 8}};
             int[][] v = {{1, 5, 8}, {4, 0, 2}, {3,6, 7}};
        EightPuzzleState st = new EightPuzzleState(v);
        BreadthFirstSearchMultiplePathPruning dps = new BreadthFirstSearchMultiplePathPruning(st);
        Node goal = dps.findPathToGoal();
        List<Action> actions = goal.getActions();
        System.out.println("The actions taken to find a solution:" + actions);
        System.out.println("The sequence of states that was encountered:");
        List<State> solution = goal.getPath();
        if (solution != null) {
            for (State st0 : solution) {
                st0.printState();
            }
        }
        System.out.println("The cost of this solution:" + goal.getCost());
        System.out.println("The number of nodes that have been expanded:" + counter);

    }

    public static void main(String[] args) {
        testEightPuzzle();
    }
}
