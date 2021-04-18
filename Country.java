package com.example.madassignmet;

import java.util.ArrayList;
/*
Class: Country
Purpose: to store details for a country
 */
public class Country {
    private  String countryName;
    private final int flag;
    private Question[] questions;

    public Country(String countryName, int flag, Question[] questions) {
        this.countryName = countryName;
        this.flag = flag;
        this.questions = questions;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getFlag() {
        return flag;
    }

    public int numQuestLeft()
    {
        int numLeft = 0;
        for(Question question:questions)
        {
            if(!(question.isBeenAnswered()))
            {
                numLeft++;
            }
        }
        return numLeft;
    }

    public void addSpecial()
    {
        for(Question question:questions)
        {
            question.increaseSpecial();
        }
    }

    public Question[] getQuestions() {
        return questions;
    }

    public Question getQuestion(int i){return questions[i];}

    public int getNumQuestions()
    {
       return questions.length;
    }
}
