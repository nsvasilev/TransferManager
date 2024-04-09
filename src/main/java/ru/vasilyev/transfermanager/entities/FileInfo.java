package ru.vasilyev.transfermanager.entities;

public class FileInfo {
    private String name;
    private String soname;
    private String patronymic;
    private String gender;
    private int birthday;

    public FileInfo(String name, String soname, String patronymic, String gender, int birthday) {
        this.name = name;
        this.soname = soname;
        this.patronymic = patronymic;
        this.gender = gender;
        this.birthday = birthday;
    }

    public FileInfo() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoname() {
        return soname;
    }

    public void setSoname(String soname) {
        this.soname = soname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "name='" + name + '\'' +
                ", soname='" + soname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
