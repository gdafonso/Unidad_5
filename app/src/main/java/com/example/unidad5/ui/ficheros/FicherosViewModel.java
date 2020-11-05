package com.example.unidad5.ui.ficheros;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FicherosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FicherosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ficheros fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}