package com.motherdiary.motherdiary.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.motherdiary.motherdiary.model.UserIdentity;
import com.motherdiary.motherdiary.model.item_entry;
import com.motherdiary.motherdiary.model.items;
import com.motherdiary.motherdiary.repository.ItemEntryRepo;
import com.motherdiary.motherdiary.repository.ItemRepo;
import com.motherdiary.motherdiary.repository.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/item")
public class ItemCont {

    @Autowired
    private ItemEntryRepo itemEntryRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/add")
    public String addItem(@RequestParam("item_name") String item_name,

                          @RequestParam("unit_price") BigDecimal unit_price,
                          @RequestParam("item_image") MultipartFile item_image,
                          HttpSession session) throws IOException {

        // Get user ID from session
        Integer userId = (Integer) session.getAttribute("user_id");

        // Fetch user entity
        UserIdentity user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return "redirect:/signin"; // Handle invalid session
        }

        // Save image
        String fileName = UUID.randomUUID().toString() + "_" + item_image.getOriginalFilename();
        Path imagePath = Paths.get("src/main/resources/static/images", fileName);
        Files.write(imagePath, item_image.getBytes());

        // Create item
        items item = new items();
        item.setUser(user);
        item.setItemName(item_name);
        item.setUnitPrice(unit_price);
        item.setItemImage("/images/" + fileName); 

        itemRepo.save(item); 

        return "redirect:/dashboard?page=add-item";
 
    }

@PostMapping("/itemEntry")
public String addEntry(@RequestParam int itemId, @RequestParam int unit, HttpSession session) {
    Integer userId = (Integer) session.getAttribute("user_id");

     UserIdentity user = userRepo.findById(userId).orElse(null);
     items item = itemRepo.findById(itemId).orElse(null);

     if (user == null || item == null) {
        return "redirect:/dashboard?page=item-entry";
    }


    item_entry entry = new item_entry();
    entry.setUser(user);
    entry.setItem(item);
    entry.setUnits(unit);
    entry.setEntryDate(LocalDate.now());

    itemEntryRepo.save(entry);

    return "redirect:/dashboard?page=item-entry";
}

}
