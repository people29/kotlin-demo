package com.spring.demo.repository

import com.spring.demo.entity.BalanceEntity
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
class UnitholderBalanceRepository {
    @Transactional
    @Modifying
    @Query("""INSERT INTO unitholder_balance_tax_mf_report ()
                tax_id, 
                sa_code, 
                unitholder_id, 
                fund_code, 
                unit_balance, 
                amount_balance,
                cost_amount, 
                nav, 
                nav_date, 
                unit_balance_bf, 
                unit_balance_af, 
                cost_amount_bf,
                cost_amount_af, 
                allow_redeem_unit,
                as_of_date
        )
        SELECT tax_id,
               sa_code,
               unitholder_id,
               fund_code,
               unit_balance,
               amount_balance,
               cost_amount,
               nav,
               nav_date,
               unit_balance_bf,
               unit_balance_af,
               cost_amount_bf,
               cost_amount_af,
               allow_redeem_unit,
               as_of_date 
        FROM unitholder_balance_tax_mf """, nativeQuery = true)
    fun save() {
    }
}