package sample._BackEnd.TableView;

public class AdminCustomerTable {
    private String NID;
    private String Name;
    private String Email;
    private String Phone;
    private String Address;
    private String Pass;

public AdminCustomerTable(String NID, String Name, String Email, String Phone, String Address, String Pass){
    this.NID = NID;
    this.Name = Name;
    this.Email = Email;
    this.Phone = Phone;
    this.Address = Address;
    this.Pass = Pass;
}

    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }
}
