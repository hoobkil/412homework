package com.example.a411homework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = findViewById(R.id.listView);

        List<Map<String,String>> values = prepareContent();

        BaseAdapter listContentAdapter = createAdapter(values);

        list.setAdapter(listContentAdapter);
    }

    @NonNull
    private BaseAdapter createAdapter(List<Map<String, String>> values) {
        return new SimpleAdapter(this, values, R.layout.activity_data, new String[]{KEY1,
                KEY2}, new int[]{R.id.textView1, R.id.textView2});
    }

    @NonNull
    private List<Map<String, String>> prepareContent() {
        List<Map<String,String>> result = new ArrayList<>();
        String[] arrayContent = getString(R.string.large_text).split("\n\n");
        for (int i = 0; i < 18; i++) {
            Map<String, String> map = new HashMap<>();
            map.put(KEY1, arrayContent[i]);
            map.put(KEY2, arrayContent[i].length()+"");
            result.add(map);
        }
        return result;
    }
}
