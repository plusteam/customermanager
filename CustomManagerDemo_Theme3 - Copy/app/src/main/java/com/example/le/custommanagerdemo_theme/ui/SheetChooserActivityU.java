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


public class SheetChooserActivityU extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<CustomListviewU> arrSheet;
    private ListView lvSheet;
    private CustomListviewAdapterU adapter;
    private TextView txtHeaderName;
    private TextView txtHeaderInfo;
    private ImageButton btnNext;
    private ImageButton btnCancel;
    private ImageButton btnBack;
    private int indexOfSheet;
    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_chooser_u);

        txtHeaderName = (TextView) findViewById(R.id.txtHeaderTitle);
        txtHeaderInfo = (TextView) findViewById(R.id.txtHeaderInfo);

        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnNext.setEnabled(false);
        btnCancel = (ImageButton) findViewById(R.id.btnCancel);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setEnabled(true);
        btnCancel.setVisibility(View.GONE);

        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnCancel.setOnClickListener(this);


        txtHeaderName.setText("Bước 2:");
        txtHeaderInfo.setText("Hãy chọn trang tính chứa dữ liệu bạn muốn thêm");

        Intent it = this.getIntent();
        filePath = it.getStringExtra("filePath");
        lvSheet = (ListView) findViewById(R.id.lvSheets);
        arrSheet = new ArrayList<>();
        adapter = new CustomListviewAdapterU(SheetChooserActivityU.this, R.layout.item_custom_list_item_u, arrSheet);
        lvSheet.setAdapter(adapter);

        lvSheet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                indexOfSheet = i;
                btnNext.setEnabled(true);
                adapter.mark(i);
                btnNext.setEnabled(arrSheet.get(i).isSelected() ? true : false);
                Toast.makeText(SheetChooserActivityU.this, "Chọn trang tính: " + String.valueOf(i), Toast.LENGTH_SHORT).show();

            }
        });


        getSheets();
    }

    public void getSheets() {
        try {
            ExcelUtilU excelUtil = new ExcelUtilU(filePath);
            String sheetName;
            String sheetIndex;
            for (int i = 0; i < excelUtil.countSheet(); i++) {
                sheetName = excelUtil.getSheetName().get(i).toString();
                sheetIndex = "Trang tính số: " + String.valueOf(excelUtil.getSheetIndex().get(i).toString());

                arrSheet.add(new CustomListviewU(sheetName, sheetIndex, false, null, 0));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            adapter.notifyDataSetChanged();
        }
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
                    it = new Intent(this, RowChooserActivityU.class);
                    it.putExtra("filePath", filePath);
                    it.putExtra("indexOfSheet", indexOfSheet);
                    startActivity(it);
                } else {
                    Toast.makeText(this, "Hãy chọn một trang tính chứa dữ liệu của bạn", Toast.LENGTH_SHORT).show();
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
