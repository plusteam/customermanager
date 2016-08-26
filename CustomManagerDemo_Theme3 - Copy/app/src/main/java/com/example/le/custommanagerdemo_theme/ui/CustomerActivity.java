package com.example.le.custommanagerdemo_theme.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.le.custommanagerdemo_theme.R;
import com.example.le.custommanagerdemo_theme.adapter.CustomListviewAdapter;
import com.example.le.custommanagerdemo_theme.common.Final;
import com.example.le.custommanagerdemo_theme.model.CustomListview;
import com.example.le.custommanagerdemo_theme.model.Customer;
import com.example.le.custommanagerdemo_theme.model.CustomerResponse;
import com.example.le.custommanagerdemo_theme.network.CustomerAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        Final fina=new Final();
        Log.d("id_aaaa",String.valueOf(fina.getId()));
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        int id = extra.getInt("id");
        getCustomers(id);

        lvCus.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<Customer> dt = customers;
                int id = dt.get(i).getIdCustomer();
                String cusName = dt.get(i).getHoVaTen();
                String phone = dt.get(i).getSdt();
                String address = dt.get(i).getDiaChi();
                String company = dt.get(i).getCongTy();
                String project = dt.get(i).getDuanQt();
                String email = dt.get(i).getEmail();
                String rate = dt.get(i).getDanhGia();
                String type = dt.get(i).getCustomerType();
                String base = dt.get(i).getNguonKhach();
                String note = dt.get(i).getGhiChu();
                String need = dt.get(i).getNhuCau();
                String Other = dt.get(i).getOther();

                Intent intent = new Intent(CustomerActivity.this, ACustomer.class);
                intent.putExtra("id", id);
                intent.putExtra("cusName", cusName);
                intent.putExtra("phone", phone);
                intent.putExtra("address", address);
                intent.putExtra("company", company);
                intent.putExtra("project", project);
                intent.putExtra("email", email);
                intent.putExtra("rate", rate);
                intent.putExtra("type", type);
                intent.putExtra("base", base);
                intent.putExtra("note", note);
                intent.putExtra("need", need);
                intent.putExtra("Other", Other);
                startActivity(intent);


            }
        });

        registerForContextMenu(lvCus);
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
            cus.add(new CustomListview(this.customers.get(i).getHoVaTen().toString(), "", false, null, 0));
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //thiet lap header

        //thiet lap item
        menu.add(Menu.NONE,Menu.NONE,0,"Delete");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getOrder()){
            case 0:
                Toast.makeText(CustomerActivity.this,"Delete",Toast.LENGTH_LONG).show();
                break;

        }
        return super.onContextItemSelected(item);
    }

}
