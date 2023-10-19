package com.api.dtos;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
public class ResponseDTO<T> {
	private T data;
	private List< String > errors;
	
}
