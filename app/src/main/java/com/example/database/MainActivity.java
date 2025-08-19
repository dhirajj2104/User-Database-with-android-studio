package com.example.database;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText idInput, usernameInput, passwordInput;
    Button insertBtn, updateBtn, deleteBtn, viewBtn, viewAllBtn;
    TextView resultText;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idInput = findViewById(R.id.idInput);
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        insertBtn = findViewById(R.id.insertBtn);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        viewBtn = findViewById(R.id.viewBtn);
        viewAllBtn = findViewById(R.id.viewAllBtn);
        resultText = findViewById(R.id.resultText);

        dbHelper = new DatabaseHelper(this);

        insertBtn.setOnClickListener(v -> insertUser());
        updateBtn.setOnClickListener(v -> updateUser());
        deleteBtn.setOnClickListener(v -> deleteUser());
        viewBtn.setOnClickListener(v -> viewUser());
        viewAllBtn.setOnClickListener(v -> viewAllUsers());
    }

    private void insertUser() {
        int id = Integer.parseInt(idInput.getText().toString());
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (dbHelper.insertUser(id, username, password)) {
            Toast.makeText(this, "User Inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Insert Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUser() {
        int id = Integer.parseInt(idInput.getText().toString());
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (dbHelper.updateUser(id, username, password)) {
            Toast.makeText(this, "User Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteUser() {
        int id = Integer.parseInt(idInput.getText().toString());

        if (dbHelper.deleteUser(id)) {
            Toast.makeText(this, "User Deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Delete Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void viewUser() {
        int id = Integer.parseInt(idInput.getText().toString());
        Cursor cursor = dbHelper.getUser(id);

        if (cursor.moveToFirst()) {
            String userData = "ID: " + cursor.getInt(0) + "\nUsername: " + cursor.getString(1) + "\nPassword: " + cursor.getString(2);
            resultText.setText(userData);
        } else {
            resultText.setText("No user found");
        }
    }

    private void viewAllUsers() {
        Cursor cursor = dbHelper.getAllUsers();
        StringBuilder users = new StringBuilder();

        while (cursor.moveToNext()) {
            users.append("ID: ").append(cursor.getInt(0))
                    .append("\nUsername: ").append(cursor.getString(1))
                    .append("\nPassword: ").append(cursor.getString(2))
                    .append("\n\n");
        }

        if (users.length() == 0) {
            resultText.setText("No users found");
        } else {
            resultText.setText(users.toString());
        }
    }
}
