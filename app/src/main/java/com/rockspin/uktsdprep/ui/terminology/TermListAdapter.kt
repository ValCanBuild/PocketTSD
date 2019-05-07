package com.rockspin.uktsdprep.ui.terminology

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rockspin.uktsdprep.R
import com.rockspin.uktsdprep.data.model.Term
import kotlinx.android.synthetic.main.item_term.view.*

class TermListAdapter(
    private val inflater: LayoutInflater,
    private val terms: List<Term>)
    : RecyclerView.Adapter<TermListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_term, parent, false))
    }

    override fun getItemCount(): Int {
        return terms.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val term = terms[position]

        holder.phraseTextView.text = term.phrase
        holder.meaningTextView.text = term.meaning
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val phraseTextView: TextView = view.phraseTextView
        val meaningTextView: TextView = view.meaningTextView
    }
}