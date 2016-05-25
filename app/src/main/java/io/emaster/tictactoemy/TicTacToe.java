package io.emaster.tictactoemy;

/**
 * Created by user on 25/05/2016.
 */
public class TicTacToe {
    public enum CellValue{EMPTY, X,O};
    public enum MoveResult{INVALID_MOVE, INVALIDMOVE,VALID_MOVE, DRAW, VICTORY  };

    private CellValue[] board;
    private boolean isXturn;
    private int moveCount;

    public boolean isXturn(){
        return isXturn;
    }

    public void resetGame() {
        for(int i=0;i<board.length;i++){
            board[i] = CellValue.EMPTY;
        }
        isXturn = true;
        moveCount = 0;
    }

    public TicTacToe() {
        board = new CellValue[9];
        resetGame();
    }

    public MoveResult makeMove(int cell){
        if(cell<1 || cell>9){
            return MoveResult.INVALIDMOVE;
        }
        cell--;
        if(board[cell] == CellValue.EMPTY){
            moveCount++;
            board[cell] = isXturn ? CellValue.X : CellValue.O;
            isXturn = !isXturn;
            if(moveCount >=5 && checkVictory(cell))
                return MoveResult.VICTORY;
            if(moveCount ==9)
                return MoveResult.DRAW;
            return MoveResult.VALID_MOVE;
        }
        return MoveResult.INVALID_MOVE;
    }

    private boolean checkVictory(int cell){
        int row = cell / 3;
        int column = cell % 3;
        if(board[column]== board[column+3] && board[column]== board[column+6])
            return true;
        row *= 3;
        if(board[row]== board[row +1] && board[row]== board[row+2])
            return true;
        if(cell%2 ==0) {
            boolean diagonal1 = board[0] != CellValue.EMPTY &&
                    board[0] == board[4] && board[0] == board[8];
            if(diagonal1) return true;
            boolean diagonal2 = board[0] != CellValue.EMPTY &&
                    board[2] == board[4] && board[2] == board[6];
            if(diagonal2) return true;
            //return diagonal1 || diagonal2;
        }
        return false;
    }


}
