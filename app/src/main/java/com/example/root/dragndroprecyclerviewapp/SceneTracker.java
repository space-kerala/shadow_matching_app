package com.example.root.dragndroprecyclerviewapp;

/**
 * Created by root on 4/11/17.
 */

public class SceneTracker {
    private static int level=0,flag=0,correctedItem=0,wrongItem=0;
    private static int totalLevel=0,count=0,picassoCount=0,score=0;
    public SceneTracker( ){

    }

    public static void setLevel( int value){
        level=value;

    }

    public static int getLevel()
    {
        return level;
    }



    public static void setCorrectedItem( int value){
        correctedItem=value;

    }

    public static int getCorrectedItem()
    {
        return correctedItem;
    }



    public static void setWrongItem( int value){
        wrongItem = value;

    }

    public static int getWrongItem()
    {
        return wrongItem;
    }





    public static void setCount(int value){
        count = value;

    }

    public  static int getCount(){
        return count;
    }


    public static void setPicassoCount(int value){
        picassoCount=value;
    }

    public static int getPicassoCount(){
        return picassoCount;
    }


    public static void setTotalLevel(int value) {
        totalLevel = value;
    }

    public static int getTotalLevel(){
        return totalLevel;
    }

    public static void setScore(int value){
        score = value;
    }
    public static int getScore(){return score;}



    public static void setFlag(int value){
        flag=value;
    }

    public static int getFlag(){return flag;}



}
