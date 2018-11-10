package com.sanketshah.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.sanketshah.R;
import com.sanketshah.data.FeedData;
import com.squareup.picasso.Picasso;


public class NewsFeedDataViewModel extends ViewModel {
    ObservableField<FeedData> dataObservableField;

    public NewsFeedDataViewModel(FeedData feedData) {
        dataObservableField = new ObservableField<>();
        setDataObservableField(feedData);
    }

    public String getFeedTitle() {
        return getDataObservableField().get().getTitle();
    }


    public ObservableField<FeedData> getDataObservableField() {
        return dataObservableField;
    }

    private void setDataObservableField(FeedData dataObservableField) {
        this.dataObservableField.set(dataObservableField);
    }


    public String getFeedDescription() {
        return getDataObservableField().get().getDescription();
    }


    public String getFeedImage() {
        return getDataObservableField().get().getImageHref();
    }

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if (imageUrl != null)
            if (!imageUrl.trim().isEmpty())
                Picasso.get().load(imageUrl).placeholder(R.drawable.no_image).error(R.drawable.no_image).into(view);
            else
                view.setImageResource(R.drawable.no_image);
        else
            view.setImageResource(R.drawable.no_image);

    }
}
