import { clone, equals, isEmpty, isNil, mergeDeepLeft, where } from 'ramda';
import { ascSort, createUUID, descSort, generateOrderValue, notNilEmpty } from 'qx-util';
import { Entity } from '../../entities';
import { IContext, IEntityBase, IEntityLocalDataService, IHttpResponse, IParams } from '../../interface';
import { acc } from '../../modules/message-center/app-communications-center';
import { Http, HttpResponse, LogUtil } from '../../utils';
import { PSDEDQCondEngine } from '../../utils/de-dq-cond';
import { EntityCache } from '../../utils/entity-cache/entity-cache';
import { SearchFilter } from '../../utils/search-filter/search-filter';

/**
 * 实体服务基类
 *
 * @export
 * @class EntityBaseService
 * @implements {IEntityLocalDataService<T>}
 * @template T
 */
export class EntityBaseService<T extends IEntityBase> implements IEntityLocalDataService<T> {
    /**
     * 统一日志输出工具类
     *
     * @protected
     * @memberof EntityBaseService
     */
    protected log = LogUtil;
    /**
     * 实体全部属性
     *
     * @protected
     * @memberof EntityBase
     */
    protected get keys(): string[] {
        return [];
    }
    /**
     * 应用实体名称
     *
     * @protected
     * @memberof EntityBaseService
     */
    protected APPDENAME = '';
    /**
     * 应用实体名称复数形式
     *
     * @protected
     * @memberof EntityBaseService
     */
    protected APPDENAMEPLURAL = '';
    /**
     * 应用实体主键
     *
     * @protected
     * @memberof EntityBaseService
     */
    protected APPDEKEY = '';
    /**
     * 应用实体主文本
     *
     * @protected
     * @memberof EntityBaseService
     */
    protected APPDETEXT = '';

    /**
     * 系统名称
     *
     * @protected
     * @memberof EntityBaseService
     */
    protected SYSTEMNAME = '';

    /**
     * 应用名称
     *
     * @protected
     * @memberof EntityBaseService
     */
    protected APPNAME = '';
    /**
     * 当前实体服务支持快速搜索的属性
     *
     * @protected
     * @type {string[]}
     * @memberof EntityBaseService
     */
    protected quickSearchFields: string[] = [];
    /**
     * 根据关系，select查询时填充额外条件。
     *
     * @protected
     * @type {*}
     * @memberof EntityBaseService
     */
    protected selectContextParam: any = null;
    /**
     * 搜索条件引擎实例缓存
     *
     * @protected
     * @type {Map<string, PSDEDQCondEngine>}
     * @memberof EntityBaseService
     */
    protected condCache: Map<string, PSDEDQCondEngine> = new Map();
    /**
     * 是否启用acc通知
     *
     * @memberof EntityBaseService
     */
    private isEnableAcc = true;
    /**
     * http请求服务
     *
     * @protected
     * @memberof EntityBaseService
     */
    protected http = Http.getInstance();

    /**
     * 数据缓存
     *
     * @protected
     * @type {EntityCache<T>}
     * @memberof EntityBaseService
     */
    protected cache: EntityCache<T> = new EntityCache();

    /**
     * 发送应用中心消息
     *
     * @protected
     * @param {''} type
     * @param {*} data
     * @memberof EntityBaseService
     */
    protected sendAppMessage(type: 'create' | 'update' | 'remove', data: any): void {
        if (this.isEnableAcc) {
            acc.send[type](data);
        }
    }

    /**
     * 启用消息中心通知
     *
     * @memberof EntityBaseService
     */
    enableAcc(): void {
        this.isEnableAcc = true;
    }

    /**
     * 禁用消息中心通知
     *
     * @memberof EntityBaseService
     */
    disableAcc(): void {
        this.isEnableAcc = false;
    }

    /**
     * 过滤当前实体服务，标准接口数据
     *
     * @return {*}  {*}
     * @memberof EntityBaseService
     */
    filterEntityData(entity: T): T {
        const data: any = {};
        this.keys.forEach(key => {
            if (entity[key] !== void 0) {
                data[key] = entity[key];
            }
        });
        return data;
    }
    /**
     * 行为执行之前
     *
     * @protected
     * @param {string} _action 行为名称
     * @param {IContext} _context
     * @param {*} _data
     * @return {*}  {Promise<void>}
     * @memberof EntityBaseService
     */
    protected async before(_action: string, _context: IContext, _data: any): Promise<void> {}

