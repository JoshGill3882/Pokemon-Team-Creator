package com.example.pokemonteamcreator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.pokemonteamcreator.R;

import java.util.ArrayList;

public class TeamViewAdapter extends BaseAdapter implements ListAdapter {
    private final ArrayList<String> teamList;
    private final Context context;

    public TeamViewAdapter(ArrayList<String> teamList, Context context) {
        this.teamList = teamList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return teamList.size();
    }
    @Override
    public Object getItem(int position) {
        return teamList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.team_selection_list_item, null);
        }

        // Display team name at current position on the TextView
        TextView teamName = view.findViewById(R.id.teamSelectionListItemName);
        teamName.setText(teamList.get(position));

        // Handle button presses
        Button selectTeamButton = view.findViewById(R.id.teamSelectionListItemButton);
        selectTeamButton.setOnClickListener(v -> {
            System.out.println("Button pressed for Team: " + teamName.getText());
        });

        return view;
    }
}
