package com.example.rosseti.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rosseti.R
import com.example.rosseti.databinding.FragmentProblemsBinding

class ProblemsFragment : Fragment() {

    private var _binding: FragmentProblemsBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var adapter: ProblemsAdapter

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
        adapter = ProblemsAdapter {
            findNavController().navigate(R.id.go_to_full_info)
        }
        binding.ideasRecyclerView.adapter = adapter
    }
}