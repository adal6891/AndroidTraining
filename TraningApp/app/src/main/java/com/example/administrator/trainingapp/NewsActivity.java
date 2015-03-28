package com.example.administrator.trainingapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.administrator.trainingapp.fragments.ArticleFragment;
import com.example.administrator.trainingapp.fragments.HeadlinesFragment;

/**
 * Created by Administrator on 2015-03-28.
 */
public class NewsActivity extends FragmentActivity implements HeadlinesFragment.OnHeadlineSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_articles);

        if(findViewById(R.id.fragment_container) != null){
            HeadlinesFragment headlinesFragment = new HeadlinesFragment();
            headlinesFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, headlinesFragment).commit();
        }

    }

    @Override
    public void onArticleSelected(int position) {
        ArticleFragment articleFragment = (ArticleFragment) getSupportFragmentManager().findFragmentById(R.id.article_fragment);

        if (articleFragment != null) {
            articleFragment.updateArticleView(position);
        } else {
            ArticleFragment newFragment = new ArticleFragment();
            Bundle args = new Bundle();
            args.putInt(ArticleFragment.ARG_POSITION, position);
            newFragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }
}
