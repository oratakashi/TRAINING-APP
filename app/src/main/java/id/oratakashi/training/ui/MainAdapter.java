package id.oratakashi.training.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.oratakashi.training.ImageHelper;
import id.oratakashi.training.R;
import id.oratakashi.training.data.model.student.DataStudent;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    Context context;
    List<DataStudent> data;

    public MainAdapter(Context context, List<DataStudent> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_student,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(data.get(position).getName());
        holder.tvNIM.setText(data.get(position).getNim());
        ImageHelper.getImage(holder.ivPhoto, data.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cvStudent)
        CardView cvStudent;
        @BindView(R.id.ivPhoto)
        ImageView ivPhoto;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvNIM)
        TextView tvNIM;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
