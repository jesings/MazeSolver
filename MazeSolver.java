/**
Gonna solve the maze I guess
I hope


no snapshots because that is infinity gay


*/

public abstract class MazeSolver{
	/////////////god damn im going to need a wrapper arent I 
	/**
	sets up the big solve
	*/
		
	public static boolean solve(Maze inMaze){
		return solve(inMaze, 0) > 0;
	}	
	
	/////cheeki compass over here no peeking/////////////
	private static final int[] compass = new int[] {Maze.EAST,Maze.NORTH,Maze.WEST,Maze.SOUTH};
	
	/**
	doing the big solve
	*/
	
	private static int solve(Maze inMaze, int solns) {
		System.out.println(inMaze.explorerPosition.rank);
		System.out.println(inMaze.explorerPosition.file); ////////made these public because your vector class doesnt work how I want it to
		System.out.println(solns); //honestly could make a getter but it is already very late
		System.out.println(inMaze.toString());
		switch(inMaze.explorerIsOnA()) {
			case Maze.WALL:
				break;
			case Maze.TRACER:
				break;
			case Maze.TREASURE:
				solns++;
				System.out.println("got one");	
				break;
			case Maze.STEPPING_STONE:
				inMaze.dropA(Maze.TRACER);
				for (int dir : compass ) {
					inMaze.go(dir);
					solns = solve(inMaze, solns);
					switch(dir) { ///improve///could be imporoved with uniformly reversible directions: -2E, -1N, 1S, 2W
						case Maze.EAST:
							inMaze.go(Maze.WEST);
							break;
						case Maze.NORTH:
							inMaze.go(Maze.SOUTH);
							break;
						case Maze.WEST:
							inMaze.go(Maze.EAST);
							break;
						case Maze.SOUTH: /////////////I really dont like how these are different widths
							inMaze.go(Maze.NORTH);
							break;
					}//////////god this is cursed. ill do reversible directions soon tm
				}
				inMaze.dropA(Maze.STEPPING_STONE);
				break;
		}
	return solns;
	}
}