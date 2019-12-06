package id.oratakashi.training.ui.register;

import id.oratakashi.training.data.model.register.ResponseRegister;

public interface RegisterInterface {
    interface View{
        void onResultRegister(ResponseRegister response);
        void onErrorRegister();
        void onLoadingRegister(boolean loading);

        void showMessage(String msg);

        String getName();
        String getEmail();
        String getPassword();
    }
    interface Presenter{
        void postRegister(String name, String email, String password);
    }
}
