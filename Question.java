package com.example.madassignmet;

import java.util.ArrayList;
/*
class Question
purpose: store the data for individual question
 */
public class Question {
    private String question;
    private String answer;
    private String[] answers;
    private int point;
    private  final int penalty;
    private boolean beenAnswered;
    private  boolean special;
    private int questionNumber;

    public Question(String question, String answer, String[] answers,int point, int penalty,boolean special,int questionNumber) {
        this.question = question;
        this.answer = answer;
        this.answers = answers;
        this.point = point;
        this.penalty = penalty;
        this.beenAnswered = false;
        this.special = special;
        this.questionNumber = questionNumber;
    }

    public int getPoint() {
        return point;
    }

    public int getNumAnswers()
    {
        return answers.length;
    }
    public String getSpecificAnswer(int i)
    {
        return answers[i];
    }

    public int getPenalty() {
        return penalty;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String[] getAnswers() {
        return answers;
    }

    public boolean isBeenAnswered(){
        return beenAnswered;
    }

    public void setHasBeenAnswered(boolean inHasBeen)
    {
        beenAnswered =inHasBeen;
    }

    public boolean isSpecial(){
        return special;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void increaseSpecial()  //method that makes the special increase easy
    {
        this.point = this.point +10;
    }
}
