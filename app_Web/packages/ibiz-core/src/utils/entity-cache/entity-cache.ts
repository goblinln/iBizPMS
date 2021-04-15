import { IContext, IEntityBase } from '../../interface';
import { where, equals, clone, isNil, isEmpty } from 'ramda';
import { isExistSessionKey, isExistSrfKey } from '../service-exist-util/service-exist-util';
import { LogUtil } from '../log-util/log-util';
import { createUUID } from 'qx-util';

/**
 * 实体缓存工具类
 *
 * @export
 * @class EntityCache
 * @template T
 */
export class EntityCache<T extends IEntityBase> {
    /**
     * 数据缓存
     *
     * @type {Map<string, T>}
     * @memberof EntityCache
     */
    readonly cacheMap: Map<string, Map<string, T>> = new Map();

    /**
     * 新增数据
     *
     * @param {*} context
     * @param {T} entity
     * @return {*}  {boolean}
     * @memberof EntityCache
     */
    add(context: IContext, entity: T): T | null {
        try {
            isExistSessionKey('add', context);
            if (isNil(entity.srfkey) || isEmpty(entity.srfkey)) {
                entity.srfkey = createUUID();
            }
            entity.srftempdate = new Date().getTime();
            const map = this.getCacheByTag(context.srfsessionkey!);
            map.set(entity.srfkey, clone(entity));
            LogUtil.warn('add', entity.srfkey, entity);
            return entity;
        } catch (err) {
            LogUtil.error(err);
            return null;
        }
    }

    /**
     * 查找数据
     *
     * @param {*} context
     * @param {string} srfKey
     * @return {*}  {T}
     * @memberof EntityCache
     */
    get(context: IContext, srfKey: string): T | null {
        try {
            isExistSessionKey('get', context);
            const map = this.getCacheByTag(context.srfsessionkey!);
            const data = map.get(srfKey);
            LogUtil.warn('get', srfKey, data);
            return clone(data)!;
        } catch (err) {
            LogUtil.error(err);
            return null;
        }
    }

    /**
     * 更新数据
     *
     * @param {IContext} context
     * @param {T} entity
     * @return {*}  {T}
     * @memberof EntityCache
     */
    update(context: IContext, entity: T): T | null {
        try {
            isExistSessionKey('update', context);
            isExistSrfKey('update', entity);
            entity.srftempdate = new Date().getTime();
            const map = this.getCacheByTag(context.srfsessionkey!);
            const data = map.get(entity.srfkey!);
            if (data) {
                data._assign!(entity);
                map.set(entity.srfkey!, data);
                LogUtil.warn('update', entity.srfkey, entity);
                return entity;
            }
            throw new Error('数据不存在，无法更新!');
        } catch (err) {
            LogUtil.error(err);
            return null;
        }
    }

    /**
     * 删除数据
     *
     * @param {IContext} context
     * @param {string} srfKey
     * @return {*}  {(T | null)}
     * @memberof EntityCache
     */
    delete(context: IContext, srfKey: string): T | null {
        try {
            isExistSessionKey('delete', context);
            const map = this.getCacheByTag(context.srfsessionkey!);
            const key = srfKey;
            if (map.has(key)) {
                const data = map.get(key)!;
                data.srftempdate = new Date().getTime();
                map.delete(key);
                LogUtil.warn('delete', key);
                return data;
            }
            return null;
        } catch (err) {
            LogUtil.error(err);
            return null;
        }
    }

    /**
     * 检查数据是否已经存在
     *
     * @param {IContext} context
     * @param {string} srfkey
     * @return {*}  {boolean}
     * @memberof EntityCache
     */
    checkData(context: IContext, srfkey: string): boolean {
        const list = this.getList(context);
        const i = list.findIndex(item => item.srfkey === srfkey);
        return i !== -1;
    }

    /**
     * 根据条件查找数据，未设置条件默认返回全部
     *
     * @param {IContext} context
     * @param {({ [key: string]: string | number | boolean })} condition
     * @return {*}  {(T[] | null)}
     * @memberof EntityCache
     */
    getList(context: IContext): T[] {
        try {
            isExistSessionKey('getList', context);
            const map = this.getCacheByTag(context.srfsessionkey!);
            if (!map) {
                return [];
            }
            return Array.from(map.values());
        } catch (err) {
            LogUtil.error(err);
            return [];
        }
    }

    /**
     * 根据条件生成查询
     *
     * @param {*} [params={}]
     * @return {*}
     * @memberof EntityCache
     */
    generatePred(params: any = {}) {
        // 查询数据条件集
        const data: any = {};
        if (params.srfkey) {
            data.srfkey = equals(params.srfkey);
        }
        delete params.srfkey;
        for (const key in params) {
            if (params.hasOwnProperty(key)) {
                const val = params[key];
                data[key] = equals(val);
            }
        }
        return where(data);
    }

    /**
     * 清除缓存
     *
     * @param {IContext} context
     * @memberof EntityCache
     */
    clear(context: IContext): void {
        this.cacheMap.delete(context.srfsessionkey!);
    }

    /**
     * 根据标识获取map组
     *
     * @private
     * @param {string} tag
     * @return {*}  {Map<string, T>}
     * @memberof EntityCache
     */
    private getCacheByTag(tag: string): Map<string, T> {
        if (!this.cacheMap.has(tag)) {
            this.cacheMap.set(tag, new Map());
        }
        return this.cacheMap.get(tag)!;
    }
}
