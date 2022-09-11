package uz.gita.notes_app.ui.note.trash.details

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
import uz.gita.notes_app.data.models.NoteData
import uz.gita.notes_app.databinding.ScreenTrashNoteDetailsBinding
import uz.gita.notes_app.presenter.TrashNoteDetailsViewModel
import uz.gita.notes_app.presenter.impl.TrashNoteDetailsViewModelImpl
import uz.gita.notes_app.ui.dialogs.DeleteDialog
import uz.gita.notes_app.ui.dialogs.RestoreDialog

// Created by Jamshid Isoqov an 9/10/2022
class TrashDetailsNoteScreen : Fragment(R.layout.screen_trash_note_details) {

    private val viewModel: TrashNoteDetailsViewModel by viewModels<TrashNoteDetailsViewModelImpl>()

    private val viewBinding: ScreenTrashNoteDetailsBinding by viewBinding()

    private val args: TrashDetailsNoteScreenArgs by navArgs()

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeOneShotObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val trash = args.trash
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
        viewModel.deleteNoteLiveData.observe(this, deleteObserver)
        viewModel.restoreNoteLiveData.observe(this, restoreObserver)
    }


    private val backObserver = Observer<Unit> {
        navController.navigateUp()
    }

    private val deleteObserver = Observer<NoteData> {
        val dialog = DeleteDialog(requireContext())
        dialog.setDeleteListener {
            viewModel.delete(it)
            navController.navigateUp()
        }
        dialog.show()
    }

    private val restoreObserver = Observer<NoteData> {
        val dialog = RestoreDialog(requireContext())
        dialog.setRestoreListener {
            viewModel.restore(it)
            navController.navigateUp()
        }
        dialog.show()
    }

}