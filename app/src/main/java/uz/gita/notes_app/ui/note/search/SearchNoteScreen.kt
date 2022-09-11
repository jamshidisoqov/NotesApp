package uz.gita.notes_app.ui.note.search

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
import kotlinx.coroutines.FlowPreview
import uz.gita.notes_app.R
import uz.gita.notes_app.data.models.NoteData
import uz.gita.notes_app.databinding.ScreenSearchNoteBinding
import uz.gita.notes_app.presenter.SearchNotesViewModel
import uz.gita.notes_app.presenter.impl.SearchNotesViewModelImpl
import uz.gita.notes_app.ui.adapters.NotesAdapter
import uz.gita.notes_app.ui.dialogs.DeleteDialog
import uz.gita.notes_app.utils.extensions.gone
import uz.gita.notes_app.utils.extensions.invisible
import uz.gita.notes_app.utils.extensions.visible

// Created by Jamshid Isoqov an 9/9/2022
@FlowPreview
class SearchNoteScreen : Fragment(R.layout.screen_search_note) {

    private val viewModel: SearchNotesViewModel by viewModels<SearchNotesViewModelImpl>()

    private val viewBinding: ScreenSearchNoteBinding by viewBinding()

    private val adapter: NotesAdapter by lazy {
        NotesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.backLiveData.observe(this, backObserver)
        viewModel.deleteLiveData.observe(this, deleteNoteObserver)
        viewModel.editLiveData.observe(this, editNoteObserver)
        viewModel.allTagsLiveData.observe(this, allTagsObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.listNotes.adapter = adapter
        viewBinding.imageBack.setOnClickListener {
            viewModel.back()
        }

        viewBinding.inputSearch.doOnTextChanged { text, _, _, _ ->
            viewModel.search(text?.toString() ?: "")
            if ((text?.toString() ?: "") == "") {
                viewBinding.listNotes.invisible()
                viewBinding.tagGroup.visible()
            } else {
                viewBinding.tagGroup.gone()
                viewBinding.listNotes.visible()
            }
        }
        viewModel.notesLiveData.observe(viewLifecycleOwner, notesObserver)
        adapter.setDeleteListener {
            viewModel.deleteClick(it)
        }
        adapter.setEditListener {
            viewModel.editClick(it)
        }
    }

    private val deleteNoteObserver = Observer<NoteData> {
        val dialog = DeleteDialog(requireContext())
        dialog.setDeleteListener {
            viewModel.delete(it)
        }
        dialog.show()
    }

    private val editNoteObserver = Observer<NoteData> {
        findNavController().navigate(
            SearchNoteScreenDirections.actionSearchNoteScreenToUpdateNoteScreen(
                it
            )
        )
    }
    private val backObserver = Observer<Unit> {
        findNavController().navigateUp()
    }
    private val notesObserver = Observer<List<NoteData>> {
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