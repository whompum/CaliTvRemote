package whompum.com.calitvremote.Rentals

import whompum.com.calitvremote.TvItem.Adapter.TvItemAdapterBinder

data class RentalsItem(val title: String,
                  val threeHoursPrice: String?,
                  val fullDayPrice: String?): TvItemAdapterBinder