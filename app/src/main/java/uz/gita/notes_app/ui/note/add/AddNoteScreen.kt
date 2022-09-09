package uz.gita.notes_app.ui.note.add

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.notes_app.R
import uz.gita.notes_app.data.models.NoteData
import uz.gita.notes_app.databinding.ScreenAddNotesBinding
import uz.gita.notes_app.presenter.AddNoteViewModel
import uz.gita.notes_app.presenter.impl.AddNoteViewModelImpl
import uz.gita.notes_app.ui.dialogs.ColorDialog
import uz.gita.notes_app.utils.extensions.changeType
import uz.gita.notes_app.utils.extensions.getCurrentDate

// Created by Jamshid Isoqov an 9/6/2022
class AddNoteScreen : Fragment(R.layout.screen_add_notes) {

    private val viewModel: AddNoteViewModel by viewModels<AddNoteViewModelImpl>()

    private val binding: ScreenAddNotesBinding by viewBinding()

    private val args: AddNoteScreenArgs by navArgs()

    private val typeList = ArrayList<View>()

    private var color = "#FFFFFF"

    private var isDeg = false

    private var isLog = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.backLiveData.observe(this, backObserver)
        viewModel.saveLiveData.observe(this, saveObserver)
        viewModel.messageLiveData.observe(this, messageObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val group = binding.containerTypeWord

        viewModel.changeTypeLiveData.observe(viewLifecycleOwner, changeTypeObserver)
        viewModel.changesLiveData.observe(viewLifecycleOwner, changesObserver)

        binding.inputDescription.setEditorFontColor(Color.WHITE)
        binding.inputDescription.setPlaceholder("Type something...")

        for (i in 0 until group.childCount) {
            val type = group.getChildAt(i)
            type.tag = i + 1
            typeList.add(type)
            type.setOnClickListener {
                val ty = it.tag.toString().toInt()
                if (isDeg) {
                    binding.inputDescription.changeType(ty)
                    isDeg = false
                } else if (ty == 6) {
                    isDeg = true
                }
                if (isLog) {
                    binding.inputDescription.changeType(ty)
                    isLog = false
                } else if (ty == 5) {
                    isLog = true
                }
                viewModel.changeType(ty)
            }
        }

        binding.inputDescription.setEditorFontColor(Color.WHITE)

        binding.imageBack.setOnClickListener {
            viewModel.backClick()
        }

        binding.imageSave.setOnClickListener {
            viewModel.saveData(
                NoteData(
                    category = args.category,
                    title = binding.inputTitle.text.toString(),
                    description = binding.inputDescription.html,
                    date = getCurrentDate(),
                    color = color
                )
            )
        }
        binding.actionTxtColor.setOnClickListener {
            val dialog = ColorDialog()
            dialog.setSelectedListener {
                color = it
                binding.inputDescription.setEditorFontColor(Color.parseColor(it))
            }
            dialog.show(childFragmentManager, "color")
        }
    }

    private val changeTypeObserver = Observer<Int> {
        binding.inputDescription.changeType(it)
    }

    private val backObserver = Observer<Unit> {
        findNavController().navigateUp()
    }

    private val saveObserver = Observer<Unit> {
        binding.inputTitle.setText("")
        binding.inputDescription.html = ""
    }

    private val changesObserver = Observer<Pair<Int, Boolean>> {
        if (it.first > 1) {
            typeList[it.first].apply {
                if (it.second) {
                    setBackgroundResource(R.drawable.bg_background)
                } else {
                    setBackgroundColor(Color.TRANSPARENT)
                }
            }
        }
    }

    private val messageObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

}