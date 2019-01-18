/*
#    This program is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    This program is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Authors:
 * Mateusz Sławomir Lach ( matlak, msl )
 * Damian Marciniak
 */
package com.ovgu.ccd.gui.twoplayer;

import com.ovgu.ccd.applogic.*;
import com.ovgu.ccd.applogic.Player.Colors;
import com.ovgu.ccd.gui.twoplayer.Moves.castling;
import com.ovgu.ccd.gui.threeplayer.ChessboardGrid;
import com.ovgu.ccd.moves.Move;
import com.ovgu.ccd.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class to represent chessboard. Chessboard is made from squares.
 * It is setting the squers of chessboard and sets the pieces(pawns)
 * witch the owner is current player on it.
 */
public class Chessboard extends JPanel implements IBoard {

    public static final int top = 0;
    public static final int bottom = 7;
    private Square[][] squares;//squares of chessboard
    //public Graphics graph;
    public static final int img_x = 5;//image x position (used in JChessView class!)
    public static final int img_y = img_x;//image y position (used in JChessView class!)
    public static final int img_widht = 480;//image width
    public static final int img_height = img_widht;//image height
    private static final Image orgImage = ResourceManager.loadImage("chessboard.png");//image of chessboard
    private static final Image org_sel_square = ResourceManager.loadImage("sel_square.png");//image of highlited square
    private static final Image org_able_square = ResourceManager.loadImage("able_square.png");//image of square where piece can go
    private static Image image = Chessboard.orgImage;//image of chessboard
    private static Image sel_square = org_sel_square;//image of highlited square
    private static Image able_square = org_able_square;//image of square where piece can go
    public Square activeSquare;
    private King kingWhite;
    private King kingBlack;
    //----------------------------
    //For En passant:
    //|-> Pawn whose in last turn moved two square
    private Pawn twoSquareMovedPawn = null;
    public Pawn twoSquareMovedPawn2 = null;
    private Image upDownLabel = null;
    private Image LeftRightLabel = null;
    private Point topLeft = new Point(0, 0);
    private int active_x_square;
    private int active_y_square;
    private float square_height;//height of square
    private ArrayList moves;
    private Settings settings;
    //-------- for undo ----------
    private Square undo1_sq_begin = null;
    private Square undo1_sq_end = null;
    private Piece undo1_piece_begin = null;
    private Piece undo1_piece_end = null;
    private Piece ifWasEnPassant = null;
    private Piece ifWasCastling = null;
    private boolean breakCastling = false; //if last move break castling
    private Moves moves_history;

    /**
     * Chessboard class constructor
     *
     * @param settings      reference to Settings class object for this chessboard
     * @param moves_history reference to Moves class object for this chessboard
     */
    public Chessboard(Settings settings, Moves moves_history) {
        this.settings = settings;
        this.activeSquare = null;
        this.square_height = img_height / 8;//we need to devide to know height of field
        this.squares = new Square[8][8];//initalization of 8x8 chessboard
        this.active_x_square = 0;
        this.active_y_square = 0;

        for (int i = 0; i < 8; i++) {//create object for each square
            for (int y = 0; y < 8; y++) {
                this.squares[i][y] = new Square(i, y, null);
            }
        }//--endOf--create object for each square
        this.moves_history = moves_history;
        this.setDoubleBuffered(true);
        this.drawLabels((int) this.square_height);
    }/*--endOf-Chessboard--*/


    /**
     * Method setPieces on begin of new game or loaded game
     *
     * @param places  string with pieces to set on chessboard
     * @param plWhite reference to white player
     * @param plBlack reference to black player
     */
    @Override
    public void setPieces(String places, Player plWhite, Player plBlack) {

        if (places.equals("")) //if NEWGAME
        {
            if (this.settings.upsideDown) {
                this.setPieces4NewGame(true, plWhite, plBlack);
            } else {
                this.setPieces4NewGame(false, plWhite, plBlack);
            }

        } else //if loadedGame
        {
            return;
        }
    }/*--endOf-setPieces--*/


