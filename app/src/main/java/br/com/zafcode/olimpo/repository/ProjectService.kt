package br.com.zafcode.olimpo.repository

import retrofit2.Response
import retrofit2.http.GET

interface ProjectService {
    @GET("project")
    suspend fun getAll(): Response<List<Model>>
}
