package com.example.Search.Search.SearchService;


import com.example.Search.Common.Entity.MemberStock;
import com.example.Search.Common.Entity.Stock;
import com.example.Search.Common.Repository.MemberStockRepository;
import com.example.Search.Common.Repository.StockRepository;
import com.example.Search.Search.Api.KisService;
import com.example.Search.Search.SearchDTO.DailyStockPriceDTO;
import com.example.Search.Search.SearchDTO.SearchResponseDto;
import com.example.Search.Search.SearchDTO.StockDetailDTO;
import com.example.Search.Search.SearchDTO.StockPriceListDTO;
import com.example.Search.Search.SearchDTO.StocksDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    private final StockRepository stockRepository;
    private final KisService kisService;
    private final MemberStockRepository memberStockRepository;

    public SearchService(StockRepository stockRepository, KisService kisService,
                         MemberStockRepository memberStockRepository) {
        this.stockRepository = stockRepository;
        this.kisService = kisService;
        this.memberStockRepository = memberStockRepository;
    }

    public List<StocksDTO> getStocks(Long memberId) {
        List<StocksDTO> result = new ArrayList<>();

        List<MemberStock> memberStocks = memberStockRepository.findTop5ByMemberIdOrderByAmountDesc(memberId,
                PageRequest.of(0, 5));
        List<String> memberStockCodes = new ArrayList<>();
        int idx = 0;

        for (MemberStock memberStock : memberStocks) {
            String code = memberStock.getStockCode();
            memberStockCodes.add(code);
            Optional<Stock> stockOptional = stockRepository.findById(code);
            if (stockOptional.isPresent()) {
                result.add(createStocksDTO(stockOptional.get(), idx++));
            }
        }

        int targetCount = 5;
        int remainingCount = targetCount - result.size();

        if (remainingCount > 0) {
            List<Stock> randomStocks;
            if (memberStockCodes.isEmpty()) {
                randomStocks = stockRepository.findRandomStocks(PageRequest.of(0, remainingCount));
            } else {
                randomStocks = stockRepository.findRandomStocksExcluding(memberStockCodes, remainingCount);
            }

            for (Stock stock : randomStocks) {
                result.add(createStocksDTO(stock, idx++));
            }
        }

        if (result.isEmpty()) {
            idx = 0;
            List<Stock> anyStocks = stockRepository.findRandomStocks(PageRequest.of(0, 5));
            for (Stock stock : anyStocks) {
                result.add(createStocksDTO(stock, idx++));
            }
        }

        return result;
    }

    private StocksDTO createStocksDTO(Stock stock, int idx) {
        SearchResponseDto searchResponseDto = (idx % 2 == 0)
                ? kisService.getCurrentAPrice(stock.getCode())
                : kisService.getCurrentBPrice(stock.getCode());

        return new StocksDTO(
                stock.getName(),
                stock.getCode(),
                searchResponseDto.getCurrentPrice().intValue(),
                searchResponseDto.getPreviousPrice(),
                searchResponseDto.getPreviousRate()
        );

    }

    public StockDetailDTO getStockByCode(String code, Long memberId) {
        Optional<Stock> stockOptional = stockRepository.findById(code);
        if (stockOptional.isPresent()) {
            Stock stock = stockOptional.get();

            SearchResponseDto searchResponseDto = kisService.getCurrentAPrice(stock.getCode());

            Double availableAmount = memberStockRepository.findAvailableAmountByMemberIdAndStockCode(memberId,
                    stock.getCode()).orElse(0.0);
            return new StockDetailDTO(stock.getName(), stock.getCode(), searchResponseDto.getCurrentPrice().intValue(),
                    availableAmount,
                    searchResponseDto.getPreviousPrice(), searchResponseDto.getPreviousRate());
        } else {
            return null;
        }
    }

    public StockPriceListDTO getStockPriceList(String code, String period) {
        List<DailyStockPriceDTO> stockPriceList = kisService.getStockChartList(code, period);
        return new StockPriceListDTO(stockPriceList);
    }


    public List<StocksDTO> searchSimilarStocks(String keyword) {
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<Stock> similarStocks = stockRepository.findSimilarStocks(keyword, pageRequest);

        return IntStream.range(0, similarStocks.getContent().size())
                .mapToObj(idx -> createStocksDTO(similarStocks.getContent().get(idx), idx))
                .collect(Collectors.toList());
    }
}

