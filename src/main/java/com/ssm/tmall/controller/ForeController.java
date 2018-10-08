package com.ssm.tmall.controller;

import com.ssm.tmall.comparator.*;
import com.ssm.tmall.pojo.*;
import com.ssm.tmall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// 首页控制器
@Controller
public class ForeController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private PropertyValueService propertyValueService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderService orderService;

    // 首页显示
    @RequestMapping(value = "/forehome")
    public String toHomePage(Model model) {
        List<Category> cs = categoryService.getCategoryList();
        productService.fill(cs);
        productService.fillByRow(cs);
        model.addAttribute("cs", cs);

        return "fore/home";
    }

    // 注册
    @RequestMapping(value = "foreregister", method = RequestMethod.POST)
    public String register(Model model, User user) {
        user.setName(HtmlUtils.htmlEscape(user.getName()));

        // 检测用户名是否存在
        if (userService.isExist(user.getName())) {
            String msg = "用户名 " + user.getName() + " 已经被占用，请修改用户名！";

            model.addAttribute("msg", msg);
            model.addAttribute("user", null);

            return "fore/register";
        }

        userService.add(user);
        return "redirect:registerSuccessPage";
    }

    // 登录
    @RequestMapping(value = "/forelogin", method = RequestMethod.POST)
    public String login(Model model, HttpSession session,
                        @RequestParam("name") String name, @RequestParam("password") String password) {
        name = HtmlUtils.htmlEscape(name);
        // 检测用户名密码是否合法
        if (userService.check(name, password)) {
            User user = userService.getByName(name);
            session.setAttribute("user", user);
            // -----？？？？-------待完善，返回之前的页面而不是登录页
            return "redirect:forehome";
        }

        model.addAttribute("msg", "用户名密码不匹配！");
        return "fore/login";
    }

    // 登出
    @RequestMapping(value = "/forelogout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");

        return "redirect:forehome";
    }

    // 产品页面
    @RequestMapping(value = "foreproduct")
    public String product(@RequestParam("pid") Integer pid, Model model) {
        Product p = productService.getById(pid);
        List<PropertyValue> pvs = propertyValueService.getList(pid);
        List<Review> reviews = reviewService.getList(pid);

        model.addAttribute("p", p);
        model.addAttribute("pvs", pvs);
        model.addAttribute("reviews", reviews);
        return "fore/product";
    }

    // 点击加入购物车和立即购买按钮时进行的Ajax请求验证
    @RequestMapping(value = "/forecheckLogin")
    @ResponseBody
    public String checkLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "fali";
        }

        return "success";
    }

    @RequestMapping(value = "/foreloginAjax")
    @ResponseBody
    public String loginAjax(@RequestParam("name") String name, @RequestParam("password") String password,
                            HttpSession session) {
        name = HtmlUtils.htmlEscape(name);
        if (userService.check(name, password)) {
            session.setAttribute("user", userService.getByName(name));
            return "success";
        }

        return "fail";
    }

    @RequestMapping(value = "/forecategory")
    public String category(Model model, @RequestParam("cid") Integer cid,
                           @RequestParam(required = false, defaultValue = "all") String sort) {
        Category c = categoryService.getCategoryById(cid);
        productService.fill(c);
        productService.setSaleAndReviewNumber(c.getProducts());

        // 根据sort参数使用对应的比较器对产品列表进行排序,默认使用综合排序
        switch (sort) {
            case "all":
                Collections.sort(c.getProducts(), new ProductAllComparator());
                break;
            case "review":
                Collections.sort(c.getProducts(), new ProductReviewComparator());
                break;
            case "date":
                Collections.sort(c.getProducts(), new ProductDateComparator());
                break;
            case "saleCount":
                Collections.sort(c.getProducts(), new ProductSaleComparator());
                break;
            case "price":
                Collections.sort(c.getProducts(), new ProductPRiceComparator());
                break;
        }

        model.addAttribute("c", c);
        return "fore/category";
    }

    @RequestMapping(value = "/foresearch")
    public String search(Model model, @RequestParam String keyword) {
        List<Product> ps = productService.search(keyword);
        model.addAttribute("ps", ps);

        return "fore/searchResult";
    }

    // 在产品页面点击 立即购买 按钮的请求响应
    // ----？？？？？存在的问题：客户端可以跳过js的验证，直接访问连接，这样也就会使得user获取为空
    @RequestMapping(value = "/forebuyone")
    public String buyone(Integer pid, Integer num, HttpSession session) {
        int oiid = 0;
        User user = (User) session.getAttribute("user");

        // 新增订单项目
        oiid = orderItemService.add(user.getId(), pid, num);

        // 跳转到提交订单界面
        return "redirect:forebuy?oiid=" + oiid;
    }

    // 选择需要购买的订单项目后，返回 订单结算页面
    @RequestMapping(value = "/forebuy")
    public String buy(Model model, HttpSession session, Integer[] oiid) {
        // 所有订单项目
        List<OrderItem> orderItems = new ArrayList<>();
        // 总金额
        float total = 0;

        for (Integer orderItemId : oiid) {
            OrderItem orderItem = orderItemService.get(orderItemId);
            orderItems.add(orderItem);
            total += orderItem.getNumber() * orderItem.getProduct().getPromotePrice();
        }

        session.setAttribute("ois", orderItems);
        model.addAttribute("total", total);

        return "fore/buy";
    }

    // 点击加入购物车按钮时的Ajax请求响应
    @RequestMapping(value = "/foreaddCart")
    @ResponseBody
    public String addCart(Integer pid, Integer num, HttpSession session) {
        User user = (User) session.getAttribute("user");

        // 新增订单项目
        orderItemService.add(user.getId(), pid, num);

        // 跳转到提交订单界面
        return "success";
    }

    // 进入购物车界面
    @RequestMapping(value = "/forecart")
    public String cart(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<OrderItem> orderItems = orderItemService.getListByUser(user.getId());

        model.addAttribute("ois", orderItems);

        return "fore/cart";
    }

    // 在购物车界面点击删除订单项目
    @RequestMapping(value = "foredeleteOrderItem", method = RequestMethod.POST)
    @ResponseBody
    public String deleteOrderItem(Model model, HttpSession session, Integer oiid) {
        if (session.getAttribute("user") == null) {
            return "fail";
        }
        orderItemService.delete(oiid);

        return "success";
    }

    // 改变订单项目中物品的数量(点击加号或减号按钮)
    @RequestMapping(value = "/forechangeOrderItem")
    @ResponseBody
    public String changeOrderItem(Integer pid, Integer number, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "fail";

        // 修改已存在订单项目的物品数量
        orderItemService.change(pid, number, user.getId());

        return "success";
    }

    @RequestMapping(value = "/forecreateOrder")
    public String createOrder(Order order, Model model, HttpSession session) {
        // 取出在 buy 方法中加入到 session 域中的订单项目列表
        List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("ois");
        User user = (User) session.getAttribute("user");
        order.setUid(user.getId());

        // 该服务需要启用事务处理，因为涉及到两张表的操作
        float totalMoney = orderService.add(order, orderItems);

        return "redirect:forealipay?oid="+order.getId() +"&total="+totalMoney;
    }

    // 点击支付成功
    @RequestMapping(value = "/forepayed")
    public String payed(Integer oid, float total, Model model) {
        Order order = orderService.get(oid);
        order.setPayDate(new Date());
        order.setStatus(OrderService.waitDelivery);
        orderService.update(order);

        model.addAttribute("o", order);

        return "fore/payed";
    }

    // 我的订单界面
    @RequestMapping(value = "/forebought")
    public String bought(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Order> orders = orderService.getList(user.getId(), "all");

        model.addAttribute("os", orders);
        return "fore/bought";
    }

    // 点击确认收货按钮
    @RequestMapping(value = "/foreconfirmPay")
    public String confirmPay (Model model, Integer oid) {
        Order order = orderService.get(oid);

        model.addAttribute("o", order);

        return "fore/confirmPay";
    }

    // 确认收货界面点击确认支付
    @RequestMapping(value = "/foreorderConfirmed")
    public String orderConfirm(Integer oid) {
        Order order = orderService.get(oid);
        order.setStatus(OrderService.waitReview);
        order.setConfirmDate(new Date());

        orderService.update(order);

        return "fore/orderConfirmed";
    }

    // 在我的订单界面点击删除按钮
    @RequestMapping("/foredeleteOrder")
    @ResponseBody
    public String deleteOrder(Integer oid){
        // 不会把订单记录真正从数据库删除，只是把订单状态标记为delete
        Order o = orderService.get(oid);
        o.setStatus(OrderService.delete);

        orderService.update(o);

        return "success";
    }

    @RequestMapping(value = "/forereview")
    public String review(Integer oid, Model model, boolean showonly) {
        Order order = orderService.get(oid);
        orderItemService.fill(order);
        Product product = order.getOrderItems().get(0).getProduct();
        List<Review> reviews = reviewService.getList(product.getId());
        productService.setSaleAndReviewNumber(product);
        model.addAttribute("showonly", showonly);
        model.addAttribute("p", product);
        model.addAttribute("o", order);
        model.addAttribute("reviews", reviews);

        return "fore/review";
    }

    // 提交评价内容
    @RequestMapping("/foredoreview")
    public String doreview( Model model,HttpSession session,@RequestParam("oid") int oid,@RequestParam("pid") int pid,String content) {
        Order o = orderService.get(oid);
        o.setStatus(OrderService.finish);
        orderService.update(o);
        content = HtmlUtils.htmlEscape(content);
        User user =(User)  session.getAttribute("user");

        Review review = new Review();
        review.setContent(content);
        review.setPid(pid);
        review.setCreateDate(new Date());
        review.setUid(user.getId());
        reviewService.add(review);

        return "redirect:forereview?oid="+oid+"&showonly=true";
    }
}
