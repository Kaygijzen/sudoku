import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        Board sudoku = new Board(new File("./data/board.txt"));
        System.out.println(sudoku);
    }
}
