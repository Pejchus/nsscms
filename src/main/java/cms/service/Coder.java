package cms.service;

import cms.model.Message;
import cms.model.Regularuser;
import cms.model.Shipment;
import cms.model.Systemmanager;

import java.util.List;

public class Coder {
    public static String codeDriverMessage(List<Message> list){
        String result ="";
        for (Message m:list){
            result = result +m.getDispatcher()+"@"+m.getTime().toString()+"@"+m.getContent()+"@"+m.getSender()+"@";
        }
        return result;
    }

    public static String codeDispatchMessage(List<Message> list){
        String result ="";
        for (Message m:list){
            result = result +m.getDriver()+"@"+m.getTime().toString()+"@"+m.getContent()+"@"+m.getSender()+"@";
        }
        return result;
    }

    public static String codeRegular(List<Regularuser> list){
        String result ="";
        for (Regularuser r:list){
            result = result +r.getUsername()+"@";
        }
        return result;
    }
    public static String codeManager(List<Systemmanager> list){
        String result ="";
        for (Systemmanager r:list){
            result = result +r.getUsername()+"@";
        }
        return result;
    }
    public static String codeShipment(List<Shipment> list){
        String result ="";
        for (Shipment m:list){
            //result = result +m.+"@"+m.getTime().toString()+"@"+m.getContent()+"@"+m.getSender()+"@";
        }
        return result;
    }
}

