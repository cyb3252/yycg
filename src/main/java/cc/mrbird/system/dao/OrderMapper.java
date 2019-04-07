package cc.mrbird.system.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.system.domain.Good;
import cc.mrbird.system.domain.PurchaseOrder;

import java.util.List;

public interface OrderMapper extends MyMapper<PurchaseOrder> {

    List<PurchaseOrder> findOrderList (PurchaseOrder order);

    Integer save (PurchaseOrder order);

    PurchaseOrder getById (String orderId);

    Integer updateById (PurchaseOrder order);

	/*List<Good> findGoodList (Good good);

    Good findByGoodsId (Good good);

    Integer updateGood (Good good);

    Integer deleteGoods (List<Integer> goodsIds);*/
}