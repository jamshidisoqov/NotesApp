package uz.gita.notes_app.ui.adapters

import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.notes_app.R
import uz.gita.notes_app.databinding.ListItemColorBinding
import uz.gita.notes_app.utils.extensions.inflate

// Created by Jamshid Isoqov an 9/8/2022
class ColorAdapter(private val colorList: List<String>) :
    RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    private var itemClickListener: ((String) -> Unit)? = null

    fun setItemClickListener(block: (String) -> Unit) {
        itemClickListener = block
    }


    inner class ViewHolder(private val binding: ListItemColorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClickListener?.invoke(colorList[absoluteAdapterPosition])
            }
        }

        fun onBind() {
            try {
                binding.colorImage.setBackgroundColor(Color.parseColor(colorList[absoluteAdapterPosition]))
            } catch (e: Exception) {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemColorBinding.bind(
            parent.inflate(
                R.layout.list_item_color
            )
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

    override fun getItemCount(): Int = colorList.size

}