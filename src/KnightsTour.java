//Jonathan Ma
// CS 241
/**
 * ******************************
 
* Copyright 2015: smanna@cpp.edu
 * CS 241
 * PA4
 * ******************************
 * STUDENT: You need to write this class. You MUST 
 * implement the public and private methods as shown. 
 * Feel free to include your own private fields and 
 * methods as well if you find it necessary.
 * 
 * Also make sure to comment your code, otherwise 2 points 
 * will be deducted.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class KnightsTour {

	// private fields
	private int size;
	private Graph graph;
	private String path;
	private int[][] mapping;
	private boolean complete = true;

	// feel free to add your own private fields and methods here

	// constructor
	public KnightsTour(int size) {
		this.size = size;
		mapping(size);
		createGraph();
	}

	/**
	 * Creates a graph representing all of the places on the board and the
	 * possible moves between them.
	 * 
	 * @return Graph representation of the board.
	 */
	private Graph createGraph() {
		graph = new Graph(size * size); // create an n by n graph
		ArrayList<int[]> moves; // prepare to add edges by retrieving coordinates of specific label
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int vertex = mapping[i][j];
				moves = getLegalMoves(i, j); // get all possible moves from that vertex
				for (int k = 0; k < moves.size(); k++) {
					int x[] = moves.get(k); // get all the neighbor using the coordinates from getLegal moves
					graph.addEdge(vertex, mapping[x[0]][x[1]]);
				}
			}
		}
		return graph;
	}

	/**
	 * Use dfs to traverse the graph until the target is found. This indicates
	 * that we have found a path from v to target. Returns a String of each
	 * label visited (beginning with v, ending with target). Uses the global
	 * variable completed along with the Vertex field, visited, to determine if
	 * it should recurse.
	 * 
	 * @param v
	 *            Current Vertex
	 * @param target
	 *            Vertex that is the destination of the path.
	 * @return String of all vertices (labels) visited
	 */
	private String dfs(Vertex v, Vertex target) {
		v.setVisited(true); // mark visited
		path += v.getLabel() + " "; // add current label to path
		Vertex[] neighbor = v.getNeighbors(); // get the neighbors of current vertex
		for (int i = 0; i < neighbor.length; i++) {
			if (target.visited()) return path; // return if current node is the target
			if (neighbor[i].visited() != complete) { // look for unvisited neighbors
				dfs(neighbor[i], target); 
			}
		}
		return path;
	}

	/**
	 * Returns the correct label for the xy coordinate parameter.
	 * 
	 * @param x
	 *            Coordinate x
	 * @param y
	 *            Coordinate y
	 * @return int representing the spot on the board.
	 */
	private int identify(int x, int y) {
		return mapping[x][y];
	}

	/**
	 * Tests the coordinate to see if it is on the board.
	 * 
	 * @param coordinate
	 * @return true if move is legal; false otherwise
	 */
	private boolean isLegal(int coordinate) {
		if (coordinate < 0 || coordinate > size - 1) 
			return false;
		return true;
	}

	/**
	 * Returns a list of all the possible (legal) moves from the current spot.
	 * 
	 * @param x
	 *            Current x coordinate
	 * @param y
	 *            Current y coordinate
	 * @return List of all possible legal moves from current spot
	 */
	private ArrayList<int[]> getLegalMoves(int x, int y) {
		ArrayList<int[]> moves = new ArrayList<int[]>();
		int xCoordinate[] = { -1, -1, -2, -2, 1, 1, 2, 2 };
		int yCoordinate[] = { -2, 2, -1, 1, -2, 2, -1, 1 };

		for (int i = 0; i < xCoordinate.length; i++) {
			int xResult = x + xCoordinate[i]; // add offset x
			int yResult = y + yCoordinate[i]; // add offset y
			if (isLegal(xResult) && isLegal(yResult)) { // check if new coordinate is legal
				int temp[] = { xResult, yResult}; 
				moves.add(temp);
			}
		}
		return moves;
	}

	/**
	 * Returns a mapping of (x,y) coordinate to vertex label
	 * 
	 * @param size
	 *            the size of one side of the square board.
	 * @return
	 */
	private int[][] mapping(int size) {
		mapping = new int[size][size];
		int label = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				mapping[i][j] = label; // create label for each entry 
				label++;
			}
		}
		return mapping; 
	}

	/**
	 * Finds path from starting label to target label using graph.
	 * 
	 * @param starting
	 *            label of first Vertex
	 * @param target
	 *            label of target Vertex
	 * @return String of every Vertex visited on path from start to target.
	 */
	public String findPath(int starting, int target) {
		path = ""; // reset the path string 
		graph.resetVisit(); // unmark the visited nodes
		path = " ["+ dfs(graph.getVertex(starting), graph.getVertex(target)).trim() + "]";
		return complete + path;
	}
}
