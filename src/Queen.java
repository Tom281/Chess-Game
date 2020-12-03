public class Queen extends Piece {
    //constructor
    public Queen(String color, int posX, int posY) {
        super(color, posX, posY);
        if(color == "white") {
            disp = "♛";
        } else {
            disp = "♕";
        }
    }
    @Override
    public boolean moveValid(int newX, int newY) {
        //Only allow the queen to move vertically, horizontally, or diagonally.
        if(newX != posX && newY != posY && Math.abs(newX - posX) != Math.abs(newY - posY)) {
            System.out.println(1);
            return false;
        }
        //check for collisions
        if(collision(newX, newY)) {
            System.out.println(2);
            return false;
        }
        return true;
    }
}
