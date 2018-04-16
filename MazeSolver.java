/**
Gonna solve the maze I guess
I hope

now using knowledge of compass opposites

*/

public class MazeSolver{

    // Fields
    Maze maze;
    Displayer display;

    /**
     construct a MazeSolver,
     capable of solving the given maze
     and outputting to a @display -sized display.
     */
    public MazeSolver( String filename
                     , int explorerRank, int explorerFile
                     , int displaySize
                     ) throws java.io.FileNotFoundException {

        maze = new Maze( filename, explorerRank, explorerFile);
        display = new Displayer(displaySize);
     }

    /**
     Solves the maze given by the command-line args.
     I've lost track of methods a bit which is not great
     But I do think it makes sense for this class to have its own main() method
     So there's only this one file to run from the command line.
    */
    public static void main( String[] commandLine) throws java.io.FileNotFoundException {
        MazeSolver jef = new MazeSolver( commandLine[0]
                                         , Integer.parseInt( commandLine[1])
                                         , Integer.parseInt( commandLine[2])
                                         , Integer.parseInt( commandLine[3])
                                       );
        jef.display.atTopOfWindow( "total solutions: " + Integer.toString(jef.solve()));
    }

    // I'm getting a bit lost in the static/non-static divide which is not good
    
    private int solve() {
        return solve( maze, 0);
    }
    
    /////cheeki compass over here no peeking/////////////
    private static final Maze.Directions[] compass = new Maze.Directions[] {Maze.Directions.EAST,Maze.Directions.NORTH,Maze.Directions.WEST,Maze.Directions.SOUTH};
    
    /**
    doing the big solve
    */
    
    private int solve(Maze inMaze, int solns) {
        display.atTopOfWindow( 
                             ""
                             + solns
                             + System.lineSeparator()
                             + inMaze
                             );

        switch(inMaze.explorerIsOnA()) {

            case Maze.WALL:
                break;
            case Maze.TRACER:
                break;

            case Maze.TREASURE:
                solns++;
                // System.out.println("got one");    
                break;

            case Maze.STEPPING_STONE:
                inMaze.dropA(Maze.TRACER);
                for (Maze.Directions dir : compass ) {
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
