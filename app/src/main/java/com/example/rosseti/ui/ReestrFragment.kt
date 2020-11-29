package com.example.rosseti.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rosseti.R
import com.example.rosseti.databinding.FragmentProblemsBinding

class ReestrFragment : Fragment() {
    private var _binding: FragmentProblemsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProblemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ideasRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.ideasRecyclerView.adapter = ProblemsAdapter()
    }
}