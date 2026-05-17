package com.constants;

public class ApiEndpoints {
	
	// Class To Store All API Endpoints
	
	// Token Endpoint
	public static final String token="/v1/oauth2/token";
	
	// Order Endpoints
	public static final String createOrder="/v2/checkout/orders";
	public static final String getOrder="/v2/checkout/orders/";
	public static final String updateOrder="/v2/checkout/orders/";
	public static final String confirmOrder="/v2/checkout/orders/{id}/confirm-payment-source";
	public static final String authorizePayment="/v2/checkout/orders/{id}/authorize";
	public static final String capturePayment="/v2/checkout/orders/{id}/capture";
	
	// Product Endpoints
	public static final String createProduct="/v1/catalogs/products";
	public static final String listProducts="/v1/catalogs/products";
	public static final String showProductDetails="/v1/catalogs/products/{id}";
	public static final String updateProductDetails="/v1/catalogs/products/{id}";

}