    /**
     * 行为执行之后
     *
     * @protected
     * @param {string} _action
     * @param {IContext} _context
     * @param {*} _data
     * @return {*}  {Promise<void>}
     * @memberof EntityBaseService
     */
    protected async after(_action: string, _context: IContext, _data: any): Promise<void> {}

    /**
     * 根据主关系填充外键数据
     *
     * @protected
     * @param {IContext} _context
     * @param {T} _entity
     * @return {*}  {Promise<void>}
     * @memberof EntityBaseService
     */
    protected async fillRelationalDataMajor(_context: IContext, _entity: T): Promise<void> {}

    /**
     * 根据从关系填充外键数据
     *
     * @protected
     * @param {IContext} _context
     * @param {T} _entity
     * @return {*}  {Promise<void>}
     * @memberof EntityBaseService
     */
    protected async fillRelationalDataMinor(_context: IContext, _entity: T): Promise<void> {}

    /**
     * 填充从实体本地数据
     *
     * @protected
     * @param {IContext} _context
     * @param {T} _data
     * @return {*}  {Promise<T>}
     * @memberof EntityBaseService
     */
    protected async fillMinor(_context: IContext, _data: T): Promise<T> {
        this.addLocal(_context, _data);
        return _data;
    }

    /**
     * 获取从实体本地数据
     *
     * @protected
     * @param {IContext} _context
     * @param {*} [_data={}]
     * @return {*}  {Promise<T>}
     * @memberof EntityBaseService
     */
    protected async obtainMinor(_context: IContext, _data: T): Promise<any> {
        if (!_data) {
            (_data as any) = new Entity();
        }
        const res = await this.GetTemp(_context, _data);
        if (res.ok) {
            return mergeDeepLeft(_data, this.filterEntityData(res.data)) as any;
        }
        return _data;
    }
    /**
     * 设置从数据
     *
     * @protected
     * @param {string} entityName
     * @param {IContext} context
     * @param {any[]} items
     * @return {*}  {Promise<boolean>}
     * @memberof EntityBaseService
     */
    protected async setMinorLocal(entityName: string, context: IContext, items: any[]): Promise<boolean> {
        if (items && items.length > 0) {
            const service: EntityBaseService<T> = await ___ibz___.gs[`get${entityName}Service`]();
            return service.setLocals(context, items);
        }
        return false;
    }

    /**
     * 获取从数据
     *
     * @protected
     * @param {string} entityName 从实体
     * @param {IContext} context 上下文
     * @param {IParams} [params] 参数
     * @param {string} [dataSet] 指定查询数据集
     * @return {*}  {(Promise<T[]>)}
     * @memberof EntityBaseService
     */
    protected async getMinorLocal(
        entityName: string,
        context: IContext,
        params?: IParams,
        dataSet?: string,
    ): Promise<T[]> {
        const service: EntityBaseService<T> = await ___ibz___.gs[`get${entityName}Service`]();
        return service.getLocals(context, params, dataSet);
    }

    /**
     * 批量建立临时数据[解包数据]
     *
     * @protected
     * @param {IContext} context
     * @param {T[]} items
     * @return {*}  {Promise<boolean>}
     * @memberof EntityBaseService
     */
    protected async setLocals(context: IContext, items: T[]): Promise<boolean> {
        if (items && items.length > 0) {
            for (let i = 0; i < items.length; i++) {
                const item = items[i];
                item.srfordervalue = generateOrderValue(i);
                await this.fillMinor(context, item);
            }
            return true;
        }
        return false;
    }

    /**
     * 批量获取临时数据[打包包数据]
     *
     * @protected
     * @param {IContext} context
     * @param {IParams} [params]
     * @param {string} [dataSet]
     * @return {*}  {(Promise<T[]>)}
     * @memberof EntityBaseService
     */
    protected async getLocals(context: IContext, params?: IParams, dataSet?: string): Promise<T[]> {
        let items: T[] = [];
        const _this: any = this;
        if (_this[dataSet!]) {
            const res = await _this[dataSet!](context, params);
            if (res.ok) {
                items = res.data;
            }
        } else {
            items = await this.selectLocal(context, params);
        }
        if (items && items.length > 0) {
            items = items.sort((a: any, b: any) => a.srfordervalue - b.srfordervalue);
            for (let i = 0; i < items.length; i++) {
                let item = items[i];
                const srfuf = item.srfuf;
                item = this.filterEntityData(item);
                item = await this.obtainMinor(context, item);
                // if (window.IBzDynamicConfig.enablePackageDataKey === false && srfuf == 0) {
                //     delete item[this.APPDEKEY];
                // }
                items[i] = item;
            }
        }
        return items;
    }

