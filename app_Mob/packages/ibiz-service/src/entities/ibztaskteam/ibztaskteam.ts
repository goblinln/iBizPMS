import { IbztaskteamBase } from './ibztaskteam-base';

/**
 * 任务团队
 *
 * @export
 * @class Ibztaskteam
 * @extends {IbztaskteamBase}
 * @implements {IIbztaskteam}
 */
export class Ibztaskteam extends IbztaskteamBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Ibztaskteam
     */
    clone(): Ibztaskteam {
        return new Ibztaskteam(this);
    }
}
export default Ibztaskteam;
