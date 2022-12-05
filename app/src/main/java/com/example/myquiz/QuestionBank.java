package com.example.myquiz;

import java.util.ArrayList;

public class QuestionBank {

    ArrayList<Question> questionList = new ArrayList<>();
    ArrayList<Integer> colorList = new ArrayList<>();

    QuestionBank() {
        questionList.add(new Question(R.string.Question1, false));
        questionList.add(new Question(R.string.Question2, true));
        questionList.add(new Question(R.string.Question3, true));
        questionList.add(new Question(R.string.Question4, true));
        questionList.add(new Question(R.string.Question5, true));
        questionList.add(new Question(R.string.Question6, false));
        questionList.add(new Question(R.string.Question7, true));
        questionList.add(new Question(R.string.Question8, true));
        questionList.add(new Question(R.string.Question9, false));
        questionList.add(new Question(R.string.Question10, true));

        colorList.add(R.color.DarkRed);
        colorList.add(R.color.Indigo);
        colorList.add(R.color.Orange);
        colorList.add(R.color.DarkSlateGray);
        colorList.add(R.color.purple_200);
        colorList.add(R.color.Blue);
        colorList.add(R.color.LightCoral);
        colorList.add(R.color.DarkMagenta);
        colorList.add(R.color.Crimson);
        colorList.add(R.color.DeepPink);
    }

}
