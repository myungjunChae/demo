package com.ono.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.ono.simple.Model.Test
import com.ono.simple.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {

    private val vm: MainViewModel by inject()
    private var i = 1

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        startKoin { injectionFeature() }

        binding.vm = vm

        binding.testButton.setOnClickListener {
            if (i > 3) i = 1
            vm.get(i++)
        }

        vm.liveData.observe(this, Observer { updateLiveData(it) })
    }

    private fun updateLiveData(resource: Test) {
        binding.testButton.text = resource.test
    }
}
