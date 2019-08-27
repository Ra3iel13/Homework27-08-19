package com.example.homework250819.presenter

import com.example.homework250819.common.Constants
import com.example.homework250819.models.Json4Kotlin_Base
import com.example.homework250819.models.Teams
import com.example.homework250819.network.RetrofitInstance
import com.example.homework250819.network.TeamRequest
import com.example.homework250819.presenter.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TeamPresenter : BasePresenter<TeamView>(){
    override fun onViewAttached(view: TeamView) {
        super.onViewAttached(view)
        view.showLoading()

        val movieRequest = RetrofitInstance().retrofitInstance.create(TeamRequest::class.java)
        val call = movieRequest.getteamPopular(Constants.API_KEY)
        call.enqueue(object : Callback<Json4Kotlin_Base> {
            override fun onFailure(call: Call<Json4Kotlin_Base>, t: Throwable) {
            }

            override fun onResponse(call: Call<Json4Kotlin_Base>, response: Response<Json4Kotlin_Base>) {
                val res = response.body()

                view.showTeamPopular(res!!)

            }

        });


    }
}

interface TeamView: BasePresenter.View {

    fun showLoading()
    fun showTeamPopular(json4kotlinBase: Json4Kotlin_Base)

}