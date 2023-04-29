package com.example.mywords.rx

import io.reactivex.Scheduler

//In the sake of testing
interface ISchedulerProvider {

    fun ui(): Scheduler

    fun io(): Scheduler
}
