package com.example.mvvm_demo_androidx.SimplifyRV

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_demo_androidx.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_simplify_recycler_view_main.*
import java.util.*


/**
 * 把四層RV 簡化到剩2層
 * **/
class SimplifyRecyclerViewMainActivity : AppCompatActivity() {

    var firstAdapter = FirstRvAdapter(this@SimplifyRecyclerViewMainActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simplify_recycler_view_main)
        initData()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        rv_first.layoutManager = LinearLayoutManager(this@SimplifyRecyclerViewMainActivity,LinearLayoutManager.VERTICAL,false)
        rv_first.adapter = firstAdapter
    }

    fun initData(){

        val dataString = "{\"success\":true,\"msg\":\"ok\",\"code\":0,\"rows\":[{\"date\":1626282000000,\"leagueOdds\":[{\"league\":{\"id\":\"27111\",\"name\":\"China Super League\",\"category\":null},\"matchOdds\":[{\"matchInfo\":{\"id\":\"5090372\",\"leagueId\":\"27111\",\"leagueName\":\"China Super League\",\"homeName\":\"Henan Songshan Longmen\",\"awayName\":\"Guangzhou\",\"startTime\":1626348600000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6493654460\",\"name\":\"Guangzhou to Draw at Half Time but Win at Full Time\\t\",\"spread\":null,\"extInfo\":\"4.00\",\"odds\":4.35,\"hkOdds\":3.35,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null}]}]},{\"date\":1626368400000,\"leagueOdds\":[{\"league\":{\"id\":\"28586\",\"name\":\"Finland Veikkausliiga\",\"category\":null},\"matchOdds\":[{\"matchInfo\":{\"id\":\"5067698\",\"leagueId\":\"28586\",\"leagueName\":\"Finland Veikkausliiga\",\"homeName\":\"Ilves Tampere\",\"awayName\":\"SJK Seinajoki\",\"startTime\":1626449400000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6491230801\",\"name\":\"Ilves Tampere Race to 3 Goals\",\"spread\":null,\"extInfo\":\"6.00\",\"odds\":6.6,\"hkOdds\":5.6,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null},{\"matchInfo\":{\"id\":\"5067709\",\"leagueId\":\"28586\",\"leagueName\":\"Finland Veikkausliiga\",\"homeName\":\"Lahti\\t\",\"awayName\":\"IFK Mariehamn\",\"startTime\":1626449400000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6491257644\",\"name\":\"IFK Mariehamn Race to 2 Goals\",\"spread\":null,\"extInfo\":\"4.55\",\"odds\":5,\"hkOdds\":4,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null}]},{\"league\":{\"id\":\"28777\",\"name\":\"Copa Libertadores \",\"category\":null},\"matchOdds\":[{\"matchInfo\":{\"id\":\"5071755\",\"leagueId\":\"28777\",\"leagueName\":\"Copa Libertadores \",\"homeName\":\"Olimpia Asuncion\",\"awayName\":\"SC Internacional RS\",\"startTime\":1626395400000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6496247538\",\"name\":\"SC Internacional RS to Win 3-0\",\"spread\":null,\"extInfo\":\"21.00\",\"odds\":26,\"hkOdds\":25,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null}]},{\"league\":{\"id\":\"26649\",\"name\":\"Copa Sudamericana\",\"category\":null},\"matchOdds\":[{\"matchInfo\":{\"id\":\"5071806\",\"leagueId\":\"26649\",\"leagueName\":\"Copa Sudamericana\",\"homeName\":\"Santos SP\",\"awayName\":\"CA Independiente\",\"startTime\":1626387300000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6496255165\",\"name\":\"Santos SP to Lead 2-0 at Half Time\",\"spread\":null,\"extInfo\":\"10.50\",\"odds\":12.5,\"hkOdds\":11.5,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null},{\"matchInfo\":{\"id\":\"5071813\",\"leagueId\":\"26649\",\"leagueName\":\"Copa Sudamericana\",\"homeName\":\"Club Nacional Montevideo\",\"awayName\":\"CA Penarol\",\"startTime\":1626395400000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6496262575\",\"name\":\"CA Penarol to Score in Both Halves\",\"spread\":null,\"extInfo\":\"4.90\",\"odds\":5.3,\"hkOdds\":4.3,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null}]}]},{\"date\":1626454800000,\"leagueOdds\":[{\"league\":{\"id\":\"28586\",\"name\":\"Finland Veikkausliiga\",\"category\":null},\"matchOdds\":[{\"matchInfo\":{\"id\":\"5070458\",\"leagueId\":\"28586\",\"leagueName\":\"Finland Veikkausliiga\",\"homeName\":\"AC Oulu\",\"awayName\":\"HJK Helsinki\",\"startTime\":1626535800000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6491286571\",\"name\":\"AC Oulu Race to 2 Goals\",\"spread\":null,\"extInfo\":\"8.00\",\"odds\":8.5,\"hkOdds\":7.5,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null}]},{\"league\":{\"id\":\"26713\",\"name\":\"Japan J1 League\",\"category\":null},\"matchOdds\":[{\"matchInfo\":{\"id\":\"5070317\",\"leagueId\":\"26713\",\"leagueName\":\"Japan J1 League\",\"homeName\":\"Shimizu S Pulse\",\"awayName\":\"Kawasaki Frontale\",\"startTime\":1626514200000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6489369895\",\"name\":\"Kawasaki Frontale to Win to Nil\",\"spread\":null,\"extInfo\":\"2.28\",\"odds\":2.5,\"hkOdds\":1.5,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null},{\"matchInfo\":{\"id\":\"5070337\",\"leagueId\":\"26713\",\"leagueName\":\"Japan J1 League\",\"homeName\":\"Avispa Fukuoka\",\"awayName\":\"Gamba Osaka\",\"startTime\":1626514200000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6489370183\",\"name\":\"Gamba Osaka to Win 1-0\",\"spread\":null,\"extInfo\":\"7.00\",\"odds\":8.7,\"hkOdds\":7.7,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null},{\"matchInfo\":{\"id\":\"5070369\",\"leagueId\":\"26713\",\"leagueName\":\"Japan J1 League\",\"homeName\":\"Cerezo Osaka\",\"awayName\":\"Vissel Kobe\\t\",\"startTime\":1626516000000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6489370748\",\"name\":\"Cerezo Osaka to Win by 1 Goal\",\"spread\":null,\"extInfo\":\"3.85\",\"odds\":4.3,\"hkOdds\":3.3,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null},{\"matchInfo\":{\"id\":\"5070380\",\"leagueId\":\"26713\",\"leagueName\":\"Japan J1 League\",\"homeName\":\"Sagan Tosu\",\"awayName\":\"Nagoya Grampus\",\"startTime\":1626516000000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6489371607\",\"name\":\"Nagoya Grampus to Win 2-0\",\"spread\":null,\"extInfo\":\"10.50\",\"odds\":13,\"hkOdds\":12,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null}]},{\"league\":{\"id\":\"26380\",\"name\":\"Norway Eliteserien\",\"category\":null},\"matchOdds\":[{\"matchInfo\":{\"id\":\"5073728\",\"leagueId\":\"26380\",\"leagueName\":\"Norway Eliteserien\",\"homeName\":\"Odd BK\",\"awayName\":\"Viking FK\",\"startTime\":1626537600000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6492101207\",\"name\":\"Odd BK  to Win 4-0\",\"spread\":null,\"extInfo\":\"56.00\",\"odds\":71,\"hkOdds\":70,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null}]}]},{\"date\":1626541200000,\"leagueOdds\":[{\"league\":{\"id\":\"26380\",\"name\":\"Norway Eliteserien\",\"category\":null},\"matchOdds\":[{\"matchInfo\":{\"id\":\"5073746\",\"leagueId\":\"26380\",\"leagueName\":\"Norway Eliteserien\",\"homeName\":\"Sarpsborg 08\",\"awayName\":\"Bodo Glimt\",\"startTime\":1626544800000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6492134078\",\"name\":\"Sarpsborg 08 to Score in Both Halves\",\"spread\":null,\"extInfo\":\"4.45\",\"odds\":4.8,\"hkOdds\":3.8,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null},{\"matchInfo\":{\"id\":\"5074680\",\"leagueId\":\"26380\",\"leagueName\":\"Norway Eliteserien\",\"homeName\":\"Tromso\",\"awayName\":\"Rosenborg\",\"startTime\":1626624000000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6492227813\",\"name\":\"Rosenborg to Win & Over 4.5\",\"spread\":null,\"extInfo\":\"12.50\",\"odds\":13.5,\"hkOdds\":12.5,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null},{\"matchInfo\":{\"id\":\"5074718\",\"leagueId\":\"26380\",\"leagueName\":\"Norway Eliteserien\",\"homeName\":\"Lillestrom\",\"awayName\":\"Molde FK\",\"startTime\":1626624000000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6492201895\",\"name\":\"Molde FK to Win by 4 or More Goals\",\"spread\":null,\"extInfo\":\"14.50\",\"odds\":17.5,\"hkOdds\":16.5,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null},{\"matchInfo\":{\"id\":\"5074733\",\"leagueId\":\"26380\",\"leagueName\":\"Norway Eliteserien\",\"homeName\":\"FK Haugesund\",\"awayName\":\"Valerenga\",\"startTime\":1626624000000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6492171315\",\"name\":\"Total Goals 0-1\",\"spread\":null,\"extInfo\":\"3.50\",\"odds\":4,\"hkOdds\":3,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null}]}]},{\"date\":1626627600000,\"leagueOdds\":[{\"league\":{\"id\":\"28586\",\"name\":\"Finland Veikkausliiga\",\"category\":null},\"matchOdds\":[{\"matchInfo\":{\"id\":\"5074433\",\"leagueId\":\"28586\",\"leagueName\":\"Finland Veikkausliiga\",\"homeName\":\"Inter Turku\",\"awayName\":\"HIFK Helsinki\",\"startTime\":1626708600000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6491311911\",\"name\":\"Inter Turku to Win by 2 Goals\",\"spread\":null,\"extInfo\":\"4.50\",\"odds\":5,\"hkOdds\":4,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null},{\"matchInfo\":{\"id\":\"5074435\",\"leagueId\":\"28586\",\"leagueName\":\"Finland Veikkausliiga\",\"homeName\":\"KTP\",\"awayName\":\"Haka\",\"startTime\":1626708600000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6491335446\",\"name\":\"KTP Race to 2 Goals\",\"spread\":null,\"extInfo\":\"3.90\",\"odds\":4.35,\"hkOdds\":3.35,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null}]},{\"league\":{\"id\":\"26380\",\"name\":\"Norway Eliteserien\",\"category\":null},\"matchOdds\":[{\"matchInfo\":{\"id\":\"5074886\",\"leagueId\":\"26380\",\"leagueName\":\"Norway Eliteserien\",\"homeName\":\"Stromsgodset\",\"awayName\":\"Stabaek\",\"startTime\":1626631200000,\"endTime\":null,\"status\":0,\"playCateNum\":null,\"name\":null,\"img\":null,\"msg\":null},\"dynamicMarkets\":{},\"odds\":{\"EPS\":[{\"id\":\"6492254247\",\"name\":\"Stromsgodset to Win by 3 Goals\",\"spread\":null,\"extInfo\":\"9.00\",\"odds\":10.5,\"hkOdds\":9.5,\"status\":0,\"producerId\":0}]},\"oddsList\":null,\"quickPlayCateList\":null}]}]}],\"total\":5}"
        val data:ListResult = Gson().fromJson(dataString,ListResult::class.java)
        val rowsData = data.rows

        /**
         * 把data 合併到 leagueOdds裡面  如果 LeagueOddsItem的 date != 0 就判斷為 日期區間 反之則變成RecyclerView 進入下一層
         * Rv第一層資料結構  Type 判斷有 data 就是時間標題 剩下的變成 leagueOdds的 List塞入下一層RV
         * 一個Type是時間 另一個是比賽名稱+第一層RV
         * */

        val newListV1 =  mutableListOf<LeagueOddsItem>() //整理完的第一層
        rowsData?.forEach {
            val newLeagueOddsItem = LeagueOddsItem(date = it.date ,league=null,matchOdds=null)
            newListV1.add(newLeagueOddsItem)
            it.leagueOdds?.forEach { leaguesOddsItems ->
                newListV1.add(leaguesOddsItems)
            }
        }
        firstAdapter.dataList = newListV1


        /**
         * 把 league 合併到  matchOdds(裡面新增一個 league型別去接) (X)
         * 創造一個新的陣列是 有matchInfo 和 EPS 的
         * */
//        var newListV2 =  mutableListOf<EpsOdds>()
//
//        newListV1[1]?.matchOdds?.forEach {
//            newListV2.add(EpsOdds(matchInfo = it.matchInfo, eps = null))
//            it.odds.eps?.forEach { EPSItem ->
//                newListV2.add(EpsOdds(matchInfo = null, eps = EPSItem))
//            }
//        }



        /**
         * 資料說明 : league 的 name 是灰色部分的聯盟名稱
         *           matchOdds 下的  matchInfo 是橘色比賽名稱
         * */

        Log.v("Bill","Bill==資料分類==>newListV1:${newListV1}")
        //TODO Bill 改完資料
//        Log.v("Bill","Bill==資料分類==>newListV2:${newListV2}")
        //明天雕畫面

    }
}