package com.blbz.webapageapp.model;

public class InsertQryBuilder {
	private String clmname = "";
	private String tblname = "";
	private String values = "";

	public InsertQryBuilder setColumname(String columnname, String value) {
		String temps = "";
		if (!this.clmname.equals("")) {
			temps = ",";
		}
		this.clmname = this.clmname + temps + columnname;
		this.values = this.values + temps + "'" + value + "'";
		return this;
	}
	public InsertQryBuilder setColumname(String columnname, int value) {
		String temps = "";
		if (!this.clmname.equals("")) {
			temps = ",";
		}
		this.clmname = this.clmname + temps + columnname;
		this.values = this.values + temps  + value ;
		return this;
	}
	public InsertQryBuilder setTablename(String s) {
		this.tblname = s;
		return this;
	}


	public String build() {
		return "insert into " + this.tblname + " (" + this.clmname + ") values (" + this.values + ")";
	}
}
