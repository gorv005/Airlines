package com.truck.airlines.airlines.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserProfile implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("u_id")
    @Expose
    private String uId;
    @SerializedName("pan")
    @Expose
    private String pan;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("usertype")
    @Expose
    private String usertype;
    @SerializedName("price_auth")
    @Expose
    private String priceAuth;
    @SerializedName("confirmed")
    @Expose
    private String confirmed;
    @SerializedName("policyagreed")
    @Expose
    private String policyagreed;
    @SerializedName("deleted")
    @Expose
    private String deleted;
    @SerializedName("suspended")
    @Expose
    private String suspended;
    @SerializedName("rec_view")
    @Expose
    private String recView;
    @SerializedName("available")
    @Expose
    private String available;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("count_login")
    @Expose
    private String countLogin;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("emailalt")
    @Expose
    private String emailalt;
    @SerializedName("emailstop")
    @Expose
    private String emailstop;
    @SerializedName("phonecode1")
    @Expose
    private String phonecode1;
    @SerializedName("phone1")
    @Expose
    private String phone1;
    @SerializedName("phonecode2")
    @Expose
    private String phonecode2;
    @SerializedName("phone2")
    @Expose
    private String phone2;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("ap_doctype")
    @Expose
    private String apDoctype;
    @SerializedName("ap_doc")
    @Expose
    private String apDoc;
    @SerializedName("idp_type")
    @Expose
    private String idpType;
    @SerializedName("idp_doc")
    @Expose
    private String idpDoc;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("firstaccess")
    @Expose
    private String firstaccess;
    @SerializedName("lastaccess")
    @Expose
    private String lastaccess;
    @SerializedName("lastlogin")
    @Expose
    private String lastlogin;
    @SerializedName("currentlogin")
    @Expose
    private String currentlogin;
    @SerializedName("login_status")
    @Expose
    private String loginStatus;
    @SerializedName("lastip")
    @Expose
    private String lastip;
    @SerializedName("secret")
    @Expose
    private String secret;
    @SerializedName("reset_code")
    @Expose
    private String resetCode;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("autosubscribe")
    @Expose
    private String autosubscribe;
    @SerializedName("screenreader")
    @Expose
    private String screenreader;
    @SerializedName("isauthentication")
    @Expose
    private String isauthentication;
    @SerializedName("logintype")
    @Expose
    private String logintype;
    @SerializedName("mailformat")
    @Expose
    private String mailformat;
    @SerializedName("truck_id")
    @Expose
    private String truckId;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("modified_by")
    @Expose
    private String modifiedBy;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("modified_at")
    @Expose
    private String modifiedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getPriceAuth() {
        return priceAuth;
    }

    public void setPriceAuth(String priceAuth) {
        this.priceAuth = priceAuth;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getPolicyagreed() {
        return policyagreed;
    }

    public void setPolicyagreed(String policyagreed) {
        this.policyagreed = policyagreed;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getSuspended() {
        return suspended;
    }

    public void setSuspended(String suspended) {
        this.suspended = suspended;
    }

    public String getRecView() {
        return recView;
    }

    public void setRecView(String recView) {
        this.recView = recView;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountLogin() {
        return countLogin;
    }

    public void setCountLogin(String countLogin) {
        this.countLogin = countLogin;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailalt() {
        return emailalt;
    }

    public void setEmailalt(String emailalt) {
        this.emailalt = emailalt;
    }

    public String getEmailstop() {
        return emailstop;
    }

    public void setEmailstop(String emailstop) {
        this.emailstop = emailstop;
    }

    public String getPhonecode1() {
        return phonecode1;
    }

    public void setPhonecode1(String phonecode1) {
        this.phonecode1 = phonecode1;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhonecode2() {
        return phonecode2;
    }

    public void setPhonecode2(String phonecode2) {
        this.phonecode2 = phonecode2;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApDoctype() {
        return apDoctype;
    }

    public void setApDoctype(String apDoctype) {
        this.apDoctype = apDoctype;
    }

    public String getApDoc() {
        return apDoc;
    }

    public void setApDoc(String apDoc) {
        this.apDoc = apDoc;
    }

    public String getIdpType() {
        return idpType;
    }

    public void setIdpType(String idpType) {
        this.idpType = idpType;
    }

    public String getIdpDoc() {
        return idpDoc;
    }

    public void setIdpDoc(String idpDoc) {
        this.idpDoc = idpDoc;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getFirstaccess() {
        return firstaccess;
    }

    public void setFirstaccess(String firstaccess) {
        this.firstaccess = firstaccess;
    }

    public String getLastaccess() {
        return lastaccess;
    }

    public void setLastaccess(String lastaccess) {
        this.lastaccess = lastaccess;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getCurrentlogin() {
        return currentlogin;
    }

    public void setCurrentlogin(String currentlogin) {
        this.currentlogin = currentlogin;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getLastip() {
        return lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getResetCode() {
        return resetCode;
    }

    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAutosubscribe() {
        return autosubscribe;
    }

    public void setAutosubscribe(String autosubscribe) {
        this.autosubscribe = autosubscribe;
    }

    public String getScreenreader() {
        return screenreader;
    }

    public void setScreenreader(String screenreader) {
        this.screenreader = screenreader;
    }

    public String getIsauthentication() {
        return isauthentication;
    }

    public void setIsauthentication(String isauthentication) {
        this.isauthentication = isauthentication;
    }

    public String getLogintype() {
        return logintype;
    }

    public void setLogintype(String logintype) {
        this.logintype = logintype;
    }

    public String getMailformat() {
        return mailformat;
    }

    public void setMailformat(String mailformat) {
        this.mailformat = mailformat;
    }

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
