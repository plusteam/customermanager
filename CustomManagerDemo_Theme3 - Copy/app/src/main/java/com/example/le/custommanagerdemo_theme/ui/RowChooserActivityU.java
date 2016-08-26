package com.example.le.custommanagerdemo_theme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.le.custommanagerdemo_theme.R;
import com.example.le.custommanagerdemo_theme.adapter.CustomListviewAdapterU;
import com.example.le.custommanagerdemo_theme.model.CustomListviewU;
import com.example.le.custommanagerdemo_theme.util.ExcelUtilU;

import java.util.ArrayList;



public class RowChooserActivityU extends AppCompatActivity implements View.OnClickListener {
    private String filePath;
    private int indexOfSheet;
    private ListView lvRows;
    private TextView txtHeaderName;
    private ImageButton btnNext;
    private ImageButton btnCancel;
    private ImageButton btnBack;
    private TextView txtHeaderInfo;
    private CustomListviewAdapterU adapter;
    private ArrayList<CustomListviewU> arrRows;
    private int indextOfRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_row_chooser_u);

        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnNext.setEnabled(false);
        btnCancel = (ImageButton) findViewById(R.id.btnCancel);
        btnCancel.setVisibility(View.GONE);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setEnabled(true);
        txtHeaderName = (TextView) findViewById(R.id.txtHeaderTitle);
        txtHeaderInfo = (TextView) findViewById(R.id.txtHeaderInfo);

        txtHeaderName.setText("Bước 3:");
        txtHeaderInfo.setText("Hãy chọn hàng chứa tên các cột của bạn");

        Intent it = this.getIntent();
        filePath = it.getStringExtra("filePath");
        indexOfSheet = it.getIntExtra("indexOfSheet", -1);

        lvRows = (ListView) findViewById(R.id.lvRows);
        arrRows = new ArrayList<>();
        adapter = new CustomListviewAdapterU(RowChooserActivityU.this, R.layout.item_custom_list_item_u, arrRows);
        lvRows.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                btnNext.setEnabled(true);
                adapter.mark(i);
                btnNext.setEnabled(arrRows.get(i).isSelected() ? true : false);
                Toast.makeText(RowChooserActivityU.this, "Chọn hàng: " + String.valueOf(i), Toast.LENGTH_SHORT).show();
                indextOfRow = i;
            }
        });
        lvRows.setAdapter(adapter);
        btnBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        getRow();
    }

    public void getRow() {
        String value;
        int position;
        try {
            ExcelUtilU excelUtil = new ExcelUtilU(filePath, indexOfSheet);
            for (int i = 0; i < excelUtil.getRowValue().size(); i++) {
                value = excelUtil.getRowValue().get(i).toString();
                arrRows.add(new CustomListviewU("Hàng số: " + String.valueOf(i), value, false, null, 0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCancel:
                break;
            case R.id.btnNext:
                Intent it;
                int i = adapter.checkSelected();
                if (i != -1) {
                    it = new Intent(this, MergeColumnsActivityU.class);
                    it.putExtra("filePath", filePath);
                    it.putExtra("indexOfSheet", indexOfSheet);
                    it.putExtra("indextOfRow", indextOfRow);
                    startActivity(it);
                } else {
                    Toast.makeText(this, "Đâu là hàng chứa tiêu đề các cột của bạn?", Toast.LENGTH_SHORT).show();
                }
                finish();
                break;
            case R.id.btnBack:
                it = new Intent(this, FileChooserActivityU.class);
                startActivity(it);
                break;
        }
    }

}
