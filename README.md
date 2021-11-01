# TicTacToe

### Prerequisites:

Make sure that you have Apache Ant installed and there are no PATH or versioning issues with JAVA or ANT.

### Building the project:

1. `cd tictactoe`

2. `ant clean`

3. `ant compile`

4. `ant test`

### How to run (after building):

1. `cd tictactoe` -- if you are not already in the `tictactoe` directory

2. `java -cp bin RowGameApp $rows $cols`, where $rows and $cols are respectively the rows and columns of the `tictactoe` game.  
   For example: `java -cp bin RowGameApp 3 3`

### How to clean up:

1. `ant clean`