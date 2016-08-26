package com.example.le.custommanagerdemo_theme.util;

import com.aspose.cells.CellValueType;
import com.aspose.cells.Cells;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.LoadOptions;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;
import com.example.le.custommanagerdemo_theme.model.CustomerU;

import java.util.ArrayList;


/**
 * Created by Henry on 03-Aug-16.
 */
public class ExcelUtilU {
    private Workbook workbook;
    private WorksheetCollection worksheets;
    private Worksheet sheet;
    private String filePath;
    private int indexOfSheet;
    private int indexOfRow;
    private Cells cells;
    private String id;
    private int indexOfColumn;

    public ExcelUtilU(String filePath) throws Exception {
        this.filePath = filePath;
        LoadOptions loadOptions = new LoadOptions(FileFormatType.XLSX);
        workbook = new Workbook(filePath, loadOptions);
        worksheets = workbook.getWorksheets();
    }

    public ExcelUtilU(String filePath, int indexOfSheet) throws Exception {
        this.indexOfSheet = indexOfSheet;
        this.filePath = filePath;
        LoadOptions loadOptions = new LoadOptions(FileFormatType.XLSX);
        workbook = new Workbook(filePath, loadOptions);
        worksheets = workbook.getWorksheets();
        sheet = worksheets.get(indexOfSheet);
    }

    public ExcelUtilU(String filePath, int indexOfSheet, int indexOfRow,String id) throws Exception {
        this.indexOfSheet = indexOfSheet;
        this.filePath = filePath;
        this.id=id;
        LoadOptions loadOptions = new LoadOptions(FileFormatType.XLSX);
        workbook = new Workbook(filePath, loadOptions);
        worksheets = workbook.getWorksheets();
        sheet = worksheets.get(indexOfSheet);
        cells = sheet.getCells();
        this.indexOfRow = indexOfRow;

    }

    public ArrayList<String> getSheetName() {
        ArrayList<String> sheetName = new ArrayList<>();
        worksheets = workbook.getWorksheets();
        for (int i = 0; i < worksheets.getCount(); i++) {
            sheetName.add(worksheets.get(i).getName());
        }
        return sheetName;
    }

    public ArrayList<Integer> getSheetIndex() {
        ArrayList<Integer> sheetIndex = new ArrayList<>();
        WorksheetCollection worksheets = workbook.getWorksheets();
        for (int i = 0; i < worksheets.getCount(); i++) {
            sheetIndex.add(worksheets.get(i).getIndex());
        }
        return sheetIndex;
    }

    public int countSheet() {
        return worksheets.getCount();
    }

    public ArrayList<String> getRowValue() {
        ArrayList<String> rowValue = new ArrayList<>();
        int i = 0;
        int j = 0;
        cells = sheet.getCells();

        while (cells.get(i, j) == null) {
            i++;
        }
        while (cells.get(i, j) == null) {
            j++;
        }
        indexOfColumn = j;
        while (cells.get(i, j).getType() != CellValueType.IS_NULL) {
            rowValue.add(cells.get(i, j).getValue().toString());
            i++;
        }
        return rowValue;

    }

    public ArrayList<String> getColumnName() {
        ArrayList<String> columnName = new ArrayList<>();
        int i = 0;
        String name = "";
        do {
            name = cells.get(indexOfRow, i).getValue().toString();
            columnName.add(name);
            i++;
        } while (cells.get(indexOfRow, i).getValue() != null);
        return columnName;
    }

    public ArrayList<CustomerU> getCustomers(ArrayList<Integer> arr) {
        ArrayList<CustomerU> customers = new ArrayList<>();
        int startRow = indexOfRow + 1;
        int startColumn = indexOfColumn;
        CustomerU customer;
        for (int k = 0; k < getRowValue().size() - indexOfRow-1; k++) {
            customer = new CustomerU();
            for (int i = 0; i < getColumnName().size(); i++) {
                if (arr.get(i)!= -1) {
                    if(i<arr.size()){
                        int p = arr.get(i) + 1;
                        String s = cells.get(k + startRow, i + startColumn).getStringValue();
                        customer.setAccountID(id);
                        customer.setString(p, s);
                        //Log.d("Fuck", cells.get(k + startRow, i + startColumn).getStringValue());
                    }

                }else {
                    break;
                }
            }
            customers.add(customer);

        }
        return customers;
    }
}
