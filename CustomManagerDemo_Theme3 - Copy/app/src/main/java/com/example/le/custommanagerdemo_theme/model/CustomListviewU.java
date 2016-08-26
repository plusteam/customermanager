package com.example.le.custommanagerdemo_theme.model;

import android.graphics.Bitmap;


public class CustomListviewU {
    private String line1;
    private String line2;
    private boolean isSelected;
    private Bitmap icon;
    private int type;

    public CustomListviewU(String line1, String line2, boolean isSelected, Bitmap icon, int type) {
        this.line1 = line1;
        this.line2 = line2;
        this.isSelected = isSelected;
        this.icon = icon;
        this.type = type;
    }

    public CustomListviewU() {

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }
}
