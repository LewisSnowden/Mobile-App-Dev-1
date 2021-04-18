package com.example.madassignmet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.TextView;


public class FourthActivity extends AppCompatActivity implements SpecificQuestionDisplayFragment.CallbacksSpec{
    private GameData gameData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gameData = GameData.get();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        FragmentManager fm = getSupportFragmentManager();
        TextView questionLabel = (TextView)findViewById(R.id.questionLabel);
        questionLabel.setText(gameData.getCurrQuestion().getQuestion());  //setting label at top of screen to display question

        Fragment specificQuestionDisplayFragment = fm.findFragmentById(R.id.questionSelectorFrame);
        Fragment userHudFragment = fm.findFragmentById(R.id.hudDisplayFrameFourth);
        ((UserHudFragment)userHudFragment).setThirdOrFourth(false);  //letting user hud fragment know that its fourth activity
        ((UserHudFragment)userHudFragment).setToDisplayButton(true);  //letting user hud fragment know that it should display the return button
        ((UserHudFragment)userHudFragment).updateDisplay();  //updating the display of user hud fragment

    }
    public void onSpecQuestionSelectedSpec() //to make sure code doesnt get grumpy that I dont have the callback interface implemented
    {
    }
}