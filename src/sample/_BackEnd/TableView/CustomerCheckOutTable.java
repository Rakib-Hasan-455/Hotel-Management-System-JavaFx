package sample._BackEnd.TableView;

public class CustomerCheckOutTable {
    String nid, roomNo, checkedIndate, checkedOutDate, priceDay, totalPrice;
    public CustomerCheckOutTable(String nid, String roomNo, String checkedInDate, String checkedOutDate, String priceDay, String totalPrice) {
    this.nid = nid;
    this.roomNo = roomNo;
    this.checkedIndate = checkedInDate;
    this.checkedOutDate = checkedOutDate;
    this.priceDay = priceDay;
    this.totalPrice = totalPrice;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getCheckedIndate() {
        return checkedIndate;
    }

    public void setCheckedIndate(String checkedIndate) {
        this.checkedIndate = checkedIndate;
    }

    public String getCheckedOutDate() {
        return checkedOutDate;
    }

    public void setCheckedOutDate(String checkedOutDate) {
        this.checkedOutDate = checkedOutDate;
    }

    public String getPriceDay() {
        return priceDay;
    }

    public void setPriceDay(String priceDay) {
        this.priceDay = priceDay;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
