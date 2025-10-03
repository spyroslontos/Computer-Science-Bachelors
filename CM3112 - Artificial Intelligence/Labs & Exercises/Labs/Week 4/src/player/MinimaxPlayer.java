package player;

import java.util.*;
import pacman.*;

/**
 * 
 * This is an implementation of the PacMan player based on minimax.
 * 
 * @author Steven Schockaert
 */
public class MinimaxPlayer implements PacManPlayer {
    
    private Move lastMove = null;

    /*
     * The game engine will call this method every time PacMan is required to 
     * make a move.
     */
    public Move chooseMove(Game game) {
        State s = game.getCurrentState();
        List<Move> legalMoves = game.getLegalPacManMoves(); 
        Move bestMove = null;
        double bestScore = 0;
        for (Move m : legalMoves) {
            /*
             *  For each move PacMan is legally alloed to make, find out
             *  the corresponding utility using a minimax analysis of the
             *  game tree.
             */ 
            State next = Game.getNextState(s, m);
            double score = getMinScore(next, m, 5);
            /*
             *  We want to prevent PacMan from going back the way he came,
             *  unless doing so can prevent him from dying.
             */ 
            score -= (lastMove == m.getOpposite() ? 100.0 : 0.0);
            if (bestMove == null || score > bestScore) {
                bestMove = m;
                bestScore = score;
            }

        }
        lastMove = bestMove;
        return bestMove;
    }

    /*
     * Check which is the worst possible countermove by PacMan's opponent.
     * Note that we consider all the ghosts together as one single opponent, so 
     * we can consider a standard 2-player version of minimax.
     */ 
    private double getMinScore(State s, Move lastPacmanMove, int depth) {
        if (depth == 0 || Game.isFinal(s))
            return evaluateState(s);
        Set<List<Move>> ghostMoves = Game.getLegalCombinedGhostMoves(s);
        double res = Double.POSITIVE_INFINITY;
        for (List<Move> move : ghostMoves) {
            State next = Game.getNextState(s, move);
            double score = getMaxScore(next, lastPacmanMove, depth - 1);
            res = Math.min(res, score);                        
        }
        return res;
    }

    /*
     * Check which is the best possible move PacMan can make in a given situation.
     */ 
    private double getMaxScore(State s, Move lastPacmanMove, int depth) {
        if (depth == 0 || Game.isFinal(s))
            return evaluateState(s);
        List<Move> pacmanMoves = Game.getLegalPacManMoves(s);
        double res = Double.NEGATIVE_INFINITY;
        for (Move move : pacmanMoves) {
            /*
             * As a straightforward optimisation of the standard minimax 
             * algorithm, we don't consider moves which would make PacMan go
             * backwards. This means that in most states, there is only 1 move
             * to play instead of 2, substantially reducing the size of the game
             * tree.
             */ 
            if (!move.equals(lastPacmanMove.getOpposite())) {
                State next = Game.getNextState(s, move);
                double score = getMinScore(next, move, depth - 1);
                res = Math.max(res, score);                
            }
        }
        return res;
    }

    /**
     * Taken from SimplePacManPlayer
     * 
     * This is a simple way of estimating how good a particular state is for
     * PacMan. By improving/adapting this method, the behaviour of PacMan can 
     * be altered.     
     */
    public double evaluateState(State s) {
        if (Game.isLosing(s))
            return -1000.0; // really bad to get eaten
        if (Game.isWinning(s))
            return 0.0; // really good to win
        double score = 0.0;
        Location pacManLocation = s.getPacManLocation();
        score -= s.getDotLocations().size(); // more dots left on the board is bad
        score -= getMinDistance(pacManLocation, s.getDotLocations()); // being far from nearest dot
        // is bad
        score += getMinDistance(pacManLocation, s.getGhostLocations()); // being far from
        // nearest ghost is good
        return score;
    }

    /**
     * Taken from SimplePacManPlayer
     */
    private double getMinDistance(Location sourceLocation, Collection<Location> targetLocations) {
        double minDistance = Double.POSITIVE_INFINITY;
        for (Location dotLocation : targetLocations) {
            double distance = Location.manhattanDistance(sourceLocation, dotLocation); // one way
            // to measure distance
            if (distance < minDistance) {
                minDistance = distance;
            }
        }
        if (Double.isInfinite(minDistance))
            throw new RuntimeException();
        return minDistance;
    }
}
