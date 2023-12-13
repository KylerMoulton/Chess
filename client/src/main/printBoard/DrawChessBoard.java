package printBoard;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import chess.positionImple;
import model.GameModel;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Random;

import static ui.EscapeSequences.*;
import static ui.EscapeSequences.SET_TEXT_COLOR_WHITE;

public class DrawChessBoard {
    private static boolean whiteSquare = true;

    private static final int BOARD_SIZE_IN_SQUARES = 8;
    private static final String EMPTY = " ";

    public static void drawBoard(ChessBoard game, ChessGame.TeamColor team) {
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        if (team == ChessGame.TeamColor.WHITE || team == null) {
            drawHeaders2(out);
            drawSquares(out, game);
            drawHeaders2(out);
        } else {
            //out.print("\u001b[39:49;0m");
            out.println();
            drawHeaders(out);
            drawSquares2(out, game);
            drawHeaders(out);
        }
    }

    private static void drawSquares(PrintStream out, ChessBoard game) {
        out.print(ERASE_SCREEN);
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(SET_TEXT_BOLD);
        for (int row = 1; row <= 8; row++) {
            out.print(ERASE_SCREEN);
            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print(SET_TEXT_BOLD);
            out.print(" " + row + " ");
            for (int col = 0; col <= 7; col++) {
                ChessPiece curPiece = game.getPiece(new positionImple(row - 1, col));
                String s = switchPiece(curPiece);
                if (curPiece != null) {
                    if (whiteSquare) {
                        out.print(SET_BG_COLOR_WHITE);
                        if (curPiece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                            out.print(SET_TEXT_COLOR_BLUE);
                        } else {
                            out.print(SET_TEXT_COLOR_RED);
                        }
                        out.print(" " + s + " ");
                        //out.print(" " + game.getPiece(new positionImple(row - 1, col)).getPieceType() + " ");
                        whiteSquare = false;
                    } else {
                        out.print(SET_BG_COLOR_BLACK);
                        if (curPiece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                            out.print(SET_TEXT_COLOR_BLUE);
                        } else {
                            out.print(SET_TEXT_COLOR_RED);
                        }
                        out.print(" " + s + " ");
                        //out.print(" " + game.getPiece(new positionImple(row - 1, col)) + " ");
                        whiteSquare = true;
                    }
                } else {
                    if (whiteSquare) {
                        out.print(SET_BG_COLOR_WHITE);
                        out.print("   ");
                        whiteSquare = false;
                    } else {
                        out.print(SET_BG_COLOR_BLACK);
                        out.print("   ");
                        whiteSquare = true;
                    }

                }

            }
            out.print(ERASE_SCREEN);
            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print(SET_TEXT_BOLD);
            out.print(" " + row + " ");
            out.print(RESET_BG_COLOR);
            out.print("\u001b[49m");
            out.println();
            whiteSquare = !whiteSquare;
        }
//        out.print("   ");
        out.print(RESET_BG_COLOR);
        out.print("\u001b[49m");
//        out.println();
    }

    private static void drawSquares2(PrintStream out, ChessBoard game) {
        out.print(ERASE_SCREEN);
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(SET_TEXT_BOLD);
        for (int row = 8; row >= 1; row--) {
            out.print(ERASE_SCREEN);
            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print(SET_TEXT_BOLD);
            out.print(" " + row + " ");
            for (int col = 7; col >= 0; col--) {
                ChessPiece curPiece = game.getPiece(new positionImple(row - 1, col));
                String s = switchPiece(curPiece);
                if (curPiece != null) {
                    if (whiteSquare) {
                        out.print(SET_BG_COLOR_WHITE);
                        if (curPiece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                            out.print(SET_TEXT_COLOR_BLUE);
                        } else {
                            out.print(SET_TEXT_COLOR_RED);
                        }
                        out.print(" " + s + " ");
                        //out.print(" " + game.getPiece(new positionImple(row - 1, col)).getPieceType() + " ");
                        whiteSquare = false;
                    } else {
                        out.print(SET_BG_COLOR_BLACK);
                        if (curPiece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                            out.print(SET_TEXT_COLOR_BLUE);
                        } else {
                            out.print(SET_TEXT_COLOR_RED);
                        }
                        out.print(" " + s + " ");
                        //out.print(" " + game.getPiece(new positionImple(row - 1, col)) + " ");
                        whiteSquare = true;
                    }
                } else {
                    if (whiteSquare) {
                        out.print(SET_BG_COLOR_WHITE);
                        out.print("   ");
                        whiteSquare = false;
                    } else {
                        out.print(SET_BG_COLOR_BLACK);
                        out.print("   ");
                        whiteSquare = true;
                    }

                }

            }
            out.print(ERASE_SCREEN);
            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print(SET_TEXT_BOLD);
            out.print(" " + row + " ");
            out.print(RESET_BG_COLOR);
            out.print("\u001b[49m");
            out.println();
            whiteSquare = !whiteSquare;
        }
//        out.print("   ");
        out.print(RESET_BG_COLOR);
        out.print("\u001b[49m");
//        out.println();
    }

    private static String switchPiece(ChessPiece piece) {
        if (piece == null) {
            return " ";
        }
        return switch (piece.getPieceType()) {
            case PAWN -> "P";
            case ROOK -> "R";
            case KNIGHT -> "N";
            case KING -> "K";
            case BISHOP -> "B";
            case QUEEN -> "Q";
        };
    }

    private static void drawHeaders(PrintStream out) {
        out.print(ERASE_SCREEN);
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(SET_TEXT_BOLD);
        out.print("   ");
        String[] headers = {"a", "b", "c", "d", "e", "f", "g", "h"};
        for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; ++boardCol) {
            drawHeader(out, headers[boardCol]);
//            if (boardCol < BOARD_SIZE_IN_SQUARES - 1) {
//                out.print(EMPTY.repeat(LINE_WIDTH_IN_CHARS));
//            }
        }
        out.print("   ");
        out.print(RESET_BG_COLOR);
        out.print("\u001b[49m");
        out.println();
    }

    private static void drawHeaders2(PrintStream out) {
        out.print(ERASE_SCREEN);
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(SET_TEXT_BOLD);
        out.print("   ");
        String[] headers = {"h", "g", "f", "e", "d", "c", "b", "a"};
        for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; ++boardCol) {
            drawHeader(out, headers[boardCol]);
//            if (boardCol < BOARD_SIZE_IN_SQUARES - 1) {
//                out.print(EMPTY.repeat(LINE_WIDTH_IN_CHARS));
//            }
        }
        out.print("   ");
        out.print(RESET_BG_COLOR);
        out.print("\u001b[49m");
        out.println();
    }

    private static void drawHeader(PrintStream out, String headerText) {
        int prefixLength = 1;
        int suffixLength = 1;

        out.print(EMPTY.repeat(prefixLength));
        printHeaderText(out, headerText);
        out.print(EMPTY.repeat(suffixLength));
    }

    private static void printHeaderText(PrintStream out, String player) {
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_TEXT_COLOR_WHITE);

        out.print(player);

    }
}
