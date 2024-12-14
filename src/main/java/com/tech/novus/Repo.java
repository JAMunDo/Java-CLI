package com.tech.novus;


public class Repo {
 public String rclass;
 public String packageType;
 public String url;
 public Boolean externalDependenciesEnabled;

    public Repo() {
    }

    public Repo(String rclass, String packageType, String url, Boolean externalDependenciesEnabled) {
        this.rclass = rclass;
        this.packageType = packageType;
        this.url = url;
        this.externalDependenciesEnabled = externalDependenciesEnabled;
    }

    public String getRclass() {
        return rclass;
    }

    public void setRclass(String rclass) {
        this.rclass = rclass;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getExternalDependenciesEnabled() {
        return externalDependenciesEnabled;
    }

    public void setExternalDependenciesEnabled(Boolean externalDependenciesEnabled) {
        this.externalDependenciesEnabled = externalDependenciesEnabled;
    }

    public void populateFields(){
        this.externalDependenciesEnabled = false;
        this.packageType = "alpine";
    }
}
