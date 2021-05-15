import bootstrap.InitDatabase;
import dao.CustomerDao;
import model.Customer;

import java.util.List;
import java.util.logging.Logger;


public class Client {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("JDBC-CRUD");
        // Creates the table "CUSTOMERS"
        InitDatabase.init();
        CustomerDao repository = new CustomerDao();
        // Create a new Customer model and stores it using the repository method
        Customer dummy = new Customer();
        dummy.setFirstName("Alessio");
        dummy.setLastName("Maddaluno");
        dummy.setEmail("alessio.maddaluno@gmail.com");
        repository.save(dummy);
        // Fetch all data from the table
        List<Customer> res = repository.findAll();
        logger.info(res.toString());
    }
}
