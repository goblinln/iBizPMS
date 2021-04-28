import { AppServiceBase, AuthServiceBase } from 'ibiz-core';

/**
 * 实体权限服务
 *
 * @export
 * @class AuthService
 */
export class AuthService extends AuthServiceBase {

    /**
     * 默认操作标识
     *
     * @public
     * @type {(any)}
     * @memberof AuthService
     */
    public defaultOPPrivs: any = {CREATE: 1,DAILY: 1,DELETE: 1,DENY: 1,DOC: 1,DOCLIB: 1,FILE: 1,MODULE: 1,MONTHLY: 1,NONE: 1,NOTOP: 1,READ: 1,REPORTLY: 1,SRFUR__ADMIN: 1,SRFUR__BUG_ACTIVATE_BUT: 1,SRFUR__BUG_ASSIGNTO_BUT: 1,SRFUR__BUG_CLOSE_BUT: 1,SRFUR__BUG_CONFIRM_BUT: 1,SRFUR__BUG_CREATECASE_BUT: 1,SRFUR__BUG_CREATE_BUT: 1,SRFUR__BUG_DELETE_BUT: 1,SRFUR__BUG_EDIT_BUT: 1,SRFUR__BUG_FAVOR_BUT: 1,SRFUR__BUG_NFAVOR_BUT: 1,SRFUR__BUG_RESOLVE_BUT: 1,SRFUR__BUG_TOSTORY_BUT: 1,SRFUR__BUG_UNLINK_BUT: 1,SRFUR__BUILD_CREATE_BUT: 1,SRFUR__BUILD_DELETE_BUT: 1,SRFUR__BUILD_EDIT_BUT: 1,SRFUR__BUILD_LINK_BUT: 1,SRFUR__BUILD_SUBT_BUT: 1,SRFUR__CASE_CASECOF_BUT: 1,SRFUR__CASE_CASERESULT_BUT: 1,SRFUR__CASE_CASERUN_BUT: 1,SRFUR__CASE_CASETOBUG_BUT: 1,SRFUR__CASE_CONFIRM_BUT: 1,SRFUR__CASE_CREATE_BUT: 1,SRFUR__CASE_DELETE_BUT: 1,SRFUR__CASE_EDIT_BUT: 1,SRFUR__CASE_FAVOR_BUT: 1,SRFUR__CASE_NFAVOR_BUT: 1,SRFUR__CASE_RESULT_BUT: 1,SRFUR__CASE_RUN_BUT: 1,SRFUR__CASE_TOBUG_BUT: 1,SRFUR__CASE_UNLINK_BUT: 1,SRFUR__CUTINTBEHAVIOR: 1,SRFUR__DAILY_SUBMIT_BUT: 1,SRFUR__DOCLIBMODULE_EDIT_BUT: 1,SRFUR__DOCLIBMODULE_FAVOUR_BUT: 1,SRFUR__DOCLIBMODULE_MAINTENANCE_BUT: 1,SRFUR__DOCLIBMODULE_NFAVOUR_BUT: 1,SRFUR__DOCLIB_EDIT_BUT: 1,SRFUR__DOCLIB_FAVOUR_BUT: 1,SRFUR__DOCLIB_NFAVOUR_BUT: 1,SRFUR__DOC_DELETE_BUT: 1,SRFUR__DOC_EDIT_BUT: 1,SRFUR__DOC_FAVOUR_BUT: 1,SRFUR__DOC_NFAVOUR_BUT: 1,SRFUR__FILE_DELETE_BUT: 1,SRFUR__FILE_DOWNLOAD_BUT: 1,SRFUR__LIBM_CREATE_BUT: 1,SRFUR__LIBM_EDIT_BUT: 1,SRFUR__LIBM_OPEN_BUT: 1,SRFUR__LIB_CREATE_BUT: 1,SRFUR__LIB_EDIT_BUT: 1,SRFUR__LIB_OPEN_BUT: 1,SRFUR__LICASE_CREATE_BUT: 1,SRFUR__LICASE_EDIT_BUT: 1,SRFUR__LICASE_OPEN_BUT: 1,SRFUR__MONTHLY_EDIT_BUT: 1,SRFUR__MONTHLY_SUBMIT_BUT: 1,SRFUR__PRODSTA_ADDPROD_BUT: 1,SRFUR__PRODSTA_PLANCRT_BUT: 1,SRFUR__PRODSTA_PROJCRT_BUT: 1,SRFUR__PRODSTA_RELCRT_BUT: 1,SRFUR__PRODSTA_STOREOP_BUT: 1,SRFUR__PRODSTA_STORYCRT_BUT: 1,SRFUR__PRODUCT_MANAGER: 1,SRFUR__PROD_CLOSED_BUT: 1,SRFUR__PROD_CREATE_BUT: 1,SRFUR__PROD_DELETE_BUT: 1,SRFUR__PROD_EDIT_BUT: 1,SRFUR__PROD_UPDATE_BUT: 1,SRFUR__PROJECT_MANAGER: 1,SRFUR__PROJECT_REPORT_BUT: 1,SRFUR__PROJT_TM_BUT: 1,SRFUR__PROJ_ACTIVATION_BUT: 1,SRFUR__PROJ_CLOSED_BUT: 1,SRFUR__PROJ_CREATE_BUT: 1,SRFUR__PROJ_DELAY_BUT: 1,SRFUR__PROJ_DELETE_BUT: 1,SRFUR__PROJ_EDIT_BUT: 1,SRFUR__PROJ_MANAGE_BUT: 1,SRFUR__PROJ_START_BUT: 1,SRFUR__PROJ_SUSPEND_BUT: 1,SRFUR__PROP_CHILD_BUT: 1,SRFUR__PROP_CREATE_BUT: 1,SRFUR__PROP_DELETE_BUT: 1,SRFUR__PROP_EDIT_BUT: 1,SRFUR__PROP_LBUG_BUT: 1,SRFUR__PROP_LSTORY_BUT: 1,SRFUR__RELEASE_ACTIVE: 1,SRFUR__RELEASE_COMMON: 1,SRFUR__RELEASE_TERMINATE: 1,SRFUR__REPORTLY_EDIT_BUT: 1,SRFUR__REPORTLY_SUBMIT_BUT: 1,SRFUR__REPORT_CREATE_BUT: 1,SRFUR__REPORT_DELETE_BUT: 1,SRFUR__REPORT_EDIT_BUT: 1,SRFUR__STEP_CREATE_BUT: 1,SRFUR__STEP_EDIT_BUT: 1,SRFUR__STEP_OPEN_BUT: 1,SRFUR__STORY_ACTIVE_BUT: 1,SRFUR__STORY_ASS_BUT: 1,SRFUR__STORY_CCASE_BUT: 1,SRFUR__STORY_CHANGED_BUT: 1,SRFUR__STORY_CLOSED_BUT: 1,SRFUR__STORY_CREATE_BUT: 1,SRFUR__STORY_DELETE_BUT: 1,SRFUR__STORY_EDIT_BUT: 1,SRFUR__STORY_FAVOR_BUT: 1,SRFUR__STORY_FJTASK_BUT: 1,SRFUR__STORY_NFAVOR_BUT: 1,SRFUR__STORY_REVIEW_BUT: 1,SRFUR__STORY_UNLP_BUT: 1,SRFUR__STORY_XQXF_BUT: 1,SRFUR__TASK_ACTIVATION_BUT: 1,SRFUR__TASK_ASSIGN_BUT: 1,SRFUR__TASK_CANCEL_BUT: 1,SRFUR__TASK_CLOSE_BUT: 1,SRFUR__TASK_COMPLETE_BUT: 1,SRFUR__TASK_CONSUM_BUT: 1,SRFUR__TASK_CREATE_BUT: 1,SRFUR__TASK_DELETE_BUT: 1,SRFUR__TASK_EDIT_BUT: 1,SRFUR__TASK_FAVOR_BUT: 1,SRFUR__TASK_FORWARD_BUT: 1,SRFUR__TASK_NFAVOR_BUT: 1,SRFUR__TASK_PAUSE_BUT: 1,SRFUR__TASK_PROCEED_BUT: 1,SRFUR__TASK_START_BUT: 1,SRFUR__TASK_SUBTASKS_BUT: 1,SRFUR__TASK_XQCHANGE_BUT: 1,SRFUR__TESTMOD_FIX_BUT: 1,SRFUR__TESTMOD_OQCVIEW_BUT: 1,SRFUR__TESTSU_CREATE_BUT: 1,SRFUR__TESTSU_DELETE_BUT: 1,SRFUR__TESTSU_EDITSUITE_BUT: 1,SRFUR__TESTSU_LINKCASE_BUT: 1,SRFUR__TESTT_ACTIVITE_BUT: 1,SRFUR__TESTT_BLOCK_BUT: 1,SRFUR__TESTT_CLOSED_BUT: 1,SRFUR__TESTT_CREATE_BUT: 1,SRFUR__TESTT_DELETE_BUT: 1,SRFUR__TESTT_EDIT_BUT: 1,SRFUR__TESTT_LCASE_BUT: 1,SRFUR__TESTT_OPEN_BUT: 1,SRFUR__TESTT_START_BUT: 1,SRFUR__UNIVERSALCREATE: 1,SRFUR__UNIVERSALDELETE: 1,SRFUR__UNIVERSALEDIT: 1,SRFUR__UNIVERSALSAVE: 1,SRFUR__WEEKLY_NSUBMIT_BUT: 1,SRFUR__WEEKLY_SUBMIT_BUT: 1,TOP: 1,UPDATE: 1,WEEKLY: 1,WFSTART: 1}; 

