package gindi.com.funquizs;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProblemsAdapter extends RecyclerView.Adapter<ProblemsAdapter.ProblemsAdapterViewHolder> {

    private List<Problems> problems;

    public ProblemsAdapter(List<Problems> problems) {
        this.problems = problems;
    }

    @NonNull
    @Override
    public ProblemsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.problems_list_item,parent,false);
        return new ProblemsAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProblemsAdapterViewHolder holder, int position) {

        Problems p = problems.get(position);
        //holder.name.setText(p.getName());
        holder.title.setText(p.getTitle());
        //holder.content.setText(p.getContent());
        //holder.answerType.setText(p.getAnswerType());

    }

    @Override
    public int getItemCount() {
        return problems.size();
    }

    public class ProblemsAdapterViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView title;
        private TextView content;
        private TextView answerType;

        public ProblemsAdapterViewHolder(View itemView) {
            super(itemView);

            //name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
            //content = itemView.findViewById(R.id.content);
            //answerType = itemView.findViewById(R.id.answertype);
        }
    }

}
