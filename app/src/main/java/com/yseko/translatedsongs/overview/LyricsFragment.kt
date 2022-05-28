package com.yseko.translatedsongs.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.yseko.translatedsongs.databinding.FragmentLyricsBinding


class LyricsFragment : Fragment() {
    private val viewModel: PickSongViewModel by viewModels()

    private var _binding: FragmentLyricsBinding? = null
    private val binding get() = _binding!!
    private val navigationArgs: LyricsFragmentArgs by navArgs()
    private var languageModelDownloaded = false










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

        }



    }


}