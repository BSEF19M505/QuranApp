package com.f19.navigator3;

import static org.apache.poi.hssf.record.FormulaSpecialCachedValue.STRING;

import static java.sql.Types.NUMERIC;

import android.content.ContentValues;
import android.util.Log;
import android.widget.Toast;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Iterator;

public class ExcelHelper {

    public static final String Tablename = "Ayat";
    public static final String ayahid = "ayahid";// 0 integer
    public static final String arabic = "arabic";// 0 integer
    public static final String urdu = "urdu";// 1 text(String)
    public static final int paraid=0;

    public static void insertExcelToSqlite(DBHelper dbAdapter, Sheet sheet,int id,int s,int e) {


        for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext(); ) {

            Row row = rit.next();
            row.setRowNum(s);
int count=row.getRowNum();
            if(count<e) {


                    // Iterate through all the cells in a row (Excluding header row)
                    Iterator<Cell> cellIterator = row.cellIterator();


                    ContentValues contentValues = new ContentValues();
                    // row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(NUMERIC);
                    row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(STRING);
                    row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(STRING);

                    contentValues.put(arabic, row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
                    contentValues.put(urdu, row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
                    //contentValues.put(paraid, row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());

                    try {
                        if (dbAdapter.insert("Ayat", contentValues) < 0) {
                            return;
                        }
                    } catch (Exception ex) {
                        Log.d("Exception in importing", ex.getMessage().toString());
                    }
                    count=row.getRowNum();
                }


        }
    }

}

