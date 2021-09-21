package com.example.chapter4_challange

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView


const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val imgUserRock: ImageView by lazy { findViewById(R.id.img_user_rock) }
    private val imgUserPaper: ImageView by lazy { findViewById(R.id.img_user_paper) }
    private val imgUserScissors: ImageView by lazy { findViewById(R.id.img_user_scissors) }

    private val imgComRock: ImageView by lazy { findViewById(R.id.img_com_rock) }
    private val imgComPaper: ImageView by lazy { findViewById(R.id.img_com_paper) }
    private val imgComScissors: ImageView by lazy { findViewById(R.id.img_com_scissors) }

    private val tvResult: TextView by lazy { findViewById(R.id.tv_result) }
    private val imgRefresh: ImageView by lazy { findViewById(R.id.img_refresh) }

    private lateinit var userChoice: String
    private lateinit var comChoice: String
    private lateinit var result: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgUserRock.setOnClickListener {
            userChoice = getString(R.string.rock)
            toggleUserHand(it as ImageView)
        }

        imgUserPaper.setOnClickListener {
            userChoice = getString(R.string.paper)
            toggleUserHand(it as ImageView)
        }

        imgUserScissors.setOnClickListener {
            userChoice = getString(R.string.scissors)
            toggleUserHand(it as ImageView)
        }

        imgRefresh.setOnClickListener {
            for (hand in listOf(
                imgUserPaper,
                imgUserRock,
                imgUserScissors,
                imgComPaper,
                imgComRock,
                imgComScissors
            )) {
                hand.alpha = 1F
                hand.setBackgroundColor(Color.WHITE)
                tvResult.setText(R.string.vs)
            }
        }

    }

    private fun toggleUserHand(imgUser: ImageView) {
        Log.i(TAG, "User choose: $userChoice")
        imgUser.setBackgroundColor(Color.MAGENTA)
        imgUser.alpha = 1F
        for (hand in listOf(imgUserPaper, imgUserRock, imgUserScissors)) {
            if (hand.tag != userChoice) {
                hand.alpha = 0.6F
                hand.setBackgroundColor(Color.WHITE)
            }
        }
        getComputerHand()
    }

    private fun drawComputerChoice() {
        for (hand in listOf(imgComPaper, imgComRock, imgComScissors)) {
            if (hand.tag != comChoice) {
                hand.alpha = 0.6F
                hand.setBackgroundColor(Color.WHITE)
            } else {
                hand.setBackgroundColor(Color.MAGENTA)
                hand.alpha = 1F
            }
        }
    }

    private fun getComputerHand() {
        comChoice = listOf(
            getString(R.string.rock),
            getString(R.string.paper),
            getString(R.string.scissors)
        ).random()
        Log.i(TAG, "Computer choose: $comChoice")
        drawComputerChoice()
        getWinner()
    }

    private fun getWinner() {
        val position = mapOf<String, Int>(
            getString(R.string.scissors) to 1,
            getString(R.string.rock) to 2,
            getString(R.string.paper) to 3
        )
        val count = position[userChoice]!! - position[comChoice]!!
        if (userChoice == comChoice) {
            result = getString(R.string.draw)
            tvResult.setText(R.string.draw)
        } else if ((count == 1) || (count == -2)) {
            result = getString(R.string.win)
            tvResult.setText(R.string.win)
        } else {
            result = getString(R.string.lose)
            tvResult.setText(R.string.lose)
        }
        Log.i(TAG, "Final result: $result")
    }
}