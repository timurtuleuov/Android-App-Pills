package space.tuleuov.pills;

public class Drug {
    private String name;
    private String dose;
    private String hour;
    private String minute;

    public Drug(String name, String dose, String hour, String minute){
        this.name = name;
        this.dose = dose;
        this.hour = hour;
        this.minute = minute;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDose(){
        return this.dose;
    }
    public void setDose(String dose){
        this.dose = dose;
    }

    public String getHour(){
        return this.hour;
    }
    public void setHour(String hour){
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }
    public void setMinute(String minute){
        this.minute = minute;
    }

    public String getTime(){
        return this.hour + ":" + this.minute;
    }
}
