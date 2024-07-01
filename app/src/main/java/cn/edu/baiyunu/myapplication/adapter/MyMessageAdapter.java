package cn.edu.baiyunu.myapplication.adapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import cn.edu.baiyunu.myapplication.R;
import cn.edu.baiyunu.myapplication.entities.MyMessage;

public class MyMessageAdapter extends RecyclerView.Adapter<MyMessageAdapter.MessageViewHolder> {
    private List<MyMessage> messageList;

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public TextView time;

        public MessageViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            time = itemView.findViewById(R.id.time);
//            textView = itemView.findViewById(android.R.id.text1);
        }
    }

    public MyMessageAdapter(List<MyMessage> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_message_item, parent, false);
//        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        MyMessage message = messageList.get(position);
        holder.time.setText(message.getTime());
        holder.text.setText((message.isUser() ?"你：":"小孔AI：")+message.getText());
        holder.text.setGravity(message.isUser() ? Gravity.RIGHT : Gravity.LEFT);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}

