package com.openclassrooms.savemytrip.todolist;

import android.graphics.Paint;

import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.savemytrip.R;
import com.openclassrooms.savemytrip.databinding.ActivityTodoListItemBinding;
import com.openclassrooms.savemytrip.models.Item;

/**
 * Created by Philippe on 28/02/2018.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private final ActivityTodoListItemBinding binding;

    public ItemViewHolder(ActivityTodoListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void updateWithItem(Item item, ItemAdapter.Listener callback) {
        binding.getRoot().setOnClickListener(view -> callback.onItemClick(item));
        binding.activityTodoListItemRemove.setOnClickListener(view -> callback.onClickDeleteButton(item));
        binding.activityTodoListItemText.setText(item.getText());
        switch (item.getCategory()) {
            case 0: // TO VISIT
                binding.activityTodoListItemImage.setBackgroundResource(R.drawable.ic_room_black_24px);
                break;
            case 1: // IDEAS
                binding.activityTodoListItemImage.setBackgroundResource(R.drawable.ic_lightbulb_outline_black_24px);
                break;
            case 2: // RESTAURANTS
                binding.activityTodoListItemImage.setBackgroundResource(R.drawable.ic_local_cafe_black_24px);
                break;
        }
        if (item.getSelected()) {
            binding.activityTodoListItemText.setPaintFlags(binding.activityTodoListItemText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            binding.activityTodoListItemText.setPaintFlags(binding.activityTodoListItemText.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }
}