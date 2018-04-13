/**

uses the maze

wow

Im gonna be a real boy
its worth the practice anyway
*/

public abstract class UserSolver{
    //uhh
    public static void main(String[] commandLine) throws java.io.FileNotFoundException {
        Maze maze = new Maze( commandLine[0]
                            , Integer.parseInt( commandLine[1])
                            , Integer.parseInt( commandLine[2])
                            );

        MazeSolver.solve( maze);
    }
    //cool
}
