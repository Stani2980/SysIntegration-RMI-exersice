/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studadminclient;

import interfaces.StudentDataInterface;
import java.rmi.Naming;
import java.util.List;

/**
 *
 * @author stanislavnovitski
 */
public class StudAdminClient {

    public static void getService() throws Exception {
        // name =  rmi:// + ServerIP +  /EngineName;
        String remoteEngine = "rmi://localhost/StudentRegistry";

        // Create local stub, lookup in the registry searching for the remote engine - the interface with the methods we want to use remotely
        StudentDataInterface sdi = (StudentDataInterface) Naming.lookup(remoteEngine);

        // Send requests to the remote services the usual way, as if they are local
        List<String> students = sdi.getStudentData();
        students.forEach((s) -> {
            System.out.println(s);
        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            getService();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
