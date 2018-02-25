package com.rockspin.uktsdprep.data.repository

import android.content.Context
import com.rockspin.uktsdprep.data.model.Term
import com.rockspin.uktsdprep.data.model.TermGroup
import com.rockspin.uktsdprep.util.Utils
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class DataRepository private constructor(context: Context) {

    companion object {
        private var instance: DataRepository? = null

        @Synchronized
        fun getInstance(context: Context): DataRepository {
            if (instance == null) {
                instance = DataRepository(context)
            }

            return instance!!
        }
    }

    val termGroups: List<TermGroup>

    init {
        val termGroupsJson = Utils.readStringFromAssets(context, "termGroups.json")
        val moshi = Moshi.Builder().build()

        val adapter: JsonAdapter<List<TermGroup>> = moshi.adapter(Types.newParameterizedType(List::class.java, TermGroup::class.java))
        termGroups = adapter.fromJson(termGroupsJson)!!
    }

    val allTerms: List<Term> by lazy {
        val allTerms = mutableListOf<Term>()
        termGroups.forEach {
            allTerms.addAll(it.terms)
        }

        allTerms
    }
}