package com.example.springbootwebSocket.chat

import com.example.springbootwebSocket.chat.domain.ChatMessage
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatController {
    // 클라이언트에서 서버로 메시지를 보내는 엔드 포인트를 /chat.sendMessage 로 정의
    @MessageMapping("/chat.sendMessage")
    // 동시에 /topic/public 를 통해 구독(subscribe)중인 클라이언트 앱에 ChatMessage 데이터를 발행(publish)
    @SendTo("/topic/public")
    fun sendMessage(@Payload chatMessage: ChatMessage?): ChatMessage? {
        return chatMessage
    }

    // /chat.addUser : 채팅방에 유저가 접속할 때 실행되는 메서드
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    fun addUser(@Payload chatMessage: ChatMessage, headerAccessor: SimpMessageHeaderAccessor): ChatMessage? {
        // session 에 유저 추가
        headerAccessor.sessionAttributes!!["username"] = chatMessage.sender
        return chatMessage
    }
}