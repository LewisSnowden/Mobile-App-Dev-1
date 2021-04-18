package com.example.madassignmet;

import android.content.Intent;
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

public class MapDisplayFragment extends Fragment {
    private GameData gameData = GameData.get();
    private MapAdapter adapter;
    private RecyclerView rv;
    private Country currCountry;

    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        View view = inflater.inflate(R.layout.fragment_mapdisplay,ui,false);
        rv = (RecyclerView) view.findViewById(R.id.mapRecyclerView);
        updateRV(); //make sure to update its contents
        adapter = new MapAdapter();
        rv.setAdapter(adapter);
        return view;
    }

    public void updateRV() //method to update content
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
    private class MapViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private ImageView mapImage;
        private TextView mapText;
        private Country myCountry;
        public MapViewHolder(LayoutInflater li,ViewGroup parent)
        {
            super(li.inflate(R.layout.map_display,parent,false));
            mapImage = itemView.findViewById(R.id.mapImageView);  //map image
            mapText = itemView.findViewById(R.id.mapText); //country name

            itemView.setOnClickListener(this);
        }
        public void bind(Country country)
        {
            if(country.numQuestLeft()==0) //if the country has no questions left make it invisible
            {
                myCountry = country;
                mapImage.setVisibility(View.INVISIBLE);
                mapText.setVisibility(View.INVISIBLE);
            }
            else
            {
                myCountry = country;
                mapImage.setImageResource(country.getFlag());
                mapText.setText(country.getCountryName());
            }

        }

        @Override
        public void onClick(View view){

            if(myCountry.numQuestLeft()==0)  //disable clicking if country has no questions left
            {
                Toast.makeText(getActivity(),"All questions answered for: "+myCountry.getCountryName(),Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(gameData.isSpecialIsAnswered()) //if a special question has been answered boost the selected country
                {
                    myCountry.addSpecial();  //adding 10 to every question
                    gameData.setSpecialIsAnswered(false);  //deactivate special question
                }
                gameData.setCurrCountry(myCountry);
                gameData.setCurrQuestion(myCountry.getQuestion(0)); //setting question to be defaulted to first question of country so in tablet mode specific question fragment doesnt crash
                Intent intent;
                intent = new Intent(view.getContext(),ThirdActivity.class);
                startActivity(intent);
            }
        }
    }

    private class MapAdapter extends RecyclerView.Adapter<MapViewHolder>
    {

        @Override
        public MapViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
            LayoutInflater li = LayoutInflater.from(getActivity());
            return new MapViewHolder(li,viewGroup);
        }

        public int getItemCount()
        {
            return gameData.NUMCOUNTRY;
        }

        @Override
        public void onBindViewHolder(MapViewHolder mapViewHolder, int i) {
            mapViewHolder.bind(gameData.getCountry(i));
        }
    }
}
