package com.saucefan.stuff.foodiefunbw.Model.remote

//simple retro fit class with factory like::

/*
interface RetroApi {
    @GET("search/movie?language=en-US&page=1&include_adult=false")
    fun getMoviesSearch(@Query("query")query: String,@Query("api_key")api_key:String) : Call<MovieSearchResult>

    class Factory {
        companion object {
            val BASE_URL = "https://api.themoviedb.org/3/"
            val gson = Gson()


            fun create(): MovieRetroApi {

                // we don't need this at the moment
                   val logger = HttpLoggingInterceptor()
                   logger.level = HttpLoggingInterceptor.Level.BASIC
                   logger.level = HttpLoggingInterceptor.Level.BODY
                   val okHttpClient = OkHttpClient.Builder()
                       .addInterceptor(logger)
                       .retryOnConnectionFailure(false)
                       .readTimeout(10, TimeUnit.SECONDS)
                       .connectTimeout(15, TimeUnit.SECONDS)
                       .build()
                val retrofit = Retrofit.Builder()
                         .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)) //gson
                    .build()

                return retrofit.create(MovieRetroApi::class.java)
            }
        }
    }

}*/

//reference method/object

/*
    fun getSearchMovies(search: String, api_key: String): MutableList<Movie> {
        tempMovieList = mutableListOf<Movie>()


        val apiInterface = RetroApi.Factory.create()
        val mutlist = mutableListOf<Movie>()
        apiInterface.getMoviesSearch(search, api_key)
            .enqueue(object : Callback<MovieSearchResult> {
                override fun onFailure(call: Call<MovieSearchResult>, t: Throwable) {
                    t.printStackTrace()
                    val response = "faliure; ${t.message}"

                }

                override fun onResponse(
                    call: Call<MovieSearchResult>,
                    response: Response<MovieSearchResult>
                ) {
                    val newMovList: MovieSearchResult? = response.body() as MovieSearchResult
                    newMovList?.results?.forEach {
                        tempMovieList.add(it)
                        mutlist.add(it)
                        insertReview(it)
                    }
                }
            })


*/