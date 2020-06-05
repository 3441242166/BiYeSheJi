package io.agora.openlive.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;

import io.agora.openlive.R;
import io.agora.openlive.bean.Message;
import io.agora.openlive.event.MessageListUpdateEvent;
import io.agora.openlive.utils.PrefManager;

public class AddMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        final EditText name = findViewById(R.id.name);
        final EditText error = findViewById(R.id.room_id);
        final EditText date = findViewById(R.id.date);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String e = error.getText().toString();
                String d = date.getText().toString();

                PrefManager.addMessage(AddMessageActivity.this, new Message(n, d, Message.STATE_UN_DOING, e));
                EventBus.getDefault().post(new MessageListUpdateEvent());
                finish();
            }
        });
    }
}
