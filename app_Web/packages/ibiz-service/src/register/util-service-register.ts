import { ServiceRegisterBase } from 'ibiz-core';

/**
 * 功能服务注册中心
 *
 * @export
 * @class UtilServiceRegister
 */
export class UtilServiceRegister extends ServiceRegisterBase {

    /**
     * Creates an instance of UtilServiceRegister.
     * @memberof UtilServiceRegister
     */
    constructor() {
        super();
    }

    /**
     * 初始化
     *
     * @protected
     * @memberof UtilServiceRegister
     */
    protected init(): void {
                this.allService.set('dynadashboard', () => import('../utilservice/dynadashboard/dynadashboard-util-service'));
    }

}
export const utilServiceRegister: UtilServiceRegister = new UtilServiceRegister();