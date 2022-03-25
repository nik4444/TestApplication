package com.ons.testapplication.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ons.testapplication.network.ApiResponse;
import com.ons.testapplication.repository.Repository;
import com.ons.testapplication.utils.AppConstant;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ResponseDataViewModel extends ViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();
    private final Repository repository;

    public ResponseDataViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ApiResponse> getApiResponse() {
        return responseLiveData;
    }

    public void getResponseData(Context context) {
        if (AppConstant.checkInternetConnection(context))
            disposables.add(repository.getApiData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
                    .subscribe(
                            result -> responseLiveData.setValue(ApiResponse.success(result)),
                            throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
                    ));
        else Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
