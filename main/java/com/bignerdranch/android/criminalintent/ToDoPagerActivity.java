package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class ToDoPagerActivity extends AppCompatActivity {
    private static final String EXTRA_TODO_ID ="com.bignerdranch.android.criminallntent.todo_id";

    private ViewPager mViewPager;
    private List<ToDo> mToDos;

    public static Intent newIntent(Context packageContext, UUID todoId){
        Intent intent = new Intent(packageContext, ToDoPagerActivity.class);
        intent.putExtra(EXTRA_TODO_ID, todoId);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID todoId = (UUID) getIntent().getSerializableExtra(EXTRA_TODO_ID);

        setContentView(R.layout.activity_todo_pager);
        mViewPager = (ViewPager) findViewById(R.id.todo_view_pager);

        mToDos = ToDoLab.get(this).getToDos();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                ToDo toDo = mToDos.get(position);
                return ToDoFragment.newInstance(toDo.getId());
            }

            @Override
            public int getCount() {

                return mToDos.size();
            }
        });

        for (int i = 0; i< mToDos.size(); i++){
            if(mToDos.get(i).getId().equals(todoId)){
                mViewPager.setCurrentItem(i);
                break;

            }
        }

    }
}
