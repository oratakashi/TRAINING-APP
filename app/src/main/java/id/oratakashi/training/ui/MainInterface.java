package id.oratakashi.training.ui;

import id.oratakashi.training.data.model.student.ResponseStudent;

public interface MainInterface {
    interface View{
        void onLoadingStudent(boolean loading);
        void onResultStudent(ResponseStudent response);
        void onErrorStudent();

        void showMessage(String msg);
    }
    interface Presenter{
        void getStudent();
    }
}
