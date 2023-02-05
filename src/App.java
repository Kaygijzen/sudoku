import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        Board sudoku = new Board(new File("./data/board.txt"));

        Agent agent = new Agent();
        agent.bruteForceSolve(sudoku, false);

        // Print solution
        System.out.println(sudoku);
    }
}
