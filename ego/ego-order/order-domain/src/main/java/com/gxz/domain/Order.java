package com.gxz.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 
 */
public class Order implements Serializable {
    /**
     * 订单id
     */
    private Integer id;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 订单状态
     */
    private Byte orderStatus;

    /**
     * 发货状态
     */
    private Byte shippingStatus;

    /**
     * 支付状态
     */
    private Byte payStatus;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 国家
     */
    private Integer country;

    /**
     * 省份
     */
    private Integer province;

    /**
     * 城市
     */
    private Integer city;

    /**
     * 县区
     */
    private Integer district;

    /**
     * 乡镇
     */
    private Integer twon;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮政编码
     */
    private String zipcode;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 邮件
     */
    private String email;

    /**
     * 物流code
     */
    private String shippingCode;

    /**
     * 物流名称
     */
    private String shippingName;

    /**
     * 支付code
     */
    private String payCode;

    /**
     * 支付方式名称
     */
    private String payName;

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**
     * 商品总价
     */
    private BigDecimal goodsPrice;

    /**
     * 邮费
     */
    private BigDecimal shippingPrice;

    /**
     * 使用余额
     */
    private BigDecimal userMoney;

    /**
     * 优惠券抵扣
     */
    private BigDecimal couponPrice;

    /**
     * 使用积分
     */
    private Integer integral;

    /**
     * 使用积分抵多少钱
     */
    private BigDecimal integralMoney;

    /**
     * 应付款金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单总价
     */
    private BigDecimal totalAmount;

    /**
     * 下单时间
     */
    private Integer addTime;

    /**
     * 最后新发货时间
     */
    private Integer shippingTime;

    /**
     * 收货确认时间
     */
    private Integer confirmTime;

    /**
     * 支付时间
     */
    private Integer payTime;

    /**
     * 活动id
     */
    private Short orderPromId;

    /**
     * 活动优惠金额
     */
    private BigDecimal orderPromAmount;

    /**
     * 价格调整
     */
    private BigDecimal discount;

    /**
     * 用户备注
     */
    private String userNote;

    /**
     * 管理员备注
     */
    private String adminNote;

    /**
     * 父单单号
     */
    private String parentSn;

