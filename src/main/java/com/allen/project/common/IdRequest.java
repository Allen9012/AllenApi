package com.allen.project.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 获取id请求
 *
 * @author allen
 */
@Data
public class IdRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}