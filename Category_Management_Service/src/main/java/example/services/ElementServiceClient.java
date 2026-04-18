package example.services;

import example.dto.simplified.category.SimplifiedCategoryDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;

@Component
public class ElementServiceClient {

    private final RestTemplate restTemplate;
    private final String elementServiceBaseUrl = "http://localhost:8081";

    public ElementServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void notifyCategoryCreated(UUID categoryId, String name, String surname) {
        String url = elementServiceBaseUrl + "/categories";
        restTemplate.postForEntity(url, new SimplifiedCategoryDTO(categoryId, name, surname), Void.class);
    }

    public void notifyCategoryDeleted(UUID categoryId) {
        String url = elementServiceBaseUrl + "/categories/" + categoryId;
        restTemplate.delete(url);
    }
}

