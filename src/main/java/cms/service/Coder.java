package cms.service;

import cms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Coder {

    @Autowired
    private  VehicleService vehicleService;





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

    public static String codeManagerFull(List<Systemmanager> list){
        String result ="";
        for (Systemmanager r:list){
            result = result + r.getFullname()+"@"+r.getUsername()+"@";
        }
        return result;
    }

    public String codeRegularFull(List<Regularuser> list){
        String result ="";
        int i = 0;
        for (Regularuser r:list){
            String vehicle;
            if(r.getVehicleid()!=null){
                vehicle=vehicleService.findById(r.getVehicleid()).getLicenseplate();
            }else {
                vehicle="none";
            }
            result = result + r.getFullname()+"@"+r.getUsername()+"@"+vehicle+"@"+r.getLicensenumber()+"@"+r.isAvailibility()+"@";
            i++;
        }
        return result;
    }
    public static String codeShipment(List<Shipment> list){
        String result ="";
        for (Shipment m:list){
            result = result +m.getShippingdate()+"@"+m.getStatus()+"@"+m.getCargo()+"@"+m.getDriver()+"@"+m.getDestination()+"@";
        }
        return result;
    }
}

