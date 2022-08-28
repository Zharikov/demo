package com.example.demo.response;

import com.example.demo.exception.ResponseException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCauseMessage;


public class ResponseBuilder {
    private static final String DEFAULT_ERROR_MESSAGE = "Серверная ошибка";

    public static ResponseProperties execute(ResponseConsumer consumer) {
        return new ResponseProperties(consumer);
    }

    public static ResponseProperties execute(ResponseSupplier supplier) {
        return new ResponseProperties(supplier);
    }

    public static ResponseProperties fromException(Exception e) {
        return new ResponseProperties(e);
    }

    public static class ResponseProperties {
        private ResponseCode code = ResponseCode.SUCCESS;
        private Object data;
        private String errorMessage;
        private String detailErrorMessage;
        private String stackTrace;

        public ResponseProperties(ResponseConsumer consumer) {
            try {
                consumer.accept();
            } catch (ResponseException e) {
                proceedResponseException(e);
            } catch (Exception e) {
                proceedException(e);
            }
        }

        public ResponseProperties(ResponseSupplier supplier) {
            try {
                data = supplier.get();
            } catch (ResponseException e) {
                proceedResponseException(e);
            } catch (Exception e) {
                proceedException(e);
            }
        }

        public ResponseProperties(Exception e) {
            if (e instanceof ResponseException) {
                proceedResponseException((ResponseException) e);
            } else {
                proceedException(e);
            }
        }

        public ResponseEntity get() {
            return ResponseEntity.ok(new ResponseData(data, code.getCode(), errorMessage, detailErrorMessage, stackTrace));
        }

        private void proceedResponseException(ResponseException e) {
            code = ResponseCode.KNOWN_ERROR;
            errorMessage = e.getMessage();
            detailErrorMessage = getCauseMessage(e);
            stackTrace = ExceptionUtils.getStackTrace(e);

        }

        private void proceedException(Exception e) {
            code = ResponseCode.UNKNOWN_ERROR;
            errorMessage = DEFAULT_ERROR_MESSAGE;
            detailErrorMessage = getRootCauseMessage(e);
            stackTrace = ExceptionUtils.getStackTrace(e);

        }

        private String getCauseMessage(final ResponseException e) {
            return getRootCauseMessage(e);
        }

        private enum ResponseCode {
            SUCCESS(0),
            KNOWN_ERROR(-1),
            UNKNOWN_ERROR(-2);

            private int code;

            ResponseCode(int code) {
                this.code = code;
            }

            public int getCode() {
                return code;
            }
        }
    }
    public static class ResponseData {
        Object data;
        Integer code;
        String errorMessage;
        String detailErrorMessage;
        String stackTrace;

        public ResponseData(Object data, Integer code, String errorMessage, String detailErrorMessage, String stackTrace) {
            this.data = data;
            this.code = code;
            this.errorMessage = errorMessage;
            this.detailErrorMessage = detailErrorMessage;
            this.stackTrace = stackTrace;
        }

        public String getStackTrace() {
            return stackTrace;
        }

        public void setStackTrace(String stackTrace) {
            this.stackTrace = stackTrace;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getDetailErrorMessage() {
            return detailErrorMessage;
        }

        public void setDetailErrorMessage(String detailErrorMessage) {
            this.detailErrorMessage = detailErrorMessage;
        }
    }
}
