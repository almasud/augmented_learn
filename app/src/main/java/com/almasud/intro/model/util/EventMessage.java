package com.almasud.intro.model.util;

import java.io.Serializable;

/**
 * An utility model class for event message.
 *
 * @author Abdullah Almasud
 */
public class EventMessage implements Serializable {
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_SUCCESS = 1;
    public static final int TYPE_WARNING = 2;
    public static final int TYPE_ERROR = 3;

    private String message;
    private int messageType;

    public EventMessage(String message, int messageType) {
        this.message = message;
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public int getMessageType() {
        return messageType;
    }

    public boolean isNormal() {
        return this.messageType == TYPE_NORMAL;
    }

    public boolean isSuccess() {
        return this.messageType == TYPE_SUCCESS;
    }

    public boolean isWarning() {
        return this.messageType == TYPE_WARNING;
    }

    public boolean isError() {
        return this.messageType == TYPE_ERROR;
    }
}
