package com.example.dynamicfeature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ondemandsample.BaseSplitActivity

class onDemandActivity : BaseSplitActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_demand)
    }
}