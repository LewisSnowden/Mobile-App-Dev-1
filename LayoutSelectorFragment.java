package com.example.madassignmet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class LayoutSelectorFragment extends Fragment {
    private GameData gameData;
    private Button onePerColBut, twoPerColBut, threePerColBut; //buttons to change the layout
    private ImageButton scrollOrientationBut;
    private MapDisplayFragment myMapDisplayFragment;
    private QuestionDisplayFragment myQuestionDisplayFragment;
    private boolean isSecondOrThird; //true is second, false is third
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        gameData = GameData.get();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        View view = inflater.inflate(R.layout.fragment_layoutselector,ui,false);
        onePerColBut = (Button) view.findViewById(R.id.onePerCollumnButton);
        twoPerColBut = (Button) view.findViewById(R.id.twoPerCollumnButton);
        threePerColBut = (Button) view.findViewById(R.id.threePerCollumnButton);
        scrollOrientationBut = (ImageButton) view.findViewById(R.id.scrollSelectorButton);

        //onclick for the horizontal scrolling or verticle scrolling button
        scrollOrientationBut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if(gameData.getHorizontalScroll()) //activity is in horizontal scroll mode
                {
                    scrollOrientationBut.setImageResource(R.drawable.ic_downward_arrow);  //change the image to be the opposite of current
                    gameData.setHorizontalScroll(false);
                    if(isSecondOrThird)
                    {
                        myMapDisplayFragment.updateRV(); //tell activity to update its layout
                    }
                    else
                    {
                        myQuestionDisplayFragment.updateRV();
                    }


                }
                else //in verticle scroll
                {
                    scrollOrientationBut.setImageResource(R.drawable.ic_forward_arrow);
                    gameData.setHorizontalScroll(true);
                    if(isSecondOrThird)
                    {
                        myMapDisplayFragment.updateRV();
                    }
                    else
                    {
                        myQuestionDisplayFragment.updateRV();
                    }
                }

            }
        });
        //changing layout to be 1 collumn
        onePerColBut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                gameData.setNumCollumns(1);
                if(isSecondOrThird)
                {
                    myMapDisplayFragment.updateRV();
                }
                else
                {
                    myQuestionDisplayFragment.updateRV();
                }
            }
        });
        //changing layout to be 2 collumns
        twoPerColBut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                gameData.setNumCollumns(2);
                if(isSecondOrThird)
                {
                    myMapDisplayFragment.updateRV();
                }
                else
                {
                    myQuestionDisplayFragment.updateRV();
                }
            }
        });
        //changing layout to be 3 collumns
        threePerColBut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                gameData.setNumCollumns(3);
                if(isSecondOrThird)
                {
                    myMapDisplayFragment.updateRV();
                }
                else
                {
                    myQuestionDisplayFragment.updateRV();
                }
            }
        });


        return view;
    }

    public void setMyMapDisplayFragment(MapDisplayFragment inMapDisplayFragment)
    {
        myMapDisplayFragment = inMapDisplayFragment;
    }

    public void setMyQuestionDisplayFragment(QuestionDisplayFragment inQuestionDisplayFragment)
    {
        myQuestionDisplayFragment = inQuestionDisplayFragment;
    }

    public void setIsSecondOrThird(boolean in)
    {
        isSecondOrThird = in;
    }
}
