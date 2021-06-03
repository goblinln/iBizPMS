import { AccountProjectBase } from './account-project-base';

/**
 * 项目
 *
 * @export
 * @class AccountProject
 * @extends {AccountProjectBase}
 * @implements {IAccountProject}
 */
export class AccountProject extends AccountProjectBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof AccountProject
     */
    clone(): AccountProject {
        return new AccountProject(this);
    }
}
export default AccountProject;
