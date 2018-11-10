package com.sanketshah.ui.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sanketshah.R;
import com.sanketshah.data.FeedData;
import com.sanketshah.databinding.RowFeedBinding;
import com.sanketshah.viewmodel.NewsFeedDataViewModel;

import java.util.List;


public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private List<FeedData> feedData;

    public FeedAdapter(List<FeedData> feedData) {
        this.feedData = feedData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowFeedBinding rowFeedBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_feed, parent, false);
        return new ViewHolder(rowFeedBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(feedData.get(position));
    }

    @Override
    public int getItemCount() {
        return feedData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RowFeedBinding rowFeedBinding;

        public ViewHolder(RowFeedBinding rowFeedBinding) {
            super(rowFeedBinding.getRoot());
            this.rowFeedBinding = rowFeedBinding;
            setIsRecyclable(false);
        }

        public void bindData(FeedData feedData) {
            if (rowFeedBinding.getFeedData() == null) {
                rowFeedBinding.setFeedData(new NewsFeedDataViewModel(feedData));
            } else {
                rowFeedBinding.setFeedData(rowFeedBinding.getFeedData());
            }
        }
    }
}
