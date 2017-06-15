package com.github.raenar4k.khrabrovoschedule.network

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name="dep")
data class Schedule (@ElementList(inline=true, name="flight", required = false) val flights: List<FlightItem>)