package com.challange.huawei_ieee_deu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class search_page extends AppCompatActivity {

    Button codepg;      //Going code button
    Button frstpg;      //Turning first page button
    String wbstrn;     // internet sites string changed shape
    Intent i;

    TextView pgshwr;       //PAge Showing Line
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        codepg = (Button) findViewById(R.id.button3);
        frstpg = (Button) findViewById(R.id.button);
        i=getIntent();
        pgshwr=(TextView)findViewById(R.id.textView);


        Bundle b=new Bundle();
        b=getIntent().getExtras();
        wbstrn=b.getString("site_domain");
        pgshwr.setText(wbstrn);
        i=new Intent(this,MainActivity.class);

        codepg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchFor=((TextView) findViewById(R.id.textView)).getText().toString();
                Intent viewSearch = new Intent(Intent.ACTION_WEB_SEARCH);
                viewSearch.putExtra(SearchManager.QUERY, searchFor);
                startActivity(viewSearch);
            }
        });

        frstpg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

               startActivity(i);

            }
        });


    }

}
