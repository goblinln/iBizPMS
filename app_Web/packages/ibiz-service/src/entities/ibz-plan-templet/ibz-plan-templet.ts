import { IbzPlanTempletBase } from './ibz-plan-templet-base';

/**
 * 计划模板
 *
 * @export
 * @class IbzPlanTemplet
 * @extends {IbzPlanTempletBase}
 * @implements {IIbzPlanTemplet}
 */
export class IbzPlanTemplet extends IbzPlanTempletBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzPlanTemplet
     */
    clone(): IbzPlanTemplet {
        return new IbzPlanTemplet(this);
    }
}
export default IbzPlanTemplet;
