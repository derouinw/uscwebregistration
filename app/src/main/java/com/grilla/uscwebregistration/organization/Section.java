package com.grilla.uscwebregistration.organization;

import android.os.Parcel;
import android.os.Parcelable;

import com.grilla.uscwebregistration.JSONHelper;

/**
 * @author Bill Derouin <bill @ billderouin.com>
 */
public class Section implements Parcelable {
    // API data
    private String termCode;
    private double courseID;
    private String sisCourseID;
    private String name;
    private String section;
    private String session;
    private double unitCode;
    private String type;
    private String beginTime;
    private String endTime;
    private String day;
    private double registered;
    private double seats;
    private String instructor;
    private String location;

    /**
     * Constructor with all information given (when Course loads data)
     * @see Course
     */
    public Section(String termCode, double courseID, String sisCourseID, String name, String section, String session, double unitCode, String type, String beginTime, String endTime, String day, double registered, double seats, String instructor, String location) {
        this.termCode = termCode;
        this.courseID = courseID;
        this.sisCourseID = sisCourseID;
        this.name = name;
        this.section = section;
        this.session = session;
        this.unitCode = unitCode;
        this.type = type;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.day = day;
        this.registered = registered;
        this.seats = seats;
        this.instructor = instructor;
        this.location = location;
    }

    /**
     * Compares two sections to see if they have conflicting times
     * @param other
     * @return  whether or not the sections conflict
     */
    public boolean isConflicting(Section other) {
        return (JSONHelper.compareTimes(beginTime, other.beginTime) < 0 && JSONHelper.compareTimes(beginTime, other.endTime) < 0)
                && (JSONHelper.compareTimes(endTime, other.beginTime) > 0 && JSONHelper.compareTimes(endTime, other.endTime) > 0);
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public double getCourseID() {
        return courseID;
    }

    public void setCourseID(double courseID) {
        this.courseID = courseID;
    }

    public String getSisCourseID() {
        return sisCourseID;
    }

    public void setSisCourseID(String sisCourseID) {
        this.sisCourseID = sisCourseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public double getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(double unitCode) {
        this.unitCode = unitCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public double getRegistered() {
        return registered;
    }

    public void setRegistered(double registered) {
        this.registered = registered;
    }

    public double getSeats() {
        return seats;
    }

    public void setSeats(double seats) {
        this.seats = seats;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String toString() {
        return "Section " + getCourseID() + ": " + getBeginTime() + " - " + getEndTime() + "; " + getInstructor();
    }

    protected Section(Parcel in) {
        termCode = in.readString();
        courseID = in.readDouble();
        sisCourseID = in.readString();
        name = in.readString();
        section = in.readString();
        session = in.readString();
        unitCode = in.readDouble();
        type = in.readString();
        beginTime = in.readString();
        endTime = in.readString();
        day = in.readString();
        registered = in.readDouble();
        seats = in.readDouble();
        instructor = in.readString();
        location = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(termCode);
        dest.writeDouble(courseID);
        dest.writeString(sisCourseID);
        dest.writeString(name);
        dest.writeString(section);
        dest.writeString(session);
        dest.writeDouble(unitCode);
        dest.writeString(type);
        dest.writeString(beginTime);
        dest.writeString(endTime);
        dest.writeString(day);
        dest.writeDouble(registered);
        dest.writeDouble(seats);
        dest.writeString(instructor);
        dest.writeString(location);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Section> CREATOR = new Parcelable.Creator<Section>() {
        @Override
        public Section createFromParcel(Parcel in) {
            return new Section(in);
        }

        @Override
        public Section[] newArray(int size) {
            return new Section[size];
        }
    };
}