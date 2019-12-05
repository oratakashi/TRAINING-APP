package id.oratakashi.training.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import id.oratakashi.training.R;
import id.oratakashi.training.Sessions;
import id.oratakashi.training.data.model.register.ResponseRegister;

public class RegisterActivity extends AppCompatActivity implements RegisterInterface.View {

    Unbinder unbinder;
    RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        unbinder = ButterKnife.bind(this);
        presenter = new RegisterPresenter(this);
    }

    @OnClick(R.id.btnDaftar) void onSubmit(){
        if(getName().isEmpty()||getEmail().isEmpty()||getPassword().isEmpty()){
            Toast.makeText(this, "Data Tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        }else{
            presenter.postRegister(
                    getName(),
                    getEmail(),
                    getPassword()
            );
        }
    }

    @Override
    public void onResultRegister(ResponseRegister response) {
        Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();

        //Set Sessions
        Sessions.getInstance(getApplicationContext()).putString(Sessions.email, getEmail());
        Sessions.getInstance(getApplicationContext()).putString(Sessions.name, getName());

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    @Override
    public void onErrorRegister() {
        Toast.makeText(this, "Gagal melakukan registrasi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadingRegister(boolean loading) {
        if(loading){
            Toast.makeText(this, "Mengirim permintaan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getName() {
        return etName.getText().toString();
    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPassword)
    EditText etPassword;
}
