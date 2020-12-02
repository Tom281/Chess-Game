public class Pawn extends Piece {
    private int maxHorizontalMove = 1;
    private int maxVerticalMove = 1;
    public Pawn(String color, int posX, int posY) {
        super(color, posX, posY);
    }
    @Override
    public boolean moveValid(int newX, int newY) {
        int horizontalMovement = newX - posX;
        int verticalMovement = newY - posY;
        Piece target = ChessGame.board[newX][newY];
        //check if the pawn is moving the wrong direction.
        if(direction == "up" && verticalMovement < 0) {
            return false;
        }
        if(direction == "down" && verticalMovement > 0) {
            return false;
        }
        //check if the pawn moves too far.
        if(Math.abs(horizontalMovement) > maxHorizontalMove) {
            return false;
        }
        if(Math.abs(verticalMovement) > maxVerticalMove) {
            return false;
        }
        //Only allow pawn to move diagonally if there is a piece to capture.
        if(verticalMovement != 0) {
            if(posX > 0 && horizontalMovement < 0 && (target == null || target.color == color)) {
                return false;
            }
            if(posX > 0 && horizontalMovement > 0 && (target == null || target.color == color)) {
                return false;
            }
        } else if(verticalMovement != 0) {
            if(posX > 0 && horizontalMovement < 0 && (target == null || target.color == color)) {
                return false;
            }
            if(posX > 0 && horizontalMovement > 0 && (target == null || target.color == color)) {
                return false;
            }
        }
        //Stop pawn from moving horizontally
        if(verticalMovement == 0 && horizontalMovement != 0) {
            return false;
        }
        return true;
    }
}
