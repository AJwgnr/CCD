package com.ovgu.ccd.applogic;

import com.ovgu.ccd.gui.threeplayer.*;
import com.ovgu.ccd.pieces.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that implements a board for 3 players
 */
public class ThreePlayerChessboard implements IBoard {
    /**
     * internal representation of A coordinate
     */
    public static final int A = 0;
    /**
     * internal representation of B coordinate
     */
    public static final int B = 1;
    /**
     * internal representation of C coordinate
     */
    public static final int C = 2;
    /**
     * internal representation of D coordinate
     */
    public static final int D = 3;
    /**
     * internal representation of E coordinate
     */
    public static final int E = 4;
    /**
     * internal representation of F coordinate
     */
    public static final int F = 5;
    /**
     * internal representation of G coordinate
     */
    public static final int G = 6;
    /**
     * internal representation of H coordinate
     */
    public static final int H = 7;
    /**
     * internal representation of I coordinate
     */
    public static final int I = 8;
    /**
     * internal representation of J coordinate
     */
    public static final int J = 9;
    /**
     * internal representation of K coordinate
     */
    public static final int K = 10;
    /**
     * internal representation of L coordinate
     */
    public static final int L = 11;

    /**
     * number of columns of the matrix
     */
    public static final int COLUMNS = 12;

    /**
     * internal representation of the board
     */
    private Square[][] matrix = null;

    /**
     * list of white squares in the middle of the board
     */
    public ArrayList<Square> WHITE_ROSETTE = new ArrayList<Square>(Arrays.asList(new Square(8, I, null), new Square(4, D, null), new Square(3, E, null)));

    /**
     * list of black squares in the middle of the board
     */
    public ArrayList<Square> BLACK_ROSETTE = new ArrayList<Square>(Arrays.asList(new Square(4, I, null), new Square(3, D, null), new Square(8, E, null)));

    /**
     * white king
     */
    private King kingWhite;
    /**
     * black king
     */
    private King kingBlack;
    /**
     * grey king
     */
    private King kingGrey;
    /**
     * used to keep track of last pawn that moved two squares
     */
    private Pawn twoSquareMovedPawn = null;

    /**
     * white player
     */
    private Player whitePlayer = null;
    /**
     * grey player
     */
    private Player greyPlayer = null;
    /**
     * black player
     */
    private Player blackPlayer = null;

    /**
     * list of blacks pawns
     */
    public ArrayList<Piece> blackPawns = new ArrayList<>();
    /**
     * list of white pawns
     */
    public ArrayList<Piece> whitePawns = new ArrayList<>();
    /**
     * list of grey pawns
     */
    public ArrayList<Piece> greyPawns = new ArrayList<>();

    /**
     * representation of the grid
     */
    private ChessboardGrid chessboardGrid = null;

    /**
     * flag to control if white spy was activated
     */
    private boolean isWhiteSpyActive = false;
    /**
     * flag to control if black spy was activated
     */
    private boolean isBlackSpyActive = false;
    /**
     * flag to control if grey spy was activated
     */
    private boolean isGreySpyActive = false;

    public ArrayList<Piece> blackPieces = new ArrayList<>();
    public ArrayList<Piece> whitePieces = new ArrayList<>();
    public ArrayList<Piece> greyPieces = new ArrayList<>();

    /*------------------------------
    #-------------MATRIX------------
    #-------------------------------
    #     |0|1|2|3|4|5|6|7|8|9|10|11
    #     |A|B|C|D|E|F|G|H|I|J|K|L|
    #   ---------------------------
    #   0 | | | | | | | | |X|X|X|X|
    #   ---------------------------
    #   1 | | | | | | | | |X|X|X|X|
    #   ---------------------------
    #   2 | | | | | | | | |X|X|X|X|
    #   ---------------------------
    #   3 | | | | | | | | |X|X|X|X|
    #   ---------------------------
    #   4 | | | | |X|X|X|X| | | | |
    #   ---------------------------
    #   5 | | | | |X|X|X|X| | | | |
    #   ---------------------------
    #   6 | | | | |X|X|X|X| | | | |
    #   ---------------------------
    #   7 | | | | |X|X|X|X| | | | |
    #   ---------------------------
    #   8 |X|X|X|X| | | | | | | | |
    #   ---------------------------
    #   9 |X|X|X|X| | | | | | | | |
    #   ---------------------------
    #   10|X|X|X|X| | | | | | | | |
    #   ---------------------------
    #   11|X|X|X|X| | | | | | | | |
    #   ---------------------------
    */

