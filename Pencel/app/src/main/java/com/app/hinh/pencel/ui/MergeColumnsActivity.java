package com.app.hinh.pencel.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.hinh.pencel.R;
import com.app.hinh.pencel.adapter.CustomListviewAdapter;
import com.app.hinh.pencel.common.Constant;
import com.app.hinh.pencel.database.DatabaseManager;
import com.app.hinh.pencel.model.CustomListview;
import com.app.hinh.pencel.network.CustomerAPI;
import com.app.hinh.pencel.util.DialogUtil;
import com.app.hinh.pencel.util.ExcelUtil;
import com.app.hinh.pencel.util.JsonUtil;

import java.util.ArrayList;


public class MergeColumnsActivity extends AppCompatActivity implements View.OnClickListener {
    private String filePath;
    private String jsonS;
    private int indexOfSheet;
    private int indextOfRow;
    private ListView lvColumns;
    private TextView txtHeaderName;
    private Button btnNext;
    private Button btnCancel;
    private Button btnBack;
    private TextView txtHeaderInfo;
    private CustomListviewAdapter adapter;
    private ArrayList<CustomListview> arrColumns;
    private DialogUtil dialogUtil;
    private ArrayList<String> arrColumnsS;
    private ArrayList<Integer> arrColumnsI;
    private String id;
    private Cursor cursor;//bang du lieu
    private DatabaseManager databaseManager;//lop lam viec voi DB
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_columns);

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setVisibility(View.GONE);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setEnabled(true);

        txtHeaderName = (TextView) findViewById(R.id.txtHeaderTitle);
        txtHeaderInfo = (TextView) findViewById(R.id.txtHeaderInfo);
        arrColumnsI = new ArrayList<>();

        txtHeaderName.setText("Bước 4:");
        txtHeaderInfo.setText("Chọn các cột tương ứng để đồng bộ dữ liệu");

        Intent it = this.getIntent();
        filePath = it.getStringExtra("filePath");
        indexOfSheet = it.getIntExtra("indexOfSheet", -1);
        indextOfRow = it.getIntExtra("indextOfRow", -1);

        lvColumns = (ListView) findViewById(R.id.lvColumns);
        arrColumns = new ArrayList<>();
        adapter = new CustomListviewAdapter(MergeColumnsActivity.this, R.layout.item_custom_list_item, arrColumns);
        lvColumns.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialog(arrColumns.get(i).getLine1(), i);
            }
        });
        lvColumns.setAdapter(adapter);
        getColumns();



        databaseManager =new DatabaseManager(MergeColumnsActivity.this);
        id=databaseManager.getID();


    }

    public void getColumns() {
        Constant constant = new Constant();
        arrColumnsS = constant.columns();
        for (int i = 0; i < constant.columns().size(); i++) {
            arrColumns.add(new CustomListview(constant.columns().get(i).toString(), "", false, null, 0));
        }
        adapter.notifyDataSetChanged();
    }

    public void showDialog(String name, final int position) {
        dialogUtil = new DialogUtil(MergeColumnsActivity.this);
        dialogUtil.showDialog();

        dialogUtil.setHeaderText("Danh sách cột", "Chọn các cột tương ứng với: <" + name + ">");

        dialogUtil.getBtnBack().setVisibility(View.GONE);
        dialogUtil.getBtnNext().setEnabled(false);
        try {
            ExcelUtil excelUtil = new ExcelUtil(filePath, indexOfSheet, indextOfRow,id);
            for (int i = 0; i < excelUtil.getColumnName().size(); i++) {
                dialogUtil.getArrView().add(new CustomListview(excelUtil.getColumnName().get(i).toString(), "", false, null, 0));
            }

            dialogUtil.getLvDialog().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    dialogUtil.getAdapter().markMore(i);

                    dialogUtil.getBtnNext().setEnabled(dialogUtil.getAdapter().checkSelected() != -1 ? true : false);
                    btnNext.setEnabled(true);
                    Toast.makeText(dialogUtil.getDialog().getContext(), dialogUtil.getArrView().get(i).getLine1().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dialogUtil.getAdapter().notifyDataSetChanged();
        }
        dialogUtil.getBtnCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogUtil.getDialog().dismiss();
            }
        });
        dialogUtil.getBtnNext().setVisibility(View.VISIBLE);
        dialogUtil.getBtnNext().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String line2 = "";
                if (arrColumnsI.size() < dialogUtil.getArrView().size()) {
                    arrColumnsI.add(arrColumnsI.size(), -1);
                }
                //neu cot cua khach hang duoc chon thi se gan chi so = cot cua db
                for (int i = 0; i < dialogUtil.getArrView().size(); i++) {
                    if (dialogUtil.getArrView().get(i).isSelected()) {
                        arrColumnsI.add(i, position);
                        line2 += dialogUtil.getArrView().get(i).getLine1().toString() + " ";
                    }

                }
                arrColumns.get(position).setLine2(line2);
                adapter.notifyDataSetChanged();
                dialogUtil.getDialog().dismiss();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCancel:
                break;
            case R.id.btnNext:
                JsonUtil jsonUtil;
                ExcelUtil excelUtil = null;
                try {
                    excelUtil = new ExcelUtil(filePath, indexOfSheet, indextOfRow, id);
                    jsonUtil = new JsonUtil(id);
                    jsonS = jsonUtil.getJson(excelUtil.getCustomers(arrColumnsI));
                    Log.d("data",jsonS);

                    //post data

                    if (android.os.Build.VERSION.SDK_INT > 9) {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                    }
                    CustomerAPI.sendCustomer(jsonS);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                excelUtil.getCustomers(arrColumnsI);
                break;
            case R.id.btnBack:
                Intent it = new Intent(MergeColumnsActivity.this, FileChooserActivity.class);
                startActivity(it);
                break;


        }
    }

}
