package com.example.springbootwebSocket.chat.listener

import com.example.springbootwebSocket.chat.domain.ChatMessage
import com.example.springbootwebSocket.chat.domain.MessageType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionConnectedEvent
import org.springframework.web.socket.messaging.SessionDisconnectEvent

@Component
class WebSocketEventListener(
    private val messagingTemplate: SimpMessageSendingOperations?
) {
    private val logger: Logger = LoggerFactory.getLogger(WebSocketEventListener::class.java)

    /**
     * Connection, Disconnection 시 이벤트를 다루기 위한 리스너 함수
     */

    @EventListener
    fun handleWebSocketConnectListener(event: SessionConnectedEvent?) {
        logger.info("Received a new web socket connection")
    }

    @EventListener
    fun handleWebSocketDisconnectListener(event: SessionDisconnectEvent) {
        val headerAccessor = StompHeaderAccessor.wrap(event.message)
        val username = headerAccessor.sessionAttributes!!["username"] as String?
        if (username != null) {
            logger.info("User Disconnected : $username")
            val chatMessage = ChatMessage(MessageType.LEAVE, "", username)

            messagingTemplate!!.convertAndSend("/topic/public", chatMessage)
        }
    }
}