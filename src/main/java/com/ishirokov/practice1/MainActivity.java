package com.ishirokov.practice1;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Date;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Date date = new Date();
    public final static String nowCount = "nowCnt";
    public static final String nowHistory = "historyList";
    SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");

    public TextView counter;
    Button upBtn;
    Button lowBtn;
    Button clearBtn;
    int cnt;
    ArrayList<String> history = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView historyClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        counter = (TextView) findViewById(R.id.counter);
        upBtn = (Button) findViewById(R.id.upBtn);
        lowBtn = (Button) findViewById(R.id.lowBtn);
        clearBtn = (Button) findViewById(R.id.clearBtn);
        historyClick = (ListView) findViewById(R.id.historyClick);

        counter.setText(Integer.toString(cnt));


        if (savedInstanceState != null) {
            cnt = savedInstanceState.getInt(nowCount, 0);
            history = savedInstanceState.getStringArrayList(nowHistory);
        } else {
            cnt = 0;
            history = new ArrayList<>();
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, history);
        historyClick.setAdapter(adapter);
        counter.setText(Integer.toString(cnt));



        View.OnClickListener clearClc = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt = 0;
                counter.setText(Integer.toString(cnt));
                String currentTime = form.format(date);
                history.add(currentTime+" Счетчик очищен");
                adapter.notifyDataSetChanged();
            }
        };
        View.OnClickListener lowClc = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt -= 1;
                counter.setText(Integer.toString(cnt));
                String currentTime = form.format(date);
                history.add(currentTime+" - Уменьшено с "+Integer.toString(cnt+1)+" до "+Integer.toString(cnt));
                adapter.notifyDataSetChanged();
            }
        };
        View.OnClickListener upClc = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt += 1;
                counter.setText(Integer.toString(cnt));
                String currentTime = form.format(date);
                history.add(currentTime+" - Увеличено с "+Integer.toString(cnt-1)+" до "+Integer.toString(cnt));
                adapter.notifyDataSetChanged();
            }
        };
        upBtn.setOnClickListener(upClc);
        lowBtn.setOnClickListener(lowClc);
        clearBtn.setOnClickListener(clearClc);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(nowCount, cnt);
        outState.putStringArrayList(nowHistory, history);

    }



}