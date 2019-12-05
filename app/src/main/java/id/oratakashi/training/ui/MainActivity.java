package id.oratakashi.training.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import id.oratakashi.training.R;
import id.oratakashi.training.Sessions;

public class MainActivity extends AppCompatActivity {

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
