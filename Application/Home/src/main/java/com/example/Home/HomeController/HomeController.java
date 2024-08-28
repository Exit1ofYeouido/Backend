package com.example.Home.HomeController;

import com.example.Home.HomeDTO.HomeResponseDTO;
import com.example.Home.HomeService.HomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/home")
    public ResponseEntity<HomeResponseDTO> getHomeData() {
        HomeResponseDTO response = homeService.getHomeData();
        return ResponseEntity.ok().body(response);
    }
}
