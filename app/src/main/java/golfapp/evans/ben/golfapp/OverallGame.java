package golfapp.evans.ben.golfapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class OverallGame extends WearableActivity {
    private int totalScoreNumber;
    private List<Integer> holeScores;
    private GridView holeScoresListView;
    private TextView totalScore;
    private Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_game);

        initializeUI();
        initializeValues();
        onUpdate();
        initializeActionListener();

        // Enables Always-on
        setAmbientEnabled();
    }

    private void initializeUI() {
        totalScore = findViewById(R.id.totalScore);
        resetBtn = findViewById(R.id.resetBtn);
    }

    private void initializeValues() {
        // Retrieve the totalScoreNumber from the main activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            totalScoreNumber = extras.getInt("total_score_number");
            holeScores = extras.getIntegerArrayList("hole_scores_list");
        }
    }

    private void onUpdate() {
        String totalScoreString = String.format(getString(R.string.total_score), totalScoreNumber);
        totalScore.setText(totalScoreString);

        ArrayList<String> filteredHoleScores = new ArrayList<>();
        for (int i = 0; i < holeScores.size(); i++) {
            int score = holeScores.get(i);
            if (score != 0) {
                filteredHoleScores.add(String.format(getString(R.string.hole_number_with_score), (i + 1), score));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredHoleScores);
        holeScoresListView = findViewById(R.id.holeScores);
        holeScoresListView.setAdapter(adapter);
    }

    private void initializeActionListener() {
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
////                if (totalScoreNumber != 0) {
//                    initializeValues();
//                    onUpdate();
////                }
//                finish();
            }
        });
    }
}
