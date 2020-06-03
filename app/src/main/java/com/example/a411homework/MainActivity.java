package com.example.a411homework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String KEY1 = "key1";
    public static final String KEY2 = "key2";
    final String SAVED_TEXT = "SAVED_TEXT";

    List<Map<String,String>> result = new ArrayList<>();
    List<Map<String,String>> values = new ArrayList<>();

    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    public void init(){
        ListView list = findViewById(R.id.list);
        final SwipeRefreshLayout swipeLayout = findViewById(R.id.swipeRefresh);
        String text = getString(R.string.large_text);
        prefs = getSharedPreferences("my prefs", MODE_PRIVATE);
        if(!prefs.contains(SAVED_TEXT)){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(SAVED_TEXT, text);
            editor.apply();
        }
        values = prepareContentFromPrefs();
        final BaseAdapter listContentAdapter = createAdapter(values);
        list.setAdapter(listContentAdapter);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                values.clear();
                values = prepareContentFromPrefs();
                listContentAdapter.notifyDataSetChanged();
                swipeLayout.setRefreshing(false);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                values.remove(position);
                listContentAdapter.notifyDataSetChanged();
            }
        });
    }

    @NonNull
    public BaseAdapter createAdapter(List<Map<String, String>> values) {
        return new SimpleAdapter(this, values, R.layout.activity_data, new String[]{KEY1,
                KEY2}, new int[]{R.id.textView1, R.id.textView2});
    }

    @NonNull

    private List<Map<String, String>> prepareContentFromPrefs(){
        String[] arrayContent = prefs.getString(SAVED_TEXT, null).split("\n\n");
        for (int i = 0; i < arrayContent.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put(KEY1, arrayContent[i]);
            map.put(KEY2, arrayContent[i].length()+"");
            result.add(map);
        }
        return result;

    }


}
