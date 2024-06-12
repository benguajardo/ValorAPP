package com.example.valorapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ChangeteamActivity : AppCompatActivity() {
    private lateinit var generalImageViewList: List<ImageView>
    private lateinit var teamImageViewList: List<ImageView>
    private lateinit var resetButton: Button
    private lateinit var acceptButton: Button
    private val team: MutableList<Agent> = mutableListOf()
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changeteam)

        generalImageViewList = listOf(
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

        teamImageViewList = listOf(
            findViewById(R.id.teamImageView1),
            findViewById(R.id.teamImageView2),
            findViewById(R.id.teamImageView3),
            findViewById(R.id.teamImageView4),
            findViewById(R.id.teamImageView5)
        )

        resetButton = findViewById(R.id.resetButton)
        acceptButton = findViewById(R.id.acceptButton)

        resetButton.setOnClickListener {
            team.clear()
            updateTeamImages()
            Log.d("ChangeteamActivity", "Team reseteado")
        }

        acceptButton.setOnClickListener {
            val intent = Intent(this, MatchfoundActivity::class.java).apply {
                putStringArrayListExtra("teamImages", ArrayList(team.map { it.displayIcon }))
            }
            startActivity(intent)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://valorant-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        apiService.getAgents().enqueue(object : Callback<AgentsResponse> {
            override fun onResponse(call: Call<AgentsResponse>, response: Response<AgentsResponse>) {
                if (response.isSuccessful) {
                    val agents = response.body()?.data
                    agents?.let {
                        for (i in it.indices) {
                            if (i < generalImageViewList.size) {
                                val agent = it[i]
                                loadImage(agent.displayIcon, generalImageViewList[i])
                                generalImageViewList[i].setOnClickListener {
                                    if (team.size < 5 && !team.contains(agent)) {
                                        team.add(agent)
                                        Log.d("ChangeteamActivity", "Agent added to team: ${agent.uuid}")
                                        updateTeamImages()
                                    } else {
                                        Log.d("ChangeteamActivity", "Team is full or agent is already in the team")
                                    }
                                }
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<AgentsResponse>, t: Throwable) {
                Log.e("ChangeteamActivity", "Error fetching agents data", t)
            }
        })

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance().reference
    }

    private fun loadImage(url: String, imageView: ImageView) {
        Glide.with(this)
            .load(url)
            .into(imageView)
    }

    private fun updateTeamImages() {
        for (i in teamImageViewList.indices) {
            if (i < team.size) {
                loadImage(team[i].displayIcon, teamImageViewList[i])
            } else {
                teamImageViewList[i].setImageDrawable(null)
            }
        }
    }

    interface ApiService {
        @GET("v1/agents")
        fun getAgents(): Call<AgentsResponse>
    }

    data class AgentsResponse(
        val data: List<Agent>
    )

    data class Agent(
        val displayIcon: String,
        val uuid: String
    )
}
