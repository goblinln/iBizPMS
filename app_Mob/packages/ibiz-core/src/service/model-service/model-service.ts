import { GlobalHelp, PSModelServiceImpl } from '@ibiz/dynamic-model-api';

/**
 * 模型服务
 *
 * @export
 * @class AppModelService
 * @extends {PSModelServiceImpl}
 */
export class AppModelService extends PSModelServiceImpl { }

/**
 * 获取模型服务
 *
 */
export const GetModelService: Function = (param: any) => {
    if(param.instTag && param.instTag2){
        return GlobalHelp.getModelServiceByTag(param.instTag,param.instTag2);
    }else{
        return GlobalHelp.getModelService(param?.srfdynainstid);
    }
}