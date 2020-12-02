import java.util.*;

public class ChessGame {
    public static Piece[][] board = new Piece[7][7];
    public static ArrayList<Piece> capturedWhite = new ArrayList<Piece>();
    public static ArrayList<Piece> capturedBlack = new ArrayList<Piece>();
    public void set() {
        board[3][0] = new Pawn("Black", 3, 0);
        board[2][1] = new Pawn("white", 2, 1);
    }
    public void play() {
        System.out.println(board[3][0].moveValid(2, 1));
    }
}
