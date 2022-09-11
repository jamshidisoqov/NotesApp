package uz.gita.notes_app.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.notes_app.R
import uz.gita.notes_app.data.models.NoteCategoryData
import uz.gita.notes_app.databinding.ListItemChipBinding
import uz.gita.notes_app.utils.extensions.gone
import uz.gita.notes_app.utils.extensions.inflate
import uz.gita.notes_app.utils.extensions.visible

// Created by Jamshid Isoqov an 9/7/2022
class ChipAdapter : RecyclerView.Adapter<ChipAdapter.ViewHolder>() {

    private var notesCategoryList: List<NoteCategoryData> = emptyList()

    var selectedPosition = 0

    val selectedCategoryId: Int
        get() = if (notesCategoryList.isNotEmpty()) notesCategoryList[selectedPosition].id
        else 1

    private var itemClickListener: ((Int) -> Unit)? = null

    fun setItemClickListener(block: (Int) -> Unit) {
        itemClickListener = block
    }

    private var itemDeleteListener: ((NoteCategoryData) -> Unit)? = null

    fun setDeleteListener(block: (NoteCategoryData) -> Unit) {
        itemDeleteListener = block
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<NoteCategoryData>) {
        notesCategoryList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListItemChipBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClickListener?.invoke(notesCategoryList[absoluteAdapterPosition].id)
                notifyItemChanged(selectedPosition)
                selectedPosition = absoluteAdapterPosition
                notifyItemChanged(absoluteAdapterPosition)
            }
            binding.root.setOnLongClickListener {
                if (absoluteAdapterPosition != 0&&selectedPosition!=absoluteAdapterPosition)
                    binding.imageDelete.visible()
                true
            }
            binding.imageDelete.setOnClickListener {
                itemDeleteListener?.invoke(notesCategoryList[absoluteAdapterPosition])
            }
        }

        fun onBind() {
            binding.tvChip.text = notesCategoryList[absoluteAdapterPosition].name
            if (absoluteAdapterPosition == selectedPosition) {
                binding.tvChip.setBackgroundResource(R.drawable.bg_chip_selected)
                binding.tvChip.setTextColor(Color.BLACK)
            } else {
                binding.tvChip.setBackgroundResource(R.drawable.bg_chip_unselected)
                binding.tvChip.setTextColor(Color.WHITE)
            }
            binding.imageDelete.gone()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemChipBinding.bind(parent.inflate(R.layout.list_item_chip))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

    override fun getItemCount(): Int = notesCategoryList.size

}

