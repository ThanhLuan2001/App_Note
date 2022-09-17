package com.example.baitestt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.baitestt.database.NoteDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ThemNote extends AppCompatActivity {

    EditText ed_name,ed_date,ed_note;
    Button btnColor;
    Button btnLuu;
    NoteAdapter noteAdapter;
    List<Note> mlistNote;
    int defaultColo = Color.BLUE;
    SharedPreferences sp;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_note);
        sp = getSharedPreferences("sp",MODE_PRIVATE);
        linearLayout = findViewById(R.id.add_linearlayout);

        ed_name = findViewById(R.id.ed_name);
        ed_date = findViewById(R.id.ed_date);
        ed_note = findViewById(R.id.ed_note);
        btnColor = findViewById(R.id.btnColor);
        btnLuu = findViewById(R.id.btnLuu);

        noteAdapter = new NoteAdapter();
        mlistNote = new ArrayList<>();
        noteAdapter.setData(mlistNote);

        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AmbilWarnaDialog dialog = new AmbilWarnaDialog(ThemNote.this, defaultColo, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        defaultColo = color;
                        linearLayout.setBackgroundColor(color);
                        sp.edit().putString("color", String.valueOf(color)).commit();
                    }

                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                    }
                });
                dialog.show();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemNotee();
            }
        });

        ed_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(ThemNote.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int ngay = i2;
                        int thang = i1+1;
                        int nam = i;
                        ed_date.setText(ngay+"/"+thang+"/"+nam);
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)
                );
                datePickerDialog.show();
            }
        });


    }
    public void ThemNotee(){
        String strname = ed_name.getText().toString().trim();
        String strdate = ed_date.getText().toString().trim();
        String strnote = ed_note.getText().toString().trim();

        if(TextUtils.isEmpty(strname)|| TextUtils.isEmpty(strnote) || TextUtils.isEmpty(strdate)){
            Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        Note note = new Note(strname,strdate,strnote,defaultColo);
        NoteDatabase.getInstance(this).noteDAO().insert(note);
        Toast.makeText(ThemNote.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
        ed_name.setText("");
        ed_date.setText("");
        ed_note.setText("");




        Intent intent = new Intent(ThemNote.this,MainActivity.class);
        startActivity(intent);
    }
}