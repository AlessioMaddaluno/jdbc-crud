package dao;

import db.DBConnection;
import mapper.CustomerMapper;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CustomerDao implements CrudDao<Customer, Long> {

    private Connection connection;
    private CustomerMapper mapper;

    public CustomerDao(){
        this.connection = DBConnection.getConnection();
        this.mapper = new CustomerMapper();
    }

    @Override
    public void save(Customer model) {
        String insertUserQuery = "INSERT INTO customers (FIRST_NAME,LAST_NAME,EMAIL) VALUES (?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(insertUserQuery)){
            preparedStatement.setString(1,model.getFirstName());
            preparedStatement.setString(2,model.getLastName());
            preparedStatement.setString(3,model.getEmail());
            preparedStatement.executeUpdate();
        }catch (SQLException e ){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Customer> findById(Long id) {
        String selectByIdQuery = "SELECT * FROM CUSTOMERS WHERE ID_CUSTOMER = ?";
        Customer customer = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(selectByIdQuery)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                customer = mapper.toModel(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Objects.nonNull(customer) ? Optional.of(customer):Optional.empty();
    }

    @Override
    public List<Customer> findAll() {
        String selectAllQuery = "SELECT * FROM CUSTOMERS";
        List<Customer> customers = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(selectAllQuery)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Customer customer = mapper.toModel(resultSet);
                customers.add(customer);
            }
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer update(Customer model) {
        String updateCustomerQuery = "UPDATE customers SET FIRST_NAME = ? , LAST_NAME = ? , EMAIL = ? WHERE ID_CUSTOMER = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(updateCustomerQuery)){
            preparedStatement.setString(1,model.getFirstName());
            preparedStatement.setString(2,model.getLastName());
            preparedStatement.setString(3,model.getEmail());
            preparedStatement.setLong(4,model.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e ){
            e.printStackTrace();
        }
        //NOTE: This is actually not a good practice since the data could be manipulated by a trigger.
        return model;
    }

    @Override
    public void delete(Long id) {
        String deleteQuery = "DELETE FROM customers WHERE ID_CUSTOMER = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        String deleteQuery = "DELETE FROM customers";
        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
