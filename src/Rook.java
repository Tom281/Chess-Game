public class Rook extends Piece {
    //constructor
    public Rook(String color, int posX, int posY) {
        super(color, posX, posY);
        if(color == "white") {
            disp = "♜";
        } else {
            disp = "♖";
        }
    }
    @Override
    public boolean moveValid(int newX, int newY) {
        //Make sure Rook moves in a straight line.
        if(newX != posX && newY != posY) {
            return false;
        }
        //check for collisions.
        if(collision(newX, newY)) {
            return false;
        }
        return true;
    }
}
