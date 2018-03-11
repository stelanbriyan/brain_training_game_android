package org.incosyz.stelan.braintraininggame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Stelan Briyan
 */
public class PlayFragment extends Fragment {
    private TextView guessLabel;
    private double answer;
    private Random random = new Random();

    private String operations[] = {"+", "-", "/", "*"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_play,
                container, false);

        this.guessLabel = rootView.findViewById(R.id.guess_label);
        this.guessLabel.setText("Guess : ".concat(generateGuessLabel()).concat(" ?"));
        return rootView;
    }

    /**
     * Generate arithmetic expression randomly
     *
     * @return
     */
    private String generateGuessLabel() {
        String result = "";


        for (int i = 0; i < PlayActivity.GAME_LEVEL; i++) {
            String op2 = operations[random.nextInt(2)];

            Map<String, String> logic = getLogic();
            if ("-".equals(op2)) {
                result += "-" + logic.get("logic");
                answer -= Double.parseDouble(logic.get("answer").toString());
            } else {
                if (i == 0) {
                    result += logic.get("logic");
                } else {
                    result += "+" + logic.get("logic");
                }
                answer += Double.parseDouble(logic.get("answer").toString());
            }
        }


        System.out.println("ANSWER : " + answer);
        return result;
    }

    private Map<String, String> getLogic() {
        String logic = null;

        int val1 = random.nextInt(100);
        int val2 = random.nextInt(100);

        String op = getOperation();

        Double answer = 0D;
        if ("+".equals(op)) {
            answer = (double) val1 + val2;

            logic = val1 + op + val2;
        } else if ("-".equals(op)) {
            answer = (double) val1 - val2;

            logic = val1 + op + val2;
        } else if ("/".equals(op)) {
            answer = (double) val1 / val2;

            logic = "(" + val1 + op + val2 + ")";
        } else if ("*".equals(op)) {
            answer = (double) val1 * val2;

            logic = "(" + val1 + op + val2 + ")";
        }

        Map<String, String> map = new HashMap<>();
        map.put("logic", logic);
        map.put("answer", String.valueOf(answer));

        return map;
    }

    /**
     * @return arithmetic operation symbols randomly.
     */
    private String getOperation() {
        return operations[random.nextInt(4)];
    }
}
