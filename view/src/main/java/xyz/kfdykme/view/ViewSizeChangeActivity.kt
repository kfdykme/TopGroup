package xyz.kfdykme.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.SeekBar

import kotlinx.android.synthetic.main.activity_view_size_change.*

class ViewSizeChangeActivity : AppCompatActivity() {

    lateinit var vh:TopViewHolder

    companion object {
        val VIEW_SIZE_CHANGE_REQUEST = 22
        val VIEW_SIZE_CHANGE_RESULT = 23

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_size_change)

        initView(intent)
       
    }


    
    
    override fun onBackPressed() {

        var intent = Intent()
        vh.toIntent(intent)

        setResult(VIEW_SIZE_CHANGE_RESULT,intent)
        super.onBackPressed()
    }

    fun initView(intent:Intent){



        var topGroup = findViewById<TopGroup>(R.id.topGroup)

        var vhs = mutableListOf<TopViewHolder>()
        
        vh = TopViewHolder(
                layoutInflater.inflate(
                        R.layout.viewholder
                        ,topGroup
                        ,false
                )
        )

        vh.loadFromIntent(intent)
        vhs.add(vh)

        topGroup.setVHS(vhs)

        vSeekBar.progress = vh.height
        hSeekBar.progress = vh.width

        vSeekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                vh.height = progress
                vh.onUpdateXYWH()
                vh.layout()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        hSeekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                vh.width = progress
                vh.onUpdateXYWH()
                vh.layout()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })



    }
}
