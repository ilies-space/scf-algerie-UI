package com.mbg.scf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import static com.mbg.scf.homePage.Lan;

public class detailsPage extends AppCompatActivity {

    TextView code, name , description ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ui Language :
        switch (Lan){
            case "infofr.db" :
                setContentView(R.layout.activity_details_page_fr);
                break;
            case "infoar.db" :
                setContentView(R.layout.activity_details_page_ar);
                break;
        }

        //Receive the selected Data & Display it :
        //Initialize
        code = findViewById(R.id.codetv);
        name = findViewById(R.id.nametv);
        description = findViewById(R.id.Descriptiontv);
        //Displace
        Intent intent = getIntent();
        //code.setText(intent.getStringExtra("codeValue"));
        name.setText(intent.getStringExtra("nameValue"));
        description.setText(intent.getStringExtra("descriptionValue"));

        //forLong Text Description :
        code.setMovementMethod(new ScrollingMovementMethod());





    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    public void close(View view) {
        onBackPressed();
    }
}
