package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // declarations

    Button startBtn;
    Button ansBtn1;
    Button ansBtn2;
    Button ansBtn3;
    Button ansBtn4;
    Button againBtn;
    TextView textHome;
    TextView textMath;
    TextView textTimer;
    TextView textScore;
    TextView textDone;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    String x = "Solve\n\n";
    String y;
    int locCorrect;
    int score = 0;
    int totalQuestions = 0;
    Random rand = new Random();
    int z;

    public void start(View view) {
        startBtn.setVisibility(View.INVISIBLE);
        textHome.setVisibility(View.INVISIBLE);
        ansBtn1.setVisibility(View.VISIBLE);
        ansBtn2.setVisibility(View.VISIBLE);
        ansBtn3.setVisibility(View.VISIBLE);
        ansBtn4.setVisibility(View.VISIBLE);
        textMath.setVisibility(View.VISIBLE);
        textTimer.setVisibility(View.VISIBLE);
        textScore.setVisibility(View.VISIBLE);
        textDone.setVisibility(View.VISIBLE);

        new CountDownTimer(30100,1000) {

            @Override
            public void onTick(long l) {
                textTimer.setText((l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                textDone.setText("Done!\n\nYou got " + score + " out of " + totalQuestions + " correct! Nice!");
                againBtn.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    public void chooseAns(View view) {
       if (Integer.toString(locCorrect).equals(view.getTag().toString())) {
            textDone.setText("Correct! Keep going!");
            score++;
        } else {
           textDone.setText("Incorrect! Try again!");
       }
       totalQuestions++;
       textScore.setText(score + "/" + totalQuestions);

       z = rand.nextInt(2);

       if(z == 0) {
           newAdditionProblem();
       } else {
           newSubtractionProblem();
       }

    }

    public void newAdditionProblem() {
        y = " + ";
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        answers.clear();

        locCorrect = rand.nextInt(4);
        for(int i=0; i<4; i++) {
            if (i == locCorrect) {
                answers.add(a+b);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == a+b) {
                    wrongAnswer = rand.nextInt(41);
                }

                answers.add(wrongAnswer);

            }
        }

        ansBtn1.setText(Integer.toString(answers.get(0)));
        ansBtn2.setText(Integer.toString(answers.get(1)));
        ansBtn3.setText(Integer.toString(answers.get(2)));
        ansBtn4.setText(Integer.toString(answers.get(3)));

        textMath.setText(x + Integer.toString(a) + y + Integer.toString(b));

    }

    public void newSubtractionProblem() {
        y = " - ";
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        answers.clear();

        locCorrect = rand.nextInt(4);
        for(int i=0; i<4; i++) {
            if (i == locCorrect) {
                answers.add(a-b);
            } else {
                int wrongAnswer = rand.nextInt(41 + 41) - 41;
                while(wrongAnswer == a-b) {
                    wrongAnswer = rand.nextInt(41 + 41) - 41;
                }

                answers.add(wrongAnswer);

            }
        }

        ansBtn1.setText(Integer.toString(answers.get(0)));
        ansBtn2.setText(Integer.toString(answers.get(1)));
        ansBtn3.setText(Integer.toString(answers.get(2)));
        ansBtn4.setText(Integer.toString(answers.get(3)));

        textMath.setText(x + Integer.toString(a) + y + Integer.toString(b));
    }

    public void playAgain(View view) {
        score = 0;
        totalQuestions = 0;
        textTimer.setText("30s");
        textScore.setText(score + "/" + totalQuestions);
        againBtn.setVisibility(View.INVISIBLE);
        newAdditionProblem();

        new CountDownTimer(30100,1000) {

            @Override
            public void onTick(long l) {
                textTimer.setText((l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                textDone.setText("Done!\n\nYou got " + score + " out of " + totalQuestions + " correct! Nice!");
                againBtn.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.btnStart);
        ansBtn1 = findViewById(R.id.btnAns1);
        ansBtn2 = findViewById(R.id.btnAns2);
        ansBtn3 = findViewById(R.id.btnAns3);
        ansBtn4 = findViewById(R.id.btnAns4);
        againBtn = findViewById(R.id.btnAgain);
        textHome = findViewById(R.id.textHome);
        textMath = findViewById(R.id.textMath);
        textTimer = findViewById(R.id.textTimer);
        textScore = findViewById(R.id.textScore);
        textDone = findViewById(R.id.textDone);



        newAdditionProblem();
    }
}