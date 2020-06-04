package io.agora.openlive.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.agora.openlive.R;
import io.agora.openlive.adapter.MessageAdapter;
import io.agora.openlive.bean.Message;
import io.agora.openlive.utils.PrefManager;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView messageListView;
    MessageAdapter adapter;
    List<Message> data;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        spinner = findViewById(R.id.state_spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.message_state_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(0);

        messageListView = findViewById(R.id.list);
        adapter = new MessageAdapter(null, this);
        messageListView.setAdapter(adapter);
        messageListView.setNestedScrollingEnabled(false);
        messageListView.setLayoutManager(new LinearLayoutManager(this));

        initData();
        initEvent();
    }

    private void initEvent() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateMessageData(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                updateMessageData(0);
            }
        });
    }

    private void initData() {
        List<Message> updateData = PrefManager.getUnDealMessageList(this);
        Collections.sort(updateData);
        data = updateData;
        adapter.setNewInstance(updateData);
    }

    private void updateMessageData(int type) {
        List<Message> updateData = PrefManager.getMessageList(this);
        switch (type) {
            case 0:
                updateData = PrefManager.getMessageList(this);
                break;
            case 1:
                updateData = PrefManager.getDoingMessageList(this);
                break;
            case 2:
                updateData = PrefManager.getUnDealMessageList(this);
                break;
            case 3:
                updateData = PrefManager.getFinishMessageList(this);
                break;
        }
        data = updateData;
        adapter.setNewInstance(updateData);
    }
}
