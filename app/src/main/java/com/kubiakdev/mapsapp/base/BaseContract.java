package com.kubiakdev.mapsapp.base;

public interface BaseContract {

    public interface View {}

    public interface Presenter<T extends View> {

        void subscribe(T view);

        void unSubscribe();
    }
}
