package com.example.le.custommanagerdemo_theme.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.le.custommanagerdemo_theme.R;

/**
 * Created by Dung-DamDe-DeTien on 13/08/2016.
 */
public class ACustomer extends Activity {
    private TextView cusId;
    private TextView tvCus;
    private TextView tvPhone;
    private TextView tvAddress;
    private TextView tvCompany;
    private TextView tvProject;
    private TextView tvEmail;
    private TextView tvRate;
    private TextView tvType;
    private TextView tvBase;
    private TextView tvNote;
    private TextView tvNeed;
    private TextView other;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_cus);

        cusId = (TextView)findViewById(R.id.vId);
        tvCus = (TextView)findViewById(R.id.vName);
        tvPhone = (TextView)findViewById(R.id.vPhone);
        tvAddress = (TextView)findViewById(R.id.vAddress);
        tvCompany = (TextView)findViewById(R.id.vCompany);
        tvProject = (TextView)findViewById(R.id.vProject);
        tvEmail = (TextView)findViewById(R.id.vEmail);
        tvRate = (TextView)findViewById(R.id.vRate);
        tvType = (TextView)findViewById(R.id.vType);
        tvBase = (TextView)findViewById(R.id.vcustomerbase);
        tvNote = (TextView)findViewById(R.id.vNote);
        tvNeed = (TextView)findViewById(R.id.vNeed);
        other = (TextView)findViewById(R.id.vOther);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        int a = extra.getInt("id");
        String name = extra.getString("cusName");
        String phone = extra.getString("phone");
        String address = extra.getString("address");
        String company = extra.getString("company");
        String pro = extra.getString("project");
        String email = extra.getString("email");
        String rate = extra.getString("rate");
        String type = extra.getString("type");
        String base = extra.getString("base");
        String note = extra.getString("note");
        String need = extra.getString("need");
        String er = extra.getString("Other");

        cusId.setText(String.valueOf(a));
        tvCus.setText(name);
        tvPhone.setText(phone);
        tvAddress.setText(address);
        tvCompany.setText(company);
        tvProject.setText(pro);
        tvEmail.setText(email);
        tvRate.setText(rate);
        tvType.setText(type);
        tvBase.setText(base);
        tvNote.setText(note);
        tvNeed.setText(need);
        other.setText(er);

    }



}