    /**
     * creates an empty board
     */
    public ThreePlayerChessboard() {
        initSquareMatrix();
        setInvalidSquares();
    }

    /**
     * @param grid to be used
     */
    public ThreePlayerChessboard(ChessboardGrid grid) {
        this.chessboardGrid = grid;
        initPlayers();
        initSquareMatrix();
        setInvalidSquares();
        initPieceStartPositions();
        redrawPieces();
    }

    /**
     * initialize the players
     */
    public void initPlayers() {
        this.whitePlayer = new Player("Mr.White", Player.Colors.WHITE.name());
        this.greyPlayer = new Player("Mr.Grey", Player.Colors.GREY.name());
        this.blackPlayer = new Player("Mr.Black", Player.Colors.BLACK.name());
    }


    /**
     * returns a array of all players
     *
     * @return  array of players
     */
    public Player[] getAllPlayers()
    {
        Player playerArray[] = {this.whitePlayer, this.greyPlayer, this.blackPlayer};
        return playerArray;
    }

    /**
     * Mark the corresponding squares in the matrix as invalid
     */
    private void setInvalidSquares() {
        if (matrix != null) {
            for (int i = 0; i <= 3; i++) {
                for (int j = 8; j <= 11; j++) {
                    if (matrix[i][j] != null)
                        matrix[i][j].setInvalid(true);
                }
            }

            for (int i = 4; i <= 7; i++) {
                for (int j = 4; j <= 7; j++) {
                    if (matrix[i][j] != null)
                        matrix[i][j].setInvalid(true);
                }
            }


            for (int i = 8; i <= 11; i++) {
                for (int j = 0; j <= 3; j++) {
                    if (matrix[i][j] != null)
                        matrix[i][j].setInvalid(true);
                }
            }
        }
    }

