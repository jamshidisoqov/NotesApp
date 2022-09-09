package uz.gita.notes_app.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.notes_app.R
import uz.gita.notes_app.databinding.ScreenMainBinding
import uz.gita.notes_app.presenter.MainViewModel
import uz.gita.notes_app.presenter.impl.MainViewModelImpl
import uz.gita.notes_app.ui.dialogs.BottomMenuDialog

// Created by Jamshid Isoqov an 9/6/2022
class MainScreen : Fragment(R.layout.screen_main) {

    private val viewBinding: ScreenMainBinding by viewBinding()

    private val navController: NavController by lazy {
        findNavController()
    }

    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeOneShotObservers()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.containerNotes.setOnClickListener {
            viewModel.openNote()
        }
        viewBinding.containerTasks.setOnClickListener {
            viewModel.openTask()
        }
        viewBinding.imageSupport.setOnClickListener {
            viewModel.openOption()
        }
    }


    private fun subscribeOneShotObservers() {
        viewModel.openNoteLiveData.observe(this, openNotesObserver)
        viewModel.openTaskLiveData.observe(this, openTaskObserver)
        viewModel.openOptionDialogLiveData.observe(this, openOptionObserver)
    }

    private val openNotesObserver = Observer<Unit> {
        navController.navigate(MainScreenDirections.actionMainScreenToNotesScreen())
    }

    private val openTaskObserver = Observer<Unit> {
        navController.navigate(MainScreenDirections.actionMainScreenToTasksScreen())
    }

    private val openOptionObserver = Observer<Unit> {
        val dialog = BottomMenuDialog()
        dialog.show(childFragmentManager, "dialog")
    }


}