package com.byakko.service.sales.service.product.repositories;

import com.byakko.service.sales.service.product.models.AdministrativeDivision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministrativeDivisionRepository extends JpaRepository<AdministrativeDivision, String> {

    @Query("select p from AdministrativeDivision p where p.etype = 'PROVINCE' order by p.slug asc ")
    List<AdministrativeDivision> listAllProvinces();

    @Query("select d from AdministrativeDivision d where d.parent.code = :provinceCode order by d.slug asc ")
    List<AdministrativeDivision> listAllDistrictsByProvince(@Param("provinceCode") String provinceCode);

    @Query("select c from AdministrativeDivision c where c.parent.code = :districtCode order by c.slug asc ")
    List<AdministrativeDivision> listAllCommunesByDistrict(@Param("districtCode") String districtCode);

}
