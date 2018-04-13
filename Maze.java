/**
  Represent a Maze with an Explorer in it
  
  A "MazeTerminal" is...
    o  a wall element; or
    o  a treasure; or
    o  a stepping stone.
  
  A "Maze" is...
    o  a MazeTerminal; or
    o  a stepping stone with a Maze as any of its 4 neighbors
  plus an optional explorer positioned on any element of the Maze.
 */
import java.util.Scanner;

public class Maze {

    // MazeTerminal named constants
    public final static int TREASURE = 0;
    public final static int WALL = 1;
    public final static int STEPPING_STONE = 2;
    public final static int TRACER = 3;
    
    // directions that can be searched
    public final static int EAST =  1;
    public final static int NORTH = 3;
    public final static int WEST =  -1;
    public final static int SOUTH = -3;
       /* Values are pretty arbitrary. 
        * East/West and North/South are opposites to make oppositeOf() simpler.
        * values |1| and |3| are chosen in case diagonals are needed;
        *   NE/SW become |4| and NW/SE become |2|.
        */
    
    private int[][] maze;
    private final static int MAX_RANKS = 64;
    private int rankCount;  // number of ranks in a constructed Maze
    
    public Vector explorerPosition;  // see Vector inner class, below

    /**
      Construct an instance from the contents of a file.
      For v0, maze is rectangular, with every line having the same length.
     */
    public Maze( String sourceFilename
               , int explorerRank, int explorerFile
               ) throws java.io.FileNotFoundException {

        /* Construct the maze array one rank at a time, in case
           we ever allow non-rectangular mazes  */
        maze = new int[ MAX_RANKS][];

        Scanner sc = new Scanner( new java.io.File( sourceFilename));
        sc.useDelimiter("");  // Whitespaces are data, not delimiters.

        // process the maze file
        while( sc.hasNextLine() ) {
            int rank = rankCount++;
            /* So rankCount == last rank +1, as usual.
               That is, rankCount is one larger than the number of
               the last-used rank.
             */
            String line = sc.nextLine();
            // System.out.println( "|" + line + "|");
            
            maze[ rank] = new int[ line.length()];

            // Convert the input line into maze elements.
            for( int file = 0; file < line.length(); file++ ) {
                String inChar = line.substring( file, file+1);
                int element;  // value destined for maze array
                if(      inChar.equals("0"))  element = TREASURE;
                else if( inChar.equals("*"))  element = STEPPING_STONE;
                // spaces and unrecognised characters are walls
                else                          element = WALL;
                maze[ rank][ file] = element;
            }
        }
        
        explorerPosition = new Vector( explorerRank, explorerFile);
        // // for debugging: report explorer's location
        // System.out.println( "explorer at " + explorerPosition.rank
                          // + ", " +           explorerPosition.file);
    }


    /**
      Copy-construct an instance.
      Deep copy of all instance fields.
     */
    public Maze( Maze old) {
        // Copy the explorer's position (code by Holmes is asserted to work)
        explorerPosition = new Vector( old.explorerPosition);
        rankCount = old.rankCount;
        maze = new int[rankCount][];
        for(int rank = 0;rank<maze.length;rank++){
            System.out.println(rank);
            maze[rank] = new int[old.maze[rank].length];
            for(int file = 0;file<maze[rank].length;file++){
                maze[rank][file] = old.maze[rank][file];
            }
        }
    }


    /**
      @return a string representing of this instance
     */
    public String toString() {
        
        /* characters that represent elements of the maze,
           indexed by the numbers used to represent elements
          */
        final String outChar = "0 *X";  // no explorer here ///////////////EXTRA Zs
        final String exOnTop = "!Ee3";  /* explorer on top of /////////////FOR TESTING
           treasure, wall, stepping stone, etc. *////////////////////////////////////////WHAT THE HELL THAT FIXED IT

        // build string for top and bottom separators
        String aboveAndBelow = "-";
        for( int file = 0; file < maze[0].length; file++)
            aboveAndBelow += "-";
        aboveAndBelow += "-" + System.lineSeparator();
        
        // process maze[][], explorer
        String result = aboveAndBelow;
        for( int rank = 0; rank < rankCount; rank++) {
            result += "|";
            for( int file = 0; file < maze[ rank].length; file++) {
                int elem = maze[ rank][ file];
                
                // choose from the appropriate character set,
                if(    explorerPosition.eval() != null
                    && explorerPosition.equals( rank, file)
                  )
                     result += exOnTop.substring( elem, elem+1);
                else result += outChar.substring( elem, elem+1);
            }
            result += "|" + System.lineSeparator();
        }
        return   result + aboveAndBelow;
    }


    /**
      Move the Explorer a step in the indicated direction.
      Attempting to position the explorer outside the maze means
      it has no position.
      
      @precondition: explorer starts in a valid position
     */
    public void go( int direction)  { 
        switch( direction) {
            case EAST:
                explorerPosition = explorerPosition.add( 0, 1);
                break;
            case NORTH:
                explorerPosition = explorerPosition.add( -1, 0);
                break;
            case WEST:
                explorerPosition = explorerPosition.add( 0, -1);
                break;
            case SOUTH:
                explorerPosition = explorerPosition.add( 1, 0);
                break;
        }
    }

    /**
      @return the direction cardinally opposite @direction.
      NORTH to SOUTH, etc.
      */
    public int oppositeOf( int direction) {
        return -1 * direction;
    }


    /**
      Modify the maze to have @mazeElement in the explorer's position.
      Nix dropping treasure.
     */
    public void dropA( int mazeElement) {
        if( mazeElement != TREASURE)
            maze[ explorerPosition.rank][ explorerPosition.file] = mazeElement;
    }


    /**
      @return the MazeElement that the explorer is on.
              When the explorer's position is null, return WALL
              because the user-programmer's code is expected to benefit
              from that equivalence.
     */
    public int explorerIsOnA() {
        if( explorerPosition.eval() == null) return WALL;
        else return maze[ explorerPosition.rank][ explorerPosition.file];
    }


    /**
       a pair of rank & file that can represent...
         o  a displacement from the current location
         o  a location in a maze, being a displacement from (0,0)
       Vectors outside the maze are legal and do exist, but eval() to null,
       representing their out-of-bounds-ness.
     */
    public class Vector {
        private int rank, file;
        
        // The no-arg constructor produces [0, 0] 
        private Vector() {}

        // copy-constructor
        private Vector( Vector old) {
            rank = old.rank;
            file = old.file;
        }

        private Vector( int rank, int file) {
            this.rank = rank;
            this.file = file;
        }

        private Vector add( int ranks, int files) { 
            rank += ranks;
            file += files;
            
            // // for debugging: report resulting position
            // System.out.println( "sum: " + rank + " / " + rankCount
                              // + ", " +    file + " / " + maze[ rank].length );
            
            return this;
        }
		
        private Vector eval() {
            if(    0 <= rank && rank < rankCount
                && 0 <= file && file < maze[ rank].length
              ) return this;
            else return null;
        }

        /**
          @return whether this Vector matches the parameters
         */
        private boolean equals( int rank, int file) {
            return this.rank == rank && this.file == file;
        }
    }
}
