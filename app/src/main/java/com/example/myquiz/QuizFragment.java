package com.example.myquiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QuizFragment extends Fragment {
    TextView quesText;
    int question;
    int color;

    public static QuizFragment newInstance(int quesID, int colorId) {

        Bundle args = new Bundle();
        args.putInt("QuestionId", quesID);
        args.putInt("ColorId", colorId);
        QuizFragment fragment = new QuizFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //   Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        quesText = (TextView) view.findViewById(R.id.quizText);
        question = getArguments().getInt("QuestionId");
        color = getArguments().getInt("ColorId");
        quesText.setText(question);
        quesText.setBackgroundResource(color);
        return view;
    }
}