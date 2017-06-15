package com.github.raenar4k.khrabrovoschedule.network

import io.reactivex.Single
import retrofit2.http.GET

interface ScheduleService {
  @GET("dep_full.xml")
  fun getDepartureSchedule(): Single<Any>
  @GET("arr_full.xml")
  fun getArrivalSchedule(): Single<Any>
}