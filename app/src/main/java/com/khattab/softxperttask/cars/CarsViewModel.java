package com.khattab.softxperttask.cars;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.khattab.softxperttask.cars.pojo.Cars;
import com.khattab.softxperttask.common.aPIsUtils.SingleLiveEvent;
import com.khattab.softxperttask.common.aPIsUtils.TaskClientWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarsViewModel extends ViewModel {

    private TaskClientWebService mWebService;
    private MutableLiveData<Cars> iList;
    private MutableLiveData<String> mHandleError;
    SingleLiveEvent<Boolean> isLoading;

    public CarsViewModel() {
        isLoading = new SingleLiveEvent<>();
        isLoading.setValue(false);
        iList = new MutableLiveData<>();
        mHandleError = new MutableLiveData<>();
    }

    public void setWebService(TaskClientWebService mWebService) {
        this.mWebService = mWebService;
    }

    public SingleLiveEvent<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<String> getmHandleError() {
        return mHandleError;
    }

    public MutableLiveData<Cars> getmList() {
        return iList;
    }

    public void loadCars(String page) {
        isLoading.setValue(true);
        mWebService.getCars(page).enqueue(new Callback<Cars>() {
            @Override
            public void onResponse(Call<Cars> call, Response<Cars> response) {
                isLoading.setValue(false);
                if (response.isSuccessful()) {
                    iList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Cars> call, Throwable t) {
                isLoading.setValue(false);
                mHandleError.setValue("");
                Log.e("ListFil", "" + t.getMessage());
                t.printStackTrace();
            }
        });
    }

}