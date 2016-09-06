package com.app.hinh.pencel.ui;

import android.util.Log;
import android.widget.ListView;

import com.app.hinh.pencel.adapter.NoteAdapter;
import com.app.hinh.pencel.model.CustomerNotification;
import com.app.hinh.pencel.model.NoteResponse;
import com.app.hinh.pencel.network.NoteAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hinh1 on 9/5/2016.
 */
public class NoteActivity  {
    private NoteAdapter adapter;
    private ListView lvCus;
    private static ArrayList<CustomerNotification> note = new ArrayList<>();


    public static void getCustomers(int id){
        NoteAPI.getNote(new Callback<NoteResponse>() {
            @Override
            public void onResponse(Call<NoteResponse> call, Response<NoteResponse> response) {
                if(response != null){
                    NoteResponse noteResponse = response.body();
                    List<CustomerNotification> customer =  noteResponse.getData();
                    bindData(customer);
                }
            }

            @Override
            public void onFailure(Call<NoteResponse> call, Throwable t) {

            }
        }, id);
    }
    public static void bindData(List<CustomerNotification> noteCus){
        note.addAll(noteCus);
        for (int i =0; i < noteCus.size();i++){
            Log.d("Note", noteCus.get(i).getDateTime());
        }

    }
}
