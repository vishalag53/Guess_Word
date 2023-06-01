package com.vishalag53.guessword.screens.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vishalag53.guessword.R
import com.vishalag53.guessword.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {

    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentScoreBinding>(inflater,
            R.layout.fragment_score,container,false)

        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()

        viewModelFactory = ScoreViewModelFactory(scoreFragmentArgs.score)
        viewModel = ViewModelProvider(this,viewModelFactory)[ScoreViewModel::class.java]

        binding.scoreVewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.eventPlayAgain.observe(viewLifecycleOwner, Observer {  playAgain ->
            if(playAgain){
                findNavController().navigate(ScoreFragmentDirections.actionRestart())
                viewModel.onPlayAgainComplete()
            }
        })
        return binding.root
    }

}