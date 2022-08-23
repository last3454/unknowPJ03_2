package com.unknown.model.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionVO {

	private String loginId;

	private String loginNm;

	private String email;
}
