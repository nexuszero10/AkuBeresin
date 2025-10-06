package com.unsoed.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.unsoed.noteapp.databinding.NoteLayoutBinding
import com.unsoed.noteapp.fragments.HomeFragmentDirections
import com.unsoed.noteapp.model.Note

class NoteAdapter  : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    // inner class viewholder
    class NoteViewHolder(val itemBinding: NoteLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Note>(){

        // membandingkan list lama & baru
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.noteDesc == newItem.noteDesc &&
                    oldItem.noteTitle == newItem.noteTitle
        }

        // update item yang berubah
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    // async -> work bg thread
    val differ = AsyncListDiffer(this, differCallback)

    // call saat recyclermembuat layout baru untuk item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            // buat note_layout
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        )

    }

    // caat saat item perlu di-bind -> dipakai saat edit
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position] // get note

        // tampilkan data di textview
        holder.itemBinding.noteTitle.text = currentNote.noteTitle
        holder.itemBinding.noteDesc.text = currentNote.noteDesc

        // saat note item diklik -> pindah ke editFragment
        holder.itemView.setOnClickListener {
            // bawa data item note dengan safe arags
            val direction = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
            it.findNavController().navigate(direction)
        }
    }

    // banyak item note di list
    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}