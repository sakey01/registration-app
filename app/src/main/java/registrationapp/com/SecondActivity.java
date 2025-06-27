package registrationapp.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    private EditText full_name, user, pass, re_pass;
    String name, username, password, sex;
    Button clear_b, register_b, return_b;
    RadioButton m, f;
    RadioGroup radioGroup;
    Spinner spinner;
    int age, sex_id;
    private ArrayList<String> user_arr, pass_arr;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.registration_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinner = findViewById(R.id.spinner);
        ArrayList<Integer> years = new ArrayList<>();
        for (int i = 2024; i >= 1920; i--) {
            years.add(i);
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(R.layout.spinner_item);

        full_name = findViewById(R.id.full_name);
        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        m = findViewById(R.id.male_radio);
        f = findViewById(R.id.female_radio);
        re_pass = findViewById(R.id.re_password);
        radioGroup = findViewById(R.id.radioGroup);
        sex_id = radioGroup.getCheckedRadioButtonId();
        age = Integer.parseInt(spinner.getSelectedItem().toString());
        user_arr = new ArrayList<>();
        pass_arr = new ArrayList<>();

        clear_b = findViewById(R.id.clear_b);
        clear_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                full_name.setText("");
                user.setText("");
                pass.setText("");
                re_pass.setText("");
                m.setChecked(false);
                f.setChecked(false);
                spinner.setSelection(0);
            }
        });
        register_b = findViewById(R.id.register_b);
        register_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (full_name.getText().toString().isEmpty()){
                    Toast.makeText(SecondActivity.this, "Please Enter Your Full Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (!m.isChecked() && !f.isChecked()){
                    Toast.makeText(SecondActivity.this, "Please Select Your Sex", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (user.getText().toString().isEmpty()) {
                    Toast.makeText(SecondActivity.this, "Please Enter A Username", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (pass.getText().toString().isEmpty() || re_pass.getText().toString().isEmpty()){
                    Toast.makeText(SecondActivity.this, "Please Enter A Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (!pass.getText().toString().equals(re_pass.getText().toString())){
                    Toast.makeText(SecondActivity.this, "Your Passwords Do Not Match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (m.isChecked()){
                    sex = "Male";
                }
                else if (f.isChecked()) {
                    sex = "Female";
                }
                if (!user_arr.contains(username) || user_arr.isEmpty()){
                    //sends data to home page
                    Toast.makeText(SecondActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    username = user.getText().toString();
                    password = pass.getText().toString();
                    name = full_name.getText().toString();
                    age = 2024 - Integer.parseInt(spinner.getSelectedItem().toString());
                    user_arr.add(username);
                    pass_arr.add(password);
                    intent = new Intent(SecondActivity.this, MainActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("user_arr", user_arr);
                    intent.putExtra("pass_arr", pass_arr);
                    intent.putExtra("sex", sex);
                    intent.putExtra("age", age);
                    startActivity(intent);
                }
                else if (user_arr.contains(username)){
                    Toast.makeText(SecondActivity.this, "Username Already Exists, Please Try Again", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        return_b = findViewById(R.id.return_b);
        return_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}