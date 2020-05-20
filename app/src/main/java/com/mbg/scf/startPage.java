package com.mbg.scf;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class startPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
    }

    public void moreInfo(View view) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (!isFinishing()){
                    new AlertDialog.Builder(startPage.this)
                            .setTitle("info")
                            .setMessage("Cette application est fournie sous la supervision du Dr: Mohamed El-Habib Merhoum .")
                            .setCancelable(true)
                            .setPositiveButton("évaluer", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Uri uri = Uri.parse("market://details?id=" + startPage.this.getPackageName());
                                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                                    // To count with Play market backstack, After pressing back button,
                                    // to taken back to our application, we need to add following flags to intent.
                                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                                    try {
                                        startActivity(goToMarket);
                                    } catch (ActivityNotFoundException e) {
                                        startActivity(new Intent(Intent.ACTION_VIEW,
                                                Uri.parse("http://play.google.com/store/apps/details?id=" + startPage.this.getPackageName())));
                                    }
                                }
                            })
                            .setNegativeButton("Fermer", null)
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .show();
                }
            }
        });


    }

    public void arabic(View view) {
        Intent myIntent2 = new Intent(startPage.this, homePage.class);
        myIntent2.putExtra("lan", "infoar.db");
        startPage.this.startActivity(myIntent2);
    }

    public void france(View view) {
        Intent myIntent2 = new Intent(startPage.this, homePage.class);
        myIntent2.putExtra("lan", "infofr.db");
        startPage.this.startActivity(myIntent2);
    }
}
