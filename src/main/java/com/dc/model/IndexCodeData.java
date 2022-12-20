package com.dc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="indexCodeData")
public class IndexCodeData implements  java.io.Serializable{

	@Id
	private String id;

	private double vol;


	private double v_ma10;
	private double v_ma20;

	private double ma5;
	private double price_change;

	private double v_ma5;
	private double p_change;
	private double high;
	@Indexed
	private String ts_code;

	private double ma20;
	private double low;
	@Indexed
	private String trade_date;
	private double close;
	private double open;
	private double ma10;
	private double turnover;

	private double ma30;
	private double ma50;
	private double ma60;
	private double ma120;
	private double ma240;

	private double pct_chg;

	private double ddd;
	private double ama;

	private double kdjk;
	private double kdjd;
	private double kdjj;

	private double rps50;

	private double amount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getVol() {
		return vol;
	}

	public void setVol(double vol) {
		this.vol = vol;
	}

	public double getV_ma10() {
		return v_ma10;
	}

	public void setV_ma10(double v_ma10) {
		this.v_ma10 = v_ma10;
	}

	public double getV_ma20() {
		return v_ma20;
	}

	public void setV_ma20(double v_ma20) {
		this.v_ma20 = v_ma20;
	}

	public double getMa5() {
		return ma5;
	}

	public void setMa5(double ma5) {
		this.ma5 = ma5;
	}

	public double getPrice_change() {
		return price_change;
	}

	public void setPrice_change(double price_change) {
		this.price_change = price_change;
	}

	public double getV_ma5() {
		return v_ma5;
	}

	public void setV_ma5(double v_ma5) {
		this.v_ma5 = v_ma5;
	}

	public double getP_change() {
		return p_change;
	}

	public void setP_change(double p_change) {
		this.p_change = p_change;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public String getTs_code() {
		return ts_code;
	}

	public void setTs_code(String ts_code) {
		this.ts_code = ts_code;
	}

	public double getMa20() {
		return ma20;
	}

	public void setMa20(double ma20) {
		this.ma20 = ma20;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public String getTrade_date() {
		return trade_date;
	}

	public void setTrade_date(String trade_date) {
		this.trade_date = trade_date;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getMa10() {
		return ma10;
	}

	public void setMa10(double ma10) {
		this.ma10 = ma10;
	}

	public double getTurnover() {
		return turnover;
	}

	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}

	public double getMa30() {
		return ma30;
	}

	public void setMa30(double ma30) {
		this.ma30 = ma30;
	}

	public double getMa50() {
		return ma50;
	}

	public void setMa50(double ma50) {
		this.ma50 = ma50;
	}

	public double getMa60() {
		return ma60;
	}

	public void setMa60(double ma60) {
		this.ma60 = ma60;
	}

	public double getMa120() {
		return ma120;
	}

	public void setMa120(double ma120) {
		this.ma120 = ma120;
	}

	public double getMa240() {
		return ma240;
	}

	public void setMa240(double ma240) {
		this.ma240 = ma240;
	}

	public double getPct_chg() {
		return pct_chg;
	}

	public void setPct_chg(double pct_chg) {
		this.pct_chg = pct_chg;
	}

	public double getDdd() {
		return ddd;
	}

	public void setDdd(double ddd) {
		this.ddd = ddd;
	}

	public double getAma() {
		return ama;
	}

	public void setAma(double ama) {
		this.ama = ama;
	}

	public double getKdjk() {
		return kdjk;
	}

	public void setKdjk(double kdjk) {
		this.kdjk = kdjk;
	}

	public double getKdjd() {
		return kdjd;
	}

	public void setKdjd(double kdjd) {
		this.kdjd = kdjd;
	}

	public double getKdjj() {
		return kdjj;
	}

	public void setKdjj(double kdjj) {
		this.kdjj = kdjj;
	}

	public double getRps50() {
		return rps50;
	}

	public void setRps50(double rps50) {
		this.rps50 = rps50;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
