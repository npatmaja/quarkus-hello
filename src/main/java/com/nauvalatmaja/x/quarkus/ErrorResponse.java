package com.nauvalatmaja.x.quarkus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
	private String errorCode;
	private String errorDescription;
}
