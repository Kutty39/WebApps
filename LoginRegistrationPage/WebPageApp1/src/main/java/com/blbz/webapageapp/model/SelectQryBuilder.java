package com.blbz.webapageapp.model;

public class SelectQryBuilder {
	private String clmname = "";
	private String tblname = "";
	private String where = "";

	public SelectQryBuilder setColumname(String s) {
		String temps = "";
		if (!this.clmname.equals("")) {
			temps = ",";
		}
		this.clmname = this.clmname + temps + s;
		return this;
	}

	public SelectQryBuilder setTablename(String s) {
		this.tblname = s;
		return this;
	}

	public SelectQryBuilder setWhere(String columnname, String condition, String value) {
		String temps = "";
		if (!where.equals("")) {
			temps = ",";
		}
		this.where = this.where + temps + columnname + condition + "'" + value + "'";
		return this;
	}

	public SelectQryBuilder setWhere(String columnname, String condition, int value) {
		String temps = "";
		if (!where.equals("")) {
			temps = ",";
		}
		this.where = this.where + temps + columnname + condition + value;
		return this;
	}

	public String build() {
		return "select " + this.clmname + " from " + this.tblname + (this.where != null ? " where " + this.where : "");
	}
}
