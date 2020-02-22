package io.github.mcasper3.loadboard.board

import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.text.SimpleDateFormat
import java.util.*


class LoadRetriever {

    private val displayDateFormat = SimpleDateFormat("EEE, d MMM yyyy", Locale.US)

    fun getLoads(): ArrayList<Load> {
        return getApiLoads()
            .mapTo(arrayListOf()) { apiLoad ->
                Load(
                    apiLoad.id,
                    apiLoad.origin,
                    apiLoad.destination,
                    displayDateFormat.format(apiLoad.date),
                    apiLoad.value,
                    apiLoad.equipment,
                    apiLoad.locked,
                    Status.from(apiLoad.status)
                )
            }
    }

    private fun getApiLoads(): List<ApiLoad> {
        // Instance of Moshi should not typically be created each time it is needed. Instead it should
        // be cached and used within Retrofit to convert api responses to JSON
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(DateAdapter())
            .build()

        val listType = Types.newParameterizedType(MutableList::class.java, ApiLoad::class.java)
        val adapter = moshi.adapter<List<ApiLoad>>(listType)

        return adapter.fromJson(getJson())!!
    }

    private fun getJson(): String {
        return "[\n" +
                "  {\n" +
                "    \"id\": \"0001\",\n" +
                "    \"origin\": \"Akron, OH\",\n" +
                "    \"destination\": \"Biloxi, MS\",\n" +
                "    \"date\": \"2019-08-01\",\n" +
                "    \"value\": 110000.0,\n" +
                "    \"equipment\": \"V\",\n" +
                "    \"locked\": false,\n" +
                "    \"status\": \"available\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"0002\",\n" +
                "    \"origin\": \"St. Louis, MO\",\n" +
                "    \"destination\": \"Las Vegas, NV\",\n" +
                "    \"date\": \"2019-08-01\",\n" +
                "    \"value\": 89000.0,\n" +
                "    \"equipment\": \"V\",\n" +
                "    \"locked\": false,\n" +
                "    \"status\": \"booked\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"003\",\n" +
                "    \"origin\": \"Chicago, IL\",\n" +
                "    \"destination\": \"Hamburg, PA\",\n" +
                "    \"date\": \"2019-08-02\",\n" +
                "    \"value\": 600000.0,\n" +
                "    \"equipment\": \"R\",\n" +
                "    \"locked\": false,\n" +
                "    \"status\": \"available\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"004\",\n" +
                "    \"origin\": \"Chicago, IL\",\n" +
                "    \"destination\": \"Las Vegas, NV\",\n" +
                "    \"date\": \"2019-08-02\",\n" +
                "    \"value\": 250000.0,\n" +
                "    \"equipment\": \"R\",\n" +
                "    \"locked\": true,\n" +
                "    \"status\": \"available\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"005\",\n" +
                "    \"origin\": \"Galesburg, IL\",\n" +
                "    \"destination\": \"Des Moines, IA\",\n" +
                "    \"date\": \"2019-08-01\",\n" +
                "    \"value\": 780000.0,\n" +
                "    \"equipment\": \"V\",\n" +
                "    \"locked\": false,\n" +
                "    \"status\": \"available\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"006\",\n" +
                "    \"origin\": \"St. Charles, IL\",\n" +
                "    \"destination\": \"Boulder, CO\",\n" +
                "    \"date\": \"2019-08-01\",\n" +
                "    \"value\": 100000.0,\n" +
                "    \"equipment\": \"F\",\n" +
                "    \"locked\": false,\n" +
                "    \"status\": \"booked\"\n" +
                "  }\n" +
                "]"
    }
}

private class DateAdapter {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    @FromJson
    fun dateFromJson(s: String) = dateFormat.parse(s)

    @ToJson
    fun dateToJson(date: Date) = dateFormat.format(date)
}

private class ApiLoad(
    val id: Long,
    val origin: String,
    val destination: String,
    val date: Date,
    val value: String,
    val equipment: String,
    val locked: Boolean,
    val status: String
)
