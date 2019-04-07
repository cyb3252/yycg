package cc.mrbird.system.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.system.domain.Good;
import cc.mrbird.system.domain.User;
import cc.mrbird.system.service.GoodService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author cyb
 * @date 2019/4/3 - 14:31
 */
@Controller
public class GoodController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GoodService goodService;

    @RequestMapping("good")
    //@RequiresPermissions("good:list")
    public String index(){
        return "system/good/good";
    }

    //@Log("获取商品信息")
    @RequestMapping("good/list")
    //@RequiresPermissions("good:list")
    @ResponseBody
    public Map<String, Object> goodList(QueryRequest request, Good good) {
        log.info("good/List");
        return super.selectByPageNumSize(request, () -> this.goodService.findGoodList(good, request));
    }


    //@Log("新增用户")
    //@RequiresPermissions("good:add")
    @RequestMapping("good/add")
    @ResponseBody
    public ResponseBo addGood(Good good) {
        try {

            this.goodService.addGood(good);
            return ResponseBo.ok("新增药品成功！");
        } catch (Exception e) {
            log.error("新增药品失败", e);
            return ResponseBo.error("新增药品失败，请联系网站管理员！");
        }
    }

    @RequestMapping("good/getGood")
    @ResponseBody
    public ResponseBo getGood(Good good) {
        try {

            Good returnGood = this.goodService.findByGoodsId(good);
            return ResponseBo.ok(returnGood);
        } catch (Exception e) {
            log.error("新增药品失败", e);
            return ResponseBo.error("新增药品失败，请联系网站管理员！");
        }
    }


    @RequestMapping("good/update")
    @ResponseBody
    public ResponseBo updateGood(Good good) {
        try {
            this.goodService.updateGood(good);
            return ResponseBo.ok("修改药品成功");
        } catch (Exception e) {
            log.error("修改药品失败", e);
            return ResponseBo.error("修改药品失败，请联系网站管理员！");
        }
    }

    @RequestMapping("good/delete")
    @ResponseBody
    public ResponseBo deleteGoods(String ids) {
        try {
            String[] split = ids.split(",");
            List<Integer> goodsIds = Arrays.stream(split).map(e -> Integer.parseInt(e)).collect(Collectors.toList());
            this.goodService.deleteGoods(goodsIds);
            return ResponseBo.ok("删除药品成功");
        } catch (Exception e) {
            log.error("删除药品失败", e);
            return ResponseBo.error("删除药品失败，请联系网站管理员！");
        }
    }
}
