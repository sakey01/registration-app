package registrationapp.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {
    TextView name, sex_view, age;
    String f_name, sex;
    int f_age;
    Button b;
    private ArrayList<String> user_arr, pass_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.welcome_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        name = findViewById(R.id.full_name);
        sex_view = findViewById(R.id.sex);
        age = findViewById(R.id.age);

        f_name = getIntent().getStringExtra("name");
        sex = getIntent().getStringExtra("sex");
        f_age = getIntent().getIntExtra("age", 0);

        name.setText(f_name);
        sex_view.setText(sex);
        age.setText(String.valueOf(f_age));
        user_arr = getIntent().getStringArrayListExtra("user_arr");
        pass_arr = getIntent().getStringArrayListExtra("pass_arr");

        b = findViewById(R.id.logout_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            //sends data to home page
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                intent.putExtra("name", f_name);
                intent.putExtra("sex", sex);
                intent.putExtra("age", f_age);
                intent.putExtra("user_arr", user_arr);
                intent.putExtra("pass_arr", pass_arr);
                startActivity(intent);
                Toast.makeText(ThirdActivity.this, "You Have Logged Out", Toast.LENGTH_SHORT).show();
            }
        });
    }
}