import { SysOrganizationBase } from './sys-organization-base';

/**
 * 单位
 *
 * @export
 * @class SysOrganization
 * @extends {SysOrganizationBase}
 * @implements {ISysOrganization}
 */
export class SysOrganization extends SysOrganizationBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof SysOrganization
     */
    clone(): SysOrganization {
        return new SysOrganization(this);
    }
}
export default SysOrganization;
