package com.byakko.service.authentication.domain.domainapplication.dto.response;

import com.byakko.service.authentication.dataaccess.entity.MenuEntity;
import com.byakko.service.authentication.dataaccess.entity.PageEntity;
import lombok.Data;

@Data
public class MenuItemResponse {
    private int id;
    private String name;
    private MenuEntity menu;
    private PageEntity page;
    private int parent_id;

}
