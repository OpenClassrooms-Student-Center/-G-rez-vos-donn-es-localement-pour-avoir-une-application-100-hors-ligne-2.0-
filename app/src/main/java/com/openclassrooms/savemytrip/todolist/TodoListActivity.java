package com.openclassrooms.savemytrip.todolist;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.openclassrooms.savemytrip.R;
import com.openclassrooms.savemytrip.databinding.ActivityTodoListBinding;


public class TodoListActivity extends AppCompatActivity {
    private ActivityTodoListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTodoListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    // -------------------
    // UI
    // -------------------

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        configureSpinner();
        binding.todoListActivityButtonAdd.setOnClickListener(view -> {
            /*TODO*/
        });
    }

    private void configureSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.todoListActivitySpinner.setAdapter(adapter);
    }
}
