import java.util.HashSet;

public class Agent {
    public Agent() { }

    public Boolean bruteForceSolve(Board sudoku, Boolean showSteps) {
        // Brute force the solution
        int dim = sudoku.getValues().length;

        for (int x=0; x < dim; x++) {
            for (int y=0; y < dim; y++) {
                if (!sudoku.isFilled(x, y)) {
                    HashSet<Integer> validVals = sudoku.validValues(x, y);
                
                    for (int v: validVals) {
                        sudoku.insertValue(v, x, y);

                        if (showSteps) {
                            System.out.println(sudoku);
                            try { Thread.sleep(40); } catch (InterruptedException e) { }
                        }
                        
                        if (bruteForceSolve(sudoku, showSteps)) { return true; }

                        // Undo the previous (failed) solution
                        sudoku.insertValue(0, x, y);;
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
