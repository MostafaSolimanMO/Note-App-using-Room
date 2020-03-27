package com.example.mo.noteappusingroom.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mo.noteappusingroom.POJO.Note;
import com.example.mo.noteappusingroom.R;

import java.util.List;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    final private ItemClickListener mItemClickListener;
    private List<Note> mNoteList;
    private Context mContext;


    NoteAdapter(Context context, ItemClickListener listener) {
        mContext = context;
        mItemClickListener = listener;
    }

    void setNotes(List<Note> list) {
        mNoteList = list;
        notifyDataSetChanged();
    }

    List<Note> getNote() {
        return mNoteList;
    }

    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteAdapter.NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteAdapter.NoteViewHolder holder, int position) {
        Note notes = mNoteList.get(position);
        String title = notes.getTitle();
        holder.titleTextView.setText(title);
        String note = notes.getDescription();
        holder.noteTextView.setText(note);
    }

    @Override
    public int getItemCount() {
        if (mNoteList != null)
            return mNoteList.size();
        else return 0;
    }

    public interface ItemClickListener {
        void onItemClickListener(Note note);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTextView;
        TextView noteTextView;

        public NoteViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_item);
            noteTextView = itemView.findViewById(R.id.note_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int elementId = getAdapterPosition();
            mItemClickListener.onItemClickListener(mNoteList.get(elementId));
        }
    }
}
