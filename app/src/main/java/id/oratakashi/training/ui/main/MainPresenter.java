package id.oratakashi.training.ui.main;

import id.oratakashi.training.data.model.student.ResponseStudent;
import id.oratakashi.training.data.model.student.delete.ResponseDelete;
import id.oratakashi.training.data.network.Api;
import id.oratakashi.training.data.network.ApiEndpoint;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainInterface.Presenter {

    MainInterface.View view;
    ApiEndpoint endpoint;

    public MainPresenter(MainInterface.View view) {
        this.view = view;
        endpoint = Api.getUrl().create(ApiEndpoint.class);
    }

    @Override
    public void getStudent() {
        view.onLoadingStudent(true);
        endpoint.getStudents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseStudent>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseStudent responseStudent) {
                        view.onLoadingStudent(false);
                        view.onResultStudent(responseStudent);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onErrorStudent();
                        view.showMessage(e.getMessage());
                    }
                });
    }

    @Override
    public void deleteStudent(String nim) {
        view.onLoadingDelete(true);
        endpoint.deleteStudent(nim)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseDelete>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseDelete responseDelete) {
                        view.onLoadingDelete(false);
                        view.onResultDelete(responseDelete);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onErrorDelete();
                        view.onLoadingDelete(false);
                        view.showMessage(e.getMessage());
                    }
                });
    }
}
