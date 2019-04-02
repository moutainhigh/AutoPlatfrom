package com.gs.bean;

/**
*
*
*@author qm
*@since 2017-04-14 16:16:20
*/
public class AccessoriesType {
	private String accTypeId;
	private String accTypeName;
	private String accTypeDes;
	private String companyId;
	private String accTypeStatus;
	private Company company;

	@Override
	public String toString() {
		return "AccessoriesType{" +
				"accTypeId='" + accTypeId + '\'' +
				", accTypeName='" + accTypeName + '\'' +
				", accTypeDes='" + accTypeDes + '\'' +
				", companyId='" + companyId + '\'' +
				", accTypeStatus='" + accTypeStatus + '\'' +
				'}';
	}
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getAccTypeId(){
		return this.accTypeId;
	}
	public void setAccTypeId(String accTypeId){
		this.accTypeId=accTypeId;
	}

	public String getAccTypeName(){
		return this.accTypeName;
	}
	public void setAccTypeName(String accTypeName){
		this.accTypeName=accTypeName;
	}

	public String getAccTypeDes(){
		return this.accTypeDes;
	}
	public void setAccTypeDes(String accTypeDes){
		this.accTypeDes=accTypeDes;
	}

	public String getCompanyId(){
		return this.companyId;
	}
	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getAccTypeStatus(){
		return this.accTypeStatus;
	}
	public void setAccTypeStatus(String accTypeStatus){
		this.accTypeStatus=accTypeStatus;
	}

}