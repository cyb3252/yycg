package cc.mrbird.system.service.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.system.dao.OrderDetailMapper;
import cc.mrbird.system.dao.OrderMapper;
import cc.mrbird.system.domain.OrderDetail;
import cc.mrbird.system.domain.PurchaseOrder;
import cc.mrbird.system.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("orderService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OrderServiceImpl extends BaseService<PurchaseOrder> implements OrderService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;



    @Override
    public List<PurchaseOrder> findOrderList (PurchaseOrder order, QueryRequest request) {
        try {
            return this.orderMapper.findOrderList(order);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<PurchaseOrder> getOrderList (PurchaseOrder order) {
        try {
            return this.orderMapper.findOrderList(order);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public Integer saveOrderDetail (OrderDetail orderDetail) {
        //采购这条记录的总金额
        BigDecimal totalPrice = orderDetail.getPrice().multiply(new BigDecimal(orderDetail.getPurchaseCount()));
        //1.如果是新建订单
        orderDetail.setOrderId(orderDetail.getOldOrderName());
        PurchaseOrder order = new PurchaseOrder();
        String newOrderId = UUID.randomUUID().toString();
        if ("0".equals(orderDetail.getHasOrder())){
            order.setOrderId(newOrderId);
            order.setOrderAmount(totalPrice);
            order.setCompanyNameSc(orderDetail.getCompanyNameSc());
            order.setOrderState(1);
            order.setOrderName(orderDetail.getNewOrderName());
            orderDetail.setOrderId(newOrderId);
            order.setAddTime(new Date());
            order.setOrderRemarks(orderDetail.getOrderRemarks());
            orderMapper.save(order);
        }else{
            String orderId = orderDetail.getOldOrderName();
            PurchaseOrder editOrder=orderMapper.getById(orderId);
            //添加到已有的采购单，修改采购单的金额，并添加一条采购单
            editOrder.setOrderAmount(editOrder.getOrderAmount().add(totalPrice));
            orderMapper.updateById(editOrder);
        }

        return orderDetailMapper.save(orderDetail);
    }


    @Override
    public PurchaseOrder findByOrderId (PurchaseOrder order) {
        return orderMapper.getById(order.getOrderId());
    }

    @Override
    public List<OrderDetail> findOrderDetails (PurchaseOrder order) {

        return orderDetailMapper.findOrderDetails();
    }


}
