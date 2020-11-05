package com.example.unidad5.ui.bbdd;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.unidad5.R;
import com.example.unidad5.ui.bbdd.BbddViewModel;

public class BbddFragment extends Fragment {

    private BbddViewModel bbddViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bbddViewModel =
                new ViewModelProvider(this).get(BbddViewModel.class);
        View root = inflater.inflate(R.layout.fragment_bbdd, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        bbddViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}