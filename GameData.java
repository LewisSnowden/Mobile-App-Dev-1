package com.example.madassignmet;

import android.media.MediaDrm;

import java.util.ArrayList;
import java.util.Random;
/*
Class: GameData
Purpose: singleton that holdes data for the whole game, to be used by every activity
 */

public class GameData {


    public static GameData instance = null;
    public static final int NUMCOUNTRY = 16;

    private int winAmount;
    private int curAmount;
    private Random random;
    private boolean isHorizontalScroll; //true for horizontal scroll false if its verticle scroll
    private Country[] countries;
    private int numCollumns;
    private Country currCountry;  //currently selected country
    private Question currQuestion; //currently selected question
    private boolean specialIsAnswered;  //is a special question been answered given the 10 point boost



    public static GameData get()  //singleton stuff
    {
        if (instance==null)
        {
            instance = new GameData();
        }
        return instance;
    }

    private GameData()
    {
        random = new Random();
        winAmount = random.nextInt(400);  //generating point win condition
        curAmount = random.nextInt(winAmount);  //generating the current points amount giving bound of win condition
        isHorizontalScroll = false;  //defaulting to verticle scroll
        countries = initialiseCountry();  //call massive constructor for countries and their quesitons
        numCollumns = 2;  //default number of collumns is two
        specialIsAnswered = false;

    }
/*
//method to restart the game data
 */
    public void restart()
    {
        instance = new GameData();
    }

    public boolean isSpecialIsAnswered() {
        return specialIsAnswered;
    }

