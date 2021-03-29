import { PSSysSFPubBase } from './pssys-sfpub-base';

/**
 * 后台服务架构
 *
 * @export
 * @class PSSysSFPub
 * @extends {PSSysSFPubBase}
 * @implements {IPSSysSFPub}
 */
export class PSSysSFPub extends PSSysSFPubBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof PSSysSFPub
     */
    clone(): PSSysSFPub {
        return new PSSysSFPub(this);
    }
}
export default PSSysSFPub;
