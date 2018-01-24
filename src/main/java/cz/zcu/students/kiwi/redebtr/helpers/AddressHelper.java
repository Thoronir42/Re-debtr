package cz.zcu.students.kiwi.redebtr.helpers;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class AddressHelper {

    public static boolean isLocalAddress(String address) {
        try {
            return isLocalAddress(InetAddress.getByName(address));
        } catch (UnknownHostException e) {
            return false;
        }
    }

    public static boolean isLocalAddress(InetAddress address) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            for (; networkInterfaces.hasMoreElements(); ) {
                NetworkInterface ni = networkInterfaces.nextElement();
                if(addressBelongsTo(address, ni)) {
                    return true;
                }
            }
        } catch (Exception ignored) {
        }

        return false;
    }

    private static boolean addressBelongsTo(InetAddress address, NetworkInterface ni) {
        for (InterfaceAddress addr : ni.getInterfaceAddresses()) {
            if (addr.getAddress().equals(address)) {
                return true;
            }
        }

        return false;
    }
}
