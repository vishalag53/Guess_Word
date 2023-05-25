package com.example.guessword.screens.score

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
import com.example.guessword.R
import com.example.guessword.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {

    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentScoreBinding>(inflater,R.layout.fragment_score,container,false)

        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()

        viewModelFactory = ScoreViewModelFactory(scoreFragmentArgs.score)
        viewModel = ViewModelProvider(this,viewModelFactory)
            .get(ScoreViewModel::class.java)

        binding.scoreVewModel = viewModel

        binding.setLifecycleOwner(this)

        viewModel.eventPlayAgain.observe(viewLifecycleOwner, Observer {  playAgain ->
            if(playAgain){
                findNavController().navigate(ScoreFragmentDirections.actionRestart())
                viewModel.onPlayAgainComplete()
            }
        })
        return binding.root
    }

}