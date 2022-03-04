package com.cy.history.jFrame;



/**
 * 病区工作日志床位变更详情DTO
 */
public class BedChangeDTO {
    //科室ID
    private String idDep;
    private String deptName;
    //姓名
    private String napi;
    //住院号
    private String numVisMed;
    //转后床号
    private String cdBedA;
    //转前床号
    private String cdBedB;
    //保险计划名称
    private String hbhna;
    //床位变动类型编码
    private String euBedctpCd;

    public String getIdDep() {
        return idDep;
    }

    public void setIdDep(String idDep) {
        this.idDep = idDep;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getNapi() {
        return napi;
    }

    public void setNapi(String napi) {
        this.napi = napi;
    }

    public String getNumVisMed() {
        return numVisMed;
    }

    public void setNumVisMed(String numVisMed) {
        this.numVisMed = numVisMed;
    }

    public String getCdBedA() {
        return cdBedA;
    }

    public void setCdBedA(String cdBedA) {
        this.cdBedA = cdBedA;
    }

    public String getCdBedB() {
        return cdBedB;
    }

    public void setCdBedB(String cdBedB) {
        this.cdBedB = cdBedB;
    }

    public String getHbhna() {
        return hbhna;
    }

    public void setHbhna(String hbhna) {
        this.hbhna = hbhna;
    }

    public String getEuBedctpCd() {
        return euBedctpCd;
    }

    public void setEuBedctpCd(String euBedctpCd) {
        this.euBedctpCd = euBedctpCd;
    }
}
