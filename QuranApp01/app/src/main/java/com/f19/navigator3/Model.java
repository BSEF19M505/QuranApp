package com.f19.navigator3;

public class Model {

        private String arabic;
    private String urdu;

        @Override
        public String toString() {
            return "Model{" +
                    "arabic='" + arabic + '\'' +
                    ", urdu=" + urdu +
                    '}';
        }

    public Model(String arabic, String urdu) {
        this.arabic = arabic;
        this.urdu = urdu;
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
