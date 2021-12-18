package sample._BackEnd.TableView;

public class ManagerCustomerTable {
    private String NID;
    private String Name;
    private String Email;
    private String Phone;
    private String Address;

    public ManagerCustomerTable(String nid, String name, String email, String phone, String address) {
        this.NID = nid;
        this.Name = name;
        this.Email = email;
        this.Phone = phone;
        this.Address = address;
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

}
