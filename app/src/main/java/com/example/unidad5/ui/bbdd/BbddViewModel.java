package com.example.unidad5.ui.bbdd;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BbddViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BbddViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is bbdd fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}