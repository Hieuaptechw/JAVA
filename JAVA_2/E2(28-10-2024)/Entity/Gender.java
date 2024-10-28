package Entity;

public enum Gender {
    MALE("Male"),FEMALE("Female"),OTHER("Other");
    private String  genderLable;
    Gender(String genderLable) {
        this.genderLable = genderLable;
    }
    public String getGenderLable() {
        return this.genderLable;
    }

    public void setGenderLable(String genderLable) {
        this.genderLable = genderLable;
    }
}
