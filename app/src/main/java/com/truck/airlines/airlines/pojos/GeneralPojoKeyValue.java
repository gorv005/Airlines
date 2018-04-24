package com.truck.airlines.airlines.pojos;

/**
 * Created by shagunverma on 29/11/17.
 */

public class GeneralPojoKeyValue {

//    All subjects=1, Language=2, Mathematics=3, Environment studies=4, Sports=5, Music=6, Science=7, Social studies=8, Accountancy=10, Biology=11, Business Studies=12, Chemistry=13, Computer Science=14, Economics=15, Engineering Drawing=16, Fine Arts=17, Geography=18, History=19, Home Science=20, Philosophy=21, Physics=22, Political Science=23, Psychology=24, Foreign Language=25, Botany=26, Zoology=27, Hindi=41, Marathi=42, Sanskrit=43, Sindhi=44, Urdu=45, English=46, Regional Language=47, Tamil=48, Telugu=49, Kannada=50, Malayalam=51, Art education=91, Health & physical education=92, Work education=93, other=0
    private String     key;

    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public GeneralPojoKeyValue() {
        super();
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String      value;
    @Override
    public String toString() {
        return value;
    }
}
