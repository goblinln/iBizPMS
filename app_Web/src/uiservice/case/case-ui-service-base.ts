import { Environment } from '@/environments/environment';
import { UIActionTool,Util } from '@/utils';
import UIService from '../ui-service';
import { Subject } from 'rxjs';
import CaseService from '@/service/case/case-service';
import CaseAuthService from '@/authservice/case/case-auth-service';

/**
 * 测试用例UI服务对象基类
 *
 * @export
 * @class CaseUIServiceBase
 */
export default class CaseUIServiceBase extends UIService {

    /**
     * 是否支持工作流
     * 
     * @memberof  CaseUIServiceBase
     */
    public isEnableWorkflow:boolean = false;

    /**
     * 是否支持实体主状态
     * 
     * @memberof  CaseUIServiceBase
     */
    public isEnableDEMainState:boolean = true;

    /**
     * 当前UI服务对应的数据服务对象
     * 
     * @memberof  CaseUIServiceBase
     */
    public dataService:CaseService = new CaseService();

    /**
     * 所有关联视图
     * 
     * @memberof  CaseUIServiceBase
     */ 
    public allViewMap: Map<string, Object> = new Map();

    /**
     * 状态值
     * 
     * @memberof  CaseUIServiceBase
     */ 
    public stateValue: number = 0;

    /**
     * 状态属性
     * 
     * @memberof  CaseUIServiceBase
     */ 
    public stateField: string = "";

    /**
     * 主状态属性集合
     * 
     * @memberof  CaseUIServiceBase
     */  
    public mainStateFields:Array<any> = ['status1','lastrunresult1','isfavorites'];

    /**
     * 主状态集合Map
     * 
     * @memberof  CaseUIServiceBase
     */  
    public allDeMainStateMap:Map<string,string> = new Map();

    /**
     * 主状态操作标识Map
     * 
     * @memberof  CaseUIServiceBase
     */ 
    public allDeMainStateOPPrivsMap:Map<string,any> = new Map();

    /**
     * Creates an instance of  CaseUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  CaseUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
        this.authService = new CaseAuthService(opts);
        this.initViewMap();
        this.initDeMainStateMap();
        this.initDeMainStateOPPrivsMap();
    }

    /**
     * 初始化视图Map
     * 
     * @memberof  CaseUIServiceBase
     */  
    public initViewMap(){
        this.allViewMap.set('EDITVIEW:', {
            viewname: 'editview',
            srfappde: 'cases',
            component: 'case-edit-view',
            openmode: '',
            title: '测试用例',
            width: 0,
            height: 0
        });
        this.allViewMap.set('MDATAVIEW:', {
            viewname: 'gridview',
            srfappde: 'cases',
            component: 'case-grid-view',
            openmode: '',
            title: '测试用例',
            width: 0,
            height: 0
        });
    }

    /**
     * 初始化主状态集合
     * 
     * @memberof  CaseUIServiceBase
     */  
    public initDeMainStateMap(){
        this.allDeMainStateMap.set('blocked__blocked__0','blocked__blocked__0');
        this.allDeMainStateMap.set('blocked__blocked__1','blocked__blocked__1');
        this.allDeMainStateMap.set('blocked__fail__0','blocked__fail__0');
        this.allDeMainStateMap.set('blocked__fail__1','blocked__fail__1');
        this.allDeMainStateMap.set('blocked__n/a__0','blocked__n/a__0');
        this.allDeMainStateMap.set('blocked__n/a__1','blocked__n/a__1');
        this.allDeMainStateMap.set('blocked__no__0','blocked__no__0');
        this.allDeMainStateMap.set('blocked__no__1','blocked__no__1');
        this.allDeMainStateMap.set('blocked__pass__0','blocked__pass__0');
        this.allDeMainStateMap.set('blocked__pass__1','blocked__pass__1');
        this.allDeMainStateMap.set('casechange__blocked__0','casechange__blocked__0');
        this.allDeMainStateMap.set('casechange__blocked__1','casechange__blocked__1');
        this.allDeMainStateMap.set('casechange__fail__0','casechange__fail__0');
        this.allDeMainStateMap.set('casechange__fail__1','casechange__fail__1');
        this.allDeMainStateMap.set('casechange__n/a__0','casechange__n/a__0');
        this.allDeMainStateMap.set('casechange__n/a__1','casechange__n/a__1');
        this.allDeMainStateMap.set('casechange__no__0','casechange__no__0');
        this.allDeMainStateMap.set('casechange__no__1','casechange__no__1');
        this.allDeMainStateMap.set('casechange__pass__0','casechange__pass__0');
        this.allDeMainStateMap.set('casechange__pass__1','casechange__pass__1');
        this.allDeMainStateMap.set('done__blocked__0','done__blocked__0');
        this.allDeMainStateMap.set('done__blocked__1','done__blocked__1');
        this.allDeMainStateMap.set('done__fail__0','done__fail__0');
        this.allDeMainStateMap.set('done__fail__1','done__fail__1');
        this.allDeMainStateMap.set('done__n/a__0','done__n/a__0');
        this.allDeMainStateMap.set('done__n/a__1','done__n/a__1');
        this.allDeMainStateMap.set('done__no__0','done__no__0');
        this.allDeMainStateMap.set('done__no__1','done__no__1');
        this.allDeMainStateMap.set('done__pass__0','done__pass__0');
        this.allDeMainStateMap.set('done__pass__1','done__pass__1');
        this.allDeMainStateMap.set('investigate__blocked__0','investigate__blocked__0');
        this.allDeMainStateMap.set('investigate__blocked__1','investigate__blocked__1');
        this.allDeMainStateMap.set('investigate__fail__0','investigate__fail__0');
        this.allDeMainStateMap.set('investigate__fail__1','investigate__fail__1');
        this.allDeMainStateMap.set('investigate__n/a__0','investigate__n/a__0');
        this.allDeMainStateMap.set('investigate__n/a__1','investigate__n/a__1');
        this.allDeMainStateMap.set('investigate__no__0','investigate__no__0');
        this.allDeMainStateMap.set('investigate__no__1','investigate__no__1');
        this.allDeMainStateMap.set('investigate__pass__0','investigate__pass__0');
        this.allDeMainStateMap.set('investigate__pass__1','investigate__pass__1');
        this.allDeMainStateMap.set('normal__blocked__0','normal__blocked__0');
        this.allDeMainStateMap.set('normal__blocked__1','normal__blocked__1');
        this.allDeMainStateMap.set('normal__fail__0','normal__fail__0');
        this.allDeMainStateMap.set('normal__fail__1','normal__fail__1');
        this.allDeMainStateMap.set('normal__n/a__0','normal__n/a__0');
        this.allDeMainStateMap.set('normal__n/a__1','normal__n/a__1');
        this.allDeMainStateMap.set('normal__no__0','normal__no__0');
        this.allDeMainStateMap.set('normal__no__1','normal__no__1');
        this.allDeMainStateMap.set('normal__pass__0','normal__pass__0');
        this.allDeMainStateMap.set('normal__pass__1','normal__pass__1');
        this.allDeMainStateMap.set('storychange__blocked__0','storychange__blocked__0');
        this.allDeMainStateMap.set('storychange__blocked__1','storychange__blocked__1');
        this.allDeMainStateMap.set('storychange__fail__0','storychange__fail__0');
        this.allDeMainStateMap.set('storychange__fail__1','storychange__fail__1');
        this.allDeMainStateMap.set('storychange__n/a__0','storychange__n/a__0');
        this.allDeMainStateMap.set('storychange__n/a__1','storychange__n/a__1');
        this.allDeMainStateMap.set('storychange__no__0','storychange__no__0');
        this.allDeMainStateMap.set('storychange__no__1','storychange__no__1');
        this.allDeMainStateMap.set('storychange__pass__0','storychange__pass__0');
        this.allDeMainStateMap.set('storychange__pass__1','storychange__pass__1');
        this.allDeMainStateMap.set('wait__blocked__0','wait__blocked__0');
        this.allDeMainStateMap.set('wait__blocked__1','wait__blocked__1');
        this.allDeMainStateMap.set('wait__fail__0','wait__fail__0');
        this.allDeMainStateMap.set('wait__fail__1','wait__fail__1');
        this.allDeMainStateMap.set('wait__n/a__0','wait__n/a__0');
        this.allDeMainStateMap.set('wait__n/a__1','wait__n/a__1');
        this.allDeMainStateMap.set('wait__no__0','wait__no__0');
        this.allDeMainStateMap.set('wait__no__1','wait__no__1');
        this.allDeMainStateMap.set('wait__pass__0','wait__pass__0');
        this.allDeMainStateMap.set('wait__pass__1','wait__pass__1');
    }

