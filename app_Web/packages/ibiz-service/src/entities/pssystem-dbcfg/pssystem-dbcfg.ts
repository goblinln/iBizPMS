import { PSSystemDBCfgBase } from './pssystem-dbcfg-base';

/**
 * 系统数据库
 *
 * @export
 * @class PSSystemDBCfg
 * @extends {PSSystemDBCfgBase}
 * @implements {IPSSystemDBCfg}
 */
export class PSSystemDBCfg extends PSSystemDBCfgBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof PSSystemDBCfg
     */
    clone(): PSSystemDBCfg {
        return new PSSystemDBCfg(this);
    }
}
export default PSSystemDBCfg;
