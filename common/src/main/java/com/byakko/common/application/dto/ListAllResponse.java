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

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }


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

        public Long getTotal() {
            return total;
        }

        public void setTotal(Long total) {
            this.total = total;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public Integer getPerPage() {
            return perPage;
        }

        public void setPerPage(Integer perPage) {
            this.perPage = perPage;
        }

        public Integer getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        public Integer getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }

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
