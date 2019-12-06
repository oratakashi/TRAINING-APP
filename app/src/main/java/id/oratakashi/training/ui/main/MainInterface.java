package id.oratakashi.training.ui.main;

import id.oratakashi.training.data.model.student.ResponseStudent;
import id.oratakashi.training.data.model.student.delete.ResponseDelete;
import okhttp3.Response;

public interface MainInterface {
    interface View{
        void onLoadingStudent(boolean loading);
        void onResultStudent(ResponseStudent response);
        void onErrorStudent();

        void onDelete(String nim);
        void onLoadingDelete(boolean loading);
        void onResultDelete(ResponseDelete response);
        void onErrorDelete();

        void showMessage(String msg);
    }
    interface Presenter{
        void getStudent();
        void deleteStudent(String nim);
    }
}
