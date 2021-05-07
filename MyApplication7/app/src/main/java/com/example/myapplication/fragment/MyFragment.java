package com.example.myapplication.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Constants;
import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {
    EditText UnameEditText,studentIDEditText;
    SharedPreferences user_profile;
    SharedPreferences.Editor editor;
    Button saveButton;
    View view;

    public MyFragment() {

    }
    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        return fragment;
    }
    public void saveOnclick(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_my2, container, false);
        // Required empty public constructor
        view=getActivity().getLayoutInflater().inflate(R.layout.fragment_my2,null);
        UnameEditText=view.findViewById(R.id.user_name);
        studentIDEditText=view.findViewById(R.id.student_id);
        UnameEditText.setHint(Constants.USER_NAME);
        studentIDEditText.setHint(Constants.STUDENT_ID);
        user_profile=getActivity().getSharedPreferences("user_profile",
                Context.MODE_PRIVATE);
        editor=user_profile.edit();
        editor.putString("Uname", Constants.USER_NAME);
        editor.putString("Student_id",Constants.STUDENT_ID);
        editor.commit();
        saveButton=view.findViewById(R.id.save_btn);
        saveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String Uname=UnameEditText.getText().toString();
                String student_id=UnameEditText.getText().toString();
                if(Uname==null || student_id==null){
                    Toast.makeText(getContext(),"错误：用户信息不可为空！",Toast.LENGTH_SHORT).show();
                }else{
                    editor.putString("Uname",Uname);
                    editor.putString("Student_id",student_id);
                    editor.commit();
                    Toast.makeText(getContext(),"修改成功",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}