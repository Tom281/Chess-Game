public class Bishop extends Piece {
    //constructor
    public Bishop(String color, int posX, int posY) {
        super(color, posX, posY);
        if(color == "white") {
            disp = "♝";
        } else {
            disp = "♗";
        }
    }
    @Override
    public boolean moveValid(int newX, int newY) {
        //Make sure Bishop moves diagonally.
        if(Math.abs(newX - posX) != Math.abs(newY - posY)) {
            return false;
        }
        //check for collisions
        if(collision(newX, newY)) {
            return false;
        }
        return true;
    }
}
