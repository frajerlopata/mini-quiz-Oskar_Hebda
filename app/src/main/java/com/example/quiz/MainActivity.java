package com.example.quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView questionText, scoreText;
    Button btnA, btnB, btnC, startButton, resetButton;

    List<Question> questions = new ArrayList<>();
    List<Question> quizQuestions;

    int currentQuestionIndex = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);

        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);

        startButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);

        initQuestions();
        hideQuiz();

        startButton.setOnClickListener(v -> startQuiz());
        resetButton.setOnClickListener(v -> resetQuiz());

        View.OnClickListener answerListener = v -> {
            Button clicked = (Button) v;
            checkAnswer(clicked.getText().toString());
        };

        btnA.setOnClickListener(answerListener);
        btnB.setOnClickListener(answerListener);
        btnC.setOnClickListener(answerListener);
    }

    private void initQuestions() {
        questions.add(new Question("Stolica Włoch to:", "Rzym", "Paryż", "Madryt", "Rzym"));
        questions.add(new Question("2+2 =", "3", "4", "5", "4"));
        questions.add(new Question("Kolor nieba:", "Zielony", "Niebieski", "Czerwony", "Niebieski"));
        questions.add(new Question("Największa planeta:", "Mars", "Ziemia", "Jowisz", "Jowisz"));
        questions.add(new Question("Stolica Polski:", "Kraków", "Warszawa", "Gdańsk", "Warszawa"));
        questions.add(new Question("5 * 3 =", "15", "10", "20", "15"));
    }

    private void startQuiz() {
        score = 0;
        currentQuestionIndex = 0;

        Collections.shuffle(questions);
        quizQuestions = questions.subList(0, 5);

        scoreText.setText("Wynik: 0");
        showQuiz();
        loadQuestion();
    }

    private void loadQuestion() {
        if (currentQuestionIndex < quizQuestions.size()) {
            Question q = quizQuestions.get(currentQuestionIndex);

            questionText.setText(q.question);
            btnA.setText(q.optionA);
            btnB.setText(q.optionB);
            btnC.setText(q.optionC);
        } else {
            questionText.setText("Koniec quizu! Twój wynik: " + score + " / 5");
            hideAnswers();
        }
    }

    private void checkAnswer(String answer) {
        Question q = quizQuestions.get(currentQuestionIndex);

        if (answer.equals(q.correctAnswer)) {
            score++;
        }

        scoreText.setText("Wynik: " + score);
        currentQuestionIndex++;
        loadQuestion();
    }

    private void resetQuiz() {
        score = 0;
        scoreText.setText("Wynik: 0");
        questionText.setText("");
        hideQuiz();
    }

    private void showQuiz() {
        questionText.setVisibility(View.VISIBLE);
        btnA.setVisibility(View.VISIBLE);
        btnB.setVisibility(View.VISIBLE);
        btnC.setVisibility(View.VISIBLE);
    }

    private void hideQuiz() {
        questionText.setVisibility(View.GONE);
        btnA.setVisibility(View.GONE);
        btnB.setVisibility(View.GONE);
        btnC.setVisibility(View.GONE);
    }

    private void hideAnswers() {
        btnA.setVisibility(View.GONE);
        btnB.setVisibility(View.GONE);
        btnC.setVisibility(View.GONE);
    }
}