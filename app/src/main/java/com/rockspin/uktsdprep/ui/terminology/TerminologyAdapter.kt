package com.rockspin.uktsdprep.ui.terminology

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rockspin.uktsdprep.R
import kotlinx.android.synthetic.main.item_term_group.view.*

class TerminologyAdapter(
    private val inflater: LayoutInflater,
    private val termEntries: List<TerminologyEntry>,
    private val listener: (Int) -> Unit)
    : RecyclerView.Adapter<TerminologyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(inflater.inflate(R.layout.item_term_group, parent, false))

        viewHolder.itemView.setOnClickListener {
            listener(viewHolder.adapterPosition)
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return termEntries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = termEntries[position].name
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView: TextView = view.termGroupTextView
    }
}