    /**
     * 新增本地数据
     *
     * @deprecated
     * @param {IContext} context
     * @param {T} entity
     * @return {*}  {(Promise<T | null>)}
     * @memberof EntityBaseService
     */
    async addLocal(context: IContext, entity: T): Promise<T | null> {
        return this.cache.add(context, entity as any);
    }

    /**
     * 新建本地数据
     *
     * @deprecated
     * @param {IContext} context
     * @param {T} entity
     * @return {*}  {(Promise<T | null>)}
     * @memberof EntityBaseService
     */
    async createLocal(context: IContext, entity: T): Promise<T | null> {
        entity.srfuf = 0;
        const data = await this.addLocal(context, entity);
        if (data) {
            this.sendAppMessage('create', entity);
        }
        return data;
    }

    /**
     * 获取缓存数据
     *
     * @param {IContext} context
     * @param {string} srfKey
     * @return {*}  {Promise<T>}
     * @memberof EntityBaseService
     */
    async getLocal2(context: IContext, srfKey: string): Promise<T> {
        return this.cache.get(context, srfKey) as any;
    }

    /**
     * 查找本地数据
     *
     * @deprecated
     * @param {IContext} context
     * @param {string} srfKey
     * @return {*}  {Promise<T | null>}
     * @memberof EntityBaseService
     */
    async getLocal(context: IContext, srfKey: string): Promise<T | null> {
        return this.cache.get(context, srfKey);
    }

    /**
     * 更新本地数据
     *
     * @deprecated
     * @param {IContext} context
     * @param {T} entity
     * @return {*}  {Promise<T>}
     * @memberof EntityBaseService
     */
    async updateLocal(context: IContext, entity: T): Promise<T> {
        const data: any = this.cache.update(context, entity);
        if (data) {
            this.sendAppMessage('update', data);
        }
        return data;
    }

    /**
     * 删除本地数据
     *
     * @deprecated
     * @param {IContext} context
     * @param {string} srfKey
     * @return {*}  {Promise<T>}
     * @memberof EntityBaseService
     */
    async removeLocal(context: IContext, srfKey: string): Promise<T> {
        const data: any = this.cache.delete(context, srfKey);
        if (data) {
            this.sendAppMessage('remove', data);
        }
        return data;
    }

    /**
     * 本地获取默认值
     *
     * @deprecated
     * @param {IContext} _context
     * @param {T} _entity
     * @return {*}  {Promise<T>}
     * @memberof EntityBaseService
     */
    async getDraftLocal(_context: IContext, _entity: T): Promise<T> {
        throw new Error('「getDraftLocal」方法暂需重写实现');
    }

    /**
     * 查询本地数据，根据属性
     *
     * @deprecated
     * @param {IContext} context
     * @param {IParams} params 根据多实体属性查找，例：{ name: '张三', age: 18, parent: null }
     * @return {*}  {Promise<T[]>}
     * @memberof EntityBaseService
     */
    async selectLocal(context: IContext, params: IParams = {}): Promise<T[]> {
        let items = await this.cache.getList(context).sort((a: any, b: any) => a.srfordervalue - b.srfordervalue);
        if (notNilEmpty(params) || notNilEmpty(context)) {
            // 查询数据条件集
            const data: any = {};
            const nullData: any = {};
            const undefinedData: any = {};
            if (params.srfkey) {
                data.srfkey = equals(params.srfkey);
            }
            if (this.selectContextParam) {
                for (const key in this.selectContextParam) {
                    if (this.selectContextParam.hasOwnProperty(key)) {
                        const val = this.selectContextParam[key];
                        if (notNilEmpty(context[key])) {
                            data[val] = equals(context[key]);
                        }
                    }
                }
            }
            delete params.srfkey;
            for (const key in params) {
                if (params.hasOwnProperty(key)) {
                    const val = params[key];
                    if (val == null) {
                        nullData[key] = equals(null);
                        undefinedData[key] = equals(undefined);
                    } else {
                        data[key] = equals(val);
                    }
                }
            }
            if (!isEmpty(data)) {
                // 返回柯里化函数，用于判断数据是否满足要求
                const pred = where(data);
                const nullPred = where(nullData);
                const undefinedPred = where(undefinedData);
                items = items.filter(obj => {
                    if (isEmpty(nullData)) {
                        if (pred(obj)) {
                            return true;
                        }
                    } else {
                        if (pred(obj) && (nullPred(obj) || undefinedPred(obj))) {
                            return true;
                        }
                    }
                });
            }
        }
        const list = items.map(obj => clone(obj));
        LogUtil.warn('select', params, list);
        return list;
    }

