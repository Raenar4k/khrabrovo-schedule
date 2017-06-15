package com.github.raenar4k.khrabrovoschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.raenar4k.khrabrovoschedule.network.ScheduleService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import timber.log.Timber

class MainActivity : AppCompatActivity() {

  private val scheduleService = createScheduleService()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    scheduleService.getDepartureSchedule()
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(
        {
          Timber.d("Success")
          Timber.d(it.toString())
        },
        {
          Timber.d("Error")
        })
  }

  private fun createScheduleService(): ScheduleService {
    val callAdapterFactory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())

    val logging = HttpLoggingInterceptor()
    logging.level = Level.BODY
    val httpClient = OkHttpClient.Builder().addInterceptor(logging).build()

    val retrofit: Retrofit = Retrofit.Builder()
      .addConverterFactory(SimpleXmlConverterFactory.create())
      .addCallAdapterFactory(callAdapterFactory)
      .baseUrl("http://kgd.aero/raspisanie/")
      .build()

    return retrofit.create(ScheduleService::class.java)
  }
}
