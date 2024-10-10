package org.example.aadphase02assignment.util;

import java.util.UUID;

public class AppUtil {
    public static String generateCustomerId(){
        return "CID-"+UUID.randomUUID().toString();
    }
    public static String generateOrderId(){
        return "OID-"+UUID.randomUUID().toString();
    }
    public static String generateItemId(){
        return "IID-"+UUID.randomUUID().toString();
    }
}
