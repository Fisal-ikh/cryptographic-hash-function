package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;


@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}

@RestController
@RequestMapping(value = "/api")
class ServerController {
    private static final String DATA_TO_HASH = "Fisal Ikhmayes";
    private static final String HASH_ALGORITHM = "SHA-256";

    @GetMapping(value = "/hash")
    public String myHash() {
        String value = null;
        try {
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] bytes = md.digest(DATA_TO_HASH.getBytes(StandardCharsets.UTF_8));
            value = bytesToHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            // handle the exception
            value = "Error calculating.";
            e.printStackTrace();
        }
        return "<p>Data: " + DATA_TO_HASH + "</p><p>Algorithm: " + HASH_ALGORITHM + "</p>CheckSum Value: " + value;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            stringBuilder.append(String.format("%02x", aByte));
        }
        return stringBuilder.toString();
    }
}