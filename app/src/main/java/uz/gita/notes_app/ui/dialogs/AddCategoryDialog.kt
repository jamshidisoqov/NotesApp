package uz.gita.notes_app.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import uz.gita.notes_app.databinding.DialogAddCategoryBinding

// Created by Jamshid Isoqov an 9/7/2022
class AddCategoryDialog(ctx: Context) : Dialog(ctx) {

    private lateinit var binding: DialogAddCategoryBinding

    private var savedListener: ((String) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogAddCategoryBinding.inflate(layoutInflater)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(binding.root)
        binding.btnDiscard.setOnClickListener {
            dismiss()
        }
        binding.btnSave.setOnClickListener {
            val name = binding.inputCategoryName.text.toString()
            if (name.isNotEmpty()) {
                dismiss()
                savedListener?.invoke(name)
            } else {
                binding.inputCategoryName.error = "Name must be entered"
            }
        }
    }

    fun setSavedListener(block: (String) -> Unit) {
        savedListener = block
    }

}