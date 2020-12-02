public class Queen extends Piece {
    public Queen(String color, int posX, int posY) {
        super(color, posX, posY);
        if(color == "white") {
            disp = "♛";
        } else {
            disp = "♕";
        }
    }
}