    /**
     *
     */
    private void setPieces4NewGame(boolean upsideDown, Player plWhite, Player plBlack) {

        /* WHITE PIECES */
        Player player = plBlack;
        Player player1 = plWhite;
        if (upsideDown) //if white on Top
        {
            player = plWhite;
            player1 = plBlack;
        }
        this.setFigures4NewGame(0, player, upsideDown);
        this.setPawns4NewGame(1, player);
        this.setFigures4NewGame(7, player1, upsideDown);
        this.setPawns4NewGame(6, player1);
    }/*--endOf-setPieces(boolean upsideDown)--*/


    /**
     * method set Figures in row (and set Queen and King to right position)
     *
     * @param i          row where to set figures (Rook, Knight etc.)
     * @param player     which is owner of pawns
     * @param upsideDown if true white pieces will be on top of chessboard
     */
    private void setFigures4NewGame(int i, Player player, boolean upsideDown) {

        if (i != 0 && i != 7) {
            System.out.println("error setting figures like rook etc.");
            return;
        } else if (i == 0) {
            player.setGoDown(true);
        }

        this.squares[0][i].setPiece(PieceFactory.getPiece(this, player, Piece.PieceTypes.ROOK));
        this.squares[7][i].setPiece(PieceFactory.getPiece(this, player, Piece.PieceTypes.ROOK));
        this.squares[1][i].setPiece(PieceFactory.getPiece(this, player, Piece.PieceTypes.KNIGHT));
        this.squares[6][i].setPiece(PieceFactory.getPiece(this, player, Piece.PieceTypes.KNIGHT));
        this.squares[2][i].setPiece(PieceFactory.getPiece(this, player, Piece.PieceTypes.BISHOP));
        this.squares[5][i].setPiece(PieceFactory.getPiece(this, player, Piece.PieceTypes.BISHOP));
        if (upsideDown) {
            this.squares[4][i].setPiece(PieceFactory.getPiece(this, player, Piece.PieceTypes.QUEEN));
            if (player.getColor() == Colors.WHITE) {
                this.squares[3][i].setPiece(kingWhite = (King) PieceFactory.getPiece(this, player, Piece.PieceTypes.KING));
            } else {
                this.squares[3][i].setPiece(kingBlack = (King) PieceFactory.getPiece(this, player, Piece.PieceTypes.KING));
            }
        } else {
            this.squares[3][i].setPiece(PieceFactory.getPiece(this, player, Piece.PieceTypes.QUEEN));
            if (player.getColor() == Colors.WHITE) {
                this.squares[4][i].setPiece(kingWhite = (King) PieceFactory.getPiece(this, player, Piece.PieceTypes.KING));
            } else {
                this.squares[4][i].setPiece(kingBlack = (King) PieceFactory.getPiece(this, player, Piece.PieceTypes.KING));
            }
        }
    }

    /**
     * method set Pawns in row
     *
     * @param i      row where to set pawns
     * @param player player which is owner of pawns
     */
    private void setPawns4NewGame(int i, Player player) {
        if (i != 1 && i != 6) {
            System.out.println("error setting pawns etc.");
            return;
        }

        for (int x = 0; x < 8; x++) {
            this.squares[x][i].setPiece(PieceFactory.getPiece(this, player, Piece.PieceTypes.PAWN));

        }
    }

    /**
     * method to get reference to square from given x and y integeres
     *
     * @param x x position on chessboard
     * @param y y position on chessboard
     * @return reference to searched square
     */
    @Override
    public Square getSquareConsideringLabels(int x, int y) {
        if ((x > this.get_height()) || (y > this.get_widht())) //test if click is out of chessboard
        {
            System.out.println("click out of chessboard.");
            return null;
        }
        if (this.settings.renderLabels) {
            x -= this.upDownLabel.getHeight(null);
            y -= this.upDownLabel.getHeight(null);
        }
        double square_x = x / square_height;//count which field in X was clicked
        double square_y = y / square_height;//count which field in Y was clicked

        if (square_x > (int) square_x) //if X is more than X parsed to Integer
        {
            square_x = (int) square_x + 1;//parse to integer and increment
        }
        if (square_y > (int) square_y) //if X is more than X parsed to Integer
        {
            square_y = (int) square_y + 1;//parse to integer and increment
        }
        //Square newActiveSquare = this.squares[(int)square_x-1][(int)square_y-1];//4test
        System.out.println("square_x: " + square_x + " square_y: " + square_y + " \n"); //4tests
        Square result;

        try {
            result = this.squares[(int) square_x - 1][(int) square_y - 1];
        } catch (java.lang.ArrayIndexOutOfBoundsException exc) {
            System.out.println("!!Array out of bounds when getting Square with Chessboard.getSquareConsideringLabels(int,int) : " + exc);
            return null;
        }
        return this.getSquares()[(int) square_x - 1][(int) square_y - 1];
    }

