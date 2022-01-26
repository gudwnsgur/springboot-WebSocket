package com.example.springbootwebSocket.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data

@Data
data class UserOrderResponse (
    @JsonProperty(value = "order_id") val orderId: Long = 0,
    @JsonProperty(value = "user_email") val userEmail: String = "",
    @JsonProperty(value = "order_display_id") var orderDisplayId: Long = 0,
    @JsonProperty(value = "order_status") var orderStatus: OrderStatus = OrderStatus.PREPARING,

    // 1메뉴 1주문일시 사용 가능한 컬럼
    // 1메뉴 다주문으로 정책이 변경될 시 폐기 & OrderDetail Table 사용
    @JsonProperty(value = "product_id") val productId: Long = 0
)