    /**
     * initializes the squares in the matrix
     */
    private void initSquareMatrix() {
        this.matrix = new Square[COLUMNS][COLUMNS];
        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < COLUMNS; y++) {
                if (this.chessboardGrid != null && chessboardGrid.getSquare(x, y) != null)
                    matrix[x][y] = chessboardGrid.getSquare(x, y).getBoardSquare();
                else
                    matrix[x][y] = new Square(x, y, null);
            }
        }
    }

    /**
     * assigns all pieces to their start positions
     */
    public void initPieceStartPositions() {
        if (this.matrix != null)
        {
            Piece piece;
            // white player start positions
            piece = PieceFactory.getPiece(this, this.whitePlayer, Piece.PieceTypes.ROOK);
            this.matrix[0][A].setPiece(piece);
            whitePieces.add(piece);
            piece = PieceFactory.getPiece(this, this.whitePlayer, Piece.PieceTypes.KNIGHT);
            this.matrix[0][B].setPiece(piece);
            whitePieces.add(piece);
            piece = PieceFactory.getPiece(this, this.whitePlayer, Piece.PieceTypes.BISHOP);
            this.matrix[0][C].setPiece(piece);
            whitePieces.add(piece);
            piece = PieceFactory.getPiece(this, this.whitePlayer, Piece.PieceTypes.QUEEN);
            this.matrix[0][D].setPiece(piece);
            whitePieces.add(piece);
            King whiteKing = (King) PieceFactory.getPiece(this, this.whitePlayer, Piece.PieceTypes.KING);
            this.matrix[0][E].setPiece(whiteKing);
            whitePieces.add(whiteKing);
            setKingWhite(whiteKing);
            piece = PieceFactory.getPiece(this, this.whitePlayer, Piece.PieceTypes.BISHOP);
            this.matrix[0][F].setPiece(piece);
            whitePieces.add(piece);
            piece = PieceFactory.getPiece(this, this.whitePlayer, Piece.PieceTypes.KNIGHT);
            this.matrix[0][G].setPiece(piece);
            whitePieces.add(piece);
            piece = PieceFactory.getPiece(this, this.whitePlayer, Piece.PieceTypes.ROOK);
            this.matrix[0][H].setPiece(piece);
            whitePieces.add(piece);
            setupWhitePawns();

            // black player start positions
            piece = PieceFactory.getPiece(this, this.blackPlayer, Piece.PieceTypes.ROOK);
            this.matrix[7][A].setPiece(piece);
            blackPieces.add(piece);
            piece = PieceFactory.getPiece(this, this.blackPlayer, Piece.PieceTypes.KNIGHT);
            this.matrix[7][B].setPiece(piece);
            blackPieces.add(piece);
            piece = PieceFactory.getPiece(this, this.blackPlayer, Piece.PieceTypes.BISHOP);
            this.matrix[7][C].setPiece(piece);
            blackPieces.add(piece);
            piece = PieceFactory.getPiece(this, this.blackPlayer, Piece.PieceTypes.QUEEN);
            this.matrix[7][D].setPiece(piece);
            blackPieces.add(piece);
            King blackKing = (King) PieceFactory.getPiece(this, this.blackPlayer, Piece.PieceTypes.KING);
            this.matrix[7][I].setPiece(blackKing);
            blackPieces.add(blackKing);
            setKingBlack(blackKing);
            piece = PieceFactory.getPiece(this, this.blackPlayer, Piece.PieceTypes.BISHOP);
            this.matrix[7][J].setPiece(piece);
            blackPieces.add(piece);
            piece = PieceFactory.getPiece(this, this.blackPlayer, Piece.PieceTypes.KNIGHT);
            this.matrix[7][K].setPiece(piece);
            blackPieces.add(piece);
            piece = PieceFactory.getPiece(this, this.blackPlayer, Piece.PieceTypes.ROOK);
            this.matrix[7][L].setPiece(piece);
            blackPieces.add(piece);
            setupBlackPawns();

            // gray player start positions
            piece = PieceFactory.getPiece(this, this.greyPlayer, Piece.PieceTypes.ROOK);
            this.matrix[11][H].setPiece(piece);
            greyPieces.add(piece);
            piece = PieceFactory.getPiece(this, this.greyPlayer, Piece.PieceTypes.KNIGHT);
            this.matrix[11][G].setPiece(piece);
            greyPieces.add(piece);
            piece = PieceFactory.getPiece(this, this.greyPlayer, Piece.PieceTypes.BISHOP);
            this.matrix[11][F].setPiece(piece);
            greyPieces.add(piece);
            piece = PieceFactory.getPiece(this, this.greyPlayer, Piece.PieceTypes.QUEEN);
            this.matrix[11][E].setPiece(piece);
            greyPieces.add(piece);
            King greyKing = (King) PieceFactory.getPiece(this, this.greyPlayer, Piece.PieceTypes.KING);
            this.matrix[11][I].setPiece(greyKing);
            setKingGrey(greyKing);
            piece = PieceFactory.getPiece(this, this.greyPlayer, Piece.PieceTypes.BISHOP);
            this.matrix[11][J].setPiece(piece);
            greyPieces.add(piece);
            piece = PieceFactory.getPiece(this, this.greyPlayer, Piece.PieceTypes.KNIGHT);
            this.matrix[11][K].setPiece(piece);
            greyPieces.add(piece);
            piece = PieceFactory.getPiece(this, this.greyPlayer, Piece.PieceTypes.ROOK);
            this.matrix[11][L].setPiece(piece);
            greyPieces.add(piece);
            setupGreyPawns();
        }
    }

    public void setupGreyPawns() {
        Piece pawn;
        for (int i = E; i <= H; i++) {
            pawn = PieceFactory.getPiece(this, this.greyPlayer, Piece.PieceTypes.PAWN);
            this.matrix[10][i].setPiece(pawn);
            greyPawns.add(pawn);
            greyPieces.add(pawn);
        }
        for (int i = I; i <= L; i++) {
            pawn = PieceFactory.getPiece(this, this.greyPlayer, Piece.PieceTypes.PAWN);
            this.matrix[10][i].setPiece(pawn);
            greyPawns.add(pawn);
            greyPieces.add(pawn);
        }
    }

    public void setupWhitePawns() {
        for (int i = A; i <= H; i++) {
            Piece pawn = PieceFactory.getPiece(this, this.whitePlayer, Piece.PieceTypes.PAWN);
            this.matrix[1][i].setPiece(pawn);
            whitePawns.add(pawn);
            whitePieces.add(pawn);
        }
    }

    public void setupBlackPawns() {
        Piece pawn;

        for (int i = A; i <= D; i++) {
            pawn = PieceFactory.getPiece(this, this.blackPlayer, Piece.PieceTypes.PAWN);
            this.matrix[6][i].setPiece(pawn);
            blackPawns.add(pawn);
            blackPieces.add(pawn);
        }
        for (int i = I; i <= L; i++) {
            pawn = PieceFactory.getPiece(this, this.blackPlayer, Piece.PieceTypes.PAWN);
            this.matrix[6][i].setPiece(pawn);
            blackPawns.add(pawn);
            blackPieces.add(pawn);
        }
    }

    /**
     * redraw all pieces in matrix
     */
    private void redrawPieces() {
        this.chessboardGrid.redraw();
    }

    @Override
    public King getKingWhite() {
        return kingWhite;
    }

    @Override
    public void setKingWhite(King kingWhite) {
        this.kingWhite = kingWhite;
    }

    @Override
    public King getKingBlack() {
        return kingBlack;
    }

    @Override
    public void setKingBlack(King kingBlack) {
        this.kingBlack = kingBlack;
    }

    @Override
    public Pawn getTwoSquareMovedPawn() {
        return twoSquareMovedPawn;
    }

    @Override
    public void setTwoSquareMovedPawn(Pawn twoSquareMovedPawn) {
        this.twoSquareMovedPawn = twoSquareMovedPawn;
    }

    @Override
    public ChessboardGrid getChessboardGrid() {
        return this.chessboardGrid;
    }

    @Override
    public boolean validMove(Square square, Piece piece) {
        return (0 <= square.getPosX() && square.getPosX() <= 11 &&
                0 <= square.getPosY() && square.getPosY() <= 11 &&
                !matrix[square.getPosX()][square.getPosY()].isInvalid() &&
                (matrix[square.getPosX()][square.getPosY()].isEmpty() || occupiedByOther(piece, square)));
    }

    /**
     * @param square in sextant for which we want the rosette
     * @return the rosette for the corresponding sextant
     * @throws Exception in case of an invalid square
     */
    public Square getCurrentRosette(Square square) throws Exception {
        int x = square.getPosX() + 1;
        int y = square.getPosY();

        if (1 <= x && x <= 4 && E <= y && y <= H) {
            return new Square(3, E, null);
        }
        if (9 <= x && x <= 12 && E <= y && y <= H) {
            return new Square(8, E, null);
        }
        if (9 <= x && x <= 12 && I <= y && y <= L) {
            return new Square(8, I, null);
        }
        if (5 <= x && x <= 8 && I <= y && y <= L) {
            return new Square(4, I, null);
        }
        if (5 <= x && x <= 8 && A <= y && y <= D) {
            return new Square(4, D, null);
        }
        if (1 <= x && x <= 4 && A <= y && y <= D) {
            return new Square(3, D, null);
        }

        throw new Exception("Invalid square");
    }

    /**
     * @param square  to check if in same sextant as coordinates
     * @param x_coord coordinate
     * @param y_coord coordinate
     * @return true if square and coordinates are in same sextant
     */
    public boolean inSextant(Square square, int x_coord, int y_coord) {
        return ((1 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 4 && E <= square.getPosY() && square.getPosY() <= H) && (1 <= (x_coord + 1) && (x_coord + 1) <= 4 && E <= y_coord && y_coord <= H) ||
                (9 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 12 && E <= square.getPosY() && square.getPosY() <= H) && (9 <= (x_coord + 1) && (x_coord + 1) <= 12 && E <= y_coord && y_coord <= H) ||
                (9 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 12 && I <= square.getPosY() && square.getPosY() <= L) && (9 <= (x_coord + 1) && (x_coord + 1) <= 12 && I <= y_coord && y_coord <= L) ||
                (5 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 8 && I <= square.getPosY() && square.getPosY() <= L) && (5 <= (x_coord + 1) && (x_coord + 1) <= 8 && I <= y_coord && y_coord <= L) ||
                (5 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 8 && A <= square.getPosY() && square.getPosY() <= D) && (5 <= (x_coord + 1) && (x_coord + 1) <= 8 && A <= y_coord && y_coord <= D) ||
                (1 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 4 && A <= square.getPosY() && square.getPosY() <= D) && (1 <= (x_coord + 1) && (x_coord + 1) <= 4 && A <= y_coord && y_coord <= D));
    }

    /**
     * @param square we are using
     * @return the neighbour square that belongs to the left sextant
     * @throws Exception in case the square is invalid
     */
    public Square getLeftSextantSquare(Square square) throws Exception {
        if (9 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 12) {
            if (square.getPosY() == I) {
                return new Square(square.getPosX() + 1, E, null);
            } else if (square.getPosX() + 1 == 9) {
                if (square.getPosY() == ThreePlayerChessboard.G) {
                    return new Square(3, square.getPosY() + 1, null);
                } else {
                    return new Square(3, square.getPosY() - 1, null);
                }
            } else {
                return new Square(3, square.getPosY() + 1, null);
            }
        }

        if (1 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 4) {
            return new Square(square.getPosX() + 1, square.getPosY() - 1, null);
        }


        if (5 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 8) {
            if (square.getPosY() == D) {
                return new Square(square.getPosX() + 1, I, null);
            } else {
                return new Square(square.getPosX() + 4, square.getPosY() + 1, null);
            }
        }
        throw new Exception("Invalid square");
    }

    /**
     * @param square we are using
     * @return the neighbour square that belongs to the right sextant
     * @throws Exception in case the square is invalid
     */
    public Square getRightSextantSquare(Square square) throws Exception {
        if (9 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 12) {
            if (square.getPosY() == E) {
                return new Square(square.getPosX() + 1, I, null);
            } else {
                return new Square(4, square.getPosY() + 1, null);
            }
        }

        if (1 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 4) {
            if (square.getPosY() == D) {
                return new Square(square.getPosX() + 1, E, null);
            } else {
                return new Square(8, square.getPosY() + 1, null);
            }
        }


        if (5 <= (square.getPosX() + 1) && (square.getPosX() + 1) <= 8) {
            if (square.getPosY() == I) {
                return new Square(square.getPosX() + 1, D, null);
            } else {
                return new Square(square.getPosX() - 1, square.getPosY() - 1, null);
            }
        }
        throw new Exception("Invalid square");
    }

    /**
     * @param square   we are using
     * @param diagonal towards center of board from square
     * @return the reachable rosette square in current sextant if any
     * @throws Exception in case of invalid square
     */
    public Square getSideRosetteTile(Square square, ArrayList<Square> diagonal) throws Exception {
        if ((E <= square.getPosY()) && (square.getPosY() <= H)) {
            if ((1 <= square.getPosX() + 1) && (square.getPosX() + 1 <= 4)) {
                if (diagonal.contains(new Square(3, F, null))) {
                    return new Square(8, E, null);
                }
                if (diagonal.contains(new Square(2, E, null))) {
                    return new Square(3, D, null);
                }
            }
            if ((9 <= square.getPosX() + 1) && (square.getPosX() + 1 <= 12)) {
                if (diagonal.contains(new Square(9, E, null))) {
                    return new Square(8, I, null);
                }
                if (diagonal.contains(new Square(8, F, null))) {
                    return new Square(3, E, null);
                }
            }
        }

        if ((A <= square.getPosY()) && (square.getPosY() <= D)) {
            if ((1 <= square.getPosX() + 1) && (square.getPosX() + 1 <= 4)) {
                if (diagonal.contains(new Square(2, D, null))) {
                    return new Square(3, E, null);
                }
                if (diagonal.contains(new Square(3, C, null))) {
                    return new Square(4, D, null);
                }
            }
            if ((5 <= square.getPosX() + 1) && (square.getPosX() + 1 <= 8)) {
                if (diagonal.contains(new Square(4, C, null))) {
                    return new Square(3, D, null);
                }
                if (diagonal.contains(new Square(5, D, null))) {
                    return new Square(4, I, null);
                }
            }
        }


        if ((I <= square.getPosY()) && (square.getPosY() <= L)) {
            if ((5 <= square.getPosX() + 1) && (square.getPosX() + 1 <= 8)) {
                if (diagonal.contains(new Square(5, I, null))) {
                    return new Square(4, D, null);
                }
                if (diagonal.contains(new Square(4, J, null))) {
                    return new Square(8, I, null);
                }
            }
            if ((9 <= square.getPosX() + 1) && (square.getPosX() + 1 <= 12)) {
                if (diagonal.contains(new Square(8, J, null))) {
                    return new Square(4, I, null);
                }
                if (diagonal.contains(new Square(9, I, null))) {
                    return new Square(8, E, null);
                }
            }
        }
        return null;
    }

    /**
     * @param piece to be placed
     * @param x     coordinate where to place it
     * @param y     coordinate where to place it
     */
    public void setPiece(Piece piece, int x, int y) {
        if (piece != null && piece.getSquare() != null) {
            Castling cast = new Castling(myKing(piece.getColor()), this);
            if (cast.isCastling(x, y)) {
                Rook rook = cast.getRook(x, y);
                Square newPos = cast.newRookPosition(rook);
                matrix[rook.getPosX()][rook.getPosY()].setPiece(null);
                matrix[newPos.getPosX()][newPos.getPosY()].setPiece(rook);
            }

            matrix[piece.getPosX()][piece.getPosY()].setPiece(null);
        }
        matrix[x][y].setPiece(piece);
    }

    /**
     * @param square we are using
     * @return a list of squares containing the squares in the rosette that are diagonally reachable
     * @throws Exception in case of invalid square
     */
    public ArrayList<Square> getDiagonalCenterPositions(Square square) throws Exception {
        if (WHITE_ROSETTE.contains(new Square(square.getPosX(), square.getPosY(), null))) {
            return WHITE_ROSETTE;
        }

        if (BLACK_ROSETTE.contains(new Square(square.getPosX(), square.getPosY(), null))) {
            return BLACK_ROSETTE;
        }

        throw new Exception("Invalid square");
    }

    /**
     * @param piece  we are moving
     * @param square to be moved
     * @return true if square is occupied by other player
     */
    public boolean occupiedByOther(Piece piece, Square square) {
        Square nextMove = matrix[square.getPosX()][square.getPosY()];
        return (!nextMove.isInvalid() && nextMove.getPiece() != null && nextMove.getPiece().getPlayer() != piece.getPlayer());
    }

    /**
     * @param piece  we are moving
     * @param square to be moved
     * @return true if square is occupied by me
     */
    public boolean occupiedByMe(Piece piece, Square square) {
        if (!(0 <= square.getPosX() && square.getPosX() <= 11 && 0 <= square.getPosY() && square.getPosY() <= 11)) {
            return false;
        }
        Square nextMove = matrix[square.getPosX()][square.getPosY()];
        return (!nextMove.isInvalid() && nextMove.getPiece() != null && nextMove.getPiece().getPlayer() == piece.getPlayer());
    }

    public void setKingGrey(King kingGrey) {
        this.kingGrey = kingGrey;
    }

    /**
     * @param player
     * @param piece
     * @throws Exception
     */
    public void activateSpy(Player player, Piece piece) throws Exception {
        new SpyActivator(player, piece, this).activateSpy();
    }

    public boolean isWhiteSpyActive() {
        return isWhiteSpyActive;
    }

    public void setWhiteSpyActive(boolean whiteSpyActive) {
        isWhiteSpyActive = whiteSpyActive;
    }

    public boolean isBlackSpyActive() {
        return isBlackSpyActive;
    }

    public void setBlackSpyActive(boolean blackSpyActive) {
        isBlackSpyActive = blackSpyActive;
    }

    public boolean isGreySpyActive() {
        return isGreySpyActive;
    }

    public void setGreySpyActive(boolean greySpyActive) {
        isGreySpyActive = greySpyActive;
    }

    @Override
    public Square getSquare(int xCoordinate, int yCoordinate) {
        return getSquares()[xCoordinate][yCoordinate];
    }

    @Override
    public King myKing(Player.Colors color) {
        if (color == Player.Colors.WHITE) {
            return kingWhite;
        } else if (color == Player.Colors.BLACK) {
            return kingBlack;
        } else {
            return kingGrey;
        }
    }

    // TODO: Remove me
    @Override
    public Square[][] getSquares() {
        return matrix;
    }


    /**
     * @param places  string with pieces to set on chessboard
     * @param plWhite reference to white player
     * @param plBlack reference to black player
     */
    @Override
    public void setPieces(String places, Player plWhite, Player plBlack) {
    }

    /**
     * @param x x position on chessboard
     * @param y y position on chessboard
     * @return null
     */
    @Override
    public Square getSquareConsideringLabels(int x, int y) {
        return null;
    }

    /**
     * Method selecting piece in chessboard
     *
     * @param sq square to select (when clicked))
     */
    @Override
    public void select(Square sq) {
    }

    /**
     * Method set variables active_x_square and active_y_square
     * to 0 values.
     */
    @Override
    public void unselect() {
    }

    /**
     * @return the width of the component
     */
    @Override
    public int get_widht() {
        return 1;
    }

    /**
     * @return the height of the component
     */
    @Override
    public int get_height() {
        return 1;
    }

    /**
     * @param includeLables boolean to determine if labels should be included
     * @return the width of the component including labels
     */
    @Override
    public int get_widht(boolean includeLables) {
        return 1;
    }

    @Override
    public int get_square_height() {
        return 1;
    }

    /**
     * @param begin of move
     * @param end   of move
     */
    @Override
    public void move(Square begin, Square end) {
    }

    /**
     * @param xFrom from which x move piece
     * @param yFrom from which y move piece
     * @param xTo   to which x move piece
     * @param yTo   to which y move piece
     */
    @Override
    public void move(int xFrom, int yFrom, int xTo, int yTo) {
    }

    /**
     * @param begin   start of move
     * @param end     ond of move
     * @param refresh if square should be unselected and force a refresh
     */
    @Override
    public void move(Square begin, Square end, boolean refresh) {
    }

    /**
     * @param begin               square from which move piece
     * @param end                 square where we want to move piece         *
     * @param refresh             chessboard, default: true
     * @param clearForwardHistory clear move history
     */
    @Override
    public void move(Square begin, Square end, boolean refresh, boolean clearForwardHistory) {
    }

    @Override
    public java.awt.Point getTopLeftPoint() {
        return new java.awt.Point();
    }

    public Player getWhitePlayer () {
        return whitePlayer;
    }

    public Player getGreyPlayer () {
        return greyPlayer;
    }

    public Player getBlackPlayer () {
        return blackPlayer;
    }

    /**
     * @return true if a king was captured
     */
    public boolean isGameFinished() throws Exception {
        return (kingWhite.getSquare() == null ||
                kingBlack.getSquare() == null ||
                kingGrey.getSquare() == null  ||
                new CheckMateController(this, kingWhite).isCheckMate() ||
                new CheckMateController(this, kingBlack).isCheckMate() ||
                new CheckMateController(this, kingGrey).isCheckMate());
    }
}
