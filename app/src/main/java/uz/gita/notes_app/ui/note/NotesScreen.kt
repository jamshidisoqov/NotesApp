package uz.gita.notes_app.ui.note

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
import uz.gita.notes_app.data.models.NoteCategoryData
import uz.gita.notes_app.data.models.NoteData
import uz.gita.notes_app.databinding.ScreenNotesBinding
import uz.gita.notes_app.presenter.NotesViewModel
import uz.gita.notes_app.presenter.impl.NotesViewModelImpl
import uz.gita.notes_app.ui.adapters.ChipAdapter
import uz.gita.notes_app.ui.adapters.NotesAdapter
import uz.gita.notes_app.ui.dialogs.AddCategoryDialog
import uz.gita.notes_app.ui.dialogs.BottomMenuDialog
import uz.gita.notes_app.ui.dialogs.DeleteDialog
import uz.gita.notes_app.utils.extensions.invisible
import uz.gita.notes_app.utils.extensions.visible

// Created by Jamshid Isoqov an 9/6/2022
class NotesScreen : Fragment(R.layout.screen_notes) {

    private val viewBinding: ScreenNotesBinding by viewBinding()

    private val viewModel: NotesViewModel by viewModels<NotesViewModelImpl>()

    private val navController: NavController by lazy {
        findNavController()
    }

    private val chipAdapter: ChipAdapter by lazy {
        ChipAdapter()
    }

    private val adapter: NotesAdapter by lazy {
        NotesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeOneShotObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.listNotes.adapter = adapter
        viewBinding.listChips.adapter = chipAdapter
        viewBinding.imageSupport.setOnClickListener {
            viewModel.supportClick()
        }

        viewBinding.fabAddNotes.setOnClickListener {
            viewModel.addNote()
        }

        viewBinding.imageCategoryAdd.setOnClickListener {
            viewModel.addCategoryClick()
        }
        adapter.setDeleteListener {
            viewModel.deleteItemClick(it)
        }

        adapter.setEditListener {
            viewModel.editItemClick(it)
        }
        chipAdapter.setItemClickListener {
            viewModel.categoryClick(it)
        }
        subscribeUiDataObservers()
    }

    private fun subscribeOneShotObservers() {
        viewModel.addCategoryLiveData.observe(this, addCategoryObserver)
        viewModel.addNoteLiveData.observe(this, addNoteObserver)
        viewModel.editNoteLiveData.observe(this, updateNote)
        viewModel.deleteNoteLiveData.observe(this, deleteObserver)
        viewModel.searchLiveData.observe(this, searchObserver)
        viewModel.supportLiveData.observe(this, supportObserver)
    }

    private fun subscribeUiDataObservers() {
        viewModel.categoryListLiveData.observe(viewLifecycleOwner, categoryListObserver)
        viewModel.notesLiveData.observe(viewLifecycleOwner, notesByCategoryObserver)
    }

    private val searchObserver = Observer<Unit> {
        //find search
    }
    private val supportObserver = Observer<Unit> {
        val dialog = BottomMenuDialog()
        dialog.show(childFragmentManager, "notes")
    }
    private val addNoteObserver = Observer<Unit> {
        navController.navigate(NotesScreenDirections.actionNotesScreenToAddNoteScreen(chipAdapter.selectedCategoryId))
    }
    private val updateNote = Observer<NoteData> {
        navController.navigate(NotesScreenDirections.actionNotesScreenToUpdateNoteScreen(it))
    }
    private val deleteObserver = Observer<NoteData> {
        val dialog = DeleteDialog(requireContext())
        dialog.show()
        dialog.setDeleteListener {
            viewModel.deleteNote(it)
        }
    }
    private val addCategoryObserver = Observer<Unit> {
        val dialog = AddCategoryDialog(requireContext())
        dialog.setSavedListener {
            viewModel.addCategory(NoteCategoryData(0, it))
        }
        dialog.show()
    }

    @SuppressLint("ResourceType")
    private val categoryListObserver = Observer<List<NoteCategoryData>> { noteCategoryList ->
        chipAdapter.submitList(noteCategoryList)
    }
    private val notesByCategoryObserver = Observer<List<NoteData>> {
        if (it.isEmpty()) {
            viewBinding.imagePlaceHolder.visible()
        } else viewBinding.imagePlaceHolder.invisible()
        adapter.submitList(it)
    }
}