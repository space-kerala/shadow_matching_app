package com.example.root.dragndroprecyclerviewapp;

import android.content.ClipData;
import android.content.Context;
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
import java.util.List;
import java.util.Random;


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
    private int i = 0;
    private TextView score;


   public ItemAdapter(List<Item> items, Context context) {
        jsonHandler = new JsonHandler();
        this.items = items;
        this.context = context;
        jsonHandler = new JsonHandler();


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

            MainActivity.nextButton.setVisibility(View.VISIBLE);
            MainActivity.nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SceneTracker.setScore(SceneTracker.getScore() + 1);
                    items.clear();
                    SceneTracker.setLevel(SceneTracker.getLevel() + 1);
                   // MainActivity.score.setText(String.valueOf(SceneTracker.getScore()));

                    items = jsonHandler.getSceneData(SceneTracker.getLevel() - 1);
                    ItemAdapter.this.notifyDataSetChanged();
                    SceneTracker.setCount(0);
                    MainActivity.nextButton.setVisibility(View.GONE);


                }
            });




                   // SceneTracker.setScore(SceneTracker.getScore() + 1);


                }
            });


        }

        Log.d("Score", String.valueOf(SceneTracker.getScore()));
    }


    private void countMatch(boolean match) {
        if (match) {
            SceneTracker.setCount(SceneTracker.getCount() + 1);
        }


        nextScene(SceneTracker.getCount());

    }


    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        Item status = items.get(position);
        holder.bind(status);
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

            matchFlag = true;
            //Log.d("tagid", Integer.toString(dropped.getId()));

        } else {
            wrongVoice.start();
            draggedImageView.setVisibility(View.VISIBLE);
            matchFlag = false;
            return false;
        }

        countMatch(matchFlag);
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
        ItemAdapter.this.notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


}