package com.kunkun.jpa.secondary;

import com.kunkun.jpa.model.primary.User;
import com.kunkun.jpa.model.secondary.DmServiceInspectRecord;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 作用描述
 *
 * @author xiaoya.zhuge 19:02 2020-07-08
 */
public interface DmServiceInspectRecordDao extends JpaRepository<DmServiceInspectRecord, Integer>,
    JpaSpecificationExecutor<DmServiceInspectRecord> {

    /**
     * 根据title查数据
     *
     * @param driverNo
     * @return
     */
    @Transactional
    @Query("SELECT dr.driverNo from DmServiceInspectRecord dr where dr.driverNo = :driverNo")
    String findDataByTitle(@Param("driverNo") Integer driverNo);
}
