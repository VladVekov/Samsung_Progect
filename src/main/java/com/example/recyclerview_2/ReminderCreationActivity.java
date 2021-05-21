package com.example.recyclerview_2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.format.DateUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.recyclerview_2.databinding.ActivityReminderCreationBinding;

import java.util.Calendar;

public class ReminderCreationActivity extends AppCompatActivity {
    TextView currentDateTime;
    Calendar dateAndTime = Calendar.getInstance();
    long data_time;
//    Calendar Date = Calendar.getInstance();
    int index = -1;
    int isFinished;
    String mName;
    String mDeadline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_creation);

        currentDateTime = (TextView) findViewById(R.id.deadline_time);
        setInitialDateTime();



        //обработка ввода названия задачи
        EditText editText = findViewById(R.id.task_name);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String str_temp = editText.getText().toString();
                    Toast.makeText(ReminderCreationActivity.this, str_temp, Toast.LENGTH_LONG).show();
                    return true;
                }
                return false;
            }
        });


        CheckBox checkBox = findViewById(R.id.isFinished);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    isFinished = 1;
                } else {
                    isFinished = 0;
                }
            }
        });


        //обработчик нажания на кнопку создать
        Button buttonCreate = (Button) findViewById(R.id.create);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Поля созданного объеката
                mName = editText.getText().toString();
                TextView TV_Deadline = (TextView) findViewById(R.id.deadline_time);
                mDeadline = TV_Deadline.getText().toString();
                data_time = dateAndTime.getTimeInMillis();

                RememberListItem cur_list_item = new RememberListItem(mName, mDeadline, isFinished, data_time);
                Intent i = new Intent();
                i.putExtra("NEW_REMIND", cur_list_item);
                if (index != -1) {
                    i.putExtra("index", index);
                }
                setResult(RESULT_OK, i);
                finish();

//                // TODO
//                AlarmReceiver.scheduleAlarms(this);
            }
        });

        //обработчик нажатия на кнопку "не создавать"
        Button buttonNotCreate = (Button) findViewById(R.id.notCreate);
        buttonNotCreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReminderCreationActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

     try{
          Intent intent = getIntent();
          RememberListItem cur_List_item = (RememberListItem) intent.getSerializableExtra("cur_elem");

          index = intent.getIntExtra("index", -1);

          mName = cur_List_item.mName;
          mDeadline = cur_List_item.strDeadline;
          dateAndTime.setTimeInMillis(cur_List_item.mDeadline);

          TextView tv_Name = findViewById(R.id.task_name);
          tv_Name.setText(mName);

          TextView tv_Deadline = findViewById(R.id.deadline_time);
          tv_Deadline.setText(mDeadline);
      }
     catch (Exception e){

     }
    }


    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {
        new DatePickerDialog(ReminderCreationActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // отображаем диалоговое окно для выбора времени
    public void setTime(View v) {
        new TimePickerDialog(ReminderCreationActivity.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }
    // установка начальных даты и времени
    private void setInitialDateTime() {

        currentDateTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }

    // установка обработчика выбора времени
    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };
}