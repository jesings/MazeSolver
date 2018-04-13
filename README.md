## personnel
Yoshiya Ohno, Jonathan Singer, Mark Winter
## known bugs
No known bugs (Not for lack of trying to find any, though). It doesn't work with mazes of size 0 but will work otherwise with coordinates outside the maze (and just do nothing). It won't work if all 3 inputs on the command line aren't inserted? I don't know what else we could put here even to sort of classify as a bug.
## version0 ~~planned~~ features
Cont total number of paths (record increments a solution counter)
## version1 planned features
Keep track of solutions in a List

Keep track of path lengths

Use `Displayer` to display all solutions once the solving is complete
## version*n* wish list
Add directional tracers so the explorer's exact path can be visualized.

Sort final solutions by length

"Screw around with maze geometry"

Add maze features
## statement of problem
The problem is to find a way to return the boolean value of the statement "there exists one path through the maze from a designated start point to a designated end point."

A maze is defined as a grid with barriers.
## recursive abstraction
When I am asked to find a path from a starting tile to an ending tile in a maze, the recursive abstraction can find a path from a tile adjacent to the starting tile to the same ending tile in that maze.
## base case
Tile to find path from is either of

* designated end point

* barrier
## pseudocode description of algorithm
Maze predefined, with a set ending position
```
solveMaze(startingPosition):
      if endingPosition tile is the startingPosition:
         record
         return
      if startingPosition is a barrier:
         return
      Take a snapshot
      place barrier on starting position
      for each adjacent tile:
          solveMaze(thatTile)
      Restore from snapshot
```
## class(es), with fields and methods
