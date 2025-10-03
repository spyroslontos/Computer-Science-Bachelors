package player;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import util.*;
import pacman.*;

/**
 * A very simple implementation of the PacManPlayer interface created by
 * the course staff. This is an
 * example of what you should create for Project 0 (but you can be more
 * creative!).
 * 
 * @author grenager
 */
public class SimplePacManPlayer implements PacManPlayer {
    
    static Random random = new Random();
    
    private Move lastMove = null;
    
    /**
     * This is the only public method, required by the PacManPlayer interface.
     */
    public Move chooseMove(Game game) {
        State s = game.getCurrentState();
        List<Move> legalMoves = game.getLegalPacManMoves(); // find out what moves
        // PacMan can make
        Counter<Move> scores = new Counter<Move>(); // keep a tally of all their
        // scores
        for (Move m : legalMoves) {
            List<State> stateList = Game.getProjectedStates(s, m); // project out
            // where this
            // direction goes
            State last = stateList.get(stateList.size() - 1);
            double stateScore = evaluateState(s, last); // how good the resulting state would be
            double turnaroundPenalty = (lastMove == m.getOpposite() ? -10.0 : 0.0); // penalize
                                                                    // a move that turns around
            scores.setCount(m, stateScore + turnaroundPenalty); // remember this score for later
        }
        scores = scores.exp().normalize(); // this is the "soft-max" function
        Move move = scores.sampleFromDistribution(random); // take a sample from this distribution
        lastMove = move; // remember what move we made for next time
        return move;
    }
    
    public double evaluateState(State s, State next) {
        if (Game.isLosing(next))
            return -1000.0; // really bad to get eaten
        if (Game.isWinning(next))
            return 0.0; // really good to win
        double score = 0.0;
        Location pacManLocation = next.getPacManLocation();
        score -= s.getDotLocations().size(); // more dots left on the board is bad
        score -= getMinDistance(pacManLocation, s.getDotLocations()); // being far from nearest dot
                                                                      // is bad
        score += getMinDistance(pacManLocation, s.getGhostLocations()); // being far from
                                                                        // nearest ghost is good
        return score;
    }
    
    /**
     * Returns the distance from the source location to the closest of the target
     * locations.
     * 
     * @param sourceLocation
     * @param targetLocations
     * @return
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
