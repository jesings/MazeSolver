## personnel
Yoshiya Ohno, Jonathan Singer, Mark Winter
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
## version0 planned features
Cont total number of paths (record increments a solution counter)
## version*n* wish list
Instead of placing a barrier behind the explorer, instead place something else so that the path the explorer takes is recorded. 
