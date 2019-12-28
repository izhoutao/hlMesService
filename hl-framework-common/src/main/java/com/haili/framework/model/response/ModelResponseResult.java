package com.haili.framework.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ModelResponseResult<T> extends ResponseResult {
    T model;

    public ModelResponseResult(ResultCode resultCode, T model) {
        super(resultCode);
        this.model = model;
    }
}
