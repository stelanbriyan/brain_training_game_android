package org.incosyz.stelan.braintraininggame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

/**
 * @author Stelan Briyan
 */
public class PlayFragment extends Fragment {
    private TextView guessLabel;
    private double answer;
    private Random random = new Random();

    String operations[] = {"/", "+", "-", "*"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_play,
                container, false);

        this.guessLabel = rootView.findViewById(R.id.guess_label);
        this.guessLabel.setText("Guess : ".concat(generateGuessLabel()).concat(" ?"));
        return rootView;
    }

    private String generateGuessLabel() {
        int level = PlayActivity.gameLevel;

        int[] numbers = getNumbers(level);

        String result = "";

        for (int i = 0; i < numbers.length; i++) {


            result += numbers[i];
            if (i != numbers.length - 1) {
                result += getOperation();
            }

        }

        return result;
    }

    private String getOperation() {
        int i = random.nextInt(4);
        return operations[i];
    }

    private int[] getNumbers(int size) {
        int[] numbers = new int[size];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(100) + 1;
        }
        return numbers;
    }
}
