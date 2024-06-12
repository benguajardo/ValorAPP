package com.example.valorapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Team(
    val agent1: String,
    val agent2: String,
    val agent3: String,
    val agent4: String,
    val agent5: String,
)

class TeamRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://valorant-api.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    fun getTeam(): LiveData<Team> {
        val itemLiveData = MutableLiveData<Team>()

        apiService.getAgents().enqueue(object : Callback<AgentsResponse> {
            override fun onResponse(call: Call<AgentsResponse>, response: Response<AgentsResponse>) {
                if (response.isSuccessful) {
                    val agents = response.body()?.data
                    agents?.let {
                        val distinctAgents = it.distinctBy { agent -> agent.displayIcon }
                        val randomAgents = distinctAgents.shuffled().take(5)
                        if (randomAgents.size >= 5) {
                            val team = Team(
                                randomAgents[0].displayIcon,
                                randomAgents[1].displayIcon,
                                randomAgents[2].displayIcon,
                                randomAgents[3].displayIcon,
                                randomAgents[4].displayIcon
                            )
                            itemLiveData.postValue(team)
                        } else {
                            // Manejar caso cuando hay menos de 5 agentes distintos
                        }
                    }
                }
            }

            override fun onFailure(call: Call<AgentsResponse>, t: Throwable) {
                // Manejo de errores
            }
        })

        return itemLiveData
    }

    interface ApiService {
        @GET("v1/agents")
        fun getAgents(): Call<AgentsResponse>
    }

    data class AgentsResponse(
        val data: List<Agent>
    )

    data class Agent(
        val displayIcon: String
    )
}
