package com.example.root.dragndroprecyclerviewapp;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static android.support.v4.content.ContextCompat.startActivity;


public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> implements View.OnDragListener {
    private List<Item> items;

    private ImageView dropTarget, dropped;
    private String tagDropTarget, tagDroppedImage;
    private View draggedImageView;
    private JsonHandler jsonHandler;
    private Context context;
    private ImageButton imageButtonBack;
    private MediaPlayer rightVoice, wrongVoice, mp;
    private ArrayList<Integer> sounds;
    private ArrayList<Item> temp;

    private int i = 0;
    private TextView score;


   public ItemAdapter(List<Item> items, Context context) {
        jsonHandler = new JsonHandler();
        this.items = items;
        this.context = context;
        jsonHandler = new JsonHandler();
/*
       temp = new ArrayList<>();
       temp.add(items.get(3));
       temp.add(items.get(4));
       temp.add(items.get(5));

       Collections.shuffle(temp);
       Log.d("temp", String.valueOf(temp));
       Log.d("items", String.valueOf(items));

       items.remove(items.get(items.size()-1));
       items.remove(items.get(items.size()-1));
       items.remove(items.get(items.size()-1));

       Log.d("itemsRemoved", String.valueOf(items));
       // Collections.shuffle(items);
       items.add(3,temp.get(2));
       items.add(4,temp.get(1));
       items.add(5,temp.get(0));
       Log.d("temp", String.valueOf(temp));
      // items.addAll(temp);*/

      updateItems(items);



    }


    public void updateItems(List<Item> items){

        temp = new ArrayList<>();
        for(int i =0 ; i<3; i++) {
            temp.add(items.get(items.size() - 1));
            Log.d("itemsSize", String.valueOf(items.size() - 1));
            items.remove(items.get(items.size() - 1));
        }

       /* temp.add(items.get(items.size() - 1));
        items.remove(items.get(items.size() - 1));

        temp.add(items.get(items.size() - 1));
        items.remove(items.get(items.size() - 1));*/




        /*temp.add(items.get(items.size()-2));
        temp.add(items.get(items.size()-3));*/

        Collections.shuffle(temp);
        Log.d("temp", String.valueOf(temp));
        Log.d("items", String.valueOf(items));

        /*items.remove(items.get(items.size()-1));
        items.remove(items.get(items.size()-1));
        items.remove(items.get(items.size()-1));*/

        Log.d("itemsRemoved", String.valueOf(items));
        // Collections.shuffle(items);
        items.add(3,temp.get(2));
        items.add(4,temp.get(1));
        items.add(5,temp.get(0));




        Log.d("temp", String.valueOf(temp));
        temp.clear();
        // items.addAll(temp);
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View statusContainer = inflater.inflate(R.layout.user_item, parent, false);
      //  score = (TextView)parent.findViewById(R.id.score);
        rightVoice = MediaPlayer.create(context, R.raw.correct);
        wrongVoice = MediaPlayer.create(context, R.raw.wrong);

        sounds = new ArrayList<>();
        sounds.add(R.raw.correct1);
        sounds.add(R.raw.correct2);
        sounds.add(R.raw.correct3);
        sounds.add(R.raw.correct4);
        sounds.add(R.raw.correct5);
        sounds.add(R.raw.correct6);



        return new ItemViewHolder(statusContainer);


    }

    private void nextScene(int value) {

            if (value == 3) {

                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {


                                Log.d("Total level",String.valueOf(SceneTracker.getTotalLevel()));
                                if(SceneTracker.getTotalLevel()==SceneTracker.getLevel()){
                                    Intent intent = new Intent(context.getApplicationContext(), LastActivity.class);
                                    context.startActivity(intent);
                                }
                                else

                                {

                                    SceneTracker.setScore(SceneTracker.getScore() + 1);
                                    items.clear();
                                    SceneTracker.setLevel(SceneTracker.getLevel() + 1);

                                    items = jsonHandler.getSceneData(SceneTracker.getLevel() - 1);
                                    if (items.size() != 0) {
                                        updateItems(items);


                                    }
                                    ItemAdapter.this.notifyDataSetChanged();
                                    SceneTracker.setCount(0);

                                    MainActivity.nextButton.setVisibility(View.GONE);
                                }



                    }
                });
            }


    }


    /*private void countMatch(boolean match) {
        if (match) {
            SceneTracker.setCount(SceneTracker.getCount() + 1);
        }


        nextScene(SceneTracker.getCount());

    }*/


    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        Item status = items.get(position);
        holder.bind(status);

        MainActivity.levelText.setText(String.valueOf(SceneTracker.getLevel()));

        Log.d("items", String.valueOf(items.size()));
      /*  if (position > 2) {


            temp.add(items.get(items.size()-1));
            temp.add(items.get(items.size()-2));
            temp.add(items.get(items.size()-3));

            Collections.shuffle(temp);
            Log.d("temp", String.valueOf(temp));
            Log.d("items", String.valueOf(items));

            items.remove(items.get(items.size()-1));
            items.remove(items.get(items.size()-1));
            items.remove(items.get(items.size()-1));

            Log.d("itemsRemoved", String.valueOf(items));
           // Collections.shuffle(items);
           *//* items.add(3,temp.get(2));
            items.add(4,temp.get(1));
            items.add(5,temp.get(0));*//*
            Log.d("temp", String.valueOf(temp));
           items.addAll(temp);

        }*/

        holder.imageButton.setVisibility(View.VISIBLE);


