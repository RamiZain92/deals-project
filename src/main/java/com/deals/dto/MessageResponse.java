package com.deals.dto;

import java.io.Serializable;
public class MessageResponse implements Serializable{

    private static final long serialVersionUID = 103L;
    private String message;
    private Object data;
    private Integer status;

    public MessageResponse(String message) {
        this.message = message;
    }

    public static MessageResponseBuilder builder() {
        return new MessageResponseBuilder();
    }

    public String getMessage() {
        return this.message;
    }

    public Object getData() {
        return this.data;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setData(final Object data) {
        this.data = data;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MessageResponse)) {
            return false;
        } else {
            MessageResponse other = (MessageResponse)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$status = this.getStatus();
                    Object other$status = other.getStatus();
                    if (this$status == null) {
                        if (other$status == null) {
                            break label47;
                        }
                    } else if (this$status.equals(other$status)) {
                        break label47;
                    }

                    return false;
                }

                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MessageResponse;
    }

    public int hashCode() {
        int result = 1;
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getMessage();
        return "MessageResponse(message=" + var10000 + ", data=" + this.getData() + ", status=" + this.getStatus() + ")";
    }

    public MessageResponse(final String message, final Object data, final Integer status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public MessageResponse() {
    }

    public static class MessageResponseBuilder {
        private String message;
        private Object data;
        private Integer status;

        MessageResponseBuilder() {
        }

        public MessageResponseBuilder message(final String message) {
            this.message = message;
            return this;
        }

        public MessageResponseBuilder data(final Object data) {
            this.data = data;
            return this;
        }

        public MessageResponseBuilder status(final Integer status) {
            this.status = status;
            return this;
        }

        public MessageResponse build() {
            return new MessageResponse(this.message, this.data, this.status);
        }

        public String toString() {
            return "MessageResponse.MessageResponseBuilder(message=" + this.message + ", data=" + this.data + ", status=" + this.status + ")";
        }
    }
}
