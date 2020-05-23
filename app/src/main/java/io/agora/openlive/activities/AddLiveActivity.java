package io.agora.openlive.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import io.agora.openlive.R;
import io.agora.openlive.bean.RoomBean;
import io.agora.openlive.utils.PrefManager;

public class AddLiveActivity extends AppCompatActivity {

    public static HashMap<String, String> roomMap = new HashMap<>();
    public static HashMap<String, String> roomPasswordMap = new HashMap<>();

    static {
        roomMap.put("16401", "计算机401实验室");
        roomMap.put("16402", "计算机402实验室");
        roomMap.put("16403", "计算机403实验室");
        roomMap.put("16404", "计算机404实验室");
        roomMap.put("16405", "计算机405实验室");
        roomMap.put("16406", "计算机406实验室");
        roomMap.put("16407", "计算机407实验室");
        roomMap.put("16408", "计算机408实验室");

        roomPasswordMap.put("16401", "401");
        roomPasswordMap.put("16402", "402");
        roomPasswordMap.put("16403", "403");
        roomPasswordMap.put("16404", "404");
        roomPasswordMap.put("16405", "405");
        roomPasswordMap.put("16406", "406");
        roomPasswordMap.put("16407", "407");
        roomPasswordMap.put("16408", "408");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_live);

        final EditText room_id = findViewById(R.id.room_id);
        final EditText room_password = findViewById(R.id.room_password);
        Button add = findViewById(R.id.button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = room_id.getText().toString();
                String password = room_password.getText().toString();

                if (TextUtils.equals(password, roomPasswordMap.get(id))) {
                    PrefManager.addRoom(AddLiveActivity.this, new RoomBean(roomMap.get(id), 0, "wanhao"));
                    finish();
                } else {
                    Toast.makeText(AddLiveActivity.this, "编号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
