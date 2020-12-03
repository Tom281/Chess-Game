public class Knight extends Piece {
    //constructor
    public Knight(String color, int posX, int posY) {
        super(color, posX, posY);
        if(color == "white") {
            disp = "♞";
        } else {
            disp = "♘";
        }
    }
}
