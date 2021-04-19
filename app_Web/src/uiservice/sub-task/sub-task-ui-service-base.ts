import { Environment } from '@/environments/environment';
import { UIActionTool,Util } from '@/utils';
import UIService from '../ui-service';
import { Subject } from 'rxjs';
import SubTaskService from '@/service/sub-task/sub-task-service';
import SubTaskAuthService from '@/authservice/sub-task/sub-task-auth-service';

/**
 * 任务UI服务对象基类
 *
 * @export
 * @class SubTaskUIServiceBase
 */
export default class SubTaskUIServiceBase extends UIService {

    /**
     * 是否支持工作流
     * 
     * @memberof  SubTaskUIServiceBase
     */
    public isEnableWorkflow:boolean = false;

    /**
     * 是否支持实体主状态
     * 
     * @memberof  SubTaskUIServiceBase
     */
    public isEnableDEMainState:boolean = true;

    /**
     * 当前UI服务对应的数据服务对象
     * 
     * @memberof  SubTaskUIServiceBase
     */
    public dataService:SubTaskService = new SubTaskService();

    /**
     * 所有关联视图
     * 
     * @memberof  SubTaskUIServiceBase
     */ 
    public allViewMap: Map<string, Object> = new Map();

    /**
     * 状态值
     * 
     * @memberof  SubTaskUIServiceBase
     */ 
    public stateValue: number = 0;

    /**
     * 状态属性
     * 
     * @memberof  SubTaskUIServiceBase
     */ 
    public stateField: string = "";

    /**
     * 主状态属性集合
     * 
     * @memberof  SubTaskUIServiceBase
     */  
    public mainStateFields:Array<any> = ['status1','isfavorites','tasktype'];

    /**
     * 主状态集合Map
     * 
     * @memberof  SubTaskUIServiceBase
     */  
    public allDeMainStateMap:Map<string,string> = new Map();

    /**
     * 主状态操作标识Map
     * 
     * @memberof  SubTaskUIServiceBase
     */ 
    public allDeMainStateOPPrivsMap:Map<string,any> = new Map();

    /**
     * Creates an instance of  SubTaskUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  SubTaskUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
        this.authService = new SubTaskAuthService(opts);
        this.initViewMap();
        this.initDeMainStateMap();
        this.initDeMainStateOPPrivsMap();
    }

    /**
     * 初始化视图Map
     * 
     * @memberof  SubTaskUIServiceBase
     */  
    public initViewMap(){
    }

    /**
     * 初始化主状态集合
     * 
     * @memberof  SubTaskUIServiceBase
     */  
    public initDeMainStateMap(){
        this.allDeMainStateMap.set('cancel__0__10','cancel__0__10');
        this.allDeMainStateMap.set('cancel__0__20','cancel__0__20');
        this.allDeMainStateMap.set('cancel__0__30','cancel__0__30');
        this.allDeMainStateMap.set('cancel__0__40','cancel__0__40');
        this.allDeMainStateMap.set('cancel__1__10','cancel__1__10');
        this.allDeMainStateMap.set('cancel__1__20','cancel__1__20');
        this.allDeMainStateMap.set('cancel__1__30','cancel__1__30');
        this.allDeMainStateMap.set('cancel__1__40','cancel__1__40');
        this.allDeMainStateMap.set('closed__0__10','closed__0__10');
        this.allDeMainStateMap.set('closed__0__20','closed__0__20');
        this.allDeMainStateMap.set('closed__0__30','closed__0__30');
        this.allDeMainStateMap.set('closed__0__40','closed__0__40');
        this.allDeMainStateMap.set('closed__1__10','closed__1__10');
        this.allDeMainStateMap.set('closed__1__20','closed__1__20');
        this.allDeMainStateMap.set('closed__1__30','closed__1__30');
        this.allDeMainStateMap.set('closed__1__40','closed__1__40');
        this.allDeMainStateMap.set('doing__0__10','doing__0__10');
        this.allDeMainStateMap.set('doing__0__20','doing__0__20');
        this.allDeMainStateMap.set('doing__0__30','doing__0__30');
        this.allDeMainStateMap.set('doing__0__40','doing__0__40');
        this.allDeMainStateMap.set('doing__1__10','doing__1__10');
        this.allDeMainStateMap.set('doing__1__20','doing__1__20');
        this.allDeMainStateMap.set('doing__1__30','doing__1__30');
        this.allDeMainStateMap.set('doing__1__40','doing__1__40');
        this.allDeMainStateMap.set('done__0__10','done__0__10');
        this.allDeMainStateMap.set('done__0__20','done__0__20');
        this.allDeMainStateMap.set('done__0__30','done__0__30');
        this.allDeMainStateMap.set('done__0__40','done__0__40');
        this.allDeMainStateMap.set('done__1__10','done__1__10');
        this.allDeMainStateMap.set('done__1__20','done__1__20');
        this.allDeMainStateMap.set('done__1__30','done__1__30');
        this.allDeMainStateMap.set('done__1__40','done__1__40');
        this.allDeMainStateMap.set('pause__0__10','pause__0__10');
        this.allDeMainStateMap.set('pause__0__20','pause__0__20');
        this.allDeMainStateMap.set('pause__0__30','pause__0__30');
        this.allDeMainStateMap.set('pause__0__40','pause__0__40');
        this.allDeMainStateMap.set('pause__1__10','pause__1__10');
        this.allDeMainStateMap.set('pause__1__20','pause__1__20');
        this.allDeMainStateMap.set('pause__1__30','pause__1__30');
        this.allDeMainStateMap.set('pause__1__40','pause__1__40');
        this.allDeMainStateMap.set('storychange__0__10','storychange__0__10');
        this.allDeMainStateMap.set('storychange__0__20','storychange__0__20');
        this.allDeMainStateMap.set('storychange__0__30','storychange__0__30');
        this.allDeMainStateMap.set('storychange__0__40','storychange__0__40');
        this.allDeMainStateMap.set('storychange__1__10','storychange__1__10');
        this.allDeMainStateMap.set('storychange__1__20','storychange__1__20');
        this.allDeMainStateMap.set('storychange__1__30','storychange__1__30');
        this.allDeMainStateMap.set('storychange__1__40','storychange__1__40');
        this.allDeMainStateMap.set('wait__0__10','wait__0__10');
        this.allDeMainStateMap.set('wait__0__20','wait__0__20');
        this.allDeMainStateMap.set('wait__0__30','wait__0__30');
        this.allDeMainStateMap.set('wait__0__40','wait__0__40');
        this.allDeMainStateMap.set('wait__1__10','wait__1__10');
        this.allDeMainStateMap.set('wait__1__20','wait__1__20');
        this.allDeMainStateMap.set('wait__1__30','wait__1__30');
        this.allDeMainStateMap.set('wait__1__40','wait__1__40');
    }

