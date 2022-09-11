package uz.gita.notes_app.ui.dialogs

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import uz.gita.notes_app.databinding.DialogDeleteBinding
import uz.gita.notes_app.databinding.DialogRestoreBinding

// Created by Jamshid Isoqov an 9/7/2022
class RestoreDialog(ctx: Context) : AlertDialog(ctx) {

    private lateinit var binding: DialogRestoreBinding

    private var restoreListener: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogRestoreBinding.inflate(layoutInflater)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(binding.root)
        binding.btnNo.setOnClickListener {
            dismiss()
        }
        binding.btnYes.setOnClickListener {
            dismiss()
            restoreListener?.invoke()
        }
    }

    fun setRestoreListener(block: () -> Unit) {
        restoreListener = block
    }

}