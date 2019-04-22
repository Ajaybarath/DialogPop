package com.example.dialogpop;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final List<Character> list = new ArrayList();
    Dialog dialog;
    ListView listView;
    Adapter_character_row adapterCharacterRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        new Thread()
        {
            public void run()
            {
                MainActivity.this.runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        //Do your UI operations like dialog opening or Toast here
                        adapterCharacterRow = new Adapter_character_row(list, MainActivity.this);
                        dialog = new Dialog(MainActivity.this);
                        updateList();
                        dialog.setContentView(R.layout.dialog_screen_activity);
                        listView = dialog.findViewById(R.id.character_list);
                        listView.setAdapter(adapterCharacterRow);
                        Window window = dialog.getWindow();
                        window.setBackgroundDrawableResource(R.drawable.dialog_bg);
                        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    }
                });
            }
        }.start();



        adapterCharacterRow = new Adapter_character_row(list, MainActivity.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                createDialog();

            }
        });

    }

    public void updateList(){

        list.add(new Character("Animal Welfare", "fg"));
        list.add(new Character("Children", "fg"));
        list.add(new Character("Civil Rights", "fg"));
        list.add(new Character("Disaster Management", "fg"));
        list.add(new Character("Economic Empowerment", "fg"));
        list.add(new Character("Education", "fg"));
        list.add(new Character("Environment", "fg"));
        list.add(new Character("Health", "fg"));
        list.add(new Character("Human Rights", "fg"));
        list.add(new Character("Poverty Alleviation", "fg"));
        list.add(new Character("Politics", "fg"));
        list.add(new Character("Science & Technology", "fg"));
        list.add(new Character("Social Services", "fg"));
        list.add(new Character("Women Welfare", "fg"));

    }


    public void createDialog(){

        Button saveBtn = dialog.findViewById(R.id.character_save);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i=0; i<list.size();i++){
                    if(list.get(i).isStatus()){
                        System.out.println("Checked characters are " + list.get(i).getName());
                    }
                }

                dialog.dismiss();
            }
        });

    }

}