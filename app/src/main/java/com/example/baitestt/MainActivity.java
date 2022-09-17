package com.example.baitestt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baitestt.database.NoteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView img_add;
    CalendarView calendarView;
    TextView tv_date;
    RecyclerView rcv_note;
    NoteAdapter noteAdapter;
    List<Note> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView = findViewById(R.id.calendarView);
        tv_date = findViewById(R.id.tv_date);
        noteAdapter = new NoteAdapter();
        noteAdapter.setData(NoteDatabase.getInstance(this).noteDAO().getAll());

        img_add = findViewById(R.id.img_add);
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ThemNote.class);
                startActivity(intent);
            }
        });
        rcv_note = findViewById(R.id.rcv_note);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_note.setLayoutManager(linearLayoutManager);
        rcv_note.setAdapter(noteAdapter);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                int ngay = i2;
                int thang = i1+1;
                int nam = i;
                String date = ngay+"/"+thang+"/"+nam;
                tv_date.setText(date);
                list = new ArrayList<>();
                list = NoteDatabase.getInstance(MainActivity.this).noteDAO().search(date);
                noteAdapter.setData(list);

            }
        });
    }
}