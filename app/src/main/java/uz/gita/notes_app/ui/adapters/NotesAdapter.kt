package uz.gita.notes_app.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.notes_app.R
import uz.gita.notes_app.data.models.NoteData
import uz.gita.notes_app.databinding.ListItemNotesBinding
import uz.gita.notes_app.utils.extensions.inflate
import uz.gita.notes_app.utils.extensions.invisible
import uz.gita.notes_app.utils.extensions.visible

// Created by Jamshid Isoqov an 9/7/2022
class NotesAdapter : ListAdapter<NoteData, NotesAdapter.ViewHolder>(itemNotedCallback) {

    private var deleteListener: ((NoteData) -> Unit)? = null

    private var editListener:((NoteData)->Unit)? = null

    fun setDeleteListener(block: (NoteData) -> Unit) {
        deleteListener = block
    }

    fun setEditListener(block: (NoteData) -> Unit) {
        editListener = block
    }

    inner class ViewHolder(private val binding: ListItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.imageTrash.setOnClickListener {
                deleteListener?.invoke(getItem(absoluteAdapterPosition))
            }
            binding.root.setOnClickListener {
                editListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun onBind() {
            val data = getItem(absoluteAdapterPosition)

            binding.apply {
                tvTitle.text = data.title
                tvDate.text = data.date
                if (data.pinned == 1) {
                    imagePin.visible()
                } else {
                    imagePin.invisible()
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemNotesBinding.bind(parent.inflate(R.layout.list_item_notes))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}

private val itemNotedCallback = object : DiffUtil.ItemCallback<NoteData>() {
    override fun areItemsTheSame(oldItem: NoteData, newItem: NoteData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: NoteData, newItem: NoteData) =
        oldItem.date == newItem.date
                && oldItem.pinned == newItem.pinned
                && oldItem.description == newItem.description
                && oldItem.title == newItem.title


}