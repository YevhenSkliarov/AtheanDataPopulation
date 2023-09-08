package Entity;

public class CustomerDao implements Dao<Customer>{
    @Override
    public Customer getData() {
        return new Customer();
    }
}
