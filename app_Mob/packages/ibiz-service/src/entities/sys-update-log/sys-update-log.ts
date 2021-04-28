import { SysUpdateLogBase } from './sys-update-log-base';

/**
 * 更新日志
 *
 * @export
 * @class SysUpdateLog
 * @extends {SysUpdateLogBase}
 * @implements {ISysUpdateLog}
 */
export class SysUpdateLog extends SysUpdateLogBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof SysUpdateLog
     */
    clone(): SysUpdateLog {
        return new SysUpdateLog(this);
    }
}
export default SysUpdateLog;
