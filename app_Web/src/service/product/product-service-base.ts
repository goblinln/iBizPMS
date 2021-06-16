import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';
import CancelProductTopLogic from '@/service/product/cancel-product-top-logic';
import ProductTopLogic from '@/service/product/product-top-logic';



/**
 * 产品服务对象基类
 *
 * @export
 * @class ProductServiceBase
 * @extends {EntityServie}
 */
export default class ProductServiceBase extends EntityService {

    /**
     * Creates an instance of  ProductServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof ProductServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='product';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'products';
        this.APPDETEXT = 'name';
        this.APPNAME = 'web';
        this.SYSTEMNAME = 'pms';
    }

// 实体接口

    /**
     * CancelProductTop接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async CancelProductTop(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/products/${context.product}/cancelproducttop`,data,isloading);
            return res;
    }

    /**
     * CheckKey接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/products/${context.product}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Close接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async Close(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/products/${context.product}/close`,data,isloading);
            return res;
    }

    /**
     * CloseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async CloseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/products/closebatch`,tempData,isloading);
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        let casesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_cases'),'undefined')){
            casesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_cases') as any);
            if(casesData && casesData.length && casesData.length > 0 && Environment.isStudioSystem === false){
                casesData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.cases = casesData;
        Object.assign(data,masterData);
        if(!data.srffrontuf || data.srffrontuf !== "1"){
            data[this.APPDEKEY] = null;
        }
        if(data.srffrontuf){
            delete data.srffrontuf;
        }
        let tempContext:any = JSON.parse(JSON.stringify(context));
        let res:any = await Http.getInstance().post(`/products`,data,isloading);
        this.tempStorage.setItem(tempContext.srfsessionkey+'_branches',JSON.stringify(res.data.branches?res.data.branches:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_bugs',JSON.stringify(res.data.bugs?res.data.bugs:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_builds',JSON.stringify(res.data.builds?res.data.builds:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_cases',JSON.stringify(res.data.cases?res.data.cases:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_productteams',JSON.stringify(res.data.productteams?res.data.productteams:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_productmodules',JSON.stringify(res.data.productmodules?res.data.productmodules:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_productplans',JSON.stringify(res.data.productplans?res.data.productplans:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_projectproducts',JSON.stringify(res.data.projectproducts?res.data.projectproducts:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_releases',JSON.stringify(res.data.releases?res.data.releases:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_stories',JSON.stringify(res.data.stories?res.data.stories:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_testmodules',JSON.stringify(res.data.testmodules?res.data.testmodules:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_testreports',JSON.stringify(res.data.testreports?res.data.testreports:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_testsuites',JSON.stringify(res.data.testsuites?res.data.testsuites:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_testtasks',JSON.stringify(res.data.testtasks?res.data.testtasks:[]));
        
        return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/products/${context.product}`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_cases',JSON.stringify(res.data.cases?res.data.cases:[]));

            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.product) delete tempData.product;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/products/getdraft`,tempData,isloading);
        res.data.product = data.product;
                    this.tempStorage.setItem(context.srfsessionkey+'_cases',JSON.stringify(res.data.cases?res.data.cases:[]));

        return res;
    }

    /**
     * MobProductCounter接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async MobProductCounter(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().put(`/products/${context.product}/mobproductcounter`,data,isloading);
            return res;
    }

    /**
     * MobProductTestCounter接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async MobProductTestCounter(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/products/${context.product}/mobproducttestcounter`,data,isloading);
            return res;
    }

    /**
     * ProductTop接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async ProductTop(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/products/${context.product}/producttop`,data,isloading);
            return res;
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().delete(`/products/${context.product}`,isloading);
            return res;
    }

    /**
     * ReturnEdit接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async ReturnEdit(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        let casesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_cases'),'undefined')){
            casesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_cases') as any);
            if(casesData && casesData.length && casesData.length > 0 && Environment.isStudioSystem === false){
                casesData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.cases = casesData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/products/${context.product}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_cases',JSON.stringify(res.data.cases?res.data.cases:[]));

            return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        let casesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_cases'),'undefined')){
            casesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_cases') as any);
            if(casesData && casesData.length && casesData.length > 0 && Environment.isStudioSystem === false){
                casesData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.cases = casesData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/products/${context.product}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_cases',JSON.stringify(res.data.cases?res.data.cases:[]));

            return res;
    }

    /**
     * WizardCreate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async WizardCreate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchAllList接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async FetchAllList(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/products/fetchalllist`,tempData,isloading);
        return res;
    }

    /**
     * searchAllList接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async searchAllList(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/products/searchalllist`,tempData,isloading);
    }

    /**
     * FetchAllProduct接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async FetchAllProduct(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/products/fetchallproduct`,tempData,isloading);
        return res;
    }

    /**
     * searchAllProduct接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async searchAllProduct(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/products/searchallproduct`,tempData,isloading);
    }

    /**
     * FetchCheckNameOrCode接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async FetchCheckNameOrCode(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/products/fetchchecknameorcode`,tempData,isloading);
        return res;
    }

    /**
     * searchCheckNameOrCode接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async searchCheckNameOrCode(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/products/searchchecknameorcode`,tempData,isloading);
    }

    /**
     * FetchCurProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async FetchCurProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/products/fetchcurproject`,tempData,isloading);
        return res;
    }

    /**
     * searchCurProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async searchCurProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/products/searchcurproject`,tempData,isloading);
    }

    /**
     * FetchCurUer接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async FetchCurUer(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/products/fetchcuruer`,tempData,isloading);
        return res;
    }

    /**
     * searchCurUer接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async searchCurUer(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/products/searchcuruer`,tempData,isloading);
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/products/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/products/searchdefault`,tempData,isloading);
    }

    /**
     * FetchESBulk接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async FetchESBulk(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/products/fetchesbulk`,tempData,isloading);
        return res;
    }

    /**
     * searchESBulk接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async searchESBulk(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/products/searchesbulk`,tempData,isloading);
    }

    /**
     * FetchProductPM接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async FetchProductPM(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/products/fetchproductpm`,tempData,isloading);
        return res;
    }

    /**
     * searchProductPM接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async searchProductPM(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/products/searchproductpm`,tempData,isloading);
    }

    /**
     * FetchProductTeam接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async FetchProductTeam(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/products/fetchproductteam`,tempData,isloading);
        return res;
    }

    /**
     * searchProductTeam接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async searchProductTeam(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/products/searchproductteam`,tempData,isloading);
    }

    /**
     * FetchStoryCurProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async FetchStoryCurProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/products/fetchstorycurproject`,tempData,isloading);
        return res;
    }

    /**
     * searchStoryCurProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async searchStoryCurProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/products/searchstorycurproject`,tempData,isloading);
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/products/${context.product}/select`,isloading);
            
            return res;
    }
}