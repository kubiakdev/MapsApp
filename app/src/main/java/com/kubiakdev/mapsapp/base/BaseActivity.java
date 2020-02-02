package com.kubiakdev.mapsapp.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<T extends BaseContract.Presenter>
        extends AppCompatActivity
        implements BaseContract.View {

    protected T presenter = getPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        presenter.subscribe(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract T getPresenter();
}
