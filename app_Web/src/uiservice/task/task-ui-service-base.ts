import { Environment } from '@/environments/environment';
import { UIActionTool,Util } from '@/utils';
import UIService from '../ui-service';
import { Subject } from 'rxjs';
import TaskService from '@/service/task/task-service';
import TaskAuthService from '@/authservice/task/task-auth-service';

/**
 * 任务UI服务对象基类
 *
 * @export
 * @class TaskUIServiceBase
 */
export default class TaskUIServiceBase extends UIService {

    /**
     * 是否支持工作流
     * 
     * @memberof  TaskUIServiceBase
     */
    public isEnableWorkflow:boolean = false;

    /**
     * 是否支持实体主状态
     * 
     * @memberof  TaskUIServiceBase
     */
    public isEnableDEMainState:boolean = true;

    /**
     * 当前UI服务对应的数据服务对象
     * 
     * @memberof  TaskUIServiceBase
     */
    public dataService:TaskService = new TaskService();

    /**
     * 所有关联视图
     * 
     * @memberof  TaskUIServiceBase
     */ 
    public allViewMap: Map<string, Object> = new Map();

    /**
     * 状态值
     * 
     * @memberof  TaskUIServiceBase
     */ 
    public stateValue: number = 0;

    /**
     * 状态属性
     * 
     * @memberof  TaskUIServiceBase
     */ 
    public stateField: string = "";

    /**
     * 主状态属性集合
     * 
     * @memberof  TaskUIServiceBase
     */  
    public mainStateFields:Array<any> = ['status1','isfavorites','tasktype'];

    /**
     * 主状态集合Map
     * 
     * @memberof  TaskUIServiceBase
     */  
    public allDeMainStateMap:Map<string,string> = new Map();

    /**
     * 主状态操作标识Map
     * 
     * @memberof  TaskUIServiceBase
     */ 
    public allDeMainStateOPPrivsMap:Map<string,any> = new Map();

    /**
     * Creates an instance of  TaskUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
        this.authService = new TaskAuthService(opts);
        this.initViewMap();
        this.initDeMainStateMap();
        this.initDeMainStateOPPrivsMap();
    }

    /**
     * 初始化视图Map
     * 
     * @memberof  TaskUIServiceBase
     */  
    public initViewMap(){
        this.allViewMap.set('MDATAVIEW:', {
            viewname: 'gridview',
            srfappde: 'tasks',
            component: 'task-grid-view',
            openmode: '',
            title: '任务',
            width: 0,
            height: 0
        });
        this.allViewMap.set('PICKUPVIEW:', {
            viewname: 'pickupview',
            srfappde: 'tasks',
            component: 'task-pickup-view',
            openmode: '',
            title: '任务',
            width: 0,
            height: 0
        });
    }

