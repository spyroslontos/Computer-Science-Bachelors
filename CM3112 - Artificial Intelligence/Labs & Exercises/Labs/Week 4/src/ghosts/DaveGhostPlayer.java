package ghosts;

import java.util.List;

import pacman.Game;
import pacman.Move;
import util.Pair;

public class DaveGhostPlayer extends DaveRandomGhostPlayer {
    
    public Move chooseMove(Game game, int ghostIndex) {
        List<Pair<Move, Double>> distribution = getMoveDistribution(game, game.getCurrentState(), ghostIndex);
        Move bestMove = null;
        double bestScore = Double.NEGATIVE_INFINITY;
        for(Pair<Move, Double> pair : distribution) {
            if(pair.second() > bestScore) {
                bestScore = pair.second();
                bestMove = pair.first();
            }
        }
        return bestMove;
    }
}