package com.example.recyclerview_2;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RememberListHolder extends RecyclerView.ViewHolder {
    private TextView tvName;
    private TextView tvDeadline;

    public RememberListHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_name);
        tvDeadline = itemView.findViewById(R.id.tv_deadline);

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //TODO
////                int positionIndex = getAdapterPosition();
////                Intent i = new Intent(context, ReminderCreationActivity.class);
////                startActivity(i);
//            }
//        });
    }

    public void ParseItem(RememberListItem cur_list_item) {
        tvName.setText(cur_list_item.getmName());
        tvDeadline.setText(cur_list_item.getDeadline());

    }
}
