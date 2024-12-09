package com.example.coachticketbookingapp.Object;

public class item {
    private String content;
    private String price;
    private int img;

    public item(String price, String from, String to,int img) {
        this.price = price;
        this.content="Tu " + from + " den " +to;
        this.img=img;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
