package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int score=0;
    int Score=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * Displays the given score for Team A.
     */
    public void increment3(View view){
        score=score+3;
                displayForTeamA(score);

    }
    public void increment2(View view){
        score=score+2;
        displayForTeamA(score);
    }
    public void increment1(View view){
        score=score+1;
        displayForTeamA(score);
    }



    public void increment3b(View view){
        Score=Score+3;
        displayForTeamB(Score);

    }
    public void increment2b(View view){
        Score=Score+2;
        displayForTeamB(Score);
    }
    public void increment1b(View view){
        Score=Score+1;
        displayForTeamB(Score);
    }
  public void reset(View view){
        score=0;
        Score=0;
      displayForTeamB(Score);
      displayForTeamA(score);

  }


    private void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team B.
     */
    private void displayForTeamB(int Score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(Score));
    }



}