    /**
     * 是否已分成0未分成1已分成
     */
    private Byte isDistribut;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Byte getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(Byte shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public Byte getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public Integer getTwon() {
        return twon;
    }

    public void setTwon(Integer twon) {
        this.twon = twon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(BigDecimal shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public BigDecimal getIntegralMoney() {
        return integralMoney;
    }

    public void setIntegralMoney(BigDecimal integralMoney) {
        this.integralMoney = integralMoney;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public Integer getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Integer shippingTime) {
        this.shippingTime = shippingTime;
    }

    public Integer getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Integer confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Integer getPayTime() {
        return payTime;
    }

    public void setPayTime(Integer payTime) {
        this.payTime = payTime;
    }

    public Short getOrderPromId() {
        return orderPromId;
    }

    public void setOrderPromId(Short orderPromId) {
        this.orderPromId = orderPromId;
    }

    public BigDecimal getOrderPromAmount() {
        return orderPromAmount;
    }

    public void setOrderPromAmount(BigDecimal orderPromAmount) {
        this.orderPromAmount = orderPromAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    public String getAdminNote() {
        return adminNote;
    }

    public void setAdminNote(String adminNote) {
        this.adminNote = adminNote;
    }

    public String getParentSn() {
        return parentSn;
    }

    public void setParentSn(String parentSn) {
        this.parentSn = parentSn;
    }

    public Byte getIsDistribut() {
        return isDistribut;
    }

    public void setIsDistribut(Byte isDistribut) {
        this.isDistribut = isDistribut;
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
        Order other = (Order) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderSn() == null ? other.getOrderSn() == null : this.getOrderSn().equals(other.getOrderSn()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getShippingStatus() == null ? other.getShippingStatus() == null : this.getShippingStatus().equals(other.getShippingStatus()))
            && (this.getPayStatus() == null ? other.getPayStatus() == null : this.getPayStatus().equals(other.getPayStatus()))
            && (this.getConsignee() == null ? other.getConsignee() == null : this.getConsignee().equals(other.getConsignee()))
            && (this.getCountry() == null ? other.getCountry() == null : this.getCountry().equals(other.getCountry()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getDistrict() == null ? other.getDistrict() == null : this.getDistrict().equals(other.getDistrict()))
            && (this.getTwon() == null ? other.getTwon() == null : this.getTwon().equals(other.getTwon()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getZipcode() == null ? other.getZipcode() == null : this.getZipcode().equals(other.getZipcode()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getShippingCode() == null ? other.getShippingCode() == null : this.getShippingCode().equals(other.getShippingCode()))
            && (this.getShippingName() == null ? other.getShippingName() == null : this.getShippingName().equals(other.getShippingName()))
            && (this.getPayCode() == null ? other.getPayCode() == null : this.getPayCode().equals(other.getPayCode()))
            && (this.getPayName() == null ? other.getPayName() == null : this.getPayName().equals(other.getPayName()))
            && (this.getInvoiceTitle() == null ? other.getInvoiceTitle() == null : this.getInvoiceTitle().equals(other.getInvoiceTitle()))
            && (this.getGoodsPrice() == null ? other.getGoodsPrice() == null : this.getGoodsPrice().equals(other.getGoodsPrice()))
            && (this.getShippingPrice() == null ? other.getShippingPrice() == null : this.getShippingPrice().equals(other.getShippingPrice()))
            && (this.getUserMoney() == null ? other.getUserMoney() == null : this.getUserMoney().equals(other.getUserMoney()))
            && (this.getCouponPrice() == null ? other.getCouponPrice() == null : this.getCouponPrice().equals(other.getCouponPrice()))
            && (this.getIntegral() == null ? other.getIntegral() == null : this.getIntegral().equals(other.getIntegral()))
            && (this.getIntegralMoney() == null ? other.getIntegralMoney() == null : this.getIntegralMoney().equals(other.getIntegralMoney()))
            && (this.getOrderAmount() == null ? other.getOrderAmount() == null : this.getOrderAmount().equals(other.getOrderAmount()))
            && (this.getTotalAmount() == null ? other.getTotalAmount() == null : this.getTotalAmount().equals(other.getTotalAmount()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
            && (this.getShippingTime() == null ? other.getShippingTime() == null : this.getShippingTime().equals(other.getShippingTime()))
            && (this.getConfirmTime() == null ? other.getConfirmTime() == null : this.getConfirmTime().equals(other.getConfirmTime()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()))
            && (this.getOrderPromId() == null ? other.getOrderPromId() == null : this.getOrderPromId().equals(other.getOrderPromId()))
            && (this.getOrderPromAmount() == null ? other.getOrderPromAmount() == null : this.getOrderPromAmount().equals(other.getOrderPromAmount()))
            && (this.getDiscount() == null ? other.getDiscount() == null : this.getDiscount().equals(other.getDiscount()))
            && (this.getUserNote() == null ? other.getUserNote() == null : this.getUserNote().equals(other.getUserNote()))
            && (this.getAdminNote() == null ? other.getAdminNote() == null : this.getAdminNote().equals(other.getAdminNote()))
            && (this.getParentSn() == null ? other.getParentSn() == null : this.getParentSn().equals(other.getParentSn()))
            && (this.getIsDistribut() == null ? other.getIsDistribut() == null : this.getIsDistribut().equals(other.getIsDistribut()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderSn() == null) ? 0 : getOrderSn().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getShippingStatus() == null) ? 0 : getShippingStatus().hashCode());
        result = prime * result + ((getPayStatus() == null) ? 0 : getPayStatus().hashCode());
        result = prime * result + ((getConsignee() == null) ? 0 : getConsignee().hashCode());
        result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getDistrict() == null) ? 0 : getDistrict().hashCode());
        result = prime * result + ((getTwon() == null) ? 0 : getTwon().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getZipcode() == null) ? 0 : getZipcode().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getShippingCode() == null) ? 0 : getShippingCode().hashCode());
        result = prime * result + ((getShippingName() == null) ? 0 : getShippingName().hashCode());
        result = prime * result + ((getPayCode() == null) ? 0 : getPayCode().hashCode());
        result = prime * result + ((getPayName() == null) ? 0 : getPayName().hashCode());
        result = prime * result + ((getInvoiceTitle() == null) ? 0 : getInvoiceTitle().hashCode());
        result = prime * result + ((getGoodsPrice() == null) ? 0 : getGoodsPrice().hashCode());
        result = prime * result + ((getShippingPrice() == null) ? 0 : getShippingPrice().hashCode());
        result = prime * result + ((getUserMoney() == null) ? 0 : getUserMoney().hashCode());
        result = prime * result + ((getCouponPrice() == null) ? 0 : getCouponPrice().hashCode());
        result = prime * result + ((getIntegral() == null) ? 0 : getIntegral().hashCode());
        result = prime * result + ((getIntegralMoney() == null) ? 0 : getIntegralMoney().hashCode());
        result = prime * result + ((getOrderAmount() == null) ? 0 : getOrderAmount().hashCode());
        result = prime * result + ((getTotalAmount() == null) ? 0 : getTotalAmount().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        result = prime * result + ((getShippingTime() == null) ? 0 : getShippingTime().hashCode());
        result = prime * result + ((getConfirmTime() == null) ? 0 : getConfirmTime().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        result = prime * result + ((getOrderPromId() == null) ? 0 : getOrderPromId().hashCode());
        result = prime * result + ((getOrderPromAmount() == null) ? 0 : getOrderPromAmount().hashCode());
        result = prime * result + ((getDiscount() == null) ? 0 : getDiscount().hashCode());
        result = prime * result + ((getUserNote() == null) ? 0 : getUserNote().hashCode());
        result = prime * result + ((getAdminNote() == null) ? 0 : getAdminNote().hashCode());
        result = prime * result + ((getParentSn() == null) ? 0 : getParentSn().hashCode());
        result = prime * result + ((getIsDistribut() == null) ? 0 : getIsDistribut().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderSn=").append(orderSn);
        sb.append(", userId=").append(userId);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", shippingStatus=").append(shippingStatus);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", consignee=").append(consignee);
        sb.append(", country=").append(country);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", district=").append(district);
        sb.append(", twon=").append(twon);
        sb.append(", address=").append(address);
        sb.append(", zipcode=").append(zipcode);
        sb.append(", mobile=").append(mobile);
        sb.append(", email=").append(email);
        sb.append(", shippingCode=").append(shippingCode);
        sb.append(", shippingName=").append(shippingName);
        sb.append(", payCode=").append(payCode);
        sb.append(", payName=").append(payName);
        sb.append(", invoiceTitle=").append(invoiceTitle);
        sb.append(", goodsPrice=").append(goodsPrice);
        sb.append(", shippingPrice=").append(shippingPrice);
        sb.append(", userMoney=").append(userMoney);
        sb.append(", couponPrice=").append(couponPrice);
        sb.append(", integral=").append(integral);
        sb.append(", integralMoney=").append(integralMoney);
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", addTime=").append(addTime);
        sb.append(", shippingTime=").append(shippingTime);
        sb.append(", confirmTime=").append(confirmTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", orderPromId=").append(orderPromId);
        sb.append(", orderPromAmount=").append(orderPromAmount);
        sb.append(", discount=").append(discount);
        sb.append(", userNote=").append(userNote);
        sb.append(", adminNote=").append(adminNote);
        sb.append(", parentSn=").append(parentSn);
        sb.append(", isDistribut=").append(isDistribut);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}