package com.example.b2101029_0526;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "MainActivity onStart 호출됨",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        Toast.makeText(getApplicationContext(), "MainActivity onStop 호출됨",
                Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(getApplicationContext(), "MainActivity onDestroy 호출됨",
                Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Toast.makeText(getApplicationContext(), "MainActivity onPause 호출됨",
                Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "MainActivity onResume 호출됨",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toast.makeText(getApplicationContext(), "SecondActivity onCreate 호출됨",
                Toast.LENGTH_SHORT).show();

        Intent inIntent = getIntent(); // intent 있는거 가져옴
        int num1 = inIntent.getIntExtra("Num1",0); //숫자로 받았으니 int로 받아옴 값이 없을떄는 0
        int num2 = inIntent.getIntExtra("Num2",0);
        int hapValue = num1 + num2;

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() { //hapValue메인에 던져주기
            @Override
            public void onClick(View view) {
                Intent outintent = new Intent(getApplicationContext(),
                        MainActivity.class);
                outintent.putExtra("Hap",hapValue);
                setResult(RESULT_OK, outintent); //반환
                finish();//second 사라짐
            }
        });

    }
}