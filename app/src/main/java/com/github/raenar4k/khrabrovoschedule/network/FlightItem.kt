package com.github.raenar4k.khrabrovoschedule.network

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "flight", strict = false)
data class FlightItem(
  @Element(name = "DATE") val date: String = "",
  @Element(name = "FLIGHT") val flight: String = "",
  @Element(name = "DTIME") val scheduledTime: String = "",
  @Element(name = "ROUTE", required = false) val routeRu: String = "",
  @Element(name = "ROUTE_E", required = false) val routeEn: String = "",
  @Element(name = "REM_R", required = false) val statusRu: String = "",
  @Element(name = "REM_E", required = false) val statusEn: String = "",
  @Element(name = "ACT_TIME", required = false) val actualTime: String = ""
) {
  constructor(): this("","","","","","","","")
}