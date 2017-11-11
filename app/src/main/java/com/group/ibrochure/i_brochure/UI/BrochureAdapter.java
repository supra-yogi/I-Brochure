package com.group.ibrochure.i_brochure.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group.ibrochure.i_brochure.Domain.ListBrochure.ListBrochure;
import com.group.ibrochure.i_brochure.Domain.UserAccount.UserAccount;
import com.group.ibrochure.i_brochure.Infrastructure.ConverterImage;
import com.group.ibrochure.i_brochure.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Yogi on 07/11/2017.
 */

public class BrochureAdapter extends RecyclerView.Adapter<BrochureAdapter.MyHolder> {
    private Context context;
    private ArrayList<ListBrochure> listBrochureArrayList;

    /*
    CONSTRUCTOR
     */
    public BrochureAdapter(Context context, ArrayList<ListBrochure> listBrochureArrayList) {
        this.context = context;
        this.listBrochureArrayList = listBrochureArrayList;
    }

    //INITIALIZE VIEW HOLDER
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_brochure_model, parent, false);
        return new MyHolder(v);
    }

    //BIND DATA
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final int id = listBrochureArrayList.get(position).getId();
        holder.title.setText(listBrochureArrayList.get(position).getTitle());
        holder.telephone.setText(listBrochureArrayList.get(position).getTelephone());
        holder.user.setText(listBrochureArrayList.get(position).getUserAccount().getName());

        String imageByteFront = listBrochureArrayList.get(position).getPictureFront();
        String imageByteBack = listBrochureArrayList.get(position).getPictureBack();
        String imageAvatar = listBrochureArrayList.get(position).getUserAccount().getPicture();

        if (!imageByteFront.equals("")) {
            Bitmap pictureFront = ConverterImage.decodeBase64(imageByteFront);
            holder.pictureFront.setImageBitmap(pictureFront);
        }

        if (!imageByteBack.equals("")) {
            Bitmap pictureBack = ConverterImage.decodeBase64(imageByteBack);
            holder.pictureBack.setImageBitmap(pictureBack);
        }

        if (!imageAvatar.equals("")) {
            Bitmap avatar = ConverterImage.decodeBase64(imageAvatar);
            holder.avatar.setImageBitmap(avatar);
        }

        holder.brochure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailBrochure = new Intent(context, DetailBrochureActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Id", id);
                detailBrochure.putExtras(bundle);
                context.startActivity(detailBrochure);
            }
        });
    }

    /*
    TOTAL ITEMS
     */
    @Override
    public int getItemCount() {
        return listBrochureArrayList.size();
    }

    /*
    ADD DATA TO ADAPTER
     */
    public void add(ListBrochure listBrochure) {
        listBrochureArrayList.add(listBrochure);
        notifyDataSetChanged();
    }

    /*
    CLEAR DATA FROM ADAPTER
     */
    public void clear() {
        listBrochureArrayList.clear();
        notifyDataSetChanged();
    }

    /*
    VIEW HOLDER CLASS
     */
    class MyHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView telephone;
        ImageView pictureFront;
        ImageView pictureBack;
        TextView user;
        CardView brochure;
        ImageView avatar;

        public MyHolder(View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.title);
            this.user = (TextView) itemView.findViewById(R.id.user);
            this.telephone = (TextView) itemView.findViewById(R.id.telephone);
            this.pictureFront = (ImageView) itemView.findViewById(R.id.pictureFront);
            this.pictureBack = (ImageView) itemView.findViewById(R.id.pictureBack);
            this.brochure = (CardView) itemView.findViewById(R.id.cardViewModelBrochure);
            this.avatar = (ImageView) itemView.findViewById(R.id.avatar2);
        }
    }
}
