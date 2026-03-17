package com.example.backend.service.upbit;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.backend.data.dao.UserDao;
import com.example.backend.data.dto.upbit.AccountDto;
import com.example.backend.data.dto.upbit.HistoryDto;
import com.example.backend.data.entity.user.User;
import com.example.backend.upbitUtil.UpbitJwtUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.Json;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class UpbitService {
    private final UpbitJwtUtil upbitJwtUtil;
    private final TextEncryptor encryptor;
    private final UserDao userDao;
    private final ObjectMapper objectMapper;

    @Autowired
    public UpbitService(UpbitJwtUtil upbitJwtUtil, TextEncryptor encryptor, UserDao userDao, ObjectMapper objectMapper) {
        this.upbitJwtUtil = upbitJwtUtil;
        this.encryptor = encryptor;
        this.userDao = userDao;
        this.objectMapper = objectMapper;
    }

    public List<AccountDto> getAccount(Long id) {
        Optional<User> user = userDao.getUserById(id);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        String accessKey = encryptor.decrypt(user.get().getAccessKey());
        String secretKey = encryptor.decrypt(user.get().getSecretKey());

        if (accessKey == null || secretKey == null) {
            throw new RuntimeException("Access key or secret key is null for user ID: " + id);
        }
        String jwtToken = upbitJwtUtil.getJwtTokenForAccount(accessKey, secretKey);

        HttpHeaders accountHeader = new HttpHeaders();
        accountHeader.set("Content-Type", "application/json");
        accountHeader.set("Authorization", jwtToken);

        HttpEntity<String> entity = new HttpEntity<>(null, accountHeader);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> accountResponseEntity = restTemplate.exchange("https://api.upbit.com/v1/accounts", HttpMethod.GET, entity, String.class);

        JsonNode accountJsonResponse;
        try {
            accountJsonResponse = objectMapper.readTree(accountResponseEntity.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while fetching account information", e);
        }

        List<AccountDto> accountDtoList = new ArrayList<>();

        for (JsonNode accountInfo : accountJsonResponse) {
            AccountDto accountDto = new AccountDto();
            String currency = accountInfo.get("currency").asText();
            double balance = accountInfo.get("balance").asDouble();
            double avgBuyPrice = accountInfo.get("avg_buy_price").asDouble();
            String unitCurrency = accountInfo.get("unit_currency").asText();

            accountDto.setCurrency(currency);
            accountDto.setBalance(balance);
            accountDto.setAvg_buy_price(avgBuyPrice);

            if (!currency.equals("KRW")) {
                accountDto.setBuyAssets(balance * avgBuyPrice);
                String url = "https://api.upbit.com/v1/candles/minutes/1?market="
                        + unitCurrency + "-" + currency + "&count=1";

                HttpHeaders marketHeader = new HttpHeaders();
                marketHeader.set("accept", "application/json");

                ResponseEntity<String> marketResponseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
                JsonNode marketJsonResponse;
                try {
                    marketJsonResponse = objectMapper.readTree(marketResponseEntity.getBody());
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("Error occurred while fetching market information", e);
                }

                double tradePrice = marketJsonResponse.get(0).get("trade_price").asDouble();
                accountDto.setNow_price(tradePrice);
                accountDto.setNowAssets(tradePrice * balance);
                accountDto.setReturn_rate((balance * avgBuyPrice - tradePrice * balance) / (tradePrice * balance) * 100);
            }

            accountDtoList.add(accountDto);
        }

        return accountDtoList;
    }

   public List<HistoryDto> getHistory(Long id) throws UnsupportedEncodingException, NoSuchAlgorithmException {
       Optional<User> user = userDao.getUserById(id);

       if (user.isEmpty()) {
           throw new RuntimeException("User not found with ID: " + id);
       }
       String accessKey = encryptor.decrypt(user.get().getAccessKey());
       String secretKey = encryptor.decrypt(user.get().getSecretKey());

       if (accessKey == null || secretKey == null) {
           throw new RuntimeException("Access key or secret key is null for user ID: " + id);
       }
       String jwtToken = upbitJwtUtil.getJwtTokenForHistory(accessKey, secretKey);

       HttpHeaders headers = new HttpHeaders();
       headers.set("Content-Type", "application/json");
       headers.set("Authorization", jwtToken);

       HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

       HashMap<String, String> params = new HashMap<>();
       params.put("state", "done");

       UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.upbit.com/v1/orders");
       for (Map.Entry<String, String> entry : params.entrySet()) {
           builder.queryParam(entry.getKey(), entry.getValue());
       }
       String finalUrl = builder.toUriString();

       RestTemplate restTemplate = new RestTemplate();
       ResponseEntity<String> responseEntity = restTemplate.exchange(finalUrl, HttpMethod.GET, httpEntity, String.class);

       JsonNode jsonResponse;
       try{
           jsonResponse = objectMapper.readTree(responseEntity.getBody());
       }catch (Exception e){
           e.printStackTrace();
           throw new RuntimeException("Error occurred while fetching account information", e);
       }

       List<HistoryDto> historyDtoList = new ArrayList<>();

       for(JsonNode info: jsonResponse){
           HistoryDto historyDto = new HistoryDto();
           String market = info.get("market").asText();
           String side = info.get("side").asText();
           double price = info.get("price").asDouble();
           String state = info.get("state").asText();
           String createdAt = info.get("created_at").asText();
           double volume = info.get("volume").asDouble();
           double executedVolume = info.get("executed_volume").asDouble();
           double remainingVolume = info.get("remaining_volume").asDouble();

           historyDto.setMarket(market);
           historyDto.setSide(side);
           historyDto.setPrice(price);
           historyDto.setState(state);
           historyDto.setCreated_at(createdAt);
           historyDto.setVolume(volume);
           historyDto.setExecuted_volume(executedVolume);
           historyDto.setRemaining_volume(remainingVolume);


           historyDtoList.add(historyDto);
       }
       return historyDtoList;
   }

}
