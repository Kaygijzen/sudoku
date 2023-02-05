import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
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
}

