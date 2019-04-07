package cc.mrbird.system.service;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.IService;
import cc.mrbird.system.domain.Good;
import cc.mrbird.system.domain.OrderDetail;
import cc.mrbird.system.domain.PurchaseOrder;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

@CacheConfig(cacheNames = "OrderService")
public interface OrderService extends IService<PurchaseOrder> {


    //@Cacheable(key = "#p0.toString() + (#p1 != null ? #p1.toString() : '')")
    List<PurchaseOrder> findOrderList (PurchaseOrder order, QueryRequest request);

    List<PurchaseOrder> getOrderList(PurchaseOrder order);

    Integer saveOrderDetail (OrderDetail orderDetail);

    PurchaseOrder findByOrderId (PurchaseOrder order);

    List<OrderDetail> findOrderDetails (PurchaseOrder order);
}
