package com.ons.testapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ons.testapplication.model.Features;

import java.util.List;


public class SocialMediaAdapter extends RecyclerView.Adapter<SocialMediaAdapter.SocialMediaViewHolder> {

    public Context context;
    private List<Features> featuresList;

    public SocialMediaAdapter(Context context, List<Features> data) {
        this.featuresList = data;
        this.context = context;
    }

    @NonNull
    @Override
    public SocialMediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SocialMediaViewHolder(featuresList, context, LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_social, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SocialMediaViewHolder holder, int position) {
        Features features = featuresList.get(position);
        if (features.getScreenName() != null) {
            holder.tvSocialName.setText(features.getScreenName());
        }
        if (features.getImageUrl() != null) {
            Glide.with(holder.itemView)
                    .load(features.getImageUrl())
                    .into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        if (this.featuresList != null) {
            return this.featuresList.size();
        }
        return 0;
    }

    public void setSocialList(List<Features> featuresList) {
        this.featuresList = featuresList;
        notifyDataSetChanged();
    }

    static class SocialMediaViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvSocialName;
        private final ImageView image;

        public SocialMediaViewHolder(List<Features> featuresList, Context context, @NonNull View itemView) {
            super(itemView);
            tvSocialName = itemView.findViewById(R.id.tvSocialName);
            image = itemView.findViewById(R.id.imageView1);
            image.setOnClickListener(view -> {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(featuresList.get(getAdapterPosition()).getRedirectUrl()));
                context.startActivity(i);
            });
        }
    }
}
