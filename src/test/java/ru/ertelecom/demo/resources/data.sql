INSERT INTO wb_bpm_crm_tasks (
    wbct_code, wbct_status, wbct_dt, wbct_task_data, wbct_task_type, wbct_uid1, wbct_duid1, wbct_uid2, wbct_duid2,
    wbct_bpd_id, wbct_org_inn, wbct_ring, wbct_dop_office, wbct_branch, wbct_manager, wbct_name, wbct_deadline,
    wbct_dt_end, wbct_task_id, wbct_prty, wbct_cust_code, wbct_ppl_code, wbct_comment, wbct_dt_start, wbct_client_base,
    wbct_client_type, wbct_comment2, wbct_parent_code, wbct_wcs_code, wbct_prev_code, wbct_coll_owner, wbct_coll_owner_group, wbct_crt_code
) VALUES 
(
    1, 'DET', '{2024, 6, 27, 17, 28, 0}', 'Task data 1', 'Type1', 'UID1', 'DUID1', 'UID2', 'DUID2', 1001, '1234567890', 'Ring1', 'Office1', 'Branch1',
    'Manager1', 'Task Name 1', '{2024, 7, 1, 0, 0, 0}', '{2024, 7, 1, 0, 0, 0}', 10001, 'High', 'Cust1', 'PPL1', 'Comment1', '{2024, 6, 27, 17, 28, 0}',
    'Base1', 'TypeA', 'Comment2-1', 100001, 10001, 1001, 'Owner1', 'OwnerGroup1', 'CRT1'
),
(
    2, 'DET', '{2024, 6, 27, 17, 28, 0}', 'Task data 2', 'Type2', 'UID3', 'DUID3', 'UID4', 'DUID4', 1002, '0987654321', 'Ring2', 'Office2', 'Branch2',
    'Manager2', 'Task Name 2', '{2024, 7, 1, 0, 0, 0}', '{2024, 7, 1, 0, 0, 0}', 10002, 'Medium', 'Cust2', 'PPL2', 'Comment2', '{2024, 6, 27, 17, 28, 0}',
    'Base2', 'TypeB', 'Comment2-2', 100002, 10002, 1002, 'Owner2', 'OwnerGroup2', 'CRT2'
);
