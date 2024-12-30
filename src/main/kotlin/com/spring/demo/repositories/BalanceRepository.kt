package com.spring.demo.repositories

import com.spring.demo.entity.BBLAMBalance
import com.spring.demo.entity.SCBAMBalance
import org.springframework.data.jpa.repository.JpaRepository

interface BBLAMBalanceRepository : JpaRepository<BBLAMBalance, Long>
interface SCBAMBalanceRepository : JpaRepository<SCBAMBalance, Long>