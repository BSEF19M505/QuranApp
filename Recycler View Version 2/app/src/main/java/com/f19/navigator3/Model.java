package com.f19.navigator3;

public class Model {

        private String arabic;
    private String urdu;
    private String english;



    @Override
        public String toString() {
            return "Model{" +
                    "arabic='" + arabic + '\'' +
                    ", urdu='" + urdu + '\'' +
                    ", english=" + english +
                    '}';
        }

    public Model(String arabic, String urdu,String eng) {
        this.arabic = arabic;
        this.urdu = urdu;
        this.english=eng;
    }
    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getUrdu() {
        return urdu;
    }

    public void setUrdu(String urdu) {
        this.urdu = urdu;
    }
}
