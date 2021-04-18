package com.example.madassignmet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*
first activity that is pulled up on launch
 */
public class MainActivity extends AppCompatActivity {
    private GameData gameData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameData = GameData.get();
        Button startButton;
        TextView startPoint,winPoint;

        startButton = (Button)findViewById(R.id.startButtonMain);
        startPoint = (TextView)findViewById(R.id.startPointLabel);
        winPoint = (TextView)findViewById(R.id.winPointLabel);
        winPoint.setText("Win: "+gameData.getWinAmount());  //giving values to labels on start screen
        startPoint.setText("Points: "+gameData.getCurrentAmount());

        //on click listner for start button to launch second activity
            startButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                Intent intent;

                    intent = new Intent(MainActivity.this,SecondActivity.class);
                    startActivity(intent);

                }
            });
    }
}