    /**
     * 搜索本地数据
     *
     * @deprecated
     * @protected
     * @param {PSDEDQCondEngine | null} cond 查询实例
     * @param {SearchFilter} filter 过滤对象
     * @param {string[]} [queryParamKeys=this.quickSearchFields] 当前实体支持快速搜索的属性
     * @return {*}  {Promise<T[]>}
     * @memberof EntityBaseService
     */
    protected async searchLocal(
        cond: PSDEDQCondEngine | null,
        filter: SearchFilter,
        queryParamKeys: string[] = this.quickSearchFields,
    ): Promise<T[]> {
        let list = [];
        // 走查询条件
        if (cond) {
            list = this.cache.getList(filter.context);
            if (list?.length > 0) {
                list = list.filter(obj => cond.test(obj, filter));
            }
        } else {
            list = await this.selectLocal(filter.context);
            if (list?.length > 0) {
                // 识别query查询
                const condition = filter.data;
                if (condition != null && !isEmpty(condition)) {
                    if (queryParamKeys) {
                        list = list.filter(obj => {
                            const reg = new RegExp(filter.query);
                            for (let i = 0; i < queryParamKeys.length; i++) {
                                const key = queryParamKeys[i];
                                const val: string = obj[key];
                                if (reg.test(val)) {
                                    return true;
                                }
                            }
                        });
                    }
                }
            }
        }
        if (!isNil(filter.sortField) && !isEmpty(filter.sortField)) {
            if (filter.sortMode === 'DESC') {
                // 倒序
                list = descSort(list, filter.sortField);
            } else {
                // 正序
                list = ascSort(list, filter.sortField);
            }
        }
        const { page, size } = filter;
        const start = page * size;
        const end = (page + 1) * size - 1;
        const items = list.slice(start, end).map(item => clone(item));
        LogUtil.warn('search', cond, items);
        return items;
    }

