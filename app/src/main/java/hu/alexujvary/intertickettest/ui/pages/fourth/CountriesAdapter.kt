package hu.alexujvary.intertickettest.ui.pages.fourth

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.alexujvary.intertickettest.R
import hu.alexujvary.intertickettest.model.CountryResponse

class CountriesAdapter(private var items: MutableList<CountryResponse> = mutableListOf()) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    fun update(itemsList: List<CountryResponse>) {
        val itemsSize = items.size
        items.clear()
        notifyItemRangeRemoved(0, itemsSize)
        if (itemsList.isEmpty()) {
            return
        }
        items.addAll(itemsList)
        notifyItemRangeInserted(items.size, itemsList.size)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = items[position]
        viewHolder.tvCountry.text = item.name
        viewHolder.tvCapital.text = item.capital
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCountry: TextView = view.findViewById(R.id.tvCountry)
        val tvCapital: TextView = view.findViewById(R.id.tvCapital)
    }
}