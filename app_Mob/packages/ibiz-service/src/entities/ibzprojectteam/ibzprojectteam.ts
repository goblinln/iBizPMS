import { IBZPROJECTTEAMBase } from './ibzprojectteam-base';

/**
 * 项目团队
 *
 * @export
 * @class IBZPROJECTTEAM
 * @extends {IBZPROJECTTEAMBase}
 * @implements {IIBZPROJECTTEAM}
 */
export class IBZPROJECTTEAM extends IBZPROJECTTEAMBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZPROJECTTEAM
     */
    clone(): IBZPROJECTTEAM {
        return new IBZPROJECTTEAM(this);
    }
}
export default IBZPROJECTTEAM;
