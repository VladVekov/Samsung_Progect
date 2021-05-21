package com.example.recyclerview_2;

import android.content.Context;
import android.util.Log;

import androidx.loader.content.Loader;
import androidx.room.Room;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MyDatabaseСlass{
    private static final String TAG = "MyDatabaseСlass";
    MyDataBase db;
    ReminderDAO reminderDAO;
    MainActivity mainActivity;

    public MyDatabaseСlass(Context context, MainActivity mainActivity) {
        db = Room.databaseBuilder(context, MyDataBase.class, "ReminderDatabase").build();
        reminderDAO = db.reminderDAO();
        this.mainActivity = mainActivity;
    }

    public MyDatabaseСlass(Context context) {
        db = Room.databaseBuilder(context, MyDataBase.class, "ReminderDatabase").build();
        reminderDAO = db.reminderDAO();
    }

    public void insertRememberListItem(RememberListItem rememberListItem){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reminderDAO.insert(rememberListItem);
                } catch (Exception exception) {
                    Log.e(TAG, "Получено исключение в методе insertRememberListItem класса MydatabaseClass", exception);
                }
            }
        });
        thread.start();
    }

    //метод для обновления элемента списка
    public void updateRememberListItem(RememberListItem rememberListItem_1) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                reminderDAO.update(rememberListItem_1);
//                reminderDAO.delete(rememberListItem_1);
//                reminderDAO.insert(rememberListItem_1);
            }
        });
        thread.start();
    }

    public  ArrayList<RememberListItem> loadRememberListItem(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<RememberListItem> list = reminderDAO.getAll();
                mainActivity.reminders_array.addAll(list);
            }
        });
        thread.start();
        return new ArrayList<RememberListItem>();
    }


    public void deleteRememberListItem(ArrayList<RememberListItem> ArrReminderList, int position){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                reminderDAO.delete(ArrReminderList.get(position));
                ArrReminderList.remove(position);
            }
        });
        thread.start();
    }
}
