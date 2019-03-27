package com.tieorange.graphqlwdi

import AllEventsQuery
import AllEventsQuery.Data
import GetEventDetailsQuery
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx2.Rx2Apollo
import io.reactivex.Observable

class GraphQlClient(private val apolloClient: ApolloClient) {

    fun getAllEvents(): Observable<Response<Data>> {
        return Rx2Apollo.from(
            apolloClient.query(AllEventsQuery.builder().build())
        )
    }

    fun getEventDetails(eventId: String): ApolloQueryCall<GetEventDetailsQuery.Data> {
        return apolloClient.query(
            GetEventDetailsQuery
                .builder()
                .eventId(eventId)
                .build()
        )
    }

}
