package com.org.smartwarehouse.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
	  @NotBlank(message = "Customer name is required")
	    private String customerName;

	    @NotBlank(message = "Product name is required")
	    private String productName;
	    
		@NotNull(message = "Quantity is required")
	    @Positive(message = "Quantity must be greater than zero")
	    private Integer quantity;

	    @NotBlank(message = "Status is required")
	    private String status;
}