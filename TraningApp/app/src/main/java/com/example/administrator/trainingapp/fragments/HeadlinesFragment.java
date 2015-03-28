package com.example.administrator.trainingapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.trainingapp.Ipsum;
import com.example.administrator.trainingapp.R;

/**
 * Created by Administrator on 2015-03-28.
 */
public class HeadlinesFragment extends ListFragment {
    private OnHeadlineSelectedListener mCallBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layout = android.R.layout.simple_list_item_activated_1;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), layout, Ipsum.Headlines);
        setListAdapter(arrayAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getFragmentManager().findFragmentById(R.id.article_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallBack = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "nonono");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mCallBack.onArticleSelected(position);
        getListView().setItemChecked(position, true);
    }

    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
    }
}
