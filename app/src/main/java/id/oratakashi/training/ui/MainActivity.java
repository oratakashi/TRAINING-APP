package id.oratakashi.training.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
    MainPresenter presenter;

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

        presenter = new MainPresenter(this);

        presenter.getStudent();

        srMain.setDistanceToTriggerSync(220);
        srMain.setOnRefreshListener(() -> presenter.getStudent());
    }

    @Override
    public void onLoadingStudent(boolean loading) {
        if(loading){
            srMain.setRefreshing(true);
        }else{
            srMain.setRefreshing(false);
        }
    }

    @Override
    public void onResultStudent(ResponseStudent response) {
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.setAdapter(new MainAdapter(this, response.getData()));
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
    @BindView(R.id.rvMain)
    RecyclerView rvMain;
    @BindView(R.id.srMain)
    SwipeRefreshLayout srMain;
}
