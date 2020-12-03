public class Piece {
    String disp;
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
    //Cycles through the spaces between the current space and the target space to check for collisions.
    public boolean collision(int newX, int newY) {
        //if there are spaces between the current position and the target...
        if(Math.abs(newX - posX) > 0 || Math.abs(newY - posY) > 0) {
            //scenarios for different directions
            //diagonal, up right
            if(newX > posX && newY > posY) {
                for(int i = 1; i < Math.abs(newX - posX); i++) {
                    if(ChessGame.board[posX + i][posY + i] != null) {
                        return true;
                    }
                }
            }
            //diagonal, up left
            if(newX < posX && newY > posY) {
                for(int i = 1; i < Math.abs(newX - posX); i++) {
                    if(ChessGame.board[posX - i][posY + i] != null) {
                        return true;
                    }
                }
            }
            //diagonal, down right
            if(newX > posX && newY < posY) {
                for(int i = 1; i < Math.abs(newX - posX); i++) {
                    if(ChessGame.board[posX + i][posY - i] != null) {
                        return true;
                    }
                }
            }
            //diagonal, down left
            if(newX < posX && newY < posY) {
                for(int i = 1; i < Math.abs(newX - posX); i++) {
                    if(ChessGame.board[posX - i][posY - i] != null) {
                        return true;
                    }
                }
            }
            //up
            if(newY > posY) {
                for(int i = 1; i < Math.abs(newY - posY); i++) {
                    if(ChessGame.board[posX][posY + i] != null) {
                        return true;
                    }
                }
            }
            //down
            if(newY < posY) {
                for(int i = 1; i < Math.abs(newY - posY); i++) {
                    if(ChessGame.board[posX][posY - i] != null) {
                        return true;
                    }
                }
            }
            //left
            if(newX < posX) {
                for(int i = 1; i < Math.abs(newX - posX); i++) {
                    if(ChessGame.board[posX - i][posY] != null) {
                        return true;
                    }
                }
            }
            //right
            if(newX > posX) {
                for(int i = 1; i < Math.abs(newX - posX); i++) {
                    if(ChessGame.board[posX + i][posY] != null) {
                        return true;
                    }
                }
            }
        }
        //Check the target space for a piece from the same side.
        if(ChessGame.board[newX][newY] != null && ChessGame.board[newX][newY].color != color) {
            return true;
        }
        return false;
    }
}