    /**
     * Creates an instance of AuthService.
     * 
     * @param {*} [opts={}]
     * @memberof AuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 注册系统操作标识统一资源
     *
     * @param {string} name 实体名称
     * @returns {Promise<any>}
     * @memberof AuthService
     */ 
    public registerSysOPPrivs(){
        this.sysOPPrivsMap.set('SRFUR__ADMIN','ADMIN');
        this.sysOPPrivsMap.set('SRFUR__BUG_ACTIVATE_BUT','BUG_ACTIVATE_BUT');
        this.sysOPPrivsMap.set('SRFUR__BUG_ASSIGNTO_BUT','BUG_ASSIGNTO_BUT');
        this.sysOPPrivsMap.set('SRFUR__BUG_CLOSE_BUT','BUG_CLOSE_BUT');
        this.sysOPPrivsMap.set('SRFUR__BUG_CONFIRM_BUT','BUG_CONFIRM_BUT');
        this.sysOPPrivsMap.set('SRFUR__BUG_CREATECASE_BUT','BUG_CREATECASE_BUT');
        this.sysOPPrivsMap.set('SRFUR__BUG_CREATE_BUT','BUG_CREATE_BUT');
        this.sysOPPrivsMap.set('SRFUR__BUG_DELETE_BUT','BUG_DELETE_BUT');
        this.sysOPPrivsMap.set('SRFUR__BUG_EDIT_BUT','BUG_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__BUG_FAVOR_BUT','BUG_FAVOR_BUT');
        this.sysOPPrivsMap.set('SRFUR__BUG_NFAVOR_BUT','BUG_NFAVOR_BUT');
        this.sysOPPrivsMap.set('SRFUR__BUG_RESOLVE_BUT','BUG_RESOLVE_BUT');
        this.sysOPPrivsMap.set('SRFUR__BUG_TOSTORY_BUT','BUG_TOSTORY_BUT');
        this.sysOPPrivsMap.set('SRFUR__BUG_UNLINK_BUT','BUG_UNLINK_BUT');
        this.sysOPPrivsMap.set('SRFUR__BUILD_CREATE_BUT','BUILD_CREATE_BUT');
        this.sysOPPrivsMap.set('SRFUR__BUILD_DELETE_BUT','BUILD_DELETE_BUT');
        this.sysOPPrivsMap.set('SRFUR__BUILD_EDIT_BUT','BUILD_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__BUILD_LINK_BUT','BUILD_LINK_BUT');
        this.sysOPPrivsMap.set('SRFUR__BUILD_SUBT_BUT','BUILD_SUBT_BUT');
        this.sysOPPrivsMap.set('SRFUR__CASE_CASECOF_BUT','CASE_CASECOF_BUT');
        this.sysOPPrivsMap.set('SRFUR__CASE_CASERESULT_BUT','CASE_CASERESULT_BUT');
        this.sysOPPrivsMap.set('SRFUR__CASE_CASERUN_BUT','CASE_CASERUN_BUT');
        this.sysOPPrivsMap.set('SRFUR__CASE_CASETOBUG_BUT','CASE_CASETOBUG_BUT');
        this.sysOPPrivsMap.set('SRFUR__CASE_CONFIRM_BUT','CASE_CONFIRM_BUT');
        this.sysOPPrivsMap.set('SRFUR__CASE_CREATE_BUT','CASE_CREATE_BUT');
        this.sysOPPrivsMap.set('SRFUR__CASE_DELETE_BUT','CASE_DELETE_BUT');
        this.sysOPPrivsMap.set('SRFUR__CASE_EDIT_BUT','CASE_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__CASE_FAVOR_BUT','CASE_FAVOR_BUT');
        this.sysOPPrivsMap.set('SRFUR__CASE_NFAVOR_BUT','CASE_NFAVOR_BUT');
        this.sysOPPrivsMap.set('SRFUR__CASE_RESULT_BUT','CASE_RESULT_BUT');
        this.sysOPPrivsMap.set('SRFUR__CASE_RUN_BUT','CASE_RUN_BUT');
        this.sysOPPrivsMap.set('SRFUR__CASE_TOBUG_BUT','CASE_TOBUG_BUT');
        this.sysOPPrivsMap.set('SRFUR__CASE_UNLINK_BUT','CASE_UNLINK_BUT');
        this.sysOPPrivsMap.set('SRFUR__CUTINTBEHAVIOR','CUTINTBEHAVIOR');
        this.sysOPPrivsMap.set('SRFUR__DAILY_SUBMIT_BUT','DAILY_SUBMIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__DOCLIBMODULE_EDIT_BUT','DOCLIBMODULE_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__DOCLIBMODULE_FAVOUR_BUT','DOCLIBMODULE_FAVOUR_BUT');
        this.sysOPPrivsMap.set('SRFUR__DOCLIBMODULE_MAINTENANCE_BUT','DOCLIBMODULE_MAINTENANCE_BUT');
        this.sysOPPrivsMap.set('SRFUR__DOCLIBMODULE_NFAVOUR_BUT','DOCLIBMODULE_NFAVOUR_BUT');
        this.sysOPPrivsMap.set('SRFUR__DOCLIB_EDIT_BUT','DOCLIB_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__DOCLIB_FAVOUR_BUT','DOCLIB_FAVOUR_BUT');
        this.sysOPPrivsMap.set('SRFUR__DOCLIB_NFAVOUR_BUT','DOCLIB_NFAVOUR_BUT');
        this.sysOPPrivsMap.set('SRFUR__DOC_DELETE_BUT','DOC_DELETE_BUT');
        this.sysOPPrivsMap.set('SRFUR__DOC_EDIT_BUT','DOC_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__DOC_FAVOUR_BUT','DOC_FAVOUR_BUT');
        this.sysOPPrivsMap.set('SRFUR__DOC_NFAVOUR_BUT','DOC_NFAVOUR_BUT');
        this.sysOPPrivsMap.set('SRFUR__FILE_DELETE_BUT','FILE_DELETE_BUT');
        this.sysOPPrivsMap.set('SRFUR__FILE_DOWNLOAD_BUT','FILE_DOWNLOAD_BUT');
        this.sysOPPrivsMap.set('SRFUR__LIBM_CREATE_BUT','LIBM_CREATE_BUT');
        this.sysOPPrivsMap.set('SRFUR__LIBM_EDIT_BUT','LIBM_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__LIBM_OPEN_BUT','LIBM_OPEN_BUT');
        this.sysOPPrivsMap.set('SRFUR__LIB_CREATE_BUT','LIB_CREATE_BUT');
        this.sysOPPrivsMap.set('SRFUR__LIB_EDIT_BUT','LIB_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__LIB_OPEN_BUT','LIB_OPEN_BUT');
        this.sysOPPrivsMap.set('SRFUR__LICASE_CREATE_BUT','LICASE_CREATE_BUT');
        this.sysOPPrivsMap.set('SRFUR__LICASE_EDIT_BUT','LICASE_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__LICASE_OPEN_BUT','LICASE_OPEN_BUT');
        this.sysOPPrivsMap.set('SRFUR__MONTHLY_EDIT_BUT','MONTHLY_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__MONTHLY_SUBMIT_BUT','MONTHLY_SUBMIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__PRODSTA_ADDPROD_BUT','PRODSTA_ADDPROD_BUT');
        this.sysOPPrivsMap.set('SRFUR__PRODSTA_PLANCRT_BUT','PRODSTA_PLANCRT_BUT');
        this.sysOPPrivsMap.set('SRFUR__PRODSTA_PROJCRT_BUT','PRODSTA_PROJCRT_BUT');
        this.sysOPPrivsMap.set('SRFUR__PRODSTA_RELCRT_BUT','PRODSTA_RELCRT_BUT');
        this.sysOPPrivsMap.set('SRFUR__PRODSTA_STOREOP_BUT','PRODSTA_STOREOP_BUT');
        this.sysOPPrivsMap.set('SRFUR__PRODSTA_STORYCRT_BUT','PRODSTA_STORYCRT_BUT');
        this.sysOPPrivsMap.set('SRFUR__PRODUCT_MANAGER','PRODUCT_MANAGER');
        this.sysOPPrivsMap.set('SRFUR__PROD_CLOSED_BUT','PROD_CLOSED_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROD_CREATE_BUT','PROD_CREATE_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROD_DELETE_BUT','PROD_DELETE_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROD_EDIT_BUT','PROD_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROD_UPDATE_BUT','PROD_UPDATE_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROJECT_MANAGER','PROJECT_MANAGER');
        this.sysOPPrivsMap.set('SRFUR__PROJECT_REPORT_BUT','PROJECT_REPORT_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROJT_TM_BUT','PROJT_TM_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROJ_ACTIVATION_BUT','PROJ_ACTIVATION_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROJ_CLOSED_BUT','PROJ_CLOSED_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROJ_CREATE_BUT','PROJ_CREATE_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROJ_DELAY_BUT','PROJ_DELAY_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROJ_DELETE_BUT','PROJ_DELETE_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROJ_EDIT_BUT','PROJ_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROJ_MANAGE_BUT','PROJ_MANAGE_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROJ_START_BUT','PROJ_START_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROJ_SUSPEND_BUT','PROJ_SUSPEND_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROP_CHILD_BUT','PROP_CHILD_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROP_CREATE_BUT','PROP_CREATE_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROP_DELETE_BUT','PROP_DELETE_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROP_EDIT_BUT','PROP_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROP_LBUG_BUT','PROP_LBUG_BUT');
        this.sysOPPrivsMap.set('SRFUR__PROP_LSTORY_BUT','PROP_LSTORY_BUT');
        this.sysOPPrivsMap.set('SRFUR__RELEASE_ACTIVE','RELEASE_ACTIVE');
        this.sysOPPrivsMap.set('SRFUR__RELEASE_COMMON','RELEASE_COMMON');
        this.sysOPPrivsMap.set('SRFUR__RELEASE_TERMINATE','RELEASE_TERMINATE');
        this.sysOPPrivsMap.set('SRFUR__REPORTLY_EDIT_BUT','REPORTLY_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__REPORTLY_SUBMIT_BUT','REPORTLY_SUBMIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__REPORT_CREATE_BUT','REPORT_CREATE_BUT');
        this.sysOPPrivsMap.set('SRFUR__REPORT_DELETE_BUT','REPORT_DELETE_BUT');
        this.sysOPPrivsMap.set('SRFUR__REPORT_EDIT_BUT','REPORT_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__STEP_CREATE_BUT','STEP_CREATE_BUT');
        this.sysOPPrivsMap.set('SRFUR__STEP_EDIT_BUT','STEP_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__STEP_OPEN_BUT','STEP_OPEN_BUT');
        this.sysOPPrivsMap.set('SRFUR__STORY_ACTIVE_BUT','STORY_ACTIVE_BUT');
        this.sysOPPrivsMap.set('SRFUR__STORY_ASS_BUT','STORY_ASS_BUT');
        this.sysOPPrivsMap.set('SRFUR__STORY_CCASE_BUT','STORY_CCASE_BUT');
        this.sysOPPrivsMap.set('SRFUR__STORY_CHANGED_BUT','STORY_CHANGED_BUT');
        this.sysOPPrivsMap.set('SRFUR__STORY_CLOSED_BUT','STORY_CLOSED_BUT');
        this.sysOPPrivsMap.set('SRFUR__STORY_CREATE_BUT','STORY_CREATE_BUT');
        this.sysOPPrivsMap.set('SRFUR__STORY_DELETE_BUT','STORY_DELETE_BUT');
        this.sysOPPrivsMap.set('SRFUR__STORY_EDIT_BUT','STORY_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__STORY_FAVOR_BUT','STORY_FAVOR_BUT');
        this.sysOPPrivsMap.set('SRFUR__STORY_FJTASK_BUT','STORY_FJTASK_BUT');
        this.sysOPPrivsMap.set('SRFUR__STORY_NFAVOR_BUT','STORY_NFAVOR_BUT');
        this.sysOPPrivsMap.set('SRFUR__STORY_REVIEW_BUT','STORY_REVIEW_BUT');
        this.sysOPPrivsMap.set('SRFUR__STORY_UNLP_BUT','STORY_UNLP_BUT');
        this.sysOPPrivsMap.set('SRFUR__STORY_XQXF_BUT','STORY_XQXF_BUT');
        this.sysOPPrivsMap.set('SRFUR__TASK_ACTIVATION_BUT','TASK_ACTIVATION_BUT');
        this.sysOPPrivsMap.set('SRFUR__TASK_ASSIGN_BUT','TASK_ASSIGN_BUT');
        this.sysOPPrivsMap.set('SRFUR__TASK_CANCEL_BUT','TASK_CANCEL_BUT');
        this.sysOPPrivsMap.set('SRFUR__TASK_CLOSE_BUT','TASK_CLOSE_BUT');
        this.sysOPPrivsMap.set('SRFUR__TASK_COMPLETE_BUT','TASK_COMPLETE_BUT');
        this.sysOPPrivsMap.set('SRFUR__TASK_CONSUM_BUT','TASK_CONSUM_BUT');
        this.sysOPPrivsMap.set('SRFUR__TASK_CREATE_BUT','TASK_CREATE_BUT');
        this.sysOPPrivsMap.set('SRFUR__TASK_DELETE_BUT','TASK_DELETE_BUT');
        this.sysOPPrivsMap.set('SRFUR__TASK_EDIT_BUT','TASK_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__TASK_FAVOR_BUT','TASK_FAVOR_BUT');
        this.sysOPPrivsMap.set('SRFUR__TASK_FORWARD_BUT','TASK_FORWARD_BUT');
        this.sysOPPrivsMap.set('SRFUR__TASK_NFAVOR_BUT','TASK_NFAVOR_BUT');
        this.sysOPPrivsMap.set('SRFUR__TASK_PAUSE_BUT','TASK_PAUSE_BUT');
        this.sysOPPrivsMap.set('SRFUR__TASK_PROCEED_BUT','TASK_PROCEED_BUT');
        this.sysOPPrivsMap.set('SRFUR__TASK_START_BUT','TASK_START_BUT');
        this.sysOPPrivsMap.set('SRFUR__TASK_SUBTASKS_BUT','TASK_SUBTASKS_BUT');
        this.sysOPPrivsMap.set('SRFUR__TASK_XQCHANGE_BUT','TASK_XQCHANGE_BUT');
        this.sysOPPrivsMap.set('SRFUR__TESTMOD_FIX_BUT','TESTMOD_FIX_BUT');
        this.sysOPPrivsMap.set('SRFUR__TESTMOD_OQCVIEW_BUT','TESTMOD_OQCVIEW_BUT');
        this.sysOPPrivsMap.set('SRFUR__TESTSU_CREATE_BUT','TESTSU_CREATE_BUT');
        this.sysOPPrivsMap.set('SRFUR__TESTSU_DELETE_BUT','TESTSU_DELETE_BUT');
        this.sysOPPrivsMap.set('SRFUR__TESTSU_EDITSUITE_BUT','TESTSU_EDITSUITE_BUT');
        this.sysOPPrivsMap.set('SRFUR__TESTSU_LINKCASE_BUT','TESTSU_LINKCASE_BUT');
        this.sysOPPrivsMap.set('SRFUR__TESTT_ACTIVITE_BUT','TESTT_ACTIVITE_BUT');
        this.sysOPPrivsMap.set('SRFUR__TESTT_BLOCK_BUT','TESTT_BLOCK_BUT');
        this.sysOPPrivsMap.set('SRFUR__TESTT_CLOSED_BUT','TESTT_CLOSED_BUT');
        this.sysOPPrivsMap.set('SRFUR__TESTT_CREATE_BUT','TESTT_CREATE_BUT');
        this.sysOPPrivsMap.set('SRFUR__TESTT_DELETE_BUT','TESTT_DELETE_BUT');
        this.sysOPPrivsMap.set('SRFUR__TESTT_EDIT_BUT','TESTT_EDIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__TESTT_LCASE_BUT','TESTT_LCASE_BUT');
        this.sysOPPrivsMap.set('SRFUR__TESTT_OPEN_BUT','TESTT_OPEN_BUT');
        this.sysOPPrivsMap.set('SRFUR__TESTT_START_BUT','TESTT_START_BUT');
        this.sysOPPrivsMap.set('SRFUR__UNIVERSALCREATE','UNIVERSALCREATE');
        this.sysOPPrivsMap.set('SRFUR__UNIVERSALDELETE','UNIVERSALDELETE');
        this.sysOPPrivsMap.set('SRFUR__UNIVERSALEDIT','UNIVERSALEDIT');
        this.sysOPPrivsMap.set('SRFUR__UNIVERSALSAVE','UNIVERSALSAVE');
        this.sysOPPrivsMap.set('SRFUR__WEEKLY_NSUBMIT_BUT','WEEKLY_NSUBMIT_BUT');
        this.sysOPPrivsMap.set('SRFUR__WEEKLY_SUBMIT_BUT','WEEKLY_SUBMIT_BUT');
    }

    /**
     * 根据菜单项获取菜单权限
     *
     * @param {*} item 菜单标识
     * @returns {boolean}
     * @memberof AuthService
     */
    public getMenusPermission(item: any): boolean {
        const Environment = AppServiceBase.getInstance().getAppEnvironment();
        if(!this.getStore().getters['authresource/getEnablePermissionValid']) {
            return true;
        }
        if(Object.is(Environment.menuPermissionMode,"RT")){
            return this.getStore().getters['authresource/getAuthMenuWithRT'](item);
        }else if(Object.is(Environment.menuPermissionMode,"RESOURCE")){
            return this.getStore().getters['authresource/getAuthMenuWithResource'](item);
        }else{
            return this.getStore().getters['authresource/getAuthMenu'](item);
        }
    }

}