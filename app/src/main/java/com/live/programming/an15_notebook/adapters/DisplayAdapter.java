package com.live.programming.an15_notebook.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.live.programming.an15_notebook.DetailsActivity;
import com.live.programming.an15_notebook.R;
import com.live.programming.an15_notebook.databinding.ItemLayoutBinding;
import com.live.programming.an15_notebook.models.NoteModel;

import java.util.List;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.Design> {

    private List<NoteModel> listNotes;
    private Context ctx;

    public DisplayAdapter(Context ctx,List<NoteModel> listNotes) {
        this.listNotes = listNotes;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public DisplayAdapter.Design onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View designView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        DisplayAdapter.Design design = new DisplayAdapter.Design(designView);
        return design;
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayAdapter.Design holder, int position) {
        NoteModel note = listNotes.get(position);
        holder.txtTitle.setText(note.getNoteTitle());
        holder.card.setOnClickListener(view -> {

            /* to route to a different screen with a selected note details  */
            Bundle bundle = new Bundle();
            bundle.putString("id", note.getNoteId());
            bundle.putString("title", note.getNoteTitle());
            bundle.putString("desc", note.getNoteDesc());
            bundle.putString("date_time", note.getDateTime());
            Intent intent = new Intent(ctx, DetailsActivity.class);
            intent.putExtra("details",bundle);
            ctx.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    public class Design extends RecyclerView.ViewHolder {
        private ItemLayoutBinding layoutBinding;
        private TextView txtTitle;
        private CardView card;
        public Design(@NonNull View itemView) {
            super(itemView);
            layoutBinding = ItemLayoutBinding.bind(itemView);
            txtTitle = layoutBinding.noteTitle;
            card = layoutBinding.getRoot();
        }
    }
}
