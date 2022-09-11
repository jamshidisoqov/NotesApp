package uz.gita.notes_app.ui.task.search

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.chip.Chip
import uz.gita.notes_app.R
import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.databinding.ScreenSearchTaskBinding
import uz.gita.notes_app.presenter.SearchTasksViewModel
import uz.gita.notes_app.presenter.impl.SearchTasksViewModelImpl
import uz.gita.notes_app.ui.adapters.TaskAdapter
import uz.gita.notes_app.ui.dialogs.DeleteDialog
import uz.gita.notes_app.utils.extensions.gone
import uz.gita.notes_app.utils.extensions.invisible
import uz.gita.notes_app.utils.extensions.visible

// Created by Jamshid Isoqov an 9/9/2022
class SearchTaskScreen : Fragment(R.layout.screen_search_task) {

    private val viewBinding: ScreenSearchTaskBinding by viewBinding()

    private val viewModel: SearchTasksViewModel by viewModels<SearchTasksViewModelImpl>()

    private val adapter: TaskAdapter by lazy {
        TaskAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.backLiveData.observe(this, backObserver)
        viewModel.deleteLiveData.observe(this, deleteNoteObserver)
        viewModel.editLiveData.observe(this, editNoteObserver)
        viewModel.allTagsLiveData.observe(this, allTagsObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.listTasks.adapter = adapter
        viewBinding.imageBack.setOnClickListener {
            viewModel.back()
        }
        viewBinding.inputSearch.doOnTextChanged { text, _, _, _ ->
            viewModel.search(text?.toString() ?: "")
            if ((text?.toString() ?: "") == "") {
                viewBinding.listTasks.invisible()
                viewBinding.tagGroup.visible()
            } else {
                viewBinding.tagGroup.gone()
                viewBinding.listTasks.visible()
            }
        }
        viewModel.taskLiveData.observe(viewLifecycleOwner, taskObserver)

        adapter.setDeleteListener {
            viewModel.deleteClick(it)
        }
        adapter.setEditListener {
            viewModel.editClick(it)
        }
        adapter.setCheckedListener {
            viewModel.editTask(it)
        }
    }

    private val deleteNoteObserver = Observer<TaskData> {
        val dialog = DeleteDialog(requireContext())
        dialog.setDeleteListener {
            viewModel.delete(it)
        }
        dialog.show()
    }

    private val editNoteObserver = Observer<TaskData> {
        findNavController().navigate(
            SearchTaskScreenDirections.actionSearchTaskScreenToUpdateTaskScreen(
                it
            )
        )
    }
    private val backObserver = Observer<Unit> {
        findNavController().navigateUp()
    }
    private val taskObserver = Observer<List<TaskData>> {
        if (it.isEmpty()) {
            viewBinding.imagePlaceHolder.visible()
        } else viewBinding.imagePlaceHolder.invisible()
        adapter.submitList(it)
    }

    private val allTagsObserver = Observer<List<String>> {
        for (i in it.indices) {
            val chip = Chip(requireContext())
            chip.chipStrokeColor = ColorStateList.valueOf(Color.WHITE)
            chip.chipStrokeWidth = 1f
            chip.chipBackgroundColor = ColorStateList.valueOf(Color.parseColor("#252525"))
            chip.text = it[i]
            chip.setTextColor(Color.WHITE)
            chip.setOnClickListener {
                viewBinding.inputSearch.setText(chip.text.toString())
            }
            viewBinding.tagGroup.addView(chip)
        }
    }


}