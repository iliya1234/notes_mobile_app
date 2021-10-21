package com.example.notes.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.models.AppNote
import kotlinx.android.synthetic.main.note_item.view.*

class MainAdapter:RecyclerView.Adapter<MainAdapter.MainHolder>() {
    private var mListNotes = emptyList<AppNote>()


    override fun onViewAttachedToWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener {
            MainFragment.click(mListNotes[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener(null)
    }

    class MainHolder(view: View):RecyclerView.ViewHolder(view){
        val nameNote:TextView = view.item_note_name
        val textNote:TextView = view.item_note_text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.nameNote.text = mListNotes[position].name
        holder.textNote.text = mListNotes[position].text
    }

    override fun getItemCount(): Int = mListNotes.size

    fun setList(list:List<AppNote>){
        mListNotes = list
        notifyDataSetChanged()
    }
}