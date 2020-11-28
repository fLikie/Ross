package com.example.rosseti.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.rosseti.ui.IdeasFragment
import com.example.rosseti.ui.ProblemsFragment

class ForumFragmentsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> IdeasFragment()
            else -> ProblemsFragment()
        }
    }
}