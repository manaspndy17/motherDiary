package com.motherdiary.motherdiary.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.motherdiary.motherdiary.model.UserIdentity;
import com.motherdiary.motherdiary.model.item_entry;
import com.motherdiary.motherdiary.model.items;
import com.motherdiary.motherdiary.repository.ItemEntryRepo;
import com.motherdiary.motherdiary.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashCont {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemEntryRepo itemEntryRepo;

    @GetMapping("/dashboard")
    public String dashboard(
            @RequestParam(value = "page", required = false, defaultValue = "item-entry") String page,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            HttpSession session, Model model) {

        String username = (String) session.getAttribute("username");
        Integer userId = (Integer) session.getAttribute("user_id");

        if (username == null || userId == null) {
            return "redirect:/signin";
        }

        model.addAttribute("username", username);
        model.addAttribute("page", page);
        
        UserIdentity user = userService.findById(userId);
        model.addAttribute("user",user);

        // For Item Entry page: show user-specific item list
        if ("item-entry".equals(page)) {
            List<items> itemList = userService.findByUser(user);
            model.addAttribute("items", itemList);
            
        }

        // For Expenditure page
        if ("expenditure".equals(page)) {
            List<item_entry> entries;

            if (startDate != null && endDate != null) {
                LocalDate start = LocalDate.parse(startDate);
                LocalDate end = LocalDate.parse(endDate);
                entries = itemEntryRepo.findByUserAndEntryDateBetween(user, start, end);
            } else {
                entries = itemEntryRepo.findByUser(user);
            }

            List<Map<String, Object>> expenditureList = new ArrayList<>();

            for (item_entry entry : entries) {
                items item = entry.getItem();
                if (item != null) {
                    BigDecimal total = item.getUnitPrice().multiply(BigDecimal.valueOf(entry.getUnits()));
                    Map<String, Object> map = new HashMap<>();
                    map.put("itemName", item.getItemName());
                    map.put("unitPrice", item.getUnitPrice());
                    map.put("units", entry.getUnits());
                    map.put("date", entry.getEntryDate());
                    map.put("total", total);
                    expenditureList.add(map);
                }
            }
            BigDecimal grandTotal = expenditureList.stream()
                    .map(e -> (BigDecimal) e.get("total"))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            model.addAttribute("expenditures", expenditureList);
            model.addAttribute("grandTotal", grandTotal);
        }

        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/signin";
    }
}
