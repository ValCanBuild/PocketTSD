package com.rockspin.uktsdprep.ui.terminology

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        dataRepository = DataRepository.getInstance(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_terminology, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val entries = dataRepository.termGroups.map { TerminologyEntry(it.groupName) }

        val adapter = TerminologyAdapter(LayoutInflater.from(context), entries) {
            val intent = TermListActivity.createIntent(context!!, dataRepository.termGroups[it])
            context!!.startActivity(intent)
        }

        termGroupRecyclerView.adapter = adapter

        shuffleButton.setOnClickListener {
            val intent = TermPractiseActivity.createIntent(context!!, dataRepository.allTerms)
            context!!.startActivity(intent)
        }
    }
}