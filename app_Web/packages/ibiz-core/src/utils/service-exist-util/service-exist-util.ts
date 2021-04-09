import { isEmpty, isNil } from 'ramda';
import { IContext } from '../../interface';
import { LogUtil } from '../log-util/log-util';

/**
 * 判断数据主键是否存在，不存在抛出异常
 *
 * @export
 * @param {string} funcName 执行方法名称
 * @param {*} entity 判断数据
 * @return {*}  {boolean}
 */
export function isExistSrfKey(funcName: string, entity: any): boolean {
    if (entity != null) {
        const { srfkey } = entity;
        if (!isNil(srfkey) && !isEmpty(srfkey)) {
            return true;
        }
    }
    LogUtil.log(entity);
    throw new Error(`执行「${funcName}」不存在「srfkey」无法处理`);
}

/**
 * 判断缓存srfsessionkey是否存在，不存在抛异常
 *
 * @export
 * @param {string} funcName 执行方法名称
 * @param {IContext} context 上下文
 * @return {*}  {boolean}
 */
export function isExistSessionKey(funcName: string, context: IContext): boolean {
    const { srfsessionkey } = context;
    if (!isNil(srfsessionkey) && !isEmpty(srfsessionkey)) {
        return true;
    }
    LogUtil.log(context);
    throw new Error(`执行「${funcName}」不存在「srfsessionkey」无法处理`);
}
