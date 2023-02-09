package com.zhangjiang.base.Enum;

/**
 * @className TypeEnum
 * @Author zhangjiang
 * @Description:
 * @Date 2022/11/5 21:57:38
 */
public enum ContractEnum {
    CSHZCONTRACTTOJXS("CSHZContractToJXS","融资租赁合同"),
    SALESCONTRACTINDUSTRIALPRODUCTS("SalesContractIndustrialProducts","产品买卖合同"),
    CSBACKCONFIRMATIONLETTER("CSBackConfirmationLetter","厂商回购函");

    ContractEnum(String type,String name){
        this.type = type;
        this.name = name;
    }
    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
