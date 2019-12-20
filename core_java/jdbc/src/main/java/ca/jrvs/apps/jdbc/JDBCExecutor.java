package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecutor {
    public static void main(String[] args) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "hplussport", "postgres", "password");

        try{
            Connection connection = dcm.getConnection();
            CustomerDAO customerDAO = new CustomerDAO((connection));
            //inserting values to table
//            Customer customer = new Customer();
//             customer.setFirstName("Manpreet");
//             customer.setLastName("Kaur");
//             customer.setEmail("manpreetk0294@gmail.com");
//             customer.setPhone("(647)111-1111");
//             customer.setAddress("10 xyz road");
//             customer.setCity("Toronto");
//             customer.setState("ON");;
//             customer.setZipCode("A1B2C3");

//             customerDAO.create((customer));

            // show a data by id
//            Customer customer = customerDAO.findById(708);
//            System.out.println(customer.getFirstName() + " " + customer.getLastName());

            //upcdate data
//            Customer customer = customerDAO.findById(10000);
//            System.out.println(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getEmail());
//            customer.setEmail("mann@jrvs.ca");
//            customer = customerDAO.update(customer);
//            System.out.println(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getEmail());

            //delete a data
//            Customer customer = new Customer();
//            customer.setFirstName("yo");
//            customer.setLastName("yo");
//            customer.setEmail("manpreetk0294@gmail.com");
//            customer.setPhone("(647)111-1111");
//            customer.setAddress("10 xyz road");
//            customer.setCity("yo");
//            customer.setState("ON");;
//            customer.setZipCode("A1B2C3");
//
//            Customer dbCustomer = customerDAO.create(customer);
//            System.out.println(dbCustomer);
//            dbCustomer = customerDAO.findById(dbCustomer.getId());
//            System.out.println(dbCustomer);
//            dbCustomer.setEmail("yo@gmail.com");
//            dbCustomer = customerDAO.update(dbCustomer);
//            System.out.println(dbCustomer);
//            customerDAO.delete(dbCustomer.getId());

            OrderDAO orderDao = new OrderDAO(connection);
            Order order = orderDao.findById(1000);
            System.out.println(order);

            }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

}
