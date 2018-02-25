package com.rockspin.uktsdprep.ui.customview

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.text.Html
import android.util.AttributeSet

class HtmlTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        text = Html.fromHtml(text.toString())
    }
}