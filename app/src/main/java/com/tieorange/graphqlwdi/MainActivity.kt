package com.tieorange.graphqlwdi

import AllEventsQuery.Data
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.apollographql.apollo.api.Response
import com.mcxiaoke.koi.log.logd
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initFAB()
        val eventsAdapter = initRecyclerView()

        val disposable = GraphQlApp.graphQlClient.getAllEvents()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { eventsList: Response<Data> ->
                    onDataFetched(eventsAdapter, eventsList)
                },
                { onError(it) }
            )
    }

    private fun onError(it: Throwable) {
        logd { it.message }
    }

    private fun onDataFetched(
        eventsAdapter: EventsAdapter,
        eventsList: Response<Data>
    ) {
        val data = eventsList.data()?.allEvents()?.map {
            it.fragments().eventBasic()
        } ?: emptyList()

        eventsAdapter.setData(data)
        logd { data }
    }

    private fun initFAB() {
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun initRecyclerView(): EventsAdapter {
        val eventsAdapter = EventsAdapter(this)

        eventsList.layoutManager = LinearLayoutManager(this)
        eventsList.adapter = eventsAdapter

        return eventsAdapter
    }

}
