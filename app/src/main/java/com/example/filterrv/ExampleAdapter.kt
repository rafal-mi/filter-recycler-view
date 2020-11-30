package com.example.filterrv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ExampleAdapter internal constructor(exampleList: MutableList<ExampleItem>) :
    RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>(), Filterable {
    private val exampleList: List<ExampleItem>
    private lateinit var exampleListFull: List<ExampleItem>

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.image_view)
        var textView1: TextView = itemView.findViewById(R.id.text_view1)
        var textView2: TextView = itemView.findViewById(R.id.text_view2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.example_item,
            parent, false
        )
        return ExampleViewHolder(v)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem: ExampleItem = exampleList[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.setText(currentItem.text1)
        holder.textView2.setText(currentItem.text2)
    }

    override fun getItemCount(): Int {
        return exampleList.size
    }

    private val exampleFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: MutableList<ExampleItem> = ArrayList()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(exampleListFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (item in exampleListFull) {
                    if (item.text2.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            exampleList.clear()
            exampleList.addAll(results.values as List<ExampleItem>)
            notifyDataSetChanged()
        }
    }

    init {
        this.exampleList = exampleList
        exampleListFull = ArrayList(exampleList)
    }

    override fun getFilter(): Filter {
        return exampleFilter
    }
}
