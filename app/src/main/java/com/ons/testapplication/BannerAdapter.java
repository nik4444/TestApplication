package com.ons.testapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ons.testapplication.model.Offers;

import java.util.List;



public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {

    private List<Offers> offersList;

    public BannerAdapter(List<Offers> data) {
        this.offersList = data;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_banner, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        Offers offers = offersList.get(position);

        if (offers.getImage() != null) {
            Glide.with(holder.itemView)
                    .load(offers.getImage())
                    .into(holder.ivBanner);
        }
    }

    @Override
    public int getItemCount() {
        if (this.offersList != null) {
            return this.offersList.size();
        }
        return 0;
    }

    public void setBanners(List<Offers> offersList) {
        this.offersList = offersList;
        notifyDataSetChanged();
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {


        private final ImageView ivBanner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBanner = itemView.findViewById(R.id.ivBanner);
        }
    }
}
