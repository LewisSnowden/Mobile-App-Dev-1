package com.example.madassignmet;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SpecificQuestionDisplayFragment extends  Fragment {
    private GameData gameData = GameData.get();
    private SpecificQuestionAdapter adapter;
    private RecyclerView rv;
    private CallbacksSpec callbacks;

    public interface CallbacksSpec{
        void onSpecQuestionSelectedSpec();
    }
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        callbacks=(CallbacksSpec) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks=null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        View view = inflater.inflate(R.layout.fragment_indiv_questiondisplay,ui,false);
        rv = (RecyclerView) view.findViewById(R.id.indivQuestionRecyclerView);
        int spacingPixel = getResources().getDimensionPixelSize(R.dimen.spacing);
        rv.addItemDecoration(new SpecificQuestionItemDecoration(spacingPixel));
        updateRV();
        adapter = new SpecificQuestionDisplayFragment.SpecificQuestionAdapter();
        rv.setAdapter(adapter);
        return view;
    }

    public void updateRV()
    {
        if(gameData.getHorizontalScroll()) //horizontal scrolling
        {
            rv.setLayoutManager(new GridLayoutManager(getActivity(),gameData.getNumCollumns(),GridLayoutManager.HORIZONTAL,false));
        }
        else //vertical scrolling
        {
            rv.setLayoutManager(new GridLayoutManager(getActivity(),gameData.getNumCollumns(),GridLayoutManager.VERTICAL,false));
        }
    }

    public void refresh()
    {
        adapter.notifyDataSetChanged();
    }
    private class SpecificQuestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        private TextView answerLabel;
        private String myAnswer;

        public SpecificQuestionViewHolder(LayoutInflater li,ViewGroup parent)
        {
            super(li.inflate(R.layout.indiv_question_display,parent,false));
            itemView.setOnClickListener(this);
            answerLabel = itemView.findViewById(R.id.answer);

        }
        public void bind(String answer)
        {
            myAnswer = answer;
            answerLabel.setText(answer);


        }

        @Override
        public void onClick(View view){
            if(!(gameData.getCurrQuestion().isBeenAnswered()))
            {
                if(gameData.getCurrQuestion().getAnswer().equals(myAnswer)) //answer is corrent
                {
                    Toast.makeText(getActivity(),"Answer Correct",Toast.LENGTH_SHORT).show();
                    gameData.addPoints(gameData.getCurrQuestion().getPoint());
                    gameData.getCurrQuestion().setHasBeenAnswered(true);
                    callbacks.onSpecQuestionSelectedSpec(); //to update the ui as question has been answered blanking the question
                    if(gameData.getCurrQuestion().isSpecial()) //answered question is special so flag should be set in gameData
                    {
                        gameData.setSpecialIsAnswered(true);
                    }
                    if(gameData.hasWon())//win condition followed by restart
                    {
                        Toast.makeText(getActivity(),"YOU WIN",Toast.LENGTH_SHORT).show();
                        gameData.restart();
                        Intent intent;
                        intent = new Intent(getActivity(),MainActivity.class);
                        startActivity(intent);
                    }
                }
                else //incorrect guess
                {
                    Toast.makeText(getActivity(),"Answer Incorrect",Toast.LENGTH_SHORT).show();
                    gameData.removePoints(gameData.getCurrQuestion().getPenalty()); //removing points based on penalty
                    if(gameData.hasLost()) //checking lose condition and restarting game if it is true
                    {
                        Toast.makeText(getActivity(),"YOU LOSE",Toast.LENGTH_SHORT).show();
                        gameData.restart();
                        Intent intent;
                        intent = new Intent(getActivity(),MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
            else //question has been answered so clicking on it will result in nothing
            {
                Toast.makeText(getActivity(),"Question Has Already Been Answered",Toast.LENGTH_SHORT).show();
            }

        }


    }

    private class SpecificQuestionAdapter extends RecyclerView.Adapter<SpecificQuestionDisplayFragment.SpecificQuestionViewHolder>
    {

        @Override
        public SpecificQuestionDisplayFragment.SpecificQuestionViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
            LayoutInflater li = LayoutInflater.from(getActivity());
            return new SpecificQuestionDisplayFragment.SpecificQuestionViewHolder(li,viewGroup);
        }

        public int getItemCount()
        {
            return gameData.getCurrQuestion().getNumAnswers();
        }

        @Override
        public void onBindViewHolder(SpecificQuestionDisplayFragment.SpecificQuestionViewHolder questionViewHolder, int i) {
            questionViewHolder.bind(gameData.getCurrQuestion().getSpecificAnswer(i));
        }


    }

    private class SpecificQuestionItemDecoration extends RecyclerView.ItemDecoration  //spacing the answers correctly
    {
        private int spacing;

        public SpecificQuestionItemDecoration(int space) {
            this.spacing = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
        {
            outRect.left = spacing;
            outRect.right = spacing;
            outRect.bottom = spacing;
            outRect.top = spacing;

        }
    }


}


