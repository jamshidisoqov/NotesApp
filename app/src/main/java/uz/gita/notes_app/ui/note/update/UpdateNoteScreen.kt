package uz.gita.notes_app.ui.note.update

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
import uz.gita.notes_app.databinding.ScreenUpdateNotesBinding
import uz.gita.notes_app.presenter.UpdateNoteViewModel
import uz.gita.notes_app.presenter.impl.UpdateNoteViewModelImpl
import uz.gita.notes_app.utils.extensions.changeType

// Created by Jamshid Isoqov an 9/6/2022
class UpdateNoteScreen : Fragment(R.layout.screen_update_notes) {

    private val binding: ScreenUpdateNotesBinding by viewBinding()

    private val viewModel: UpdateNoteViewModel by viewModels<UpdateNoteViewModelImpl>()

    private val args: UpdateNoteScreenArgs by navArgs()

    private val typeList = ArrayList<View>()

    private var isDeg = false

    private var isLog = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.backLiveData.observe(this, backObserver)
        viewModel.updateLiveData.observe(this, saveObserver)
        viewModel.messageLiveData.observe(this, messageObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val group = binding.containerTypeWord

        binding.inputTitle.setText(args.note.title)

        binding.inputDescription.html = args.note.description

        binding.inputDescription.setEditorFontColor(Color.parseColor(args.note.color))

        viewModel.changeTypeLiveData.observe(viewLifecycleOwner, changeTypeObserver)

        viewModel.changesLiveData.observe(viewLifecycleOwner, changesObserver)

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

        binding.imageBack.setOnClickListener {
            viewModel.backClick()
        }

        binding.imageSave.setOnClickListener {
            viewModel.updateNote(
                args.note.copy(
                    title = binding.inputTitle.text.toString(),
                    description = binding.inputDescription.html
                )
            )
        }

    }

    private val changeTypeObserver = Observer<Int> {
        binding.inputDescription.changeType(it)
    }

    private val backObserver = Observer<Unit> {
        findNavController().navigateUp()
    }

    private val saveObserver = Observer<Unit> {
        findNavController().navigateUp()
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