package com.openclassrooms.savemytrip.todolist;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.savemytrip.R;
import com.openclassrooms.savemytrip.databinding.ActivityTodoListBinding;
import com.openclassrooms.savemytrip.injection.ViewModelFactory;
import com.openclassrooms.savemytrip.models.Item;
import com.openclassrooms.savemytrip.models.User;

import java.util.List;

public class TodoListActivity extends AppCompatActivity implements ItemAdapter.Listener {
    private ActivityTodoListBinding binding;
    // FOR DATA
    private ItemViewModel itemViewModel;
    private ItemAdapter adapter;
    private static final int USER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTodoListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        configureViewModel();
        initView();

        getCurrentUser();
        getItems();
    }

    @Override
    public void onClickDeleteButton(Item item) {
        deleteItem(item);
    }

    @Override
    public void onItemClick(Item item) {
        updateItem(item);
    }

    // -------------------
    // DATA
    // -------------------

    private void configureViewModel() {
        ViewModelFactory mViewModelFactory = new ViewModelFactory(this);
        this.itemViewModel = new ViewModelProvider(this, mViewModelFactory).get(ItemViewModel.class);
        this.itemViewModel.init(USER_ID);
    }

    private void getCurrentUser() {
        LiveData<User> userLiveData = itemViewModel.getUser();
        if (userLiveData != null) {
            userLiveData.observe(this, this::updateView);
        }
    }

    private void getItems() {
        this.itemViewModel.getItems(USER_ID).observe(this, this::updateItemsList);
    }

    private void createItem() {
        itemViewModel.createItem(binding.todoListActivityEditText.getText().toString(), binding.todoListActivitySpinner.getSelectedItemPosition(), USER_ID);
        binding.todoListActivityEditText.setText("");
    }

    private void deleteItem(Item item) {
        this.itemViewModel.deleteItem(item.getId());
    }

    private void updateItem(Item item) {
        item.setSelected(!item.getSelected());
        this.itemViewModel.updateItem(item);
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
            createItem();
        });
        configureRecyclerView();
    }

    private void configureSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.todoListActivitySpinner.setAdapter(adapter);
    }

    private void configureRecyclerView() {
        this.adapter = new ItemAdapter(this);
        binding.todoListActivityRecyclerView.setAdapter(this.adapter);
        binding.todoListActivityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void updateView(User user) {
        binding.todoListActivityHeaderProfileText.setText(user.getUsername());
        Glide.with(this).load(user.getUrlPicture()).apply(RequestOptions.circleCropTransform()).into(binding.todoListActivityHeaderProfileImage);
    }

    private void updateItemsList(List<Item> items) {
        this.adapter.updateData(items);
    }
}
