package com.mobi.codingtest.ui.mainactivity

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.mobi.codingtest.MyApplication
import com.mobi.codingtest.R
import com.mobi.codingtest.adapter.AcronymAdapter
import com.mobi.codingtest.data.AcronymResponse
import com.mobi.codingtest.networkcall.ApiResponse
import com.mobi.codingtest.ui.BaseActivity
import com.mobi.codingtest.viewmodel.MainViewModel
import com.mobi.codingtest.viewmodel.ViewModelFactory

import javax.inject.Inject

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.mobi.codingtest.networkcall.Status
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    @BindView(R.id.edit_abbreviation)
    lateinit var editAbbreviation: EditText
    @BindView(R.id.tv_status)
    lateinit var tvStatus: TextView
    @BindView(R.id.rv_acronym)
    lateinit var rvAcronym: RecyclerView
    @BindView(R.id.progress_bar)
    lateinit var progressBar: ProgressBar

    private lateinit var viewModel: MainViewModel

    private var adapter: SectionedRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        (application as MyApplication).appComponent.doInjection(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.responseLiveData.observe(this, Observer<ApiResponse> { this.consumeResponse(it) })

        initRecyclerView()
    }

    @OnClick(R.id.btn_show_result)
    fun onViewClicked() {
        // hide keyboard
        editAbbreviation.onEditorAction(EditorInfo.IME_ACTION_DONE)

        if (!checkNetworkAvailability()) {
            showStatusText(getString(R.string.network_error))
            return
        }

        if (!isValid(editAbbreviation)) {
            return
        }

        if (!isValidInput(editAbbreviation)) {
            showStatusText(getString(R.string.incorrect_abbre))
            return
        }

        viewModel.getAbbreviationResult(getString(editAbbreviation))
    }

    private fun initRecyclerView() {
        if (adapter == null) {
            adapter = SectionedRecyclerViewAdapter()
            rvAcronym.layoutManager = LinearLayoutManager(this)
            rvAcronym.adapter = adapter
        }
    }

    private fun consumeResponse(response: ApiResponse) {
        when (response.status) {
            Status.LOADING -> showProgressBar()

            Status.SUCCESS -> {
                assert(response.data != null)
                updateRecyclerView(response.data!!)
            }

            Status.ERROR -> if (response.error != null)
                response.error.message?.let { showStatusText(it) }
        }
    }

    private fun updateRecyclerView(data: List<AcronymResponse>) {
        if (data.isEmpty()) {
            showStatusText(getString(R.string.no_result_found))
            return
        }
        showRecyclerView()
        adapter!!.removeAllSections()
        for (acronym in data[0].lfs!!) {
            adapter!!.addSection(AcronymAdapter(acronym))
        }
        rvAcronym.adapter = adapter
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        tvStatus.visibility = View.GONE
        rvAcronym.visibility = View.GONE
    }

    private fun showStatusText(text: String) {
        progressBar.visibility = View.GONE
        rvAcronym.visibility = View.GONE
        tvStatus.visibility = View.VISIBLE
        tvStatus.text = text
    }

     private fun showRecyclerView() {
        progressBar.visibility = View.GONE
        tvStatus.visibility = View.GONE
        rvAcronym.visibility = View.VISIBLE
    }
}