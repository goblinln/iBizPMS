import { BranchBase } from './branch-base';

/**
 * 产品的分支和平台信息
 *
 * @export
 * @class Branch
 * @extends {BranchBase}
 * @implements {IBranch}
 */
export class Branch extends BranchBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Branch
     */
    clone(): Branch {
        return new Branch(this);
    }
}
export default Branch;
