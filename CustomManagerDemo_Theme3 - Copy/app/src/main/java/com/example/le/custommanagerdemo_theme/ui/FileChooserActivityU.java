package com.example.le.custommanagerdemo_theme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.le.custommanagerdemo_theme.R;
import com.example.le.custommanagerdemo_theme.model.CustomListviewU;
import com.example.le.custommanagerdemo_theme.util.DialogUtilU;

import java.io.File;



public class FileChooserActivityU extends AppCompatActivity implements View.OnClickListener {
    private String filePath;
    private ImageButton btnCancel;
    private ImageButton btnNext;
    private ImageButton btnBack;
    private Button btnShowDialog;
    private TextView txtHeaderTitle;
    private TextView txtHeaderInfo;
    private File root; //Bien luu thu muc goc
    private File currentFolder; //Bien luu thu muc hien tai
    private DialogUtilU dialogUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_chooser_u);
        txtHeaderTitle = (TextView) findViewById(R.id.txtHeaderTitle);
        txtHeaderInfo = (TextView) findViewById(R.id.txtHeaderInfo);

        txtHeaderTitle.setText("Bước đầu tiên:");
        txtHeaderInfo.setText("Hãy chọn file Excel của bạn");

        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnNext.setEnabled(false);
        btnShowDialog = (Button) findViewById(R.id.btnShowDialog);
        btnCancel = (ImageButton) findViewById(R.id.btnCancel);
        btnBack = (ImageButton) findViewById(R.id.btnBack);

        btnBack.setVisibility(View.GONE);
        btnShowDialog.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    public void showDialog() {
        dialogUtil = new DialogUtilU(this);
        dialogUtil.showDialog();
        root = new File("/storage/");
        dialogUtil.setHeaderIcon(R.drawable.folder);
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());//Thu muc goc la the nho
            root.setReadable(false);
        }
        dialogUtil.getBtnCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogUtil.getDialog().dismiss();
            }
        });
        dialogUtil.getBtnBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentFolder.equals(root)) {
                    loadListFile(currentFolder.getParentFile());
                }
            }
        });
        dialogUtil.getBtnNext().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = dialogUtil.getAdapter().checkSelected();
                if (i != -1) {
                    filePath = currentFolder.getPath() + "/" + dialogUtil.getArrView().get(i).getLine1();
                    dialogUtil.getDialog().dismiss();
                } else {
                    Toast.makeText(dialogUtil.getDialog().getContext(), "Hãy chọn file Excel bạn muốn import", Toast.LENGTH_SHORT).show();
                }

            }
        });

        dialogUtil.getLvDialog().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                File file = new File(dialogUtil.getArrView().get(i).getLine2());

                if (file.isDirectory()) {
                    //neu hang duoc chon la thu muc thi load cac thu muc con cua no va hien thi nut back
                    dialogUtil.getBtnBack().setEnabled(true);
                    loadListFile(file);
                } else {
                    dialogUtil.getAdapter().mark(i);
                    dialogUtil.getBtnNext().setEnabled(dialogUtil.getArrView().get(i).isSelected() ? true : false);
                    btnNext.setEnabled(true);
                    filePath = file.getPath() + "/" + file.getName();
                    Toast.makeText(dialogUtil.getDialog().getContext(), file.getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        loadListFile(root);

    }

    public void loadListFile(File file) {
        //Gan thu muc hien tai la file va hien thi ten thu muc + path len header
        currentFolder = file;
        String fileName;
        String filePath;
        int type = 0;
        dialogUtil.setHeaderText(file.getName(), file.getPath());

        //Xoa toan bo du lieu trong mang
        dialogUtil.getArrView().clear();

        //Neu da la thu muc goc thi k hien thi nut back
        if (file.equals(root))
            dialogUtil.getBtnBack().setEnabled(false);

        File[] files = file.listFiles();

        //liet ke cac thu muc con trong thu muc file, gan ten va duong dan
        for (File f : files) {
            if (!f.canRead()) continue;

            fileName = f.getName();
            filePath = f.getPath();
            if (f.isDirectory())
                type = 2;
            else if (f.isFile())
                type = 1;

            dialogUtil.getArrView().add(new CustomListviewU(fileName, filePath, false, null, type));

        }
        dialogUtil.getAdapter().notifyDataSetChanged();

    }

    @Override
    public void onClick(View view) {
        Intent it;
        switch (view.getId()) {
            case R.id.btnShowDialog:
                showDialog();
                break;
            case R.id.btnCancel:
                it = new Intent(FileChooserActivityU.this, MainActivity.class);
                startActivity(it);
                break;
            case R.id.btnNext:
                if (!filePath.equals("")) {
                    it = new Intent(this, SheetChooserActivityU.class);
                    it.putExtra("filePath", filePath);
                    startActivity(it);
                } else {
                    Toast.makeText(this, "Hãy chọn file Excel bạn muốn import", Toast.LENGTH_SHORT).show();
                }

            case R.id.btnBack:
                break;
        }

    }
}
