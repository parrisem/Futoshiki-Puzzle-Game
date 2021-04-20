/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshikipuzzlegui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author pe79
 */
public class FutoshikiPuzzle2 {
    public int gridSize;

    private Random random = new Random();
    private FutoshikiSquare[][] grid;
    private Constraint[][] rowConstraint;
    private Constraint[][] colConstraint;

    public FutoshikiPuzzle2(int gridSize) {
        this.gridSize = gridSize;
        grid = new FutoshikiSquare[gridSize][gridSize];
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                grid[row][col] = new FutoshikiSquare(row, col);
            }
        }
        rowConstraint = new Constraint[gridSize][gridSize - 1];
        colConstraint = new Constraint[gridSize - 1][gridSize];
    }

    public void setSquare(int i, int row, int col) {
        if (i > 0 && row < grid.length && col < grid.length) {
            grid[row][col].setSquare(i);
        } else {
            System.out.println("At least one of these values, " + i + " , " + row + " and " + col + " are wrong.");
        }
    }
   
    public FutoshikiSquare getGrid(int row, int col) {
        return grid[row][col];
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public FutoshikiSquare[][] getGrid() {
        return grid;
    }

    public void setGrid(FutoshikiSquare[][] grid) {
        this.grid = grid;
    }

    public String getRowConstraints(int row, int col) {
        return rowConstraint[row][col].getSymbol();
    }

    public void setRowConstraint(Constraint[][] rowConstraint) {
        this.rowConstraint = rowConstraint;
    }

    public String getColumnConstraints(int row, int col) {
        return colConstraint[row][col].getSymbol();
    }

    public void setColConstraint(Constraint[][] colConstraint) {
        this.colConstraint = colConstraint;
    }
    
    public void setRowConstraints(int num, int row, int col) {
        Constraint constraint;
        if (num == 0) {
            constraint = new LessThan(true);
        } else if (num == 1) {
            constraint = new GreaterThan(true);
        } else {
            constraint = new EmptyConstraint(true);
        }
        rowConstraint[row][col] = constraint;
    }


    public void setColumnConstraints(int num, int row, int col) {
        Constraint constraint;
        if (num == 0) {
            constraint = new LessThan(false);

        } else if (num == 1) {
            constraint = new GreaterThan(false);
        } else {
            constraint = new EmptyConstraint(false);
        }
        colConstraint[row][col] = constraint;
    }

    public void fillPuzzle(int numb, int cons) {
        int countNumb = 0;
        int countCons = 0;
        while (countNumb < numb) {
            int randInt = random.nextInt(grid.length) + 1;
            int randRow = random.nextInt(grid.length);
            int randCol = random.nextInt(grid.length);
            if (grid[randRow][randCol].getSquare() == 0) {
                setSquare(randInt, randRow, randCol);
                countNumb++;
            }
        }
        while (countCons < cons) {
            int randInt = random.nextInt(2);

            if (randInt == 0) {
                int randrow = random.nextInt(rowConstraint.length);
                int randcol = random.nextInt(rowConstraint.length - 1);
                int randSym = random.nextInt(2);
                if (rowConstraint[randrow][randcol] == null) {
                    setRowConstraints(randSym, randrow, randcol);
                    countCons++;
                }
            }
            if (randInt == 1) {
                int randrow = random.nextInt(colConstraint.length);
                int randcol = random.nextInt(colConstraint.length + 1);
                int randSym = random.nextInt(2);
                if (colConstraint[randrow][randcol] == null) {
                    setColumnConstraints(randSym, randrow, randcol);
                    countCons++;
                }
            }
        }
        for (int row = 0; row < rowConstraint.length; row++) {
            for (int col = 0; col < rowConstraint.length - 1; col++) {
                if (rowConstraint[row][col] == null) {
                    rowConstraint[row][col] = new EmptyConstraint(true);
                }
            }
        }
        for (int row = 0; row < colConstraint.length; row++) {
            for (int col = 0; col < colConstraint.length + 1; col++) {
                if (colConstraint[row][col] == null) {
                    colConstraint[row][col] = new EmptyConstraint(false);
                }
            }
        } while (!this.isLegal());
    }

    public String displayString() {
        String s = "";
        int cellWidth = String.valueOf(grid.length).length();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                for (int i = 0; i < cellWidth; i++) {
                    s += "-";
                }
                s += "-- ";
            }
            s += "\n";
            for (int col = 0; col < grid.length; col++) {
                s += "|";
                int val = getGrid()[row][col].getSquare();
                int startingValue;
                if (val != 0) {
                    startingValue = String.valueOf(val).length();
                } else {
                    startingValue = 0;
                }
                for (int i = startingValue; i < cellWidth; i++) {
                    s += " ";
                }
                if (startingValue > 0) {
                    s += val;
                }
                s += "|";
                if (col < grid.length - 1) {
                    s += getRowConstraints(row, col);
                } else {
                    s += "\n";
                }
            }
            for (int col = 0; col < grid.length; col++) {
                for (int i = 0; i < cellWidth; i++) {
                    s += "-";
                }
                s += "-- ";
            }
            s += "\n";
            if (row < grid.length - 1) {
                for (int col = 0; col < grid.length; col++) {
                    s += " ";
                    for (int i = 1; i < cellWidth; i++) {
                        s += " ";
                    }
                    s += getColumnConstraints(row, col);
                    s += "  ";
                }
                s += "\n";
            }
        }
        System.out.println(s);
        return s;
    }

    private boolean hasDuplicateInRows() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                FutoshikiSquare i = grid[row][col];
                if (i.getSquare() != 0) {
                    for (int j = col + 1; j < grid.length; j++) {
                        if (i.getSquare() == grid[row][j].getSquare()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private boolean hasDuplicateInColumns() {
        for (int col = 0; col < grid.length; col++) {
            for (int row = 0; row < grid[col].length; row++) {
                FutoshikiSquare i = grid[row][col];
                if (i.getSquare() != 0) {
                    for (int k = row + 1; k < grid.length; k++) {
                        if (i.getSquare() == grid[k][col].getSquare()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean bigValues() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                if (grid[row][col].getSquare() > grid.length) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean smallValues() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                if (grid[row][col].getSquare() < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean correctColumnConstraints() {
        for (int col = 0; col < colConstraint.length - 1; col++) {
            for (int row = 0; row < rowConstraint.length - 1; row++) {
                if (colConstraint[row][col].getSymbol().equals(new GreaterThan(false).getSymbol())) {
                    if (grid[row][col].getSquare() > grid[row + 1][col].getSquare()) {
                        return false;
                    }
                } else if (colConstraint[row][col].getSymbol().equals(new LessThan(false).getSymbol())) {
                    if (grid[row][col].getSquare() < grid[row + 1][col].getSquare()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean correctRowConstraints() {
        for (int row = 0; row < rowConstraint.length - 1; row++) {
            for (int col = 0; col < colConstraint.length - 1; col++) {
                if (rowConstraint[row][col].getSymbol() == new GreaterThan(true).getSymbol()) {
                    if (grid[row][col].getSquare() > grid[row][col + 1].getSquare()) {
                        return false;
                    }
                } else if (rowConstraint[row][col].getSymbol() == new LessThan(true).getSymbol()) {
                    if (grid[row][col].getSquare() < grid[row][col + 1].getSquare()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isLegal() {
        if (!hasDuplicateInRows() && !hasDuplicateInColumns() && correctRowConstraints() && correctColumnConstraints() && !bigValues() && !smallValues()) {
            return true;
        } else {
            return false;
        }
    }

    public void legalPuzzle() {
        fillPuzzle(5, 5);
        while (!isLegal()) {
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid.length; col++) {
                    grid[row][col] = new FutoshikiSquare(row, col);
                }
            }
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid.length; col++) {
                    grid[row][col] = new FutoshikiSquare(row, col);
                }
            }
            getProblems();
            //fillPuzzle(5, 5);
        }
    }
    
    private boolean isIllegalValue(int val) {
        return (val < 1) || (val > gridSize);
    }

    public void getProblems() {
        if (!isLegal()) {
            if (hasDuplicateInRows()) {
                System.out.println("There is a duplicate number in a row.");
            }
            if (hasDuplicateInColumns() == true) {
                System.out.println("There is a duplicate number in a column.");
            }

            if (bigValues() == true) {
                System.out.println("There is a value in the grid that is bigger than the grid length.");
            }

            if (smallValues() == true) {
                System.out.println("The smallest value has to be 1. This value is too small.");
            }

            if (correctColumnConstraints() == false) {
                System.out.println("A column constraint has been violated.");
            }

            if (correctRowConstraints() == false) {
                System.out.println("A row constraint has been violated.");
            } else {

            }
        }
    }
    
    public boolean isPuzzleComplete() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                if (grid != null && isLegal()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FutoshikiPuzzle2 f2 = new FutoshikiPuzzle2(5);
        f2.fillPuzzle(5, 5);
        f2.displayString();
        f2.legalPuzzle();
        //f2.isLegal();
        //f2.getProblems();

    }

}

//column/row constraint is legal if there is a constraint plus 2 blank spaces not satisfied
