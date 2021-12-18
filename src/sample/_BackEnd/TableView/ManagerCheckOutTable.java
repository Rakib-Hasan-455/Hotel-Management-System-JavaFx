package sample._BackEnd.TableView;

public class ManagerCheckOutTable {

    String roomno,  type,  capacity,  priceday,  totalprice,  checkedin,  checkedout, nid;

    public ManagerCheckOutTable(String nid, String roomno, String type, String capacity, String priceday, String totalprice, String checkedin, String checkedout) {
    this.roomno = roomno;
    this.type = type;
    this.capacity = capacity;
    this.priceday = priceday;
    this.totalprice = totalprice;
    this.checkedin = checkedin;
    this.checkedout = checkedout;
    this.nid = nid;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getPriceday() {
        return priceday;
    }

    public void setPriceday(String priceday) {
        this.priceday = priceday;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getCheckedin() {
        return checkedin;
    }

    public void setCheckedin(String checkedin) {
        this.checkedin = checkedin;
    }

    public String getCheckedout() {
        return checkedout;
    }

    public void setCheckedout(String checkedout) {
        this.checkedout = checkedout;
    }
}
