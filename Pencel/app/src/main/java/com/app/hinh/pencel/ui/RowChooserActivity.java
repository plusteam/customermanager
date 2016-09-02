package com.app.hinh.pencel.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.hinh.pencel.R;
import com.app.hinh.pencel.adapter.CustomListviewAdapter;
import com.app.hinh.pencel.model.CustomListview;
import com.app.hinh.pencel.util.ExcelUtil;

import java.util.ArrayList;


public class RowChooserActivity extends AppCompatActivity implements View.OnClickListener {
    private String filePath;
    private int indexOfSheet;
    private ListView lvRows;
    private TextView txtHeaderName;
    private ImageButton btnNext;
    private ImageButton btnCancel;
    private ImageButton btnBack;
    private TextView txtHeaderInfo;
    private CustomListviewAdapter adapter;
    private ArrayList<CustomListview> arrRows;
    private int indextOfRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_row_chooser);

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
        adapter = new CustomListviewAdapter(RowChooserActivity.this, R.layout.item_custom_list_item, arrRows);
        lvRows.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                btnNext.setEnabled(true);
                adapter.mark(i);
                btnNext.setEnabled(arrRows.get(i).isSelected() ? true : false);
                Toast.makeText(RowChooserActivity.this, "Chọn hàng: " + String.valueOf(i), Toast.LENGTH_SHORT).show();
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
            ExcelUtil excelUtil = new ExcelUtil(filePath, indexOfSheet);
            for (int i = 0; i < excelUtil.getRowValue().size(); i++) {
                value = excelUtil.getRowValue().get(i).toString();
                arrRows.add(new CustomListview("Hàng số: " + String.valueOf(i), value, false, null, 0));
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
                    it = new Intent(this, MergeColumnsActivity.class);
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
                it = new Intent(this, FileChooserActivity.class);
                startActivity(it);
                break;
        }
    }

}
