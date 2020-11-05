package com.example.unidad5.ui.ficheros;

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
import com.example.unidad5.ui.ficheros.FicherosViewModel;

public class FicherosFragment extends Fragment {

    private FicherosViewModel ficherosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ficherosViewModel =
                new ViewModelProvider(this).get(FicherosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ficheros, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        ficherosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}