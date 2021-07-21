package com.currencyconverter.featuremain.presentation

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) : ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.bottom = verticalSpaceHeight

        if (itemPosition == 0) {
            outRect.top = verticalSpaceHeight
        }
    }
}