//        Picasso.with(holder.imageView.getContext()).load(ImageBinder.getImageUrl(url)).into(holder.imageButton);

   /*     if (position <= 2) {

            holder.imageButton.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View view) {

                    ClipData clipData = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    view.startDrag(clipData, shadowBuilder, view, 0);
                    view.setVisibility(View.INVISIBLE);

                    return true;

                }
            });
        }*/

        if (position <= 2) {

            holder.imageButton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    ClipData clipData = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    view.startDrag(clipData, shadowBuilder, view, 0);
                    view.setVisibility(View.INVISIBLE);


                    return true;
                }
            });

        }



        if (position > 2) {

            holder.imageButton.setOnDragListener(this);
        }

    }


    @Override
    public boolean onDrag(View receivingLayoutView, DragEvent dragEvent) {
        draggedImageView = (View) dragEvent.getLocalState();
        // ImageView draggedImageView = (ImageView) dragEvent.getLocalState();

        switch (dragEvent.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:


                return true;

            case DragEvent.ACTION_DRAG_ENTERED:

                return true;

            case DragEvent.ACTION_DRAG_LOCATION:

                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                return true;

            case DragEvent.ACTION_DROP:

                dropTarget = (ImageView) receivingLayoutView;
                dropped = (ImageView) draggedImageView;
                // Log.d("tagf",dropTarget.toString());

                tagDropTarget = (String) receivingLayoutView.getTag();
                tagDroppedImage = (String) draggedImageView.getTag();

                isDragMatching();

                return true;


            case DragEvent.ACTION_DRAG_ENDED:

                if (!dragEvent.getResult()) {

                    draggedImageView.setVisibility(View.VISIBLE);
                }

                return true;

            default:

                break;
        }
        return false;
    }


    private boolean isDragMatching() {


        boolean matchFlag = false;
        if ((tagDropTarget != null) && (tagDropTarget.equals(tagDroppedImage))) {


            rightVoice.start();
            rightVoice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {

                    playRandomSound();
                    dropTarget.setImageDrawable(dropped.getDrawable());


                }
            });
            SceneTracker.setCorrectedItem((SceneTracker.getCorrectedItem()+1));
            matchFlag = true;
            MainActivity.right.setText(String.valueOf(SceneTracker.getCorrectedItem()));
            Log.d("right", String.valueOf(SceneTracker.getCorrectedItem()));
            SceneTracker.setCount(SceneTracker.getCount() + 1);
            nextScene(SceneTracker.getCount());

        } else {
            wrongVoice.start();
            SceneTracker.setWrongItem((SceneTracker.getWrongItem()+1));
            MainActivity.wrong.setText(String.valueOf(SceneTracker.getWrongItem()));
            draggedImageView.setVisibility(View.VISIBLE);
            matchFlag = false;
            return false;
        }


        return true;
    }

    private void playRandomSound() {
        int randomInt = (new Random().nextInt(sounds.size()));
        int sound = sounds.get(randomInt);
        mp = MediaPlayer.create(context, sound);
        if (SceneTracker.getCount() != 3) {
            mp.start();
        }

    }


    public void prevScene() {
        items.clear();
        SceneTracker.setLevel(SceneTracker.getLevel() - 1);
        items = jsonHandler.getSceneData(SceneTracker.getLevel() - 1);
        if(items.size()!=0){
            updateItems(items);

        }
        ItemAdapter.this.notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


}