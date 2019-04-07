package cc.mrbird.system.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.system.domain.Good;
import java.util.List;

public interface GoodMapper extends MyMapper<Good> {

	List<Good> findGoodList (Good good);

    Good findByGoodsId (Good good);

    Integer updateGood (Good good);

    Integer deleteGoods (List<Integer> goodsIds);
}