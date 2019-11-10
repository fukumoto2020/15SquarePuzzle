package com.example.a15squarepuzzle;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Taylor Fukumoto
 * @date 7 November 2019
 *
 * PuzzleController is the controller class
 * It initializes the puzzle board, listens for user clicks,
 * and checks move validity
 *
 */
public class PuzzleController implements View.OnClickListener {
    protected int blankSquareIndex; //index of blank square
    protected ArrayList<Button> squareArray = new ArrayList<>();


    //array of values between 1 and 15 with one blank
    protected ArrayList<String> puzzleLayout = new ArrayList<>();

    private static final String TAG = "onClick";

    //empty constructor
    public PuzzleController(){ }

    /**
     * setSquareArray method initializes the puzzle board
     *
     * @param mySquareArray: array of the 16 buttons passed in from MainActivity class
     * adds numbers 1-15 and one blank to puzzleLayout, an ArrayList of strings that contain
     *                     the values displayed on each square in puzzle
     *
     * calls shuffle puzzle to shuffle puzzleLayout array to then assign to buttons in
     *                     squareArray
     *
     */
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

    /**
     * shufflePuzzle method shuffles the puzzleLayout array randomly, and then checks that the shuffled
     *      array is winnable
     *      If it's not winnable, reshuffles. If it is winnable, assigns the puzzleLayout values to
     *          the button texts
     *
     *
     */
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


    /**
     * onClick method is the listener for user tapping a square to move in puzzle
     *
     * onClick is also used to listen for reset button clicks- if reset button is clicked,
     *      calls the shuffle array
     *
     * checks that the square user selected is a valid square to move, and if it is,
     *      assigns the blank square's text to the selected square and clears selected
     *      square's text
     *
     */
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
                        //checks if square is in correct position, if it is, changes text color
                        if (this.isCorrect(this.blankSquareIndex)) {
                            this.squareArray.get(this.blankSquareIndex).setTextColor(Color.BLUE);
                        } else {
                            this.squareArray.get(this.blankSquareIndex).setTextColor(Color.WHITE);
                        }
                    }
                }
            }

            //checks if game is won, if so, changes every square's background color to green
            if (this.isWin()) {
                for (int i = 0; i < this.squareArray.size(); i++) {
                    this.squareArray.get(i).setBackgroundColor(Color.GREEN);
                }
            }
        }
    }

    /**
     * isValid method checks if selected square is valid to move
     *
     * @param selectedIndex: selected square's index
     *
     * @return true if is valid, false if not valid
     *
     */
    public boolean isValid(int selectedIndex){
        //checks if blank square is to the left of square
        if(selectedIndex != 0 && selectedIndex !=4 && selectedIndex !=8 && selectedIndex!=12
                && this.squareArray.get(selectedIndex - 1).getText().equals(" ")){
            this.blankSquareIndex = selectedIndex - 1;
            return true;
        }
        //checks if blank square is to the right of square
        else if(selectedIndex !=3 && selectedIndex !=7 && selectedIndex !=11 && selectedIndex!=15 &&
                this.squareArray.get(selectedIndex +1).getText().equals(" ")){
            this.blankSquareIndex = selectedIndex + 1;
            return true;
        }
        //checks if blank square is above square
        else if(selectedIndex !=0 && selectedIndex !=1 && selectedIndex !=2 && selectedIndex !=3
                && this.squareArray.get(selectedIndex - 4).getText().equals(" ")){
            this.blankSquareIndex = selectedIndex - 4;
            return true;
        }
        //checks if blank square is below square
        else if(selectedIndex !=12 && selectedIndex !=13 && selectedIndex !=14 && selectedIndex !=15
                && this.squareArray.get(selectedIndex + 4).getText().equals(" ")){
            this.blankSquareIndex = selectedIndex + 4;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * isCorrect method checks if square is in correct spot on the board
     *      if it is, returns true
     *
     * @param index: index of square to be checked if position is correct
     *
     */
    public boolean isCorrect(int index){
        //checks if square's text at "index" is equal to what number it should be
        if(this.squareArray.get(index).getText().equals(String.valueOf(index+1))){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * isWin method checks if all squares are in the right spot
     *
     * iterates through all squares in square array and if it goes through the whole array
     *      without finding an incorrect square, returns true
     *
     */
    public boolean isWin(){
        //checks every square in the squareArray for a square that is not correct
        for(int i = 0; i<this.squareArray.size(); i++){
            if(!isCorrect(i)){
                return false;
            }
        }
        return true;
    }

}
