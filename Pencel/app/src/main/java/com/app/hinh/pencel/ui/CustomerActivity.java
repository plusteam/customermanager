package com.app.hinh.pencel.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.hinh.pencel.R;
import com.app.hinh.pencel.adapter.CustomListviewAdapter;
import com.app.hinh.pencel.model.CustomListview;
import com.app.hinh.pencel.model.Customer;
import com.app.hinh.pencel.model.CustomerResponse;
import com.app.hinh.pencel.network.CustomerAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.example.le.custommanagerdemo_theme.common.Final;

/**
 * Created by Dung-DamDe-DeTien on 13/08/2016.
 */
public class CustomerActivity extends Activity {
    private CustomListviewAdapter adapter;
    private ListView lvCus;
    private ArrayList<Customer> customers;
    private ArrayList<CustomListview> cus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer);

       /* if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        CustomerAPI.sendCustomer("aaaa");*/

        cus = new ArrayList<>();
        customers = new ArrayList<>();
        lvCus = (ListView)findViewById(R.id.lvCustomer);
        adapter = new CustomListviewAdapter(this, R.layout.item_custom_list_item,cus);
        lvCus.setAdapter(adapter);
        /*Final fina=new Final();
        Log.d("id_aaaa",String.valueOf(fina.getId()));*/
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        int id = extra.getInt("id");
        getCustomers(id);

        lvCus.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<Customer> dt = customers;
                String id = dt.get(i).getAccountID();
                String cusName = dt.get(i).getName();
                String phone = dt.get(i).getPhoneNumber();
                String address = dt.get(i).getAddress();
                String company = dt.get(i).getCompany();
                String project = dt.get(i).getProject();
                String email = dt.get(i).getEmail();
                String rate = dt.get(i).getRating();
                String type = dt.get(i).getCustomerType();
                String agency = dt.get(i).getAgency();
                String note = dt.get(i).getNote();
                String demand = dt.get(i).getDemand();
                String other = dt.get(i).getOther();

                Intent intent = new Intent(CustomerActivity.this, InformationCustomerActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("cusName", cusName);
                intent.putExtra("phone", phone);
                intent.putExtra("address", address);
                intent.putExtra("company", company);
                intent.putExtra("project", project);
                intent.putExtra("email", email);
                intent.putExtra("rate", rate);
                intent.putExtra("type", type);
                intent.putExtra("base", agency);
                intent.putExtra("note", note);
                intent.putExtra("need", demand);
                intent.putExtra("Other", other);
                startActivity(intent);

            }
        });
    }

    private void getCustomers(int id){
        CustomerAPI.getCustomer(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if(response != null){
                    CustomerResponse customerResponse = response.body();
                    List<Customer> customer =  customerResponse.getData();
                    bindData(customer);
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {

            }
        },id );
    }

    private void bindData(List<Customer> customer1){
        this.customers.addAll(customer1);
        for (int i = 0; i < customers.size(); i ++){
            cus.add(new CustomListview(this.customers.get(i).getName().toString(), "", false, null, 0));
        }
        adapter.notifyDataSetChanged();
    }

}
