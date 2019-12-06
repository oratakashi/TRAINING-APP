package id.oratakashi.training.ui.register;

import id.oratakashi.training.data.model.register.ResponseRegister;
import id.oratakashi.training.data.network.Api;
import id.oratakashi.training.data.network.ApiEndpoint;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter implements RegisterInterface.Presenter{

    RegisterInterface.View view;
    ApiEndpoint endpoint;

    public RegisterPresenter(RegisterInterface.View view) {
        this.view = view;
        endpoint = Api.getUrl().create(ApiEndpoint.class);
    }

    @Override
    public void postRegister(String name, String email, String password) {
        view.onLoadingRegister(true);
        endpoint.postRegister(email, name, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseRegister>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseRegister responseRegister) {
                        view.onLoadingRegister(false);
                        view.onResultRegister(responseRegister);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onErrorRegister();
                        view.showMessage(e.getMessage());
                    }
                });
    }
}
