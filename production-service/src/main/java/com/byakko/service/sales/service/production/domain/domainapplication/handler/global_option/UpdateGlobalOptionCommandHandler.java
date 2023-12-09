package com.byakko.service.sales.service.production.domain.domainapplication.handler.global_option;

import com.byakko.service.sales.service.production.domain.domainapplication.dto.global_option.OptionResponse;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.global_option.UpdateGlobalOptionCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.mapper.OptionMapper;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.GlobalOptionRepository;
import com.byakko.service.sales.service.production.domain.domaincore.entity.GlobalOption;
import com.byakko.service.sales.service.production.domain.domaincore.entity.GlobalOptionValue;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateGlobalOptionCommandHandler {

    private final GlobalOptionRepository globalOptionRepository;
    private final GlobalOptionCommandHandlerHelper globalOptionCommandHandlerHelper;

    public UpdateGlobalOptionCommandHandler(GlobalOptionRepository globalOptionRepository, GlobalOptionCommandHandlerHelper globalOptionCommandHandlerHelper) {
        this.globalOptionRepository = globalOptionRepository;
        this.globalOptionCommandHandlerHelper = globalOptionCommandHandlerHelper;
    }

    @Transactional
    public OptionResponse handler(UpdateGlobalOptionCommand command) {
        GlobalOption option = globalOptionCommandHandlerHelper.findOptionById(command.getId());

        if(command.getName() != null) {
            option.setName(command.getName());
        }

        if(command.getValues() != null) {
            /* Remove các value không còn tồn tại trong danh sách mới
               Ví dụ: danh sách hiện tại là ['Black', 'White', 'Gray', 'Navy']
               Danh sách mới từ request là ['Black', 'White']
               Khi đó 'Gray', 'Navy' dư thừa và cần phải xóa đi
            */
            option.getValues().removeIf(value -> {
                if(!command.getValues().contains(value.getName())) {
//                    variantOptionRepository.deleteVariantOptionByOption(option);
                    return true;
                }
                return false;
            });

            /* Thêm các value mới vào trong danh sách hiện đang có
               Ví dụ: danh sách hiện đang có là ['Black', 'White']
               Danh sách mới từ request là ['Black', 'White', 'Gray']
               Gray không tồn tại trong danh sách hiện đang có nên cần add thêm vào
             */
            command.getValues().forEach(valueName -> {
                if(option.getValues().stream().noneMatch(value -> value.getName().equals(valueName))) {
                    option.getValues().add(new GlobalOptionValue(valueName, option));
                }
            });
        }

        option.validate();
        globalOptionRepository.save(option);

        return OptionMapper.toOptionResponse(option);
    }

}
