package com.example.myquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    int index = 0;
    int questionId;
    int colorId;
    QuizFragment fragmentObj;
    Button trueButton;
    Button falseButton;
    QuestionBank obj = new QuestionBank();
    FileStorageManager storageObject = new FileStorageManager();
    ProgressBar simpleProgressBar;
    Boolean tag;
    int totalScore = 1;
    String getAverageDialogString;

    public void updateFragment(int questionId, int colorId) {
        FragmentManager manager = getSupportFragmentManager();
        manager.findFragmentById(R.id.fragment_container);
        fragmentObj = QuizFragment.newInstance(questionId, colorId);
        manager.beginTransaction().replace(R.id.fragment_container, fragmentObj).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get saved index state
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt("QuestionIndex");
        }

        questionId = obj.questionList.get(index).question;
        colorId = obj.colorList.get(index);
        updateFragment(questionId, colorId);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);

        simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
        simpleProgressBar.setMax(100);
        simpleProgressBar.setProgress(0);
    }

    //save state
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("QuestionIndex", index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.average:
                String message = storageObject.getData(MainActivity.this);
                int attemptCount = storageObject.countAttempts();
                int totalAverage = storageObject.countAverageScore();
                String dialogMessage = "Your correct answers are " + totalAverage + " in " + attemptCount + " attempts !!";
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(dialogMessage);
                builder.setPositiveButton(R.string.ok, null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;

            case R.id.reset_data:
                storageObject.resetData(MainActivity.this);
                break;
        }
        return true;
    }

    // True/False button click
    public void ButtonClicked(View view) {
        if (index < obj.questionList.size() - 1) {
            if (trueButton.isPressed()) {
                tag = true;
            } else {
                tag = false;
            }
            // check if answer is correct or wrong
            if (tag == obj.questionList.get(index).answer) {
                totalScore++;
                Toast.makeText(this, R.string.CorrectAnswer, Toast.LENGTH_SHORT).show();
            }
            if (tag != obj.questionList.get(index).answer) {
                Toast.makeText(this, R.string.IncorrectAnswer, Toast.LENGTH_SHORT).show();
            }
            index++;
            questionId = obj.questionList.get(index).question;
            colorId = obj.colorList.get(index);
            updateFragment(questionId, colorId);
            simpleProgressBar.setProgress(simpleProgressBar.getProgress() + 10);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Your Scores are" + "\t" + totalScore + "\t" + "out of 10 !!");
            getAverageDialogString = totalScore + "/" + 10 + "#";
            builder.setPositiveButton(R.string.save, (dialogInterface, i) -> storageObject.saveData(MainActivity.this, getAverageDialogString));
            totalScore = 0;
            builder.setNegativeButton(R.string.ignore, null);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            index = 0;
            Collections.shuffle(obj.questionList);
            Collections.shuffle(obj.colorList);
            updateFragment(obj.questionList.get(index).question, obj.colorList.get(index));
            simpleProgressBar.setProgress(0);
        }
    }

}