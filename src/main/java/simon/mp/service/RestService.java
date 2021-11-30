package simon.mp.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import simon.mp.dataclass.SendEmailResp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class RestService {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(RestService.class);


    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public boolean getMpSessionCode(String appId, String appSecret, String jsCode) {
        String url = "https://sendgrid.com/v3/mail/send";

        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth("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");

        // create a map for post parameters
        Map<String, Object> map = new HashMap<>();
        map.put("from", 1);
        map.put("title", "Introduction to Spring Boot");
        map.put("body", "Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications.");



        // build the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity response = this.restTemplate.postForEntity(url, entity, SendEmailResp.class);
        if(response.getStatusCode() == HttpStatus.ACCEPTED) {
            return true;
        } else {
            return false;
        }
    }
}