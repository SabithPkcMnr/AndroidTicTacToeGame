package com.sabithpkcmnr.tictactoe

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.content.Intent


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /*
        Hello, this is simple game using Kotlin. May have so many hardcoded words,
        redundant methods, etc. Feel free to change it and experiment with the codes.

        Note: The app will crash if the game play became Tie. App don't know what to do
        when both the player went same score. So, use your logic and do the code :)

        Thanks! - Sabith Pkc Mnr
     */

    fun ButtonOnClick(view: View) {

        var selectedButton = view as Button
        var cellId = 0

        when (selectedButton.id) {

            R.id.bt1 -> cellId = 1
            R.id.bt2 -> cellId = 2
            R.id.bt3 -> cellId = 3
            R.id.bt4 -> cellId = 4
            R.id.bt5 -> cellId = 5
            R.id.bt6 -> cellId = 6
            R.id.bt7 -> cellId = 7
            R.id.bt8 -> cellId = 8
            R.id.bt9 -> cellId = 9
        }

        ManagePlayers(selectedButton, cellId)
    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var activePlayer = 1

    fun ManagePlayers(selectedButton: Button, cellId: Int) {

        if (activePlayer == 1) {
            player1.add(cellId)
            selectedButton.text = "X"
            selectedButton.setBackgroundColor(Color.RED)
            activePlayer = 2

            AutoPlay()

        } else {
            player2.add(cellId)
            selectedButton.text = "O"
            selectedButton.setBackgroundColor(Color.BLUE)
            activePlayer = 1
        }
        selectedButton.isEnabled = false
        gameWinner()
    }

    fun gameWinner() {
        var winner = -1

        //Row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        //Row 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }

        //Row 3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        //Col 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }

        //Col 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }

        //Col 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        //Cross 1 - 9
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }

        //Cross 3 - 7
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }

        //Cross 2 - 8
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }

        //Result
        if (winner == 1) {
            DisplayResult("You")
            //tabLayout.isEnabled= false

        } else if (winner == 2) {
            DisplayResult("Robot")
        }
    }

    fun DisplayResult(winnerIs: String) {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("$winnerIs Won the game")
        builder.setMessage("That was an amazing game play!")
        builder.setPositiveButton("Play Again!") { dialog, which ->
            dialog.dismiss()

            //Simple way of restarting the whole app
            val intent = intent
            finish()
            startActivity(intent)
            //Till here-----------------------------

        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    fun AutoPlay() {
        var emptyCells = ArrayList<Int>()
        for (cellId in 1..9) {
            if (!(player1.contains(cellId) || player2.contains(cellId))) {
                emptyCells.add(cellId)
            }
        }

        var randomNumber = Random()
        var randomPick = randomNumber.nextInt(emptyCells.size - 0) + 0

        var emptyCellItem = emptyCells[randomPick]
        var selectionKey: Button?
        when (emptyCellItem) {

            1 -> selectionKey = bt1
            2 -> selectionKey = bt2
            3 -> selectionKey = bt3
            4 -> selectionKey = bt4
            5 -> selectionKey = bt5
            6 -> selectionKey = bt6
            7 -> selectionKey = bt7
            8 -> selectionKey = bt8
            9 -> selectionKey = bt9
            else -> selectionKey = bt1
        }
        ManagePlayers(selectionKey, emptyCellItem)
    }
}