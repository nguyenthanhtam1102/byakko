package com.byakko.common.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class ListAllResponse<T> {

    private List<T> data;
    private Pagination pagination;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Pagination {

        @JsonProperty("total")
        private Long total;

        @JsonProperty("count")
        private Integer count;

        @JsonProperty("per_page")
        private Integer perPage;

        @JsonProperty("current_page")
        private Integer currentPage;

        @JsonProperty("total_pages")
        private Integer totalPages;

        public static Pagination toPagination(Page page) {
            return Pagination.builder()
                    .total(page.getTotalElements())
                    .count(page.getNumberOfElements())
                    .perPage(page.getSize())
                    .currentPage(page.getNumber())
                    .totalPages(page.getTotalPages())
                    .build();
        }
    }

}
