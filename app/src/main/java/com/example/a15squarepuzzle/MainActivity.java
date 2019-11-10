package com.example.a15squarepuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @author Taylor Fukumoto
 * @date 7 November 2019
 *
 */
public class MainActivity extends AppCompatActivity {
    protected ArrayList<Button> squareArray = new ArrayList<>(); //array of all 16 buttons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PuzzleController myListener = new PuzzleController();

        Button reset = (Button)findViewById(R.id.resetButton);
        reset.setOnClickListener(myListener);
        Button square1 = (Button)findViewById(R.id.button);
        square1.setOnClickListener(myListener);
        Button square2 = (Button)findViewById(R.id.button2);
        square2.setOnClickListener(myListener);
        Button square3 = (Button)findViewById(R.id.button3);
        square3.setOnClickListener(myListener);
        Button square4 = (Button)findViewById(R.id.button4);
        square4.setOnClickListener(myListener);
        Button square5 = (Button)findViewById(R.id.button5);
        square5.setOnClickListener(myListener);
        Button square6 = (Button)findViewById(R.id.button6);
        square6.setOnClickListener(myListener);
        Button square7 = (Button)findViewById(R.id.button7);
        square7.setOnClickListener(myListener);
        Button square8 = (Button)findViewById(R.id.button8);
        square8.setOnClickListener(myListener);
        Button square9 = (Button)findViewById(R.id.button9);
        square9.setOnClickListener(myListener);
        Button square10 = (Button)findViewById(R.id.button10);
        square10.setOnClickListener(myListener);
        Button square11 = (Button)findViewById(R.id.button11);
        square11.setOnClickListener(myListener);
        Button square12 = (Button)findViewById(R.id.button12);
        square12.setOnClickListener(myListener);
        Button square13 = (Button)findViewById(R.id.button13);
        square13.setOnClickListener(myListener);
        Button square14 = (Button)findViewById(R.id.button14);
        square14.setOnClickListener(myListener);
        Button square15 = (Button)findViewById(R.id.button15);
        square15.setOnClickListener(myListener);
        Button square16 = (Button)findViewById(R.id.button16);
        square16.setOnClickListener(myListener);


        //adds each button to squareArray
        this.squareArray.add(square1);
        this.squareArray.add(square2);
        this.squareArray.add(square3);
        this.squareArray.add(square4);
        this.squareArray.add(square5);
        this.squareArray.add(square6);
        this.squareArray.add(square7);
        this.squareArray.add(square8);
        this.squareArray.add(square9);
        this.squareArray.add(square10);
        this.squareArray.add(square11);
        this.squareArray.add(square12);
        this.squareArray.add(square13);
        this.squareArray.add(square14);
        this.squareArray.add(square15);
        this.squareArray.add(square16);

        //passes squareArray to listener class through setSquareArray method
        myListener.setSquareArray(this.squareArray);

    }

}
