import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';
import GetCurUserConcatLogic from '@/service/bug/get-cur-user-concat-logic';



/**
 * Bug服务对象基类
 *
 * @export
 * @class BugServiceBase
 * @extends {EntityServie}
 */
export default class BugServiceBase extends EntityService {

    /**
     * Creates an instance of  BugServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  BugServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof BugServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='bug';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'bugs';
        this.APPDETEXT = 'title';
        this.APPNAME = 'web';
        this.SYSTEMNAME = 'pms';
    }

// 实体接口

    /**
     * Activate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async Activate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/activate`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/activate`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/activate`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/activate`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/activate`,data,isloading);
            return res;
    }

    /**
     * ActivateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ActivateBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/activatebatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/activatebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/activatebatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/activatebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/activatebatch`,tempData,isloading);
    }

    /**
     * AssignTo接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async AssignTo(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/assignto`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/assignto`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/assignto`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/assignto`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/assignto`,data,isloading);
            return res;
    }

    /**
     * AssignToBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async AssignToBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/assigntobatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/assigntobatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/assigntobatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/assigntobatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/assigntobatch`,tempData,isloading);
    }

    /**
     * BatchUnlinkBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async BatchUnlinkBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/batchunlinkbug`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/batchunlinkbug`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/batchunlinkbug`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/batchunlinkbug`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/batchunlinkbug`,data,isloading);
            return res;
    }

    /**
     * BatchUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async BatchUnlinkBugBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/batchunlinkbugbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/batchunlinkbugbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/batchunlinkbugbatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/batchunlinkbugbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/batchunlinkbugbatch`,tempData,isloading);
    }

    /**
     * BugFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async BugFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/bugfavorites`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/bugfavorites`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/bugfavorites`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/bugfavorites`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/bugfavorites`,data,isloading);
            return res;
    }

    /**
     * BugNFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async BugNFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/bugnfavorites`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/bugnfavorites`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/bugnfavorites`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/bugnfavorites`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/bugnfavorites`,data,isloading);
            return res;
    }

    /**
     * BuildBatchUnlinkBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async BuildBatchUnlinkBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/buildbatchunlinkbug`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/buildbatchunlinkbug`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/buildbatchunlinkbug`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/buildbatchunlinkbug`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/buildbatchunlinkbug`,data,isloading);
            return res;
    }

    /**
     * BuildBatchUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async BuildBatchUnlinkBugBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/buildbatchunlinkbugbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/buildbatchunlinkbugbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/buildbatchunlinkbugbatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/buildbatchunlinkbugbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/buildbatchunlinkbugbatch`,tempData,isloading);
    }

    /**
     * BuildLinkBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async BuildLinkBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/buildlinkbug`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/buildlinkbug`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/buildlinkbug`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/buildlinkbug`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/buildlinkbug`,data,isloading);
            return res;
    }

    /**
     * BuildLinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async BuildLinkBugBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/buildlinkbugbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/buildlinkbugbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/buildlinkbugbatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/buildlinkbugbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/buildlinkbugbatch`,tempData,isloading);
    }

    /**
     * BuildUnlinkBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async BuildUnlinkBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/buildunlinkbug`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/buildunlinkbug`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/buildunlinkbug`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/buildunlinkbug`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/buildunlinkbug`,data,isloading);
            return res;
    }

    /**
     * BuildUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async BuildUnlinkBugBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/buildunlinkbugbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/buildunlinkbugbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/buildunlinkbugbatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/buildunlinkbugbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/buildunlinkbugbatch`,tempData,isloading);
    }

    /**
     * CheckKey接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/checkkey`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Close接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async Close(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/close`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/close`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/close`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/close`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/close`,data,isloading);
            return res;
    }

    /**
     * CloseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async CloseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/closebatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/closebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/closebatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/closebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/closebatch`,tempData,isloading);
    }

    /**
     * Confirm接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async Confirm(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/confirm`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/confirm`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/confirm`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/confirm`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/confirm`,data,isloading);
            return res;
    }

    /**
     * ConfirmBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ConfirmBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/confirmbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/confirmbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/confirmbatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/confirmbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/confirmbatch`,tempData,isloading);
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs`,data,isloading);
            
            return res;
        }
        if(context.project && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs`,data,isloading);
            
            return res;
        }
        if(context.story && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs`,data,isloading);
            
            return res;
        }
        if(context.product && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
        if(!data.srffrontuf || data.srffrontuf !== "1"){
            data[this.APPDEKEY] = null;
        }
        if(data.srffrontuf){
            delete data.srffrontuf;
        }
        let tempContext:any = JSON.parse(JSON.stringify(context));
        let res:any = await Http.getInstance().post(`/bugs`,data,isloading);
        
        return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}`,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/${context.bug}`,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/${context.bug}`,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/${context.bug}`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/bugs/${context.bug}`,isloading);
            
            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.bug) delete tempData.bug;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/getdraft`,tempData,isloading);
            res.data.bug = data.bug;
            
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.bug) delete tempData.bug;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/getdraft`,tempData,isloading);
            res.data.bug = data.bug;
            
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.bug) delete tempData.bug;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/getdraft`,tempData,isloading);
            res.data.bug = data.bug;
            
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.bug) delete tempData.bug;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/getdraft`,tempData,isloading);
            res.data.bug = data.bug;
            
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.bug) delete tempData.bug;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/bugs/getdraft`,tempData,isloading);
        res.data.bug = data.bug;
        
        return res;
    }

    /**
     * GetUserConcat接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async GetUserConcat(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let appLogic:GetCurUserConcatLogic = new GetCurUserConcatLogic({context:JSON.parse(JSON.stringify(context)),data:JSON.parse(JSON.stringify(data))});
        const res = await appLogic.onExecute(context,data,isloading?true:false);
        return {status:200,data:res};
    }

    /**
     * LinkBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async LinkBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/linkbug`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/linkbug`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/linkbug`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/linkbug`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/linkbug`,data,isloading);
            return res;
    }

    /**
     * LinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async LinkBugBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/linkbugbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/linkbugbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/linkbugbatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/linkbugbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/linkbugbatch`,tempData,isloading);
    }

    /**
     * ReleaaseBatchUnlinkBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ReleaaseBatchUnlinkBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/releaasebatchunlinkbug`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/releaasebatchunlinkbug`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/releaasebatchunlinkbug`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/releaasebatchunlinkbug`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/releaasebatchunlinkbug`,data,isloading);
            return res;
    }

    /**
     * ReleaaseBatchUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ReleaaseBatchUnlinkBugBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/releaasebatchunlinkbugbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/releaasebatchunlinkbugbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/releaasebatchunlinkbugbatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/releaasebatchunlinkbugbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/releaasebatchunlinkbugbatch`,tempData,isloading);
    }

    /**
     * ReleaseLinkBugbyBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ReleaseLinkBugbyBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/releaselinkbugbybug`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/releaselinkbugbybug`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/releaselinkbugbybug`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/releaselinkbugbybug`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/releaselinkbugbybug`,data,isloading);
            return res;
    }

    /**
     * ReleaseLinkBugbyBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ReleaseLinkBugbyBugBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/releaselinkbugbybugbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/releaselinkbugbybugbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/releaselinkbugbybugbatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/releaselinkbugbybugbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/releaselinkbugbybugbatch`,tempData,isloading);
    }

    /**
     * ReleaseLinkBugbyLeftBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ReleaseLinkBugbyLeftBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/releaselinkbugbyleftbug`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/releaselinkbugbyleftbug`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/releaselinkbugbyleftbug`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/releaselinkbugbyleftbug`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/releaselinkbugbyleftbug`,data,isloading);
            return res;
    }

    /**
     * ReleaseLinkBugbyLeftBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ReleaseLinkBugbyLeftBugBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/releaselinkbugbyleftbugbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/releaselinkbugbyleftbugbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/releaselinkbugbyleftbugbatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/releaselinkbugbyleftbugbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/releaselinkbugbyleftbugbatch`,tempData,isloading);
    }

    /**
     * ReleaseUnLinkBugbyLeftBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ReleaseUnLinkBugbyLeftBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/releaseunlinkbugbyleftbug`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/releaseunlinkbugbyleftbug`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/releaseunlinkbugbyleftbug`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/releaseunlinkbugbyleftbug`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/releaseunlinkbugbyleftbug`,data,isloading);
            return res;
    }

    /**
     * ReleaseUnLinkBugbyLeftBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ReleaseUnLinkBugbyLeftBugBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/releaseunlinkbugbyleftbugbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/releaseunlinkbugbyleftbugbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/releaseunlinkbugbyleftbugbatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/releaseunlinkbugbyleftbugbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/releaseunlinkbugbyleftbugbatch`,tempData,isloading);
    }

    /**
     * ReleaseUnlinkBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ReleaseUnlinkBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/releaseunlinkbug`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/releaseunlinkbug`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/releaseunlinkbug`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/releaseunlinkbug`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/releaseunlinkbug`,data,isloading);
            return res;
    }

    /**
     * ReleaseUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ReleaseUnlinkBugBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/releaseunlinkbugbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/releaseunlinkbugbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/releaseunlinkbugbatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/releaseunlinkbugbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/releaseunlinkbugbatch`,tempData,isloading);
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let res:any = await Http.getInstance().delete(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}`,isloading);
            return res;
        }
        if(context.project && context.bug){
            let res:any = await Http.getInstance().delete(`/projects/${context.project}/bugs/${context.bug}`,isloading);
            return res;
        }
        if(context.story && context.bug){
            let res:any = await Http.getInstance().delete(`/stories/${context.story}/bugs/${context.bug}`,isloading);
            return res;
        }
        if(context.product && context.bug){
            let res:any = await Http.getInstance().delete(`/products/${context.product}/bugs/${context.bug}`,isloading);
            return res;
        }
            let res:any = await Http.getInstance().delete(`/bugs/${context.bug}`,isloading);
            return res;
    }

    /**
     * Resolve接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async Resolve(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/resolve`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/resolve`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/resolve`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/resolve`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/resolve`,data,isloading);
            return res;
    }

    /**
     * ResolveBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ResolveBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/resolvebatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/resolvebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/resolvebatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/resolvebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/resolvebatch`,tempData,isloading);
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/save`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/save`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/save`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/save`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/bugs/${context.bug}/save`,data,isloading);
            
            return res;
    }

    /**
     * SendMessage接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async SendMessage(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/sendmessage`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/sendmessage`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/sendmessage`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/sendmessage`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/sendmessage`,data,isloading);
            return res;
    }

    /**
     * SendMessageBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async SendMessageBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/sendmessagebatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/sendmessagebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/sendmessagebatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/sendmessagebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/sendmessagebatch`,tempData,isloading);
    }

    /**
     * SendMsgPreProcess接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async SendMsgPreProcess(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/sendmsgpreprocess`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/sendmsgpreprocess`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/sendmsgpreprocess`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/sendmsgpreprocess`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/sendmsgpreprocess`,data,isloading);
            return res;
    }

    /**
     * SendMsgPreProcessBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async SendMsgPreProcessBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/sendmsgpreprocessbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/sendmsgpreprocessbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/sendmsgpreprocessbatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/sendmsgpreprocessbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/sendmsgpreprocessbatch`,tempData,isloading);
    }

    /**
     * ToStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ToStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/tostory`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/tostory`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/tostory`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/tostory`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/tostory`,data,isloading);
            return res;
    }

    /**
     * ToStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ToStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/tostorybatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/tostorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/tostorybatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/tostorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/tostorybatch`,tempData,isloading);
    }

    /**
     * UnlinkBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async UnlinkBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/unlinkbug`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/${context.bug}/unlinkbug`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/${context.bug}/unlinkbug`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/${context.bug}/unlinkbug`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/bugs/${context.bug}/unlinkbug`,data,isloading);
            return res;
    }

    /**
     * UnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async UnlinkBugBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/unlinkbugbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/unlinkbugbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/unlinkbugbatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/unlinkbugbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/unlinkbugbatch`,tempData,isloading);
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/bugs/${context.bug}`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/bugs/${context.bug}`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/bugs/${context.bug}`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/bugs/${context.bug}`,data,isloading);
            
            return res;
    }

    /**
     * UpdateStoryVersion接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async UpdateStoryVersion(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/updatestoryversion`,data,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/bugs/${context.bug}/updatestoryversion`,data,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/bugs/${context.bug}/updatestoryversion`,data,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/bugs/${context.bug}/updatestoryversion`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().put(`/bugs/${context.bug}/updatestoryversion`,data,isloading);
            return res;
    }

    /**
     * FetchAssignedToMyBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchAssignedToMyBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchassignedtomybug`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchassignedtomybug`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchassignedtomybug`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchassignedtomybug`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchassignedtomybug`,tempData,isloading);
        return res;
    }

    /**
     * searchAssignedToMyBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchAssignedToMyBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchassignedtomybug`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchassignedtomybug`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchassignedtomybug`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchassignedtomybug`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchassignedtomybug`,tempData,isloading);
    }

    /**
     * FetchAssignedToMyBugPc接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchAssignedToMyBugPc(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchassignedtomybugpc`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchassignedtomybugpc`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchassignedtomybugpc`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchassignedtomybugpc`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchassignedtomybugpc`,tempData,isloading);
        return res;
    }

    /**
     * searchAssignedToMyBugPc接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchAssignedToMyBugPc(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchassignedtomybugpc`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchassignedtomybugpc`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchassignedtomybugpc`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchassignedtomybugpc`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchassignedtomybugpc`,tempData,isloading);
    }

    /**
     * FetchBugsByBuild接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchBugsByBuild(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/fetchbugsbybuild`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/fetchbugsbybuild`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/fetchbugsbybuild`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/fetchbugsbybuild`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/bugs/fetchbugsbybuild`,tempData,isloading);
        return res;
    }

    /**
     * searchBugsByBuild接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchBugsByBuild(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchbugsbybuild`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchbugsbybuild`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchbugsbybuild`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchbugsbybuild`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchbugsbybuild`,tempData,isloading);
    }

    /**
     * FetchBuildBugs接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchBuildBugs(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/fetchbuildbugs`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/fetchbuildbugs`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/fetchbuildbugs`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/fetchbuildbugs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/bugs/fetchbuildbugs`,tempData,isloading);
        return res;
    }

    /**
     * searchBuildBugs接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchBuildBugs(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchbuildbugs`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchbuildbugs`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchbuildbugs`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchbuildbugs`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchbuildbugs`,tempData,isloading);
    }

    /**
     * FetchBuildLinkResolvedBugs接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchBuildLinkResolvedBugs(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/fetchbuildlinkresolvedbugs`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/fetchbuildlinkresolvedbugs`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/fetchbuildlinkresolvedbugs`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/fetchbuildlinkresolvedbugs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/bugs/fetchbuildlinkresolvedbugs`,tempData,isloading);
        return res;
    }

    /**
     * searchBuildLinkResolvedBugs接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchBuildLinkResolvedBugs(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchbuildlinkresolvedbugs`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchbuildlinkresolvedbugs`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchbuildlinkresolvedbugs`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchbuildlinkresolvedbugs`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchbuildlinkresolvedbugs`,tempData,isloading);
    }

    /**
     * FetchBuildOpenBugs接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchBuildOpenBugs(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/fetchbuildopenbugs`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/fetchbuildopenbugs`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/fetchbuildopenbugs`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/fetchbuildopenbugs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/bugs/fetchbuildopenbugs`,tempData,isloading);
        return res;
    }

    /**
     * searchBuildOpenBugs接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchBuildOpenBugs(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchbuildopenbugs`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchbuildopenbugs`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchbuildopenbugs`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchbuildopenbugs`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchbuildopenbugs`,tempData,isloading);
    }

    /**
     * FetchBuildProduceBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchBuildProduceBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchbuildproducebug`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchbuildproducebug`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchbuildproducebug`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchbuildproducebug`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchbuildproducebug`,tempData,isloading);
        return res;
    }

    /**
     * searchBuildProduceBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchBuildProduceBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchbuildproducebug`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchbuildproducebug`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchbuildproducebug`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchbuildproducebug`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchbuildproducebug`,tempData,isloading);
    }

    /**
     * FetchBuildProduceBugModule接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchBuildProduceBugModule(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchbuildproducebugmodule`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchbuildproducebugmodule`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchbuildproducebugmodule`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchbuildproducebugmodule`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchbuildproducebugmodule`,tempData,isloading);
        return res;
    }

    /**
     * searchBuildProduceBugModule接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchBuildProduceBugModule(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchbuildproducebugmodule`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchbuildproducebugmodule`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchbuildproducebugmodule`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchbuildproducebugmodule`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchbuildproducebugmodule`,tempData,isloading);
    }

    /**
     * FetchBuildProduceBugModule_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchBuildProduceBugModule_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchbuildproducebugmodule_project`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchbuildproducebugmodule_project`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchbuildproducebugmodule_project`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchbuildproducebugmodule_project`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchbuildproducebugmodule_project`,tempData,isloading);
        return res;
    }

    /**
     * searchBuildProduceBugModule_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchBuildProduceBugModule_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchbuildproducebugmodule_project`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchbuildproducebugmodule_project`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchbuildproducebugmodule_project`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchbuildproducebugmodule_project`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchbuildproducebugmodule_project`,tempData,isloading);
    }

    /**
     * FetchBuildProduceBugOpenedBy接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchBuildProduceBugOpenedBy(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchbuildproducebugopenedby`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchbuildproducebugopenedby`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchbuildproducebugopenedby`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchbuildproducebugopenedby`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchbuildproducebugopenedby`,tempData,isloading);
        return res;
    }

    /**
     * searchBuildProduceBugOpenedBy接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchBuildProduceBugOpenedBy(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchbuildproducebugopenedby`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchbuildproducebugopenedby`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchbuildproducebugopenedby`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchbuildproducebugopenedby`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchbuildproducebugopenedby`,tempData,isloading);
    }

    /**
     * FetchBuildProduceBugOpenedBy_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchBuildProduceBugOpenedBy_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchbuildproducebugopenedby_project`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchbuildproducebugopenedby_project`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchbuildproducebugopenedby_project`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchbuildproducebugopenedby_project`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchbuildproducebugopenedby_project`,tempData,isloading);
        return res;
    }

    /**
     * searchBuildProduceBugOpenedBy_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchBuildProduceBugOpenedBy_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchbuildproducebugopenedby_project`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchbuildproducebugopenedby_project`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchbuildproducebugopenedby_project`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchbuildproducebugopenedby_project`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchbuildproducebugopenedby_project`,tempData,isloading);
    }

    /**
     * FetchBuildProduceBugRES接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchBuildProduceBugRES(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchbuildproducebugres`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchbuildproducebugres`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchbuildproducebugres`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchbuildproducebugres`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchbuildproducebugres`,tempData,isloading);
        return res;
    }

    /**
     * searchBuildProduceBugRES接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchBuildProduceBugRES(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchbuildproducebugres`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchbuildproducebugres`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchbuildproducebugres`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchbuildproducebugres`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchbuildproducebugres`,tempData,isloading);
    }

    /**
     * FetchBuildProduceBugRESOLVEDBY接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchBuildProduceBugRESOLVEDBY(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchbuildproducebugresolvedby`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchbuildproducebugresolvedby`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchbuildproducebugresolvedby`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchbuildproducebugresolvedby`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchbuildproducebugresolvedby`,tempData,isloading);
        return res;
    }

    /**
     * searchBuildProduceBugRESOLVEDBY接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchBuildProduceBugRESOLVEDBY(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchbuildproducebugresolvedby`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchbuildproducebugresolvedby`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchbuildproducebugresolvedby`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchbuildproducebugresolvedby`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchbuildproducebugresolvedby`,tempData,isloading);
    }

    /**
     * FetchBuildProduceBugRESOLVEDBY_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchBuildProduceBugRESOLVEDBY_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchbuildproducebugresolvedby_project`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchbuildproducebugresolvedby_project`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchbuildproducebugresolvedby_project`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchbuildproducebugresolvedby_project`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchbuildproducebugresolvedby_project`,tempData,isloading);
        return res;
    }

    /**
     * searchBuildProduceBugRESOLVEDBY_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchBuildProduceBugRESOLVEDBY_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchbuildproducebugresolvedby_project`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchbuildproducebugresolvedby_project`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchbuildproducebugresolvedby_project`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchbuildproducebugresolvedby_project`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchbuildproducebugresolvedby_project`,tempData,isloading);
    }

    /**
     * FetchBuildProduceBugResolution_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchBuildProduceBugResolution_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchbuildproducebugresolution_project`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchbuildproducebugresolution_project`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchbuildproducebugresolution_project`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchbuildproducebugresolution_project`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchbuildproducebugresolution_project`,tempData,isloading);
        return res;
    }

    /**
     * searchBuildProduceBugResolution_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchBuildProduceBugResolution_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchbuildproducebugresolution_project`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchbuildproducebugresolution_project`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchbuildproducebugresolution_project`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchbuildproducebugresolution_project`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchbuildproducebugresolution_project`,tempData,isloading);
    }

    /**
     * FetchBuildProduceBugSeverity_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchBuildProduceBugSeverity_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchbuildproducebugseverity_project`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchbuildproducebugseverity_project`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchbuildproducebugseverity_project`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchbuildproducebugseverity_project`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchbuildproducebugseverity_project`,tempData,isloading);
        return res;
    }

    /**
     * searchBuildProduceBugSeverity_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchBuildProduceBugSeverity_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchbuildproducebugseverity_project`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchbuildproducebugseverity_project`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchbuildproducebugseverity_project`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchbuildproducebugseverity_project`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchbuildproducebugseverity_project`,tempData,isloading);
    }

    /**
     * FetchBuildProduceBugStatus_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchBuildProduceBugStatus_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchbuildproducebugstatus_project`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchbuildproducebugstatus_project`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchbuildproducebugstatus_project`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchbuildproducebugstatus_project`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchbuildproducebugstatus_project`,tempData,isloading);
        return res;
    }

    /**
     * searchBuildProduceBugStatus_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchBuildProduceBugStatus_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchbuildproducebugstatus_project`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchbuildproducebugstatus_project`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchbuildproducebugstatus_project`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchbuildproducebugstatus_project`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchbuildproducebugstatus_project`,tempData,isloading);
    }

    /**
     * FetchBuildProduceBugType_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchBuildProduceBugType_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchbuildproducebugtype_project`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchbuildproducebugtype_project`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchbuildproducebugtype_project`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchbuildproducebugtype_project`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchbuildproducebugtype_project`,tempData,isloading);
        return res;
    }

    /**
     * searchBuildProduceBugType_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchBuildProduceBugType_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchbuildproducebugtype_project`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchbuildproducebugtype_project`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchbuildproducebugtype_project`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchbuildproducebugtype_project`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchbuildproducebugtype_project`,tempData,isloading);
    }

    /**
     * FetchCurUserResolve接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchCurUserResolve(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchcuruserresolve`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchcuruserresolve`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchcuruserresolve`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchcuruserresolve`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchcuruserresolve`,tempData,isloading);
        return res;
    }

    /**
     * searchCurUserResolve接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchCurUserResolve(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchcuruserresolve`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchcuruserresolve`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchcuruserresolve`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchcuruserresolve`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchcuruserresolve`,tempData,isloading);
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/fetchdefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/bugs/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchdefault`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchdefault`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchdefault`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchdefault`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchdefault`,tempData,isloading);
    }

    /**
     * FetchESBulk接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchESBulk(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchesbulk`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchesbulk`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchesbulk`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchesbulk`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchesbulk`,tempData,isloading);
        return res;
    }

    /**
     * searchESBulk接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchESBulk(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchesbulk`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchesbulk`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchesbulk`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchesbulk`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchesbulk`,tempData,isloading);
    }

    /**
     * FetchMyAgentBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchMyAgentBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchmyagentbug`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchmyagentbug`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchmyagentbug`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchmyagentbug`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchmyagentbug`,tempData,isloading);
        return res;
    }

    /**
     * searchMyAgentBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchMyAgentBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchmyagentbug`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchmyagentbug`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchmyagentbug`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchmyagentbug`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchmyagentbug`,tempData,isloading);
    }

    /**
     * FetchMyCurOpenedBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchMyCurOpenedBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchmycuropenedbug`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchmycuropenedbug`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchmycuropenedbug`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchmycuropenedbug`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchmycuropenedbug`,tempData,isloading);
        return res;
    }

    /**
     * searchMyCurOpenedBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchMyCurOpenedBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchmycuropenedbug`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchmycuropenedbug`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchmycuropenedbug`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchmycuropenedbug`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchmycuropenedbug`,tempData,isloading);
    }

    /**
     * FetchMyFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchMyFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/bugs/fetchmyfavorites`,tempData,isloading);
        return res;
    }

    /**
     * searchMyFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchMyFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchmyfavorites`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchmyfavorites`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchmyfavorites`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchmyfavorites`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchmyfavorites`,tempData,isloading);
    }

    /**
     * FetchNotCurPlanLinkBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchNotCurPlanLinkBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchnotcurplanlinkbug`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchnotcurplanlinkbug`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchnotcurplanlinkbug`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchnotcurplanlinkbug`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchnotcurplanlinkbug`,tempData,isloading);
        return res;
    }

    /**
     * searchNotCurPlanLinkBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchNotCurPlanLinkBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchnotcurplanlinkbug`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchnotcurplanlinkbug`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchnotcurplanlinkbug`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchnotcurplanlinkbug`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchnotcurplanlinkbug`,tempData,isloading);
    }

    /**
     * FetchProjectBugs接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchProjectBugs(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/fetchprojectbugs`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/fetchprojectbugs`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/fetchprojectbugs`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/fetchprojectbugs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/bugs/fetchprojectbugs`,tempData,isloading);
        return res;
    }

    /**
     * searchProjectBugs接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchProjectBugs(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchprojectbugs`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchprojectbugs`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchprojectbugs`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchprojectbugs`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchprojectbugs`,tempData,isloading);
    }

    /**
     * FetchReleaseBugs接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchReleaseBugs(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/fetchreleasebugs`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/fetchreleasebugs`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/fetchreleasebugs`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/fetchreleasebugs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/bugs/fetchreleasebugs`,tempData,isloading);
        return res;
    }

    /**
     * searchReleaseBugs接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchReleaseBugs(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchreleasebugs`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchreleasebugs`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchreleasebugs`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchreleasebugs`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchreleasebugs`,tempData,isloading);
    }

    /**
     * FetchReleaseLeftBugs接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchReleaseLeftBugs(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/fetchreleaseleftbugs`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/fetchreleaseleftbugs`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/fetchreleaseleftbugs`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/fetchreleaseleftbugs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/bugs/fetchreleaseleftbugs`,tempData,isloading);
        return res;
    }

    /**
     * searchReleaseLeftBugs接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchReleaseLeftBugs(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchreleaseleftbugs`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchreleaseleftbugs`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchreleaseleftbugs`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchreleaseleftbugs`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchreleaseleftbugs`,tempData,isloading);
    }

    /**
     * FetchReleaseLinkableLeftBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchReleaseLinkableLeftBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchreleaselinkableleftbug`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchreleaselinkableleftbug`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchreleaselinkableleftbug`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchreleaselinkableleftbug`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchreleaselinkableleftbug`,tempData,isloading);
        return res;
    }

    /**
     * searchReleaseLinkableLeftBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchReleaseLinkableLeftBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchreleaselinkableleftbug`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchreleaselinkableleftbug`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchreleaselinkableleftbug`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchreleaselinkableleftbug`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchreleaselinkableleftbug`,tempData,isloading);
    }

    /**
     * FetchReleaseLinkableResolvedBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchReleaseLinkableResolvedBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchreleaselinkableresolvedbug`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchreleaselinkableresolvedbug`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchreleaselinkableresolvedbug`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchreleaselinkableresolvedbug`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchreleaselinkableresolvedbug`,tempData,isloading);
        return res;
    }

    /**
     * searchReleaseLinkableResolvedBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchReleaseLinkableResolvedBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchreleaselinkableresolvedbug`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchreleaselinkableresolvedbug`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchreleaselinkableresolvedbug`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchreleaselinkableresolvedbug`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchreleaselinkableresolvedbug`,tempData,isloading);
    }

    /**
     * FetchReportBugs接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchReportBugs(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/fetchreportbugs`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/bugs/fetchreportbugs`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/bugs/fetchreportbugs`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/bugs/fetchreportbugs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/bugs/fetchreportbugs`,tempData,isloading);
        return res;
    }

    /**
     * searchReportBugs接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchReportBugs(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchreportbugs`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchreportbugs`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchreportbugs`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchreportbugs`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchreportbugs`,tempData,isloading);
    }

    /**
     * FetchTaskRelatedBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async FetchTaskRelatedBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/fetchtaskrelatedbug`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/fetchtaskrelatedbug`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/fetchtaskrelatedbug`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/fetchtaskrelatedbug`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/bugs/fetchtaskrelatedbug`,tempData,isloading);
        return res;
    }

    /**
     * searchTaskRelatedBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async searchTaskRelatedBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/bugs/searchtaskrelatedbug`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/bugs/searchtaskrelatedbug`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/bugs/searchtaskrelatedbug`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/bugs/searchtaskrelatedbug`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/bugs/searchtaskrelatedbug`,tempData,isloading);
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.bug){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/bugs/${context.bug}/select`,isloading);
            
            return res;
        }
        if(context.project && context.bug){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/bugs/${context.bug}/select`,isloading);
            
            return res;
        }
        if(context.story && context.bug){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/bugs/${context.bug}/select`,isloading);
            
            return res;
        }
        if(context.product && context.bug){
            let res:any = await Http.getInstance().get(`/products/${context.product}/bugs/${context.bug}/select`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/bugs/${context.bug}/select`,isloading);
            
            return res;
    }
}