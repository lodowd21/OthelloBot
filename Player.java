package edu.unca.csci312;

public class Player {

    private String color;
    private int diskNum = 30;

    public Player(String color, int diskNum){
        this.diskNum = diskNum;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDiskNum() {
        return diskNum;
    }

    public void setDiskNum(int diskNum) {
        this.diskNum = diskNum;
    }

    public String toString(){
        return this.color;
    }
    
}
// color
// diskNum
