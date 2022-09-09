package uz.gita.notes_app.ui.task.add

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
import uz.gita.notes_app.data.models.TaskData
import uz.gita.notes_app.databinding.ScreenAddTasksBinding
import uz.gita.notes_app.presenter.AddTaskViewModel
import uz.gita.notes_app.presenter.impl.AddTaskViewModelImpl
import uz.gita.notes_app.ui.dialogs.ColorDialog
import uz.gita.notes_app.utils.extensions.changeType
import uz.gita.notes_app.utils.extensions.getCurrentDate

// Created by Jamshid Isoqov an 9/6/2022
class AddTaskScreen : Fragment(R.layout.screen_add_tasks) {

    private val args: AddTaskScreenArgs by navArgs()

    private val vieBinding: ScreenAddTasksBinding by viewBinding()

    private val viewModel: AddTaskViewModel by viewModels<AddTaskViewModelImpl>()

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

        val group = vieBinding.containerTypeWord

        viewModel.changeTypeLiveData.observe(viewLifecycleOwner, changeTypeObserver)
        viewModel.changesLiveData.observe(viewLifecycleOwner, changesObserver)

        vieBinding.inputDescription.setEditorFontColor(Color.WHITE)
        vieBinding.inputDescription.setPlaceholder("Type something...")

        for (i in 0 until group.childCount) {
            val type = group.getChildAt(i)
            type.tag = i + 1
            typeList.add(type)
            type.setOnClickListener {
                val ty = it.tag.toString().toInt()
                if (isDeg) {
                    vieBinding.inputDescription.changeType(ty)
                    isDeg = false
                } else if (ty == 6) {
                    isDeg = true
                }
                if (isLog) {
                    vieBinding.inputDescription.changeType(ty)
                    isLog = false
                } else if (ty == 5) {
                    isLog = true
                }
                viewModel.changeType(ty)
            }
        }

        vieBinding.inputDescription.setEditorFontColor(Color.WHITE)

        vieBinding.imageBack.setOnClickListener {
            viewModel.backClick()
        }

        vieBinding.imageSave.setOnClickListener {
            viewModel.saveData(
                TaskData(
                    category = args.category,
                    title = vieBinding.inputTitle.text.toString(),
                    description = vieBinding.inputDescription.html,
                    date = getCurrentDate(),
                    color = color
                )
            )
        }
        vieBinding.actionTxtColor.setOnClickListener {
            val dialog = ColorDialog()
            dialog.setSelectedListener {
                color = it
                vieBinding.inputDescription.setEditorFontColor(Color.parseColor(it))
            }
            dialog.show(childFragmentManager, "color")
        }
    }

    private val changeTypeObserver = Observer<Int> {
        vieBinding.inputDescription.changeType(it)
    }

    private val backObserver = Observer<Unit> {
        findNavController().navigateUp()
    }

    private val saveObserver = Observer<Unit> {
        vieBinding.inputTitle.setText("")
        vieBinding.inputDescription.html = ""
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