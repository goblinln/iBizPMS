import { ServiceRegisterBase } from 'ibiz-core';

/**
 * 视图消息服务注册中心
 *
 * @export
 * @class MessageServiceRegister
 */
export class MessageServiceRegister extends ServiceRegisterBase {

    /**
     * Creates an instance of MessageServiceRegister.
     * @memberof MessageServiceRegister
     */
    constructor() {
        super();
    }

    /**
     * 初始化
     *
     * @protected
     * @memberof MessageServiceRegister
     */
    protected init(): void {
            }

}
export const messageServiceRegister: MessageServiceRegister = new MessageServiceRegister();