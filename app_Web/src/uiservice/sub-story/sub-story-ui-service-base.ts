import { Environment } from '@/environments/environment';
import { UIActionTool,Util } from '@/utils';
import UIService from '../ui-service';
import { Subject } from 'rxjs';
import SubStoryService from '@/service/sub-story/sub-story-service';
import SubStoryAuthService from '@/authservice/sub-story/sub-story-auth-service';

/**
 * 需求UI服务对象基类
 *
 * @export
 * @class SubStoryUIServiceBase
 */
export default class SubStoryUIServiceBase extends UIService {

    /**
     * 是否支持工作流
     * 
     * @memberof  SubStoryUIServiceBase
     */
    public isEnableWorkflow:boolean = false;

    /**
     * 是否支持实体主状态
     * 
     * @memberof  SubStoryUIServiceBase
     */
    public isEnableDEMainState:boolean = true;

    /**
     * 当前UI服务对应的数据服务对象
     * 
     * @memberof  SubStoryUIServiceBase
     */
    public dataService:SubStoryService = new SubStoryService();

    /**
     * 所有关联视图
     * 
     * @memberof  SubStoryUIServiceBase
     */ 
    public allViewMap: Map<string, Object> = new Map();

    /**
     * 状态值
     * 
     * @memberof  SubStoryUIServiceBase
     */ 
    public stateValue: number = 0;

    /**
     * 状态属性
     * 
     * @memberof  SubStoryUIServiceBase
     */ 
    public stateField: string = "";

    /**
     * 主状态属性集合
     * 
     * @memberof  SubStoryUIServiceBase
     */  
    public mainStateFields:Array<any> = ['status','isfavorites','ischild'];

    /**
     * 主状态集合Map
     * 
     * @memberof  SubStoryUIServiceBase
     */  
    public allDeMainStateMap:Map<string,string> = new Map();

    /**
     * 主状态操作标识Map
     * 
     * @memberof  SubStoryUIServiceBase
     */ 
    public allDeMainStateOPPrivsMap:Map<string,any> = new Map();

    /**
     * Creates an instance of  SubStoryUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  SubStoryUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
        this.authService = new SubStoryAuthService(opts);
        this.initViewMap();
        this.initDeMainStateMap();
        this.initDeMainStateOPPrivsMap();
    }

    /**
     * 初始化视图Map
     * 
     * @memberof  SubStoryUIServiceBase
     */  
    public initViewMap(){
    }

    /**
     * 初始化主状态集合
     * 
     * @memberof  SubStoryUIServiceBase
     */  
    public initDeMainStateMap(){
        this.allDeMainStateMap.set('active__0__0','active__0__0');
        this.allDeMainStateMap.set('active__0__1','active__0__1');
        this.allDeMainStateMap.set('active__0__2','active__0__2');
        this.allDeMainStateMap.set('active__1__0','active__1__0');
        this.allDeMainStateMap.set('active__1__1','active__1__1');
        this.allDeMainStateMap.set('active__1__2','active__1__2');
        this.allDeMainStateMap.set('changed__0__0','changed__0__0');
        this.allDeMainStateMap.set('changed__0__1','changed__0__1');
        this.allDeMainStateMap.set('changed__0__2','changed__0__2');
        this.allDeMainStateMap.set('changed__1__0','changed__1__0');
        this.allDeMainStateMap.set('changed__1__1','changed__1__1');
        this.allDeMainStateMap.set('changed__1__2','changed__1__2');
        this.allDeMainStateMap.set('closed__0__0','closed__0__0');
        this.allDeMainStateMap.set('closed__0__1','closed__0__1');
        this.allDeMainStateMap.set('closed__0__2','closed__0__2');
        this.allDeMainStateMap.set('closed__1__0','closed__1__0');
        this.allDeMainStateMap.set('closed__1__1','closed__1__1');
        this.allDeMainStateMap.set('closed__1__2','closed__1__2');
        this.allDeMainStateMap.set('draft__0__0','draft__0__0');
        this.allDeMainStateMap.set('draft__0__1','draft__0__1');
        this.allDeMainStateMap.set('draft__0__2','draft__0__2');
        this.allDeMainStateMap.set('draft__1__0','draft__1__0');
        this.allDeMainStateMap.set('draft__1__1','draft__1__1');
        this.allDeMainStateMap.set('draft__1__2','draft__1__2');
    }

