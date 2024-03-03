package uz.ubtuit.powerbi.data

import retrofit2.Call
import retrofit2.http.GET
import uz.ubtuit.powerbi.model.Book
import uz.ubtuit.powerbi.model.Question
import uz.ubtuit.powerbi.model.Video

interface ApiService {

    @GET("list/test/")
    fun getTests(): Call<ArrayList<Question>>

    @GET("list/Pdf/")
    fun getPDFs(): Call<ArrayList<Book>>

    @GET("list/videos/")
    fun getVideos(): Call<ArrayList<Video>>

}