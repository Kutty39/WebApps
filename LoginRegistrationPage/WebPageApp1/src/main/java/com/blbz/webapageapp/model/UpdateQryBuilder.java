package com.blbz.webapageapp.model;

public class UpdateQryBuilder {
	private String fielddata = "";
	private String tblname = "";
	private String where = "";

	public UpdateQryBuilder setFielddata(String columnname, String value) {
		String temps = "";
		if (!this.fielddata.equals("")) {
			temps = ",";
		}
		this.fielddata = this.fielddata + temps + columnname + "='" + value + "'";
		return this;
	}

	public UpdateQryBuilder setFielddata(String columnname, int value) {
		String temps = "";
		if (!this.fielddata.equals("")) {
			temps = ",";
		}
		this.fielddata = this.fielddata + temps + columnname + "=" + value;
		return this;
	}

	public UpdateQryBuilder setWhere(String columnname, String value) {
		this.where = columnname + "='" + value + "'";
		return this;
	}

	public UpdateQryBuilder setWhere(String columnname, int value) {
		this.where = columnname + "=" + value;
		return this;
	}

	public UpdateQryBuilder setTablename(String s) {
		this.tblname = s;
		return this;
	}

	public String build() {
		return "update " + this.tblname + " set "+ this.fielddata+" where "+this.where;
	}
}
