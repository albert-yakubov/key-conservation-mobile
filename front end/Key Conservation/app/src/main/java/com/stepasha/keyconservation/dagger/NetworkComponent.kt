package com.stepasha.keyconservation.dagger

import com.stepasha.keyconservation.MainActivity
import dagger.Component
import javax.inject.Singleton

//tie the miodule and where will you use it
@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent {

    fun inject(activity: MainActivity?)

}