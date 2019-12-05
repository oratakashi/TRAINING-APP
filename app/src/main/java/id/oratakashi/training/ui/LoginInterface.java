package id.oratakashi.training.ui;

import id.oratakashi.training.data.model.login.ResponseLogin;

public interface LoginInterface {
    interface View{
        /**
         * Method OnLoading, OnResult, dan OnError
         * @param loading
         *
         * Digunakan untuk menunjukan proses dari Thread
         * yang berhubungan dengan Rest API ( Retrofit ),
         * OnResult untuk hasil dari restAPI, dan onError
         * untuk method jika ada kesalahan jaringan seperti disconect
         * atau timeout
         */
        void onLoadingLogin(boolean loading);
        void onResultLogin(ResponseLogin response);
        void onErrorLogin();

        //Method untuk menampilkan pesan error
        void showMessage(String msg);
        //Method Get Atribut dari View
        String getEmail();
        String getPassword();
    }
    interface Presenter{
        void postLogin(String email, String password);
    }
}
