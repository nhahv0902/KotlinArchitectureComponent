package com.nhahv.architecturecomponent.ui.main

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.nhahv.architecturecomponent.R
import com.nhahv.architecturecomponent.data.source.local.SharePrefs
import com.nhahv.architecturecomponent.databinding.ActivityMainBinding
import com.nhahv.architecturecomponent.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = obtainViewModel()
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = mViewModel


        SharePrefs.getInstance(this).put("string", "can tien")
        val temp = SharePrefs.getInstance(this).get("string", String::class.java)
        log("$temp")

        val ob: Observer<String> = Observer { textShow -> textView.text = textShow }
        mViewModel.textShow.observe(this, ob)


        change.setOnClickListener { mViewModel.textShow.value = "hoang nhaM" }
    }
}
