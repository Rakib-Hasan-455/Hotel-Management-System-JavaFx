package sample._BackEnd.TableView;

public class ManagerRoomTable {
    String ROOMNO;
    String TYPE;
    String CAPACITY;
    String PRICEDAY;
    String STATUS;

    public ManagerRoomTable(String ROOMNO, String TYPE, String CAPACITY, String PRICEDAY, String STATUS){
        this.ROOMNO = ROOMNO;
        this.TYPE = TYPE;
        this.CAPACITY = CAPACITY;
        this.PRICEDAY = PRICEDAY;
        this.STATUS = STATUS;
    }

    public String getROOMNO() {
        return ROOMNO;
    }

    public void setROOMNO(String ROOMNO) {
        this.ROOMNO = ROOMNO;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getCAPACITY() {
        return CAPACITY;
    }

    public void setCAPACITY(String CAPACITY) {
        this.CAPACITY = CAPACITY;
    }

    public String getPRICEDAY() {
        return PRICEDAY;
    }

    public void setPRICEDAY(String PRICEDAY) {
        this.PRICEDAY = PRICEDAY;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
}
