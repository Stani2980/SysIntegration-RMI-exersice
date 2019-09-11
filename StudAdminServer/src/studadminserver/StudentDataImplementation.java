/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studadminserver;

import interfaces.StudentDataInterface;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stanislavnovitski
 */
public class StudentDataImplementation extends UnicastRemoteObject implements StudentDataInterface {

    public StudentDataImplementation() throws RemoteException {
        super();
    }

    @Override
    public List<String> getStudentData() throws RemoteException {
        Connection conn = null;
        List students = new ArrayList<String>();
        try {
            Properties connectionProps = new Properties();
            connectionProps.put("user", "school");
            connectionProps.put("password", "Passw0rd");
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://207.154.222.88:3306/Student";
            conn = DriverManager.getConnection(url, connectionProps);

            String SQL = "SELECT * FROM Student.student;";
            PreparedStatement ps = conn.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                System.out.println(name + " " + email + " " + address);
                students.add("Name: " + name + " Email: " + email + " Address: " + address);
            }
            conn.close();

            Path currentRelativePath = Paths.get("");
            String filePath = currentRelativePath.toAbsolutePath().toString();
            File file = new File(filePath + "//src//newStudents.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String temp;
            while ((temp = br.readLine()) != null) {
                students.add(temp);
            }

        } catch (IOException | ClassNotFoundException | SQLException ex) {
            System.out.println("A DB error occured");
            System.out.println(ex.toString());
        }

        return students;
    }

}
