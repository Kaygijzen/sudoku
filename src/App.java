import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        Board sudoku = new Board(new File("./data/extremeDifficulty.txt"));

        System.out.println(sudoku);

        Agent agent = new Agent();
        agent.bruteForceSolve(sudoku, false);

        // Print solution
        System.out.println(sudoku);
    }
}
