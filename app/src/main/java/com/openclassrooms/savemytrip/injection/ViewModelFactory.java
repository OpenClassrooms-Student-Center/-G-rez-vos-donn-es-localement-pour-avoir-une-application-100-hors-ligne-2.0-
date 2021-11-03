package com.openclassrooms.savemytrip.injection;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.openclassrooms.savemytrip.database.SaveMyTripDatabase;
import com.openclassrooms.savemytrip.repositories.ItemDataRepository;
import com.openclassrooms.savemytrip.repositories.UserDataRepository;
import com.openclassrooms.savemytrip.todolist.ItemViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Philippe on 27/02/2018.
 */


public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ItemDataRepository itemDataSource;
    private final UserDataRepository userDataSource;
    private final Executor executor;

    public ViewModelFactory(Context context) {
        SaveMyTripDatabase database = SaveMyTripDatabase.getInstance(context);
        this.itemDataSource = new ItemDataRepository(database.itemDao());
        this.userDataSource = new UserDataRepository(database.userDao());
        this.executor = Executors.newSingleThreadExecutor();
    }

    @Override
    @NotNull
    public <T extends ViewModel>  T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ItemViewModel.class)) {
            return (T) new ItemViewModel(itemDataSource, userDataSource, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}