package com.lwseasy.lwsprofile.view.experience

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lwseasy.lwsprofile.R
import com.lwseasy.lwsprofile.databinding.AdapterExperienceItemBinding

class ExperienceAdapter(
    private val context: Context?,
    private val contentList: List<String> = ArrayList()
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)

        val view = (DataBindingUtil.inflate(
            inflater,
            R.layout.adapter_experience_item,
            parent,
            false
        ) as AdapterExperienceItemBinding).root

        return ContentHolder(view)
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (context == null)
            return

        when (holder) {
            is ContentHolder -> {
                val binding =
                    DataBindingUtil.getBinding<AdapterExperienceItemBinding>(holder.itemView)
            }
        }
    }

    inner class ContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}