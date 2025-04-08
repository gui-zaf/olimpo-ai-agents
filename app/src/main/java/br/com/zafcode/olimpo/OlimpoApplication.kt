package br.com.zafcode.olimpo

import android.app.Application
import br.com.zafcode.olimpo.repository.ChatService
import br.com.zafcode.olimpo.repository.ProjectService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OlimpoApplication : Application() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://olimpo.zafcode.com.br")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val chatService: ChatService = retrofit.create(ChatService::class.java)
    val projectService: ProjectService = retrofit.create(ProjectService::class.java)
}