package com.haili.framework.domain.ucenter;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by admin on 2018/2/10.
 */
@Data
@ToString
@NoArgsConstructor
public class CompanyUser implements Serializable {
    private static final long serialVersionUID = -916357110051689786L;
    private String id;
    private String companyId;
    private String userId;
}
