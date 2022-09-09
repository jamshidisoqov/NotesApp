package uz.gita.notes_app.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.gita.notes_app.databinding.DialogColorsBinding
import uz.gita.notes_app.ui.adapters.ColorAdapter
import uz.gita.notes_app.utils.Constants

// Created by Jamshid Isoqov an 8/12/2022
class ColorDialog : BottomSheetDialogFragment() {


    private lateinit var binding: DialogColorsBinding

    private var selectedListener: ((String) -> Unit)? = null

    fun setSelectedListener(block:(String)->Unit){
        selectedListener = block
    }

    private val colorAdapter: ColorAdapter by lazy {
        ColorAdapter(Constants.generateColor())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = DialogColorsBinding.inflate(inflater, container, false)

        binding.listColors.adapter = colorAdapter

        colorAdapter.setItemClickListener {
            selectedListener?.invoke(it)
        }

        return binding.root
    }

}