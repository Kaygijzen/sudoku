import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Board {
    private int[][] values;

    public Board(File file) throws IOException {
		//Initiates a board from a file
        RandomAccessFile r = null;
		try {
			r = new RandomAccessFile(file, "r");
			StringTokenizer dims = new StringTokenizer(r.readLine(), " ");
			int w = Integer.parseInt(dims.nextToken());
			int h = Integer.parseInt(dims.nextToken());
			System.out.println("Reading " + w + "x" + h + " board from " + file);
            values = new int[9][9];
        
			int y = 0;
			String line = r.readLine();
			while (line != null && !line.trim().equals("")) {
				if (y >= h)
				{
					System.out.println("Error reading board: nr of rows wrong");
					System.exit(0);
				}
				line=line.trim();
				if (line.length() != w)
				{
					System.out.println("Error reading board: row of wrong length, "+line+" not length "+line.length());
					System.exit(0);
				}
				for (int x=0;x<line.length();x++)
				{	
                    values[y][x] = Character.getNumericValue(line.charAt(x));
				}
				line=r.readLine();
				y++;
			}
			if (y != h)
			{
				System.out.println("Error reading board: nr of rows wrong");
				System.exit(0);
			}

			r.close();
		} catch (Exception e) {
			System.out.println("Error reading board "+file);
			e.printStackTrace();
			System.exit(0);
		} finally {
			if (r != null) r.close();
		}
	}

	public void insertValue(int v, int x, int y) {
		values[x][y] = v;
	}

	public HashSet<Integer> validValues(int x, int y) {
		HashSet<Integer> invalidVals = new HashSet<Integer>();

		// Check row
		for (int v : getRowValues(x))
		{
			invalidVals.add(v);
		}
		// Check column
		for (int v : getColValues(y)) { 
			invalidVals.add(v);
		}
		// Check grid
		for (int v: getGridValues(x, y)) {
			invalidVals.add(v);
		}

		// Return all valid values
		HashSet<Integer> validVals = new HashSet<>();
		for (int i = 1; i < 10; i++) { validVals.add(i); }
		validVals.removeAll(invalidVals);
		return validVals;
	}

	public boolean isFilled(int x, int y) {
		return values[x][y] > 0;
	}

	public int[][] getValues() {
		return values;
	}

    public String toString() {
        String string = new String();
        for (int x=0; x < 9; x++) {
			if (x != 0 && x % 3 == 0) { string += "------+-------+------\n";}
            for (int y=0; y < 9; y++) {
                if (y != 0 && y % 3 == 0) { string += "| ";}
				if (values[x][y] == 0) { string += "  ";}
                else {string += Integer.toString(values[x][y]) + " ";}
            }
            string += "\n";
        }
        return string;
    }

	/**
	 * SECTION: Private helper methods
	 */
	private List<Integer> getRowValues(int row) {
		int[] vals = Arrays.stream(values[row]).filter(v -> v > 0).toArray();
		List<Integer> lst = new ArrayList<Integer>();
		for (int v: vals) { lst.add(v); }
		return lst;
	}

	private List<Integer> getColValues(int col) {
		int[] vals = new int[values.length];
		for (int i=0; i<values.length; i++){
			vals[i] = values[i][col];
		}
		List<Integer> lst = new ArrayList<Integer>();
		for (int v: vals) { if (v > 0) lst.add(v); }
		return lst;
	}

	private List<Integer> getGridValues(int x, int y) {
		int xGrid = x / 3;
		int yGrid = y / 3;
		ArrayList<Integer> lst = new ArrayList<Integer>();
		for (int iX=0; iX<3; iX++) {
			for (int iY=0; iY<3; iY++) {
				int _x = xGrid*3 + iX;
				int _y = yGrid*3 + iY;
				int v = values[_x][_y];
				if (v>0) lst.add(v);
			}
		}
		return lst;
	}
}

