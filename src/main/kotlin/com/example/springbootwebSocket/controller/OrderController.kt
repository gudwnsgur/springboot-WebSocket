package com.example.springbootwebSocket.controller

import com.example.springbootwebSocket.service.ProducerService
import com.example.springbootwebSocket.dto.OrderStatus
import com.example.springbootwebSocket.dto.OrdersDTO
import com.example.springbootwebSocket.dto.UserOrderRequest
import com.example.springbootwebSocket.dto.UserOrderResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class OrderController @Autowired constructor(
    var producer: ProducerService
) {
    @ResponseBody
    @PostMapping("/send/{topic}")
    fun sender(@PathVariable topic: String?, @RequestBody orderResponse: UserOrderResponse) {
        print("sender")
        // /user/order 에서 등록된 orderResponse send
        println(orderResponse)
        topic?.let { producer.sendMessage(it, orderResponse) }
    }

    @GetMapping("/admin/order")
    fun getAdminOrderPage() :String {
        print("GET adminOrder ")
        return "admin_order"
    }

    @GetMapping("/user/order")
    fun getUserOrderPage() :String {
        print("GET userOrder ")
        return "user_order"
    }

//    @ResponseBody
//    @PostMapping("/user/order")
//    fun userOrder(@RequestParam ordersDTO: OrdersDTO) {
//        print("여기서 service 이용해서 order request(현 DTO) 등록")
//        print("POST userOrder : $ordersDTO")
//    }

    @ResponseBody
    @PostMapping("/user/order")
    fun userOrder(@RequestBody orderRequest: UserOrderRequest) : ResponseEntity<Any> {
        println("POST userOrder : $orderRequest")
        println("orderRequest => orderDTO 생성")
        println("여기서 service 이용해서 orderDTO를 db에등록")
        val response = UserOrderResponse(
            100, "gudwnsgur12@naver.com", 200,
            OrderStatus.PREPARING, orderRequest.productId
        )
        // 생성됐다 침
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}