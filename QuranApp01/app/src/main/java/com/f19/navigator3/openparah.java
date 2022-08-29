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

public class openparah extends AppCompatActivity {

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
        setContentView(R.layout.activity_openparah);

        Intent Intent = getIntent();
        id =Intent.getIntExtra("id",0);
        start = Intent.getIntExtra("start", 0);
        end = Intent.getIntExtra("end", 0);


        // Creating Input Stream

        try {
            InputStream myInput = getAssets().open("tayah.xlsx");


            XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);
            DBHelper dbAdapter = new DBHelper(this);

            XSSFSheet mySheet = myWorkBook.getSheetAt(0);


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
                    DBHelper controller = new DBHelper(openparah.this);
                }
                ArrayList<Model> myList = controller.getProducts();
                if (myList.size() != 0) {
                    lv = findViewById(R.id.lstView);
                    CustomAdapter adapter=new CustomAdapter(this,myList);
                    /*ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,myList);
                    /*ListAdapter adapter = new SimpleAdapter(openparah.this, myList,
                            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new String[]{DBHelper.arabic,
                            DBHelper.urdu},
                            new int[]{ R.id.txtarabic,
                                    R.id.txturdu});*/
                    lv.setAdapter(adapter);
                }
            } catch (Exception ex) {
                Toast("FillList error: " + ex.getMessage(), ex);
            }
        }

        void Toast(String message, Exception ex) {
            if (ex != null)
                Log.e("Error", ex.getMessage().toString());
            Toast.makeText(openparah.this, message, Toast.LENGTH_LONG).show();

        }













      /*  filePicker = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        Intent intent1 = result.getData();

                        Uri uri = intent1.getData();
                        ReadExcelFile(openparah.this
                                , uri);

                    }
                });*/


   /* private boolean CheckPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            Snackbar.make(mLayout, R.string.storage_access_required,
                    Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requestStoragePermission();
                }
            }).show();

            return false;
        }
    }*/



   /* public void ChooseFile() {
        try {
            Intent fileIntent = new Intent(Intent.ACTION_GET_CONTENT);
            fileIntent.addCategory(Intent.CATEGORY_OPENABLE);

            if (fileType == extensionXLS)
                fileIntent.setType("application/vnd.ms-excel");
            else
                fileIntent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            filePicker.launch(fileIntent);
        } catch (Exception ex) {
            Toast("ChooseFile error: " + ex.getMessage().toString(), ex);

        }
    }*/



    //@Override
    /*public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_MEMORY_ACCESS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                OpenFilePicker();
            } else {
                Snackbar.make(mLayout, R.string.storage_access_denied,
                        Snackbar.LENGTH_SHORT)
                        .show();
            }
        }
    }*/

   /* private void requestStoragePermission() {

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(openparah.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_MEMORY_ACCESS);

        } else {
            Snackbar.make(mLayout, R.string.storage_unavailable, Snackbar.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_MEMORY_ACCESS);
        }
    }*/

    //@Override
    /*public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    //@Override
    /*public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_import_xls) {
            fileType = extensionXLS;
            OpenFilePicker();
        } else if (id == R.id.action_import_xlxs) {
            fileType = extensionXLXS;
            OpenFilePicker();
        }

        return super.onOptionsItemSelected(item);
    }*/

   /* public void OpenFilePicker() {
        try {
            //if (CheckPermission()) {
                ChooseFile();
            //}
        } catch (ActivityNotFoundException e) {
            lbl.setText("No activity can handle picking a file. Showing alternatives.");
        }

    }*/
}