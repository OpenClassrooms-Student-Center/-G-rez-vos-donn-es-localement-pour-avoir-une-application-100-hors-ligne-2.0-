package com.openclassrooms.savemytrip;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.openclassrooms.savemytrip.databinding.ActivityMainBinding;
import com.openclassrooms.savemytrip.todolist.TodoListActivity;
import com.openclassrooms.savemytrip.tripbook.TripBookActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.mainActivityButtonTripBook.setOnClickListener(view -> {
            Intent intent = new Intent(this, TripBookActivity.class);
            startActivity(intent);
        });
        binding.mainActivityButtonTodoList.setOnClickListener(view -> {
            Intent intent = new Intent(this, TodoListActivity.class);
            startActivity(intent);
        });
    }
}
