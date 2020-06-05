package common.tools;

import common.ocsf.server.ConnectionToClient;

import java.io.Serializable;
import java.sql.ResultSet;

public class Message implements Serializable {
    private static final long serialVersionUID = -5764940580602574742L;

    private OperationType operationType;
    private Object object;
    private ReturnMsgType returnMsgType;

    public Message(OperationType operationtype, Object object) {
        this.operationType = operationtype;
        this.object = object;
    }

    public Message(OperationType operationtype, ReturnMsgType returnmsg, Object object) {
        this.operationType = operationtype;
        this.object = object;
        this.returnMsgType = returnmsg;
    }



    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public ReturnMsgType getReturnMsgType() {
        return returnMsgType;
    }

    public void setReturnMsgType(ReturnMsgType returnMsgType) {
        this.returnMsgType = returnMsgType;
    }

    @Override
    public String toString() {
        return "Message{" +
                "operationType=" + operationType +
                ", object=" + object +
                ", returnMsgType=" + returnMsgType +
                '}';
    }
}
