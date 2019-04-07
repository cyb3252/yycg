package cc.mrbird.system.service;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.IService;
import cc.mrbird.system.domain.Good;
import cc.mrbird.system.domain.User;
import cc.mrbird.system.domain.UserWithRole;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "GoodService")
public interface GoodService extends IService<Good> {


    //@Cacheable(key = "#p0.toString() + (#p1 != null ? #p1.toString() : '')")
    List<Good> findGoodList (Good good, QueryRequest request);

    Integer addGood (Good good);

    Good findByGoodsId (Good good);

    Integer updateGood (Good good);

    Integer deleteGoods (List<Integer> goodsIds);
}
