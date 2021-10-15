package com.bee_studio.learn_recycler_view.BasicViewModel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BasicViewModel : ViewModel() {

    /*
    * Ici un systeme de timer a ete cree afin de mettre
    *  a jour les valeurs du Ui Controller grace au LiveData automatiquement
    * */
    private lateinit var timer: CountDownTimer
    private val _second = MutableLiveData<Int>()
    var finished = MutableLiveData<Boolean>()
    //Ici on utilise une valeur get pour recuperer
    // la valeur prive du live Data qui ne doit pas etre
    // modifier par une autre classe
    fun seconds():LiveData<Int>{
        return _second
    }

    fun startTimer() {
        timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                val timeLeft = p0 / 1000
                _second.value = timeLeft.toInt()
            }

            override fun onFinish() {
                finished.value=true;
            }

        }.start()
    }

    fun stopTimer() {
        timer.cancel()
    }

}