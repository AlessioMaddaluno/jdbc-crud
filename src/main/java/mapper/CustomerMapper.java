package mapper;

import model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements BaseMapper<Customer> {
    @Override
    public Customer toModel(ResultSet resultSet) {
        Customer customer = new Customer();
        try {
            customer.setId(resultSet.getLong("ID_CUSTOMER"));
            customer.setEmail(resultSet.getString("EMAIL"));
            customer.setFirstName(resultSet.getString("FIRST_NAME"));
            customer.setLastName(resultSet.getString("LAST_NAME"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
