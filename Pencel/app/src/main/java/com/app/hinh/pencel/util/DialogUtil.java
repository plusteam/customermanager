package com.app.hinh.pencel.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.hinh.pencel.R;
import com.app.hinh.pencel.adapter.CustomListviewAdapter;
import com.app.hinh.pencel.model.CustomListview;

import java.util.ArrayList;

/**
 * Created by hinh1 on 8/31/2016.
 */
public class DialogUtil {
    private Dialog dialog;
    private ImageButton btnCancel;
    private ImageButton btnNext;
    private ImageButton btnBack;
    private Context context;
    private ImageView imvHeaderIcon;
    private TextView txtHeaderTitle;
    private TextView txtHeaderInfo;
    private ListView lvDialog;
    private ArrayList<CustomListview> arrView;
    private CustomListviewAdapter adapter;

    public DialogUtil(Context context) {
        this.context = context;
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom);
        imvHeaderIcon = (ImageView) dialog.findViewById(R.id.imvHeaderIcon);
        btnNext = (ImageButton) dialog.findViewById(R.id.btnNext);
        btnCancel = (ImageButton) dialog.findViewById(R.id.btnCancel);
        btnBack = (ImageButton) dialog.findViewById(R.id.btnBack);
        txtHeaderTitle = (TextView) dialog.findViewById(R.id.txtHeaderTitle);
        txtHeaderInfo = (TextView) dialog.findViewById(R.id.txtHeaderInfo);


    }

    public void setHeaderText(String title, String info) {
        txtHeaderTitle.setText(title);
        txtHeaderInfo.setText(info);
    }

    public void setHeaderIcon(int res) {
        imvHeaderIcon.setImageResource(res);
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public ImageButton getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(ImageButton btnCancel) {
        this.btnCancel = btnCancel;
    }

    public ImageButton getBtnNext() {
        return btnNext;
    }

    public void setBtnNext(ImageButton btnNext) {
        this.btnNext = btnNext;
    }

    public ImageButton getBtnBack() {
        return btnBack;
    }

    public void setBtnBack(ImageButton btnBack) {
        this.btnBack = btnBack;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ImageView getImvHeaderIcon() {
        return imvHeaderIcon;
    }

    public void setImvHeaderIcon(ImageView imvHeaderIcon) {
        this.imvHeaderIcon = imvHeaderIcon;
    }

    public TextView getTxtHeaderTitle() {
        return txtHeaderTitle;
    }

    public void setTxtHeaderTitle(TextView txtHeaderTitle) {
        this.txtHeaderTitle = txtHeaderTitle;
    }

    public TextView getTxtHeaderInfo() {
        return txtHeaderInfo;
    }

    public void setTxtHeaderInfo(TextView txtHeaderInfo) {
        this.txtHeaderInfo = txtHeaderInfo;
    }

    public ListView getLvDialog() {
        return lvDialog;
    }

    public void setLvDialog(ListView lvDialog) {
        this.lvDialog = lvDialog;
    }

    public ArrayList<CustomListview> getArrView() {
        return arrView;
    }

    public void setArrView(ArrayList<CustomListview> arrView) {
        this.arrView = arrView;
    }

    public CustomListviewAdapter getAdapter() {
        return adapter;
    }
    public void showDialog() {
        lvDialog = (ListView) dialog.findViewById(R.id.lvDialog);
        arrView = new ArrayList<>();
        adapter = new CustomListviewAdapter(dialog.getContext(), R.layout.item_file_list, arrView);
        lvDialog.setAdapter(adapter);
        dialog.show();
    }
    public void setAdapter(CustomListviewAdapter adapter) {
        this.adapter = adapter;
    }

}
