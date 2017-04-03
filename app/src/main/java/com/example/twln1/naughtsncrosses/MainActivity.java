package com.example.twln1.naughtsncrosses;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Button> buttons;
    int id;
    final Game game = new Game();

    private static final int[] BUTTON_IDS = {
            R.id.button_1,
            R.id.button_2,
            R.id.button_3,
            R.id.button_4,
            R.id.button_5,
            R.id.button_6,
            R.id.button_7,
            R.id.button_8,
            R.id.button_9,
    };
    public int index = 0;

    public static Position position = new Position();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttons = new ArrayList<Button>(BUTTON_IDS.length);

        for(int id : BUTTON_IDS) {
            Button button = (Button)findViewById(id);
            button.setOnClickListener(myListnener);
            buttons.add(button);
        }
        index++;


    }


    private View.OnClickListener myListnener = new View.OnClickListener() {
        public void onClick(View v) {

        if(!game.gameOver) {
            switch (v.getId()) {

                case R.id.button_1:
                    if(buttons.get(0).getText() == "") {
                        game.makeMove(0, buttons);
                    }
                    break;
                case R.id.button_2:
                    if(buttons.get(1).getText() == "") {
                        game.makeMove(1, buttons);
                    }
                    break;
                case R.id.button_3:
                    if(buttons.get(2).getText() == "") {
                        game.makeMove(2, buttons);
                    }
                    break;
                case R.id.button_4:
                    if(buttons.get(3).getText() == "") {
                        game.makeMove(3, buttons);
                    }
                    break;
                case R.id.button_5:
                    if(buttons.get(4).getText() == "") {
                        game.makeMove(4, buttons);
                    }
                    break;
                case R.id.button_6:
                    if(buttons.get(5).getText() == "") {
                        game.makeMove(5, buttons);
                    }
                    break;
                case R.id.button_7:
                    if(buttons.get(6).getText() == "") {
                        game.makeMove(6, buttons);
                    }
                    break;
                case R.id.button_8:
                    if(buttons.get(7).getText() == "") {
                        game.makeMove(7, buttons);
                    }

                    break;
                case R.id.button_9:
                    if(buttons.get(8).getText() == "") {
                        game.makeMove(8, buttons);
                    }
                    break;

            }

            if(game.gameOver){
                showWinner();
            }
        }

        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            resetGame();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showWinner(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(game.winner + " is the winner!");
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        resetGame();
                    }
                });
        AlertDialog a = builder1.create();
        a.show();
    }
    public void resetGame(){
        recreate();

    }


}
