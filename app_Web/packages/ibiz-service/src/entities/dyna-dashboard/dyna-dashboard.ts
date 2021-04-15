import { DynaDashboardBase } from './dyna-dashboard-base';

/**
 * 动态数据看板
 *
 * @export
 * @class DynaDashboard
 * @extends {DynaDashboardBase}
 * @implements {IDynaDashboard}
 */
export class DynaDashboard extends DynaDashboardBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof DynaDashboard
     */
    clone(): DynaDashboard {
        return new DynaDashboard(this);
    }
}
export default DynaDashboard;
