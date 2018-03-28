package com.tieorange.graphqlwdi

import AllEventsQuery
import AllEventsQuery.Data
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.cache.http.HttpCachePolicy
import com.apollographql.apollo.rx2.Rx2Apollo
import io.reactivex.Single

class GraphQlClient(private val apolloClient: ApolloClient) {

    fun getAllEvents(): Single<Response<Data>> {
        return Rx2Apollo.from(
                apolloClient.query(AllEventsQuery.builder().build()).httpCachePolicy(HttpCachePolicy.CACHE_FIRST)
        ).singleOrError()
    }

}
