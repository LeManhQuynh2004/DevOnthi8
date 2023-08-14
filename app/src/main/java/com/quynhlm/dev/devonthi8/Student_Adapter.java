package com.quynhlm.dev.devonthi8;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

public class Student_Adapter extends RecyclerView.Adapter<Student_Adapter.StudentViewHolder>{
    Context context;
    ArrayList<Student> list;
    StudentDao studentDao;

    public Student_Adapter(Context context, ArrayList<Student> list) {
        this.context = context;
        this.list = list;
        studentDao = new StudentDao(context);
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sinhvien,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.id.setText(list.get(position).getId()+"");
        holder.birth.setText(list.get(position).getBirthday()+"");
        holder.mssv.setText(list.get(position).getMSSV()+"");
        holder.name.setText(list.get(position).getName());
        holder.imgDelete.setOnClickListener(v -> {
            DeleteItem(position);
        });
    }

    private void DeleteItem(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thong bao");
        builder.setMessage("Ban co chac chan muon xoa item nay khong ?");
        builder.setPositiveButton("xoa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Student student = list.get(position);
                if(studentDao.deleteData(student)){
                    Toast.makeText(context, "xoa thanh cong", Toast.LENGTH_SHORT).show();
                    list.remove(student);
                    notifyDataSetChanged();
                }else{
                    Toast.makeText(context, "xoa that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("huy",null);
        builder.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder{

        TextView id,name,birth,mssv;
        ImageView imgDelete;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txt_id);
            name = itemView.findViewById(R.id.txt_item_name);
            birth = itemView.findViewById(R.id.txt_item_sinhnhat);
            mssv = itemView.findViewById(R.id.txt_item_MSSV);
            imgDelete = itemView.findViewById(R.id.imgDelete);
        }
    }
}
