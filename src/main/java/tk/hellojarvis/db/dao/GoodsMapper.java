package tk.hellojarvis.db.dao;

import tk.hellojarvis.db.entity.Goods;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : JarvisHsu
 * @create 2020/07/29 21:58
 */
public interface GoodsMapper {
    public List<Goods> loadAll();

    public Goods loadById(int goodsId);

    public List<Goods> loadPage(Map<String, Integer> map);

}
