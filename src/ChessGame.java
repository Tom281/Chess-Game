import java.util.*;

public class ChessGame {
    public static Piece[][] board = new Piece[7][7];
    public static ArrayList<Piece> capturedWhite = new ArrayList<Piece>();
    public static ArrayList<Piece> capturedBlack = new ArrayList<Piece>();
    public void set() {
    }
    public void play() {
    }
    //used for detecting checks, checkmates, and stopping kings from moving too close.
    public static int[] findKing(String color) {
        int[] kingLoc = new int[2];
        for(int row = 0; row < 6; row++) {
            for(int col = 0; col < 6; col++) {
                if(board[col][row] instanceof King && board[col][row].color == color) {
                    kingLoc[0] = col;
                    kingLoc[1] = row;
                    break;
                }
            }
        }
        return kingLoc;
    }
}
