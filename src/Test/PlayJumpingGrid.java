package Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayJumpingGrid {

	//private static boolean walkthru;
	private static int recursionDepth;
	private static Scanner input;
	private static boolean isSolvable;
	private static ArrayList<String> solutions =
		new ArrayList<String>();

	private static final char NORTH = 'N';
	private static final char EAST = 'E';
	private static final char SOUTH = 'S';
	private static final char WEST = 'W';
	private static final char BACKTRACK = 'B';
	private static final char HELP = 'H';
	private static final char GIVE_UP = 'G';

	public static void main(String[] args) {
		input = new Scanner(System.in);

		System.out.println("J U M P I N  G R I D\n");

		System.out.print("Enter grid size between 2 and 10: ");
		int gridSize = input.nextInt();
		if(2 > gridSize || gridSize > 10) {
			System.out.println("Why can't you follow directions?! Bye.");
			System.exit(1);
		}

		System.out.print("\nEnter min and max values from 1 to 9 for filling the grid: ");
		int minValue = input.nextInt();
		int maxValue = input.nextInt();
		if(maxValue < minValue) {
			System.out.println("You're impossible! Bye.");
			System.exit(2);
		} else if (1 > minValue || maxValue > 9){
			System.out.println("Really? Bye.");
			System.exit(3);
		}

		System.out.print("\nEnter the maximum number of jumps: ");
		int maxJumps = input.nextInt();
		if (maxJumps < 1) {
			System.out.println("I'm not even going to try that! Bye.");
			System.exit(4);
		}
		//System.out.println("\nHere is your grid:");
		int[][] grid;

		int numTries = 100;
		do {
			numTries--;
		  grid = initJumpGrid(gridSize, minValue, maxValue);
			findAllPaths(grid, maxJumps);
	 	} while (!isSolvable && numTries > 0);

		if(solutions.size() == 0) {
			System.out.println("I could not generate solvable grid...");
			System.out.println("Please try again with different parameters.");
			System.exit(4);
		}


		displayOptions();
		play(grid, maxJumps);

		input.close();

		System.out.println("\nHere is the grid you played:\n");
		displayJumpGrid(grid);
		System.out.println("\nAnd here are all the solutions: \n");
		displaySolutions();
	}

	private static void play(int[][]grid, int maxJumps) {
		JGCell here = new JGCell(0, 0);
		ArrayList<JGCell> path = new ArrayList<JGCell>();
		path.add(here);

		while(!isDestination(here, grid)) {
			//displayVisibleGrid(grid, here);
			System.out.print("\nYour path: ");
			displayPath(path);
			if(path.size() > maxJumps) {
				System.out.println("(It is too long! You should backtrack...)");
			}
			System.out.println("You can jump by " +
			                   grid[here.r][here.c] +
												 " positions.");
			here = move(grid, path);
		}

		displayPath(path);
		System.out.println("You did it in " + (path.size() - 1) + " jumps!");

		System.out.println("\nhere is the grid:\n");
		displayJumpGrid(grid);
		System.out.println("\nand here are all the solutions I found");
		displaySolutions();

	}

	private static boolean isDestination(JGCell pos, int[][] grid) {
		return (pos.r == grid.length - 1 && pos.c == grid[0].length - 1);
	}

	private static JGCell move (int[][] grid,
	               			        ArrayList<JGCell> path) {

		System.out.print("\nEnter your choice: ");
		char option = Character.toUpperCase(input.next().charAt(0));

		switch(option) {
			case NORTH:
			case EAST:
			case SOUTH:
			case WEST:
				jump(grid, path, option);
				break;
			case BACKTRACK:
				if (path.size() > 1) {
					path.remove(path.size() - 1);
				}
				else {
				 System.out.println("\nYou can't backtrack! You are at the start...");
				}
				break;
			case HELP:
				displayOptions(); break;
			case GIVE_UP:
				giveUp(grid); break;
			default:
					System.out.println("\n" + option + " is not a recognized option");
		}

		return path.get(path.size() - 1);
	}

	private static void giveUp(int[][] grid) {
		input.close();
		System.out.println("\nOK quitter... Here is the grid:\n");
		displayJumpGrid(grid);
		System.out.println("\nAnd here are all the solutions:\n");
		displaySolutions();
		System.exit(5);
	}

	private static void displayOptions() {
		System.out.println("\njumping grid options:");
		System.out.println("\t H - see these options");
		System.out.println("\t G - give up");
		System.out.println("\t B - backtrack to your last position");
		System.out.println("\t N - go North");
		System.out.println("\t E - go East");
		System.out.println("\t S - go South");
		System.out.println("\t W - go West");
		System.out.println();

	}

	private static void jump(int[][] grid,
	                         ArrayList<JGCell> path,
													 char option) {
	  JGCell here = path.get(path.size() - 1);
		int jumpLength = grid[here.r][here.c];

		JGCell newPos;

		switch(option) {
			case NORTH:
				newPos = new JGCell(here.r - jumpLength, here.c); break;
			case EAST:
				newPos = new JGCell(here.r, here.c + jumpLength); break;
			case SOUTH:
				newPos = new JGCell(here.r + jumpLength, here.c); break;
			case WEST:
				newPos = new JGCell(here.r, here.c - jumpLength); break;
			case BACKTRACK:
				newPos = path.remove(path.size() - 1);
				if(path.size() == 0)
					System.out.println("\nCan't backtrack from starting position...");
				break;
			default:
				newPos = here; // should not happen
		}

		if (isOutsideGrid(newPos, grid)) {
			System.out.println("\nThat would be outside the grid!");
		}
		else {
			path.add(newPos);
		}

	}

	private static boolean isOutsideGrid(JGCell pos, int[][] grid) {
		return (0 > pos.r || pos.r >= grid.length ||
			      0 > pos.c || pos.c >= grid[0].length);

	}

	public static void findAllPaths(int[][] grid, int maxJumps) {
		findPathsAt(grid, new JGCell(0, 0), new ArrayList<JGCell>(), maxJumps);
	}

	private static void findPathsAt(int[][] grid,
	                                JGCell cell,
																	ArrayList<JGCell> path,
																	int maxJumps) {

		int r = cell.r;
		int c = cell.c;

		if (isOutsideGrid(cell, grid)) {
			return;
		}
		if (path.contains(cell)) {
			return;
		}

		path.add(cell);

		if (isDestination(cell, grid)) {
			isSolvable = true;
			solutions.add(pathToString(path));

		} else if (path.size() > maxJumps) {
			// in this case we will backtrack

		} else {

			int jump = grid[r][c];

			findPathsAt(grid, new JGCell(r - jump, c), path, maxJumps);
			findPathsAt(grid, new JGCell(r, c + jump), path, maxJumps);
			findPathsAt(grid, new JGCell(r + jump, c), path, maxJumps);
			findPathsAt(grid, new JGCell(r, c - jump), path, maxJumps);
		}

		path.remove(path.size() - 1);
	}

	private static String pathToString(ArrayList<JGCell> path) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (; i < path.size() - 1; i++) {
			sb.append(path.get(i));
			sb.append("-->");
		}
		sb.append(path.get(i));
		return sb.toString();
	}

	private static void displayPath(ArrayList<JGCell> path) {

		System.out.println(pathToString(path));
	}

	private static int[][] initJumpGrid(int size, int min, int max) {

		int[][] grid = new int[size][size];
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				grid[r][c] = (int)(min + Math.random() * (max - min + 1));
			}
		}
		return grid;
	}

	private static void displayJumpGrid(int[][] grid) {

		System.out.print("\n    ");
		for(int i = 0; i < grid.length; i++) {
			System.out.print(i + "  ");
		}
		System.out.print("\n   ");
		for(int i = 0; i < grid.length; i++) {
			System.out.print("---");
		}
		System.out.println();

		for(int r = 0; r < grid.length; r++) {
			System.out.print(" " + r + "| ");
			for(int c = 0; c < grid[r].length; c++) {
				System.out.print(grid[r][c] + "  ");
			}
			System.out.println();
		}
	}

	private static void displaySolutions() {
		for(String path : solutions)
			System.out.println(path);
	}


	private static void displayVisibleGrid(int[][] grid, JGCell here) {

		System.out.print("\n    ");
		for(int i = 0; i < grid.length; i++) {
			System.out.print(i + "  ");
		}
		System.out.print("\n   ");
		for(int i = 0; i < grid.length; i++) {
			System.out.print("---");
		}
		System.out.println();

		for(int r = 0; r < grid.length; r++) {
			System.out.print(" " + r + "| ");
			for(int c = 0; c < grid[r].length; c++) {
				//int cellValue = grid[r][c];
				if(here.r == r && here.c == c)
					//System.out.print("\u001B[43m" + cellValue + "\u001B[0m"+ "  ");
					System.out.print(grid[r][c] + "  ");
				else
					System.out.print("   ");
			}
			System.out.println();
		}
	}
}


class JGCell {
	int r, c;

	JGCell(int r, int c) {
		this.r = r;
		this.c = c;
	}

	// does this cell represent the lower left corner
	// in a grid of given size?
	boolean isEnd(int size) {
		return (r == size - 1) && (c == size - 1);
	}

	@Override
	public boolean equals(Object o) {
		JGCell cell = (JGCell) o;
		return this.r == cell.r && this.c == cell.c;
	}

	@Override
	public String toString() {
		return "(" + r + "," + c + ")";
	}
}
