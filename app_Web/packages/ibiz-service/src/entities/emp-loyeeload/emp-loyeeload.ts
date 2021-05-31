import { EmpLoyeeloadBase } from './emp-loyeeload-base';

/**
 * 员工负载表
 *
 * @export
 * @class EmpLoyeeload
 * @extends {EmpLoyeeloadBase}
 * @implements {IEmpLoyeeload}
 */
export class EmpLoyeeload extends EmpLoyeeloadBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof EmpLoyeeload
     */
    clone(): EmpLoyeeload {
        return new EmpLoyeeload(this);
    }
}
export default EmpLoyeeload;
