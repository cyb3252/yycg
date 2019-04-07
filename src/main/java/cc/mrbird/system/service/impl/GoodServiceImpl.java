package cc.mrbird.system.service.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.common.util.MD5Utils;
import cc.mrbird.system.dao.GoodMapper;
import cc.mrbird.system.dao.UserMapper;
import cc.mrbird.system.dao.UserRoleMapper;
import cc.mrbird.system.domain.Good;
import cc.mrbird.system.domain.User;
import cc.mrbird.system.domain.UserRole;
import cc.mrbird.system.domain.UserWithRole;
import cc.mrbird.system.service.GoodService;
import cc.mrbird.system.service.UserRoleService;
import cc.mrbird.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("goodService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GoodServiceImpl extends BaseService<Good> implements GoodService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GoodMapper goodMapper;



    @Override
    public List<Good> findGoodList (Good good, QueryRequest request) {
        try {
            return this.goodMapper.findGoodList(good);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }


    @Override
    @Transactional
    public Integer addGood (Good good) {
        return this.goodMapper.insert(good);
    }

    @Override
    public Good findByGoodsId (Good good) {
        return this.goodMapper.findByGoodsId(good);
    }

    @Override
    @Transactional
    public Integer updateGood (Good good) {
        return this.goodMapper.updateGood(good);
    }

    @Override
    @Transactional
    public Integer deleteGoods (List<Integer> ids) {
        return this.goodMapper.deleteGoods(ids);
    }
}
