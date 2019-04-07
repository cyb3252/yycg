package cc.mrbird.system.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.system.domain.OrderDetail;

import java.util.List;

public interface OrderDetailMapper extends MyMapper<OrderDetail> {


    Integer save (OrderDetail orderDetail);

    List<OrderDetail> findOrderDetails ();

}