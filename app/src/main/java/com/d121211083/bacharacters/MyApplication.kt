package com.d121211083.bacharacters

import android.app.Application
import com.d121211083.bacharacters.data.AppContainer
import com.d121211083.bacharacters.data.DefaultAppContainer

class MyApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}