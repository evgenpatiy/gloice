package com.gmail.evgenpatiy.gloice.ui.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.evgenpatiy.gloice.databinding.FragmentNoMovieErrorBinding
import com.gmail.evgenpatiy.gloice.ui.fragments.AbstractFragment

class NoPreviousMovieSelectedFragment : AbstractFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentNoMovieErrorBinding.inflate(inflater).root
    }
}