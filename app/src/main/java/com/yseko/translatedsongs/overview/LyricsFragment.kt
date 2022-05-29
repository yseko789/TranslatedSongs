package com.yseko.translatedsongs.overview

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.yseko.translatedsongs.R
import com.yseko.translatedsongs.databinding.FragmentLyricsBinding


class LyricsFragment : Fragment() {
    private val viewModel: PickSongViewModel by viewModels()

    private var _binding: FragmentLyricsBinding? = null
    private val binding get() = _binding!!
    private val navigationArgs: LyricsFragmentArgs by navArgs()










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


        binding.btnTranslate.setOnClickListener{
            viewModel.getLyrics(navigationArgs.commonTrackId)
//            binding.lyricText.text = viewModel.temp.value.toString()

//            viewModel.temp.value.toString().dropLast(10)
//            dropLastWhile { !(it.equals("."))}
//            binding.lyricsDescription.text = getString(R.string.descriptionLyrics)

        }
        binding.btnAnswer.setOnClickListener{
            openDialogToSong()
        }
    }

    private fun openDialogToSong(){
        val dialogMessage = navigationArgs.qTrack + "\n" + navigationArgs.qArtist
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        alertDialog.setTitle(R.string.dialogTitle)
            .setMessage(dialogMessage)

        alertDialog.show()

    }


}