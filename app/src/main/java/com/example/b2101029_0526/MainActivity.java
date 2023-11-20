package com.example.b2101029_0526;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
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
        super.onStop(); //super위에 해야함 사라지고 해야하니까
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
/*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            int hap = data.getIntExtra("Hap",0);
            Toast.makeText(getApplicationContext(), "합계 : " + hap,
                    Toast.LENGTH_SHORT).show();
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(), "MainActivity onCreate 호출됨",
                Toast.LENGTH_SHORT).show();

        EditText editText1 = (EditText) findViewById(R.id.editText1);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        Button btnDial = (Button) findViewById(R.id.button3);
        Button btnWeb = (Button) findViewById(R.id.button4);
        Button btnMap = (Button) findViewById(R.id.button5);
        Button btnSearch = (Button) findViewById(R.id.button6);
        Button btnSms = (Button) findViewById(R.id.button7);
        Button btnPhoto = (Button) findViewById(R.id.button8);


        ActivityResultLauncher resultLauncher;
        resultLauncher = registerForActivityResult(new
                ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                int hap = result.getData().getIntExtra("Hap", 0);
                Toast.makeText(getApplicationContext(), "Lancher 합계 : " + hap,
                        Toast.LENGTH_SHORT).show();
            }
            //동작시킬 내용
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() { //버튼을 누르면 입력한 텍스트를 가지고 second로 가야함
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);//넘어가기 위해 intent
                intent.putExtra("Num1", Integer.parseInt(editText1.getText().toString())); //데이터 가져가야함 (int 형으로)
                intent.putExtra("Num2", Integer.parseInt(editText2.getText().toString()));
                //startActivityForResult(intent, 0); //데이터 던져짐 ->start 안쓰는 법 activityresultlauncher(런치타입으로 바꿔야함)
                resultLauncher.launch(intent);
            }
        });

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:01012345678"));
                startActivity(intent);
            }
        });

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.kunsan.ac.kr"));
                startActivity(intent);
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.co.kr/maps?q="
                       +35.944715+","+126.682935));
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,"군산대학교");
                startActivity(intent);
            }
        });

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO,
                        Uri.parse("smsto:01012345678"));
                intent.putExtra("sms_body","메시지 내용.....");
                startActivity(intent);
            }
        });

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });
    }
}