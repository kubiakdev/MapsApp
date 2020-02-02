package com.kubiakdev.mapsapp.base;

public abstract class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter<T> {

    protected T view;

    @Override
    public void subscribe(T view) {
        this.view = view;
    }

    @Override
    public void unSubscribe() {
        view = null;
    }
}
