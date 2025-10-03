package ghosts;

import java.util.*;

import pacman.*;

public class MinimaxGhost extends GhostPlayer {

      
    static State lastStateProcessed;
    static List<Move> lastStateMove;

    
   /*
    *  The method below will be called once for every ghost of this type in the
    *  game. However, we only want to analyse the game tree once (for efficiency)
    *  and we want to make sure that the ghosts take a coordinated action.
    * 
    *  To "hack" the framework to allow this, we search for the optimal combined 
    *  moves for the ghosts the first time this method is called, and store this
    *  move in a static variable "lastStateMove". When this method is subsequently 
    *  called for the other ghosts, they will choose the move found by the first
    *  ghost.
    * 
    * @author Steven Schockaert
    */ 
    public Move chooseMove(Game game, int ghostIndex) {
        State state = game.getCurrentState();
        if (!state.equals(lastStateProcessed)) {
            Set<List<Move>> ghostMoves = Game.getLegalCombinedGhostMoves(state);
            List<Move> bestMove = null;
            double bestScore = 0;
            for (List<Move> m : ghostMoves) {
                State next = Game.getNextState(state, m);
                int score = getMinScore(next, 5);
                if (bestMove == null || score > bestScore) {
                    bestMove = m;
                    bestScore = score;
                }
            }
            lastStateProcessed = state;
            lastStateMove=bestMove;
        }        
        return lastStateMove.get(ghostIndex);
    }

    private int getMinScore(State s, int depth) {
        if (depth == 0 || Game.isFinal(s))
            return evaluateState(s);
        List<Move> pacmanMoves = Game.getLegalPacManMoves(s);
        int res = Integer.MAX_VALUE;
        for (Move move : pacmanMoves) {
            State next = Game.getNextState(s, move);
            int score = getMaxScore(next, depth - 1);
            res = Math.min(res, score);            
        }
        return res;
    }

    private int getMaxScore(State s, int depth) {
        if (depth == 0 || Game.isFinal(s))
            return evaluateState(s);
        Set<List<Move>> ghostMoves = Game.getLegalCombinedGhostMoves(s);
        int res = Integer.MIN_VALUE;
        for (List<Move> move : ghostMoves) {
            State next = Game.getNextState(s, move);
            int score = getMinScore(next, depth - 1);
            res = Math.max(res, score);            
        }
        return res;
    }

    /*
     *  Simple attempt to characterise which states are good for the ghosts
     *  Essentially: everything which is good for PacMan is bad for the ghosts
     *  and vice versa. 
     */ 
    public int evaluateState(State s) {
        int score = 5000;
        if (Game.isLosing(s))
            score+= 10000;
        if (Game.isWinning(s))
            score-=10000;        
        Location pacManLocation = s.getPacManLocation();
        List<Location> ghostLocations = s.getGhostLocations();
        int minX = 1000;
        int maxX = -1000;
        int minY = 1000;
        int maxY = -1000;
        for (int i = 0; i < ghostLocations.size(); i++) {
            Location loc = ghostLocations.get(i);
            score -= Location.euclideanDistance(pacManLocation, loc);
            minX = Math.min(minX,loc.getX());
            maxX = Math.max(maxX,loc.getX());
            minY = Math.min(minY,loc.getY());
            maxY = Math.max(maxY,loc.getY());
        }
        if(minX<=pacManLocation.getX() && maxX>=pacManLocation.getX())
            score+=1;
        if(minY<=pacManLocation.getY() && maxY>=pacManLocation.getY())
            score+=1;
        return score;
    }
}