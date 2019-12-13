package com.blbz.webapageapp.model;

import com.blbz.webapageapp.repository.WebAppDbActions;
import com.blbz.webapageapp.service.Utility;
import com.blbz.webapageapp.service.WebAppDb;
import com.blbz.webapageapp.utility.UtilityImpl;

public class WebPageAppModel {
	private static Utility util = null;
	private static WebAppDb db = null;

	public static Utility util() {
		if (util == null) {
			util = new UtilityImpl();
		}
		return util;
	}

	public static WebAppDb dbAction() {
		if (db == null) {
			db = new WebAppDbActions();
		}
		return db;
	}

	public static SelectQryBuilder selectQryBuilder() {
		return new SelectQryBuilder();
	}
	
	public static InsertQryBuilder insertQryBuilder() {
		return new InsertQryBuilder();
	}
	public static UpdateQryBuilder updateQryBuilder() {
		return new UpdateQryBuilder();
	}

}
