package com.example.coachticketbookingapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.coachticketbookingapp.R;

import java.util.List;

public class UserAdapter extends ArrayAdapter<Userr> {
    private Context context;
    private List<Userr> userList;

    public UserAdapter(Context context, List<Userr> userList) {
        super(context, 0, userList);
        this.context = context;
        this.userList = userList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        }

        Userr user = userList.get(position);

        TextView userName = convertView.findViewById(R.id.userName);
        TextView userEmail = convertView.findViewById(R.id.userEmail);
        TextView userRole = convertView.findViewById(R.id.userRole);

        userName.setText(user.getUserName());
        userEmail.setText(user.getEmail());
        userRole.setText(user.getRole());

        return convertView;
    }
}