package uz.gita.notes_app.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.notes_app.R
import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.databinding.ListItemTasksBinding
import uz.gita.notes_app.utils.extensions.inflate
import uz.gita.notes_app.utils.extensions.invisible
import uz.gita.notes_app.utils.extensions.visible

// Created by Jamshid Isoqov an 9/9/2022
class TaskAdapter : ListAdapter<TaskData, TaskAdapter.ViewHolder>(itemTaskCallback) {

    private var editListener: ((TaskData) -> Unit)? = null

    private var checkedListener: ((TaskData) -> Unit)? = null

    fun setEditListener(block: (TaskData) -> Unit) {
        editListener = block
    }

    fun setCheckedListener(block: (TaskData) -> Unit) {
        checkedListener = block
    }

    inner class ViewHolder(val binding: ListItemTasksBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                editListener?.invoke(getItem(absoluteAdapterPosition))
            }
            binding.checkboxTask.setOnClickListener {
                checkedListener?.invoke(getItem(absoluteAdapterPosition).copy(is_checked = if (binding.checkboxTask.isChecked) 1 else 0))
            }
        }

        fun onBind() {
            val data = getItem(absoluteAdapterPosition)
            binding.tvTitle.text = data.title
            binding.tvDate.text = data.date
            binding.checkboxTask.apply {
                if (data.is_checked == 1) {
                    isChecked = true
                    binding.lineView.visible()
                } else {
                    binding.lineView.invisible()
                    isChecked = false
                }
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemTasksBinding.bind(parent.inflate(R.layout.list_item_tasks))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

}

private val itemTaskCallback = object : DiffUtil.ItemCallback<TaskData>() {
    override fun areItemsTheSame(oldItem: TaskData, newItem: TaskData) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: TaskData, newItem: TaskData): Boolean =
        oldItem.title == newItem.title && oldItem.description == newItem.description && oldItem.is_checked == newItem.is_checked

}