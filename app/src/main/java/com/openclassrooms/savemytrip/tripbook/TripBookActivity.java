package com.openclassrooms.savemytrip.tripbook;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.openclassrooms.savemytrip.R;
import com.openclassrooms.savemytrip.databinding.ActivityTripBookBinding;

public class TripBookActivity extends AppCompatActivity {
    private ActivityTripBookBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTripBookBinding.inflate(getLayoutInflater());
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

        CompoundButton.OnCheckedChangeListener checkedChangeListener = (button, isChecked) -> {
            if (isChecked) {
                int id = button.getId();
                if (id == R.id.trip_book_activity_radio_internal) {
                    binding.tripBookActivityExternalChoice.setVisibility(View.GONE);
                    binding.tripBookActivityInternalChoice.setVisibility(View.VISIBLE);
                } else if (id == R.id.trip_book_activity_radio_external) {
                    binding.tripBookActivityExternalChoice.setVisibility(View.VISIBLE);
                    binding.tripBookActivityInternalChoice.setVisibility(View.GONE);
                }
            }
        };
        binding.tripBookActivityRadioInternal.setOnCheckedChangeListener(checkedChangeListener);
        binding.tripBookActivityRadioExternal.setOnCheckedChangeListener(checkedChangeListener);
        binding.tripBookActivityRadioPrivate.setOnCheckedChangeListener(checkedChangeListener);
        binding.tripBookActivityRadioPublic.setOnCheckedChangeListener(checkedChangeListener);
        binding.tripBookActivityRadioNormal.setOnCheckedChangeListener(checkedChangeListener);
        binding.tripBookActivityRadioVolatile.setOnCheckedChangeListener(checkedChangeListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_share) {
            /*TODO*/
            return true;
        } else if (itemId == R.id.action_save) {
            /*TODO*/
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
