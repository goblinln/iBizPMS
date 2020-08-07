import { Http,Util,Errorlog } from '@/utils';
import ControlService from '@/widgets/control-service';
import ProductLifeService from '@/service/product-life/product-life-service';
import GetRoadmapSModel from './get-roadmap-s-list-model';


/**
 * GetRoadmapS 部件服务对象
 *
 * @export
 * @class GetRoadmapSService
 */
export default class GetRoadmapSService extends ControlService {

    /**
     * 产品生命周期服务对象
     *
     * @type {ProductLifeService}
     * @memberof GetRoadmapSService
     */
    public appEntityService: ProductLifeService = new ProductLifeService({ $store: this.getStore() });

    /**
     * 设置从数据模式
     *
     * @type {boolean}
     * @memberof GetRoadmapSService
     */
    public setTempMode(){
        this.isTempMode = false;
    }

    /**
     * Creates an instance of GetRoadmapSService.
     * 
     * @param {*} [opts={}]
     * @memberof GetRoadmapSService
     */
    constructor(opts: any = {}) {
        super(opts);
        this.model = new GetRoadmapSModel();
    }

    /**
     * 查询数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof GetRoadmapSService
     */
    @Errorlog
    public search(action: string, context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        const {data:Data,context:Context} = this.handleRequestData(action,context,data,true);
        return new Promise((resolve: any, reject: any) => {
            const _appEntityService: any = this.appEntityService;
            let result: Promise<any>;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context,Data, isloading);
            }else{
                result =_appEntityService.FetchDefault(Context,Data, isloading);
            }
            result.then((response) => {
                this.handleResponse(action, response);
                resolve(response);
            }).catch(response => {
                reject(response);
            });      
        });
    }

    /**
     * 删除数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof GetRoadmapSService
     */
    @Errorlog
    public delete(action: string, context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        const {data:Data,context:Context} = this.handleRequestData(action,context,data,true);
        return new Promise((resolve: any, reject: any) => {
            const _appEntityService: any = this.appEntityService;
            let result: Promise<any>;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context,Data, isloading);
            }else{
                result =_appEntityService.remove(Context,Data , isloading);
            }
            result.then((response) => {
                this.handleResponse(action, response);
                resolve(response);
            }).catch(response => {
                reject(response);
            });      
        });
    }

}