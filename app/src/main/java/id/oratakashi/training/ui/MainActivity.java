package id.oratakashi.training.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import id.oratakashi.training.R;
import id.oratakashi.training.Sessions;
import id.oratakashi.training.data.model.student.ResponseStudent;

public class MainActivity extends AppCompatActivity implements MainInterface.View {

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        tvName.setText(
                "Nama : "+
                Sessions.getInstance(getApplicationContext()).getString(Sessions.name)
        );
        tvEmail.setText(
                "Email : "+
                Sessions.getInstance(getApplicationContext()).getString(Sessions.email)
        );
    }

    @Override
    public void onLoadingStudent(boolean loading) {
        
    }

    @Override
    public void onResultStudent(ResponseStudent response) {

    }

    @Override
    public void onErrorStudent() {
        Toast.makeText(this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnLogout) void onLogout(){
        Sessions.getInstance(getApplicationContext()).logout();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
}
