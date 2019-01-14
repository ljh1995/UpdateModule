package com.example.administrator.myapplication;

public class AppResult {
    private String appid;

    private String appname;

    private String serverversion;

    private String serverflag;

    private String lastforce;

    private String updateurl;

    private String upgradeinfo;

    private String packageinfo;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname == null ? null : appname.trim();
    }

    public String getServerversion() {
        return serverversion;
    }

    public void setServerversion(String serverversion) {
        this.serverversion = serverversion == null ? null : serverversion.trim();
    }

    public String getServerflag() {
        return serverflag;
    }

    public void setServerflag(String serverflag) {
        this.serverflag = serverflag == null ? null : serverflag.trim();
    }

    public String getLastforce() {
        return lastforce;
    }

    public void setLastforce(String lastforce) {
        this.lastforce = lastforce == null ? null : lastforce.trim();
    }

    public String getUpdateurl() {
        return updateurl;
    }

    public void setUpdateurl(String updateurl) {
        this.updateurl = updateurl == null ? null : updateurl.trim();
    }

    public String getUpgradeinfo() {
        return upgradeinfo;
    }

    public void setUpgradeinfo(String upgradeinfo) {
        this.upgradeinfo = upgradeinfo == null ? null : upgradeinfo.trim();
    }

    public String getPackageinfo() {
        return packageinfo;
    }

    public void setPackageinfo(String packageinfo) {
        this.packageinfo = packageinfo == null ? null : packageinfo.trim();
    }
}