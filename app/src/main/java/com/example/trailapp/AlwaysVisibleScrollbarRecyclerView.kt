package com.example.trailapp

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class AlwaysVisibleScrollbarRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    init {
        isVerticalScrollBarEnabled = true
        isHorizontalScrollBarEnabled = true
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setScrollBarStyle(SCROLLBARS_INSIDE_OVERLAY)
        setScrollbarFadingEnabled(false)
    }
}