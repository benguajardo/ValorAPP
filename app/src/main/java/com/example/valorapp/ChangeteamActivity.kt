package com.example.valorapp

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ChangeteamActivity : AppCompatActivity() {

    private lateinit var imageViewList: List<ImageView>
    private val team: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changeteam)

        imageViewList = listOf(
            findViewById(R.id.imageView1),
            findViewById(R.id.imageView2),
            findViewById(R.id.imageView3),
            findViewById(R.id.imageView4),
            findViewById(R.id.imageView5),
            findViewById(R.id.imageView6),
            findViewById(R.id.imageView7),
            findViewById(R.id.imageView8),
            findViewById(R.id.imageView9),
            findViewById(R.id.imageView10),
            findViewById(R.id.imageView11),
            findViewById(R.id.imageView12),
            findViewById(R.id.imageView13),
            findViewById(R.id.imageView14),
            findViewById(R.id.imageView15),
            findViewById(R.id.imageView16),
            findViewById(R.id.imageView17),
            findViewById(R.id.imageView18),
            findViewById(R.id.imageView19),
            findViewById(R.id.imageView20),
            findViewById(R.id.imageView21),
            findViewById(R.id.imageView22),
            findViewById(R.id.imageView23)
        )

        val retrofit = Retrofit.Builder()
            .baseUrl("https://valorant-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        apiService.getAgents().enqueue(object : Callback<AgentsResponse> {
            override fun onResponse(call: Call<AgentsResponse>, response: Response<AgentsResponse>) {
                if (response.isSuccessful) {
                    val agents = response.body()?.data
                    agents?.let { agentList ->
                        for ((index, agent) in agentList.withIndex()) {
                            if (index < imageViewList.size) {
                                loadImage(agent.displayIcon, imageViewList[index])

                                // Agregar el UUID del agente a la lista team
                                imageViewList[index].setOnClickListener {
                                    if (team.size < 5) {
                                        team.add(agent.uuid)
                                        Log.d("Team", "Agent added to team: ${agent.uuid}")
                                        Log.d("Team", "Agentes en la lista Team: ${team.joinToString()}")
                                    } else {
                                        Log.d("Team", "Maximum team size reached.")
                                    }
                                }

                                // Remover el UUID del agente de la lista team si ya estaba agregado
                                imageViewList[index].setOnLongClickListener {
                                    if (team.contains(agent.uuid)) {
                                        team.remove(agent.uuid)
                                        Log.d("Team", "Agent removed from team: ${agent.uuid}")
                                        Log.d("Team", "Agentes en la lista Team: ${team.joinToString()}")
                                    }
                                    true
                                }
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<AgentsResponse>, t: Throwable) {
                Log.e("MainActivity", "Error fetching agents data", t)
            }
        })
    }

    private fun loadImage(url: String, imageView: ImageView) {
        Glide.with(this)
            .load(url)
            .into(imageView)
    }

    interface ApiService {
        @GET("v1/agents")
        fun getAgents(): Call<AgentsResponse>
    }

    data class AgentsResponse(
        val data: List<Agent>
    )

    data class Agent(
        val uuid: String,
        val displayIcon: String
    )
}
