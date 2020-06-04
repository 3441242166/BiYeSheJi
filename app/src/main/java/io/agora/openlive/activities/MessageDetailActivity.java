package io.agora.openlive.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.agora.openlive.R;
import io.agora.openlive.bean.Message;

public class MessageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);


    }

    public static class MessageUtil{
        public static Message tempMessage = null;
    }
}


