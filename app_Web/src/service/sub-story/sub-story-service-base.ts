import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';
import GetCurUserConcatLogic from '@/service/sub-story/get-cur-user-concat-logic';



/**
 * 需求服务对象基类
 *
 * @export
 * @class SubStoryServiceBase
 * @extends {EntityServie}
 */
export default class SubStoryServiceBase extends EntityService {

    /**
     * Creates an instance of  SubStoryServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  SubStoryServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof SubStoryServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='substory';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'substories';
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
     * @memberof SubStoryServiceBase
     */
    public async Activate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/activate`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/activate`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/activate`,data,isloading);
            return res;
    }

    /**
     * ActivateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ActivateBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/activatebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/activatebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/activatebatch`,tempData,isloading);
    }

    /**
     * AllPush接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async AllPush(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/allpush`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/allpush`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/allpush`,data,isloading);
            return res;
    }

    /**
     * AllPushBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async AllPushBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/allpushbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/allpushbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/allpushbatch`,tempData,isloading);
    }

    /**
     * AssignTo接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async AssignTo(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/assignto`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/assignto`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/assignto`,data,isloading);
            return res;
    }

    /**
     * AssignToBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async AssignToBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/assigntobatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/assigntobatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/assigntobatch`,tempData,isloading);
    }

    /**
     * BatchAssignTo接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchAssignTo(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/batchassignto`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/batchassignto`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/batchassignto`,data,isloading);
            return res;
    }

    /**
     * BatchAssignToBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchAssignToBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/batchassigntobatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/batchassigntobatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/batchassigntobatch`,tempData,isloading);
    }

    /**
     * BatchChangeBranch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchChangeBranch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/batchchangebranch`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/batchchangebranch`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/batchchangebranch`,data,isloading);
            return res;
    }

    /**
     * BatchChangeBranchBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchChangeBranchBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/batchchangebranchbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/batchchangebranchbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/batchchangebranchbatch`,tempData,isloading);
    }

    /**
     * BatchChangeModule接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchChangeModule(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/batchchangemodule`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/batchchangemodule`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/batchchangemodule`,data,isloading);
            return res;
    }

    /**
     * BatchChangeModuleBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchChangeModuleBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/batchchangemodulebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/batchchangemodulebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/batchchangemodulebatch`,tempData,isloading);
    }

    /**
     * BatchChangePlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchChangePlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/batchchangeplan`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/batchchangeplan`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/batchchangeplan`,data,isloading);
            return res;
    }

    /**
     * BatchChangePlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchChangePlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/batchchangeplanbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/batchchangeplanbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/batchchangeplanbatch`,tempData,isloading);
    }

    /**
     * BatchChangeStage接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchChangeStage(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/batchchangestage`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/batchchangestage`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/batchchangestage`,data,isloading);
            return res;
    }

    /**
     * BatchChangeStageBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchChangeStageBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/batchchangestagebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/batchchangestagebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/batchchangestagebatch`,tempData,isloading);
    }

    /**
     * BatchClose接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchClose(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/batchclose`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/batchclose`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/batchclose`,data,isloading);
            return res;
    }

    /**
     * BatchCloseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchCloseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/batchclosebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/batchclosebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/batchclosebatch`,tempData,isloading);
    }

    /**
     * BatchReview接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchReview(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/batchreview`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/batchreview`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/batchreview`,data,isloading);
            return res;
    }

    /**
     * BatchReviewBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchReviewBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/batchreviewbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/batchreviewbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/batchreviewbatch`,tempData,isloading);
    }

    /**
     * BatchUnlinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchUnlinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/batchunlinkstory`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/batchunlinkstory`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/batchunlinkstory`,data,isloading);
            return res;
    }

    /**
     * BatchUnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchUnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/batchunlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/batchunlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/batchunlinkstorybatch`,tempData,isloading);
    }

    /**
     * BugToStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BugToStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/bugtostory`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/bugtostory`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/bugtostory`,data,isloading);
            return res;
    }

    /**
     * BugToStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BugToStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/bugtostorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/bugtostorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/bugtostorybatch`,tempData,isloading);
    }

    /**
     * BuildBatchUnlinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BuildBatchUnlinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/buildbatchunlinkstory`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/buildbatchunlinkstory`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/buildbatchunlinkstory`,data,isloading);
            return res;
    }

    /**
     * BuildBatchUnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BuildBatchUnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/buildbatchunlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/buildbatchunlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/buildbatchunlinkstorybatch`,tempData,isloading);
    }

    /**
     * BuildLinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BuildLinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/buildlinkstory`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/buildlinkstory`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/buildlinkstory`,data,isloading);
            return res;
    }

    /**
     * BuildLinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BuildLinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/buildlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/buildlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/buildlinkstorybatch`,tempData,isloading);
    }

    /**
     * BuildUnlinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BuildUnlinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/buildunlinkstory`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/buildunlinkstory`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/buildunlinkstory`,data,isloading);
            return res;
    }

    /**
     * BuildUnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BuildUnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/buildunlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/buildunlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/buildunlinkstorybatch`,tempData,isloading);
    }

    /**
     * BuildUnlinkStorys接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BuildUnlinkStorys(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/buildunlinkstorys`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/buildunlinkstorys`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/buildunlinkstorys`,data,isloading);
            return res;
    }

    /**
     * Change接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async Change(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/change`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/change`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/change`,data,isloading);
            return res;
    }

    /**
     * ChangeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ChangeBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/changebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/changebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/changebatch`,tempData,isloading);
    }

    /**
     * CheckKey接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/checkkey`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Close接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async Close(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/close`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/close`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/close`,data,isloading);
            return res;
    }

    /**
     * CloseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async CloseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/closebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/closebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/closebatch`,tempData,isloading);
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
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
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories`,data,isloading);
            
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
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories`,data,isloading);
            
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
        let res:any = await Http.getInstance().post(`/substories`,data,isloading);
        
        return res;
    }

    /**
     * CreateTasks接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async CreateTasks(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/createtasks`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/createtasks`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/createtasks`,data,isloading);
            return res;
    }

    /**
     * CreateTasksBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async CreateTasksBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/createtasksbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/createtasksbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/createtasksbatch`,tempData,isloading);
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/${context.substory}`,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/${context.substory}`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/substories/${context.substory}`,isloading);
            
            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.substory) delete tempData.substory;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/getdraft`,tempData,isloading);
            res.data.substory = data.substory;
            
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.substory) delete tempData.substory;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/getdraft`,tempData,isloading);
            res.data.substory = data.substory;
            
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.substory) delete tempData.substory;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/substories/getdraft`,tempData,isloading);
        res.data.substory = data.substory;
        
        return res;
    }

    /**
     * GetStorySpec接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async GetStorySpec(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/getstoryspec`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/getstoryspec`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/getstoryspec`,data,isloading);
            return res;
    }

    /**
     * GetStorySpecBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async GetStorySpecBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/getstoryspecbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/getstoryspecbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/getstoryspecbatch`,tempData,isloading);
    }

    /**
     * GetStorySpecs接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async GetStorySpecs(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/getstoryspecs`,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/${context.substory}/getstoryspecs`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/substories/${context.substory}/getstoryspecs`,isloading);
            
            return res;
    }

    /**
     * GetTaskReStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async GetTaskReStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * GetUserConcat接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async GetUserConcat(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let appLogic:GetCurUserConcatLogic = new GetCurUserConcatLogic({context:JSON.parse(JSON.stringify(context)),data:JSON.parse(JSON.stringify(data))});
        const res = await appLogic.onExecute(context,data,isloading?true:false);
        return {status:200,data:res};
    }

    /**
     * ImportPlanStories接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ImportPlanStories(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/importplanstories`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/importplanstories`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/importplanstories`,data,isloading);
            return res;
    }

    /**
     * ImportPlanStoriesBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ImportPlanStoriesBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/importplanstoriesbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/importplanstoriesbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/importplanstoriesbatch`,tempData,isloading);
    }

    /**
     * LinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async LinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/linkstory`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/linkstory`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/linkstory`,data,isloading);
            return res;
    }

    /**
     * LinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async LinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/linkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/linkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/linkstorybatch`,tempData,isloading);
    }

    /**
     * ProjectBatchUnlinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ProjectBatchUnlinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/projectbatchunlinkstory`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/projectbatchunlinkstory`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/projectbatchunlinkstory`,data,isloading);
            return res;
    }

    /**
     * ProjectBatchUnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ProjectBatchUnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/projectbatchunlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/projectbatchunlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/projectbatchunlinkstorybatch`,tempData,isloading);
    }

    /**
     * ProjectLinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ProjectLinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/projectlinkstory`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/projectlinkstory`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/projectlinkstory`,data,isloading);
            return res;
    }

    /**
     * ProjectLinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ProjectLinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/projectlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/projectlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/projectlinkstorybatch`,tempData,isloading);
    }

    /**
     * ProjectUnlinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ProjectUnlinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/projectunlinkstory`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/projectunlinkstory`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/projectunlinkstory`,data,isloading);
            return res;
    }

    /**
     * ProjectUnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ProjectUnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/projectunlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/projectunlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/projectunlinkstorybatch`,tempData,isloading);
    }

    /**
     * ProjectUnlinkStorys接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ProjectUnlinkStorys(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/projectunlinkstorys`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/projectunlinkstorys`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/projectunlinkstorys`,data,isloading);
            return res;
    }

    /**
     * Push接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async Push(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/push`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/push`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/push`,data,isloading);
            return res;
    }

    /**
     * PushBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async PushBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/pushbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/pushbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/pushbatch`,tempData,isloading);
    }

    /**
     * ReleaseBatchUnlinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ReleaseBatchUnlinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/releasebatchunlinkstory`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/releasebatchunlinkstory`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/releasebatchunlinkstory`,data,isloading);
            return res;
    }

    /**
     * ReleaseBatchUnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ReleaseBatchUnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/releasebatchunlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/releasebatchunlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/releasebatchunlinkstorybatch`,tempData,isloading);
    }

    /**
     * ReleaseLinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ReleaseLinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/releaselinkstory`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/releaselinkstory`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/releaselinkstory`,data,isloading);
            return res;
    }

    /**
     * ReleaseLinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ReleaseLinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/releaselinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/releaselinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/releaselinkstorybatch`,tempData,isloading);
    }

    /**
     * ReleaseUnlinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ReleaseUnlinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/releaseunlinkstory`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/releaseunlinkstory`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/releaseunlinkstory`,data,isloading);
            return res;
    }

    /**
     * ReleaseUnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ReleaseUnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/releaseunlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/releaseunlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/releaseunlinkstorybatch`,tempData,isloading);
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let res:any = await Http.getInstance().delete(`/products/${context.product}/stories/${context.story}/substories/${context.substory}`,isloading);
            return res;
        }
        if(context.story && context.substory){
            let res:any = await Http.getInstance().delete(`/stories/${context.story}/substories/${context.substory}`,isloading);
            return res;
        }
            let res:any = await Http.getInstance().delete(`/substories/${context.substory}`,isloading);
            return res;
    }

    /**
     * ResetReviewedBy接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ResetReviewedBy(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/resetreviewedby`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/resetreviewedby`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/resetreviewedby`,data,isloading);
            return res;
    }

    /**
     * ResetReviewedByBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ResetReviewedByBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/resetreviewedbybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/resetreviewedbybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/resetreviewedbybatch`,tempData,isloading);
    }

    /**
     * Review接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async Review(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/review`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/review`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/review`,data,isloading);
            return res;
    }

    /**
     * ReviewBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ReviewBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/reviewbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/reviewbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/reviewbatch`,tempData,isloading);
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/save`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/save`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/substories/${context.substory}/save`,data,isloading);
            
            return res;
    }

    /**
     * SendMessage接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async SendMessage(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/sendmessage`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/sendmessage`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/sendmessage`,data,isloading);
            return res;
    }

    /**
     * SendMessageBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async SendMessageBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/sendmessagebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/sendmessagebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/sendmessagebatch`,tempData,isloading);
    }

    /**
     * SendMsgPreProcess接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async SendMsgPreProcess(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/sendmsgpreprocess`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/sendmsgpreprocess`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/sendmsgpreprocess`,data,isloading);
            return res;
    }

    /**
     * SendMsgPreProcessBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async SendMsgPreProcessBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/sendmsgpreprocessbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/sendmsgpreprocessbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/sendmsgpreprocessbatch`,tempData,isloading);
    }

    /**
     * StoryFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async StoryFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/storyfavorites`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/storyfavorites`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/storyfavorites`,data,isloading);
            return res;
    }

    /**
     * StoryNFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async StoryNFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/storynfavorites`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/storynfavorites`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/storynfavorites`,data,isloading);
            return res;
    }

    /**
     * SyncFromIbiz接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async SyncFromIbiz(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/syncfromibiz`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/syncfromibiz`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/syncfromibiz`,data,isloading);
            return res;
    }

    /**
     * SyncFromIbizBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async SyncFromIbizBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/syncfromibizbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/syncfromibizbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/syncfromibizbatch`,tempData,isloading);
    }

    /**
     * ToStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ToStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * UnlinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async UnlinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/unlinkstory`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/${context.substory}/unlinkstory`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/substories/${context.substory}/unlinkstory`,data,isloading);
            return res;
    }

    /**
     * UnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async UnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/unlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/unlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/unlinkstorybatch`,tempData,isloading);
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/substories/${context.substory}`,data,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/substories/${context.substory}`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/substories/${context.substory}`,data,isloading);
            
            return res;
    }

    /**
     * FetchAssignedToMyStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchAssignedToMyStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchassignedtomystory`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchassignedtomystory`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchassignedtomystory`,tempData,isloading);
        return res;
    }

    /**
     * searchAssignedToMyStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchAssignedToMyStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchassignedtomystory`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchassignedtomystory`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchassignedtomystory`,tempData,isloading);
    }

    /**
     * FetchAssignedToMyStoryCalendar接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchAssignedToMyStoryCalendar(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchassignedtomystorycalendar`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchassignedtomystorycalendar`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchassignedtomystorycalendar`,tempData,isloading);
        return res;
    }

    /**
     * searchAssignedToMyStoryCalendar接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchAssignedToMyStoryCalendar(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchassignedtomystorycalendar`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchassignedtomystorycalendar`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchassignedtomystorycalendar`,tempData,isloading);
    }

    /**
     * FetchBugStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchBugStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchbugstory`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchbugstory`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchbugstory`,tempData,isloading);
        return res;
    }

    /**
     * searchBugStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchBugStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchbugstory`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchbugstory`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchbugstory`,tempData,isloading);
    }

    /**
     * FetchBuildLinkCompletedStories接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchBuildLinkCompletedStories(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchbuildlinkcompletedstories`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchbuildlinkcompletedstories`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchbuildlinkcompletedstories`,tempData,isloading);
        return res;
    }

    /**
     * searchBuildLinkCompletedStories接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchBuildLinkCompletedStories(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchbuildlinkcompletedstories`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchbuildlinkcompletedstories`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchbuildlinkcompletedstories`,tempData,isloading);
    }

    /**
     * FetchBuildLinkableStories接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchBuildLinkableStories(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchbuildlinkablestories`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchbuildlinkablestories`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchbuildlinkablestories`,tempData,isloading);
        return res;
    }

    /**
     * searchBuildLinkableStories接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchBuildLinkableStories(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchbuildlinkablestories`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchbuildlinkablestories`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchbuildlinkablestories`,tempData,isloading);
    }

    /**
     * FetchBuildStories接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchBuildStories(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/fetchbuildstories`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/fetchbuildstories`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/substories/fetchbuildstories`,tempData,isloading);
        return res;
    }

    /**
     * searchBuildStories接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchBuildStories(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchbuildstories`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchbuildstories`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchbuildstories`,tempData,isloading);
    }

    /**
     * FetchByModule接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchByModule(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/fetchbymodule`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/fetchbymodule`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/substories/fetchbymodule`,tempData,isloading);
        return res;
    }

    /**
     * searchByModule接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchByModule(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchbymodule`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchbymodule`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchbymodule`,tempData,isloading);
    }

    /**
     * FetchCaseStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchCaseStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchcasestory`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchcasestory`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchcasestory`,tempData,isloading);
        return res;
    }

    /**
     * searchCaseStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchCaseStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchcasestory`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchcasestory`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchcasestory`,tempData,isloading);
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/fetchdefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/substories/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchdefault`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchdefault`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchdefault`,tempData,isloading);
    }

    /**
     * FetchESBulk接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchESBulk(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchesbulk`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchesbulk`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchesbulk`,tempData,isloading);
        return res;
    }

    /**
     * searchESBulk接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchESBulk(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchesbulk`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchesbulk`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchesbulk`,tempData,isloading);
    }

    /**
     * FetchGetProductStories接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchGetProductStories(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchgetproductstories`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchgetproductstories`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchgetproductstories`,tempData,isloading);
        return res;
    }

    /**
     * searchGetProductStories接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchGetProductStories(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchgetproductstories`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchgetproductstories`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchgetproductstories`,tempData,isloading);
    }

    /**
     * FetchMyAgentStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchMyAgentStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchmyagentstory`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchmyagentstory`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchmyagentstory`,tempData,isloading);
        return res;
    }

    /**
     * searchMyAgentStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchMyAgentStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchmyagentstory`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchmyagentstory`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchmyagentstory`,tempData,isloading);
    }

    /**
     * FetchMyCurOpenedStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchMyCurOpenedStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchmycuropenedstory`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchmycuropenedstory`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchmycuropenedstory`,tempData,isloading);
        return res;
    }

    /**
     * searchMyCurOpenedStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchMyCurOpenedStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchmycuropenedstory`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchmycuropenedstory`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchmycuropenedstory`,tempData,isloading);
    }

    /**
     * FetchMyFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchMyFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/substories/fetchmyfavorites`,tempData,isloading);
        return res;
    }

    /**
     * searchMyFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchMyFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchmyfavorites`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchmyfavorites`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchmyfavorites`,tempData,isloading);
    }

    /**
     * FetchNotCurPlanLinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchNotCurPlanLinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchnotcurplanlinkstory`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchnotcurplanlinkstory`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchnotcurplanlinkstory`,tempData,isloading);
        return res;
    }

    /**
     * searchNotCurPlanLinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchNotCurPlanLinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchnotcurplanlinkstory`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchnotcurplanlinkstory`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchnotcurplanlinkstory`,tempData,isloading);
    }

    /**
     * FetchParentDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchParentDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/fetchparentdefault`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/fetchparentdefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/substories/fetchparentdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchParentDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchParentDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchparentdefault`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchparentdefault`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchparentdefault`,tempData,isloading);
    }

    /**
     * FetchParentDefaultQ接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchParentDefaultQ(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchparentdefaultq`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchparentdefaultq`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchparentdefaultq`,tempData,isloading);
        return res;
    }

    /**
     * searchParentDefaultQ接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchParentDefaultQ(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchparentdefaultq`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchparentdefaultq`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchparentdefaultq`,tempData,isloading);
    }

    /**
     * FetchProjectLinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchProjectLinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchprojectlinkstory`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchprojectlinkstory`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchprojectlinkstory`,tempData,isloading);
        return res;
    }

    /**
     * searchProjectLinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchProjectLinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchprojectlinkstory`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchprojectlinkstory`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchprojectlinkstory`,tempData,isloading);
    }

    /**
     * FetchProjectStories接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchProjectStories(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/fetchprojectstories`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/fetchprojectstories`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/substories/fetchprojectstories`,tempData,isloading);
        return res;
    }

    /**
     * searchProjectStories接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchProjectStories(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchprojectstories`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchprojectstories`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchprojectstories`,tempData,isloading);
    }

    /**
     * FetchReleaseLinkableStories接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchReleaseLinkableStories(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchreleaselinkablestories`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchreleaselinkablestories`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchreleaselinkablestories`,tempData,isloading);
        return res;
    }

    /**
     * searchReleaseLinkableStories接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchReleaseLinkableStories(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchreleaselinkablestories`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchreleaselinkablestories`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchreleaselinkablestories`,tempData,isloading);
    }

    /**
     * FetchReleaseStories接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchReleaseStories(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/fetchreleasestories`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/fetchreleasestories`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/substories/fetchreleasestories`,tempData,isloading);
        return res;
    }

    /**
     * searchReleaseStories接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchReleaseStories(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchreleasestories`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchreleasestories`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchreleasestories`,tempData,isloading);
    }

    /**
     * FetchReportStories接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchReportStories(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/fetchreportstories`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/substories/fetchreportstories`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/substories/fetchreportstories`,tempData,isloading);
        return res;
    }

    /**
     * searchReportStories接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchReportStories(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchreportstories`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchreportstories`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchreportstories`,tempData,isloading);
    }

    /**
     * FetchStoryChild接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchStoryChild(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchstorychild`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchstorychild`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchstorychild`,tempData,isloading);
        return res;
    }

    /**
     * searchStoryChild接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchStoryChild(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchstorychild`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchstorychild`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchstorychild`,tempData,isloading);
    }

    /**
     * FetchStoryRelated接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchStoryRelated(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchstoryrelated`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchstoryrelated`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchstoryrelated`,tempData,isloading);
        return res;
    }

    /**
     * searchStoryRelated接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchStoryRelated(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchstoryrelated`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchstoryrelated`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchstoryrelated`,tempData,isloading);
    }

    /**
     * FetchSubStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchSubStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchsubstory`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchsubstory`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchsubstory`,tempData,isloading);
        return res;
    }

    /**
     * searchSubStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchSubStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchsubstory`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchsubstory`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchsubstory`,tempData,isloading);
    }

    /**
     * FetchTaskRelatedStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchTaskRelatedStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchtaskrelatedstory`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchtaskrelatedstory`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchtaskrelatedstory`,tempData,isloading);
        return res;
    }

    /**
     * searchTaskRelatedStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchTaskRelatedStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchtaskrelatedstory`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchtaskrelatedstory`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchtaskrelatedstory`,tempData,isloading);
    }

    /**
     * FetchView接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async FetchView(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/fetchview`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/fetchview`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/substories/fetchview`,tempData,isloading);
        return res;
    }

    /**
     * searchView接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async searchView(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/substories/searchview`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/substories/searchview`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/substories/searchview`,tempData,isloading);
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.substory){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/substories/${context.substory}/select`,isloading);
            
            return res;
        }
        if(context.story && context.substory){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/substories/${context.substory}/select`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/substories/${context.substory}/select`,isloading);
            
            return res;
    }
}