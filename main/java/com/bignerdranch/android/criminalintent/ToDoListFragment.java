package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ToDoListFragment extends Fragment {

    private RecyclerView mToDoRecyclerView;
    private ToDoAdapter mAdapter;
    private boolean mSubtitleVisible;
    private static final String SAVED_SUBTITLE_VISIBLE= "subtitle";

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_todo_list,container,false);

        mToDoRecyclerView=(RecyclerView) view
                .findViewById(R.id.todo_recycler_view);
        mToDoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null){
            mSubtitleVisible= savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE,mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_list_todo, menu);

        MenuItem subtitleItem =menu.findItem(R.id.show_subtitle);
        if (mSubtitleVisible){
            subtitleItem.setTitle(R.string.hide_subtitle);
        }else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.new_todo:
                ToDo toDo = new ToDo();
                ToDoLab.get(getActivity()).addToDo(toDo);
                Intent intent = ToDoPagerActivity.newIntent(getActivity(), toDo.getId());
                startActivity(intent);
                return true;
            case R.id.show_subtitle:
                mSubtitleVisible = !mSubtitleVisible ;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle(){
        ToDoLab toDoLab = ToDoLab.get(getActivity());
        int todoCount = toDoLab.getToDos().size();
        String subtitle = getString(R.string.subtitle_format, todoCount);

        if(!mSubtitleVisible){
            subtitle=null;
        }

        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI() {
        ToDoLab toDoLab = ToDoLab.get(getActivity());
        List<ToDo> toDos = toDoLab.getToDos();

        if (mAdapter == null) {
            mAdapter = new ToDoAdapter(toDos);
            mToDoRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setToDos(toDos);
            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();
    }

    private class ToDoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ToDo mToDo;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;
        private TextView mCatTextView;
        //private TextView mCollabView;






        public ToDoHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_todo, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.todo_title);
            mCatTextView = (TextView) itemView.findViewById(R.id.todo_cat_view);
            mDateTextView = (TextView)itemView.findViewById(R.id.todo_date);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.todo_solved);
            //mCollabView = (TextView)itemView.findViewById(R.id.todo_cat_view);




        }

        @Override
        public void onClick(View view){
            Intent intent = ToDoPagerActivity.newIntent(getActivity(), mToDo.getId());
            startActivity(intent);
        }


        public void bind(ToDo toDo){
            mToDo = toDo;
            mTitleTextView.setText(mToDo.getTitle());
            mCatTextView.setText(mToDo.getCat());
            mDateTextView.setText(mToDo.getDate().toString());
            mSolvedImageView.setVisibility(toDo.isSolved() ? View.VISIBLE : View.GONE);
            //mCollabView.setText(mToDo.getCollab().toString());



        }

    }

    private class ToDoAdapter extends RecyclerView.Adapter<ToDoHolder>{
        private List<ToDo> mToDos;
        public ToDoAdapter(List<ToDo> toDos){
            mToDos = toDos;
        }

        @Override
        public ToDoHolder onCreateViewHolder(ViewGroup parent,int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ToDoHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(ToDoHolder holder, int position){
            ToDo toDo = mToDos.get(position);
            holder.bind(toDo);
        }

        @Override
        public int getItemCount(){
            return mToDos.size();
        }

        public void setToDos(List<ToDo> toDos){
            mToDos = toDos;
        }
    }

}
