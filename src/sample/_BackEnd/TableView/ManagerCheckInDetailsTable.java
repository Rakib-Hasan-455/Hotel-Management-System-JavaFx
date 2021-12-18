package sample._BackEnd.TableView;

public class ManagerCheckInDetailsTable {

    String sino,  name,  roomno,  priceday,  checkedin,  address;

    public ManagerCheckInDetailsTable(String sino, String name, String roomno, String priceday, String checkedin, String address) {
    this.sino = sino;
    this.name = name;
    this.roomno = roomno;
    this.priceday = priceday;
    this.checkedin = checkedin;
    this.address = address;

    }

    public String getSino() {
        return sino;
    }

    public void setSino(String sino) {
        this.sino = sino;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public String getPriceday() {
        return priceday;
    }

    public void setPriceday(String priceday) {
        this.priceday = priceday;
    }

    public String getCheckedin() {
        return checkedin;
    }

    public void setCheckedin(String checkedin) {
        this.checkedin = checkedin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
