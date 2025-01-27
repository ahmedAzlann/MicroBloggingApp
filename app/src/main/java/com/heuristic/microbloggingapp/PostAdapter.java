package com.heuristic.microbloggingapp;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heuristic.microbloggingapp.databinding.PostCardItemBinding;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Posts> posts = new ArrayList<>();
    private UtilityButtonClickListener utilityButtonClickListener;
    String userId;

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PostCardItemBinding view = PostCardItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.bind(posts.get(position)); // viewholder = 48357485, Post()
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setOnUtilityButtonClickListener(UtilityButtonClickListener utilityButtonClickListener, String userId) {
        this.utilityButtonClickListener = utilityButtonClickListener;
        this.userId = userId;
    }

    public void setData(List<Posts> posts) {
        if (this.posts != null) {
            this.posts.clear();
        } else {
            this.posts = new ArrayList<>();
        }
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{

        private PostCardItemBinding binding;
        public PostViewHolder(@NonNull PostCardItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        private String getDate(long time) {
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(time);
            String date = DateFormat.format("dd LLL yyyy", cal).toString();
            return date;
        }

        public void bind(Posts posts) {
            binding.name.setText(StringUtils.capitalize(posts.getUser_name()));
            binding.content.setText(posts.getPost_description());
            long dv = Long.parseLong(posts.getTimestamp());// its need to be in milisecond
            binding.date.setText("Posted on " + getDate(dv));
            if (posts.getLikes() != null) {
                if (posts.getLikes().containsKey(userId)) {
                    binding.heartIv.setImageResource(R.drawable.ic_heart_filled);
                } else {
                    binding.heartIv.setImageResource(R.drawable.ic_heart_normal);
                }
                if (posts.getLikes().size() <= 1) {
                    binding.likes.setText(posts.getLikes().size() + " like");
                } else {
                    binding.likes.setText(posts.getLikes().size() + " likes");
                }
            } else {
                binding.likes.setText("0 likes");
                binding.heartIv.setImageResource(R.drawable.ic_heart_normal);
            }

            binding.heartIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (utilityButtonClickListener != null) {
                        utilityButtonClickListener.onLikeClicked(posts.getPost_id());
                    }
                }
            });
        }
    }
}

interface UtilityButtonClickListener {
    void onLikeClicked(String postId);
}
