package com.app.hinh.pencel.network;

import com.app.hinh.pencel.app.Application;
import com.app.hinh.pencel.model.NoteResponse;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by hinh1 on 9/5/2016.
 */
public class NoteAPI {
    public static void getNote(Callback<NoteResponse> callback, int id) {
        Call<NoteResponse> callGetCus = Application.APIServer.getNote(String.valueOf(id));
        callGetCus.enqueue(callback);
    }


}
