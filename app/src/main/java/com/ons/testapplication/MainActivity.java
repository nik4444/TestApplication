package com.ons.testapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.makeramen.roundedimageview.RoundedImageView;
import com.ons.testapplication.model.Feeds;
import com.ons.testapplication.network.ApiResponse;
import com.ons.testapplication.utils.AppConstant;
import com.ons.testapplication.viewmodel.ResponseDataViewModel;
import com.ons.testapplication.viewmodel.ViewModelFactory;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.HttpException;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    ResponseDataViewModel responseDataViewModel;

    ProgressDialog progressDialog;

    @BindView(R.id.imageView1)
    RoundedImageView imageView;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvNumber)
    TextView tvNumber;

    @BindView(R.id.tvModel)
    TextView tvModel;

    @BindView(R.id.rvSocial)
    RecyclerView rvSocial;

    @BindView(R.id.rvBanner)
    RecyclerView rvBanner;

    private SocialMediaAdapter socialMediaAdapter;
    private BannerAdapter bannerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        socialMediaAdapter = new SocialMediaAdapter(new ArrayList<>());
        bannerAdapter = new BannerAdapter(new ArrayList<>());

        progressDialog = AppConstant.getProgressDialog(this, "Please wait...");
        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getAppComponent().doInjection(this);
        responseDataViewModel = new ViewModelProvider(this, viewModelFactory).get(ResponseDataViewModel.class);

        responseDataViewModel.getApiResponse().observe(this, this::consumeResponse);

        responseDataViewModel.getResponseData(this);

    }

    private void consumeResponse(ApiResponse apiResponse) {
        switch (apiResponse.status) {

            case LOADING:
                progressDialog.show();
                break;

            case SUCCESS:
                progressDialog.dismiss();
                assert apiResponse.data != null;
                renderSuccessResponse(apiResponse.data);
                break;

            case ERROR:
                progressDialog.dismiss();

                if (apiResponse.error instanceof HttpException) {
                    HttpException exception = (HttpException) apiResponse.error;
                    switch (exception.code()) {
                        case 400:
                            Toast.makeText(this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(this, "internal server", Toast.LENGTH_SHORT).show();
                            break;
                        case 408:
                            Toast.makeText(this, "time out", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
                break;

            default:
                break;
        }
    }

    private void renderSuccessResponse(JsonElement response) {
        if (!response.isJsonNull()) {
            Gson gson = new Gson();
            Log.d("response=", response.toString());
            Feeds applicableListData = gson.fromJson(response, Feeds.class);

            Glide.with(this)
                    .load(applicableListData.getFeeds().get(0).getCar().getImage())
                    .into(imageView);

            tvName.setText(applicableListData.getFeeds().get(0).getCar().getMake());
            tvNumber.setText(applicableListData.getFeeds().get(0).getCar().getRegNo());
            tvModel.setText(getString(R.string.model, applicableListData.getFeeds().get(0).getCar().getTransmission(), applicableListData.getFeeds().get(0).getCar().getFuelType()));

            rvSocial.setAdapter(socialMediaAdapter);
            rvBanner.setAdapter(bannerAdapter);

            socialMediaAdapter.setSocialList(applicableListData.getFeeds().get(1).getFeatures());
            bannerAdapter.setBanners(applicableListData.getFeeds().get(2).getOffers());

        } else {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
        }
    }
}