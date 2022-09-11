package uz.gita.notes_app.ui.task.trash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.notes_app.R
import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.databinding.ScreenTrashTaskBinding
import uz.gita.notes_app.presenter.TrashTaskViewModel
import uz.gita.notes_app.presenter.impl.TrashTaskViewModelImpl
import uz.gita.notes_app.ui.adapters.TrashTaskAdapter
import uz.gita.notes_app.ui.dialogs.DeleteDialog

// Created by Jamshid Isoqov an 9/10/2022
class TrashTaskScreen : Fragment(R.layout.screen_trash_task) {

    private val viewModel: TrashTaskViewModel by viewModels<TrashTaskViewModelImpl>()

    private val viewBinding: ScreenTrashTaskBinding by viewBinding()

    private val adapter: TrashTaskAdapter by lazy {
        TrashTaskAdapter()
    }

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeOneShotObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.imageCleanTrash.setOnClickListener {
            viewModel.deleteAllTasks()
        }
        viewBinding.listTrashes.adapter = adapter

        adapter.setDeleteListener {
            viewModel.taskDeleteClick(it)
        }
        adapter.setEditListener {
            viewModel.taskItemClick(it)
        }

        viewBinding.imageBack.setOnClickListener {
            viewModel.backClicked()
        }
        subscribeUiDataObserver()
    }

    private fun subscribeOneShotObserver() {
        viewModel.backLiveData.observe(this, backObserver)
        viewModel.deleteTaskLiveData.observe(this, deleteObserver)
        viewModel.deleteAllTaskLiveData.observe(this, deleteAllNotesObserver)
        viewModel.openDetailsTaskLiveData.observe(this, openNoteDetailsObserver)
    }

    private fun subscribeUiDataObserver() {
        viewModel.allTrashTasksLiveData.observe(viewLifecycleOwner, allTrashListObserver)
    }

    private val backObserver = Observer<Unit> {
        navController.navigateUp()
    }

    private val deleteAllNotesObserver = Observer<Unit> {
        val dialog = DeleteDialog(requireContext())
        dialog.setDeleteListener {
            viewModel.deleteAllTasks()
        }
        dialog.show()
    }

    private val deleteObserver = Observer<TaskData> {
        val dialog = DeleteDialog(requireContext())
        dialog.setDeleteListener {
            viewModel.deleteTask(it)
        }
        dialog.show()
    }

    private val openNoteDetailsObserver = Observer<TaskData> {
        navController.navigate(
            TrashTaskScreenDirections.actionTrashTaskScreenToTrashTaskDetailsScreen(
                it
            )
        )
    }

    private val allTrashListObserver = Observer<List<TaskData>> {
        adapter.submitList(it)
    }

}