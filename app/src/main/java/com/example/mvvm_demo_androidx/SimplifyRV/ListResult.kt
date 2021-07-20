package com.example.mvvm_demo_androidx.SimplifyRV

import com.google.gson.annotations.SerializedName

data class RowsItem(val date: Long = 0,
                    val leagueOdds: List<LeagueOddsItem>?)


data class ListResult(val msg: String = "",
                      val total: Int = 0,
                      val code: Int = 0,
                      val success: Boolean = false,
                      val rows: List<RowsItem>?)


data class Odds(
    @SerializedName("EPS")
    val eps: List<EPSItem>?)

data class EpsOdds(
    val matchInfo: MatchInfo?,
    val eps: EPSItem?)


data class League(val name: String = "",
                  val id: String = "")


data class LeagueOddsItem(
    var date: Long = 0,
    val league: League?,
    val matchOdds: List<MatchOddsItem>?
)

data class MatchInfo(val leagueName: String = "",
                     val awayName: String = "",
                     val leagueId: String = "",
                     val homeName: String = "",
                     val startTime: Long = 0,
                     val id: String = "",
                     val status: Int = 0)




data class MatchOddsItem(
                         val matchInfo: MatchInfo,
                         val odds: Odds)


data class EPSItem(val hkOdds: Double = 0.0,
                   val odds: Double = 0.0,
                   val name: String = "",
                   val id: String = "",
                   val producerId: Int = 0,
                   val extInfo: String = "",
                   val status: Int = 0)




