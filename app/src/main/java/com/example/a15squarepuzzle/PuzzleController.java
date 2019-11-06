package com.example.a15squarepuzzle;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;


public class PuzzleController implements View.OnClickListener {
    protected int blankSquareIndex; //index of blank square
    protected ArrayList<Button> squareArray = new ArrayList<>();


    //array of values between 1 and 15 with one blank
    protected ArrayList<String> puzzleLayout = new ArrayList<>();

    private static final String TAG = "onClick";


    public PuzzleController(){ }


    public void setSquareArray(ArrayList<Button> mySquareArray){
        this.squareArray = mySquareArray;

        puzzleLayout.add("1");
        puzzleLayout.add("2");
        puzzleLayout.add("3");
        puzzleLayout.add("4");
        puzzleLayout.add("5");
        puzzleLayout.add("6");
        puzzleLayout.add("7");
        puzzleLayout.add("8");
        puzzleLayout.add("9");
        puzzleLayout.add("10");
        puzzleLayout.add("11");
        puzzleLayout.add("12");
        puzzleLayout.add("13");
        puzzleLayout.add("14");
        puzzleLayout.add("15");
        puzzleLayout.add(" ");

        this.shufflePuzzle();

    }

    public void shufflePuzzle(){
        //randomizes array order to randomize puzzle
        Collections.shuffle(puzzleLayout);

        //makes puzzle winnable
        int n = 0; //number of squares that appear after selected square that are less than square's value
        int sum = 0; // sum of each square's n
        for(int a = 0; a<this.squareArray.size(); a++){
            for(int b = a+1; b<this.squareArray.size(); b++){
                if(!this.puzzleLayout.get(b).equals(" ") && !this.puzzleLayout.get(a).equals(" ") && Integer.parseInt(this.puzzleLayout.get(b)) < Integer.parseInt(this.puzzleLayout.get(a))){
                    n++;
                }
            }
            sum+=n;
            n = 0;
        }

        //if sum is even, puzzle is not solvable so it reshuffles until sum is odd
        if(sum%2 == 0){
            this.shufflePuzzle();
        }
        //if sum is odd(solvable), sets the square's text to the content of puzzleLayout
        else {
            for (int i = 0; i < this.squareArray.size(); i++) {
                this.squareArray.get(i).setText(this.puzzleLayout.get(i));
                if (this.isCorrect(i)) {
                    this.squareArray.get(i).setTextColor(Color.BLUE);
                } else {
                    this.squareArray.get(i).setTextColor(Color.WHITE);
                }
            }
        }

    }


    public void onClick(View v) {
        if(v.getId() == R.id.resetButton){
            Log.i(TAG, "reset pressed");
            this.shufflePuzzle();
        }
        else {
            //finds the index of the clicked button, checks if that button is adjacent to the blank button, and if so,
            //sets that blank button's text to the clicked button's text, and clears clicked button's text
            for (int i = 0; i < this.squareArray.size(); i++) {
                if (this.squareArray.get(i).getId() == v.getId()) {
                    if (this.isValid(i)) {
                        this.squareArray.get(this.blankSquareIndex).setText(this.squareArray.get(i).getText());
                        this.squareArray.get(i).setText(" ");
                        if (this.isCorrect(this.blankSquareIndex)) {
                            this.squareArray.get(this.blankSquareIndex).setTextColor(Color.BLUE);
                        } else {
                            this.squareArray.get(this.blankSquareIndex).setTextColor(Color.WHITE);
                        }
                    }
                }
            }

            if (this.isWin()) {
                for (int i = 0; i < this.squareArray.size(); i++) {
                    this.squareArray.get(i).setBackgroundColor(Color.GREEN);
                }
            }
        }

    }

    public boolean isValid(int selectedIndex){
        if(selectedIndex != 0 && selectedIndex !=4 && selectedIndex !=8 && selectedIndex!=12
                && this.squareArray.get(selectedIndex - 1).getText().equals(" ")){
            this.blankSquareIndex = selectedIndex - 1;
            return true;
        }
        else if(selectedIndex !=3 && selectedIndex !=7 && selectedIndex !=11 && selectedIndex!=15 &&
                this.squareArray.get(selectedIndex +1).getText().equals(" ")){
            this.blankSquareIndex = selectedIndex + 1;
            return true;
        }
        else if(selectedIndex !=0 && selectedIndex !=1 && selectedIndex !=2 && selectedIndex !=3
                && this.squareArray.get(selectedIndex - 4).getText().equals(" ")){
            this.blankSquareIndex = selectedIndex - 4;
            return true;
        }
        else if(selectedIndex !=12 && selectedIndex !=13 && selectedIndex !=14 && selectedIndex !=15
                && this.squareArray.get(selectedIndex + 4).getText().equals(" ")){
            this.blankSquareIndex = selectedIndex + 4;
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isCorrect(int index){
        if(this.squareArray.get(index).getText().equals(String.valueOf(index+1))){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isWin(){
        for(int i = 0; i<this.squareArray.size(); i++){
            if(!this.squareArray.get(i).getText().equals(String.valueOf(i+1))){
                return false;
            }
        }
        return true;
    }
}
