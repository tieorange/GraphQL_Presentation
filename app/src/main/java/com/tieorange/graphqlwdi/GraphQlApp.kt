package com.tieorange.graphqlwdi

import android.app.Application

class GraphQlApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val netModule = NetModule(this)
        graphQlClient  = netModule.provideGraphQlClient()
    }

    companion object {
        lateinit var graphQlClient: GraphQlClient
    }
}