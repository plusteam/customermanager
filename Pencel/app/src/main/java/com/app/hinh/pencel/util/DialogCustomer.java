package com.app.hinh.pencel.util;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import com.app.hinh.pencel.R;
import com.app.hinh.pencel.adapter.CustomListviewAdapter;
import com.app.hinh.pencel.model.CustomListview;

import java.util.ArrayList;

/**
 * Created by Admin on 8/31/2016.
 */
public class DialogCustomer {
    private Dialog dialogCustomer;
    private Button buttonOk;
    private ListView listCusomer;
    private CheckBox checkSelected;
    private ArrayList<CustomListview> arrCustomer;
    private CustomListviewAdapter customListviewAdapter;
    private Context context;
    private ArrayList<CustomListview> arrCustomerReturn;

    public DialogCustomer(Context context) {
        this.context  = context;
        dialogCustomer = new Dialog(context);
        dialogCustomer.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCustomer.setContentView(R.layout.dialog_layout);
        listCusomer = (ListView)dialogCustomer.findViewById(R.id.listCustomer);
        buttonOk = (Button)dialogCustomer.findViewById(R.id.btnOk);
        arrCustomer = new ArrayList<>();
        arrCustomerReturn = new ArrayList<>();
    }

    public ListView getListCusomer() {
        return listCusomer;
    }

    public void setListCusomer(ListView listCusomer) {
        this.listCusomer = listCusomer;
    }

    public Dialog getDialogCustomer() {
        return dialogCustomer;
    }

    public void setDialogCustomer(Dialog dialogCustomer) {
        this.dialogCustomer = dialogCustomer;
    }

    public Button getButtonOk() {
        return buttonOk;
    }

    public void setButtonOk(Button buttonOk) {
        this.buttonOk = buttonOk;
    }


    public ArrayList<CustomListview> getArrCustomer() {
        return arrCustomer;
    }

    public void setArrCustomer(ArrayList<CustomListview> arrCustomer) {
        this.arrCustomer = arrCustomer;
    }

    public CustomListviewAdapter getCustomListviewAdapter() {
        return customListviewAdapter;
    }

    public void setCustomListviewAdapter(CustomListviewAdapter customListviewAdapter) {
        this.customListviewAdapter = customListviewAdapter;
    }

    public void showDialog(){
        listCusomer = (ListView)dialogCustomer.findViewById(R.id.listCustomer);
        customListviewAdapter = new CustomListviewAdapter(context,R.layout.item_custom_list_item, arrCustomer);
        listCusomer.setAdapter(customListviewAdapter);
        dialogCustomer.show();
    }

}
