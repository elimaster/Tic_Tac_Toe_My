package io.emaster.tictactoemy;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import io.emaster.tictactoemy.TicTacToe.MoveResult;
public class MainActivity extends Activity implements GameOverDialogFragment.GameOverDialogListener {

    LinearLayout boardLayout;
    TicTacToe game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new TicTacToe();
        boardLayout = (LinearLayout)findViewById(R.id.boardLayout);
        int width = boardLayout.getLayoutParams().width;
        int margin = 5;
        int imageSize = (width/3 -margin*2);
        for(int i = 0; i<3; i++){
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            for(int j=0;j<3;j++){
                ImageView cellView = new ImageView(this);
                cellView.setOnClickListener(cellClickListener);
                LinearLayout.LayoutParams cellViewLayout = new LinearLayout.LayoutParams(imageSize,imageSize);
                cellViewLayout.setMargins(margin,margin,margin,margin);
                cellView.setTag(i*3 + j + 1);
                row.addView(cellView, cellViewLayout);
            }
            LinearLayout.LayoutParams rowLayout = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            boardLayout.addView(row, rowLayout);
        }
    }

    private View.OnClickListener cellClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            int cell = (Integer)v.getTag();
            TicTacToe.MoveResult moveResult = game.makeMove(cell);
            if(moveResult == TicTacToe.MoveResult.INVALID_MOVE)
                Toast.makeText(MainActivity.this, "Invalid move", Toast.LENGTH_SHORT).show();
            else{
                ((ImageView)v).setImageResource(game.isXturn() ? R.drawable.o_ttt : R.drawable.x_ttt);
                if(moveResult!= TicTacToe.MoveResult.VALID_MOVE){
                    GameOverDialogFragment fragment = new GameOverDialogFragment();
                    if(moveResult == MoveResult.VICTORY){
                        fragment.setFragment("we have a winner!", "the winner is: "+ (game.isXturn()? "O":"X"), MainActivity.this);
                    }else {
                        fragment.setFragment("we have a draw!", "no winner", MainActivity.this);
                    }
                    android.app.FragmentManager fragmentManager = MainActivity.this.getFragmentManager();
                    fragment.show(fragmentManager, "game over");
                }
            }
        }
    };

    @Override
    public void onStartNewGame() {
        game.resetGame();
        for(int i =1; i<=9; i++){
            ImageView cellView = (ImageView)boardLayout.findViewWithTag(i);
            cellView.setImageDrawable(null);
        }
    }
}