    /**
     * Method selecting piece in chessboard
     *
     * @param sq square to select (when clicked))
     */
    @Override
    public void select(Square sq) {
        this.activeSquare = sq;
        this.active_x_square = sq.getPosX() + 1;
        this.active_y_square = sq.getPosY() + 1;

        //this.draw();//redraw
        System.out.println("active_x: " + this.active_x_square + " active_y: " + this.active_y_square);//4tests
        repaint();

    }/*--endOf-select--*/


    /**
     * Method set variables active_x_square and active_y_square
     * to 0 values.
     */
    @Override
    public void unselect() {
        this.active_x_square = 0;
        this.active_y_square = 0;
        this.activeSquare = null;
        //this.draw();//redraw
        repaint();
    }/*--endOf-unselect--*/

    @Override
    public int get_widht() {
        return this.get_widht(false);
    }

    @Override
    public int get_height() {
        return this.get_height(false);
    }


    @Override
    public int get_widht(boolean includeLables) {
        return this.getHeight();
    }/*--endOf-get_widht--*/


    int get_height(boolean includeLabels) {
        if (this.settings.renderLabels) {
            return Chessboard.image.getHeight(null) + upDownLabel.getHeight(null);
        }
        return Chessboard.image.getHeight(null);
    }/*--endOf-get_height--*/


    @Override
    public int get_square_height() {
        int result = (int) this.square_height;
        return result;
    }

    @Override
    public void move(Square begin, Square end) {
        move(begin, end, true);
    }

    /**
     * Method to move piece over chessboard
     *
     * @param xFrom from which x move piece
     * @param yFrom from which y move piece
     * @param xTo   to which x move piece
     * @param yTo   to which y move piece
     */
    @Override
    public void move(int xFrom, int yFrom, int xTo, int yTo) {
        Square fromSQ = null;
        Square toSQ = null;

        try {
            fromSQ = this.squares[xFrom][yFrom];
            toSQ = this.squares[xTo][yTo];
        } catch (java.lang.IndexOutOfBoundsException exc) {

            System.out.println("error moving piece: " + exc);
            return;
        }
        this.move(this.getSquares()[xFrom][yFrom], this.getSquares()[xTo][yTo], true);
    }

    @Override
    public void move(Square begin, Square end, boolean refresh) {
        this.move(begin, end, refresh, true);
    }

