package zeev.fraiman.dbpictures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bAdd, bView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=MainActivity.this;

        bAdd=findViewById(R.id.bAdd);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go=new Intent(context, AddToDB.class);
                startActivity(go);
            }
        });

        bView=findViewById(R.id.bView);
        bView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go=new Intent(context, ViewFromDB.class);
                startActivity(go);
            }
        });
    }
}