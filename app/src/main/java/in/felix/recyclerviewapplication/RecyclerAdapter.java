package in.felix.recyclerviewapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends  RecyclerView.Adapter<RecyclerAdapter.ViewHolder>  {

    private List<Product> mData;

    private LayoutInflater mInflater;

    Context mContext;

    private ItemClickListener mClickListener;


    public RecyclerAdapter(Context context,List<Product> products) {
        mInflater=LayoutInflater.from(context);
        this.mData=products;
        this.mContext=context;
    }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

       View view=mInflater.inflate(R.layout.recycler_view_row_item,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.txtProductName.setText(mData.get(position).getProductName());
        holder.txtPrice.setText(String.valueOf(mData.get(position).getPrice()));
        holder.txtQty.setText(String.valueOf(mData.get(position).getQuantity()));
        holder.txtUnit.setText(mData.get(position).getUnit());

        Picasso.with(mContext).load(mData.get(position).getImageUrl()).fit().into(holder.imgProductImage);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtProductName,txtPrice,txtQty,txtUnit;

        ImageView imgProductImage;

        ViewHolder(View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.tvName);
            txtPrice = itemView.findViewById(R.id.tvPrice);
            txtQty = itemView.findViewById(R.id.tvQty);
            txtUnit = itemView.findViewById(R.id.tvUnit);
            imgProductImage=itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
}
