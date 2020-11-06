package com.example.unidad5.ui.preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.unidad5.R;

public class SettingsContainerActivity extends AppCompatActivity implements SettingsFragment.OnFragmentInteractionListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_container);

    }
}
