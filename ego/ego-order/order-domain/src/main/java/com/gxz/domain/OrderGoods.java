package com.gxz.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 
 */
public class OrderGoods implements Serializable {
    /**
     * 表id自增
     */
    private Integer id;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 视频名称
     */
    private String goodsName;

    /**
     * 商品货号
     */
    private String goodsSn;

    /**
     * 购买数量
     */
    private Short goodsNum;

    /**
     * 市场价
     */
    private BigDecimal marketPrice;

    /**
     * 本店价
     */
    private BigDecimal goodsPrice;

    /**
     * 商品成本价
     */
    private BigDecimal costPrice;

    /**
     * 会员折扣价
     */
    private BigDecimal memberGoodsPrice;

    /**
     * 购买商品赠送积分
     */
    private Integer giveIntegral;

    /**
     * 商品规格key
     */
    private String specKey;

    /**
     * 规格对应的中文名字
     */
    private String specKeyName;

    /**
     * 条码
     */
    private String barCode;

    /**
     * 是否评价
     */
    private Byte isComment;

    /**
     * 0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠
     */
    private Byte promType;

    /**
     * 活动id
     */
    private Integer promId;

    /**
     * 0未发货，1已发货，2已换货，3已退货
     */
    private Byte isSend;

    /**
     * 发货单ID
     */
    private Integer deliveryId;

    /**
     * sku
     */
    private String sku;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public Short getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Short goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getMemberGoodsPrice() {
        return memberGoodsPrice;
    }

    public void setMemberGoodsPrice(BigDecimal memberGoodsPrice) {
        this.memberGoodsPrice = memberGoodsPrice;
    }

    public Integer getGiveIntegral() {
        return giveIntegral;
    }

    public void setGiveIntegral(Integer giveIntegral) {
        this.giveIntegral = giveIntegral;
    }

    public String getSpecKey() {
        return specKey;
    }

    public void setSpecKey(String specKey) {
        this.specKey = specKey;
    }

    public String getSpecKeyName() {
        return specKeyName;
    }

    public void setSpecKeyName(String specKeyName) {
        this.specKeyName = specKeyName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Byte getIsComment() {
        return isComment;
    }

    public void setIsComment(Byte isComment) {
        this.isComment = isComment;
    }

    public Byte getPromType() {
        return promType;
    }

    public void setPromType(Byte promType) {
        this.promType = promType;
    }

    public Integer getPromId() {
        return promId;
    }

    public void setPromId(Integer promId) {
        this.promId = promId;
    }

    public Byte getIsSend() {
        return isSend;
    }

    public void setIsSend(Byte isSend) {
        this.isSend = isSend;
    }

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OrderGoods other = (OrderGoods) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getGoodsName() == null ? other.getGoodsName() == null : this.getGoodsName().equals(other.getGoodsName()))
            && (this.getGoodsSn() == null ? other.getGoodsSn() == null : this.getGoodsSn().equals(other.getGoodsSn()))
            && (this.getGoodsNum() == null ? other.getGoodsNum() == null : this.getGoodsNum().equals(other.getGoodsNum()))
            && (this.getMarketPrice() == null ? other.getMarketPrice() == null : this.getMarketPrice().equals(other.getMarketPrice()))
            && (this.getGoodsPrice() == null ? other.getGoodsPrice() == null : this.getGoodsPrice().equals(other.getGoodsPrice()))
            && (this.getCostPrice() == null ? other.getCostPrice() == null : this.getCostPrice().equals(other.getCostPrice()))
            && (this.getMemberGoodsPrice() == null ? other.getMemberGoodsPrice() == null : this.getMemberGoodsPrice().equals(other.getMemberGoodsPrice()))
            && (this.getGiveIntegral() == null ? other.getGiveIntegral() == null : this.getGiveIntegral().equals(other.getGiveIntegral()))
            && (this.getSpecKey() == null ? other.getSpecKey() == null : this.getSpecKey().equals(other.getSpecKey()))
            && (this.getSpecKeyName() == null ? other.getSpecKeyName() == null : this.getSpecKeyName().equals(other.getSpecKeyName()))
            && (this.getBarCode() == null ? other.getBarCode() == null : this.getBarCode().equals(other.getBarCode()))
            && (this.getIsComment() == null ? other.getIsComment() == null : this.getIsComment().equals(other.getIsComment()))
            && (this.getPromType() == null ? other.getPromType() == null : this.getPromType().equals(other.getPromType()))
            && (this.getPromId() == null ? other.getPromId() == null : this.getPromId().equals(other.getPromId()))
            && (this.getIsSend() == null ? other.getIsSend() == null : this.getIsSend().equals(other.getIsSend()))
            && (this.getDeliveryId() == null ? other.getDeliveryId() == null : this.getDeliveryId().equals(other.getDeliveryId()))
            && (this.getSku() == null ? other.getSku() == null : this.getSku().equals(other.getSku()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getGoodsName() == null) ? 0 : getGoodsName().hashCode());
        result = prime * result + ((getGoodsSn() == null) ? 0 : getGoodsSn().hashCode());
        result = prime * result + ((getGoodsNum() == null) ? 0 : getGoodsNum().hashCode());
        result = prime * result + ((getMarketPrice() == null) ? 0 : getMarketPrice().hashCode());
        result = prime * result + ((getGoodsPrice() == null) ? 0 : getGoodsPrice().hashCode());
        result = prime * result + ((getCostPrice() == null) ? 0 : getCostPrice().hashCode());
        result = prime * result + ((getMemberGoodsPrice() == null) ? 0 : getMemberGoodsPrice().hashCode());
        result = prime * result + ((getGiveIntegral() == null) ? 0 : getGiveIntegral().hashCode());
        result = prime * result + ((getSpecKey() == null) ? 0 : getSpecKey().hashCode());
        result = prime * result + ((getSpecKeyName() == null) ? 0 : getSpecKeyName().hashCode());
        result = prime * result + ((getBarCode() == null) ? 0 : getBarCode().hashCode());
        result = prime * result + ((getIsComment() == null) ? 0 : getIsComment().hashCode());
        result = prime * result + ((getPromType() == null) ? 0 : getPromType().hashCode());
        result = prime * result + ((getPromId() == null) ? 0 : getPromId().hashCode());
        result = prime * result + ((getIsSend() == null) ? 0 : getIsSend().hashCode());
        result = prime * result + ((getDeliveryId() == null) ? 0 : getDeliveryId().hashCode());
        result = prime * result + ((getSku() == null) ? 0 : getSku().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsSn=").append(goodsSn);
        sb.append(", goodsNum=").append(goodsNum);
        sb.append(", marketPrice=").append(marketPrice);
        sb.append(", goodsPrice=").append(goodsPrice);
        sb.append(", costPrice=").append(costPrice);
        sb.append(", memberGoodsPrice=").append(memberGoodsPrice);
        sb.append(", giveIntegral=").append(giveIntegral);
        sb.append(", specKey=").append(specKey);
        sb.append(", specKeyName=").append(specKeyName);
        sb.append(", barCode=").append(barCode);
        sb.append(", isComment=").append(isComment);
        sb.append(", promType=").append(promType);
        sb.append(", promId=").append(promId);
        sb.append(", isSend=").append(isSend);
        sb.append(", deliveryId=").append(deliveryId);
        sb.append(", sku=").append(sku);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}