/**
Gonna solve the maze I guess
I hope

now using knowledge of compass opposites

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
        System.out.println(solns);
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
                    inMaze.go( Maze.oppositeOf(dir));
                }
                inMaze.dropA(Maze.STEPPING_STONE);
                break;
        }

    return solns;
    }
}
