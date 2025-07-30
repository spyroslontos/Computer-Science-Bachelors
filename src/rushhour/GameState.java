/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rushhour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.*;

import search.Action;
import search.State;

/**
 * @author steven + C1722325
 */
public class GameState implements search.State {

    boolean[][] occupiedPositions;
    List<Car> cars; // target car is always the first one
    int nrRows;
    int nrCols;

    public GameState(String fileName) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        nrRows = Integer.parseInt(in.readLine().split("\\s")[0]);
        nrCols = Integer.parseInt(in.readLine().split("\\s")[0]);
        String s = in.readLine();
        cars = new ArrayList();
        while (s != null) {
            cars.add(new Car(s));
            s = in.readLine();
        }
        initOccupied();
    }

    public GameState(int nrRows, int nrCols, List<Car> cars) {
        this.nrRows = nrRows;
        this.nrCols = nrCols;
        this.cars = cars;
        initOccupied();
    }

    public GameState(GameState gs) {
        nrRows = gs.nrRows;
        nrCols = gs.nrCols;
        occupiedPositions = new boolean[nrRows][nrCols];
        for (int i = 0; i < nrRows; i++) {
            for (int j = 0; j < nrCols; j++) {
                occupiedPositions[i][j] = gs.occupiedPositions[i][j];
            }
        }
        cars = new ArrayList();
        for (Car c : gs.cars) {
            cars.add(new Car(c));
        }
    }

    public void printState() {
        int[][] state = new int[nrRows][nrCols];

        for (int i = 0; i < cars.size(); i++) {
            List<Position> l = cars.get(i).getOccupyingPositions();
            for (Position pos : l) {
                state[pos.getRow()][pos.getCol()] = i + 1;
            }
        }

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                if (state[i][j] == 0) {
                    System.out.print("[" + "." + "]");
                } else {
//                    System.out.print("x");
//                    System.out.print(state[i][j] - 1);

                    System.out.print("[" + (state[i][j] - 1) + "]");
                }
            }
            System.out.println();
        }
    }

    private void initOccupied() {
        occupiedPositions = new boolean[nrRows][nrCols];
        for (Car c : cars) {
            List<Position> l = c.getOccupyingPositions();
            for (Position pos : l) {
                occupiedPositions[pos.getRow()][pos.getCol()] = true;
            }
        }
    }

    public boolean isGoal() {
        Car goalCar = cars.get(0);
        return goalCar.getCol() + goalCar.getLength() == nrCols;
    }

    public boolean equals(Object o) {
        if (!(o instanceof GameState)) {
            return false;
        } else {
            GameState gs = (GameState) o;
            return nrRows == gs.nrRows && nrCols == gs.nrCols && cars.equals(gs.cars); // note that we don't need to check equality of occupiedPositions since
            // that follows from the equality of cars
        }
    }

    public int hashCode() {
        return cars.hashCode();
    }

    public void printToFile(String fn) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(fn));
            out.println(nrRows);
            out.println(nrCols);
            for (Car c : cars) {
                out.println(c.getRow() + " " + c.getCol() + " " + c.getLength() + " " + (c.isVertical() ? "V" : "H"));
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Action> getLegalActions() {

        int[][] state = new int[nrRows][nrCols];
        List<PositionalDirection> blankNeighbours = new ArrayList();
        ArrayList<Action> carActions = new ArrayList();

        // Getting the occupying positions of the cars
        for (int i = 0; i < cars.size(); i++) {
            List<Position> l = cars.get(i).getOccupyingPositions();
            for (Position pos : l) {
                state[pos.getRow()][pos.getCol()] = i + 1;
            }
        }

        // Finds the 4 points surrounding the empty spaces it with its positional direction
        // and also avoiding any out the box instances

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                if (state[i][j] == 0) {
                    //Checks to see if the empty space has been found in the top most row of the box
                    if (i > 0) {
                        blankNeighbours.add(new PositionalDirection(i - 1, j, "up"));
                    }
                    //Checks to see if the empty space has been found in the bottom most row of the box
                    if (i < nrRows - 1) {
                        blankNeighbours.add(new PositionalDirection(i + 1, j, "down"));
                    }
                    //Checks to see if the empty space has been found in the left most column of the box
                    if (j > 0) {
                        blankNeighbours.add(new PositionalDirection(i, j - 1, "left"));
                    }
                    //Checks to see if the empty space has been found in the left most column of the box
                    if (j < nrCols - 1) {
                        blankNeighbours.add(new PositionalDirection(i, j + 1, "right"));
                    }
                }
            }
        }

        //Finds and adds the Action containing the car, the direction it can move and the number of steps
        for (int blankPos = 0; blankPos < blankNeighbours.size(); blankPos++) {
            for (Car car : cars) {
                for (int occPos = 0; occPos < car.getOccupyingPositions().size(); occPos++) {

                    //Checks to see if the point neighbouring the blank also has a car and if can move to it
                    if (blankNeighbours.get(blankPos).getCol() == car.getOccupyingPositions().get(occPos).getCol()
                            && blankNeighbours.get(blankPos).getRow() == car.getOccupyingPositions().get(occPos).getRow()
                            && blankNeighbours.get(blankPos).getDirection().equals("down")
                            && car.isVertical()) {

                        //Finds the number of steps a car can move before finding another car to stop it
                        //Each step is added as a different action
                        for (int steps = 1; steps <= nrRows - (nrRows - blankNeighbours.get(blankPos).getRow()); steps++ ) {
                            if (state[blankNeighbours.get(blankPos).getRow() - steps][blankNeighbours.get(blankPos).getCol()] == 0){
                                Action actionUp = new MoveUp(cars.indexOf(car),steps);
                                //Checks if the data entered is valid and then add it in the list of actions
                                if (isLegal(actionUp))
                                    carActions.add(actionUp);
                            }
                            else { break; }
                        }

                    //Checks to see if the point neighbouring the blank also has a car and if can move to it
                    } else if (blankNeighbours.get(blankPos).getCol() == car.getOccupyingPositions().get(occPos).getCol()
                            && blankNeighbours.get(blankPos).getRow() == car.getOccupyingPositions().get(occPos).getRow()
                            && blankNeighbours.get(blankPos).getDirection().equals("up")
                            && car.isVertical()) {

                        //Finds the number of steps a car can move before finding another car to stop it
                        //Each step is added as a different action
                        for (int steps = 1; steps < nrRows - blankNeighbours.get(blankPos).getRow(); steps++ ) {
                            if (state[blankNeighbours.get(blankPos).getRow() + steps][blankNeighbours.get(blankPos).getCol()] == 0){
                                Action actionDown = new MoveDown(cars.indexOf(car),steps);
                                //Checks if the data entered is valid and then add it in the list of actions
                                if (isLegal(actionDown))
                                    carActions.add(actionDown);
                            }
                            else { break; }
                        }

                    //Checks to see if the point neighbouring the blank also has a car and if can move to it
                    } else if (blankNeighbours.get(blankPos).getCol() == car.getOccupyingPositions().get(occPos).getCol()
                            && blankNeighbours.get(blankPos).getRow() == car.getOccupyingPositions().get(occPos).getRow()
                            && blankNeighbours.get(blankPos).getDirection().equals("right")
                            && !car.isVertical()) {

                        //Finds the number of steps a car can move before finding another car to stop it
                        //Each step is added as a different action
                        for (int steps = 1; steps <= nrCols - (nrCols - blankNeighbours.get(blankPos).getCol()); steps++ ) {
                            if (state[blankNeighbours.get(blankPos).getRow()][blankNeighbours.get(blankPos).getCol()- steps] == 0){
                                Action actionLeft = new MoveLeft(cars.indexOf(car),steps);
                                //Checks if the data entered is valid and then add it in the list of actions
                                if (isLegal(actionLeft))
                                    carActions.add(actionLeft);
                            }
                            else { break; }
                        }

                    //Checks to see if the point neighbouring the blank also has a car and if can move to it
                    } else if (blankNeighbours.get(blankPos).getCol() == car.getOccupyingPositions().get(occPos).getCol()
                            && blankNeighbours.get(blankPos).getRow() == car.getOccupyingPositions().get(occPos).getRow()
                            && blankNeighbours.get(blankPos).getDirection().equals("left")
                            && !car.isVertical()) {

                        //Finds the number of steps a car can move before finding another car to stop it
                        //Each step is added as a different action
                        for (int steps = 1; steps < nrCols - blankNeighbours.get(blankPos).getCol(); steps++ ) {
                            if (state[blankNeighbours.get(blankPos).getRow()][blankNeighbours.get(blankPos).getCol()+steps] == 0){
                                Action actionRight = new MoveRight(cars.indexOf(car),steps);
                                //Checks if the data entered is valid and then add it in the list of actions
                                if (isLegal(actionRight))
                                    carActions.add(actionRight);
                            }
                            else { break; }
                        }
                    }
                }
            }
        }

        //returns a list of action that can be made at that specific state of the game.
        return carActions;
    }

    public boolean isLegal(Action action) {

        //Checks if the format of the direction in the Action is correct
        if (action.getDirection() instanceof String){
            return true;
        }
         else {
             return false;
         }
    }

    public State doAction(Action action) {

        //Creates an instance of the GameState at every action
        GameState moveState = new GameState(new GameState(nrRows, nrCols, cars));

        //Moves a car, a specific direction with a certain number of steps
        if(action.getDirection().equals("up")) {
            moveState.cars.get(action.getCar()).setRow(cars.get(action.getCar()).getRow()-action.getSteps());
        }
        else if(action.getDirection().equals("down")) {
            moveState.cars.get(action.getCar()).setRow(cars.get(action.getCar()).getRow() + action.getSteps());
        }
        else if(action.getDirection().equals("left")) {
            moveState.cars.get(action.getCar()).setCol(cars.get(action.getCar()).getCol() - action.getSteps());
        }
        else if(action.getDirection().equals("right")) {
            moveState.cars.get(action.getCar()).setCol(cars.get(action.getCar()).getCol() + action.getSteps());
        }
        else
            return null;

        //returns the state to find its cost and estimatedDistanceToGoal
        return moveState;
    }

    public int getEstimatedDistanceToGoal() {

        int[][] state = new int[nrRows][nrCols];

        // Getting the occupying positions of the cars
        for (int i = 0; i < cars.size(); i++) {
            List<Position> l = cars.get(i).getOccupyingPositions();
            for (Position pos : l) {
                state[pos.getRow()][pos.getCol()] = i;
            }
        }

        int blockingCars = 0;
        int blockingofBlocked = 0;

        // We check to find the number of cars blocking car 0
        for (int steps = 1; steps <= nrCols - cars.get(0).getCol() - cars.get(0).getLength() ; steps++){
            if (state[cars.get(0).getRow()][cars.get(0).getCol()+cars.get(0).getLength()-1+steps] != 0 ) {

                blockingCars++;
                //After finding a car that is blocking Car 0 we check again to see if that car is blocked on both sides
                int car = state[cars.get(0).getRow()][cars.get(0).getCol()+cars.get(0).getLength()-1+steps];

                boolean blocked = false;

                if (cars.get(car).getRow() > 0) {
                    if (state[cars.get(car).getRow()-1][cars.get(car).getCol()] != 0) {
                        blocked = true;
                    }
                }
                if (cars.get(car).getRow() < nrRows-cars.get(car).getLength() && blocked) {
                    if (state[cars.get(car).getRow() + cars.get(car).getLength()][cars.get(car).getCol()] != 0) {
                        blockingofBlocked++;
                        //If it is found to be blocked on both sides we add 1
                    }
                }
            }
        }

        //This checks if is at least 1 car blocking car 0 from reaching the goal and adds 1 to
        // account for the final move of the car in reaching the goal.
        if (blockingCars > 0) {blockingCars++;}

        //Returns the admissible heuristic's result
        return blockingCars+blockingofBlocked;
    }
}

/**
 * javac rushhour/*.java
 * java -Xmx1g rushhour/RushHour random1.txt
 */