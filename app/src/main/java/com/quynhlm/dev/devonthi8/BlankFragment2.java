package com.quynhlm.dev.devonthi8;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


public class BlankFragment2 extends Fragment {

    View view;

    EditText edt_id, edt_name, edt_birth, edt_mssv;

    StudentDao studentDao;

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
        view = inflater.inflate(R.layout.fragment_blank2, container, false);
        edt_id = view.findViewById(R.id.edt_Update_id);
        edt_name = view.findViewById(R.id.edt_Update_name);
        edt_birth = view.findViewById(R.id.edt_Update_birth);
        edt_mssv = view.findViewById(R.id.edt_Update_mssv);
        studentDao = new StudentDao(getContext());

        view.findViewById(R.id.btnUpdate).setOnClickListener(v -> {
            String id = edt_id.getText().toString();
            String name = edt_name.getText().toString();
            String birth = edt_birth.getText().toString();
            String mssv = edt_mssv.getText().toString();
            if (id.isEmpty() || name.isEmpty() || birth.isEmpty() || mssv.isEmpty()) {
                Toast.makeText(getContext(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();
            } else if (!isChuoi(name)) {
                Toast.makeText(getContext(), "Nhập sai định dạng tên", Toast.LENGTH_SHORT).show();
            } else if (!isNguyen(id) || !isNguyen(birth) || !isNguyen(mssv)) {
                Toast.makeText(getContext(), "Nhập sai định dạng số nguyên ", Toast.LENGTH_SHORT).show();
            } else {
                Student student = new Student();
                student.setName(name);
                student.setId(Integer.parseInt(id));
                student.setBirthday(Integer.parseInt(birth));
                student.setMSSV(Integer.parseInt(mssv));
                if (studentDao.updateData(student)) {
                    Toast.makeText(getContext(), "sua thanh cong", Toast.LENGTH_SHORT).show();
                    ClearInput();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    BlankFragment blankFragment = (BlankFragment) fragmentManager.findFragmentByTag("danhsach");
                    if (blankFragment != null) {
                        blankFragment.update();
                    }
                } else {
                    Toast.makeText(getContext(), "sua that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    private void ClearInput() {
        edt_id.setText("");
        edt_name.setText("");
        edt_birth.setText("");
        edt_mssv.setText("");
    }
}