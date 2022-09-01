package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

public class ToDoFragment extends Fragment {

    private ToDo mToDo;
    private EditText mTitleField;
    private EditText mDetailsField;
    private EditText mCatField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private Button mShareTaskButton;
    private Button mCollabButton;

    private static final String ARG_TODO_ID ="todo_id";
    private static final String DIALOG_DATE ="DialogDate";
    private static final int REQUEST_DATA=0;
    private static final int REQUEST_CONTACT =1;




    public static ToDoFragment newInstance(UUID todoId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TODO_ID, todoId);

        ToDoFragment fragment = new ToDoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID todoId =(UUID)getArguments().getSerializable(ARG_TODO_ID);
        mToDo = ToDoLab.get(getActivity()).getToDo(todoId);

    }

    @Override
    public void onPause(){
        super.onPause();

        ToDoLab.get(getActivity()).updateToDo(mToDo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_todo , container , false);

        mTitleField = (EditText) v.findViewById(R.id.todo_title);
        mTitleField.setText(mToDo.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mToDo.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mDetailsField = (EditText) v.findViewById(R.id.todo_details);
        mDetailsField.setText(mToDo.getDetail());
        mDetailsField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mToDo.setDetail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCatField = (EditText) v.findViewById(R.id.todo_cat);
        mCatField.setText(mToDo.getCat());
        mCatField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mToDo.setCat(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        mDateButton=(Button) v.findViewById(R.id.todo_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mToDo.getDate());
                dialog.setTargetFragment(ToDoFragment.this, REQUEST_DATA);
                dialog.show(manager, DIALOG_DATE);

            }
        });

        mSolvedCheckBox=(CheckBox)v.findViewById(R.id.todo_solved);
        mSolvedCheckBox.setChecked(mToDo.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mToDo.setSolved(isChecked);
            }
        });

        mShareTaskButton =(Button)v.findViewById(R.id.todo_add);
        mShareTaskButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, getTodoReport());
                i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.todo_add_subject));
                startActivity(i);
            }
        });

        final Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        mCollabButton = (Button) v.findViewById(R.id.todo_collab);
        mCollabButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivityForResult(pickContact,REQUEST_CONTACT);
            }
        });

        if(mToDo.getCollab()!=null){
            mCollabButton.setText(mToDo.getCollab());
        }

        PackageManager packageManager = getActivity().getPackageManager();
        if (packageManager.resolveActivity(pickContact, PackageManager.MATCH_DEFAULT_ONLY) == null) {

            mCollabButton.setEnabled(false);
        }


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK){
            return;
        }

        if (requestCode == REQUEST_DATA){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mToDo.setDate(date);
            updateDate();
        }else if(requestCode == REQUEST_CONTACT && data !=null){
            Uri contactUri = data.getData();
            String[]queryFields = new String[]
                    {
                            ContactsContract.Contacts.DISPLAY_NAME
                    };

            Cursor c = getActivity().getContentResolver().query(contactUri, queryFields,null,null,null);

            try{
                if(c.getCount() ==0){
                    return;
                }
                c.moveToFirst();
                String collab = c.getString(0);
                mToDo.setCollab(collab);
                mCollabButton.setText(collab);
            }   finally{
                c.close();
            }

        }
    }

    private void updateDate() {
        mDateButton.setText(mToDo.getDate().toString());
    }

    private String getTodoReport(){

        String solvedString = null;
        if(mToDo.isSolved()){
            solvedString = getString(R.string.todo_add_solved);
        }else{
            solvedString=getString(R.string.todo_add_unsolved);
        }

        String dateFormat = "EEE, MMM dd";
        String dateString = DateFormat.format(dateFormat,mToDo.getDate()).toString();

        String collab = mToDo.getCollab();
        if(collab == null){
            collab = getString(R.string.todo_add_no_collab);

        }else{
            collab = getString(R.string.todo_add_collab, collab);
        }

        String report = getString(R.string.todo_add, mToDo.getTitle(),dateString,solvedString,collab);
        return report;


    }

}
