package cn.edu.baiyunu.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.baiyunu.myapplication.adapter.MyMessageAdapter;
import cn.edu.baiyunu.myapplication.entities.MyMessage;
import cn.edu.baiyunu.myapplication.wenxinyiyan.YiClient;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyMessageAdapter myMessageAdapter;
    private List<MyMessage> messageList;
    private EditText editTextMessage;
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);

        messageList = new ArrayList<>();
        myMessageAdapter = new MyMessageAdapter(messageList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myMessageAdapter);

        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                String  result = (String)msg.obj;
                // 显示AI响应信息
                messageList.add(new MyMessage(getCurrentTime(),result, false));
                myMessageAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(messageList.size() - 1);
            }
        };

        YiClient yiClient = new YiClient(handler);

        // 发送按钮
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = editTextMessage.getText().toString();
                if (!messageText.isEmpty()) {
                    // 现实用户的输入信息
                    messageList.add(new MyMessage(getCurrentTime(),messageText, true));
                    myMessageAdapter.notifyDataSetChanged();
                    editTextMessage.setText("");    // 输入框清空
                    yiClient.sendRequest(messageText);  // 请求 AI
                }
            }
        });
    }

    // 获取当前时间
    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return sdf.format(new Date());
    }
}