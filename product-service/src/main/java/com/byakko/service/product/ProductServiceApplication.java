package com.byakko.service.product;

import com.byakko.service.product.models.AdministrativeDivision;
import com.byakko.service.product.models.AdministrativeDivisionType;
import com.byakko.service.product.models.Supplier;
import com.byakko.service.product.repositories.AdministrativeDivisionRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@EnableJpaRepositories(basePackages = { "com.byakko.service.product", "com.byakko.common" })
@EntityScan(basePackages = { "com.byakko.service.product", "com.byakko.common" })
@SpringBootApplication(scanBasePackages = "com.byakko")
public class ProductServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AdministrativeDivisionRepository administrativeDivisionRepository;

    @Override
    public void run(String... args) throws Exception {
//        importAdministrativeDivisions();

        Supplier supplier = new Supplier();
        
    }

    private void importAdministrativeDivisions() throws Exception {
        String provincesJson = readJsonFile("provinces.json");
        String districtsJson = readJsonFile("districts.json");
        String communesJson = readJsonFile("communes.json");

        Map<String, AdministrativeDivision> provinces = objectMapper.readValue(provincesJson, new TypeReference<>() {});
        provinces.values().forEach(province -> {
            province.setEtype(AdministrativeDivisionType.PROVINCE);
            administrativeDivisionRepository.saveAndFlush(province);
        });

        Map<String, AdministrativeDivision> districts = objectMapper.readValue(districtsJson, new TypeReference<>() {});
        districts.values().forEach(district -> {
            district.setParent(provinces.get(district.getParentCode()));
            district.setEtype(AdministrativeDivisionType.DISTRICT);
            administrativeDivisionRepository.saveAndFlush(district);
        });

        Map<String, AdministrativeDivision> communes = objectMapper.readValue(communesJson, new TypeReference<>() {});
        communes.values().forEach(commune -> {
            commune.setParent(districts.get(commune.getParentCode()));
            commune.setEtype(AdministrativeDivisionType.COMMUNE);
            administrativeDivisionRepository.saveAndFlush(commune);
        });
    }

    public String readJsonFile(String fileName) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("file not found! " + fileName);
            } else {
                return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            }
        }
    }

}
