package by.olegkopeykin.weather.common

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("visibleGone")
fun View.setVisibilityGone(visible: Boolean?) {
    this.visibility = if (visible == true) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleInvisible")
fun View.setVisibilityInvisible(visible: Boolean?) {
    this.visibility = if (visible == true) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("visibleGoneByList")
fun View.visibleGoneByList(list: List<*>) {
    this.visibility = if (!list.isNullOrEmpty()) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleGoneByString")
fun View.visibleGoneByString(string: String?) {
    this.visibility = if (!string.isNullOrEmpty()) View.VISIBLE else View.GONE
}

@BindingAdapter(value = ["items", "adapter"], requireAll = false)
@Suppress("UNCHECKED_CAST")
fun RecyclerView.setItems(items: List<Any>?, adapter: RecyclerView.Adapter<*>) {
    if (this.adapter != adapter) {
        this.adapter = adapter
    }
    if (items != null) {
        this.adapter?.also {
            this.recycledViewPool.clear()
            (it as ListAdapter<Any, *>).submitList(ArrayList(items))
        }
    }
}