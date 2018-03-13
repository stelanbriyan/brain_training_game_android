package org.incosyz.stelan.braintraininggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ToggleButton;

public class LevelFragment extends Fragment {
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_level,
                container, false);

        spinner = rootView.findViewById(R.id.game_level_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.game_levels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button startButton = rootView.findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        final ToggleButton toggleButton = rootView.findViewById(R.id.hint_toggle);
        toggleButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if ("ON".equals(toggleButton.getText())) {
                    PlayFragment.HINTS = true;
                } else {
                    PlayFragment.HINTS = false;
                }
            }
        });

        return rootView;
    }

    public void startGame() {
        String difficulty = spinner.getSelectedItem().toString();
        if ("Novice".equals(difficulty)) {
            PlayActivity.GAME_LEVEL = 2;
        } else if ("Easy".equals(difficulty)) {
            PlayActivity.GAME_LEVEL = 3;
        } else if ("Medium".equals(difficulty)) {
            PlayActivity.GAME_LEVEL = 4;
        } else if ("Guru".equals(difficulty)) {
            PlayActivity.GAME_LEVEL = 5;
        }

        Intent intent = new Intent(getActivity(), PlayActivity.class);
        getActivity().startActivity(intent);
    }
}
