package com.byakko.service.authentication;

import com.byakko.service.authentication.dataaccess.entity.PageEntity;
import com.byakko.service.authentication.dataaccess.repository.PageRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.byakko.service.authentication.dataaccess", "com.byakko.common.dataaccess" })
@EntityScan(basePackages = { "com.byakko.service.authentication.dataaccess", "com.byakko.common.dataaccess" })
@SpringBootApplication(scanBasePackages = "com.byakko")
public class AuthenticationServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServiceApplication.class, args);
    }
    @Autowired
    private PageRepository pageRepository;
    @Override
    public void run(String... args) throws Exception {
        if(pageRepository.findByPath("/admin/products")==null) {
            PageEntity pageProduct = new PageEntity();
            pageProduct.setName("Product");
            pageProduct.setPath("/admin/products");
            pageProduct.setDescription("product page");
            pageRepository.save(pageProduct);
        }
        if(pageRepository.findByPath("/admin/products/create")==null) {
            PageEntity pageCreateProduct = new PageEntity();
            pageCreateProduct.setName("Create Product");
            pageCreateProduct.setPath("/admin/products/create");
            pageCreateProduct.setDescription("product create page");
            pageRepository.save(pageCreateProduct);
        }
        if(pageRepository.findByPath("/admin/categories")==null) {
            PageEntity pageCreateProduct = new PageEntity();
            pageCreateProduct.setName("Create Categories");
            pageCreateProduct.setPath("/admin/categories");
            pageCreateProduct.setDescription("categories page");
            pageRepository.save(pageCreateProduct);
        }
        if(pageRepository.findByPath("/admin/suppliers")==null) {
            PageEntity pageCreateProduct = new PageEntity();
            pageCreateProduct.setName("Supplier");
            pageCreateProduct.setPath("/admin/suppliers");
            pageCreateProduct.setDescription("supplier page");
            pageRepository.save(pageCreateProduct);
        }
        if(pageRepository.findByPath("/admin/suppliers/create")==null) {
            PageEntity pageCreateProduct = new PageEntity();
            pageCreateProduct.setName("Create suppliers");
            pageCreateProduct.setPath("/admin/suppliers/create");
            pageCreateProduct.setDescription("suppliers create page");
            pageRepository.save(pageCreateProduct);
        }
        if(pageRepository.findByPath("/admin/purchase-orders")==null) {
            PageEntity pageCreateProduct = new PageEntity();
            pageCreateProduct.setName("purchase-orders");
            pageCreateProduct.setPath("/admin/purchase-orders/add");
            pageCreateProduct.setDescription("create purchase-orders");
            pageRepository.save(pageCreateProduct);
        }
        if(pageRepository.findByPath("/admin/options")==null) {
            PageEntity pageOptions = new PageEntity();
            pageOptions.setName("Option");
            pageOptions.setPath("/admin/options");
            pageOptions.setDescription("product options");
            pageRepository.save(pageOptions);
        }
        if(pageRepository.findByPath("/admin/roles")==null) {
            PageEntity pageRole = new PageEntity();
            pageRole.setName("Role");
            pageRole.setPath("/admin/roles");
            pageRole.setDescription("role");
            pageRepository.save(pageRole);
        }
    }
}