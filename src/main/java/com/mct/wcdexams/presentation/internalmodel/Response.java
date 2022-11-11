package com.mct.wcdexams.presentation.internalmodel;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Response {

    private boolean success;

    private String message;

    private Level level;

    private Object data;

    private String error;

    private Map<String, String> errors;

    public Response(boolean success, String message, Level level) {
        this.success = success;
        this.message = message;
        this.level = level;
    }

    public Response(boolean success, String message, Level level, Map<String, String> errors) {
        this.success = success;
        this.message = message;
        this.level = level;
        this.errors = errors;
    }

    @NotNull
    public static Response success() {
        return success("success", Level.success);
    }

    @NotNull
    public static Response success(String message) {
        return success(message, Level.success);
    }

    @NotNull
    public static Response success(String message, Level level) {
        return new Response(true, message, level);
    }

    @NotNull
    public static Response error(String message) {
        return error(message, Level.error);
    }

    @NotNull
    public static Response error(String message, Level level) {
        return new Response(false, message, level);
    }

    @NotNull
    public static Response error(Map<String, String> errors) {
        return new Response(false, null, Level.error, errors);
    }

    @NotNull
    public static Response error(Map<String, String> errors, Level level) {
        return new Response(false, null, level, errors);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public boolean isSuccess() {
        return success;
    }

    public Response setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public Response setLevel(Level level) {
        this.level = level;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public String getError() {
        return error;
    }

    public Response setError(String error) {
        this.error = error;
        return this;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public Response setErrors(Map<String, String> errors) {
        this.errors = errors;
        return this;
    }

    public enum Level {
        success, info, warning, error
    }
}
