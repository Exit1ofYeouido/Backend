package com.example.Mypage.Common.Repository;

import com.example.Mypage.Common.Entity.MemberStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberStockRepository extends JpaRepository<MemberStock,Long> {
    MemberStock findByIdAndStockCode(Long memId, String code);

    List<MemberStock> findByMemberId(Long memId);


    @Query(nativeQuery = true, value = "SELECT * FROM Member_stock m WHERE :enterpriseName = m.stock_name AND m.member_id = :memId")
    MemberStock findByStockNameAndMember(@Param("enterpriseName") String enterpriseName,@Param("memId") Long memId);
}
