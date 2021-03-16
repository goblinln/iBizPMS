import { ControlServiceBase, IBizKanbanModel} from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { AppKanbanModel, Errorlog } from 'ibiz-vue';


/**
 * Main 部件服务对象
 *
 * @export
 * @class AppKanbanService
 */
export class AppKanbanService extends ControlServiceBase {

    /**
    * 看板实例对象
    *
    * @memberof AppKanbanService
    */
   public controlInstance !: IBizKanbanModel;

   /**
    * 数据服务对象
    *
    * @type {any}
    * @memberof AppKanbanService
    */
   public appEntityService!: any;


   /**
    * 初始化服务参数
    *
    * @type {boolean}
    * @memberof AppKanbanService
    */
   public async initServiceParam(opts: any) {
       this.controlInstance = opts;
       this.appEntityService = await new GlobalService().getService((this.controlInstance.appDataEntity as any).codeName);
       this.model = new AppKanbanModel(opts);
   }

   /**
    * Creates an instance of AppKanbanService.
    * 
    * @param {*} [opts={}]
    * @memberof AppKanbanService
    */
   constructor(opts: any = {}) {
       super(opts);
       this.initServiceParam(opts);
   }  

    /**
     * 查询数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppKanbanService
     */
    @Errorlog
    public search(action: string,context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        const {data:Data,context:Context} = this.handleRequestData(action,context,data,true);
        return new Promise((resolve: any, reject: any) => {
            const _appEntityService: any = this.appEntityService;
            let result: Promise<any>;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context,Data, isloading);
            }else{
                result =_appEntityService.FetchDefault(Context,Data, isloading);
            }
            result.then(async (response) => {
                await this.handleResponse(action, response);
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
     * @memberof AppKanbanService
     */
    @Errorlog
    public delete(action: string,context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        const {data:Data,context:Context} = this.handleRequestData(action,context,data,true);
        return new Promise((resolve: any, reject: any) => {
            const _appEntityService: any = this.appEntityService;
            let result: Promise<any>;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context,Data, isloading);
            }else{
                result =_appEntityService.remove(Context,Data, isloading);
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
     * 更新数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppKanbanService
     */
    @Errorlog
    public update(action: string,context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        const {data:Data,context:Context} = this.handleRequestData(action,context,data,true);
        return new Promise((resolve: any, reject: any) => {
            const _appEntityService: any = this.appEntityService;
            let result: Promise<any>;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context,Data, isloading);
            }else{
                result =_appEntityService.update(Context,Data, isloading);
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