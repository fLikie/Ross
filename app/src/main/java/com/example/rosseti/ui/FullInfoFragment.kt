package com.example.rosseti.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rosseti.databinding.FragmentFullInfoBinding

class FullInfoFragment : Fragment() {

    private var _binding: FragmentFullInfoBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var adapter: FullInfoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFullInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FullInfoAdapter()
        binding.commentsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.commentsRecyclerView.adapter = adapter
        binding.info.moreInfoButton.text = "Написать комментарий"
    }
}