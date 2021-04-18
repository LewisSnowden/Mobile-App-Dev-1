package com.example.madassignmet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity implements QuestionDisplayFragment.Callbacks,SpecificQuestionDisplayFragment.CallbacksSpec{

    FragmentManager fm;
    GameData gameData = GameData.get();
    TextView questionLabel;
    private Fragment questionDisplayFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masterdetail); //instead of actual activity points to variable in refs which chooses if its phone or tablet
        if(!(findViewById(R.id.specQuestionFrag)==null)) //if its tablet mode need to update question, if its phone mode this label wont exist
        {
            questionLabel = (TextView)findViewById(R.id.questionLabel);
            questionLabel.setText(gameData.getCurrQuestion().getQuestion());
        }

        fm = getSupportFragmentManager();

        Fragment selectorFragment = fm.findFragmentById(R.id.layoutSelectorFrame);
        questionDisplayFragment = fm.findFragmentById(R.id.questionSelectorFrame);
        Fragment userHudFragment = fm.findFragmentById(R.id.hudDisplayFrame);

        ((UserHudFragment)userHudFragment).setThirdOrFourth(true); //act like a 3rd activty for purpose of return button
        ((UserHudFragment)userHudFragment).setToDisplayButton(true);
        ((UserHudFragment)userHudFragment).updateDisplay();

        ((LayoutSelectorFragment)selectorFragment).setIsSecondOrThird(false); //is third actity for layout selector
        ((LayoutSelectorFragment)selectorFragment).setMyQuestionDisplayFragment((QuestionDisplayFragment)questionDisplayFragment); //give reference to layout selector so it call call question displays updateRv()
    }

    @Override
    public void onQuestionSelected() //callback for updating recycler views on tablets or launch activity for phone when question is selected
    {
        if(findViewById(R.id.specQuestionFrag)==null) //is true for phone, false for tablet
        {
            Intent intent = new Intent(this,FourthActivity.class); //launch 4th activity
            startActivity(intent);
        }
        else
        {
            Fragment specificQuestionFrag = fm.findFragmentById(R.id.specQuestionFrag); //call refresh for right side of tablet view aka the answer select
            ((SpecificQuestionDisplayFragment)specificQuestionFrag).refresh(); //refresh answer select
            ((QuestionDisplayFragment)questionDisplayFragment).refresh(); //refresh question display
            questionLabel.setText(gameData.getCurrQuestion().getQuestion()); //update to new question
        }
    }

    @Override
    public void onSpecQuestionSelectedSpec() //refresh for when answer is selected
    {
            Fragment specificQuestionFrag = fm.findFragmentById(R.id.specQuestionFrag);
            ((SpecificQuestionDisplayFragment)specificQuestionFrag).refresh();
            ((QuestionDisplayFragment)questionDisplayFragment).refresh();
    }

}