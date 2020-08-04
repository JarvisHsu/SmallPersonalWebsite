package tk.hellojarvis.db.entity;

/**
 * 是程序中的持久化对象类 是映射数据库中goods数据表的数据的
 * 类的结构应该和goods数据表保持一致
 *
 * @author Lenovo
 */
public class Goods {
    private int goodsId;
    private String goodsName;
    private String goodsAuthor;
    private double goodsPrice;
    private String goodsInfo;

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsAuthor='" + goodsAuthor + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsInfo='" + goodsInfo + '\'' +
                '}';
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsAuthor() {
        return goodsAuthor;
    }

    public void setGoodsAuthor(String goodsAuthor) {
        this.goodsAuthor = goodsAuthor;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {

        this.goodsInfo = goodsInfo;
    }


}