    /**
     * 初始化主状态操作标识
     * 
     * @memberof  SubStoryUIServiceBase
     */  
    public initDeMainStateOPPrivsMap(){
        this.allDeMainStateOPPrivsMap.set('active__0__0',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_REVIEW_BUT':0,'SRFUR__STORY_XQXF_BUT':0,'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_FAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('active__0__1',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_FAVOR_BUT':0,'SRFUR__STORY_ASS_BUT':0,'SRFUR__STORY_REVIEW_BUT':0,'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_CCASE_BUT':0,'SRFUR__STORY_DELETE_BUT':0,'SRFUR__STORY_CHANGED_BUT':0,'SRFUR__STORY_CLOSED_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('active__0__2',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_REVIEW_BUT':0,'SRFUR__STORY_FAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('active__1__0',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_NFAVOR_BUT':0,'SRFUR__STORY_REVIEW_BUT':0,'SRFUR__STORY_XQXF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('active__1__1',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_CHANGED_BUT':0,'SRFUR__STORY_NFAVOR_BUT':0,'SRFUR__STORY_CLOSED_BUT':0,'SRFUR__STORY_DELETE_BUT':0,'SRFUR__STORY_ASS_BUT':0,'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_REVIEW_BUT':0,'SRFUR__STORY_CCASE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('active__1__2',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_REVIEW_BUT':0,'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_NFAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('changed__0__0',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_XQXF_BUT':0,'SRFUR__STORY_FAVOR_BUT':0,'SRFUR__STORY_ACTIVE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('changed__0__1',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_CHANGED_BUT':0,'SRFUR__STORY_ASS_BUT':0,'SRFUR__STORY_CCASE_BUT':0,'SRFUR__STORY_FAVOR_BUT':0,'SRFUR__STORY_REVIEW_BUT':0,'SRFUR__STORY_CLOSED_BUT':0,'SRFUR__STORY_DELETE_BUT':0,'SRFUR__STORY_XQXF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('changed__0__2',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_FAVOR_BUT':0,'SRFUR__STORY_XQXF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('changed__1__0',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_XQXF_BUT':0,'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_NFAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('changed__1__1',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_ASS_BUT':0,'SRFUR__STORY_DELETE_BUT':0,'SRFUR__STORY_REVIEW_BUT':0,'SRFUR__STORY_CCASE_BUT':0,'SRFUR__STORY_CHANGED_BUT':0,'SRFUR__STORY_NFAVOR_BUT':0,'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_XQXF_BUT':0,'SRFUR__STORY_CLOSED_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('changed__1__2',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_XQXF_BUT':0,'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_NFAVOR_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('closed__0__0',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_ASS_BUT':0,'SRFUR__STORY_XQXF_BUT':0,'SRFUR__STORY_FAVOR_BUT':0,'SRFUR__STORY_REVIEW_BUT':0,'SRFUR__STORY_CHANGED_BUT':0,'SRFUR__STORY_CLOSED_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('closed__0__1',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_DELETE_BUT':0,'SRFUR__STORY_CHANGED_BUT':0,'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_FAVOR_BUT':0,'SRFUR__STORY_ASS_BUT':0,'SRFUR__STORY_REVIEW_BUT':0,'SRFUR__STORY_XQXF_BUT':0,'SRFUR__STORY_CLOSED_BUT':0,'SRFUR__STORY_CCASE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('closed__0__2',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_CLOSED_BUT':0,'SRFUR__STORY_REVIEW_BUT':0,'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_FAVOR_BUT':0,'SRFUR__STORY_ASS_BUT':0,'SRFUR__STORY_CHANGED_BUT':0,'SRFUR__STORY_XQXF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('closed__1__0',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_CHANGED_BUT':0,'SRFUR__STORY_CLOSED_BUT':0,'SRFUR__STORY_ASS_BUT':0,'SRFUR__STORY_NFAVOR_BUT':0,'SRFUR__STORY_REVIEW_BUT':0,'SRFUR__STORY_XQXF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('closed__1__1',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_REVIEW_BUT':0,'SRFUR__STORY_CHANGED_BUT':0,'SRFUR__STORY_XQXF_BUT':0,'SRFUR__STORY_ASS_BUT':0,'SRFUR__STORY_CLOSED_BUT':0,'SRFUR__STORY_NFAVOR_BUT':0,'SRFUR__STORY_DELETE_BUT':0,'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_CCASE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('closed__1__2',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_REVIEW_BUT':0,'SRFUR__STORY_CLOSED_BUT':0,'SRFUR__STORY_ASS_BUT':0,'SRFUR__STORY_NFAVOR_BUT':0,'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_CHANGED_BUT':0,'SRFUR__STORY_XQXF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('draft__0__0',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_FAVOR_BUT':0,'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_XQXF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('draft__0__1',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_FAVOR_BUT':0,'SRFUR__STORY_CCASE_BUT':0,'SRFUR__STORY_ASS_BUT':0,'SRFUR__STORY_CLOSED_BUT':0,'SRFUR__STORY_XQXF_BUT':0,'SRFUR__STORY_REVIEW_BUT':0,'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_DELETE_BUT':0,'SRFUR__STORY_CHANGED_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('draft__0__2',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_XQXF_BUT':0,'SRFUR__STORY_FAVOR_BUT':0,'SRFUR__STORY_ACTIVE_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('draft__1__0',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_NFAVOR_BUT':0,'SRFUR__STORY_XQXF_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('draft__1__1',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_DELETE_BUT':0,'SRFUR__STORY_CHANGED_BUT':0,'SRFUR__STORY_CCASE_BUT':0,'SRFUR__STORY_XQXF_BUT':0,'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_REVIEW_BUT':0,'SRFUR__STORY_NFAVOR_BUT':0,'SRFUR__STORY_CLOSED_BUT':0,'SRFUR__STORY_ASS_BUT':0,}));
        this.allDeMainStateOPPrivsMap.set('draft__1__2',Object.assign({'CREATE':1,'DELETE':1,'READ':1,'UPDATE':1},{'SRFUR__STORY_ACTIVE_BUT':0,'SRFUR__STORY_NFAVOR_BUT':0,'SRFUR__STORY_XQXF_BUT':0,}));
    }


    /**
     * 获取指定数据的重定向页面
     * 
     * @param srfkey 数据主键
     * @param isEnableWorkflow  重定向视图是否需要处理流程中的数据
     * @memberof  SubStoryUIServiceBase
     */
    public async getRDAppViewEX(srfkey:string,isEnableWorkflow:boolean,rdviewname:string){        
        this.isEnableWorkflow = isEnableWorkflow;
        // 进行数据查询
        let result:any = await this.dataService.Get({substory:srfkey});
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
     * @memberof  SubStoryUIServiceBase
     */
    public async getRDAppView(srfkey:string,isEnableWorkflow:boolean){
        return this.getRDAppViewEX(srfkey,isEnableWorkflow,'')
    }

    /**
	 * 获取实际的数据类型
     * 
     * @memberof  SubStoryUIServiceBase
	 */
	public getRealDEType(entity:any){

    }

    /**
     * 获取实体单数据实体视图预定义参数
     * 
     * @param curData 当前数据
     * @param bDataInWF 是否有数据在工作流中
     * @param bWFMode   是否工作流模式
     * @memberof  SubStoryUIServiceBase
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
     * @memberof  SubStoryUIServiceBase
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
    * @memberof  SubStoryUIServiceBase
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
    * @memberof  SubStoryUIServiceBase
    */ 
   public getAllOPPrivs(data:any){
       return this.authService.getOPPrivs(this.getDEMainStateOPPrivs(data));
   }

}