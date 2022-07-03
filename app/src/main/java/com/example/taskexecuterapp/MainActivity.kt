package com.example.taskexecuterapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskexecuterapp.databinding.ActivityMainBinding
import com.example.taskexecuterapp.presentation.MainViewModel
import com.example.taskexecuterapp.presentation.ScreenAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private val screenAdapter by lazy { ScreenAdapter(mutableListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        setupClicking()
        viewModel.liveData.observe(this) {
            screenAdapter.updateList(it)
        }

    }

    private fun initRecycler() {

        with(binding.recycler) {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = screenAdapter
        }
    }

    private fun setupClicking() {
        with(binding) {
            taskOne.setOnClickListener {
                taskOne.setTextClickableColor()
                taskTwo.setTextUnClickableColor()
                taskThree.setTextUnClickableColor()
                taskFour.setTextUnClickableColor()
                viewModel.startWorker(this@MainActivity, taskOne.text.toString())
            }

            taskTwo.setOnClickListener {
                taskOne.setTextUnClickableColor()
                taskTwo.setTextClickableColor()
                taskThree.setTextUnClickableColor()
                taskFour.setTextUnClickableColor()
                viewModel.startWorker(this@MainActivity, taskTwo.text.toString())
            }

            taskThree.setOnClickListener {
                taskOne.setTextUnClickableColor()
                taskTwo.setTextUnClickableColor()
                taskThree.setTextClickableColor()
                taskFour.setTextUnClickableColor()
                viewModel.startWorker(this@MainActivity, taskThree.text.toString())
            }

            taskFour.setOnClickListener {
                taskOne.setTextUnClickableColor()
                taskTwo.setTextUnClickableColor()
                taskThree.setTextUnClickableColor()
                taskFour.setTextClickableColor()
                viewModel.startWorker(this@MainActivity, taskFour.text.toString())
            }

        }
    }
}

fun TextView.setTextClickableColor() {
    setBackgroundColor(Color.BLACK)
    setTextColor(Color.WHITE)
}

fun TextView.setTextUnClickableColor() {
    setBackgroundColor(Color.WHITE)
    setTextColor(Color.BLACK)
}