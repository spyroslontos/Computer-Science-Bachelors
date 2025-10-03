/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package weakbridge;

import search.*;
import java.util.*;

/**
 *
 * @author steven
 */
public class WeakBridgeState implements State {

    Set<String> peopleLeft;
    Set<String> peopleRight;
    Map<String, Integer> costMap;
    boolean torchLeft;

    public WeakBridgeState(Map<String, Integer> costMap) {
        peopleLeft = new HashSet();
        peopleRight = new HashSet();
        peopleLeft.addAll(costMap.keySet());
        this.costMap = costMap;
        torchLeft = true;
    }

    public WeakBridgeState(WeakBridgeState st) {
        peopleLeft = new HashSet();
        peopleLeft.addAll(st.peopleLeft);
        peopleRight = new HashSet();
        peopleRight.addAll(st.peopleRight);
        costMap = new HashMap();
        costMap.putAll(st.costMap);
        torchLeft = st.torchLeft;
    }

    public List<Action> getLegalActions() {
        List<Action> res = new ArrayList();
        if (torchLeft) {
            for (String person1 : peopleLeft) {
                res.add(new Move(person1, null, true, costMap));
                for (String person2 : peopleLeft) {
                    if (person1.compareTo(person2) < 0)
                        res.add(new Move(person1, person2, true, costMap));
                }
            }
        }
        else {
            for (String person1 : peopleRight) {
                res.add(new Move(person1, null, false, costMap));
                for (String person2 : peopleRight) {
                    if (person1.compareTo(person2) < 0)
                        res.add(new Move(person1, person2, false, costMap));
                }
            }
        }
        return res;
    }

    public boolean isGoal() {
        return peopleLeft.isEmpty();
    }

    public int getEstimatedDistanceToGoal(){        
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for(String person:peopleLeft){
            sum+=costMap.get(person);
            min = Math.min(min, costMap.get(person));
        }
        if(peopleLeft.size() % 2 == 0)
            return sum/2;        
        else
            return (sum/2) + min;
    }
    
    public void printState() {
        System.out.print("People left:");
        for (String s : peopleLeft)
            System.out.print(" " + s);
        System.out.println();
        System.out.println("The torch is:" + (torchLeft ? "left" : "right"));
    }

    public boolean isLegal(Action action) {
        if (!(action instanceof Move))
            return false;
        Move m = (Move) action;        
        if (m.fromLeft()){
            if (!torchLeft)
                return false;
            if (!peopleLeft.contains(m.getPerson1()))
                return false;
            if (m.getPerson2() != null && !peopleLeft.contains(m.getPerson2()))
                return false;
            return true;
        }
        else {
            if (torchLeft)
                return false;
            if (!peopleRight.contains(m.getPerson1()))
                return false;
            if (m.getPerson2() != null && !peopleRight.contains(m.getPerson2()))
                return false;
            return true;
        }
    }

    public State doAction(Action action) {
        Move m = (Move) action;
        WeakBridgeState st = new WeakBridgeState(this);
        if (m.fromLeft()) {
            st.torchLeft = false;
            st.peopleLeft.remove(m.getPerson1());
            st.peopleRight.add(m.getPerson1());
            if (m.getPerson2() != null) {
                st.peopleLeft.remove(m.getPerson2());
                st.peopleRight.add(m.getPerson2());
            }
        }
        else {
            st.torchLeft = true;
            st.peopleRight.remove(m.getPerson1());
            st.peopleLeft.add(m.getPerson1());
            if (m.getPerson2() != null) {
                st.peopleRight.remove(m.getPerson2());
                st.peopleLeft.add(m.getPerson2());
            }
        }
        return st;
    }

    public boolean equals(State st) {
        if(!(st instanceof WeakBridgeState))
            return false;
        WeakBridgeState wbs = (WeakBridgeState) st;
        return wbs.peopleLeft.equals(peopleLeft) && wbs.peopleRight.equals(peopleRight) && wbs.torchLeft==torchLeft;
    }
    
     public int hashCode(){
        int sum = 0;
        for(String l:peopleLeft)
            sum += 2*l.hashCode();
        if(torchLeft)
            sum+=11;
        return sum;
    }
        
    public static void main(String [] args){
        Map<String,Integer> costs = new HashMap();
        costs.put("p1", 1);
        costs.put("p2", 2);
        costs.put("p3", 7);
        costs.put("p4", 10);
        WeakBridgeState st = new WeakBridgeState(costs);
        st.printState();
        List<Action> actions = st.getLegalActions();
        for(Action action:actions){
            System.out.println("*******************");
            System.out.println(action);
            st.doAction(action).printState();                 
        }
    }
}
