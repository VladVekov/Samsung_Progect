package com.example.recyclerview_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.sort;

public class MainActivity extends AppCompatActivity implements RememberListAdapter.OnItemClick{

    public ArrayList<RememberListItem> reminders_array= new ArrayList<>();
    public RememberListAdapter rememberListAdapter;
    public RecyclerView recyclerView;
    public MyDatabaseСlass databaseСlass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //создание базы данных
        databaseСlass = new MyDatabaseСlass(getApplicationContext(), this);

        //добавление элементов в список
        reminders_array = databaseСlass.loadRememberListItem();


        //устанавливаем адаптер в RecycleView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        rememberListAdapter= new RememberListAdapter(this, reminders_array);

        recyclerView.setAdapter(rememberListAdapter);


        // обработка нажания на плавующую кнопку
        FloatingActionButton mFAB = (FloatingActionButton) findViewById(R.id.fab);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReminderCreationActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    //принимает новый объект напоминания
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            RememberListItem cur_List_item = (RememberListItem) data.getSerializableExtra("NEW_REMIND");
            reminders_array.add(cur_List_item);
            Collections.sort(reminders_array); // возможна ошибка
            RememberListAdapter listAdapter = new RememberListAdapter(this, reminders_array);
            recyclerView.setAdapter(listAdapter);

            databaseСlass.insertRememberListItem(cur_List_item);
        }
        if(requestCode == 2 && resultCode == RESULT_OK) {
            RememberListItem cur_List_item = (RememberListItem) data.getSerializableExtra("NEW_REMIND");
            int cur_index = data.getIntExtra("index", -1);

            reminders_array.set(cur_index, cur_List_item);
            databaseСlass.updateRememberListItem(cur_List_item);

            Collections.sort(reminders_array); // возможна ошибка
            RememberListAdapter listAdapter = new RememberListAdapter(this, reminders_array);
            recyclerView.setAdapter(listAdapter);
        }
    }

    @Override
    public void onClickItem(int index) {
        RememberListItem curItem =  reminders_array.get(index);
        Intent intent = new Intent(MainActivity.this, ReminderCreationActivity.class);
        intent.putExtra("cur_elem", curItem);
        intent.putExtra("index", index);
        startActivityForResult(intent, 2);
    }
}