    /**
     * Method move piece from square to square
     *
     * @param begin   square from which move piece
     * @param end     square where we want to move piece         *
     * @param refresh chessboard, default: true
     */
    @Override
    public void move(Square begin, Square end, boolean refresh, boolean clearForwardHistory) {

        castling wasCastling = Moves.castling.none;
        Piece promotedPiece = null;
        boolean wasEnPassant = false;
        if (end.getPiece() != null) {
            end.getPiece().setSquare(null);
        }

        Square tempBegin = new Square(begin);//4 moves history
        Square tempEnd = new Square(end);  //4 moves history
        //for undo
        undo1_piece_begin = begin.getPiece();
        undo1_sq_begin = begin;
        undo1_piece_end = end.getPiece();
        undo1_sq_end = end;
        ifWasEnPassant = null;
        ifWasCastling = null;
        breakCastling = false;
        // ---

        twoSquareMovedPawn2 = getTwoSquareMovedPawn();

        //begin.getPiece().setSquare(end);//set square of piece to ending
        Piece piece = begin.getPiece();
        begin.setPiece(null);//make null piece for begining square
        end.setPiece(piece);//for ending square set piece from beginin square


        if (end.getPiece().name.equals("King")) {
            if (!((King) end.getPiece()).isWasMotion()) {
                breakCastling = true;
                ((King) end.getPiece()).setWasMotion(true);
            }

            //Castling

            if (begin.getPosX() + 2 == end.getPosX()) {
                move(squares[7][begin.getPosY()], squares[end.getPosX() - 1][begin.getPosY()], false, false);
                ifWasCastling = end.getPiece();  //for undo
                wasCastling = Moves.castling.shortCastling;
                //this.moves_history.addMove(tempBegin, tempEnd, clearForwardHistory, wasCastling, wasEnPassant);
                //return;
            } else if (begin.getPosX() - 2 == end.getPosX()) {
                move(squares[0][begin.getPosY()], squares[end.getPosX() + 1][begin.getPosY()], false, false);
                ifWasCastling = end.getPiece();  // for undo
                wasCastling = Moves.castling.longCastling;
                //this.moves_history.addMove(tempBegin, tempEnd, clearForwardHistory, wasCastling, wasEnPassant);
                //return;
            }
            //endOf Castling
        } else if (end.getPiece().name.equals("Rook")) {
            if (!((Rook) end.getPiece()).isWasMotion()) {
                breakCastling = true;
                ((Rook) end.getPiece()).setWasMotion(true);
            }

        } else if (end.getPiece().name.equals("Pawn")) {
            if (getTwoSquareMovedPawn() != null && squares[end.getPosX()][begin.getPosY()] == getTwoSquareMovedPawn().getSquare()) //en passant
            {
                ifWasEnPassant = squares[end.getPosX()][begin.getPosY()].getPiece(); //for undo

                tempEnd.setPiece(squares[end.getPosX()][begin.getPosY()].getPiece()); //ugly hack - put taken pawn in en passant plasty do end square

                squares[end.getPosX()][begin.getPosY()].setPiece(null);
                wasEnPassant = true;
            }

            if (begin.getPosY() - end.getPosY() == 2 || end.getPosY() - begin.getPosY() == 2) //moved two square
            {
                breakCastling = true;
                setTwoSquareMovedPawn((Pawn) end.getPiece());
            } else {
                setTwoSquareMovedPawn(null); //erase last saved move (for En passant)
            }

            if (end.getPiece().getSquare().getPosY() == 0 || end.getPiece().getSquare().getPosY() == 7) //promote Pawn

            {
                if (clearForwardHistory) {
                    String color;
                    if (end.getPiece().getPlayer().getColor() == Colors.WHITE) {
                        color = "W"; // promotionWindow was show with pieces in this color
                    } else {
                        color = "B";
                    }

                    String newPiece = JChessApp.jcv.showPawnPromotionBox(color); //return name of new piece

                    if (newPiece.equals("Queen")) // transform pawn to queen
                    {
                        Queen queen = (Queen) PieceFactory.getPiece(this, end.getPiece().player, Piece.PieceTypes.QUEEN);
                        queen.setChessboard(end.getPiece().getChessboard());
                        queen.player = end.getPiece().player;
                        queen.setSquare(end.getPiece().getSquare());
                        end.setPiece(queen);
                    } else if (newPiece.equals("Rook")) // transform pawn to rook
                    {
                        Rook rook = (Rook) PieceFactory.getPiece(this, end.getPiece().player, Piece.PieceTypes.ROOK);
                        rook.setChessboard(end.getPiece().getChessboard());
                        rook.player = end.getPiece().player;
                        rook.setSquare(end.getPiece().getSquare());
                        end.setPiece(rook);
                    } else if (newPiece.equals("Bishop")) // transform pawn to bishop
                    {
                        Bishop bishop = (Bishop) PieceFactory.getPiece(this, end.getPiece().player, Piece.PieceTypes.BISHOP);
                        bishop.setChessboard(end.getPiece().getChessboard());
                        bishop.player = end.getPiece().player;
                        bishop.setSquare(end.getPiece().getSquare());
                        end.setPiece(bishop);
                    } else // transform pawn to knight
                    {
                        Knight knight = (Knight) PieceFactory.getPiece(this, end.getPiece().player, Piece.PieceTypes.KNIGHT);
                        knight.setChessboard(end.getPiece().getChessboard());
                        knight.player = end.getPiece().player;
                        knight.setSquare(end.getPiece().getSquare());
                        end.setPiece(knight);
                    }
                    promotedPiece = end.getPiece();
                }
            }
        } else if (!end.getPiece().name.equals("Pawn")) {
            setTwoSquareMovedPawn(null); //erase last saved move (for En passant)
        }
        //}

        if (refresh) {
            this.unselect();//unselect square
            repaint();
        }

        if (clearForwardHistory) {
            this.moves_history.clearMoveForwardStack();
            this.moves_history.addMove(tempBegin, tempEnd, true, wasCastling, wasEnPassant, promotedPiece);
        } else {
            this.moves_history.addMove(tempBegin, tempEnd, false, wasCastling, wasEnPassant, promotedPiece);
        }
    }/*endOf-move()-*/


