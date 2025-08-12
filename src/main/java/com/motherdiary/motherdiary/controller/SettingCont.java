package com.motherdiary.motherdiary.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.motherdiary.motherdiary.model.UserIdentity;
import com.motherdiary.motherdiary.repository.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/settings")
public class SettingCont {

    @Autowired
    private UserRepo userRepo;

    
    @GetMapping
    public String showSettingsPage(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("user_id");
        UserIdentity user = userRepo.findById(userId).orElse(null);

        if (user == null) return "redirect:/signin";

        model.addAttribute("user", user);
        return "settings"; 
    }

    
    @PostMapping("/update")
    public String updateSettings(@RequestParam("tell") String tell,
                                 @RequestParam("email") String email,
                                 @RequestParam(value = "profileImage", required = false) MultipartFile profileImage,
                                 @RequestParam(value = "coverImage", required = false) MultipartFile coverImage,
                                 HttpSession session) throws IOException {

        Integer userId = (Integer) session.getAttribute("user_id");
        UserIdentity user = userRepo.findById(userId).orElse(null);
        if (user == null) return "redirect:/signin";

        user.setTell(tell);
        user.setEmail(email);

        if (profileImage != null && !profileImage.isEmpty()) {
            String profileImageName = UUID.randomUUID() + "_" + profileImage.getOriginalFilename();
            Path profilePath = Paths.get("src/main/resources/static/profileImg", profileImageName);
            Files.write(profilePath, profileImage.getBytes());
            user.setProfileImage("/profileImg/" + profileImageName);
        }

        if (coverImage != null && !coverImage.isEmpty()) {
            String coverImageName = UUID.randomUUID() + "_" + coverImage.getOriginalFilename();
            Path coverPath = Paths.get("src/main/resources/static/coverImg", coverImageName);
            Files.write(coverPath, coverImage.getBytes());
            user.setCoverImage("/coverImg/" + coverImageName);
        }

        userRepo.save(user);
        return "redirect:/dashboard";
    }
}
