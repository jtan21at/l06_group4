package L05;

import java.util.ArrayList;
import java.util.List;

public class SymbolGrid {
	
	private static char[] SYMBOLS = {'~', '!', '@', '#', '$', '^', '&', '*'};



	// NOTE: 
	//   Do not change this method signature.
	//   This is method calls another recursive method, but it should
	//   not call itself.
	public static void findAllPaths(char[][] grid, char[] sequence) {

		List<Cell> list = new ArrayList<Cell>();

		for(int i = 1; i < grid.length; i++) {
			for (int k = 1; k < grid[i].length; k++) {
				if (grid[i][k] == sequence[0]) {
					Cell c1 = new Cell(i, k);

					list.add(c1);

					findPathsAt(grid, c1.getY(), c1.getX(), list, sequence, 0);


				}

				for(int a = 0; a < list.size(); a ++){
					System.out.print(list.get(a).toString() + " ");
				}

			}

		}


			System.out.println("\n--- finished searching");
	}
	
	// TO DO: 
	//     Implement recursive method with backtracking
	//     
	//     NOTE: You may change the list of parameters here 
	private static boolean findPathsAt(char[][] grid, int x, int y, List<Cell> path,
									   char[] sequence, int index) {

		//base case (if the cell does not match the character at the index it will stop the recursion
		if(grid[x][y] != sequence[index + 1]) {
			path.clear();
			return false;
		}
		else {
			//searches the right side of the cell
			if (findPathsAt(grid, x + 1, y, path, sequence, index + 1)) {
				//stops indexOutOfBounds
				if (x > 4) {
					path.clear();
					return false;
				}
				//adds cell to the list
				Cell c2 = new Cell(x, y);
				path.add(c2);

				return true;


			}

			//searches the bottom right corner
			if(findPathsAt(grid, x + 1, y + 1, path, sequence, index + 1)){
				//stops indexOutOfBounds
				if(x > 4 || y > 4) {
					path.clear();
					return false;
				}
				//adds cell to the list
				Cell c3 = new Cell(x,y);
				path.add(c3);

				return true;


			}

			//searches the bottom
			if(findPathsAt(grid, x, y + 1, path, sequence, index + 1)){
				//stops indexOutOfBounds
				if(y > 4){
					path.clear();
					return false;
				}
				//adds cell to the list
				Cell c4 = new Cell(x,y);
				path.add(c4);

				return true;
			}

			//searches bottom left corner
			if(findPathsAt(grid,x - 1, y + 1, path, sequence, index + 1)){
				//stops indexOutOfBounds
				if (x < 4 || y > 4){
					path.clear();
					return false;
				}
				//adds cell to the list
				Cell c5 = new Cell(x,y);
				path.add(c5);

				return true;
			}

			//searches left side
			if(findPathsAt(grid, x - 1, y, path, sequence, index + 1)){
				//stops indexOutOfBounds
				if(x < 4) {
					path.clear();
					return false;
				}
				//adds cell to the list
				Cell c6 = new Cell(x,y);
				path.add(c6);

				return true;
			}

			//searches top left side
			if(findPathsAt(grid, x - 1, y - 1, path, sequence, index + 1 )){
				//stops indexOutOfBounds
				if (x < 4 || y < 4){
					path.clear();
					return false;
				}
				//adds cell to the list
				Cell c7 = new Cell(x,y);
				path.add(c7);

				return true;
			}

			//searches top side
			if(findPathsAt(grid, x, y - 1, path, sequence, index + 1)){
				//stops indexOutOfBounds
				if(y < 4){
					path.clear();
					return false;
				}
				//adds cell to the list
				Cell c8 = new Cell(x,y);
				path.add(c8);

				return true;
			}



		}

		return false;
	}

	// ........................................................................................................................
	public static void main(String[] args) {
		char[][] grid = randomSymbolGrid(7);
		displaySymbolGrid(grid);
		
		System.out.println();
		
		char[] seq = randomSymbolSequence(4);
		System.out.print("sequence: "); 
		System.out.println(seq);
		
		System.out.println("\npaths:");
		findAllPaths(grid, seq);

	}

	//================================================================================================================

	/* Helper methods below -- you shouldn't need to alter them */
	
	private static char[] randomSymbolSequence(int length) {
		char[] sequence = new char[length];
		for(int i = 0; i < length; i++) {
			sequence[i] = SYMBOLS[(int)(Math.random()*SYMBOLS.length)];
		}
		return sequence;
	}
	
	private static char[][] randomSymbolGrid(int size) {
		char[][] grid = new char[size][size];
		for(int r = 0; r < size; r++) {
			grid[r] = randomSymbolSequence(size);
		}
		return grid;
	}
	
	private static void displaySymbolGrid(char[][] grid) {
		// Display column indices
		System.out.print("\n    ");
		for(int i = 0; i < grid.length; i++) {
			System.out.print(i + "  ");
		}
		System.out.println();
		
		// Display grid
		for(int r = 0; r < grid.length; r++) {
			// Display row index
			System.out.print("  " + r + " ");
			for(int c = 0; c < grid[r].length; c++) {
				System.out.print(grid[r][c] + "  ");
			}
			System.out.println();
		}
	}

}

/* Represents a cell on a grid -- just a convenient way of 
 * packaging a pair of indices  */
class Cell {
	int r, c;
	
	Cell(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public boolean equals(Object o) {
		Cell cell = (Cell) o;
		return this.r == cell.r && this.c == cell.c;
	}

	public int getX() {
		return c;
	}

	public int getY() {
		return r;
	}

	@Override
	public String toString() {
		return "(" + r + ", " + c + ")";
	}
}
