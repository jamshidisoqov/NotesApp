package uz.gita.notes_app.ui.task

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.notes_app.R
import uz.gita.notes_app.data.models.TaskCategoryData
import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.databinding.ScreenTasksBinding
import uz.gita.notes_app.presenter.TasksViewModel
import uz.gita.notes_app.presenter.impl.TasksViewModelImpl
import uz.gita.notes_app.ui.adapters.ChipTaskAdapter
import uz.gita.notes_app.ui.adapters.TaskAdapter
import uz.gita.notes_app.ui.dialogs.AddCategoryDialog
import uz.gita.notes_app.ui.dialogs.BottomMenuDialog
import uz.gita.notes_app.ui.dialogs.DeleteDialog
import uz.gita.notes_app.utils.extensions.invisible
import uz.gita.notes_app.utils.extensions.visible

// Created by Jamshid Isoqov an 9/6/2022
class TasksScreen : Fragment(R.layout.screen_tasks) {

    private val viewModel: TasksViewModel by viewModels<TasksViewModelImpl>()

    private val viewBinding: ScreenTasksBinding by viewBinding()

    private val navController: NavController by lazy {
        findNavController()
    }


    private val chipAdapter: ChipTaskAdapter by lazy {
        ChipTaskAdapter()
    }

    private val taskAdapter: TaskAdapter by lazy {
        TaskAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeOneShotObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.listTask.adapter = taskAdapter
        viewBinding.listChips.adapter = chipAdapter
        viewBinding.imageSupport.setOnClickListener {
            viewModel.supportClick()
        }

        viewBinding.fabAddTasks.setOnClickListener {
            viewModel.addTask()
        }

        viewBinding.imageCategoryAdd.setOnClickListener {
            viewModel.addCategoryClick()
        }

        taskAdapter.setEditListener {
            viewModel.editItemClick(it)
        }
        chipAdapter.setItemClickListener {
            viewModel.categoryClick(it)
        }

        taskAdapter.setCheckedListener {
            viewModel.editItem(it)
        }
        subscribeUiDataObservers()
    }

    private fun subscribeOneShotObservers() {
        viewModel.addCategoryLiveData.observe(this, addCategoryObserver)
        viewModel.addTaskLiveData.observe(this, addTaskObserver)
        viewModel.editTaskLiveData.observe(this, editTask)
        viewModel.deleteTaskLiveData.observe(this, deleteObserver)
        viewModel.searchLiveData.observe(this, searchObserver)
        viewModel.supportLiveData.observe(this, supportObserver)
    }

    private fun subscribeUiDataObservers() {
        viewModel.categoryListLiveData.observe(viewLifecycleOwner, categoryListObserver)
        viewModel.taskLiveData.observe(viewLifecycleOwner, tasksByCategory)
    }

    private val searchObserver = Observer<Unit> {
        //find search
    }
    private val supportObserver = Observer<Unit> {
        val dialog = BottomMenuDialog()
        dialog.show(childFragmentManager, "tasks")
    }
    private val addTaskObserver = Observer<Unit> {
        navController.navigate(TasksScreenDirections.actionTasksScreenToAddTaskScreen(chipAdapter.selectedCategoryId))
    }
    private val editTask = Observer<TaskData> {
        navController.navigate(TasksScreenDirections.actionTasksScreenToUpdateTaskScreen(it))
    }
    private val deleteObserver = Observer<TaskData> {
        val dialog = DeleteDialog(requireContext())
        dialog.show()
        dialog.setDeleteListener {
            viewModel.deleteTask(it)
        }
    }
    private val addCategoryObserver = Observer<Unit> {
        val dialog = AddCategoryDialog(requireContext())
        dialog.setSavedListener {
            viewModel.addCategory(TaskCategoryData(0, it))
        }
        dialog.show()
    }

    @SuppressLint("ResourceType")
    private val categoryListObserver = Observer<List<TaskCategoryData>> { tasks ->
        chipAdapter.submitList(tasks)
    }
    private val tasksByCategory = Observer<List<TaskData>> {
        if (it.isEmpty()) {
            viewBinding.imagePlaceHolder.visible()
        } else viewBinding.imagePlaceHolder.invisible()
        taskAdapter.submitList(it)
    }


}