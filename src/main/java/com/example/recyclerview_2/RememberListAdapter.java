package com.example.recyclerview_2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RememberListAdapter extends RecyclerView.Adapter<RememberListHolder>{
    private ArrayList<RememberListItem> RemindersArr;
    private Context context;

    public interface OnItemClick {
        void onClickItem (int index);
    }

    //Profit
    public RememberListAdapter(Context context, ArrayList<RememberListItem> cur_list) {
        RemindersArr = cur_list;
        this.context = context;
    }


    //Profit
    @NonNull
    @Override
    public RememberListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_markup, parent, false);
        RememberListHolder rememberListHolder = new RememberListHolder(view);
        return rememberListHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull  RememberListHolder cur_holder, int position) {
        cur_holder.ParseItem(RemindersArr.get(position));

        cur_holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnItemClick listener = (OnItemClick) context;
                listener.onClickItem(position);

            }
        });
    }


    @Override
    public int getItemCount() {
        return RemindersArr.size();
    }
}
