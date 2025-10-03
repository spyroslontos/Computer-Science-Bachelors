package ghosts;

import java.util.ArrayList;
import java.util.List;

import pacman.Game;
import pacman.Location;
import pacman.LocationSet;
import pacman.State;
import pacman.Move;
import util.Pair;

public class DaveRandomGhostPlayer extends RandomGhostPlayer {
    
    static double weightDistanceToPacMan = 0.5,
        weightDistanceToNearestGhost = 0.45,
        weightDistanceToNearestDot = 0.05;
    
    static double distanceToNearestGhostThreshold = 8;
    
    public List<Pair<Move, Double>> getMoveDistribution(Game game, State state, int ghostIndex) {
        List<Move> moves = Game.getLegalGhostMoves(state, ghostIndex);
        if (moves.isEmpty()) return null;
        double maxDistance = Game.xDim + Game.yDim;
        Location pacManLoc = state.getPacManLocation();
        List<Location> ghostLocations = new ArrayList<Location>(state.getGhostLocations());
        ghostLocations.remove(ghostIndex);
        LocationSet dotLocations = state.getDotLocations();
        double totalScore = 0;
        
        List<Pair<Move, Double>> distribution = new ArrayList<Pair<Move, Double>>();
        for (Move move : moves) {
            Location newLoc = Game.getNextLocation(state.getGhostLocations().get(ghostIndex), move);
            double score = weightDistanceToPacMan*(1.0-Location.manhattanDistance(newLoc, pacManLoc)/maxDistance);
            double distanceToNearestGhost = (double)Location.manhattanDistanceToClosest(newLoc, ghostLocations);
            if(distanceToNearestGhost < distanceToNearestGhostThreshold) {
      		score += (weightDistanceToNearestGhost*(distanceToNearestGhost/maxDistance));
            }
            score += (weightDistanceToNearestDot*(1.0-Location.manhattanDistanceToClosest(newLoc, dotLocations.list())/maxDistance));
            distribution.add(new Pair<Move, Double>(move, score));
            totalScore += score;
        }
        for(Pair<Move, Double> pair : distribution) {
            pair.setSecond(pair.second()/totalScore);
        }
        return distribution;
    }
}