    public void setSpecialIsAnswered(boolean inBool) {
        this.specialIsAnswered = inBool;
    }
/*
//method to check if win condition is met
 */
    public boolean hasWon()
    {
        if (curAmount >= winAmount)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /*
    //method to check if lose condition is met
     */
    public boolean hasLost(){
        if(curAmount<=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Country getCurrCountry() {
        return currCountry;
    }

    public void setCurrCountry(Country currCountry) {
        this.currCountry = currCountry;
    }

    public Question getCurrQuestion() {
        return currQuestion;
    }

    public void setCurrQuestion(Question currQuestion) {
        this.currQuestion = currQuestion;
    }

    public void addPoints(int pointsToAdd)
    {
        curAmount = curAmount +  pointsToAdd;
    }

    public void removePoints(int pointsToRemove)
    {
        curAmount = curAmount - pointsToRemove;
    }
    public void setNumCollumns(int numCollumns)
    {
        this.numCollumns = numCollumns;
    }
    public int getNumCollumns(){
        return numCollumns;
    }
    public Country getCountry(int i)
    {
        return countries[i];
    }
    public int getWinAmount() {
        return winAmount;
    }

    public int getCurrentAmount() {
        return curAmount;
    }

    public boolean getHorizontalScroll() {
        return isHorizontalScroll;
    }

    public void setHorizontalScroll(boolean horizontalScroll) {
        isHorizontalScroll = horizontalScroll;
    }
/*
method to generate all countries and questions
 */
    private Country[] initialiseCountry()
    {

        Question[] ausQuestion = new Question[]{new Question("What is the capital of Australia","Canberra",new String[]{"Canberra","Perth","Darwin", "Melbourne","Brisbane"},10,5,false,1),new Question("What is the legal drinking age in AUS ","18",new String[]{"18","19","20","21"},15,5,true,2),new Question("What year did Australia become a federation ","1901",new String[]{"1900","1901","1920","1921"},20,5,false,3),new Question("What is the first letter of this country ","A",new String[]{"A","U","S","T","R"},10,10,false,4),new Question("What is the second letter of this country ","U",new String[]{"A","U","S","T","R"},10,10,false,5),new Question("What is the third letter of this country ","S",new String[]{"A","U","S","T","R"},5,5,false,6),new Question("What is the fourth letter of this country ","T",new String[]{"A","U","S","T","R"},10,10,false,7),new Question("What is the fifth letter of this country ","R",new String[]{"A","U","S","T","R"},20,20,false,8),new Question("What is the sixth letter of this country ","A",new String[]{"A","U","S","T","R"},20,20,false,9),new Question("What is the seventh letter of this country aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","L",new String[]{"A","U","S","T","L"},20,20,false,10)};
        Country australia = new Country("Australia",R.drawable.flag_au,ausQuestion);

        Question[] brazilQuestion = new Question[]{new Question("What is the capital of Brazil","Brasilia",new String[]{"Rio de Janeiro","Brasilia","Sao Paulo","Porto Alegre"},10,5,false,1), new Question("What is the official language of brazil","Portuguese",new String[]{"Brazilian","Spanish","Mexican","Portuguese"},10,5,true,2), new Question("How many time zones does brazil cover","3",new String[]{"1","2","3","4","5"},15,5,false,3),new Question("Which is Brazil's Biggest city","São Paulo",new String[]{"São Paulo","Rio de Janeiro","Salvador","Fortaleza"},15,5,false,4),new Question("Which is Brazil's Second Biggest city","Rio de Janeiro",new String[]{"São Paulo","Rio de Janeiro","Salvador","Fortaleza"},15,5,false,5),new Question("Which is Brazil's Third Biggest city","Salvador",new String[]{"São Paulo","Rio de Janeiro","Salvador","Fortaleza"},15,5,false,6),new Question("Which is Brazil's Fourth Biggest city","Fortaleza",new String[]{"São Paulo","Rio de Janeiro","Salvador","Fortaleza"},15,5,false,7)};
        Country brazil = new Country("Brazil",R.drawable.flag_br,brazilQuestion);

        Question[] canadaQuestion = new Question[]{new Question("Whats the capital of Canada","Ottawa",new String[]{"Toronto","Vancouver","Quebec","Ottawa"},10,5,false,1),new Question("Which is Canada's Biggest city","Toronto",new String[]{"Toronto","Vancouver","Quebec","Ottawa"},15,5,false,2), new Question("When is Canada day","1 July",new String[]{"5 July","4 July","1 July"},15,5,true,3),new Question("Which is Canada's Second Biggest city","Montréal",new String[]{"Toronto","Montréal","Calgary","Ottawa"},15,5,false,4),new Question("Which is Canada's Third Biggest city","Calgary",new String[]{"Toronto","Montréal","Calgary","Ottawa"},15,5,false,5),new Question("Which is Canada's Fourth Biggest city","Ottawa",new String[]{"Toronto","Montréal","Calgary","Ottawa"},15,5,false,6)};
        Country canada = new Country("Canada",R.drawable.flag_ca,canadaQuestion);

        Question[] germanyQuestion = new Question[]{new Question("Whats the capital of Germany","Berlin",new String[]{"Berlin","Hamburg","Munich","Cologne"},10,5,false,1),new Question("Which is Germany's Biggest city","Berlin",new String[]{"Berlin","Hamburg","Munich","Cologne"},15,5,false,2),new Question("Which is Germany's Second Biggest city","Hamburg",new String[]{"Berlin","Hamburg","Munich","Cologne"},15,5,false,3),new Question("Which is Germany's Third Biggest city","Munich",new String[]{"Berlin","Hamburg","Munich","Cologne"},15,5,false,4),new Question("Which is Germany's Fourth Biggest city","Cologne",new String[]{"Berlin","Hamburg","Munich","Cologne"},15,5,false,5)};
        Country germany = new Country("Germany",R.drawable.flag_de,germanyQuestion);

        Question[] czQuestion = new Question[]{new Question("Whats the capital of Czeck Republic","Prague",new String[]{"Prague","Brno","Ostrava","Pilsen"},10,5,false,1),new Question("Which is Czech Republics Biggest city","Prague",new String[]{"Prague","Brno","Ostrava","Pilsen"},15,5,false,2),new Question("Which is Czech Republics Second Biggest city","Brno",new String[]{"Prague","Brno","Ostrava","Pilsen"},15,5,false,3),new Question("Which is Czech Republics Third Biggest city","Ostrava",new String[]{"Prague","Brno","Ostrava","Pilsen"},15,5,false,4),new Question("Which is Czech Republics Fourth Biggest city","Pilsen",new String[]{"Prague","Brno","Ostrava","Pilsen"},15,5,false,5)};
        Country czeck= new Country("Czeck Republic",R.drawable.flag_cz,czQuestion);

        Question[] bangladeshQuestion = new Question[]{new Question("Whats the capital of Bangladesh","Dhaka",new String[]{"Dhaka","Chittagong","Khulna","Rājshāhi"},10,5,false,1),new Question("Which is Bangladesh's Biggest city","Dhaka",new String[]{"Dhaka","Chittagong","Khulna","Rājshāhi"},15,5,false,2),new Question("Which is Bangladesh's Second Biggest city","Chittagong",new String[]{"Dhaka","Chittagong","Khulna","Rājshāhi"},15,5,false,3),new Question("Which is Bangladesh's Third Biggest city","Khulna",new String[]{"Dhaka","Chittagong","Khulna","Rājshāhi"},15,5,false,4),new Question("Which is Bangladesh's Fourth Biggest city","Rājshāhi",new String[]{"Dhaka","Chittagong","Khulna","Rājshāhi"},15,5,false,5)};
        Country bangladesh = new Country("Bangladesh",R.drawable.flag_bd,bangladeshQuestion);

        Question[] japanQuestion = new Question[]{new Question("Whats the capital of Japan","Tokyo",new String[]{"Tokyo","Yokohama","Osaka","Nagoya"},10,5,false,1),new Question("Which is Japan's Biggest city","Tokyo",new String[]{"Tokyo","Yokohama","Osaka","Nagoya"},15,5,false,2),new Question("Which is Japan's Second Biggest city","Yokohama",new String[]{"Tokyo","Yokohama","Osaka","Nagoya"},15,5,false,3),new Question("Which is Japan's Third Biggest city","Osaka",new String[]{"Tokyo","Yokohama","Osaka","Nagoya"},15,5,false,4),new Question("Which is Japan's Fourth Biggest city","Nagoya",new String[]{"Tokyo","Yokohama","Osaka","Nagoya"},15,5,false,5)};
        Country japan = new Country("Japan",R.drawable.flag_jp,japanQuestion);

        Question[] koreaQuestion = new Question[]{new Question("Whats the capital of South Korea","Seoul",new String[]{"Busan","Seoul","Incheon","Daegu"},10,5,false,1),new Question("Which is South Korea's Biggest city","Seoul",new String[]{"Seoul","Busan","Incheon","Daegu"},15,5,false,2),new Question("Which is South Korea's Second Biggest city","Busan",new String[]{"Seoul","Busan","Incheon","Daegu"},15,5,false,3),new Question("Which is South Korea's Third Biggest city","Incheon",new String[]{"Seoul","Busan","Incheon","Daegu"},15,5,false,4),new Question("Which is South Korea's Fourth Biggest city","Daegu",new String[]{"Seoul","Busan","Incheon","Daegu"},15,5,false,5)};
        Country korea = new Country("South Korea",R.drawable.flag_kr,koreaQuestion);

        Question[] mexicoQuestion = new Question[]{new Question("Whats the capital of Mexico","Mexico City",new String[]{"Mexico City","Iztapalapa","Ecatepec de Morelos","Guadalajara"},10,5,false,1),new Question("Which is Mexico's Biggest city","Mexico City",new String[]{"Mexico City","Iztapalapa","Ecatepec de Morelos","Guadalajara"},15,5,false,2),new Question("Which is Mexico's Second Biggest city","Iztapalapa",new String[]{"Mexico City","Iztapalapa","Ecatepec de Morelos","Guadalajara"},15,5,false,3),new Question("Which is Mexico's Third Biggest city","Ecatepec de Morelos",new String[]{"Mexico City","Iztapalapa","Ecatepec de Morelos","Guadalajara"},15,5,false,4),new Question("Which is Mexico's Fourth Biggest city","Guadalajara",new String[]{"Mexico City","Iztapalapa","Ecatepec de Morelos","Guadalajara"},15,5,false,5)};
        Country mexico = new Country("Mexico",R.drawable.flag_mx,mexicoQuestion);

        Question[] malaysiaQuestion = new Question[]{new Question("Whats the capital of Malaysia","Kuala Lumpur",new String[]{"Kuala Lumpur","George Town of Penang","Ipoh","Johor Bahru"},10,5,false,1),new Question("Which is Malaysia Biggest city","Kuala Lumpur",new String[]{"Kuala Lumpur","George Town of Penang","Ipoh","Johor Bahru"},15,5,false,2),new Question("Which is Malaysia Second Biggest city","George Town of Penang",new String[]{"Kuala Lumpur","George Town of Penang","Ipoh","Johor Bahru"},15,5,false,3),new Question("Which is Malaysia Third Biggest city","Ipoh",new String[]{"Kuala Lumpur","George Town of Penang","Ipoh","Johor Bahru"},15,5,false,4),new Question("Which is Malaysia's Fourth Biggest city","Johor Bahru",new String[]{"London","Birmingham","Liverpool","Nottingham","Kuala Lumpur","George Town of Penang","Ipoh","Johor Bahru"},15,5,false,5)};
        Country malaysia = new Country("Malaysia",R.drawable.flag_my,malaysiaQuestion);

        Question[] netherQuestion = new Question[]{new Question("Whats the capital of Netherlands","Amsterdam",new String[]{"Amsterdam","Rotterdam","The Hague","Utrecht"},10,5,false,1),new Question("Which is Netherlands Biggest city","Amsterdam",new String[]{"Amsterdam","Rotterdam","The Hague","Utrecht"},15,5,false,2),new Question("Which is Netherlands Second Biggest city","Rotterdam",new String[]{"Amsterdam","Rotterdam","The Hague","Utrecht"},15,5,false,3),new Question("Which is Netherlands Third Biggest city","The Hague",new String[]{"Amsterdam","Rotterdam","The Hague","Utrecht"},15,5,false,4),new Question("Which is Netherlands Fourth Biggest city","Utrecht",new String[]{"Amsterdam","Rotterdam","The Hague","Utrecht"},15,5,false,5)};
        Country netherlands = new Country("Netherlands",R.drawable.flag_nl,netherQuestion);

        Question[] polandQuestion = new Question[]{new Question("Whats the capital of Poland","Warsaw",new String[]{"Warsaw","Łódź","Kraków","Wrocław"},10,5,false,1),new Question("Which is Poland's Biggest city","Warsaw",new String[]{"Warsaw","Łódź","Kraków","Wrocław"},15,5,false,2),new Question("Which is Poland's Second Biggest city","Łódź",new String[]{"Warsaw","Łódź","Kraków","Wrocław"},15,5,false,3),new Question("Which is Poland's Third Biggest city","Kraków",new String[]{"Warsaw","Łódź","Kraków","Wrocław"},15,5,false,4),new Question("Which is Poland's Fourth Biggest city","Wrocław",new String[]{"Warsaw","Łódź","Kraków","Wrocław"},15,5,false,5)};
        Country poland = new Country("Poland",R.drawable.flag_pl,polandQuestion);

        Question[] qatarQuestion = new Question[]{new Question("Whats the capital of Qatar","Doha",new String[]{"Doha","Ar Rayyān","Umm Şalāl Muḩammad","Al Wakrah"},10,5,false,1),new Question("Which is Qatar's Biggest city","Doha",new String[]{"Doha","Ar Rayyān","Umm Şalāl Muḩammad","Al Wakrah"},15,5,false,2),new Question("Which is Qatar's Second Biggest city","Ar Rayyān",new String[]{"Doha","Ar Rayyān","Umm Şalāl Muḩammad","Al Wakrah"},15,5,false,3),new Question("Which is Qatar's Third Biggest city","Umm Şalāl Muḩammad",new String[]{"Doha","Ar Rayyān","Umm Şalāl Muḩammad","Al Wakrah"},15,5,false,4),new Question("Which is Qatar's Fourth Biggest city","Al Wakrah",new String[]{"Doha","Ar Rayyān","Umm Şalāl Muḩammad","Al Wakrah"},15,5,false,5)};
        Country qatar = new Country("Qatar",R.drawable.flag_qa,qatarQuestion);

        Question[] russianQuestion = new Question[]{new Question("Whats the capital of Russia","Moscow",new String[]{"Moscow","Saint Petersburg","Novosibirsk","Yekaterinburg"},10,5,false,1),new Question("Which is Russia's Biggest city","Moscow",new String[]{"Moscow","Saint Petersburg","Novosibirsk","Yekaterinburg"},15,5,false,2),new Question("Which is Russia's Second Biggest city","Saint Petersburg",new String[]{"Moscow","Saint Petersburg","Novosibirsk","Yekaterinburg"},15,5,false,3),new Question("Which is Russia's Third Biggest city","Novosibirsk",new String[]{"Moscow","Saint Petersburg","Novosibirsk","Yekaterinburg"},15,5,false,4),new Question("Which is Russia's Fourth Biggest city","Yekaterinburg",new String[]{"Moscow","Saint Petersburg","Novosibirsk","Yekaterinburg"},15,5,false,5)};
        Country russia = new Country("Russia",R.drawable.flag_ru,russianQuestion);

        Question[] ukQuestion = new Question[]{new Question("Whats the capital of United Kingdom","London",new String[]{"London","Birmingham","Liverpool","Nottingham"},10,5,false,1),new Question("Which is United Kingdom Biggest city","London",new String[]{"London","Birmingham","Liverpool","Nottingham"},15,5,false,2),new Question("Which is United Kingdom Second Biggest city","Birmingham",new String[]{"London","Birmingham","Liverpool","Nottingham"},15,5,false,3),new Question("Which is United Kingdom Third Biggest city","Liverpool",new String[]{"London","Birmingham","Liverpool","Nottingham"},15,5,false,4),new Question("Which is United Kingdom Fourth Biggest city","Nottingham",new String[]{"London","Birmingham","Liverpool","Nottingham"},15,5,false,5)};
        Country UK = new Country("United Kingdom",R.drawable.flag_uk,ukQuestion);

        Question[] vietnamQuestion = new Question[]{new Question("Whats the capital of Vietnam","Ha Noi",new String[]{"Ha Noi","Ho Chi Minh City","Hai phong","Can Tho"},10,5,false,1),new Question("Which is Vietnam's Biggest city","Ho Chi Minh City",new String[]{"Ha Noi","Ho Chi Minh City","Hai Phong","Can Tho"},15,5,false,2),new Question("Which is Vietnam's Second Biggest city","Ha Noi",new String[]{"Ha Noi","Ho Chi Minh City","Hai Phong","Can Tho"},15,5,false,3),new Question("Which is Vietnam's Third Biggest city","Hai Phong",new String[]{"Ha Noi","Ho Chi Minh City","Hai Phong","Can Tho"},15,5,false,4),new Question("Which is Vietnam's Fourth Biggest city","Can Tho",new String[]{"Ha Noi","Ho Chi Minh City","Hai Phong","Can Tho"},15,5,false,5)};
        Country vietnam = new Country("Vietnam",R.drawable.flag_vn,vietnamQuestion);

        Country[] allCountries = new Country[]{australia,bangladesh,brazil,canada,czeck,germany,japan,korea,mexico,malaysia,netherlands,poland,qatar,russia,UK,vietnam};
        return allCountries;
    }
}
