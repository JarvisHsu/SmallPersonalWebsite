package tk.hellojarvis.db.entity;

/**
 * @author Lenovo
 */
public class Account {
    private int iD;
    private int uid;
    private double money;

    @Override
    public String toString() {
        return "Account{" +
                "iD=" + iD +
                ", uid=" + uid +
                ", money=" + money +
                '}';
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