    /**
     * 初始化主状态集合
     * 
     * @memberof  TaskUIServiceBase
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
     * @memberof  TaskUIServiceBase
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
     * MORE
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
    public async Task_More(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
            { pathName: 'mainmygridview', parameterName: 'mainmygridview' },
        ];
        const openIndexViewTab = (data: any) => {
            const routePath = actionContext.$viewTool.buildUpRoutePath(actionContext.$route, context, deResParameters, parameters, _args, data);
            actionContext.$router.push(routePath);
            return null;
        }
        openIndexViewTab(data);
    }

    /**
     * 关闭
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
    public async Task_CloseTask(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-close-task-view', 
                height: 600, 
                width: 800,  
                title: actionContext.$t('entities.task.views.closetaskview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 工时
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
    public async Task_WorkHours1(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-work-hours-view', 
                height: 600, 
                width: 800,  
                title: actionContext.$t('entities.task.views.workhoursview.title'),
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
    public async Task_MainEdit(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-main-edit-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.task.views.maineditview.title'),
                placement: 'DRAWER_TOP',
            };
            openDrawer(view, data);
    }

    /**
     * 指派
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
    public async Task_AssignTask(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-assign-task-view', 
                height: 600, 
                width: 800,  
                title: actionContext.$t('entities.task.views.assigntaskview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 开始
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
    public async Task_MStartTaskDash(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(params,{actioninfo:"当前任务只有%1$s才可以开始。"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
            const curService:TaskService =  new TaskService();
            curService.TaskForward(context,data, false).then((response: any) => {
                if (!response || response.status !== 200) {
                    actionContext.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                const _this: any = actionContext;
                const { data: result } = response;
                let _args: any[] = [];
                if (Object.is(actionContext.$util.typeOf(result), 'array')) {
                    _args = [...result];
                } else if (Object.is(actionContext.$util.typeOf(result), 'object')) {
                    _args = [{...result}];
                } else {
                    _args = [...args];
                }
                if (this.Task_StartTaskDash && this.Task_StartTaskDash instanceof Function) {
                    this.Task_StartTaskDash(_args,context, params, $event, xData,actionContext);
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
    public async Task_MainEditDash(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-main-edit-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.task.views.maineditview.title'),
                placement: 'DRAWER_TOP',
            };
            openDrawer(view, data);
    }

    /**
     * 继续
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
    public async Task_ReStartTeamTask(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(params,{actioninfo:"当前任务只有%1$s才可以继续。"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
            const curService:TaskService =  new TaskService();
            curService.TaskForward(context,data, false).then((response: any) => {
                if (!response || response.status !== 200) {
                    actionContext.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                const _this: any = actionContext;
                const { data: result } = response;
                let _args: any[] = [];
                if (Object.is(actionContext.$util.typeOf(result), 'array')) {
                    _args = [...result];
                } else if (Object.is(actionContext.$util.typeOf(result), 'object')) {
                    _args = [{...result}];
                } else {
                    _args = [...args];
                }
                if (this.Task_ReStartTask && this.Task_ReStartTask instanceof Function) {
                    this.Task_ReStartTask(_args,context, params, $event, xData,actionContext);
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
     * 关闭
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
    public async Task_CloseTaskDash(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-close-task-view', 
                height: 600, 
                width: 800,  
                title: actionContext.$t('entities.task.views.closetaskview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 子任务
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
    public async Task_NewSubTaskDash(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{PROJECT:"%project%",MODULE:"%module%"});
        Object.assign(params,{project:"%project%",module:"%module%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.task && true){
            deResParameters = [
            { pathName: 'tasks', parameterName: 'task' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'subtasks', parameterName: 'subtask' },
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
                viewname: 'task-sub-task-new-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.subtask.views.subtasknewview.title'),
                placement: 'DRAWER_TOP',
            };
            openDrawer(view, data);
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
    public async Task_confirmStoryChange(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
            const curService:TaskService =  new TaskService();
            curService.ConfirmStoryChange(context,data, true).then((response: any) => {
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
     * 转交
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
    public async Task_ForwardDash(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-forward-view', 
                height: 600, 
                width: 800,  
                title: actionContext.$t('entities.task.views.forwardview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 转交
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
    public async Task_CheckForward(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(params,{actioninfo:"当前任务只有%1$s才可以转交。"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
            const curService:TaskService =  new TaskService();
            curService.TaskForward(context,data, false).then((response: any) => {
                if (!response || response.status !== 200) {
                    actionContext.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                const _this: any = actionContext;
                const { data: result } = response;
                let _args: any[] = [];
                if (Object.is(actionContext.$util.typeOf(result), 'array')) {
                    _args = [...result];
                } else if (Object.is(actionContext.$util.typeOf(result), 'object')) {
                    _args = [{...result}];
                } else {
                    _args = [...args];
                }
                if (this.Task_Forward && this.Task_Forward instanceof Function) {
                    this.Task_Forward(_args,context, params, $event, xData,actionContext);
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
     * 激活
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
    public async Task_Activation(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-activation-task-view', 
                height: 600, 
                width: 800,  
                title: actionContext.$t('entities.task.views.activationtaskview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 完成
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
    public async Task_DoneTaskDash(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{TASK:"%id%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-done-task-view', 
                height: 600, 
                width: 800,  
                title: actionContext.$t('entities.task.views.donetaskview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 开始
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
    public async Task_MStartTaskDash1(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(params,{actioninfo:"当前任务只有%1$s才可以开始。"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
            const curService:TaskService =  new TaskService();
            curService.TaskForward(context,data, false).then((response: any) => {
                if (!response || response.status !== 200) {
                    actionContext.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                const _this: any = actionContext;
                const { data: result } = response;
                let _args: any[] = [];
                if (Object.is(actionContext.$util.typeOf(result), 'array')) {
                    _args = [...result];
                } else if (Object.is(actionContext.$util.typeOf(result), 'object')) {
                    _args = [{...result}];
                } else {
                    _args = [...args];
                }
                if (this.Task_StartTask && this.Task_StartTask instanceof Function) {
                    this.Task_StartTask(_args,context, params, $event, xData,actionContext);
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
     * 暂停
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
    public async Task_MPauseTask(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(params,{actioninfo:"当前任务只有%1$s才可以暂停任务。"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
            const curService:TaskService =  new TaskService();
            curService.TaskForward(context,data, false).then((response: any) => {
                if (!response || response.status !== 200) {
                    actionContext.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                const _this: any = actionContext;
                const { data: result } = response;
                let _args: any[] = [];
                if (Object.is(actionContext.$util.typeOf(result), 'array')) {
                    _args = [...result];
                } else if (Object.is(actionContext.$util.typeOf(result), 'object')) {
                    _args = [{...result}];
                } else {
                    _args = [...args];
                }
                if (this.Task_PauseTaskDash && this.Task_PauseTaskDash instanceof Function) {
                    this.Task_PauseTaskDash(_args,context, params, $event, xData,actionContext);
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
     * 取消
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
    public async Task_CancelTaskDash(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-cancel-task-view', 
                height: 600, 
                width: 800,  
                title: actionContext.$t('entities.task.views.canceltaskview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 指派
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
    public async Task_AssignTaskDash(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-assign-task-view', 
                height: 600, 
                width: 800,  
                title: actionContext.$t('entities.task.views.assigntaskview.title'),
            };
            openPopupModal(view, data);
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
    public async Task_TaskFavorites(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
            const curService:TaskService =  new TaskService();
            curService.TaskFavorites(context,data, true).then((response: any) => {
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
     * 完成
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
    public async Task_done(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(params,{actioninfo:"当前任务只有%1$s才可以完成。"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
            const curService:TaskService =  new TaskService();
            curService.TaskForward(context,data, false).then((response: any) => {
                if (!response || response.status !== 200) {
                    actionContext.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                const _this: any = actionContext;
                const { data: result } = response;
                let _args: any[] = [];
                if (Object.is(actionContext.$util.typeOf(result), 'array')) {
                    _args = [...result];
                } else if (Object.is(actionContext.$util.typeOf(result), 'object')) {
                    _args = [{...result}];
                } else {
                    _args = [...args];
                }
                if (this.Task_DoneTask && this.Task_DoneTask instanceof Function) {
                    this.Task_DoneTask(_args,context, params, $event, xData,actionContext);
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
     * 工时
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
    public async Task_MWorkHours(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(params,{actioninfo:"当前任务只有%1$s才可以记录工时。"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
            const curService:TaskService =  new TaskService();
            curService.TaskForward(context,data, false).then((response: any) => {
                if (!response || response.status !== 200) {
                    actionContext.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                const _this: any = actionContext;
                const { data: result } = response;
                let _args: any[] = [];
                if (Object.is(actionContext.$util.typeOf(result), 'array')) {
                    _args = [...result];
                } else if (Object.is(actionContext.$util.typeOf(result), 'object')) {
                    _args = [{...result}];
                } else {
                    _args = [...args];
                }
                if (this.Task_WorkHours && this.Task_WorkHours instanceof Function) {
                    this.Task_WorkHours(_args,context, params, $event, xData,actionContext);
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
     * 更多
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
    public async Task_ChildMore(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(params,{n_parent_eq:"%parent%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
            { pathName: 'childmoregridview', parameterName: 'childmoregridview' },
        ];
        const openIndexViewTab = (data: any) => {
            const routePath = actionContext.$viewTool.buildUpRoutePath(actionContext.$route, context, deResParameters, parameters, _args, data);
            actionContext.$router.push(routePath);
            return null;
        }
        openIndexViewTab(data);
    }

    /**
     * 提缺陷
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
    public async Task_TaskToBug(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{MAILTO:"%mailto%",ASSIGNEDTO:"%assignedto%",TASK:"%task%",STORYNAME:"%storyname%",MODULE:"%module%",CURPROJECT:"%project%",STORY:"%story%",PROJECT:"%project%",MODULENAME:"%modulename%",COLOR:"%color%",BRANCH:"0",TITLE:"%name%"});
        Object.assign(params,{mailto:"%mailto%",modulename:"%modulename%",story:"%story%",assignedto:"%assignedto%",project:"%project%",title:"%name%",module:"%module%",curproject:"%project%",color:"%color%",task:"%task%",branch:"0",storyname:"%storyname%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
            { pathName: 'bugs', parameterName: 'bug' },
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
                viewname: 'bug-task-to-bug-edit-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.bug.views.tasktobugeditview.title'),
                placement: 'DRAWER_RIGHT',
            };
            openDrawer(view, data);
    }

    /**
     * 转交
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
    public async Task_Forward(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{TASK:"%id%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-forward-view', 
                height: 600, 
                width: 800,  
                title: actionContext.$t('entities.task.views.forwardview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 下载
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
    public async Task_BatchDownload(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
            // 自定义实体界面行为
            actionContext.$Notice.warning({ title: '错误', desc: '下载 未实现' });

    }

    /**
     * 开始
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
    public async Task_StartTaskDash(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-open-task-view', 
                height: 600, 
                width: 800,  
                title: actionContext.$t('entities.task.views.opentaskview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 子任务
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
    public async Task_NewSubTask(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{PROJECT:"%project%",MODULE:"%module%"});
        Object.assign(params,{project:"%project%",module:"%module%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.task && true){
            deResParameters = [
            { pathName: 'tasks', parameterName: 'task' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'subtasks', parameterName: 'subtask' },
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
                viewname: 'task-sub-task-new-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.subtask.views.subtasknewview.title'),
                placement: 'DRAWER_TOP',
            };
            openDrawer(view, data);
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
    public async Task_TaskNFavorites(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
            const curService:TaskService =  new TaskService();
            curService.TaskNFavorites(context,data, true).then((response: any) => {
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
     * 完成
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
    public async Task_DoneTask(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{TASK:"%id%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-done-task-view', 
                height: 600, 
                width: 800,  
                title: actionContext.$t('entities.task.views.donetaskview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 继续
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
    public async Task_ReStartTask(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
        ];
            const openPopupModal = (view: any, data: any) => {
                let container: Subject<any> = actionContext.$appmodal.openModal(view, context, data);
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
                viewname: 'task-restart-task-view', 
                height: 600, 
                width: 800,  
                title: actionContext.$t('entities.task.views.restarttaskview.title'),
            };
            openPopupModal(view, data);
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
    public async Task_confirmStoryChangeCz(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
            const curService:TaskService =  new TaskService();
            curService.ConfirmStoryChange(context,data, true).then((response: any) => {
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
     * 工时
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
    public async Task_WorkHours(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{PROJECT:"%project%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-work-hours-view', 
                height: 600, 
                width: 800,  
                title: actionContext.$t('entities.task.views.workhoursview.title'),
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
    public async Task_Create(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-edit-view', 
                height: 0, 
                width: 0,  
                title: actionContext.$t('entities.task.views.editview.title'),
                placement: 'DRAWER_RIGHT',
            };
            openDrawer(view, data);
    }

    /**
     * 激活
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
    public async Task_ActivationDash(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-activation-task-view', 
                height: 600, 
                width: 800,  
                title: actionContext.$t('entities.task.views.activationtaskview.title'),
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
    public async Task_delete(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
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
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
            const curService:TaskService =  new TaskService();
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
     * 开始
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
    public async Task_StartTask(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(context,{TASK:"%id%"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-open-task-view', 
                height: 600, 
                width: 800,  
                title: actionContext.$t('entities.task.views.opentaskview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 暂停
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
    public async Task_PauseTaskDash(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
    
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
        if(context.projectmodule && true){
            deResParameters = [
            { pathName: 'projectmodules', parameterName: 'projectmodule' },
            ]
        }
        const parameters: any[] = [
            { pathName: 'tasks', parameterName: 'task' },
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
                viewname: 'task-pause-task-view', 
                height: 600, 
                width: 800,  
                title: actionContext.$t('entities.task.views.pausetaskview.title'),
            };
            openPopupModal(view, data);
    }

    /**
     * 转交
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
    public async Task_checkForwardDash(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(params,{actioninfo:"当前任务只有%1$s才可以转交。"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
            const curService:TaskService =  new TaskService();
            curService.TaskForward(context,data, false).then((response: any) => {
                if (!response || response.status !== 200) {
                    actionContext.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                const _this: any = actionContext;
                const { data: result } = response;
                let _args: any[] = [];
                if (Object.is(actionContext.$util.typeOf(result), 'array')) {
                    _args = [...result];
                } else if (Object.is(actionContext.$util.typeOf(result), 'object')) {
                    _args = [{...result}];
                } else {
                    _args = [...args];
                }
                if (this.Task_ForwardDash && this.Task_ForwardDash instanceof Function) {
                    this.Task_ForwardDash(_args,context, params, $event, xData,actionContext);
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
     * 完成
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
    public async Task_MDoneTask(args: any[],context:any = {}, params:any = {}, $event?: any, xData?: any,actionContext?: any,srfParentDeName?:string){
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        Object.assign(params,{actioninfo:"当前任务只有%1$s才可以完成。"});
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { task: '%task%' });
        Object.assign(params, { id: '%task%' });
        Object.assign(params, { name: '%name%' });
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
            const curService:TaskService =  new TaskService();
            curService.TaskForward(context,data, false).then((response: any) => {
                if (!response || response.status !== 200) {
                    actionContext.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                const _this: any = actionContext;
                const { data: result } = response;
                let _args: any[] = [];
                if (Object.is(actionContext.$util.typeOf(result), 'array')) {
                    _args = [...result];
                } else if (Object.is(actionContext.$util.typeOf(result), 'object')) {
                    _args = [{...result}];
                } else {
                    _args = [...args];
                }
                if (this.Task_DoneTaskDash && this.Task_DoneTaskDash instanceof Function) {
                    this.Task_DoneTaskDash(_args,context, params, $event, xData,actionContext);
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
     * 获取指定数据的重定向页面
     * 
     * @param srfkey 数据主键
     * @param isEnableWorkflow  重定向视图是否需要处理流程中的数据
     * @memberof  TaskUIServiceBase
     */
    public async getRDAppViewEX(srfkey:string,isEnableWorkflow:boolean,rdviewname:string){        
        this.isEnableWorkflow = isEnableWorkflow;
        // 进行数据查询
        let result:any = await this.dataService.Get({task:srfkey});
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
     * @memberof  TaskUIServiceBase
     */
    public async getRDAppView(srfkey:string,isEnableWorkflow:boolean){
        this.getRDAppViewEX(srfkey,isEnableWorkflow,'')
    }

    /**
	 * 获取实际的数据类型
     * 
     * @memberof  TaskUIServiceBase
	 */
	public getRealDEType(entity:any){

    }

    /**
     * 获取实体单数据实体视图预定义参数
     * 
     * @param curData 当前数据
     * @param bDataInWF 是否有数据在工作流中
     * @param bWFMode   是否工作流模式
     * @memberof  TaskUIServiceBase
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
     * @memberof  TaskUIServiceBase
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
    * @memberof  TaskUIServiceBase
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
    * @memberof  TaskUIServiceBase
    */ 
   public getAllOPPrivs(data:any){
       return this.authService.getOPPrivs(this.getDEMainStateOPPrivs(data));
   }

}