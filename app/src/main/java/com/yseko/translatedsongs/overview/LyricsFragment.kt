package com.yseko.translatedsongs.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.yseko.translatedsongs.databinding.FragmentLyricsBinding


class LyricsFragment : Fragment() {
    private val viewModel: PickSongViewModel by viewModels()

    private var _binding: FragmentLyricsBinding? = null
    private val binding get() = _binding!!
    private val navigationArgs: LyricsFragmentArgs by navArgs()

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentLyricsBinding.inflate(inflater, container,false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}