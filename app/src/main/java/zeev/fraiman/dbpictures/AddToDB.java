package zeev.fraiman.dbpictures;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class AddToDB extends AppCompatActivity {

    ImageView ivImage;
    private String imageFilename;
    Uri imageUri;
    HelperDB helperDB;
    SQLiteDatabase db;
    boolean isPhoto=false;
    Context context;
    String imageFileLocation="nothing";
    EditText etComment;
    Button bSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_db);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 200);

        context=AddToDB.this;
        etComment=findViewById(R.id.etComment);
        bSave=findViewById(R.id.bSave);
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stComment=etComment.getText().toString();
                if (stComment.equals(""))
                    stComment="Nothing";
                BitmapDrawable drawable = (BitmapDrawable) ivImage.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                //Bitmap bitmap = BitmapFactory.decodeFile(stringFilePath);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                byte[] bytesImage = byteArrayOutputStream.toByteArray();
                helperDB=new HelperDB(context);
                db=helperDB.getWritableDatabase();
                ContentValues contentValues=new ContentValues();
                contentValues.put(helperDB.PLACE_COMMENT, stComment);
                contentValues.put(helperDB.PLACE_PICTURE, bytesImage);
                db.insert(helperDB.PLACE_TABLE,null,contentValues);
                db.close();
                Toast.makeText(context, "Picture and comment now in DB", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        ivImage=findViewById(R.id.ivImage);
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageFilename="ZeFra_"+(int)(1000000*Math.random());
                callCameraApp();
            }
        });
    }

    private void callCameraApp() {
        Intent go=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File photoFile=null;
        try {
            photoFile=createImageFile();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        String authorities="zeev.fraiman.dbpictures.fileprovider";
        imageUri= FileProvider.getUriForFile(context,
                authorities, photoFile);
        go.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        startActivityForResult(go, 3);
    }

    private File createImageFile() throws IOException {
        //File storageDirectory= Environment.
          //      getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File storageDirectory=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile=File.createTempFile(imageFilename,".jpg",
                storageDirectory);
        imageFileLocation=imageFile.getAbsolutePath();
        return imageFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==3 && resultCode==RESULT_OK)  {
            Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show();
            ivImage.setImageURI(imageUri);
            isPhoto=true;
        }

    }



}