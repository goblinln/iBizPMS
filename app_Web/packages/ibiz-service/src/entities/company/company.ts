import { CompanyBase } from './company-base';

/**
 * 公司
 *
 * @export
 * @class Company
 * @extends {CompanyBase}
 * @implements {ICompany}
 */
export class Company extends CompanyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Company
     */
    clone(): Company {
        return new Company(this);
    }
}
export default Company;
