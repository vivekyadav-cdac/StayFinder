package com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.service;

import com.razorpay.Order;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.razorpay.RazorpayClient;

import java.math.BigDecimal;

@Service
public class RazorpayOrderService {

    private static final String RAZORPAY_KEY = "rzp_test_tgTBSwXi2pgDqY";
    private static final String RAZORPAY_SECRET = "smpSmWEYt3eH72Doc3B10OXK";

    public Order createRazorpayOrder(BigDecimal amount, String currency, String receipt) throws Exception {
        RazorpayClient client = new RazorpayClient(RAZORPAY_KEY, RAZORPAY_SECRET);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount.multiply(new BigDecimal(100))); // Amount in paisa
        orderRequest.put("currency", currency);
        orderRequest.put("receipt", receipt);
        orderRequest.put("payment_capture", 1);

        return client.orders.create(orderRequest);
    }
}
