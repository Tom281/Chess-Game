public class Bishop extends Piece {
    public Bishop(String color, int posX, int posY) {
        super(color, posX, posY);
        if(color == "white") {
            disp = "♝";
        } else {
            disp = "♗";
        }
    }
}
