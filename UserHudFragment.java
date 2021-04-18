package com.example.madassignmet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class UserHudFragment extends Fragment {

    private GameData gameData=GameData.get();
    private Button returnButton;
    private TextView currPointLabel, currPointValue,targetPointLabel,targetPointValue;
    private boolean toDisplayButton=false; //determines if it should display the return button
    private boolean thirdOrFourth; //true for third false for 4th   <- used to return to either activity two or 3 depending
    View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        view = inflater.inflate(R.layout.fragment_userhud,ui,false);

        returnButton = (Button) view.findViewById(R.id.returnButton);
        currPointLabel = (TextView) view.findViewById(R.id.currentPointsLabel);
        currPointValue = (TextView) view.findViewById(R.id.currentPointsValue);
        targetPointLabel= (TextView) view.findViewById(R.id.targetPointsLabel);
        targetPointValue= (TextView) view.findViewById(R.id.targetPointsValue);

        currPointValue.setText("|"+gameData.getCurrentAmount());
        targetPointValue.setText("|"+gameData.getWinAmount());
        if(toDisplayButton)  //if it should make the return button clickable and visible
        {
            returnButton.setClickable(true);
            returnButton.setVisibility(View.VISIBLE);
        }
        else{
            returnButton.setClickable(false);
            returnButton.setVisibility(View.INVISIBLE);
        }
        returnButton.setOnClickListener(new View.OnClickListener()  //onclick for return button
        {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(thirdOrFourth)  //if it is the user hud in third activity should return to second activity
                {
                    intent = new Intent(view.getContext(),SecondActivity.class);
                    startActivity(intent);
                }
                else //if its the fourth actvity
                {
                    if(gameData.isSpecialIsAnswered())  //has a special question been answered correctly should return to map select
                    {
                        intent = new Intent(view.getContext(),SecondActivity.class);
                    }
                    else //if its a normal question should return to third activity
                    {
                        intent = new Intent(view.getContext(),ThirdActivity.class);
                    }
                    startActivity(intent);
                }


            }
        });

        return view;
    }
    public void updateDisplay() //need to be called on loading the 3rd activity as if not the button will remain invisible from creation at second activity
    {
        if(toDisplayButton) //if the button should be displayed or not
        {
            returnButton.setClickable(true);
            returnButton.setVisibility(View.VISIBLE);
        }
        else{
            returnButton.setClickable(false);
            returnButton.setVisibility(View.INVISIBLE);
        }
    }
    public void setToDisplayButton(boolean inToDisplayButton)
    {
        toDisplayButton = inToDisplayButton;
    }

    public void setThirdOrFourth(boolean in) {
        thirdOrFourth = in;
    }
}
