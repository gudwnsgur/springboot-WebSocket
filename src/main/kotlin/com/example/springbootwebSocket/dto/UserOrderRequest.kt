package com.example.springbootwebSocket.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data

@Data
data class UserOrderRequest(
    @JsonProperty(value = "product_id") val productId: Long = 0
)