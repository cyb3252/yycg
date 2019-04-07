package cc.mrbird.system.controller;

import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.system.domain.Good;
import cc.mrbird.system.domain.OrderDetail;
import cc.mrbird.system.domain.PurchaseOrder;
import cc.mrbird.system.service.GoodService;
import cc.mrbird.system.service.OrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cyb
 * @date 2019/4/3 - 14:31
 */
@Controller
public class OrderController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodService goodService;

    @RequestMapping("order")
    @RequiresPermissions("order:list")
    public String index(){
        return "system/order/order";
    }

    //@Log("获取商品信息")
    @RequestMapping("order/list")
    //@RequiresPermissions("order:list")
    @ResponseBody
    public Map<String, Object> orderList(QueryRequest request, PurchaseOrder order) {
        log.info("order/List");
        return super.selectByPageNumSize(request, () -> this.orderService.findOrderList(order, request));
    }


    @RequestMapping("order/hasOrder")
    @ResponseBody
    public ResponseBo toPurchase(String hasOrder,PurchaseOrder order){
        try {
            if ("1".equals(hasOrder)){
                List<PurchaseOrder> orderList = orderService.getOrderList(order);
                return ResponseBo.ok(orderList);
            }
            return ResponseBo.error("获取采购单名称失败");
        }catch (Exception e){
            log.error("获取采购单名称失败",e);
            return ResponseBo.error("获取采购单名称失败");
        }
    }

    @RequestMapping("/order/add")
    @ResponseBody
    public ResponseBo addOrder(OrderDetail orderDetail){
        try {

            this.orderService.saveOrderDetail(orderDetail);
            return ResponseBo.ok("添加采购单成功！");
        } catch (Exception e) {
            log.error("添加采购单失败", e);
            return ResponseBo.error("添加采购单失败，请联系网站管理员！");
        }
    }


    @RequestMapping("order/toOrderUpdate")
    public String getOrder(PurchaseOrder order) {
        PurchaseOrder returnOrder = this.orderService.findByOrderId(order);
        return "system/order/orderUpdate";

    }

    @RequestMapping("order/getDetail")
    @ResponseBody
    public ResponseBo getDetail(PurchaseOrder order) {
        try {
            List<OrderDetail> orderDetails = this.orderService.findOrderDetails(order);
            return ResponseBo.ok(orderDetails);
        } catch (Exception e) {
            log.error("获取订单详情失败", e);
            return ResponseBo.error("获取订单详情失败，请联系网站管理员！");
        }
    }



    /*//@Log("新增用户")
    //@RequiresPermissions("order:add")
    @RequestMapping("order/add")
    @ResponseBody
    public ResponseBo addGood(Good order) {
        try {

            this.orderService.addGood(order);
            return ResponseBo.ok("新增药品成功！");
        } catch (Exception e) {
            log.error("新增药品失败", e);
            return ResponseBo.error("新增药品失败，请联系网站管理员！");
        }
    }

    @RequestMapping("order/getGood")
    @ResponseBody
    public ResponseBo getGood(Good order) {
        try {

            Good returnGood = this.orderService.findByGoodsId(order);
            return ResponseBo.ok(returnGood);
        } catch (Exception e) {
            log.error("新增药品失败", e);
            return ResponseBo.error("新增药品失败，请联系网站管理员！");
        }
    }


    @RequestMapping("order/update")
    @ResponseBody
    public ResponseBo updateGood(Good order) {
        try {
            this.orderService.updateGood(order);
            return ResponseBo.ok("修改药品成功");
        } catch (Exception e) {
            log.error("修改药品失败", e);
            return ResponseBo.error("修改药品失败，请联系网站管理员！");
        }
    }

    @RequestMapping("order/delete")
    @ResponseBody
    public ResponseBo deleteGoods(String ids) {
        try {
            String[] split = ids.split(",");
            List<Integer> ordersIds = Arrays.stream(split).map(e -> Integer.parseInt(e)).collect(Collectors.toList());
            this.orderService.deleteGoods(ordersIds);
            return ResponseBo.ok("删除药品成功");
        } catch (Exception e) {
            log.error("删除药品失败", e);
            return ResponseBo.error("删除药品失败，请联系网站管理员！");
        }
    }*/
}
