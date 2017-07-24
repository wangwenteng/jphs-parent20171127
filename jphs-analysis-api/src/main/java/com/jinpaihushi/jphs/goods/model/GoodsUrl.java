package com.jinpaihushi.jphs.goods.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * GOODS_URL 商品地址表
 * 继承自父类的字段:
 * id : 	
 * type : 1、旧版，2、新版	
 * 
 * @author wangwt
 * @date 2017-06-13 15:01:42
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GoodsUrl extends BaseModel implements Predicate<GoodsUrl>, Updator<GoodsUrl> {

    /**  */
    @Length(max = 255, message = "{goodsUrl.path.illegal.length}")
    private String path;

    /**  */
    @Length(max = 255, message = "{goodsUrl.name.illegal.length}")
    private String name;

    /** url类型id */
    @Length(max = 50, message = "{goodsUrl.productId.illegal.length}")
    private String productId;

    public GoodsUrl() {
    }

    public GoodsUrl(String id) {
        this.id = id;
    }

    /**
     * 获取
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取url类型id
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置url类型id
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String toString() {
        return new StringBuilder().append("GoodsUrl{").append("id=").append(id).append(",path=").append(path)
                .append(",name=").append(name).append(",productId=").append(productId).append(",type=").append(type)
                .append('}').toString();
    }

    /**
     * 复制字段：
     * id, path, name, productId, 
     * type
     */
    public GoodsUrl copy() {
        GoodsUrl goodsUrl = new GoodsUrl();
        goodsUrl.id = this.id;
        goodsUrl.path = this.path;
        goodsUrl.name = this.name;
        goodsUrl.productId = this.productId;
        goodsUrl.type = this.type;
        return goodsUrl;
    }

    /**
     * 比较字段：
     * id, path, name, productId, 
     * type
     */
    @Override
    public boolean test(GoodsUrl t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id)) && (this.path == null || this.path.equals(t.path))
                && (this.name == null || this.name.equals(t.name))
                && (this.productId == null || this.productId.equals(t.productId))
                && (this.type == null || this.type.equals(t.type));
    }

    @Override
    public void update(GoodsUrl element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.path != null && !this.path.isEmpty()) {
            element.path = this.path;
        }
        if (this.name != null && !this.name.isEmpty()) {
            element.name = this.name;
        }
        if (this.productId != null && !this.productId.isEmpty()) {
            element.productId = this.productId;
        }
        if (this.type != null) {
            element.type = this.type;
        }
    }
}
