package uz.gita.notes_app.ui.task.trash.details

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.notes_app.R
import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.databinding.ScreenTrashTaskDetailsBinding
import uz.gita.notes_app.presenter.TrashTaskDetailsViewModel
import uz.gita.notes_app.presenter.impl.TrashTaskDetailsViewModelImpl
import uz.gita.notes_app.ui.dialogs.DeleteDialog
import uz.gita.notes_app.ui.dialogs.RestoreDialog

// Created by Jamshid Isoqov an 9/10/2022
class TrashTaskDetailsScreen : Fragment(R.layout.screen_trash_task_details) {

    private val viewModel: TrashTaskDetailsViewModel by viewModels<TrashTaskDetailsViewModelImpl>()

    private val viewBinding: ScreenTrashTaskDetailsBinding by viewBinding()

    private val args: TrashTaskDetailsScreenArgs by navArgs()

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeOneShotObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val trash = args.task
        viewBinding.apply {
            imageBack.setOnClickListener {
                viewModel.backClick()
            }
            imageDelete.setOnClickListener {
                viewModel.deleteClick(trash)
            }
            imageRestore.setOnClickListener {
                viewModel.restoreClick(trash)
            }

            tvTitle.text = trash.title

            inputDescription.html = trash.description

            inputDescription.setEditorFontColor(Color.parseColor(trash.color))

            inputDescription.setEditorBackgroundColor(Color.parseColor("#000000"))

            inputDescription.isEnabled = false
        }
    }

    private fun subscribeOneShotObserver() {
        viewModel.backLiveData.observe(this, backObserver)
        viewModel.deleteTaskLiveData.observe(this, deleteObserver)
        viewModel.restoreTaskLiveData.observe(this, restoreObserver)
    }


    private val backObserver = Observer<Unit> {
        navController.navigateUp()
    }

    private val deleteObserver = Observer<TaskData> {
        val dialog = DeleteDialog(requireContext())
        dialog.setDeleteListener {
            viewModel.delete(it)
            navController.navigateUp()
        }
        dialog.show()
    }

    private val restoreObserver = Observer<TaskData> {
        val dialog = RestoreDialog(requireContext())
        dialog.setRestoreListener {
            viewModel.restore(it)
            navController.navigateUp()
        }
        dialog.show()
    }


}