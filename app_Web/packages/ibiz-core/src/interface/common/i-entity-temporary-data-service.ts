import { IContext } from './i-context';
import { IHttpResponse } from './i-http-response';
import { IParams } from './i-params';

/**
 * 实体临时数据服务接口
 *
 * @interface IEntityLocalDataService
 * @Locallate T
 */
export interface IEntityLocalDataService<T> {
    /**
     * 新增临时数据
     *
     * @deprecated
     * @param {IContext} context
     * @param {T} entity
     * @return {*}  {Promise<T | null>}
     * @memberof IEntityLocalDataService
     */
    addLocal(context: IContext, entity: T): Promise<T | null>;
    /**
     * 新建临时数据，在前端新建
     *
     * @deprecated
     * @param {IContext} context
     * @param {T} entity
     * @return {*}  {Promise<T | null>}
     * @memberof IEntityLocalDataService
     */
    createLocal(context: IContext, entity: T): Promise<T | null>;
    /**
     * 查找临时数据
     *
     * @deprecated
     * @param {IContext} context
     * @param {string} srfKey
     * @return {*}  {Promise<T | null>}
     * @memberof IEntityLocalDataService
     */
    getLocal(context: IContext, srfKey: string): Promise<T | null>;
    /**
     * 更新临时数据
     *
     * @deprecated
     * @param {IContext} context
     * @param {T} entity
     * @return {*}  {Promise<T>}
     * @memberof IEntityLocalDataService
     */
    updateLocal(context: IContext, entity: T): Promise<T>;
    /**
     * 删除临时数据
     *
     * @deprecated
     * @param {IContext} context
     * @param {string} srfKey
     * @return {*}  {Promise<T>}
     * @memberof IEntityLocalDataService
     */
    removeLocal(context: IContext, srfKey: string): Promise<T>;
    /**
     * 获取临时数据默认值
     *
     * @deprecated
     * @param {IContext} context
     * @param {T} entity
     * @return {*}  {Promise<T>}
     * @memberof IEntityLocalDataService
     */
    getDraftLocal(context: IContext, entity: T): Promise<T>;
    /**
     * 查找临时数据列表
     *
     * @deprecated
     * @param {IContext} context
     * @param {IParams} params
     * @return {*}  {Promise<T[]>}
     * @memberof IEntityLocalDataService
     */
    selectLocal(context: IContext, params: IParams): Promise<T[]>;

    /**
     * 预置新建
     *
     * @param {IContext} context
     * @param {T} entity
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    Create(context: IContext, entity: T): Promise<IHttpResponse>;

    /**
     * 预置删除
     *
     * @param {IContext} context
     * @param {IParams} [params]
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    Remove(context: IContext, params?: IParams): Promise<IHttpResponse>;

    /**
     * 预置更新
     *
     * @param {IContext} context
     * @param {T} entity
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    Update(context: IContext, entity: T): Promise<IHttpResponse>;

    /**
     * 预置获取
     *
     * @param {IContext} context
     * @param {IParams} [params]
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    Get(context: IContext, params?: IParams): Promise<IHttpResponse>;

    /**
     * 批量新建数据
     *
     * @param {IContext} context
     * @param {IParams} [params]
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof IEntityLocalDataService
     */
    CreateBatch(context: IContext, params?: IParams): Promise<IHttpResponse>;

    /**
     * 预置查询数据是否存在
     *
     * @param {IContext} context
     * @param {string} srfKey
     * @return {*}  {Promise<boolean>}
     * @memberof IEntityLocalDataService
     */
    checkData(context: IContext, srfKey: string): Promise<boolean>;

    /**
     * 预置默认值获取
     *
     * @param {IContext} context
     * @param {IParams} [params]
     * @memberof EntityBaseService
     */
    GetDraft(context: IContext, params?: IParams): Promise<IHttpResponse>;

    /**
     * 预置默认查询
     *
     * @param {IContext} context
     * @param {IParams} [params]
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    FetchDefault(context: IContext, params?: IParams): Promise<IHttpResponse>;

    /**
     * 新建本地数据
     *
     * @param {IContext} context
     * @param {T} entity
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    CreateTemp(context: IContext, entity: T): Promise<IHttpResponse>;

    /**
     * 获取本地数据新建默认值
     *
     * @param {IContext} context
     * @param {IParams} [params]
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    GetDraftTemp(context: IContext, params?: IParams): Promise<IHttpResponse>;

    /**
     * 删除本地数据
     *
     * @param {IContext} context
     * @param {IParams} [params]
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    RemoveTemp(context: IContext, params?: IParams): Promise<IHttpResponse>;

    /**
     * 更新本地数据
     *
     * @param {IContext} context
     * @param {T} entity
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    UpdateTemp(context: IContext, entity: T): Promise<IHttpResponse>;

    /**
     * 获取本地数据
     *
     * @param {IContext} context
     * @param {IParams} [params]
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    GetTemp(context: IContext, params?: IParams): Promise<IHttpResponse>;

    /**
     * 拷贝指定数据
     *
     * @param {IContext} context
     * @param {T} [data]
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof EntityBaseService
     */
    CopyTemp(context: IContext, data?: T): Promise<IHttpResponse>;

    /**
     * 批量新建本地数据
     *
     * @param {IContext} _context
     * @param {IParams} _params
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof IEntityLocalDataService
     */
    CreateBatchTemp(_context: IContext, _params: IParams): Promise<IHttpResponse>;

    /**
     * 根据主键查询本地数据是否存在
     *
     * @param {IContext} context
     * @param {string} srfKey
     * @return {*}  {Promise<boolean>}
     * @memberof IEntityLocalDataService
     */
    checkDataTemp(context: IContext, srfKey: string): Promise<boolean>;

    /**
     * [临时数据]主数据新建
     *
     * @param {IContext} context
     * @param {IParams} [params]
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof IEntityLocalDataService
     */
    CreateTempMajor(context: IContext, params?: IParams): Promise<IHttpResponse>;

    /**
     * [临时数据]主数据新建默认值
     *
     * @param {IContext} context
     * @param {IParams} [params]
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof IEntityLocalDataService
     */
    GetDraftTempMajor(context: IContext, params?: IParams): Promise<IHttpResponse>;

    /**
     * [临时数据]获取主数据
     *
     * @param {IContext} context
     * @param {IParams} [params]
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof IEntityLocalDataService
     */
    GetTempMajor(context: IContext, params?: IParams): Promise<IHttpResponse>;

    /**
     * [临时数据]更新主数据
     *
     * @param {IContext} context
     * @param {T} entity
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof IEntityLocalDataService
     */
    UpdateTempMajor(context: IContext, entity: T): Promise<IHttpResponse>;

    /**
     * [临时数据]删除主数据
     *
     * @param {IContext} context
     * @param {IParams} [params]
     * @return {*}  {Promise<IHttpResponse>}
     * @memberof IEntityLocalDataService
     */
    RemoveTempMajor(context: IContext, params?: IParams): Promise<IHttpResponse>;
}
