import { Environment } from '@/environments/environment';
import { UIActionTool,Util } from '@/utils';
import UIService from '../ui-service';
import { Subject } from 'rxjs';
import IBZ_PROJECTTEAMService from '@/service/ibz-projectteam/ibz-projectteam-service';

/**
 * 项目团队UI服务对象基类
 *
 * @export
 * @class IBZ_PROJECTTEAMUIServiceBase
 */
export default class IBZ_PROJECTTEAMUIServiceBase extends UIService {

    /**
     * 是否支持工作流
     * 
     * @memberof  IBZ_PROJECTTEAMUIServiceBase
     */
    public isEnableWorkflow:boolean = false;

    /**
     * 当前UI服务对应的数据服务对象
     * 
     * @memberof  IBZ_PROJECTTEAMUIServiceBase
     */
    public dataService:IBZ_PROJECTTEAMService = new IBZ_PROJECTTEAMService();

    /**
     * 所有关联视图
     * 
     * @memberof  IBZ_PROJECTTEAMUIServiceBase
     */ 
    public allViewMap: Map<string, Object> = new Map();

    /**
     * 状态值
     * 
     * @memberof  IBZ_PROJECTTEAMUIServiceBase
     */ 
    public stateValue: number = 0;

    /**
     * 状态属性
     * 
     * @memberof  IBZ_PROJECTTEAMUIServiceBase
     */ 
    public stateField: string = "";

    /**
     * 主状态属性集合
     * 
     * @memberof  IBZ_PROJECTTEAMUIServiceBase
     */  
    public mainStateFields:Array<any> = [];

    /**
     * 主状态集合Map
     * 
     * @memberof  IBZ_PROJECTTEAMUIServiceBase
     */  
    public allDeMainStateMap:Map<string,string> = new Map();

    /**
     * Creates an instance of  IBZ_PROJECTTEAMUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZ_PROJECTTEAMUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
        this.initViewMap();
        this.initDeMainStateMap();
    }

    /**
     * 初始化视图Map
     * 
     * @memberof  IBZ_PROJECTTEAMUIServiceBase
     */  
    public initViewMap(){
        this.allViewMap.set('MDATAVIEW:',{viewname:'maingridview',srfappde:'ibz_projectteams'});
    }

    /**
     * 初始化主状态集合
     * 
     * @memberof  IBZ_PROJECTTEAMUIServiceBase
     */  
    public initDeMainStateMap(){
    }


    /**
     * 获取指定数据的重定向页面
     * 
     * @param srfkey 数据主键
     * @param isEnableWorkflow  重定向视图是否需要处理流程中的数据
     * @memberof  IBZ_PROJECTTEAMUIServiceBase
     */
    public async getRDAppView(srfkey:string,isEnableWorkflow:boolean){
        this.isEnableWorkflow = isEnableWorkflow;
        // 进行数据查询
        let result:any = await this.dataService.Get({ibz_projectteam:srfkey});
        const curData:any = result.data;
        //判断当前数据模式,默认为true，todo
        const iRealDEModel:boolean = true;

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
	 * 获取实际的数据类型
     * 
     * @memberof  IBZ_PROJECTTEAMUIServiceBase
	 */
	public getRealDEType(entity:any){

    }

    /**
     * 获取实体单数据实体视图预定义参数
     * 
     * @param curData 当前数据
     * @param bDataInWF 是否有数据在工作流中
     * @param bWFMode   是否工作流模式
     * @memberof  IBZ_PROJECTTEAMUIServiceBase
     */
    public async getDESDDEViewPDTParam(curData:any, bDataInWF:boolean, bWFMode:boolean){
        let strPDTParam:string = '';
		if (bDataInWF) {
			// 判断数据是否在流程中
        }
        //多表单，todo
        const isEnableMultiForm:boolean = false;
        const multiFormDEField:string|null =null;

        if (isEnableMultiForm && multiFormDEField) {
			const objFormValue:string = curData[multiFormDEField];
			if(!Environment.isAppMode){
				return 'MOBEDITVIEW'+objFormValue;
			}
			return 'EDITVIEW'+objFormValue;
        }
		if(!Environment.isAppMode){
            if(this.getDEMainStateTag(curData)){
                return `MOBEDITVIEW:MSTAG:${ await this.getDEMainStateTag(curData)}`;
            }
			return 'MOBEDITVIEW:';
        }
        if(this.getDEMainStateTag(curData)){
            return `EDITVIEW:MSTAG:${ await this.getDEMainStateTag(curData)}`;
        }
		return 'EDITVIEW:';
    }

    /**
     * 获取数据对象的主状态标识
     * 
     * @param curData 当前数据
     * @memberof  IBZ_PROJECTTEAMUIServiceBase
     */  
    public async getDEMainStateTag(curData:any){
        if(this.mainStateFields.length === 0) return null;

        this.mainStateFields.forEach((singleMainField:any) =>{
            if(!(singleMainField in curData)){
                console.error(`当前数据对象不包含属性singleMainField，可能会发生错误`);
            }
        })

        let strTag:String = "";
        for (let i = 0; i <= 1; i++) {
            let strTag:string = (curData[this.mainStateFields[0]])?(i == 0) ? curData[this.mainStateFields[0]] : "":"";
            if (this.mainStateFields.length >= 2) {
                for (let j = 0; j <= 1; j++) {
                    let strTag2:string = (curData[this.mainStateFields[1]])?`${strTag}__${(j == 0) ? curData[this.mainStateFields[1]] : ""}`:strTag;
                    if (this.mainStateFields.length >= 3) {
                        for (let k = 0; k <= 1; k++) {
                            let strTag3:string = (curData[this.mainStateFields[2]])?`${strTag2}__${(k == 0) ? curData[this.mainStateFields[2]] : ""}`:strTag2;
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

}