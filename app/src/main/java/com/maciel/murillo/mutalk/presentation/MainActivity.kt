package com.maciel.murillo.mutalk.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.maciel.murillo.gif_finder.presentation.GifFinderViewModel
import com.maciel.murillo.mutalk.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val gifFinderViewModel: GifFinderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gifFinderViewModel.getTrendingGifs()
    }
}