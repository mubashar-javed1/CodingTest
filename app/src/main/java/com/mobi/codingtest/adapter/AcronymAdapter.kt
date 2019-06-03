package com.mobi.codingtest.adapter


import android.view.View
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import com.mobi.codingtest.R
import com.mobi.codingtest.data.Acronym

import butterknife.BindView
import butterknife.ButterKnife
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection

class AcronymAdapter(private val acronym: Acronym) : StatelessSection(SectionParameters.builder()
        .itemResourceId(R.layout.item_acronym)
        .headerResourceId(R.layout.item_acronym_header)
        .build()) {

    override fun getContentItemsTotal(): Int {
        return this.acronym.vars!!.size
    }

    override fun getHeaderViewHolder(view: View): RecyclerView.ViewHolder {
        return MyHeaderViewHolder(view)
    }

    override fun getItemViewHolder(view: View): RecyclerView.ViewHolder {
        return MyItemViewHolder(view)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
        val itemHolder = holder as MyHeaderViewHolder?
        itemHolder!!.tvLf.text = acronym.lf
        itemHolder.tvFreq.text = acronym.freq.toString()
        itemHolder.tvSince.text = acronym.since.toString()
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemHolder = holder as MyItemViewHolder
        itemHolder.tvLf.text = acronym.vars!![position].lf
        itemHolder.tvFreq.text = acronym.vars!![position].freq.toString()
        itemHolder.tvSince.text = acronym.vars!![position].since.toString()
    }


    internal inner class MyHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.tv_lf)
        lateinit var tvLf: TextView
        @BindView(R.id.tv_freq)
        lateinit var tvFreq: TextView
        @BindView(R.id.tv_since)
        lateinit var tvSince: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }


    internal inner class MyItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.tv_lf)
        lateinit var tvLf: TextView
        @BindView(R.id.tv_freq)
        lateinit var tvFreq: TextView
        @BindView(R.id.tv_since)
        lateinit var tvSince: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}