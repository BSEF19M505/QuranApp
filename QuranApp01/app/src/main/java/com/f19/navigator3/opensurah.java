package com.f19.navigator3;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.snackbar.Snackbar;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.f19.navigator3.DBHelper;
import com.f19.navigator3.ExcelHelper;

import android.os.Bundle;


public class opensurah extends AppCompatActivity {


            static {
                System.setProperty(
                        "org.apache.poi.javax.xml.stream.XMLInputFactory",
                        "com.fasterxml.aalto.stax.InputFactoryImpl"
                );
                System.setProperty(
                        "org.apache.poi.javax.xml.stream.XMLOutputFactory",
                        "com.fasterxml.aalto.stax.OutputFactoryImpl"
                );
                System.setProperty(
                        "org.apache.poi.javax.xml.stream.XMLEventFactory",
                        "com.fasterxml.aalto.stax.EventFactoryImpl"
                );
            }

            TextView lbl;
            DBHelper controller = new DBHelper(this);
            ListView lv;
            private static final int PERMISSION_REQUEST_MEMORY_ACCESS = 0;
            private static String fileType = "";
            private View mLayout;
            private static String extensionXLS = "XLS";
            private static String extensionXLXS = "XLXS";
            ActivityResultLauncher<Intent> filePicker;

            int id,start,end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opensurah);

                Intent Intent = getIntent();
                id =Intent.getIntExtra("id",0);
                start = Intent.getIntExtra("start", 0);
                end = Intent.getIntExtra("end", 0);


                // Creating Input Stream

                try {
                    InputStream myInput = getAssets().open("tayah.xlsx");

                    //File file = new File(context.getExternalFilesDir(null), raw/tsurah.xlsx);
                    // FileInputStream myInput = new FileInputStream(file);

                    // Create a POIFSFileSystem object
                    // POIXFS myFileSystem = new POIFSFileSystem(myInput);

                    // Create a workbook using the File System
                    XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);
                    DBHelper dbAdapter = new DBHelper(this);
                    // Get the first sheet from workbook
                    XSSFSheet mySheet = myWorkBook.getSheetAt(0);

                    /** We now need something to iterate through the cells. **/
                    Iterator rowIter = mySheet.rowIterator();
                    dbAdapter.open();
                    dbAdapter.delete();
                    dbAdapter.close();
                    dbAdapter.open();

                    ExcelHelper.insertExcelToSqlite(dbAdapter, mySheet,id,start,end);

                    dbAdapter.close();
                    myInput.close();
                    FillList();
                } catch (IOException e) {
                    lbl.setText("First " + e.getMessage().toString());
                    e.printStackTrace();
                }
            }
            public void FillList(){
                try {
                    if (controller == null) {
                        DBHelper controller = new DBHelper(this);
                    }
                    ArrayList<Model> myList = controller.getProducts();
                    if (myList.size() != 0) {
                        lv = findViewById(R.id.lstView);
                        CustomAdapter adapter=new CustomAdapter(this,myList);

                        lv.setAdapter(adapter);
                    }
                } catch (Exception ex) {
                    Toast("FillList error: " + ex.getMessage(), ex);
                }
            }

            void Toast(String message, Exception ex) {
                if (ex != null)
                    Log.e("Error", ex.getMessage().toString());
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();

            }

        }