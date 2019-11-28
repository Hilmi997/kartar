package phonebase.hilmi.kartar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import phonebase.hilmi.kartar.R;
import phonebase.hilmi.kartar.model.Anggota;

/**
 * Created by User on 21/11/2019.
 */

public class AnggotaAdapter extends RecyclerView.Adapter<AnggotaAdapter.MyViewHolder>{
    private List<Anggota.Item> mList;
    private Context mContext;
    private onClickListener listener;

    public interface onClickListener{
        void onItemClicked(Anggota.Item item, int position);
        void onItemLongClicked(Anggota.Item item, int position);
    }

    public AnggotaAdapter(Context mContext, List<Anggota.Item> mList, onClickListener listener) {
        this.mList = mList;
        this.mContext = mContext;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mDocNo, mDate, mOkNo, mArticle, mconfirmDate;

        public MyViewHolder(View view) {
            super(view);
//            mDocNo = (TextView) view.findViewById(R.id.date);
//            mDate = (TextView) view.findViewById(R.id.date);

        }

        public void bind(final Anggota.Item item, final int position) {
            mDocNo.setText(item.Fullname);
            mDate.setText(item.Alamat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(item, position);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClicked(item, position);
                    return false;
                }
            });
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_identitas, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

