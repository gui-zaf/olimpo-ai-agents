package br.com.zafcode.olimpo.repository

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatService {
    @POST("chat/complete")
    suspend fun sendMessage(@Body body: ChatCompleteBody): Response<ChatCompleteResponse>

    data class ChatCompleteBody(
        val model: String,
        val messages: List<Message>
    )

    data class ChatCompleteResponse(
        val response: String
    )
}
