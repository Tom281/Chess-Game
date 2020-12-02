public class Piece {
    char disp;
    int posX;
    int posY;
    String color;
    String direction;
    //Constructor
    public Piece(String color, int posX, int posY) {
        this.color = color;
        if(this.color == "white") {
            direction = "down";
        } else {
            direction = "up";
        }
        this.posX = posX;
        this.posY = posY;
    }
    public void move(int newX, int newY) {
        if(moveValid(newX, newY)) {
            //set the new position
            ChessGame.board[newX][newY] = ChessGame.board[posX][posY];
            //clear the old position
            ChessGame.board[posX][posY] = null;
            //update piece coordinates
            posX = newX;
            posY = newY;
        }
    }
    public boolean moveValid(int newX, int newY) {
        //Defined in subclass
        return true;
    }
    public void kill() {
        //remove reference of this piece on the chessboard.
        ChessGame.board[posX][posY] = null;
    }
    public void capture(int targX, int targY) {
        Piece target = ChessGame.board[targX][targY];
        //
        if(target.color == "white") {
            ChessGame.capturedWhite.add(ChessGame.capturedWhite.size(), target);
        } else {
            ChessGame.capturedBlack.add(ChessGame.capturedBlack.size(), target);
        }
        //kill the Piece you capture
        target.kill();
    }
}
