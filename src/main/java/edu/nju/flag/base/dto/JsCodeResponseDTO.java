package edu.nju.flag.base.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JsCodeResponseDTO
 *
 * @author xuan
 * @date 2018/12/17
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JsCodeResponseDTO {
    
    private String openid;
    
    private String session_key;
    
    private String unionid;
    
    private String errcode;
    
    private String errmsg;
    
    
    
}
