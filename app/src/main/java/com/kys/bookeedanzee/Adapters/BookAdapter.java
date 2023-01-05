package com.kys.bookeedanzee.Adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kys.bookeedanzee.Models.BookModel;
import com.kys.bookeedanzee.R;
import com.kys.bookeedanzee.ReadBookActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.viewHolder>{
    ArrayList<BookModel> list;
    Context context;

    public BookAdapter(ArrayList<BookModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_book,parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        BookModel model = list.get(position);
        Picasso.get()
                .load(model.getImage())
                .into(holder.bookImage);

        //holder.bookImage.setImageResource(model.getImage());
        holder.bookText.setText(model.getBookName());

        holder.itemView.setOnClickListener(view -> {
//                Intent intent = new Intent(context, ReadBookActivity.class);
//                intent.putExtra("url",model.getUrl());
//                context.startActivity(intent);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(model.getUrl()), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Intent newIntent = Intent.createChooser(intent, "Open File");
            try {
                context.startActivity(newIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, "Error in opening file Download a pdf Viewer in ur device", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView bookImage;
        TextView bookText;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            bookImage = itemView.findViewById(R.id.imageView);
            bookText = itemView.findViewById(R.id.textView);

        }
    }
}
