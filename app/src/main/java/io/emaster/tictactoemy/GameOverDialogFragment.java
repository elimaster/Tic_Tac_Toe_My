package io.emaster.tictactoemy;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by user on 25/05/2016.
 */
public class GameOverDialogFragment extends DialogFragment {
    private String text;
    private String title;
    private GameOverDialogListener listener;

    public void setFragment(String text, String title, GameOverDialogListener listener) {
        this.text = text;
        this.title = title;
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_game_over, container);
        TextView txtGameOverText = (TextView)view.findViewById(R.id.txtGameOverText);
        txtGameOverText.setText(text);
        Button btnNewGame = (Button)view.findViewById(R.id.btnNewGame);
        btnNewGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onStartNewGame();
                    dismiss();
                }
            }
        });
        getDialog().setTitle(title);
        return view;
    }

    public static interface GameOverDialogListener{
        void onStartNewGame();
    }
}
