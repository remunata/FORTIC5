package com.pplbo.fortic5.service.order;

import com.pplbo.fortic5.model.order.Order;
import com.pplbo.fortic5.model.request.CheckoutRequest;
import com.pplbo.fortic5.model.user.User;

public interface OrderService {

    Order save(User user, CheckoutRequest request);

    Order findById(Integer id);
}
