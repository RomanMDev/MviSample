package com.example.mvisample.ui.map

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvisample.R
import com.example.mvisample.model.Location
import com.example.mvisample.model.Point
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_point.*
import kotlinx.android.synthetic.main.layout_bottom.*

class PointsAdapter : RecyclerView.Adapter<PointsAdapter.PointViewHolder>() {


    var items = emptyList<Point>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder =
        PointViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_point, parent, false)
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun update(points: List<Point>) {
        items = points
        notifyDataSetChanged()
    }

    class PointViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(point: Point) {
            pointTitle.text = point.location.toString()
        }
    }

}
