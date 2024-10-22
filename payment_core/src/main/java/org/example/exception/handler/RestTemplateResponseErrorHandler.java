package org.example.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.exception.CustomRestException;
import org.example.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Service
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is5xxServerError() ||
                response.getStatusCode().is4xxClientError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is5xxServerError()) {
            throw new HttpClientErrorException(response.getStatusCode());
        } else if (response.getStatusCode().is4xxClientError()) {
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                ObjectMapper objectMapper = new ObjectMapper();
                ErrorResponse errorResponse = objectMapper.readValue(response.getBody(), ErrorResponse.class);
                throw new CustomRestException(errorResponse);
            }
        }
    }
}
