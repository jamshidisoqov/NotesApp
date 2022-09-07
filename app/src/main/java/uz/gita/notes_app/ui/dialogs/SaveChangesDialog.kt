package uz.gita.notes_app.ui.dialogs

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import uz.gita.notes_app.databinding.DialogSaveChangesBinding

// Created by Jamshid Isoqov an 9/7/2022
class SaveChangesDialog(ctx:Context) : AlertDialog(ctx) {

    private lateinit var binding:DialogSaveChangesBinding

    private var saveListener:(()->Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogSaveChangesBinding.inflate(layoutInflater)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(binding.root)
        binding.apply {
            btnSave.setOnClickListener {
                dismiss()
                saveListener?.invoke()
            }
            btnDiscard.setOnClickListener {
                dismiss()
            }
        }
    }

    fun setSaveListener(block:()->Unit){
        saveListener = block
    }

}