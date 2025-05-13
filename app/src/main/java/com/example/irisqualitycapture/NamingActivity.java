// ✅ Cleaned NamingActivity.java
package com.example.irisqualitycapture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.irisqualitycapture.medium.MainActivity3;

public class NamingActivity extends Activity {
    private String sub_ID;
    private String session_ID;
    private String trial_Num;

    private String TAG = "NamingActivity:";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Log.d(TAG, "###########height:" + height + " Width" + width);

        setContentView(R.layout.naming);

        EditText usr_sub_id = findViewById(R.id.editsubid);
        usr_sub_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                sub_ID = editable.toString();
            }
        });

        EditText usr_session_id = findViewById(R.id.editsessionid);
        usr_session_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                session_ID = editable.toString();
            }
        });

        EditText usr_trial_num = findViewById(R.id.edittrailnum);
        usr_trial_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                trial_Num = editable.toString();
            }
        });

        Button next_button = findViewById(R.id.nextButton);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NamingActivity.this, MainActivity3.class);
                intent.putExtra("N_subID", sub_ID);
                intent.putExtra("N_sessionID", session_ID);
                intent.putExtra("N_trialNum", trial_Num);
                startActivity(intent);
            }
        });
    }
}