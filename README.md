# Pool Table
This code is designed to model a pool table game. The pool game is played on a rectangular table with different colour of balls on it. This pool table has 6 pockets into which the balls can fall. All the balls are randomly placed on the table at the beginning of the game. Among all balls, there is a ball being the white cue ball. A player can only hit the white cue ball with the cue stick. If the balls approach the side of the table, they will bounce back. Balls also bounce off other balls. This is a single player game and is won when all the balls, other than the cue ball, are in the pockets. Game is lost when the white cue ball falls into any of the pockets. The score is calculated when a ball falls into a pocket. Duration of the game is clocked until all balls are in the pocket.


These are the functionality of classes:
- The App class is where the overall application is being run. When the cue ball falls
into the pocket when playing the game, the game resets whereas when all the
colored balls fall into the pocket it prints ‘win and bye.’

- The GameWindow class is where the game attributes are drawn. When the user
clicks the cue ball and drags it in a certain direction, a line is shown to notify the
direction and extent of the drag. When the user releases the mouse click, the ball
flies in the opposite direction of the drag. The power is determined by the extent to of
the ball is dragged, however, the ball’s velocity cannot go above 50 due to a limit that
prevents the ball from flying too fast to the extent that the user cannot see the ball
bouncing each other and going into pockets.
- The Ball class contains information about the ball such as the color, x and y position,
x and y velocity, and mass. It also contains how the ball moves in the x and y
directions.
- The BallPit class is closely associated with the Ball class as it holds all details of the
existing balls in the game. It mainly deals with checking the movement of each ball moving in 
the x and y direction, handling the bouncing off of walls, and collision with other balls.
- The Collision class contains functions to check collisions between balls and
calculate new velocities for them. Also, it checks if the ball has fallen into any of the
pockets.
- The Reader classes, including ConfigReader, TableReader, and BallReader, read
the information of the game off of the JSON file given.
- The Builder classes which includes Builder, BallBuilder, and BallDircetor, helps in
building balls using information of each attribute from the JSON file read by the
BallReader.
- The Strategy classes consist of Strategy, StrategyContext, CueBallStrategy, and
ColoredBallStrategy, which deal with the restart or the end of the overall game.
When the cue ball goes into one of the pockets, the game is restarted whereas when
all the colored balls fall into the pockets, the game ends.
- The PoolTable class import all the JSON file data from the TableReader class and
stores them. It also contains the pocket details which are of type Pocket. Details such
as the x and y coordinates and the sides of the pockets can be found using the Pocket class.
