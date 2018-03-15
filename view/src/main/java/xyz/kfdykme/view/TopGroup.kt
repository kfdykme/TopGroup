package xyz.kfdykme.view

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.util.Log
import android.widget.RelativeLayout

/**
 * Created by wimkf on 2018/3/12.
 */
open class TopGroup  :RelativeLayout{


    private var vhs= mutableListOf<TopViewHolder>()


    constructor(context: Context):super(context){

    }

    constructor(context:Context,attrs:AttributeSet):super(context,attrs){

    }

    constructor(context: Context,attrs: AttributeSet,defStyle:Int):super(context,attrs,defStyle){

    }



    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        layoutVH()
    }

    /*
    *
    *   return  是否有重叠, true -> 有 ;false->无
    */
    fun checkOverLap(vh:TopViewHolder):Boolean{
        for(ivh in vhs){
            if(ivh != vh){
               // Log.i("Top", vh.isOverLap(ivh).toString())
                if(vh.isOverLap(ivh) != -1){
                    return true
                }
            }
        }
        return false
    }

    fun layoutVH(){
        for(vh in vhs){
           vh.layout()
        }
    }


    fun setVHS(vhs:MutableList<TopViewHolder>){
        this.vhs = vhs
        for(vh in vhs) {
            vh.mTopGroup = this

            addView(vh.view)

        }
    }

    fun loadFromIntent(data: Intent){
        var id:Long = data!!.getLongExtra("id",0)
        var viewHolder:TopViewHolder? = null

        for(v in vhs){
            if(v.id == id)
                viewHolder = v

        }

        if(viewHolder == null) {
           // Log.i("TopGroup","load from intent fail")
            return
        }

        viewHolder!!.loadFromIntent(data!!)

        viewHolder?.layout()

    }


    fun findViewHolderById(id:Long):TopViewHolder?{
        for(vh in vhs){
            if(vh.id == id)
                return vh
        }

        return null
    }
}