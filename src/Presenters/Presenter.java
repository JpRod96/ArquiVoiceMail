package Presenters;

import main.Connection;

/**
 * Created by CORE i7 on 27/05/2018.
 */
public interface Presenter<T> {
    void showView(T model);
    T getModelFromView();
}