    /**
     * 搜索本地数据
     *
     * @deprecated
     * @protected
     * @param {PSDEDQCondEngine | null} cond
     * @param {SearchFilter} filter
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    protected async searchAppLocal(cond: PSDEDQCondEngine | null, filter: SearchFilter): Promise<IHttpResponse> {
        const list = await this.searchLocal(cond, filter);
        return new HttpResponse(list);
    }

    /**
     * 新建本地数据[弃用]
     *
     * @deprecated
     * @param {IContext} context
     * @param {T} entity
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    createAppLocal(context: IContext, entity: T): Promise<IHttpResponse> {
        return this.CreateTemp(context, entity);
    }

    /**
     * 获取本地数据[弃用]
     *
     * @deprecated
     * @param {IContext} context
     * @param {IParams} [params]
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    getAppLocal(context: IContext, params?: IParams): Promise<IHttpResponse> {
        return this.GetTemp(context, params);
    }

    /**
     * 获取默认值[弃用]
     *
     * @deprecated
     * @param {IContext} context
     * @param {*} [params]
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    getDraftAppLocal(context: IContext, params?: any): Promise<IHttpResponse> {
        return this.GetDraftTemp(context, params);
    }

    /**
     * 更新本地数据[弃用]
     *
     * @deprecated
     * @param {IContext} context
     * @param {T} [entity]
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    updateAppLocal(context: IContext, entity: T): Promise<IHttpResponse> {
        return this.UpdateTemp(context, entity);
    }

    /**
     * 删除本地数据[弃用]
     *
     * @deprecated
     * @param {IContext} context
     * @param {IParams} [params]
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    async removeAppLocal(context: IContext, params?: IParams): Promise<IHttpResponse> {
        return this.RemoveTemp(context, params);
    }

    /**
     * 获取本地默认数据集[弃用]
     *
     * @deprecated
     * @param {IContext} context
     * @param {IParams} [params]
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    selectAppLocal(context: IContext, params?: IParams): Promise<IHttpResponse> {
        return this.FetchDefault(context, params);
    }

    Create(context: IContext, entity: T): Promise<IHttpResponse> {
        return this.createAppLocal(context, entity);
    }

    Remove(context: IContext, params?: IParams): Promise<IHttpResponse> {
        return this.removeAppLocal(context, params);
    }

    Update(context: IContext, entity: T): Promise<IHttpResponse> {
        return this.updateAppLocal(context, entity);
    }

    Get(context: IContext, params?: IParams): Promise<IHttpResponse> {
        return this.getAppLocal(context, params);
    }

    GetDraft(context: IContext, params?: IParams): Promise<IHttpResponse> {
        return this.getDraftAppLocal(context, params);
    }

    CreateBatch(context: IContext, params?: IParams): Promise<IHttpResponse> {
        return this.CreateBatchTemp(context, params);
    }

    checkData(context: IContext, srfkey: string): Promise<boolean> {
        return this.checkDataTemp(context, srfkey);
    }

    async FetchDefault(context: IContext, params?: IParams): Promise<IHttpResponse> {
        try {
            await this.before('FetchDefault', context, params);
            const items = await this.selectLocal(context, params);
            await this.after('FetchDefault', context, items);
            return new HttpResponse(items);
        } catch (err) {
            return new HttpResponse(err, {
                ok: false,
                status: 500,
            });
        }
    }

    async CreateTemp(context: IContext, entity: T): Promise<IHttpResponse> {
        try {
            await this.before('CreateTemp', context, entity);
            const data = await this.createLocal(context, entity);
            await this.after('CreateTemp', context, data);
            return new HttpResponse(data);
        } catch (err) {
            return new HttpResponse(err, {
                ok: false,
                status: 500,
            });
        }
    }

    async GetDraftTemp(context: IContext, params?: any): Promise<IHttpResponse> {
        try {
            await this.before('GetDraftTemp', context, params);
            const data = await this.getDraftLocal(context, params);
            await this.after('GetDraftTemp', context, data);
            if (data) {
                return new HttpResponse(data);
            }
            return new HttpResponse(data, {
                ok: false,
                status: 500,
            });
        } catch (err) {
            return new HttpResponse(err, {
                ok: false,
                status: 500,
            });
        }
    }

    async RemoveTemp(context: IContext, params?: IParams): Promise<IHttpResponse> {
        try {
            await this.before('RemoveTemp', context, params);
            let key = null;
            if (params) {
                key = params[this.APPDEKEY.toLowerCase()];
            }
            if (!key && context) {
                key = context[this.APPDENAME.toLowerCase()];
            }
            const data = await this.removeLocal(context, key);
            await this.after('RemoveTemp', context, data);
            if (data) {
                return new HttpResponse(data);
            }
            return new HttpResponse(data, {
                ok: false,
                status: 500,
            });
        } catch (err) {
            return new HttpResponse(err, {
                ok: false,
                status: 500,
            });
        }
    }

    async UpdateTemp(context: IContext, entity: T): Promise<IHttpResponse> {
        try {
            await this.before('UpdateTemp', context, entity);
            const data = await this.updateLocal(context, entity);
            await this.after('UpdateTemp', context, data);
            if (data) {
                return new HttpResponse(data);
            }
            return new HttpResponse(data, {
                ok: false,
                status: 500,
            });
        } catch (err) {
            return new HttpResponse(err, {
                ok: false,
                status: 500,
            });
        }
    }

    async GetTemp(context: IContext, params?: IParams): Promise<IHttpResponse> {
        try {
            await this.before('GetTemp', context, params);
            let key = null;
            if (params) {
                key = params[this.APPDEKEY.toLowerCase()];
            }
            if (!key && context) {
                key = context[this.APPDENAME.toLowerCase()];
            }
            const data = await this.getLocal(context, key);
            await this.after('GetTemp', context, data);
            if (data) {
                return new HttpResponse(data);
            }
            return new HttpResponse(data, {
                ok: false,
                status: 500,
            });
        } catch (err) {
            return new HttpResponse(err, {
                ok: false,
                status: 500,
            });
        }
    }

    /**
     * 批量新建本地数据
     *
     * @param {IContext} _context
     * @param {IParams} [_params]
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    async CreateBatchTemp(_context: IContext, _params?: IParams): Promise<IHttpResponse> {
        throw new Error('「CreateBatchTemp」未实现');
    }

    /**
     * 拷贝一条数据
     *
     * @param {IContext} context
     * @param {T} data
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    async CopyTemp(context: IContext, data: T): Promise<IHttpResponse> {
        const result = await this.GetTemp(context, data);
        if (result.ok) {
            this.disableAcc();
            const entity: T = result.data;
            entity.assign!(data);
            entity.srfkey = createUUID();
            entity.srfmajortext = `${entity.srfmajortext} copy`;
            const draftRes = await this.GetDraftTemp(
                {
                    ...context,
                    [this.APPDENAME.toLowerCase()]: context[this.APPDENAME.toLowerCase()] || entity.srfpkey,
                },
                entity,
            );
            if (draftRes.ok) {
                const res = await this.CreateTemp(context, draftRes.data);
                this.enableAcc();
                return res;
            }
            this.enableAcc();
        }
        return new HttpResponse(null, { status: 500, statusText: '拷贝数据失败失败' });
    }

    /**
     * FetchTempDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityBaseService
     */
    public async FetchTempDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        try {
            if (context && context.srfsessionkey) {
                const tempData = await this.getLocals(context);
                return { "status": 200, "data": tempData };
            } else {
                return { "status": 200, "data": [] };
            }
        } catch (error: any) {
            return { "status": 200, "data": [] };
        }
    }

    /**
     * 根据主键判断数据是否存在
     *
     * @param {IContext} context
     * @param {string} srfkey
     * @return {*}  {Promise<boolean>}
     * @memberof EntityBaseService
     */
    async checkDataTemp(context: IContext, srfkey: string): Promise<boolean> {
        return this.cache.checkData(context, srfkey);
    }

    CreateTempMajor(context: IContext, params?: any): Promise<IHttpResponse> {
        return this.Create(context, params);
    }
    GetDraftTempMajor(context: IContext, params?: any): Promise<IHttpResponse> {
        return this.GetDraft(context, params);
    }
    GetTempMajor(context: IContext, params?: IParams): Promise<IHttpResponse> {
        return this.Get(context, params);
    }
    UpdateTempMajor(context: IContext, entity: T): Promise<IHttpResponse> {
        return this.Update(context, entity);
    }
    RemoveTempMajor(context: IContext, params?: IParams): Promise<IHttpResponse> {
        return this.Remove(context, params);
    }

    /**
     * ImportData接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async ImportData(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        let _data: Array<any> = [];
        if (data && data.importData) _data = data.importData;
        return Http.getInstance().post(`/${this.APPDENAMEPLURAL.toLowerCase()}/import?config=${data.name}`, _data, isloading);
    }

    /**
     * createBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async createBatch(context: any = {}, data: any, isloading?: boolean): Promise<any> {
        return Http.getInstance().post(`/${this.APPDENAMEPLURAL.toLowerCase()}/batch`, data, isloading);
    }

    /**
     * saveBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async saveBatch(context: any = {}, data: any, isloading?: boolean): Promise<any> {
        return Http.getInstance().post(`/${this.APPDENAMEPLURAL.toLowerCase()}/savebatch`, data, isloading);
    }

    /**
     * updateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async updateBatch(context: any = {}, data: any, isloading?: boolean): Promise<any> {
        return Http.getInstance().put(`/${this.APPDENAMEPLURAL.toLowerCase()}/batch`, data, isloading);
    }

    /**
     * removeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async removeBatch(context: any = {}, data: any, isloading?: boolean): Promise<any> {
        return Http.getInstance().delete(`/${this.APPDENAMEPLURAL.toLowerCase()}/batch`, isloading, data[this.APPDEKEY]);
    }

    /**
     * getDataInfo接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async getDataInfo(context: any = {}, data: any, isloading?: boolean): Promise<any> {
        if (context[this.APPDENAME.toLowerCase()]) {
            return this.Get(context, data);
        }
    }

    /**
     * getDynaModel(获取动态模型)接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async getDynaModel(context: any = {}, data: any, isloading?: boolean): Promise<any> {
        if (data && data.configType && data.targetType) {
            return Http.getInstance().get(`/configs/${data.configType}/${data.targetType}`);
        }
    }

    /**
     * setDynaModel(设置动态模型)接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async setDynaModel(context: any = {}, data: any, isloading?: boolean): Promise<any> {
        if (data && data.configType && data.targetType) {
            return Http.getInstance().put(`/configs/${data.configType}/${data.targetType}`, { model: data.model });
        }
    }

    /**
     * getDynaWorkflow接口方法(获取指定工作流版本信息)
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @param {*} [localdata]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async getDynaWorkflow(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        return Http.getInstance().get(
            `/wfcore/${this.SYSTEMNAME.toLowerCase()}-app-${this.APPNAME.toLowerCase()}/${context.srfdynainstid}/${this.APPDENAMEPLURAL.toLowerCase()}/process-definitions`,
            isloading,
        );
    }

    /**
     * WFStart接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @param {*} [localdata]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async WFStart(context: any = {}, data: any = {}, localdata?: any, isloading?: boolean): Promise<any> {
        const requestData: any = {};
        Object.assign(requestData, { activedata: data });
        Object.assign(requestData, localdata);
        return Http.getInstance().post(
            `/wfcore/${this.SYSTEMNAME.toLowerCase()}-app-${this.APPNAME.toLowerCase()}/${this.APPDENAMEPLURAL.toLowerCase()}/${data[this.APPDEKEY]
            }/process-instances`,
            requestData,
            isloading,
        );
    }

    /**
     * WFClose接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async WFClose(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        return Http.getInstance().post(`/${this.APPDENAMEPLURAL.toLowerCase()}/${data[this.APPDEKEY]}/wfclose`, data, isloading);
    }

    /**
     * WFMarkRead接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async WFMarkRead(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        return Http.getInstance().post(`/${this.APPDENAMEPLURAL.toLowerCase()}/${data[this.APPDEKEY]}/wfmarkread`, data, isloading);
    }

    /**
     * WFGoto接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async WFGoto(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        return Http.getInstance().post(`/${this.APPDENAMEPLURAL.toLowerCase()}/${data[this.APPDEKEY]}/wfgoto`, data, isloading);
    }

    /**
     * WFRollback接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async WFRollback(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        return Http.getInstance().post(`/${this.APPDENAMEPLURAL.toLowerCase()}/${data[this.APPDEKEY]}/wfrollback`, data, isloading);
    }

    /**
     * WFRestart接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async WFRestart(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        return Http.getInstance().post(`/${this.APPDENAMEPLURAL.toLowerCase()}/${data[this.APPDEKEY]}/wfrestart`, data, isloading);
    }

    /**
     * WFReassign接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async WFReassign(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        return Http.getInstance().post(`/${this.APPDENAMEPLURAL.toLowerCase()}/${data[this.APPDEKEY]}/wfreassign`, data, isloading);
    }

    /**
     * WFGetWorkFlow接口方法(获取工作流定义)
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async WFGetWorkFlow(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        return Http.getInstance().get(
            `/wfcore/${this.SYSTEMNAME.toLowerCase()}-app-${this.APPNAME.toLowerCase()}/${this.APPDENAMEPLURAL.toLowerCase()}/process-definitions`,
        );
    }

    /**
     * WFGetWFStep接口方法(根据系统实体查找当前适配的工作流模型步骤)
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async WFGetWFStep(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        return Http.getInstance().get(
            `/wfcore/${this.SYSTEMNAME.toLowerCase()}-app-${this.APPNAME.toLowerCase()}/${this.APPDENAMEPLURAL.toLowerCase()}/process-definitions-nodes`,
        );
    }

    /**
     * GetWFLink接口方法(根据业务主键和当前步骤获取操作路径)
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async GetWFLink(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        return Http.getInstance().get(
            `/wfcore/${this.SYSTEMNAME.toLowerCase()}-app-${this.APPNAME.toLowerCase()}/${this.APPDENAMEPLURAL.toLowerCase()}/${context[this.APPDENAME.toLowerCase()]
            }/usertasks/${data['taskDefinitionKey']}/ways`,
        );
    }

    /**
     * GetWFLinks接口方法(根据当前步骤和任务获取批量操作路径)
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async getWFLinks(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        return Http.getInstance().get(
            `/wfcore/${this.SYSTEMNAME.toLowerCase()}-app-${this.APPNAME.toLowerCase()}/${this.APPDENAMEPLURAL.toLowerCase()}/process-definitions/${data['processDefinitionKey']}/usertasks/${data['taskDefinitionKey']}/ways`,
        );
    }

    /**
     * wfSubmitBatch接口方法(批量提交)
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async wfSubmitBatch(context: any = {}, data: any = {}, localdata: any, isloading?: boolean): Promise<any> {
        return Http.getInstance().post(
            `/wfcore/${this.SYSTEMNAME.toLowerCase()}-app-${this.APPNAME.toLowerCase()}/${this.APPDENAMEPLURAL.toLowerCase()}/process-definitions/${localdata['processDefinitionKey']}/usertasks/${localdata['taskDefinitionKey']}/ways/${localdata['sequenceFlowId']}/submit`,
            data,
        );
    }

    /**
     * GetWFHistory接口方法(根据业务主键获取工作流程记录)
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async GetWFHistory(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        return Http.getInstance().get(
            `/wfcore/${this.SYSTEMNAME.toLowerCase()}-app-${this.APPNAME.toLowerCase()}/${this.APPDENAMEPLURAL.toLowerCase()}/${context[this.APPDENAME.toLowerCase()]
            }/process-instances/alls/history`,
        );
    }

    /**
     * WFSubmit接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @param {*} [localdata]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async WFSubmit(context: any = {}, data: any = {}, localdata?: any): Promise<any> {
        const requestData: any = {};
        if (data.viewparams) {
            delete data.viewparams;
        }
        Object.assign(requestData, { activedata: data });
        Object.assign(requestData, localdata);
        return Http.getInstance().post(
            `/wfcore/${this.SYSTEMNAME.toLowerCase()}-app-${this.APPNAME.toLowerCase()}/${this.APPDENAMEPLURAL.toLowerCase()}/${data[this.APPDEKEY.toLowerCase()]}/tasks/${localdata['taskId']
            }`,
            requestData
        );
    }

    /**
     * WFGetProxyData接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EntityService
     */
    public async WFGetProxyData(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        return Http.getInstance().get(
            `/${this.APPDENAMEPLURAL.toLowerCase()}/${context[this.APPDENAME.toLowerCase()]}/wfgetproxydata`,
            data,
            isloading,
        );
    }

    /**
     * 测试数据是否在工作流中
     *
     * @param context
     * @param data
     * @param isloading
     */
    public async testDataInWF(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        if (!context.stateField || !context.stateValue) return false;
        if (context.stateValue == data[context.stateField]) {
            return true;
        }
        return false;
    }

    /**
     * 测试当前用户是否提交过工作流
     *
     * @param context
     * @param data
     * @param isloading
     */
    public async testUserWFSubmit(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        return true;
    }

    /**
     * 测试当前用户是否存在待办列表
     *
     * @param context
     * @param data
     * @param isloading
     */
    public async testUserExistWorklist(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        const requestData: any = {};
        Object.assign(requestData, { wfdata: data });
        return Http.getInstance().post(
            `/${this.APPDENAMEPLURAL.toLowerCase()}/${data[this.APPDENAME.toLowerCase()]}/testuserexistworklist`,
            requestData,
            isloading,
        );
    }

    /**
     * 获取所有应用数据
     *
     * @param context
     * @param data
     * @param isloading
     */
    public async getAllApp(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        return Http.getInstance().get(`/uaa/access-center/app-switcher/default`, data, isloading);
    }

    /**
     * 更新已选择的应用
     *
     * @param context
     * @param data
     * @param isloading
     */
    public async updateChooseApp(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        return Http.getInstance().put(`/uaa/access-center/app-switcher/default`, data, isloading);
    }

    /**
     * 修改密码
     *
     * @param context
     * @param data
     * @param isloading
     */
    public async changPassword(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        return Http.getInstance().post(`/v7/changepwd`, data, isloading);
    }

    /**
     * 获取数字字典
     *
     * @param tag
     * @param data
     * @param isloading
     */
    public async getPredefinedCodelist(tag: string, data: any = {}, isloading?: boolean): Promise<any> {
        return Http.getInstance().get(`/dictionarys/codelist/${tag}`, data, isloading);
    }
}
