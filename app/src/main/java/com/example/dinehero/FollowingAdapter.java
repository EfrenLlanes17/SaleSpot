package com.example.dinehero;





import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.ViewHolder>{

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<String> strings = new ArrayList<>();

    public static final String EXTRA_TEXT7 = "com.example.dinehero.example.EXTRA_TEXT7";

    private Context context;


    public FollowingAdapter(Context context) {
     this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.following_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.txtSeller.setText(strings.get(position).toString());
       holder.parent.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               for (int x = FollowingActivity.getFollowingProductList().size()-1 ; x >-1; x--) {
                   if(FollowingActivity.getFollowingProductList().get(x).getProductSeller().equals(strings.get(position).toString())){

                       FollowingActivity.getFollowingProductList().remove(x);
                   }
               }
               String justRemoved = strings.get(position).toString();
               ProfileActivity.removeFromFollowing(strings.get(position).toString());

           ProfileActivity.showSnackbar(justRemoved);
           notifyDataSetChanged();
           }
       });



    }


    @Override
    public int getItemCount() {
        if(strings == null){
            return 1;
        }

        return strings.size();
    }

    public void setProducts(ArrayList<String> strings) {
        this.strings = strings;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtSeller;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSeller = itemView.findViewById(R.id.txtSeller);
            parent = itemView.findViewById(R.id.parent);


        }
    }
}
