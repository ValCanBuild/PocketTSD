package com.rockspin.uktsdprep.ui.terminology

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rockspin.uktsdprep.R
import com.rockspin.uktsdprep.data.model.TermGroup
import kotlinx.android.synthetic.main.activity_term_list.*

class TermListActivity : AppCompatActivity() {

    companion object {
        private const val TERM_GROUP = "TERM_GROUP"

        fun createIntent(context: Context, group: TermGroup): Intent {
            return Intent(context, TermListActivity::class.java)
                .putExtra(TERM_GROUP, group)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_term_list)

        val group: TermGroup = intent.getParcelableExtra(TERM_GROUP)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        title = group.groupName

        val adapter = TermListAdapter(layoutInflater, group.terms)
        termListRecyclerView.adapter = adapter

        practiseButton.setOnClickListener {
            val intent = TermPractiseActivity.createIntent(this, group.terms)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
