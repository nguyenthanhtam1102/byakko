package com.byakko.service.authentication.domain.domainapplication.dto.response;

import com.byakko.service.authentication.dataaccess.entity.Menu;
import com.byakko.service.authentication.dataaccess.entity.Page;
import lombok.Data;

@Data
public class MenuItemResponse {
    private int id;
    private String name;
    private Menu menu;
    private Page page;
    private int parent_id;

}
