package com.example.meetime.Domain.Response;

public class ResponseDto {

    private Long status;

    private Object content;

    public ResponseDto(Long status, Object content) {
        this.status = status;
        this.content = content;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
