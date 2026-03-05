package com.forohub.dto;

public record ApiResponse<T>(T data, String message, boolean success) {
}
