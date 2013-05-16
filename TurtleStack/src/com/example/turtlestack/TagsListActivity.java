package com.example.turtlestack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TagsListActivity extends Activity {
    
    // List view
    private ListView lv;
     
    // Listview Adapter
    ArrayAdapter<String> adapter;
     
    // Search EditText
    EditText inputSearch;
     
     
    // ArrayList for Listview
    ArrayList<String> arrayOfTags;
 
    TagDataSource ts;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags_list);
         
         
        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
         
        // Adding items to listview
		ts = TagDataSource.getInstance(this);
		ts.open();
        arrayOfTags = ts.getAllTheTags();
        adapter =  new ArrayAdapter<String>(this, R.layout.list_row,arrayOfTags);
        lv.setAdapter(adapter);
        ts.close();
        
         
        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {
             
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                TagsListActivity.this.adapter.getFilter().filter(cs);   
            }
             
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
                 
            }

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
             

        });

        OnItemClickListener itemClickListener = new OnItemClickListener() {
        	@Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
        		startCloud(position);
             }
        };
       
        lv.setOnItemClickListener(itemClickListener);

    }  
    public void startCloud (int position) {
		Intent intent = new Intent(this, TagCloudActivity.class);
		intent.putExtra("mainTag", arrayOfTags.get(position));
		startActivity(intent);
    }

}