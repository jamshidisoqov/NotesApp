package uz.gita.task_app.domain.presenter.main

import androidx.lifecycle.LiveData

// Created by Jamshid Isoqov an 8/12/2022
interface BottomMenuViewModel {

    val supportUsLiveData: LiveData<Unit>

    val shareAppLiveData: LiveData<Unit>

    val aboutUsLiveData: LiveData<Unit>

    fun supportClick()

    fun shareClick()

    fun aboutClick()

}