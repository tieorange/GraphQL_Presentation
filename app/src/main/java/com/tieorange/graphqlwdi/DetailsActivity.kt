package com.tieorange.graphqlwdi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.apollographql.apollo.api.cache.http.HttpCachePolicy

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val eventId = intent.getStringExtra("id")
        fetchData(eventId)
    }

    private fun fetchData(eventId: String) {
        GraphQlApp.graphQlClient.getEventDetails(eventId)
            .httpCachePolicy(HttpCachePolicy.CACHE_FIRST)
            .enqueue(object :  {

            })
    }
}
