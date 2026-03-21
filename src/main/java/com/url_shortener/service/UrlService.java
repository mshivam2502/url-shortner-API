package com.url_shortener.service;

import com.url_shortener.models.entity.UrlMapping;
import com.url_shortener.repository.UrlRepository;
import com.url_shortener.utils.Base62Encoder;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(String originalUrl, String shortCode) {
        UrlMapping entity = new UrlMapping();
        entity.setOriginalUrl(originalUrl);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryTime = now.plusHours(24);
        entity.setExpiryTime(expiryTime);

        if(shortCode.isEmpty()) {
            //Step 1: Save to get ID
            UrlMapping saved = urlRepository.save(entity);

            //Step 2: Generate Short Code
            shortCode = Base62Encoder.encode(saved.getId());

            //Step 3: Update shortCode
            saved.setShortCode(shortCode);
            urlRepository.save(saved);
        }else{

            //Validations
            UrlMapping map = urlRepository.findByShortCode(shortCode);

            if(map != null) {
                throw new EntityExistsException("Short code already exists!");
            }

            entity.setShortCode(shortCode);
            urlRepository.save(entity);
        }

        return shortCode;
    }

    public String findByShortCode(String shortCode) {

        UrlMapping urlMapping = urlRepository.findByShortCode(shortCode);

        if(urlMapping == null) {
            return "NOT_FOUND";
        }

        if(urlMapping.getExpiryTime() != null && urlMapping.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Url Expired");
        }

        //increment click count
        urlMapping.setClickCount(urlMapping.getClickCount() + 1);
        urlRepository.save(urlMapping);

        return urlMapping.getOriginalUrl();

    }

}
