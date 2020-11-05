package com.kunkun.jpa.model.secondary;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 作用描述
 *
 * @author xiaoya.zhuge 19:02 2020-07-08
 */
@Entity
@Data
@Table(name = "dm_service_inspect_record")
public class DmServiceInspectRecord {


    @Id
    private Integer id;

    private Integer driverNo;

}
