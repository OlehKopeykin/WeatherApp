package by.olegkopeykin.weather.common

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

object DataBinding {

    @BindingAdapter("visibleGone")
    @JvmStatic
    fun setVisibilityGone(view: View, visible: Boolean?) {
        view.visibility = if (visible == true) View.VISIBLE else View.GONE
    }

    @BindingAdapter("visibleInvisible")
    @JvmStatic
    fun setVisibilityInvisible(view: View, visible: Boolean?) {
        view.visibility = if (visible == true) View.VISIBLE else View.INVISIBLE
    }

    @BindingAdapter("app:visibleGoneByList")
    @JvmStatic
    fun visibleGoneByList(view: View, list: List<*>) {
        view.visibility = if (!list.isNullOrEmpty()) View.VISIBLE else View.GONE
    }

    @BindingAdapter("app:visibleGoneByString")
    @JvmStatic
    fun visibleGoneByString(view: View, string: String?) {
        view.visibility = if (!string.isNullOrEmpty()) View.VISIBLE else View.GONE
    }

    @BindingAdapter(value = ["app:items", "app:adapter"], requireAll = false)
    @JvmStatic
    fun setItems(view: RecyclerView, items: List<Any>?, adapter: RecyclerView.Adapter<*>) {
        if (view.adapter != adapter) {
            view.adapter = adapter
        }
        if (items != null) {
            view.adapter?.also {
                view.recycledViewPool.clear()
                (it as ListAdapter<Any, *>).submitList(ArrayList(items))
            }
        }
    }
}