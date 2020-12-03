import java.util.*;

public class ChessGame {
    public static Piece[][] board = new Piece[8][8];
    public static ArrayList<Piece> capturedWhite = new ArrayList<Piece>();
    public static ArrayList<Piece> capturedBlack = new ArrayList<Piece>();
    public static String turn = "white";
    public static String currentMove = "        ";
    private static Scanner scan = new Scanner(System.in);
    private static String letters = "ABCDEFGH";
    private static String numbers = "12345678";
    private static boolean checkMate = false;
    private static int whiteCheckTime = 0;
    private static int blackCheckTime = 0;
    private static String error = "";
    private static String winner = "";
    public void set() {
        //black pieces
        board[0][0] = new Rook("black", 0, 0);
        board[1][0] = new Knight("black", 1, 0);
        board[2][0] = new Bishop("black", 2, 0);
        board[3][0] = new King("black", 3, 0);
        board[4][0] = new Queen("black", 4, 0);
        board[5][0] = new Bishop("black", 5, 0);
        board[6][0] = new Knight("black", 6, 0);
        board[7][0] = new Rook("black", 7, 0);
        board[0][1] = new Pawn("black", 0, 1);
        board[1][1] = new Pawn("black", 1, 1);
        board[2][1] = new Pawn("black", 2, 1);
        board[3][1] = new Pawn("black", 3, 1);
        board[4][1] = new Pawn("black", 4, 1);
        board[5][1] = new Pawn("black", 5, 1);
        board[6][1] = new Pawn("black", 6, 1);
        board[7][1] = new Pawn("black", 7, 1);
        //white pieces
        board[0][7] = new Rook("white", 0, 7);
        board[1][7] = new Knight("white", 1, 7);
        board[2][7] = new Bishop("white", 2, 7);
        board[3][7] = new King("white", 3, 7);
        board[4][7] = new Queen("white", 4, 7);
        board[5][7] = new Bishop("white", 5, 7);
        board[6][7] = new Knight("white", 6, 7);
        board[7][7] = new Rook("white", 7, 7);
        board[0][6] = new Pawn("white", 0, 6);
        board[1][6] = new Pawn("white", 1, 6);
        board[2][6] = new Pawn("white", 2, 6);
        board[3][6] = new Pawn("white", 3, 6);
        board[4][6] = new Pawn("white", 4, 6);
        board[5][6] = new Pawn("white", 5, 6);
        board[6][6] = new Pawn("white", 6, 6);
        board[7][6] = new Pawn("white", 7, 6);
    }
    public void play() {
        while(!checkMate) {
            check();
            dispBoard();
            int[] moveData = prompt();
            if(moveData[0] != -1) {
                Piece selected = board[moveData[0]][moveData[1]];
                if(selected == null) {
                    error = "There is no piece in this position.";
                } else if(selected.color != turn) {
                    error = "This is not your piece.";
                } else if(!selected.moveValid(moveData[2], moveData[3])) {
                    error = "This piece can't move there.";
                } else if(board[moveData[2]][moveData[3]] != null) {
                    selected.capture(moveData[2], moveData[3]);
                    error = "";
                    if(turn == "white") {
                        turn = "black";
                    } else {
                        turn = "white";
                    }
                } else {
                    selected.move(moveData[2], moveData[3]);
                    error = "";
                    if(turn == "white") {
                        turn = "black";
                    } else {
                        turn = "white";
                    }
                }
            } else {
                error = "Invalid input.";
            }

        }
    }
    public void menu() {
        System.out.println();
        System.out.println();
        System.out.println(" /$$$$$$$$ /$$$$$$$$ /$$$$$$$  /$$      /$$ /$$$$$$ /$$   /$$  /$$$$$$  /$$              /$$$$$$  /$$   /$$ /$$$$$$$$  /$$$$$$   /$$$$$$ ");
        System.out.println("|__  $$__/| $$_____/| $$__  $$| $$$    /$$$|_  $$_/| $$$ | $$ /$$__  $$| $$             /$$__  $$| $$  | $$| $$_____/ /$$__  $$ /$$__  $$");
        System.out.println("   | $$   | $$      | $$  \\ $$| $$$$  /$$$$  | $$  | $$$$| $$| $$  \\ $$| $$            | $$  \\__/| $$  | $$| $$      | $$  \\__/| $$  \\__/");
        System.out.println("   | $$   | $$$$$   | $$$$$$$/| $$ $$/$$ $$  | $$  | $$ $$ $$| $$$$$$$$| $$            | $$      | $$$$$$$$| $$$$$   |  $$$$$$ |  $$$$$$ ");
        System.out.println("   | $$   | $$__/   | $$__  $$| $$  $$$| $$  | $$  | $$  $$$$| $$__  $$| $$            | $$      | $$__  $$| $$__/    \\____  $$ \\____  $$");
        System.out.println("   | $$   | $$      | $$  \\ $$| $$\\  $ | $$  | $$  | $$\\  $$$| $$  | $$| $$            | $$    $$| $$  | $$| $$       /$$  \\ $$ /$$  \\ $$");
        System.out.println("   | $$   | $$$$$$$$| $$  | $$| $$ \\/  | $$ /$$$$$$| $$ \\  $$| $$  | $$| $$$$$$$$      |  $$$$$$/| $$  | $$| $$$$$$$$|  $$$$$$/|  $$$$$$/");
        System.out.println("   |__/   |________/|__/  |__/|__/     |__/|______/|__/  \\__/|__/  |__/|________/       \\______/ |__/  |__/|________/ \\______/  \\______/ ");
        System.out.println();
        System.out.println("_________________________________________________________________________________________________________________________________________");
        System.out.println();
        System.out.println("                                                            Press Enter to Begin");
        scan.nextLine();
    }
    //used for detecting checks, checkmates, and stopping kings from moving too close.
    public static int[] findKing(String color) {
        int[] kingLoc = new int[2];
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                if(board[col][row] instanceof King && board[col][row].color == color) {
                    kingLoc[0] = col;
                    kingLoc[1] = row;
                    break;
                }
            }
        }
        return kingLoc;
    }
    public static int[] prompt() {
        int[] moveData = new int[4];
        System.out.println(turn.substring(0,1).toUpperCase() + turn.substring(1) + " move.");
        currentMove = scan.nextLine();
        if(currentMove.matches("^[A-H][1-8]\sto\s[A-H][1-8]$")) {
            moveData[0] = letters.indexOf(currentMove.charAt(0));
            moveData[1] = numbers.indexOf(currentMove.charAt(1));
            moveData[2] = letters.indexOf(currentMove.charAt(6));
            moveData[3] = numbers.indexOf(currentMove.charAt(7));
        } else {
            moveData[0] = -1;
        }
        return moveData;
    }
    public static void dispBoard() {
        System.out.println("____________________________________________________________________");
        System.out.println();
        System.out.println("                         | TERMINAL CHESS |                         ");
        System.out.println("____________________________________________________________________");
        if(turn == "white") {
            System.out.println("████████████████████████████ White turn ████████████████████████████");
        } else {
            System.out.println("                             Black turn                             ");
        }
        System.out.println((turn == "white")? "████████████████████████████████████████████████████████████████████" : "____________________________________________________________________");
        System.out.println();
        System.out.println("             Board               |           Information            ");
        System.out.println("____________________________________________________________________");
        System.out.println();
        System.out.println("      ________________________   |                                  ");
        for(int row = 7; row >= 0; row--) {
            System.out.print("  " + String.valueOf(row + 1) + "  ");
            for(int col = 0; col < 7; col++) {
                if(board[col][row] != null) {
                    System.out.print("|" + board[col][row].disp + "_");
                } else {
                    System.out.print("|__");
                }
            }
            if(row != 1 && row != 3) {
                if(board[7][row] != null) {
                    System.out.println("|" + board[7][row].disp + "_|   |");
                } else {
                    System.out.println("|__|   |");
                } 
            } else {
                if(board[7][row] != null) {
                    System.out.print("|" + board[7][row].disp + "_|   |");
                } else {
                    System.out.print("|__|   |");
                } 
            }
            if(row == 1) {
                System.out.print(" White captured: ");
                if(capturedBlack.size() != 0) {
                    for(int i = 0; i < capturedBlack.size() - 1; i++) {
                        System.out.print(capturedBlack.get(i).disp + ", ");
                    }
                    System.out.println(capturedBlack.get(capturedBlack.size() - 1).disp);
                } else {
                    System.out.println();
                }
            }
            if(row == 3) {
                System.out.print(" Black captured: ");
                if(capturedWhite.size() != 0) {
                    for(int i = 0; i < capturedWhite.size() - 1; i++) {
                        System.out.print(capturedWhite.get(i).disp + ", ");
                    }
                    System.out.println(capturedWhite.get(capturedWhite.size() - 1).disp);
                } else {
                    System.out.println();
                }
            }
        }
        System.out.println("                                 |");
        System.out.println("      A  B  C  D  E  F  G  H     |");
        System.out.println("                                 |");
        System.out.println("            " + currentMove + "             |");
        System.out.println("                                 |");
        System.out.print("                                 |");
        System.out.println(" White king in check: " + ((whiteCheckTime > 0)? "Yes." : "No"));
        System.out.println("                                 |");
        System.out.print("                                 |");
        System.out.println(" Black king in check: " + ((blackCheckTime > 0)? "Yes." : "No"));
        System.out.println("                                 |");
        System.out.println("                                 |");
        System.out.println("                                 |");
        System.out.println("                                 |");
        System.out.println("                                 |");
        System.out.println("                                 |");
        System.out.println("                                 |");
        System.out.println("                                 |");
        System.out.println("                                 |");
        System.out.println("                                 |");
        System.out.println("                                 |");
        System.out.println("____________________________________________________________________");
        System.out.println(error != ""? "Error: " + error : "");
        System.out.println("____________________________________________________________________");
    }
    public static void check() {
        if(blackCheckTime > 1 || whiteCheckTime > 1) {
            checkMate = true;
            winner = (blackCheckTime > 1)? "White" : "Black";
        }
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                Piece selected = board[col][row];
                if(selected != null) {
                    int[] kingPos;
                    if(selected.color == "white") {
                        kingPos = findKing("black");
                    } else {
                        kingPos = findKing("white");
                    }
                    if(selected.moveValid(kingPos[0],kingPos[1])) {
                        if(selected.color == "white") {
                            blackCheckTime++;
                        } else {
                            whiteCheckTime++;
                        }
                    } else {
                        if(selected.color == "white") {
                            blackCheckTime = 0;
                        } else {
                            whiteCheckTime = 0;
                        } 
                    }
                }
            }
        }
    }
}
