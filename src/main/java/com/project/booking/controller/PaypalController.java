/**
 * THIS CONTROLLER IS TO PERFORM THE PAYMENT PROCESS
 */
package com.project.booking.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.project.booking.model.Order;
import com.project.booking.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

@Controller
public class PaypalController {

    @Autowired
    PaypalService service;
    
    public static final String SUCCESS_URL = "pay/success";

    public static final String CANCEL_URL = "pay/cancel";

    /**
     * RETURNS AN ORDER OBJECT AND REDIRECT TO PAYPAL SDK
     * @param order
     * @return
     * @throws PayPalRESTException
     */
    @PostMapping("/pay")
    public String payment(@ModelAttribute("order") Order order) throws PayPalRESTException {
        Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                order.getIntent(), order.getDescription(), "http://localhost:8080/" + CANCEL_URL,
                "http://localhost:8080/" + SUCCESS_URL);
        System.out.println(payment.toString());
        for (Links link : payment.getLinks()) {
            if (link.getRel().equals("approval_url")) {
                return "redirect:" + link.getHref();
            }

        }

        return "redirect:/";
    }

    /**
     * RETURN CANCEL VIEW IF PAYPAL PAYMENT REJECTED
     * @return
     */
    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    /**
     * RETURN SUCCESS VIEW IF PAYPAL PAYMENT ACCEPTED
     * @param paymentId
     * @param payerId
     * @return
     */
    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";

    }

}
