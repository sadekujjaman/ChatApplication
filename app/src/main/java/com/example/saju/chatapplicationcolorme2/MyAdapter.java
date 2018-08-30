package com.example.saju.chatapplicationcolorme2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Saju on 7/24/2018.
 */


public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_HOLDER_TYPE_1 = 1;
    private static final int VIEW_HOLDER_TYPE_2 = 2;
    private final Context context;
    private final ArrayList<Message> messages;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context context, ArrayList<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;

        switch (viewType) {
            // create a new view

            case VIEW_HOLDER_TYPE_1:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.mymessage, parent, false);
                ViewHolder_Type1 vh1 = new ViewHolder_Type1(v);
                return vh1;

            case VIEW_HOLDER_TYPE_2:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.message, parent, false);
                ViewHolder_Type2 vh2 = new ViewHolder_Type2(v);
                return vh2;

            default:
                break;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        switch (getItemViewType(position)) {

            case VIEW_HOLDER_TYPE_1:
                ViewHolder_Type1 viewholder1 = (ViewHolder_Type1) holder;
                TextView mytimeView = (TextView) viewholder1.mytimeTextView;
                mytimeView.setText(messages.get(position).getTime());
                TextView mymsgView = (TextView) viewholder1.mymessageTextView;
                mymsgView.setText(messages.get(position).getMessage());
                break;

            case VIEW_HOLDER_TYPE_2:
                ViewHolder_Type2 viewholder2 = (ViewHolder_Type2) holder;
                TextView timeView = (TextView) viewholder2.timeTextView;
                timeView.setText(messages.get(position).getTime());
                TextView msgView = (TextView) viewholder2.messageTextView;
                msgView.setText(messages.get(position).getMessage());
                break;

            default:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 1 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        if (messages.get(position).fromMe())
            return VIEW_HOLDER_TYPE_1;
        else
            return VIEW_HOLDER_TYPE_2;

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder_Type1 extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mymessageTextView, mytimeTextView;

        public ViewHolder_Type1(View v) {
            super(v);
            this.mymessageTextView = (TextView) v.findViewById(R.id.mymessageTextView);
            this.mytimeTextView = (TextView) v.findViewById(R.id.mytimeTextView);
        }


    }

    public static class ViewHolder_Type2 extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView messageTextView, timeTextView;

        public ViewHolder_Type2(View v) {
            super(v);
            this.messageTextView = (TextView) v.findViewById(R.id.messageTextView);
            this.timeTextView = (TextView) v.findViewById(R.id.timeTextView);
        }


    }
}


/*public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final Context context;
    private final ArrayList<Message> messages;

    private static final int VH_1 = 1;
    private static final int VH_2 = 2;

    public  static class RecycleVH_1 extends RecyclerView.ViewHolder{

        public TextView myMessageTextView, myTimeTextView;
        public RecycleVH_1(View view) {
            super(view);
            this.myMessageTextView = (TextView) view.findViewById(R.id.mymessageTextView);
            this.myTimeTextView = (TextView) view.findViewById(R.id.mytimeTextView);
        }
    }

    public static class RecycleVH_2 extends RecyclerView.ViewHolder{
        public TextView messageTextView, timeTextView;
        public  RecycleVH_2(View view){
            super(view);
            this.messageTextView = (TextView)view.findViewById(R.id.messageText);
            this.timeTextView = (TextView) view.findViewById(R.id.timeTextView);
        }
    }




    MyAdapter(Context context, ArrayList<Message> messages){
        this.context = context;
        this.messages = messages;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;

        switch (viewType){
            case VH_1:
                view = LayoutInflater.from(parent.getContext())
                                         .inflate(R.layout.mymessage, parent, false);
                RecycleVH_1 vh1 = new RecycleVH_1(view);
                return vh1;

            case VH_2:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.message, parent, false);
                RecycleVH_2 vh_2 = new RecycleVH_2(view);
                return vh_2;

            default:
                break;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch(getItemViewType(position)){
            case VH_1:
                RecycleVH_1 vh_1 = (RecycleVH_1)holder;
                TextView myMessageText = vh_1.myMessageTextView;
                myMessageText.setText(messages.get(position).getMessage());
                TextView myTimeText = vh_1.myTimeTextView;
                myTimeText.setText(messages.get(position).getTime());
                break;
            case VH_2:
                RecycleVH_2 vh_2 = (RecycleVH_2)holder;
                TextView messageText = vh_2.messageTextView;
                messageText.setText(messages.get(position).getMessage());
                TextView timeText = vh_2.timeTextView;
                timeText.setText(messages.get(position).getTime());
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 1 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        if (messages.get(position).fromMe())
            return VH_1;
        else
            return VH_2;

    }
}

*/


