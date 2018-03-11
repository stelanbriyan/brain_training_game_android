package org.incosyz.stelan.braintraininggame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Stelan Briyan
 */
public class PlayFragment extends Fragment {
    private double answer;
    private Random random = new Random();

    private String operations[] = {"+", "-", "/", "*"};

    /**
     * Components
     */
    private TextView guessLabel, answerLabel;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDel, btnHash, btnMinus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_play,
                container, false);

        this.guessLabel = rootView.findViewById(R.id.guess_label);
        this.guessLabel.setText("Guess : ".concat(generateGuessLabel()).concat(" = ?"));

        this.answerLabel = rootView.findViewById(R.id.answerLabel);
        this.btn0 = rootView.findViewById(R.id.btn0);
        this.btn1 = rootView.findViewById(R.id.btn1);
        this.btn2 = rootView.findViewById(R.id.btn2);
        this.btn3 = rootView.findViewById(R.id.btn3);
        this.btn4 = rootView.findViewById(R.id.btn4);
        this.btn5 = rootView.findViewById(R.id.btn5);
        this.btn6 = rootView.findViewById(R.id.btn6);
        this.btn7 = rootView.findViewById(R.id.btn7);
        this.btn8 = rootView.findViewById(R.id.btn8);
        this.btn9 = rootView.findViewById(R.id.btn9);
        this.btnDel = rootView.findViewById(R.id.btnDel);
        this.btnHash = rootView.findViewById(R.id.btnHash);
        this.btnMinus = rootView.findViewById(R.id.btnMinus);

        this.btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswerText(view);
            }
        });
        this.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswerText(view);
            }
        });
        this.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswerText(view);
            }
        });
        this.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswerText(view);
            }
        });
        this.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswerText(view);
            }
        });
        this.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswerText(view);
            }
        });
        this.btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswerText(view);
            }
        });
        this.btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswerText(view);
            }
        });
        this.btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswerText(view);
            }
        });
        this.btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswerText(view);
            }
        });
        this.btnHash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });
        this.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNumber();
            }
        });
        this.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = answerLabel.getText().toString();
                if (text.contains("-")) {
                    answerLabel.setText(text.replace("-", ""));
                } else {
                    answerLabel.setText("-".concat(text));
                }
            }
        });
        return rootView;
    }

    public void checkAnswer() {
        int userAnswer = Integer.parseInt(this.answerLabel.getText().toString());
        int correctAnswer = (int) answer;

        System.out.println(userAnswer);
        System.out.println(correctAnswer);
        if (userAnswer == correctAnswer) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Your answer is correct!")
                    .setPositiveButton(R.string.continue_label,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    guessLabel.setText("Guess : ".concat(generateGuessLabel()).concat(" = ?"));
                                    answerLabel.setText("0");
                                }
                            })
                    .show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Your answer is wrong!")
                    .setNegativeButton(R.string.try_label,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                    .setPositiveButton(R.string.new_label,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    guessLabel.setText("Guess : ".concat(generateGuessLabel()).concat(" = ?"));
                                    answerLabel.setText("0");
                                }
                            })
                    .show();
        }
    }

    public void deleteNumber() {
        String text = this.answerLabel.getText().toString();
        if (text.length() > 1) {
            this.answerLabel.setText(text.substring(0, text.length() - 1));
        } else {
            this.answerLabel.setText("0");
        }
    }

    public void setAnswerText(View view) {
        Button button = (Button) view;
        if ("0".equals(this.answerLabel.getText())) {
            this.answerLabel.setText("");
        }
        this.answerLabel.setText(this.answerLabel.getText() + button.getText().toString());
    }

    /**
     * Generate arithmetic expression randomly
     *
     * @return
     */
    private String generateGuessLabel() {
        String result = "";

        answer = 0;
        for (int i = 0; i < PlayActivity.GAME_LEVEL; i++) {
            Map<String, String> logic = getLogic();
            if (i == 0) {
                result += logic.get("logic");
            } else {
                result += "+" + logic.get("logic");
            }
            answer += Double.parseDouble(logic.get("answer").toString());
        }

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
