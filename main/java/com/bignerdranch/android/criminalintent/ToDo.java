package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

public class ToDo {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private String mCollab;
    private String mDetail;
    private String mCat;

    public ToDo(){
        this(UUID.randomUUID());

    }
    public ToDo(UUID id){
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getCat() {
        return mCat;
    }

    public void setCat(String cat) {
        mCat = cat;
    }

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        mDetail = detail;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public String getCollab(){
        return mCollab;
    }

    public void setCollab(String collab){
        mCollab = collab;
    }
}
