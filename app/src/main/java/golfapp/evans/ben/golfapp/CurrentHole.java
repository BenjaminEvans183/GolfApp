package golfapp.evans.ben.golfapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import golfapp.evans.ben.golfapp.fragments.CurrentHoleFragment;

public class CurrentHole extends WearableActivity FragmentActivity {
    private TextView currentHole;
    private TextView currentHoleScore;
    private int totalScoreNumber;
    private int currentHoleNumber;
    private int currentHoleScoreNumber;
    private ArrayList<Integer> holeScores;
    private Button submitBtn;
    private Button subtractBtn;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_hole);

        CurrentHoleFragment currentHoleFragment = new CurrentHoleFragment();
        FragmentManager fragmentManager = getFragmentManager().beginTransaction()..add(R.id.activity_container, currentHoleFragment);

        initializeUI();
        initializeValues();
        initializeActionListener();
        onUpdate();

        // Enables Always-on
        setAmbientEnabled();
    }

    private void initializeUI() {
        currentHole = findViewById(R.id.currentHole);
        currentHoleScore = findViewById(R.id.currentHoleScore);
        submitBtn = findViewById(R.id.submitBtn);
        subtractBtn = findViewById(R.id.subtractBtn);
        addBtn = findViewById(R.id.addBtn);
    }

    private void initializeValues() {
        currentHoleNumber = 1;
        currentHoleScoreNumber = 0;
        totalScoreNumber = 0;

        holeScores = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            holeScores.add(0);
        }
    }

    private void onUpdate() {
        String currentHoleString = String.format(getString(R.string.hole_number), currentHoleNumber);
        currentHole.setText(currentHoleString);

        String currentHoleScoreString = String.format(getString(R.string.current_score), currentHoleScoreNumber);
        currentHoleScore.setText(currentHoleScoreString);
    }

    private void initializeActionListener() {
//        subtractBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(currentHoleScoreNumber != 0) {
//                    currentHoleScoreNumber--;
//                    onUpdate();
//                }
//            }
//        });

        subtractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CurrentHole.this, OverallGame.class);
                intent.putExtra("total_score_number", totalScoreNumber);
                intent.putIntegerArrayListExtra("hole_scores_list", holeScores);

                startActivity(intent);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentHoleScoreNumber++;
                onUpdate();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentHoleScoreNumber > 0 && currentHoleNumber < 19) {
                    totalScoreNumber += currentHoleScoreNumber;
                    holeScores.set(currentHoleNumber - 1, currentHoleScoreNumber);
                    currentHoleNumber++;
                    currentHoleScoreNumber = 0;
                    onUpdate();
                }
            }
        });
    }
}
