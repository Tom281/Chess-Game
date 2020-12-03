public class King extends Piece {
    private int maxHorizontalMove = 1;
    private int maxVerticalMove = 1;
    //constructor
    public King(String color, int posX, int posY) {
        super(color, posX, posY);
        if(color == "white") {
            disp = "♚";
        } else {
            disp = "♔";
        }
    }
    @Override
    public boolean moveValid(int newX, int newY) {
        int horizontalMovement = posX - newX;
        int verticalMovement = posY - newY;
        //Make that sure the piece does not move too far.
        if(Math.abs(horizontalMovement) > maxHorizontalMove) {
            return false;
        }
        if(Math.abs(verticalMovement) > maxVerticalMove) {
            return false;
        }
        //make sure that king isn't too close to the other king.
        if(color == "white") {
            //Create a reference to the other king
            Piece otherKing = ChessGame.board[ChessGame.findKing("black")[0]][ChessGame.findKing("black")[1]];
            //If the other king is within one square from the new position...
            if(otherKing != null) {
                if(Math.abs(otherKing.posX - newX) <= 1 && Math.abs(otherKing.posY - newY) <= 1) {
                    return false;
                }
            }
        } else {
            //Create a reference to the other king
            Piece otherKing = ChessGame.board[ChessGame.findKing("white")[0]][ChessGame.findKing("white")[1]];
            //If the other king is within one square from the new position...
            if(otherKing != null) {
                if(Math.abs(otherKing.posX - newX) <= 1 && Math.abs(otherKing.posY - newY) <= 1) {
                    return false;
                }
            }
        }
        //check for collisions
        if(collision(newX, newY)) {
            return false;
        }
        return true;
    }
}
