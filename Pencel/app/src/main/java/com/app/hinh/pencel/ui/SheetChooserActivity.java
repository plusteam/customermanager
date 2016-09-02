package com.app.hinh.pencel.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.hinh.pencel.R;
import com.app.hinh.pencel.adapter.CustomListviewAdapter;
import com.app.hinh.pencel.model.CustomListview;
import com.app.hinh.pencel.util.ExcelUtil;

import java.util.ArrayList;


public class SheetChooserActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<CustomListview> arrSheet;
    private ListView lvSheet;
    private CustomListviewAdapter adapter;
    private TextView txtHeaderName;
    private TextView txtHeaderInfo;
    private Button btnNext;
    private Button btnCancel;
    private Button btnBack;
    private int indexOfSheet;
    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_chooser);

        txtHeaderName = (TextView) findViewById(R.id.txtHeaderTitle);
        txtHeaderInfo = (TextView) findViewById(R.id.txtHeaderInfo);

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setEnabled(false);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnBack = (Button) findViewById(R.id.btnBack);
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
        adapter = new CustomListviewAdapter(SheetChooserActivity.this, R.layout.item_custom_list_item, arrSheet);
        lvSheet.setAdapter(adapter);

        lvSheet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                indexOfSheet = i;
                btnNext.setEnabled(true);
                adapter.mark(i);
                btnNext.setEnabled(arrSheet.get(i).isSelected() ? true : false);
                Toast.makeText(SheetChooserActivity.this, "Chọn trang tính: " + String.valueOf(i), Toast.LENGTH_SHORT).show();

            }
        });


        getSheets();
    }

    public void getSheets() {
        try {
            ExcelUtil excelUtil = new ExcelUtil(filePath);
            String sheetName;
            String sheetIndex;
            for (int i = 0; i < excelUtil.countSheet(); i++) {
                sheetName = excelUtil.getSheetName().get(i).toString();
                sheetIndex = "Trang tính số: " + String.valueOf(excelUtil.getSheetIndex().get(i).toString());

                arrSheet.add(new CustomListview(sheetName, sheetIndex, false, null, 0));
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
                    it = new Intent(this, RowChooserActivity.class);
                    it.putExtra("filePath", filePath);
                    it.putExtra("indexOfSheet", indexOfSheet);
                    startActivity(it);
                } else {
                    Toast.makeText(this, "Hãy chọn một trang tính chứa dữ liệu của bạn", Toast.LENGTH_SHORT).show();
                }
                finish();
                break;
            case R.id.btnBack:
                it = new Intent(this, FileChooserActivity.class);
                startActivity(it);
                break;
        }
    }

}
