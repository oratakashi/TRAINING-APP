package id.oratakashi.training.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.widget.EditText;
import android.widget.Toast;

import id.oratakashi.training.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import id.oratakashi.training.Sessions;
import id.oratakashi.training.data.model.login.DataStudent;
import id.oratakashi.training.data.model.login.ResponseLogin;

public class LoginActivity extends AppCompatActivity implements LoginInterface.View {
    
    Unbinder unbinder;
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(!Sessions.getInstance(getApplicationContext()).getString(Sessions.email).equals("")){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        unbinder = ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
    }

    @Override
    public void onLoadingLogin(boolean loading) {
        if(loading){
            Toast.makeText(this, "Mengirim Permintaan login....", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResultLogin(ResponseLogin response) {
        if(response.getStatus()){
            DataStudent data = response.getData();
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();

            /**
             * Set Session dari Login
             */
            Sessions.getInstance(getApplicationContext()).putString(Sessions.name, data.getName());
            Sessions.getInstance(getApplicationContext()).putString(Sessions.email, data.getEmail());

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }else{
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onErrorLogin() {
        Toast.makeText(this, "Gagal melakukan login!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @OnClick(R.id.tvRegister) void onRegister(){
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }

    @OnClick(R.id.btnLogin) void onLogin(){
        if(getEmail().isEmpty()||getPassword().isEmpty()){
            Toast.makeText(this, "Harap isi Email / Password!", Toast.LENGTH_SHORT).show();
        }else{
            presenter.postLogin(
                    getEmail(),
                    getPassword()
            );
        }
    }

    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
}
