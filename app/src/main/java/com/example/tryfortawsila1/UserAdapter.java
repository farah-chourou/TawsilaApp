package com.example.tryfortawsila1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.MessageFormat;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>  {
    Context context;
    ArrayList<Users> arrayList;
    OnItemClickListener onItemClickListener;
    String currentUserUid;

    public UserAdapter(Context context, ArrayList<Users> arrayList, String currentUserUid) {
        this.context = context;
        this.arrayList = arrayList;
        this.currentUserUid = currentUserUid;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fullname.setText(MessageFormat.format("{0} {1}", arrayList.get(position).getFullname()));
        holder.fullname2.setText(arrayList.get(position).getFullname());
        holder.phone.setText(arrayList.get(position).getPhonenum());
        holder.phone2.setText(arrayList.get(position).getPhonenum());
        holder.email.setText(arrayList.get(position).getEmail());
        holder.itemView.setOnClickListener(view -> onItemClickListener.onClick(arrayList.get(position)));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView fullname, email, phone, fullname2, phone2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fullname = itemView.findViewById(R.id.list_item_FullName);
            fullname2 = itemView.findViewById(R.id.list_item_FullName2);
            email = itemView.findViewById(R.id.list_item_Email);
            phone = itemView.findViewById(R.id.list_item_Phone);
            phone2 = itemView.findViewById(R.id.list_item_Phone2);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(Users user);
    }
}
