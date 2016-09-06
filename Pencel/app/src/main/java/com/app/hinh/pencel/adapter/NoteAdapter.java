package com.app.hinh.pencel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.hinh.pencel.R;
import com.app.hinh.pencel.model.CustomerNotification;

import java.util.ArrayList;

/**
 * Created by hinh1 on 9/5/2016.
 */
public class NoteAdapter extends ArrayAdapter {
    private ArrayList<CustomerNotification> arrNote;
    private LayoutInflater inflater;


    public NoteAdapter(Context context, int resource, ArrayList<CustomerNotification> objects) {
        super(context, resource, objects);
        this.arrNote = objects;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_note, parent, false);
            holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            holder.tvNote = (TextView) convertView.findViewById(R.id.tvNote);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
    public class ViewHolder{
        public TextView tvDate;
        public TextView tvNote;

    }
}
