package com.example.buysell.controllers;

import com.example.buysell.models.Order;
import com.example.buysell.models.User;
import com.example.buysell.models.enums.Role;
import com.example.buysell.models.enums.Status;
import com.example.buysell.services.OrderService;
import com.example.buysell.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping("/admin/usersPanel")
    public String usersPanel(Model model) {
        model.addAttribute("users", userService.list());
        return "usersPanel";
    }

    @GetMapping("/admin/ordersPanel")
    public String ordersPanel(Model model) {
        model.addAttribute("orders", orderService.list());
        return "ordersPanel";
    }

    @PostMapping("/admin/order/delete/{id}")
    public String orderDelete(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return "ordersPanel";
    }

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String, String> form) {
        userService.changeUserRoles(user, form);
        return "redirect:/admin";
    }

    @GetMapping("/admin/order/edit/{order}")
    public String orderEdit(@PathVariable("order") Order order, Model model) {
        model.addAttribute("order", order);
        model.addAttribute("status", Status.values());
        return "order-edit";
    }

    @PostMapping("/admin/order/edit")
    public String orderEdit(@RequestParam("orderId") Order order, @RequestParam Map<String, String> form) {
        orderService.changeOrderStatus(order, form);
        return "redirect:/admin/ordersPanel";
    }

}