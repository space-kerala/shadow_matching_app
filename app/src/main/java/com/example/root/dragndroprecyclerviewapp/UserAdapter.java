package com.example.root.dragndroprecyclerviewapp;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> implements View.OnDragListener {
    private List<User> users;
    private ImageView dropTarget, dropped;
    private String tagDropTarget, tagDroppedImage;
    private View draggedImageView;
    private JsonHandler jsonHandler;
    private Context context;
    private boolean matchFlag = false;
    private ImageButton imageButtonBack;


    public UserAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
        jsonHandler = new JsonHandler(context);


    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View statusContainer = inflater.inflate(R.layout.user_item, parent, false);

        return new UserViewHolder(statusContainer);


    }

    private void nextScene(int value) {

        if (value == 3) {
            users.clear();

            SceneTracker.setLevel(SceneTracker.getLevel() + 1);
            users = jsonHandler.getSceneData(SceneTracker.getLevel() - 1);
            UserAdapter.this.notifyDataSetChanged();
            SceneTracker.setCount(0);


        }

        Log.d("tagCount", String.valueOf(SceneTracker.getCount()));
    }


    private void countMatch(boolean match) {
        if (match) {
            SceneTracker.setCount(SceneTracker.getCount() + 1);
        }


        nextScene(SceneTracker.getCount());

    }


    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

        User status = users.get(position);
        holder.bind(status);
        holder.imageButton.setVisibility(View.VISIBLE);


//        Picasso.with(holder.imageView.getContext()).load(ImageBinder.getImageUrl(url)).into(holder.imageButton);

        if (position <= 2) {

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

        if ((tagDropTarget != null) && (tagDropTarget.equals(tagDroppedImage))) {

            dropTarget.setImageDrawable(dropped.getDrawable());

            matchFlag = true;
            Log.d("tagid", Integer.toString(dropped.getId()));

        } else {
            draggedImageView.setVisibility(View.VISIBLE);
            matchFlag = false;
            return false;
        }

        countMatch(matchFlag);
        return true;
    }


    public void prevScene()
    {
        users.clear();
        SceneTracker.setLevel(SceneTracker.getLevel()-1);
        users=jsonHandler.getSceneData(SceneTracker.getLevel()-1);
        UserAdapter.this.notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return users.size();
    }


}