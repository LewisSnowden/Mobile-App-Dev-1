package com.example.madassignmet;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
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

public class QuestionDisplayFragment extends  Fragment {
    private GameData gameData = GameData.get();
    private QuestionAdapter adapter;
    private RecyclerView rv;
    private Callbacks mCallBacks;

    public interface Callbacks //callback to allow actvity to update displays
    {
        void onQuestionSelected();
    }
    @Override //callback method
    public void onAttach(Context context)
    {
        super.onAttach(context);
        mCallBacks=(Callbacks)context;
    }

    @Override //callback method
    public void onDetach() {
        super.onDetach();
        mCallBacks = null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        View view = inflater.inflate(R.layout.fragment_questiondisplay,ui,false);
        rv = (RecyclerView) view.findViewById(R.id.questionRecyclerView);
        updateRV();
        adapter = new QuestionDisplayFragment.QuestionAdapter();
        rv.setAdapter(adapter);
        return view;
    }
     /*
     method for refreshing recycler view after question has been correctly answered
      */
    public void refresh()
    {
        adapter.notifyDataSetChanged();
    }
    /*
    method for updating recycler view when the user clicks layout changing buttons
     */
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
    private class QuestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView questionNumberLabel,questionPointLabel,penaltyPointLabel,specialLabel;
        private Question myQuestion;

        public QuestionViewHolder(LayoutInflater li,ViewGroup parent)
        {
            super(li.inflate(R.layout.question_display,parent,false));
            questionNumberLabel = itemView.findViewById(R.id.questionNumLabel);  //question number
            questionPointLabel = itemView.findViewById(R.id.questionPointLabel); //question points
            penaltyPointLabel = itemView.findViewById(R.id.penaltyPointLabel); //question penalty
            specialLabel = itemView.findViewById(R.id.specialLabel); //is it a special question
             itemView.setOnClickListener(this);
        }
        public void bind(Question question)
        {
            if(question.isBeenAnswered()) //if question has been answered it should be invisible
            {
                questionNumberLabel.setVisibility(View.INVISIBLE);
                questionPointLabel.setVisibility(View.INVISIBLE);
                penaltyPointLabel.setVisibility(View.INVISIBLE);
                specialLabel.setVisibility(View.INVISIBLE);
                myQuestion = question;
            }
            else //not answered so should be visible
            {
                questionNumberLabel.setText("Question: "+question.getQuestionNumber());
                questionPointLabel.setText("Points: "+question.getPoint());
                penaltyPointLabel.setText("Penalty: "+question.getPenalty());
                if(question.isSpecial())
                {
                    specialLabel.setText("Is Special");
                }
                else
                {
                    specialLabel.setText("Not Special");
                }
                myQuestion = question;
            }
        }

        @Override
        public void onClick(View view){
            if(myQuestion.isBeenAnswered()) //if its been answered clicking on question should do nothing
            {
                Toast.makeText(getActivity(),"Question has already been answered",Toast.LENGTH_SHORT).show();
            }
            else //not answered so fine
            {
                Toast.makeText(getActivity(),"Pressed question: "+myQuestion.getQuestion(),Toast.LENGTH_SHORT).show();
                gameData.setCurrQuestion(myQuestion);
                mCallBacks.onQuestionSelected(); //question has been clicked so notify activity that fragments need updating
            }
        }
    }

    private class QuestionAdapter extends RecyclerView.Adapter<QuestionDisplayFragment.QuestionViewHolder>
    {

        @Override
        public QuestionDisplayFragment.QuestionViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
            LayoutInflater li = LayoutInflater.from(getActivity());
            return new QuestionDisplayFragment.QuestionViewHolder(li,viewGroup);
        }

        public int getItemCount()
        {
            return gameData.getCurrCountry().getNumQuestions();
        }

        @Override
        public void onBindViewHolder(QuestionDisplayFragment.QuestionViewHolder questionViewHolder, int i) {
            questionViewHolder.bind(gameData.getCurrCountry().getQuestion(i));
        }


    }


}


