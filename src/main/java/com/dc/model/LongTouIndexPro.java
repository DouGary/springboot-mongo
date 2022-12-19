package com.dc.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="longTouIndexPro")
public class LongTouIndexPro implements  java.io.Serializable{

    @Id
    private String id;

	private int index;


    @Indexed
	private String trade_date;

    private int buy;
    private int sell;

    private String indexContent;


    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    public int getSell() {
        return sell;
    }

    public void setSell(int sell) {
        this.sell = sell;
    }

    public String getIndexContent() {
        return indexContent;
    }

    public void setIndexContent(String indexContent) {
        this.indexContent = indexContent;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(String trade_date) {
        this.trade_date = trade_date;
    }
}
