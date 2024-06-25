package ru.ertelecom.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Entity
@Table(name = "wb_bpm_crm_tasks")
public class WbBpmCrmTask {

    @Id
    @Column(name = "wbct_code")
    private Long wbctCode;

    @Column(name = "wbct_status")
    private String wbctStatus;

    @Column(name = "wbct_dt")
    private LocalDateTime wbctDt;

    @Column(name = "wbct_task_data")
    private String wbctTaskData;

    @Column(name = "wbct_task_type")
    private String wbctTaskType;

    @Column(name = "wbct_uid1")
    private String wbctUid1;

    @Column(name = "wbct_duid1")
    private String wbctDuid1;

    @Column(name = "wbct_uid2")
    private String wbctUid2;

    @Column(name = "wbct_duid2")
    private String wbctDuid2;

    @Column(name = "wbct_bpd_id")
    private Long wbctBpdId;

    @Column(name = "wbct_org_inn")
    private String wbctOrgInn;

    @Column(name = "wbct_ring")
    private String wbctRing;

    @Column(name = "wbct_dop_office")
    private String wbctDopOffice;

    @Column(name = "wbct_branch")
    private String wbctBranch;

    @Column(name = "wbct_manager")
    private String wbctManager;

    @Column(name = "wbct_name")
    private String wbctName;

    @Column(name = "wbct_deadline")
    private LocalDateTime wbctDeadline;

    @Column(name = "wbct_dt_end")
    private LocalDateTime wbctDtEnd;

    @Column(name = "wbct_task_id")
    private Long wbctTaskId;

    @Column(name = "wbct_prty")
    private String wbctPrty;

    @Column(name = "wbct_cust_code")
    private String wbctCustCode;

    @Column(name = "wbct_ppl_code")
    private String wbctPplCode;

    @Column(name = "wbct_comment")
    private String wbctComment;

    @Column(name = "wbct_dt_start")
    private LocalDateTime wbctDtStart;

    @Column(name = "wbct_client_base")
    private String wbctClientBase;

    @Column(name = "wbct_client_type")
    private String wbctClientType;

    @Column(name = "wbct_comment2")
    private String wbctComment2;

    @Column(name = "wbct_parent_code")
    private Long wbctParentCode;

    @Column(name = "wbct_wcs_code")
    private Long wbctWcsCode;

    @Column(name = "wbct_prev_code")
    private Long wbctPrevCode;

    @Column(name = "wbct_coll_owner")
    private String wbctCollOwner;

    @Column(name = "wbct_coll_owner_group")
    private String wbctCollOwnerGroup;

    @Column(name = "wwbct_crt_code")
    private Long wbctCrtCode;
}
