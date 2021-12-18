package sample._BackEnd.TableView;

public class AdminEarningTable {

    String nid,  roomno,  type,  checkedin,  checkedout,  priceday,  totalprice;

    public AdminEarningTable(String nid, String roomno, String type, String checkedin, String checkedout, String priceday, String totalprice) {
    this.nid = nid;
    this.roomno = roomno;
    this.type = type;
    this.checkedin = checkedin;
    this.checkedout = checkedout;
    this.priceday = priceday;
    this.totalprice = totalprice;
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
}