    public boolean redo() {
        return redo(true);
    }

    public boolean redo(boolean refresh) {
        if (this.settings.gameType == Settings.gameTypes.LOCAL) //redo only for LOCAL game
        {
            Move first = this.moves_history.redo();

            Square from = null;
            Square to = null;

            if (first != null) {
                from = first.getFrom();
                to = first.getTo();

                this.move(this.squares[from.getPosX()][from.getPosY()], this.squares[to.getPosX()][to.getPosY()], true, false);
                if (first.getPromotedPiece() != null) {
                    Pawn pawn = (Pawn) this.squares[to.getPosX()][to.getPosY()].getPiece();
                    pawn.setSquare(null);

                    this.squares[to.getPosX()][to.getPosY()].setPiece(first.getPromotedPiece());
                    Piece promoted = this.squares[to.getPosX()][to.getPosY()].getPiece();
                    promoted.setSquare(this.squares[to.getPosX()][to.getPosY()]);
                }
                return true;
            }

        }
        return false;
    }

    public boolean undo() {
        return undo(true);
    }

    public synchronized boolean undo(boolean refresh) //undo last move
    {
        Move last = this.moves_history.undo();


        if (last != null && last.getFrom() != null) {
            Square begin = last.getFrom();
            Square end = last.getTo();
            try {
                Piece moved = last.getMovedPiece();
                this.squares[begin.getPosX()][begin.getPosY()].setPiece(moved);

                moved.setSquare(this.squares[begin.getPosX()][begin.getPosY()]);

                Piece taken = last.getTakenPiece();
                if (last.getCastlingMove() != castling.none) {
                    Piece rook = null;
                    if (last.getCastlingMove() == castling.shortCastling) {
                        rook = this.squares[end.getPosX() - 1][end.getPosY()].getPiece();
                        this.squares[7][begin.getPosY()].setPiece(rook);
                        rook.setSquare(this.squares[7][begin.getPosY()]);
                        this.squares[end.getPosX() - 1][end.getPosY()].setPiece(null);
                    } else {
                        rook = this.squares[end.getPosX() + 1][end.getPosY()].getPiece();
                        this.squares[0][begin.getPosY()].setPiece(rook);
                        rook.setSquare(this.squares[0][begin.getPosY()]);
                        this.squares[end.getPosX() + 1][end.getPosY()].setPiece(null);
                    }
                    ((King) moved).setWasMotion(false);
                    ((Rook) rook).setWasMotion(false);
                    this.breakCastling = false;
                } else if (moved.name.equals("Rook")) {
                    ((Rook) moved).setWasMotion(false);
                } else if (moved.name.equals("Pawn") && last.wasEnPassant()) {
                    Pawn pawn = (Pawn) last.getTakenPiece();
                    this.squares[end.getPosX()][begin.getPosY()].setPiece(pawn);
                    pawn.setSquare(this.squares[end.getPosX()][begin.getPosY()]);
                } else if (moved.name.equals("Pawn") && last.getPromotedPiece() != null) {
                    Piece promoted = this.squares[end.getPosX()][end.getPosY()].getPiece();

                    promoted.setSquare(null);
                    this.squares[end.getPosX()][end.getPosY()].setPiece(null);
                }

                //check one more move back for en passant
                Move oneMoveEarlier = this.moves_history.getLastMoveFromHistory();
                if (oneMoveEarlier != null && oneMoveEarlier.wasPawnTwoFieldsMove()) {
                    Piece canBeTakenEnPassant = this.squares[oneMoveEarlier.getTo().getPosX()][oneMoveEarlier.getTo().getPosY()].getPiece();
                    if (canBeTakenEnPassant.name.equals("Pawn")) {
                        this.setTwoSquareMovedPawn((Pawn) canBeTakenEnPassant);
                    }
                }

                if (taken != null && !last.wasEnPassant()) {
                    this.squares[end.getPosX()][end.getPosY()].setPiece(taken);
                    taken.setSquare(this.squares[end.getPosX()][end.getPosY()]);
                } else {
                    this.squares[end.getPosX()][end.getPosY()].setPiece(null);
                }

                if (refresh) {
                    this.unselect();//unselect square
                    repaint();
                }

            } catch (java.lang.ArrayIndexOutOfBoundsException exc) {
                return false;
            } catch (java.lang.NullPointerException exc) {
                return false;
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to draw Chessboard and their elements (pieces etc.)
     *
     * @deprecated
     */
    public void draw() {
        this.getGraphics().drawImage(image, this.getTopLeftPoint().x, this.getTopLeftPoint().y, null);//draw an Image of chessboard
        this.drawLabels();
        this.repaint();
    }/*--endOf-draw--*/


    /**
     * Annotations to superclass Game updateing and painting the crossboard
     */
    @Override
    public void update(Graphics g) {
        repaint();
    }

    public Point getTopLeftPoint() {
        if (this.settings.renderLabels) {
            return new Point(this.topLeft.x + this.upDownLabel.getHeight(null), this.topLeft.y + this.upDownLabel.getHeight(null));
        }
        return this.topLeft;
    }

    @Override
    public void setPiece(Piece piece, int x, int y) {
        squares[x][y].setPiece(piece);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Point topLeftPoint = this.getTopLeftPoint();
        if (this.settings.renderLabels) {
            if (topLeftPoint.x <= 0 && topLeftPoint.y <= 0) //if renderLabels and (0,0), than draw it! (for first run)
            {
                this.drawLabels();
            }
            g2d.drawImage(this.upDownLabel, 0, 0, null);
            g2d.drawImage(this.upDownLabel, 0, Chessboard.image.getHeight(null) + topLeftPoint.y, null);
            g2d.drawImage(this.LeftRightLabel, 0, 0, null);
            g2d.drawImage(this.LeftRightLabel, Chessboard.image.getHeight(null) + topLeftPoint.x, 0, null);
        }
        g2d.drawImage(image, topLeftPoint.x, topLeftPoint.y, null);//draw an Image of chessboard
        for (int i = 0; i < 8; i++) //drawPiecesOnSquares
        {
            for (int y = 0; y < 8; y++) {
                if (this.squares[i][y].getPiece() != null) {
                    this.squares[i][y].getPiece().draw(g);//draw image of Piece
                }
            }
        }//--endOf--drawPiecesOnSquares
        if ((this.active_x_square != 0) && (this.active_y_square != 0)) //if some square is active
        {
            g2d.drawImage(sel_square,
                    ((this.active_x_square - 1) * (int) square_height) + topLeftPoint.x,
                    ((this.active_y_square - 1) * (int) square_height) + topLeftPoint.y, null);//draw image of selected square
            Square tmpSquare = this.squares[this.active_x_square - 1][this.active_y_square - 1];
            if (tmpSquare.getPiece() != null) {
                this.moves = this.squares[this.active_x_square - 1][this.active_y_square - 1].getPiece().allMoves();
            }

            for (Iterator it = moves.iterator(); moves != null && it.hasNext(); ) {
                Square sq = (Square) it.next();
                g2d.drawImage(able_square,
                        (sq.getPosX() * (int) square_height) + topLeftPoint.x,
                        (sq.getPosY() * (int) square_height) + topLeftPoint.y, null);
            }
        }
    }/*--endOf-paint--*/


    public void resizeChessboard(int height) {
        BufferedImage resized = new BufferedImage(height, height, BufferedImage.TYPE_INT_ARGB_PRE);
        Graphics g = resized.createGraphics();
        g.drawImage(Chessboard.orgImage, 0, 0, height, height, null);
        g.dispose();
        Chessboard.image = resized.getScaledInstance(height, height, 0);
        this.square_height = (float) (height / 8);
        if (this.settings.renderLabels) {
            height += 2 * (this.upDownLabel.getHeight(null));
        }
        this.setSize(height, height);

        resized = new BufferedImage((int) square_height, (int) square_height, BufferedImage.TYPE_INT_ARGB_PRE);
        g = resized.createGraphics();
        g.drawImage(Chessboard.org_able_square, 0, 0, (int) square_height, (int) square_height, null);
        g.dispose();
        Chessboard.able_square = resized.getScaledInstance((int) square_height, (int) square_height, 0);

        resized = new BufferedImage((int) square_height, (int) square_height, BufferedImage.TYPE_INT_ARGB_PRE);
        g = resized.createGraphics();
        g.drawImage(Chessboard.org_sel_square, 0, 0, (int) square_height, (int) square_height, null);
        g.dispose();
        Chessboard.sel_square = resized.getScaledInstance((int) square_height, (int) square_height, 0);
        this.drawLabels();
    }

    protected void drawLabels() {
        this.drawLabels((int) this.square_height);
    }

    protected final void drawLabels(int square_height) {
        //BufferedImage uDL = new BufferedImage(800, 800, BufferedImage.TYPE_3BYTE_BGR);
        int min_label_height = 20;
        int labelHeight = (int) Math.ceil(square_height / 4);
        labelHeight = (labelHeight < min_label_height) ? min_label_height : labelHeight;
        int labelWidth = (int) Math.ceil(square_height * 8 + (2 * labelHeight));
        BufferedImage uDL = new BufferedImage(labelWidth + min_label_height, labelHeight, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D uDL2D = uDL.createGraphics();
        uDL2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        uDL2D.setColor(Color.white);

        uDL2D.fillRect(0, 0, labelWidth + min_label_height, labelHeight);
        uDL2D.setColor(Color.black);
        uDL2D.setFont(new Font("Arial", Font.BOLD, 12));
        int addX = (square_height / 2);
        if (this.settings.renderLabels) {
            addX += labelHeight;
        }

        String[] letters =
                {
                        "a", "b", "c", "d", "e", "f", "g", "h"
                };
        if (!this.settings.upsideDown) {
            for (int i = 1; i <= letters.length; i++) {
                uDL2D.drawString(letters[i - 1], (square_height * (i - 1)) + addX, 10 + (labelHeight / 3));
            }
        } else {
            int j = 1;
            for (int i = letters.length; i > 0; i--, j++) {
                uDL2D.drawString(letters[i - 1], (square_height * (j - 1)) + addX, 10 + (labelHeight / 3));
            }
        }
        uDL2D.dispose();
        this.upDownLabel = uDL;

        uDL = new BufferedImage(labelHeight, labelWidth + min_label_height, BufferedImage.TYPE_3BYTE_BGR);
        uDL2D = uDL.createGraphics();
        uDL2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        uDL2D.setColor(Color.white);
        //uDL2D.fillRect(0, 0, 800, 800);
        uDL2D.fillRect(0, 0, labelHeight, labelWidth + min_label_height);
        uDL2D.setColor(Color.black);
        uDL2D.setFont(new Font("Arial", Font.BOLD, 12));

        if (this.settings.upsideDown) {
            for (int i = 1; i <= 8; i++) {
                uDL2D.drawString(new Integer(i).toString(), 3 + (labelHeight / 3), (square_height * (i - 1)) + addX);
            }
        } else {
            int j = 1;
            for (int i = 8; i > 0; i--, j++) {
                uDL2D.drawString(new Integer(i).toString(), 3 + (labelHeight / 3), (square_height * (j - 1)) + addX);
            }
        }
        uDL2D.dispose();
        this.LeftRightLabel = uDL;
    }

    public void componentMoved(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void componentShown(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void componentHidden(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void printBoard() {
        for (int y = 7; y >= 0; y--) //drawPiecesOnSquares
        {
            System.out.print(y + "|");
            for (int i = 0; i < 8; i++) {
                if (this.getSquares()[i][y].getPiece() != null) {
                    System.out.print("P");
                } else {
                    System.out.print("_");
                }
                System.out.print("|");
            }
            System.out.println();
        }//--endOf--drawPiecesOnSquares
        System.out.println(" |0|1|2|3|4|5|6|7|");
    }

    @Override
    public King myKing(Colors color) {
        if (color == Colors.WHITE) {
            return kingWhite;
        } else {
            return kingBlack;
        }
    }

    @Override
    public Square getSquare(int xCoordinate, int yCoordinate) {
        return getSquares()[xCoordinate][yCoordinate];
    }

    @Override
    public Square[][] getSquares() {
        return squares;
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
        return null;
    }

    // TODO: Remove me
    @Override
    public boolean validMove(Square square, Piece piece) {
        return false;
    }

}
