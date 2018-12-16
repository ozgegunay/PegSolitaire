import java.util.ArrayList;

//Board class to hold the location of the board
public class Board {

    //Holds the pawn coordinates and states of them
    public Location [][] board = new Location[7][7];

    //Getter and Setter for the Board
    public Location[][] getBoard() {
        return board;
    }

    public void setBoard(Location[][] board) {
        this.board = board;
    }
}
