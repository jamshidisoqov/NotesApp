package uz.gita.notes_app.ui.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.notes_app.R
import uz.gita.notes_app.databinding.ScreenAboutBinding
import uz.gita.notes_app.presenter.AboutViewModel
import uz.gita.notes_app.presenter.impl.AboutViewModelImpl

// Created by Jamshid Isoqov an 9/11/2022
class AboutScreen : Fragment(R.layout.screen_about) {

    private val viewModel: AboutViewModel by viewModels<AboutViewModelImpl>()

    private val viewBinding: ScreenAboutBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.backLiveData.observe(this, backLiveData)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.imgClose.setOnClickListener {
            viewModel.back()
        }
    }

    private val backLiveData = Observer<Unit> {
        findNavController().navigateUp()
    }
}