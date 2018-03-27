## statement of problem
The problem is to find a way to return the boolean value of the statement "there exists one path through the maze from a designated start point to a designated end point."
A maze is defined as a grid with barriers.
Looping through possible paths is disallowed.
## personnel
Yoshiya Ohno, Jonathan Singer, Mark Winter
## recursive abstraction
When I am asked to find a path from a starting tile to an ending tile in a maze, the recursive abstraction can find a path from a tile adjacent to the starting tile to the same ending tile in that maze.
## algorithm
Maze predefined, with a set ending position
solveMaze(startingPosition):

      if endingPosition tile is the startingPosition:
      
         record
	 
         return
	 
      if startingPosition is a barrier:
      
         return
	 
      place barrier on starting position
      
      for each adjacent tile:
      
          solveMaze(thatTile)
	  
      remove barrier on starting position
      
