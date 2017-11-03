package com.example.root.dragndroprecyclerviewapp;
import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> implements View.OnDragListener {
    private final List<User> users;
    private ImageView dropTarget, dropped;
    private String tagDropTarget, tagDroppedImage;
    private View draggedImageView;

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View statusContainer = inflater.inflate(R.layout.user_item, parent, false);



        return new UserViewHolder(statusContainer);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User status = users.get(position);
        holder.bind(status);



       if(position<=2) {

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

        if(position>2){
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
                Log.d("tagf","dropTarget");

                tagDropTarget = (String) dropTarget.getTag();
                tagDroppedImage = (String) dropped.getTag();

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

        } else {
            draggedImageView.setVisibility(View.VISIBLE);
            return false;
        }

        return true;
    }


    @Override
    public int getItemCount() {
        return users.size();
    }


}