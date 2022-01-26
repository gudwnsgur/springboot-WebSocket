package com.example.springbootwebSocket.service

import com.example.springbootwebSocket.dto.UserOrderResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class ProducerService @Autowired constructor(
    val template: SimpMessagingTemplate
){
    fun sendMessage(topic: String, order : UserOrderResponse) {
        template.convertAndSend("/topic/$topic", order)
    }
}