package com.example.photogallery1.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.photogallery1.R;
import com.example.photogallery1.model.Item;
import com.example.photogallery1.ui.FullScreenImageActivity;
import com.example.photogallery1.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;



public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder> {

    private List<Item> mItems;
    private Context mContext;
    private PostItemListener mItemListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView titleTv;
        public ImageView image;

        PostItemListener mItemListener;

        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.tv_title);

            image = (ImageView) itemView.findViewById(R.id.imgView);
            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Item item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.getDescription());

            notifyDataSetChanged();

        }
    }

    public AnswersAdapter(Context context, List<Item> posts, PostItemListener itemListener) {
        mItems = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public AnswersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.post_row_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AnswersAdapter.ViewHolder holder, int position) {

        final Item item = mItems.get(position);
        TextView textView = holder.titleTv;
        textView.setText(item.getTitle());
        Picasso.with(mContext).load(item.getMedia().getM()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(mContext, FullScreenImageActivity.class);
                i2.putExtra(Constants.KEY_IMAGE_URL, item.getMedia().getM());
               i2.putExtra(Constants.KEY_IMAGE_TITLE, item.getTitle());
                mContext.startActivity(i2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateAnswers(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    private Item getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(String id);
    }

}
