package com.yseko.translatedsongs.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yseko.translatedsongs.databinding.FragmentPickSongBinding
import com.yseko.translatedsongs.network.TrackObj


class PickSongFragment : Fragment() {
    private var _binding: FragmentPickSongBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PickSongViewModel by viewModels()

    private var artist: String = ""
    private var track: String = ""
    private var trackList: List<TrackObj> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPickSongBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TrackListAdapter {
            val action = PickSongFragmentDirections.actionPickSongFragmentToLyricsFragment(it.track.commonTrackId, it.track.artistName, it.track.trackName)
            this.findNavController().navigate(action)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter

        binding.searchBtn.setOnClickListener{
            track = binding.inputPickSong.text.toString()
            artist = binding.inputPickArtist.text.toString()
            viewModel.searchTracks(track, artist)
//            trackList = viewModel.responseTracks.value.message.body.trackList
//            adapter.notifyDataSetChanged()
        }
        viewModel.trackList.observe(this.viewLifecycleOwner){trackObjs->
            trackObjs.let{
                adapter.submitList(it)
            }
        }


    }

}