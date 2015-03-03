package io.vinnie.friarscout.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vmagro on 3/3/15.
 */
public class Team {

    @Expose
    private String website;
    @Expose
    private String name;
    @Expose
    private String locality;
    @SerializedName("rookie_year")
    @Expose
    private Integer rookieYear;
    @Expose
    private String region;
    @SerializedName("team_number")
    @Expose
    private Integer teamNumber;
    @Expose
    private String location;
    @Expose
    private String key;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @Expose
    private String nickname;

    /**
     * @return The website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website The website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The locality
     */
    public String getLocality() {
        return locality;
    }

    /**
     * @param locality The locality
     */
    public void setLocality(String locality) {
        this.locality = locality;
    }

    /**
     * @return The rookieYear
     */
    public Integer getRookieYear() {
        return rookieYear;
    }

    /**
     * @param rookieYear The rookie_year
     */
    public void setRookieYear(Integer rookieYear) {
        this.rookieYear = rookieYear;
    }

    /**
     * @return The region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region The region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return The teamNumber
     */
    public Integer getTeamNumber() {
        return teamNumber;
    }

    /**
     * @param teamNumber The team_number
     */
    public void setTeamNumber(Integer teamNumber) {
        this.teamNumber = teamNumber;
    }

    /**
     * @return The location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location The location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return The key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key The key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return The countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName The country_name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return The nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname The nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
