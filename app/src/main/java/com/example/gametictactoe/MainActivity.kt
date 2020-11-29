package com.example.gametictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //0: Red ; 1: Yellow ; 3: Empty
    var color = 0
    var color_name = "Yellow"

    val grid = arrayOf(2,2,2,2,2,2,2,2,2)
    val winnerRow = arrayOf(arrayOf(0,1,2), arrayOf(3,4,5), arrayOf(6,7,8), arrayOf(0,3,6), arrayOf(1,4,7), arrayOf(2,5,8), arrayOf(0,4,8), arrayOf(2,4,6))

    var gameState = true

    var counting = 0

    fun markSpace(view: View){
        counting +=1

        val image:ImageView = view as ImageView

        val tagString = image.getTag().toString()
        val tagInt = tagString.toInt()

        if (grid[tagInt] == 2 && gameState){
            image.translationY = -1500F
            image.setImageResource(R.drawable.yellow)
            image.animate().translationYBy(1500F).rotation(4800F).setDuration(500)

            grid[tagInt] = color

            if (color == 0){
                image.setImageResource(R.drawable.red)
                color = 1
            } else {
                image.setImageResource(R.drawable.yellow)
                color = 0
            }
            for (winnerRow in winnerRow){
                if (grid[winnerRow[0]] == grid[winnerRow[1]] && grid[winnerRow[1]] == grid[winnerRow[2]] && grid[winnerRow[0]] != 2){
                    if (color == 0){
                        color_name = "Yellow"
                    }
                    else if (color == 1){
                        color_name = "Red"
                    }
                    winnerName.text = "${color_name} Won!"
                    winnerName.visibility = View.VISIBLE
                    resetButton.visibility = View.VISIBLE
                    gameState = false
                } else {
                    if (counting == 9){
                        winnerName.text = "Its a Draw!"
                        winnerName.visibility = View.VISIBLE
                        resetButton.visibility = View.VISIBLE
                        gameState = false
                    }
                }
            }
        }

    }

    fun resetButton(view: View){
        winnerName.visibility = View.INVISIBLE
        resetButton.visibility = View.INVISIBLE
        for (i in 0 until gridLayout.childCount) {
            val cell: ImageView = gridLayout.getChildAt(i) as ImageView
            cell.setImageDrawable(null)
        }

        for (i in grid.indices){
            grid[i] = 2
        }
        color = 0
        gameState = true
        counting = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}