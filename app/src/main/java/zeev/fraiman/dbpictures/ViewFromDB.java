package zeev.fraiman.dbpictures;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewFromDB extends AppCompatActivity {

    HelperDB helperDB;
    SQLiteDatabase db;
    ListView lv;
    ArrayList<String> all;
    ArrayAdapter<String> adapter;
    Info info;
    ArrayList<Info> allinfo;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_from_db);

        context=ViewFromDB.this;
        lv = findViewById(R.id.lv);
        buildList();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                info=allinfo.get(position);
                AlertDialog.Builder adb=new AlertDialog.Builder(context);
                adb.setTitle(info.getInfoComment());
                Bitmap bitmap= BitmapFactory.decodeByteArray(info.getInfoImage(),0,
                        info.getInfoImage().length);
                ImageView imageView=new ImageView(context);
                imageView.setImageBitmap(bitmap);
                adb.setView(imageView);
                adb.create().show();
            }
        });
    }

    private void buildList() {
        all=new ArrayList<>();
        allinfo=new ArrayList<>();
        helperDB=new HelperDB(context);
        db=helperDB.getReadableDatabase();
        Cursor cursor=db.query(helperDB.PLACE_TABLE,
                null,null,null,
                null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())  {
            String st=cursor.getString(0);
            byte[] imagecode=cursor.getBlob(1);
            info=new Info(st, imagecode);
            allinfo.add(info);
            all.add(info.getInfoComment());
            cursor.moveToNext();
        }
        db.close();
        adapter=new ArrayAdapter<>(context,
                android.R.layout.simple_list_item_1,
                all);
        lv.setAdapter(adapter);
    }
}