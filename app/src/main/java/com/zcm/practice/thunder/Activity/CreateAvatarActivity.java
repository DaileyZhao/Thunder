package com.zcm.practice.thunder.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zcm.practice.thunder.BaseActivity;
import com.zcm.practice.thunder.R;


/**
 * Copyright(c) 2016 All Rights Reserved.
 * Author: ZhaoCunming
 * Time: 16-8-21 12:05
 * E-mail: zhaocunming@dayima.com
 * Version：V1.0.1
 * ProjectName: Thunder
 * Description: TODO
 */
public class CreateAvatarActivity extends BaseActivity {
    private Intent intent;
    private String username="";
    private int age;

    ImageView allot_avatar;
    EditText name_edit;
    Button avator_next_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createavataractivity);
        allot_avatar= (ImageView) findViewById(R.id.allot_avator);
        name_edit=(EditText) findViewById(R.id.name_edit);
        avator_next_btn=(Button) findViewById(R.id.avatar_next_btn);
        avator_next_btn.setClickable(false);
        avator_next_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(CreateAvatarActivity.this, "button可用!", Toast.LENGTH_SHORT).show();
            }
        });
        name_edit.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
                avator_next_btn.setClickable(false);
                temp=s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                selectionStart=name_edit.getSelectionStart();
                selectionEnd=name_edit.getSelectionEnd();
                if (temp.length()>20) {
                    Toast.makeText(CreateAvatarActivity.this, "名字过长!", Toast.LENGTH_SHORT).show();
                    avator_next_btn.setClickable(false);
                    //s.delete(selectionStart-1, selectionEnd);
                    //int tempSelection=selectionStart;
                    //name_edit.setText(s);
                    //name_edit.setSelection(tempSelection);
                }else {
                    username=temp.toString();
                    avator_next_btn.setClickable(true);
                }
            }
        });
    }
}
