package com.platform.api.bean;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import java.io.Serializable;

/**
 * @author Advance
 * @date 2022年07月13日 18:55
 * @since V1.0.0
 */
@Data
@ToString
public class APIPlatformLogPK implements Serializable {
    private static final long serialVersionUID = 1227225159376390524L;
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String serialId;
}
