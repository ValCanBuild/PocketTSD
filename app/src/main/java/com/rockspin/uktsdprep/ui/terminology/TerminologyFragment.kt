package com.rockspin.uktsdprep.ui.terminology

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rockspin.uktsdprep.R
import com.rockspin.uktsdprep.data.repository.DataRepository
import kotlinx.android.synthetic.main.fragment_terminology.*

class TerminologyFragment: Fragment() {

    companion object {
        fun newInstance(): TerminologyFragment {
            return TerminologyFragment()
        }
    }

    private lateinit var dataRepository: DataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataRepository = DataRepository.getInstance(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_terminology, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manualEntries = listOf(TerminologyEntry(getString(R.string.title_emblem)))
        val termEntriesFromGroups = dataRepository.termGroups.map { TerminologyEntry(it.groupName) }

        val allEntries = manualEntries + termEntriesFromGroups
        val adapter = TerminologyAdapter(LayoutInflater.from(context), allEntries) { pos ->
            val isManualEntry = pos < manualEntries.size
            if (isManualEntry) {
                when (pos) {
                    0 -> startActivity(Intent(context, EmblemActivity::class.java))
                }
            } else {
                val groupTermPos = pos - manualEntries.size
                val intent = TermListActivity.createIntent(requireContext(), dataRepository.termGroups[groupTermPos])
                startActivity(intent)
            }
        }

        termGroupRecyclerView.adapter = adapter

        shuffleButton.setOnClickListener {
            val intent = TermPractiseActivity.createIntent(requireContext(), dataRepository.allTerms)
            context!!.startActivity(intent)
        }
    }
}