    /**
     * 初始化主状态操作标识
     * 
     * @memberof  SubTaskUIServiceBase
     */  
    public initDeMainStateOPPrivsMap(){
        this.allDeMainStateOPPrivsMap.set('cancel__0__10',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('cancel__0__20',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_DELETE_BUT':0,'SRFUR__TASK_CONSUM_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_START_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('cancel__0__30',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('cancel__0__40',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('cancel__1__10',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('cancel__1__20',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_DELETE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_CONSUM_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('cancel__1__30',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('cancel__1__40',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('closed__0__10',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('closed__0__20',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_DELETE_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_CONSUM_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('closed__0__30',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_START_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('closed__0__40',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('closed__1__10',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_START_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('closed__1__20',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_CONSUM_BUT':0,'SRFUR__TASK_DELETE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('closed__1__30',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('closed__1__40',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('doing__0__10',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('doing__0__20',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_CONSUM_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,'SRFUR__TASK_DELETE_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('doing__0__30',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('doing__0__40',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('doing__1__10',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('doing__1__20',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_CONSUM_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_DELETE_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('doing__1__30',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('doing__1__40',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('done__0__10',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('done__0__20',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_CONSUM_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_DELETE_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,'SRFUR__TASK_START_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('done__0__30',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('done__0__40',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('done__1__10',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('done__1__20',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_DELETE_BUT':0,'SRFUR__TASK_CONSUM_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,'SRFUR__TASK_START_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('done__1__30',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('done__1__40',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('pause__0__10',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('pause__0__20',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_DELETE_BUT':0,'SRFUR__TASK_CONSUM_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('pause__0__30',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('pause__0__40',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('pause__1__10',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_START_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('pause__1__20',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_CONSUM_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_DELETE_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('pause__1__30',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('pause__1__40',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('storychange__0__10',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,'SRFUR__TASK_START_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('storychange__0__20',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_DELETE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_CONSUM_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('storychange__0__30',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('storychange__0__40',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('storychange__1__10',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('storychange__1__20',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_CONSUM_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_DELETE_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('storychange__1__30',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_FJTASK_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('storychange__1__40',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_CANCEL_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('wait__0__10',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('wait__0__20',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_DELETE_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,'SRFUR__TASK_CONSUM_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('wait__0__30',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('wait__0__40',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_FAVOR_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('wait__1__10',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('wait__1__20',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_DELETE_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_ASSIGN_BUT':0,'SRFUR__TASK_CONSUM_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_COMPLETE_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__STORY_FJTASK_BUT':0,'SRFUR__TASK_START_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('wait__1__30',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('wait__1__40',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__TASK_ACTIVATION_BUT':0,'SRFUR__TASK_SUBTASKS_BUT':0,'SRFUR__TASK_XQCHANGE_BUT':0,'SRFUR__TASK_PAUSE_BUT':0,'SRFUR__TASK_PROCEED_BUT':0,'SRFUR__TASK_NFAVOR_BUT':0,'SRFUR__TASK_FORWARD_BUT':0,'SRFUR__TASK_CLOSE_BUT':0,}));
    }


    /**
     * 获取指定数据的重定向页面
     * 
     * @param srfkey 数据主键
     * @param isEnableWorkflow  重定向视图是否需要处理流程中的数据
     * @memberof  SubTaskUIServiceBase
     */
    public async getRDAppViewEX(srfkey:string,isEnableWorkflow:boolean,rdviewname:string){        
        this.isEnableWorkflow = isEnableWorkflow;
        // 进行数据查询
        let result:any = await this.dataService.Get({subtask:srfkey});
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
     * @memberof  SubTaskUIServiceBase
     */
    public async getRDAppView(srfkey:string,isEnableWorkflow:boolean){
        this.getRDAppViewEX(srfkey,isEnableWorkflow,'')
    }

    /**
	 * 获取实际的数据类型
     * 
     * @memberof  SubTaskUIServiceBase
	 */
	public getRealDEType(entity:any){

    }

    /**
     * 获取实体单数据实体视图预定义参数
     * 
     * @param curData 当前数据
     * @param bDataInWF 是否有数据在工作流中
     * @param bWFMode   是否工作流模式
     * @memberof  SubTaskUIServiceBase
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
     * @memberof  SubTaskUIServiceBase
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
    * @memberof  SubTaskUIServiceBase
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
    * @memberof  SubTaskUIServiceBase
    */ 
   public getAllOPPrivs(data:any){
       return this.authService.getOPPrivs(this.getDEMainStateOPPrivs(data));
   }

}