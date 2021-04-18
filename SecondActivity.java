package com.example.madassignmet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        fm = getSupportFragmentManager();
        Fragment selectorFragment = fm.findFragmentById(R.id.layoutSelectorFrame);
        Fragment mapDisplayFragment = fm.findFragmentById(R.id.flagSelectorFrame);
        Fragment userHudFragment = fm.findFragmentById(R.id.hudDisplayFrame);
        ((LayoutSelectorFragment)selectorFragment).setIsSecondOrThird(true); //as its second activity should make return button invisable
        ((LayoutSelectorFragment)selectorFragment).setMyMapDisplayFragment((MapDisplayFragment)mapDisplayFragment); //giving layout selector fragment instance of map recycler view fragment to be able to call updaterv()

    }
}