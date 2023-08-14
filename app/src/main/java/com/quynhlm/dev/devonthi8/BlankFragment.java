package com.quynhlm.dev.devonthi8;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class BlankFragment extends Fragment {

    RecyclerView recyclerView;
    View view;

    EditText edt_name, edt_birth, edt_mssv;

    StudentDao studentDao;

    ArrayList<Student> list = new ArrayList<>();
    Student_Adapter student_adapter;

    public boolean isChuoi(String str) {
        return str.matches("[a-z A-Z 0-9]+");
    }

    public boolean isNguyen(String str) {
        return str.matches("\\d+");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_blank, container, false);
        recyclerView = view.findViewById(R.id.RecyclerView);
        studentDao = new StudentDao(getContext());
        list = studentDao.selectAll();
        student_adapter = new Student_Adapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(student_adapter);
        view.findViewById(R.id.fab_add).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            View view1 = LayoutInflater.from(getContext()).inflate(R.layout.add_sinhvien, null);
            builder.setView(view1);
            AlertDialog alertDialog = builder.create();
            edt_name = view1.findViewById(R.id.edt_name);
            edt_birth = view1.findViewById(R.id.edt_sinhnhat);
            edt_mssv = view1.findViewById(R.id.edt_mssv);
            view1.findViewById(R.id.btn_add).setOnClickListener(v1 -> {
                String name = edt_name.getText().toString();
                String birth = edt_birth.getText().toString();
                String mssv = edt_mssv.getText().toString();
                if (name.isEmpty() || birth.isEmpty() || mssv.isEmpty()) {
                    Toast.makeText(getContext(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                } else if (!isChuoi(name)) {
                    Toast.makeText(getContext(), "Nhập sai định dạng tên", Toast.LENGTH_SHORT).show();
                } else if (!isNguyen(birth) || !isNguyen(mssv)) {
                    Toast.makeText(getContext(), "Nhập sai định dạng số nguyên ", Toast.LENGTH_SHORT).show();
                } else {
                    Student student = new Student();
                    student.setName(name);
                    student.setBirthday(Integer.parseInt(birth));
                    student.setMSSV(Integer.parseInt(mssv));

                    if (studentDao.insertData(student)) {
                        Toast.makeText(getContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                        list.add(student);
                        student_adapter.notifyDataSetChanged();
                        alertDialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Them that bai", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            alertDialog.show();
        });
        return view;
    }

    public void update() {
        list.clear();
        list.addAll(studentDao.selectAll());
        student_adapter.notifyDataSetChanged();
    }
}