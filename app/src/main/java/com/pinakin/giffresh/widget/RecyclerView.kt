package com.pinakin.giffresh.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    var emptyView: View? = null
        set(value) {
            field = value
            checkIfEmpty()
        }

    private val observer = object : AdapterDataObserver() {

        override fun onChanged() {
            checkIfEmpty()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        val oldAdapter = getAdapter()

        oldAdapter?.unregisterAdapterDataObserver(observer)

        super.setAdapter(adapter)

        adapter?.registerAdapterDataObserver(observer)

        checkIfEmpty()
    }

    private fun checkIfEmpty() {

        if (emptyView != null && adapter != null) {

            val emptyViewVisible: Boolean = adapter?.itemCount == 0

            emptyView?.visibility = if (emptyViewVisible) View.VISIBLE else View.INVISIBLE

            visibility = if (emptyViewVisible) View.INVISIBLE else View.VISIBLE
        }
    }
}