import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 产品生命周期服务对象基类
 *
 * @export
 * @class ProductLifeServiceBase
 * @extends {EntityServie}
 */
export default class ProductLifeServiceBase extends EntityService {

    /**
     * Creates an instance of  ProductLifeServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductLifeServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof ProductLifeServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='productlife';
        this.APPDEKEY = 'productlifeid';
        this.APPDENAME = 'productlives';
        this.APPDETEXT = 'productlifename';
        this.APPNAME = 'web';
        this.SYSTEMNAME = 'pms';
    }

// 实体接口

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductLifeServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductLifeServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productlives/searchdefault`,tempData,isloading);
    }

    /**
     * FetchGetRoadmap接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductLifeServiceBase
     */
    public async FetchGetRoadmap(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/productlives/fetchgetroadmap`,tempData,isloading);
        return res;
    }

    /**
     * searchGetRoadmap接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductLifeServiceBase
     */
    public async searchGetRoadmap(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productlives/searchgetroadmap`,tempData,isloading);
    }

    /**
     * FetchGetRoadmapS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductLifeServiceBase
     */
    public async FetchGetRoadmapS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/productlives/fetchgetroadmaps`,tempData,isloading);
        return res;
    }

    /**
     * searchGetRoadmapS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductLifeServiceBase
     */
    public async searchGetRoadmapS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productlives/searchgetroadmaps`,tempData,isloading);
    }

    /**
     * FetchRoadMapYear接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductLifeServiceBase
     */
    public async FetchRoadMapYear(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/productlives/fetchroadmapyear`,tempData,isloading);
        return res;
    }

    /**
     * searchRoadMapYear接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductLifeServiceBase
     */
    public async searchRoadMapYear(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productlives/searchroadmapyear`,tempData,isloading);
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductLifeServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/productlives/${context.productlife}/select`,isloading);
            
            return res;
    }
}