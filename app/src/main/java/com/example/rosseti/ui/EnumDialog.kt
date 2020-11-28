package com.example.rosseti.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.rosseti.R
import com.example.rosseti.databinding.DialogAddEnumBinding

class EnumDialog : DialogFragment() {

    private var _binding: DialogAddEnumBinding? = null
    private val binding
        get() = _binding!!
    private val listener: Listener?
        get() = (parentFragment as? Listener)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddEnumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonAdd.setOnClickListener {
            if (binding.edittextName.text.isNotBlank() && binding.edittextSum.text.isNotBlank()) {
                listener?.onEnumAdded(binding.edittextName.text.toString(), binding.edittextSum.text.toString().toLong())
                dismissAllowingStateLoss()
            } else {
                binding.edittextName.setHintTextColor(resources.getColor(R.color.red))
                binding.edittextSum.setHintTextColor(resources.getColor(R.color.red))
            }
        }
    }

    interface Listener {
        fun onEnumAdded(description: String, sum: Long)
    }
}