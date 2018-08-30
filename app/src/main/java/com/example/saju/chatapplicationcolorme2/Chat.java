package com.example.saju.chatapplicationcolorme2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Date;

public class Chat extends AppCompatActivity implements View.OnClickListener {

    ImageButton sendButton;
    EditText messageText;
    RecyclerView messageList;
    RecyclerView.Adapter myAdapter = null;

    ArrayList<Message> messages = null;
    int in_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sendButton = (ImageButton) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(this);

        messageText = (EditText) findViewById(R.id.messageText);

        messages = new ArrayList<>();


        myAdapter = new MyAdapter(this, messages);

        Intent intent = getIntent();
        String friendName = intent.getStringExtra("Friend_Name");

        String titleBar = getResources().getString(R.string.app_name_2);
        getSupportActionBar().setTitle(titleBar + ": " + friendName);

        messageList = (RecyclerView) findViewById(R.id.messageList);
        messageList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        messageList.setLayoutManager(llm);
        messageList.setAdapter(myAdapter);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.sendButton:

                String messageString = messageText.getText().toString();
                if (!messageString.trim().equals("")) {
                    Message message = new Message("", messageString, true, new Date());

                    messages.add(message);

                    myAdapter.notifyDataSetChanged();
                    sendMessage();
                    message = null;
                    messageText.setText("");
                }

                break;
        }
    }

    private void sendMessage() {

        String[] incoming = {"Hey, How's it going?",
                "Super! Let's do lunch tomorrow",
                "How about Mexican?",
                "Great, I found this new place around the corner",
                "Ok, see you at 12 then!"};

        if (in_index < incoming.length) {
            Message message = new Message("", incoming[in_index], false, new Date());

            messages.add(message);
            message = null;
            in_index++;
        }
        messageList.scrollToPosition(messages.size() - 1);
        myAdapter.notifyDataSetChanged();

    }
}
