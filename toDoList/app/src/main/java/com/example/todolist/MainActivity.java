package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    //decalring the variables for the xml contents
    private EditText itemET;
    private Button btn;
    private ListView itemLists;

    //declaring array to store the values.
    private ArrayList <String> items;
    //declaring array adaptars they will fill in the grid views
    private ArrayAdapter <String> adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting the references
        itemET = findViewById(R.id.item_edit_text);
        btn = findViewById(R.id.add_btn);
        itemLists = findViewById(R.id.items_list);

        items = FileHelper.readData(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        itemLists.setAdapter(adapter);


        btn.setOnClickListener(this);
        itemLists.setOnItemClickListener(this);


    }

    @Override
    public void onClick(View view) {
        //making a switch case to assign the corresponding task to the button.
        switch (view.getId()){
            case R.id.add_btn:
                String itemsEntered = itemET.getText().toString();
                adapter.add(itemsEntered);
                itemET.setText("");

                FileHelper.writeData(items, this);

                //toast is the small notification on the screen below.
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        items.remove(i);
        adapter.notifyDataSetChanged();
        FileHelper.writeData(items, this);
        Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
    }
}