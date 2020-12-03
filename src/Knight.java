public class Knight extends Piece {
    //constructor
    private int maxHorizontalMove = 2;
    private int maxVerticalMove = 2;
    public Knight(String color, int posX, int posY) {
        super(color, posX, posY);
        if(color == "white") {
            disp = "♞";
        } else {
            disp = "♘";
        }
    }
    @Override
    public boolean moveValid(int newX, int newY) {
        //Only allow Knight to move in an 'L' shape.
        int changeX = Math.abs(newX - posX);
        int changeY = Math.abs(newY - posY);
        if(changeX > maxHorizontalMove || changeY > maxVerticalMove) {
            return false;
        }
        if(changeX + changeY != 3) {
            return false;
        }
        //Stop Knight from hitting a piece on it's own side.
        if(ChessGame.board[newX][newY] != null && ChessGame.board[newX][newY].color == color) {
            return false;
        }
        return true;
    }
}
