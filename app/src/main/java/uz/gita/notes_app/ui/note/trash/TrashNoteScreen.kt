package uz.gita.notes_app.ui.note.trash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.notes_app.R
import uz.gita.notes_app.data.models.NoteData
import uz.gita.notes_app.databinding.ScreenTrashNoteBinding
import uz.gita.notes_app.presenter.TrashNoteViewModel
import uz.gita.notes_app.presenter.impl.TrashNoteViewModelImpl
import uz.gita.notes_app.ui.adapters.NotesAdapter
import uz.gita.notes_app.ui.dialogs.DeleteDialog

// Created by Jamshid Isoqov an 9/10/2022
class TrashNoteScreen : Fragment(R.layout.screen_trash_note) {

    private val viewModel: TrashNoteViewModel by viewModels<TrashNoteViewModelImpl>()

    private val viewBinding: ScreenTrashNoteBinding by viewBinding()

    private val navController: NavController by lazy {
        findNavController()
    }

    private val adapter: NotesAdapter by lazy {
        NotesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeOneShotObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.imageCleanTrash.setOnClickListener {
            viewModel.deleteAllNotesClick()
        }
        viewBinding.listTrashes.adapter = adapter

        adapter.setDeleteListener {
            viewModel.noteDeleteClick(it)
        }
        adapter.setEditListener {
            viewModel.noteItemClick(it)
        }

        viewBinding.imageBack.setOnClickListener {
            viewModel.backClicked()
        }
        subscribeUiDataObserver()
    }

    private fun subscribeOneShotObserver() {
        viewModel.backLiveData.observe(this, backObserver)
        viewModel.deleteLiveData.observe(this, deleteObserver)
        viewModel.deleteAllNotesLiveData.observe(this, deleteAllNotesObserver)
        viewModel.openDetailsTrashLiveData.observe(this, openNoteDetailsObserver)
    }

    private fun subscribeUiDataObserver() {
        viewModel.allTrashNotesLiveData.observe(viewLifecycleOwner, allTrashListObserver)
    }

    private val backObserver = Observer<Unit> {
        navController.navigateUp()
    }

    private val deleteAllNotesObserver = Observer<Unit> {
        val dialog = DeleteDialog(requireContext())
        dialog.setDeleteListener {
            viewModel.deleteAllNotes()
        }
        dialog.show()
    }

    private val deleteObserver = Observer<NoteData> {
        val dialog = DeleteDialog(requireContext())
        dialog.setDeleteListener {
            viewModel.deleteNote(it)
        }
        dialog.show()
    }

    private val openNoteDetailsObserver = Observer<NoteData> {
        navController.navigate(
            TrashNoteScreenDirections.actionTrashNoteScreenToTrashDetailsNoteScreen(
                it
            )
        )
    }

    private val allTrashListObserver = Observer<List<NoteData>> {
        adapter.submitList(it)
    }

}