package registrationapp.com;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView register, fail_case, user_count, pass_count;
    Button sign_in_b;
    private EditText user, pass;
    String user_string, pass_string, f_name, sex;
    int age;
    private ArrayList<String> user_arr, pass_arr;
    TextWatcher textWatcherUserCount, textWatcherPassCount;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        register = findViewById(R.id.register_url);
        sign_in_b = findViewById(R.id.login_button);
        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        user_count = findViewById(R.id.user_count);
        pass_count = findViewById(R.id.pass_count);
        fail_case = findViewById(R.id.failed_view);
        f_name = getIntent().getStringExtra("name");
        sex = getIntent().getStringExtra("sex");
        age = getIntent().getIntExtra("age", 0);
        user_arr = getIntent().getStringArrayListExtra("user_arr");
        pass_arr = getIntent().getStringArrayListExtra("pass_arr");

        textWatcherUserCount = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                user_count.setText(String.valueOf(s.length())+"/20");
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        user.addTextChangedListener(textWatcherUserCount);

        textWatcherPassCount = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pass_count.setText(String.valueOf(s.length())+"/20");
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        pass.addTextChangedListener(textWatcherPassCount);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("user_arr", user_arr);
                intent.putExtra("pass_arr", pass_arr);
                startActivity(intent);
                user.setText("");
                pass.setText("");
            }
        });
        sign_in_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_string = user.getText().toString();
                pass_string = pass.getText().toString();
                user_arr = getIntent().getStringArrayListExtra("user_arr");
                pass_arr = getIntent().getStringArrayListExtra("pass_arr");
                intent = new Intent(MainActivity.this, ThirdActivity.class);
                intent.putExtra("user_arr", user_arr);
                intent.putExtra("pass_arr", pass_arr);
                intent.putExtra("name", f_name);
                intent.putExtra("sex", sex);
                intent.putExtra("age", age);
                if (user_arr != null && pass_arr != null) {
                    for (int i = 0; i < user_arr.size(); i++) {
                        if (user_string.equals(user_arr.get(i)) && pass_string.equals(pass_arr.get(i))) {
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            return;
                        }
                    }
                }
                if (user_string.isEmpty() || pass_string.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
                if (!user_string.equals(f_name) && !pass_string.equals(sex)){
                    fail_case.setText(R.string.fail);
                }
            }
        });
    }
}