    /**
     * 初始化主状态操作标识
     * 
     * @memberof  CaseUIServiceBase
     */  
    public initDeMainStateOPPrivsMap(){
        this.allDeMainStateOPPrivsMap.set('blocked__blocked__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('blocked__blocked__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('blocked__fail__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('blocked__fail__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('blocked__n/a__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('blocked__n/a__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('blocked__no__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('blocked__no__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('blocked__pass__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('blocked__pass__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('casechange__blocked__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_EDIT_BUT':0,'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_RESULT_BUT':0,'SRFUR__CASE_DELETE_BUT':0,'SRFUR__CASE_CASERUN_BUT':0,'SRFUR__CASE_CASETOBUG_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_RUN_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('casechange__blocked__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_EDIT_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_CASETOBUG_BUT':0,'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_RUN_BUT':0,'SRFUR__CASE_DELETE_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_RESULT_BUT':0,'SRFUR__CASE_CASERUN_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('casechange__fail__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CASERUN_BUT':0,'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_RUN_BUT':0,'SRFUR__CASE_CASETOBUG_BUT':0,'SRFUR__CASE_EDIT_BUT':0,'SRFUR__CASE_RESULT_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('casechange__fail__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_RUN_BUT':0,'SRFUR__CASE_EDIT_BUT':0,'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_CASETOBUG_BUT':0,'SRFUR__CASE_CASERUN_BUT':0,'SRFUR__CASE_RESULT_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('casechange__n/a__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASERUN_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_EDIT_BUT':0,'SRFUR__CASE_RUN_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_CASETOBUG_BUT':0,'SRFUR__CASE_RESULT_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('casechange__n/a__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_RESULT_BUT':0,'SRFUR__CASE_EDIT_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_CASETOBUG_BUT':0,'SRFUR__CASE_RUN_BUT':0,'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_CASERUN_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('casechange__no__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_RESULT_BUT':0,'SRFUR__CASE_RUN_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CASETOBUG_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_DELETE_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_CASERUN_BUT':0,'SRFUR__CASE_EDIT_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('casechange__no__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_DELETE_BUT':0,'SRFUR__CASE_EDIT_BUT':0,'SRFUR__CASE_CASERUN_BUT':0,'SRFUR__CASE_RUN_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_RESULT_BUT':0,'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CASETOBUG_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('casechange__pass__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASETOBUG_BUT':0,'SRFUR__CASE_EDIT_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_CASERUN_BUT':0,'SRFUR__CASE_RESULT_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_RUN_BUT':0,'SRFUR__CASE_CASERESULT_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('casechange__pass__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_RUN_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CASETOBUG_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_EDIT_BUT':0,'SRFUR__CASE_CASERUN_BUT':0,'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_RESULT_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('done__blocked__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('done__blocked__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('done__fail__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('done__fail__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('done__n/a__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('done__n/a__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('done__no__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('done__no__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('done__pass__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('done__pass__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('investigate__blocked__0',Object.assign({'CASECOF':0,'CASERESULT':0,'CASERUN':0,'CASETOBUG':0,'CONFIRM':0,'CREATE':0,'DELETE':0,'EDIT':0,'READ':0,'RESULT':0,'RUN':0,'TOBUG':0,'UNLINK':0,'UPDATE':0},{'SRFUR__CASE_FAVOR_BUT':1,'SRFUR__CASE_CONFIRM_BUT':1,'SRFUR__CASE_CASECOF_BUT':1,'SRFUR__CASE_TOBUG_BUT':1,}));
        this.allDeMainStateOPPrivsMap.set('investigate__blocked__1',Object.assign({'CASECOF':0,'CASERESULT':0,'CASERUN':0,'CASETOBUG':0,'CONFIRM':0,'CREATE':0,'DELETE':0,'EDIT':0,'READ':0,'RESULT':0,'RUN':0,'TOBUG':0,'UNLINK':0,'UPDATE':0},{'SRFUR__CASE_NFAVOR_BUT':1,'SRFUR__CASE_CASECOF_BUT':1,'SRFUR__CASE_TOBUG_BUT':1,'SRFUR__CASE_CONFIRM_BUT':1,}));
        this.allDeMainStateOPPrivsMap.set('investigate__fail__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('investigate__fail__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('investigate__n/a__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('investigate__n/a__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('investigate__no__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('investigate__no__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('investigate__pass__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('investigate__pass__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('normal__blocked__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('normal__blocked__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('normal__fail__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('normal__fail__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('normal__n/a__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('normal__n/a__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('normal__no__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('normal__no__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('normal__pass__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('normal__pass__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('storychange__blocked__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASERUN_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_EDIT_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_RUN_BUT':0,'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_RESULT_BUT':0,'SRFUR__CASE_CASETOBUG_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('storychange__blocked__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_EDIT_BUT':0,'SRFUR__CASE_RESULT_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CASERUN_BUT':0,'SRFUR__CASE_RUN_BUT':0,'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_CASETOBUG_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('storychange__fail__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_RUN_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_RESULT_BUT':0,'SRFUR__CASE_CASETOBUG_BUT':0,'SRFUR__CASE_CASERUN_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_EDIT_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('storychange__fail__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CASERUN_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_CASETOBUG_BUT':0,'SRFUR__CASE_RESULT_BUT':0,'SRFUR__CASE_EDIT_BUT':0,'SRFUR__CASE_RUN_BUT':0,'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('storychange__n/a__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_CASERUN_BUT':0,'SRFUR__CASE_CASETOBUG_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_EDIT_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_RESULT_BUT':0,'SRFUR__CASE_RUN_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('storychange__n/a__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_CASERUN_BUT':0,'SRFUR__CASE_RESULT_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CASETOBUG_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_EDIT_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_RUN_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('storychange__no__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_RESULT_BUT':0,'SRFUR__CASE_RUN_BUT':0,'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_CASETOBUG_BUT':0,'SRFUR__CASE_CASERUN_BUT':0,'SRFUR__CASE_EDIT_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('storychange__no__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASETOBUG_BUT':0,'SRFUR__CASE_RESULT_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_RUN_BUT':0,'SRFUR__CASE_EDIT_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_CASERUN_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('storychange__pass__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_CASERUN_BUT':0,'SRFUR__CASE_RESULT_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_CASETOBUG_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_EDIT_BUT':0,'SRFUR__CASE_RUN_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('storychange__pass__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_UNLINK_BUT':0,'SRFUR__CASE_CASERESULT_BUT':0,'SRFUR__CASE_RESULT_BUT':0,'SRFUR__CASE_CASERUN_BUT':0,'SRFUR__CASE_CASETOBUG_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_RUN_BUT':0,'SRFUR__CASE_EDIT_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('wait__blocked__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('wait__blocked__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('wait__fail__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('wait__fail__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('wait__n/a__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('wait__n/a__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('wait__no__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('wait__no__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('wait__pass__0',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,'SRFUR__CASE_FAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('wait__pass__1',Object.assign({'CASECOF':1,'CASERESULT':1,'CASERUN':1,'CASETOBUG':1,'CONFIRM':1,'CREATE':1,'DELETE':1,'EDIT':1,'READ':1,'RESULT':1,'RUN':1,'TOBUG':1,'UNLINK':1,'UPDATE':1},{'SRFUR__CASE_TOBUG_BUT':0,'SRFUR__CASE_CONFIRM_BUT':0,'SRFUR__CASE_NFAVOR_BUT':0,'SRFUR__CASE_CASECOF_BUT':0,}));
    }

    /**
     * 批量新建用例
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_BatchNew(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'NONE';
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        let deResParameters: any[] = [];
        if(context.product && true){
            deResParameters = [
            { pathName: 'products', parameterName: 'product' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'cases', parameterName: 'case' },
        ];
            const openPopupModal = (view: any, data: any) => {
                let container: Subject<any> = actionContext.$appmodal.openModal(view, context, data);
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    const _this: any = actionContext;
                    return result.datas;
                });
            }
            const view: any = {
                viewname: 'case-batch-new-grid-view', 
                height: 700, 
                width: 1200,  
                title: actionContext.$t('entities.case.views.batchnewgridview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 保存
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_linkCase(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{IDS:"%id%",TASK:"%task%",ID:"0",VERSIONS:"%version%"});
        Object.assign(params,{id:"0",versions:"%version%",ids:"%id%",task:"%task%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'MULTIKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        if(Object.is(actionTarget,"MULTIKEY")){
            let tempDataArray:Array<any> = [];
            if((_args.length >1) && (Object.keys(data).length >0)){
                for(let i =0;i<_args.length;i++){
                    let tempObject:any = {};
                    Object.keys(data).forEach((key:string) =>{
                        Object.assign(tempObject,{[key]:data[key].split(',')[i]});
                    })
                    tempDataArray.push(tempObject);
                }
            }else{
                tempDataArray.push(data);
            }
            data = tempDataArray;
        }
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        if(!Object.is(actionTarget,"MULTIKEY")){
            Object.assign(data,parentObj);
        }
        Object.assign(context,parentObj);
        // 直接调实体服务需要转换的数据
        if(context && context.srfsessionid){
          context.srfsessionkey = context.srfsessionid;
            delete context.srfsessionid;
        }
        
        const backend = () => {
            const curService:CaseService =  new CaseService();
            curService.LinkCaseBatch(context,data, true).then((response: any) => {
                if (!response || response.status !== 200) {
                    actionContext.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                actionContext.$Notice.success({ title: '成功', desc: '保存成功！' });
                const _this: any = actionContext;
                if (xData && xData.refresh && xData.refresh instanceof Function) {
                    xData.refresh(args);
                }
                actionContext.closeView(null);
                return response;
            }).catch((response: any) => {
                if (!response || !response.status || !response.data) {
                    actionContext.$Notice.error({ title: '错误', desc: '系统异常！' });
                    return;
                }
                if (response && response.data) {
                    actionContext.$Notice.error({ title: '错误', desc: response.data.message });
                    return;
                }
                if (response.status === 401) {
                    return;
                }
                return response;
            });
        };
        backend();
    }

    /**
     * 收藏
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_caseFavorite(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{OPENEDBY:"%srfloginname%",SRFLOGINNAME:"%srfloginname%"});
        Object.assign(params,{srfloginname:"%srfloginname%",openedby:"%srfloginname%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        if(Object.is(actionTarget,"MULTIKEY")){
            let tempDataArray:Array<any> = [];
            if((_args.length >1) && (Object.keys(data).length >0)){
                for(let i =0;i<_args.length;i++){
                    let tempObject:any = {};
                    Object.keys(data).forEach((key:string) =>{
                        Object.assign(tempObject,{[key]:data[key].split(',')[i]});
                    })
                    tempDataArray.push(tempObject);
                }
            }else{
                tempDataArray.push(data);
            }
            data = tempDataArray;
        }
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        if(!Object.is(actionTarget,"MULTIKEY")){
            Object.assign(data,parentObj);
        }
        Object.assign(context,parentObj);
        // 直接调实体服务需要转换的数据
        if(context && context.srfsessionid){
          context.srfsessionkey = context.srfsessionid;
            delete context.srfsessionid;
        }
        
        const backend = () => {
            const curService:CaseService =  new CaseService();
            curService.CaseFavorite(context,data, true).then((response: any) => {
                if (!response || response.status !== 200) {
                    actionContext.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                actionContext.$Notice.success({ title: '成功', desc: '收藏成功！' });
                const _this: any = actionContext;
                if (xData && xData.refresh && xData.refresh instanceof Function) {
                    xData.refresh(args);
                }
                return response;
            }).catch((response: any) => {
                if (!response || !response.status || !response.data) {
                    actionContext.$Notice.error({ title: '错误', desc: '系统异常！' });
                    return;
                }
                if (response && response.data) {
                    actionContext.$Notice.error({ title: '错误', desc: response.data.message });
                    return;
                }
                if (response.status === 401) {
                    return;
                }
                return response;
            });
        };
        backend();
    }

    /**
     * 结果
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_OpenTestRunResultViewCz(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        let deResParameters: any[] = [];
        if(context.product && true){
            deResParameters = [
            { pathName: 'products', parameterName: 'product' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'cases', parameterName: 'case' },
        ];
            const openPopupModal = (view: any, data: any) => {
                let container: Subject<any> = actionContext.$appmodal.openModal(view, context, data);
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    const _this: any = actionContext;
                    return result.datas;
                });
            }
            const view: any = {
                viewname: 'case-exc-edit-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.case.views.exceditview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 编辑
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_MainEditCz(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        let deResParameters: any[] = [];
        if(context.product && true){
            deResParameters = [
            { pathName: 'products', parameterName: 'product' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'cases', parameterName: 'case' },
        ];
            const openDrawer = (view: any, data: any) => {
                let container: Subject<any> = actionContext.$appdrawer.openDrawer(view, context,data);
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    const _this: any = actionContext;
                    if (_this.Refresh) {
                        _this.Refresh(result.datas,context,params, $event, xData,actionContext);
                    }
                    return result.datas;
                });
            }
            const view: any = {
                viewname: 'case-main-edit-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.case.views.maineditview.title'),
                placement: 'DRAWER_TOP',
            };
            openDrawer(view, data);
    }

    /**
     * 关联用例
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_LinkCaseC(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{TASK:"%task%"});
        Object.assign(params,{task:"%task%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'NONE';
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        let deResParameters: any[] = [];
        if(context.product && true){
            deResParameters = [
            { pathName: 'products', parameterName: 'product' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'cases', parameterName: 'case' },
        ];
            const openDrawer = (view: any, data: any) => {
                let container: Subject<any> = actionContext.$appdrawer.openDrawer(view, context,data);
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    const _this: any = actionContext;
                    if (xData && xData.refresh && xData.refresh instanceof Function) {
                        xData.refresh(args);
                    }
                    return result.datas;
                });
            }
            const view: any = {
                viewname: 'case-link-case-grid-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.case.views.linkcasegridview.title'),
                placement: 'DRAWER_TOP',
            };
            openDrawer(view, data);
    }

    /**
     * 概况
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_Edits(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        let deResParameters: any[] = [];
        if(context.product && true){
            deResParameters = [
            { pathName: 'products', parameterName: 'product' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'cases', parameterName: 'case' },
        ];
            const openDrawer = (view: any, data: any) => {
                let container: Subject<any> = actionContext.$appdrawer.openDrawer(view, context,data);
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    const _this: any = actionContext;
                    return result.datas;
                });
            }
            const view: any = {
                viewname: 'case-test-main-dashboard-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.case.views.testmaindashboardview.title'),
                placement: 'DRAWER_TOP',
            };
            openDrawer(view, data);
    }

    /**
     * 转Bug
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_NewBugByTestCaseResult(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{TASK:"%task%"});
        Object.assign(params,{task:"%task%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        let deResParameters: any[] = [];
        if(context.product && true){
            deResParameters = [
            { pathName: 'products', parameterName: 'product' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'cases', parameterName: 'case' },
        ];
            const openPopupModal = (view: any, data: any) => {
                let container: Subject<any> = actionContext.$appmodal.openModal(view, context, data);
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    const _this: any = actionContext;
                    _this.closeView(null);
                    return result.datas;
                });
            }
            const view: any = {
                viewname: 'case-to-bug-test-edit-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.case.views.tobugtesteditview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 执行
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_ExecuteCz(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        let deResParameters: any[] = [];
        if(context.product && true){
            deResParameters = [
            { pathName: 'products', parameterName: 'product' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'cases', parameterName: 'case' },
        ];
            const openPopupModal = (view: any, data: any) => {
                let container: Subject<any> = actionContext.$appmodal.openModal(view, context, data);
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    const _this: any = actionContext;
                    return result.datas;
                });
            }
            const view: any = {
                viewname: 'case-option-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.case.views.optionview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 保存
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_suitelinkCase(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{IDS:"%id%",ID:"0",SUITE:"%suite%",VERSIONS:"%version%"});
        Object.assign(params,{id:"0",versions:"%version%",ids:"%id%",suite:"%suite%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'MULTIKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        if(Object.is(actionTarget,"MULTIKEY")){
            let tempDataArray:Array<any> = [];
            if((_args.length >1) && (Object.keys(data).length >0)){
                for(let i =0;i<_args.length;i++){
                    let tempObject:any = {};
                    Object.keys(data).forEach((key:string) =>{
                        Object.assign(tempObject,{[key]:data[key].split(',')[i]});
                    })
                    tempDataArray.push(tempObject);
                }
            }else{
                tempDataArray.push(data);
            }
            data = tempDataArray;
        }
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        if(!Object.is(actionTarget,"MULTIKEY")){
            Object.assign(data,parentObj);
        }
        Object.assign(context,parentObj);
        // 直接调实体服务需要转换的数据
        if(context && context.srfsessionid){
          context.srfsessionkey = context.srfsessionid;
            delete context.srfsessionid;
        }
        
        const backend = () => {
            const curService:CaseService =  new CaseService();
            curService.TestsuitelinkCaseBatch(context,data, true).then((response: any) => {
                if (!response || response.status !== 200) {
                    actionContext.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                actionContext.$Notice.success({ title: '成功', desc: '保存成功！' });
                const _this: any = actionContext;
                if (xData && xData.refresh && xData.refresh instanceof Function) {
                    xData.refresh(args);
                }
                actionContext.closeView(null);
                return response;
            }).catch((response: any) => {
                if (!response || !response.status || !response.data) {
                    actionContext.$Notice.error({ title: '错误', desc: '系统异常！' });
                    return;
                }
                if (response && response.data) {
                    actionContext.$Notice.error({ title: '错误', desc: response.data.message });
                    return;
                }
                if (response.status === 401) {
                    return;
                }
                return response;
            });
        };
        backend();
    }

    /**
     * 取消收藏
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_CaseNFavorite(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        if(Object.is(actionTarget,"MULTIKEY")){
            let tempDataArray:Array<any> = [];
            if((_args.length >1) && (Object.keys(data).length >0)){
                for(let i =0;i<_args.length;i++){
                    let tempObject:any = {};
                    Object.keys(data).forEach((key:string) =>{
                        Object.assign(tempObject,{[key]:data[key].split(',')[i]});
                    })
                    tempDataArray.push(tempObject);
                }
            }else{
                tempDataArray.push(data);
            }
            data = tempDataArray;
        }
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        if(!Object.is(actionTarget,"MULTIKEY")){
            Object.assign(data,parentObj);
        }
        Object.assign(context,parentObj);
        // 直接调实体服务需要转换的数据
        if(context && context.srfsessionid){
          context.srfsessionkey = context.srfsessionid;
            delete context.srfsessionid;
        }
        
        const backend = () => {
            const curService:CaseService =  new CaseService();
            curService.CaseNFavorite(context,data, true).then((response: any) => {
                if (!response || response.status !== 200) {
                    actionContext.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                actionContext.$Notice.success({ title: '成功', desc: '取消收藏成功！' });
                const _this: any = actionContext;
                if (xData && xData.refresh && xData.refresh instanceof Function) {
                    xData.refresh(args);
                }
                return response;
            }).catch((response: any) => {
                if (!response || !response.status || !response.data) {
                    actionContext.$Notice.error({ title: '错误', desc: '系统异常！' });
                    return;
                }
                if (response && response.data) {
                    actionContext.$Notice.error({ title: '错误', desc: response.data.message });
                    return;
                }
                if (response.status === 401) {
                    return;
                }
                return response;
            });
        };
        backend();
    }

    /**
     * 结果
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_CASEOpenTestRunResultView(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{TASK:"%task%"});
        Object.assign(params,{task:"%task%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        let deResParameters: any[] = [];
        if(context.product && true){
            deResParameters = [
            { pathName: 'products', parameterName: 'product' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'cases', parameterName: 'case' },
        ];
            const openPopupModal = (view: any, data: any) => {
                let container: Subject<any> = actionContext.$appmodal.openModal(view, context, data);
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    const _this: any = actionContext;
                    return result.datas;
                });
            }
            const view: any = {
                viewname: 'case-test-task-exc-edit-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.case.views.testtaskexceditview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 移除
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_unlinkCase(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{TASK:"%task%"});
        Object.assign(params,{task:"%task%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        if(Object.is(actionTarget,"MULTIKEY")){
            let tempDataArray:Array<any> = [];
            if((_args.length >1) && (Object.keys(data).length >0)){
                for(let i =0;i<_args.length;i++){
                    let tempObject:any = {};
                    Object.keys(data).forEach((key:string) =>{
                        Object.assign(tempObject,{[key]:data[key].split(',')[i]});
                    })
                    tempDataArray.push(tempObject);
                }
            }else{
                tempDataArray.push(data);
            }
            data = tempDataArray;
        }
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        if(!Object.is(actionTarget,"MULTIKEY")){
            Object.assign(data,parentObj);
        }
        Object.assign(context,parentObj);
        // 直接调实体服务需要转换的数据
        if(context && context.srfsessionid){
          context.srfsessionkey = context.srfsessionid;
            delete context.srfsessionid;
        }
        
        const backend = () => {
            const curService:CaseService =  new CaseService();
            curService.UnlinkCases(context,data, true).then((response: any) => {
                if (!response || response.status !== 200) {
                    actionContext.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                actionContext.$Notice.success({ title: '成功', desc: '移除成功！' });
                const _this: any = actionContext;
                if (xData && xData.refresh && xData.refresh instanceof Function) {
                    xData.refresh(args);
                }
                return response;
            }).catch((response: any) => {
                if (!response || !response.status || !response.data) {
                    actionContext.$Notice.error({ title: '错误', desc: '系统异常！' });
                    return;
                }
                if (response && response.data) {
                    actionContext.$Notice.error({ title: '错误', desc: response.data.message });
                    return;
                }
                if (response.status === 401) {
                    return;
                }
                return response;
            });
        };
        backend();
    }

    /**
     * 结果
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_OpenTestRunResultView(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        let deResParameters: any[] = [];
        if(context.product && true){
            deResParameters = [
            { pathName: 'products', parameterName: 'product' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'cases', parameterName: 'case' },
        ];
            const openPopupModal = (view: any, data: any) => {
                let container: Subject<any> = actionContext.$appmodal.openModal(view, context, data);
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    const _this: any = actionContext;
                    return result.datas;
                });
            }
            const view: any = {
                viewname: 'case-exc-edit-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.case.views.exceditview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 移除
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_unlinkSuiteCase(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{SUITE:"%suite%"});
        Object.assign(params,{suite:"%suite%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        if(Object.is(actionTarget,"MULTIKEY")){
            let tempDataArray:Array<any> = [];
            if((_args.length >1) && (Object.keys(data).length >0)){
                for(let i =0;i<_args.length;i++){
                    let tempObject:any = {};
                    Object.keys(data).forEach((key:string) =>{
                        Object.assign(tempObject,{[key]:data[key].split(',')[i]});
                    })
                    tempDataArray.push(tempObject);
                }
            }else{
                tempDataArray.push(data);
            }
            data = tempDataArray;
        }
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        if(!Object.is(actionTarget,"MULTIKEY")){
            Object.assign(data,parentObj);
        }
        Object.assign(context,parentObj);
        // 直接调实体服务需要转换的数据
        if(context && context.srfsessionid){
          context.srfsessionkey = context.srfsessionid;
            delete context.srfsessionid;
        }
        
        const backend = () => {
            const curService:CaseService =  new CaseService();
            curService.UnlinkSuiteCases(context,data, true).then((response: any) => {
                if (!response || response.status !== 200) {
                    actionContext.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                actionContext.$Notice.success({ title: '成功', desc: '移除成功！' });
                const _this: any = actionContext;
                if (xData && xData.refresh && xData.refresh instanceof Function) {
                    xData.refresh(args);
                }
                return response;
            }).catch((response: any) => {
                if (!response || !response.status || !response.data) {
                    actionContext.$Notice.error({ title: '错误', desc: '系统异常！' });
                    return;
                }
                if (response && response.data) {
                    actionContext.$Notice.error({ title: '错误', desc: response.data.message });
                    return;
                }
                if (response.status === 401) {
                    return;
                }
                return response;
            });
        };
        backend();
    }

    /**
     * 确认
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_confirmstorychange(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        if(Object.is(actionTarget,"MULTIKEY")){
            let tempDataArray:Array<any> = [];
            if((_args.length >1) && (Object.keys(data).length >0)){
                for(let i =0;i<_args.length;i++){
                    let tempObject:any = {};
                    Object.keys(data).forEach((key:string) =>{
                        Object.assign(tempObject,{[key]:data[key].split(',')[i]});
                    })
                    tempDataArray.push(tempObject);
                }
            }else{
                tempDataArray.push(data);
            }
            data = tempDataArray;
        }
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        if(!Object.is(actionTarget,"MULTIKEY")){
            Object.assign(data,parentObj);
        }
        Object.assign(context,parentObj);
        // 直接调实体服务需要转换的数据
        if(context && context.srfsessionid){
          context.srfsessionkey = context.srfsessionid;
            delete context.srfsessionid;
        }
        
        const backend = () => {
            const curService:CaseService =  new CaseService();
            curService.Confirmstorychange(context,data, true).then((response: any) => {
                if (!response || response.status !== 200) {
                    actionContext.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                actionContext.$Notice.success({ title: '成功', desc: '确认成功！' });
                const _this: any = actionContext;
                if (xData && xData.refresh && xData.refresh instanceof Function) {
                    xData.refresh(args);
                }
                return response;
            }).catch((response: any) => {
                if (!response || !response.status || !response.data) {
                    actionContext.$Notice.error({ title: '错误', desc: '系统异常！' });
                    return;
                }
                if (response && response.data) {
                    actionContext.$Notice.error({ title: '错误', desc: response.data.message });
                    return;
                }
                if (response.status === 401) {
                    return;
                }
                return response;
            });
        };
        backend();
    }

    /**
     * 确认用例变动
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_confirmChange(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{TASK:"%task%"});
        Object.assign(params,{task:"%task%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        if(Object.is(actionTarget,"MULTIKEY")){
            let tempDataArray:Array<any> = [];
            if((_args.length >1) && (Object.keys(data).length >0)){
                for(let i =0;i<_args.length;i++){
                    let tempObject:any = {};
                    Object.keys(data).forEach((key:string) =>{
                        Object.assign(tempObject,{[key]:data[key].split(',')[i]});
                    })
                    tempDataArray.push(tempObject);
                }
            }else{
                tempDataArray.push(data);
            }
            data = tempDataArray;
        }
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        if(!Object.is(actionTarget,"MULTIKEY")){
            Object.assign(data,parentObj);
        }
        Object.assign(context,parentObj);
        // 直接调实体服务需要转换的数据
        if(context && context.srfsessionid){
          context.srfsessionkey = context.srfsessionid;
            delete context.srfsessionid;
        }
        
        const backend = () => {
            const curService:CaseService =  new CaseService();
            curService.ConfirmChange(context,data, true).then((response: any) => {
                if (!response || response.status !== 200) {
                    actionContext.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                actionContext.$Notice.success({ title: '成功', desc: '确认用例变动成功！' });
                const _this: any = actionContext;
                if (xData && xData.refresh && xData.refresh instanceof Function) {
                    xData.refresh(args);
                }
                return response;
            }).catch((response: any) => {
                if (!response || !response.status || !response.data) {
                    actionContext.$Notice.error({ title: '错误', desc: '系统异常！' });
                    return;
                }
                if (response && response.data) {
                    actionContext.$Notice.error({ title: '错误', desc: response.data.message });
                    return;
                }
                if (response.status === 401) {
                    return;
                }
                return response;
            });
        };
        backend();
    }

    /**
     * 编辑
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_MainEdit(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        let deResParameters: any[] = [];
        if(context.product && true){
            deResParameters = [
            { pathName: 'products', parameterName: 'product' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'cases', parameterName: 'case' },
        ];
            const openDrawer = (view: any, data: any) => {
                let container: Subject<any> = actionContext.$appdrawer.openDrawer(view, context,data);
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    const _this: any = actionContext;
                    if (_this.Refresh) {
                        _this.Refresh(result.datas,context,params, $event, xData,actionContext);
                    }
                    return result.datas;
                });
            }
            const view: any = {
                viewname: 'case-main-edit-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.case.views.maineditview.title'),
                placement: 'DRAWER_TOP',
            };
            openDrawer(view, data);
    }

    /**
     * 转Bug
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_NewBugByCaseResultCz(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        let deResParameters: any[] = [];
        if(context.product && true){
            deResParameters = [
            { pathName: 'products', parameterName: 'product' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'cases', parameterName: 'case' },
        ];
            const openPopupModal = (view: any, data: any) => {
                let container: Subject<any> = actionContext.$appmodal.openModal(view, context, data);
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    const _this: any = actionContext;
                    return result.datas;
                });
            }
            const view: any = {
                viewname: 'case-to-bug-edit-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.case.views.tobugeditview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 新建
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_Create(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'NONE';
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        let deResParameters: any[] = [];
        if(context.product && true){
            deResParameters = [
            { pathName: 'products', parameterName: 'product' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'cases', parameterName: 'case' },
        ];
            const openDrawer = (view: any, data: any) => {
                let container: Subject<any> = actionContext.$appdrawer.openDrawer(view, context,data);
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    const _this: any = actionContext;
                    if (xData && xData.refresh && xData.refresh instanceof Function) {
                        xData.refresh(args);
                    }
                    return result.datas;
                });
            }
            const view: any = {
                viewname: 'case-main-new-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.case.views.mainnewview.title'),
                placement: 'DRAWER_RIGHT',
            };
            openDrawer(view, data);
    }

    /**
     * 执行
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_CaseExecute(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{TASK:"%task%"});
        Object.assign(params,{task:"%task%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        let deResParameters: any[] = [];
        if(context.product && true){
            deResParameters = [
            { pathName: 'products', parameterName: 'product' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'cases', parameterName: 'case' },
        ];
            const openPopupModal = (view: any, data: any) => {
                let container: Subject<any> = actionContext.$appmodal.openModal(view, context, data);
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    const _this: any = actionContext;
                    return result.datas;
                });
            }
            const view: any = {
                viewname: 'case-test-option-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.case.views.testoptionview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 转Bug
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_NewBugByCaseResult(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{TASK:"%task%"});
        Object.assign(params,{task:"%task%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        let deResParameters: any[] = [];
        if(context.product && true){
            deResParameters = [
            { pathName: 'products', parameterName: 'product' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'cases', parameterName: 'case' },
        ];
            const openPopupModal = (view: any, data: any) => {
                let container: Subject<any> = actionContext.$appmodal.openModal(view, context, data);
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    const _this: any = actionContext;
                    _this.closeView(null);
                    return result.datas;
                });
            }
            const view: any = {
                viewname: 'case-to-bug-edit-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.case.views.tobugeditview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 执行
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_Execute(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        let deResParameters: any[] = [];
        if(context.product && true){
            deResParameters = [
            { pathName: 'products', parameterName: 'product' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'cases', parameterName: 'case' },
        ];
            const openPopupModal = (view: any, data: any) => {
                let container: Subject<any> = actionContext.$appmodal.openModal(view, context, data);
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    const _this: any = actionContext;
                    return result.datas;
                });
            }
            const view: any = {
                viewname: 'case-option-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.case.views.optionview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 删除
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async Case_deleteCz(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let confirmResult:boolean = await new Promise((resolve: any, reject: any) => {
          actionContext.$Modal.confirm({
              title: '警告',
              content: '确认要删除，删除操作将不可恢复？',
              onOk: () => {resolve(true);},
              onCancel: () => {resolve(false);}
          });
        });
        if(!confirmResult){
            return;
        }
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { case: '%case%' });
        Object.assign(params, { id: '%case%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        if(Object.is(actionTarget,"MULTIKEY")){
            let tempDataArray:Array<any> = [];
            if((_args.length >1) && (Object.keys(data).length >0)){
                for(let i =0;i<_args.length;i++){
                    let tempObject:any = {};
                    Object.keys(data).forEach((key:string) =>{
                        Object.assign(tempObject,{[key]:data[key].split(',')[i]});
                    })
                    tempDataArray.push(tempObject);
                }
            }else{
                tempDataArray.push(data);
            }
            data = tempDataArray;
        }
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        if(!Object.is(actionTarget,"MULTIKEY")){
            Object.assign(data,parentObj);
        }
        Object.assign(context,parentObj);
        // 直接调实体服务需要转换的数据
        if(context && context.srfsessionid){
          context.srfsessionkey = context.srfsessionid;
            delete context.srfsessionid;
        }
        
        const backend = () => {
            const curService:CaseService =  new CaseService();
            curService.Remove(context,data, true).then((response: any) => {
                if (!response || response.status !== 200) {
                    actionContext.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                actionContext.$Notice.success({ title: '成功', desc: '已删除' });
                const _this: any = actionContext;
                if (xData && xData.refresh && xData.refresh instanceof Function) {
                    xData.refresh(args);
                }
                actionContext.closeView(null);
                return response;
            }).catch((response: any) => {
                if (!response || !response.status || !response.data) {
                    actionContext.$Notice.error({ title: '错误', desc: '系统异常！' });
                    return;
                }
                if (response && response.data) {
                    actionContext.$Notice.error({ title: '错误', desc: response.data.message });
                    return;
                }
                if (response.status === 401) {
                    return;
                }
                return response;
            });
        };
        backend();
    }


    /**
     * 获取指定数据的重定向页面
     * 
     * @param srfkey 数据主键
     * @param isEnableWorkflow  重定向视图是否需要处理流程中的数据
     * @memberof  CaseUIServiceBase
     */
    public async getRDAppViewEX(srfkey:string,isEnableWorkflow:boolean,rdviewname:string){        
        this.isEnableWorkflow = isEnableWorkflow;
        // 进行数据查询
        let result:any = await this.dataService.Get({case:srfkey});
        const curData:any = result.data;
        //判断当前数据模式,默认为true，todo
        const iRealDEModel:boolean = true;
        //常规重定向
        const dataTypeDEField:string|null =null;
        let strRDViewParam = rdviewname +':RDITEM:'+ curData[dataTypeDEField!];
        if(this.allViewMap.has(strRDViewParam)){
            return this.allViewMap.get(strRDViewParam);
        }
        let bDataInWF:boolean = false;
		let bWFMode:any = false;
		// 计算数据模式
		if (this.isEnableWorkflow) {
			bDataInWF = await this.dataService.testDataInWF({stateValue:this.stateValue,stateField:this.stateField},curData);
			if (bDataInWF) {
				bDataInWF = true;
				bWFMode = await this.dataService.testUserExistWorklist(null,curData);
			}
        }
        let strPDTViewParam:string = await this.getDESDDEViewPDTParam(curData, bDataInWF, bWFMode);
        //若不是当前数据模式，处理strPDTViewParam，todo

        //查找视图

        //返回视图
        return this.allViewMap.get(strPDTViewParam);
    }

    /**
     * 获取指定数据的重定向页面
     * 
     * @param srfkey 数据主键
     * @param isEnableWorkflow  重定向视图是否需要处理流程中的数据
     * @memberof  CaseUIServiceBase
     */
    public async getRDAppView(srfkey:string,isEnableWorkflow:boolean){
        return this.getRDAppViewEX(srfkey,isEnableWorkflow,'')
    }

    /**
	 * 获取实际的数据类型
     * 
     * @memberof  CaseUIServiceBase
	 */
	public getRealDEType(entity:any){

    }

    /**
     * 获取实体单数据实体视图预定义参数
     * 
     * @param curData 当前数据
     * @param bDataInWF 是否有数据在工作流中
     * @param bWFMode   是否工作流模式
     * @memberof  CaseUIServiceBase
     */
    public async getDESDDEViewPDTParam(curData:any, bDataInWF:boolean, bWFMode:boolean){
        let strPDTParam:string = '';
		if (bDataInWF) {
			// 判断数据是否在流程中
        }
        //多表单，todo
        const multiFormDEField:string|null =null;

        if (multiFormDEField) {
			const objFormValue:string = curData[multiFormDEField];
			if(!Environment.isAppMode){
				return 'MOBEDITVIEW:'+objFormValue;
			}
			return 'EDITVIEW:'+objFormValue;
        }
        const stateTag = this.getDEMainStateTag(curData);
		if(!Environment.isAppMode){
            if (stateTag) {
                return `MOBEDITVIEW:MSTAG:${stateTag}`;
            }
			return 'MOBEDITVIEW:';
        }
        if(stateTag){
            return `EDITVIEW:MSTAG:${stateTag}`;
        }
		return 'EDITVIEW:';
    }

    /**
     * 获取数据对象的主状态标识
     * 
     * @param curData 当前数据
     * @memberof  CaseUIServiceBase
     */  
    public getDEMainStateTag(curData:any){
        if(this.mainStateFields.length === 0) return null;

        this.mainStateFields.forEach((singleMainField:any) =>{
            if (!(singleMainField in curData)) {
                console.warn(`当前数据对象不包含属性「${singleMainField}」，根据「${singleMainField}」属性进行的主状态计算默认为空值`);
            }
        })
        for (let i = 0; i <= 1; i++) {
            let strTag:string = (curData[this.mainStateFields[0]] != null && curData[this.mainStateFields[0]] !== "")?(i == 0) ? `${curData[this.mainStateFields[0]]}` : "":"";
            if (this.mainStateFields.length >= 2) {
                for (let j = 0; j <= 1; j++) {
                    let strTag2:string = (curData[this.mainStateFields[1]] != null && curData[this.mainStateFields[1]] !== "")?`${strTag}__${(j == 0) ? `${curData[this.mainStateFields[1]]}` : ""}`:strTag;
                    if (this.mainStateFields.length >= 3) {
                        for (let k = 0; k <= 1; k++) {
                            let strTag3:string = (curData[this.mainStateFields[2]] != null && curData[this.mainStateFields[2]] !== "")?`${strTag2}__${(k == 0) ? `${curData[this.mainStateFields[2]]}` : ""}`:strTag2;
                            // 判断是否存在
                            return this.allDeMainStateMap.get(strTag3);
                        }
                    }else{
                        return this.allDeMainStateMap.get(strTag2);
                    }
                }
            }else{
                return this.allDeMainStateMap.get(strTag);
            }
        }
        return null;
    }

    /**
    * 获取数据对象当前操作标识
    * 
    * @param data 当前数据
    * @memberof  CaseUIServiceBase
    */  
    public getDEMainStateOPPrivs(data:any){
        const stateTag = this.getDEMainStateTag(data);
        if (stateTag) {
            return this.allDeMainStateOPPrivsMap.get(stateTag);
        } else {
            return null;
        }
    }

    /**
    * 获取数据对象所有的操作标识
    * 
    * @param data 当前数据
    * @memberof  CaseUIServiceBase
    */ 
   public getAllOPPrivs(data:any){
       return this.authService.getOPPrivs(this.getDEMainStateOPPrivs(data));
   }

}