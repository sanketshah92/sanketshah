package com.sanketshah.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sanketshah.MainActivity;
import com.sanketshah.R;
import com.sanketshah.data.NewsFeedWrapper;
import com.sanketshah.databinding.FragmentNewsfeedBinding;
import com.sanketshah.ui.adapters.FeedAdapter;
import com.sanketshah.utils.AppLoader;
import com.sanketshah.viewmodel.NewsFeedViewModel;

public class NewsFeedFragment extends Fragment {


    private FragmentNewsfeedBinding fragmentNewsfeedBinding;
    private FeedAdapter feedAdapter;
    private NewsFeedViewModel newsFeedViewModel;
    private AppLoader appLoader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentNewsfeedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_newsfeed, container, false);

        return fragmentNewsfeedBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentNewsfeedBinding.rvFeeds.setLayoutManager(new LinearLayoutManager(getActivity()));

        appLoader = new AppLoader(getActivity(), false, null);
        appLoader.show();
        newsFeedViewModel = new NewsFeedViewModel(getActivity(), new NewsFeedViewModel.OnResponse() {
            @Override
            public void onSuccess(NewsFeedWrapper newsFeedWrapper) {
                if (getActivity() != null) {
                    if (fragmentNewsfeedBinding.swipe.isRefreshing())
                        fragmentNewsfeedBinding.swipe.setRefreshing(false);
                    appLoader.dismiss();
                    ((MainActivity) getActivity()).setTitle("" + newsFeedWrapper.getTitle());
                    fragmentNewsfeedBinding.setViewModel(newsFeedViewModel);
                    feedAdapter = new FeedAdapter(newsFeedViewModel.getNewsFeedWrapper().getRows());
                    fragmentNewsfeedBinding.rvFeeds.setAdapter(feedAdapter);
                }
            }

            @Override
            public void onFailure(String msg) {
                if (getActivity() != null) {
                    if (fragmentNewsfeedBinding.swipe.isRefreshing())
                        fragmentNewsfeedBinding.swipe.setRefreshing(false);
                    appLoader.dismiss();
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                }
            }
        });

        newsFeedViewModel.callFeedApi();
        fragmentNewsfeedBinding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!appLoader.isShowing())
                    appLoader.show();
                newsFeedViewModel.callFeedApi();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (newsFeedViewModel != null)
            newsFeedViewModel.dispose();
    }
}
