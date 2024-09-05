package com.example.Search.Search.SearchController;



import com.example.Search.Search.Api.KisService;
import com.example.Search.Search.SearchDTO.StockDetailDTO;
import com.example.Search.Search.SearchDTO.StockPriceListDTO;
import com.example.Search.Search.SearchDTO.StocksDTO;
import com.example.Search.Search.SearchService.SearchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="서치 API")
public class SearchController {

    private final SearchService searchService;
    private final KisService kisService;

    public SearchController(SearchService searchService, KisService kisService) {
        this.searchService = searchService;
        this.kisService = kisService;
    }

    @GetMapping("/stocks")
    public List<StocksDTO> getStocks(@RequestHeader String memberId) {
        return searchService.getStocks(Long.valueOf(memberId));
    }

    @GetMapping("/stock/{code}")
    public StockDetailDTO getStock(@PathVariable String code,
                                   @RequestHeader String memberId) {
        return searchService.getStockByCode(code, Long.valueOf(memberId));
    }

    @GetMapping("/stockPriceList/{code}")
    public StockPriceListDTO getStockPriceList(@PathVariable String code,
                                               @RequestParam(defaultValue = "1M") String period) {

        return searchService.getStockPriceList(code, period);
    }

    @GetMapping("/stocks/keyword")
    public List<StocksDTO> searchStocks(@RequestParam String query) {
        System.out.println("query === " + query);
        return searchService.searchSimilarStocks(query);
    }
}
