// import dayjs from 'dayjs';
import { AppCommunicationsCenter } from './app-communications-center';

/**
 * 应用消息发送工具
 *
 * @export
 * @class AccSendUtil
 */
export class AccSendUtil {
    /**
     * 消息发送
     *
     * @protected
     * @memberof AccSendUtil
     */
    protected acc: AppCommunicationsCenter;

    /**
     * Creates an instance of AccSendUtil.
     * @memberof AccSendUtil
     */
    constructor(acc: AppCommunicationsCenter) {
        this.acc = acc;
    }

    /**
     * 指令-更新
     *
     * @param {*} data 数据
     * @param {string} [deName] 数据所属实体
     * @memberof AccSendUtil
     */
    public update(data: any, deName?: string): void {
        this.acc.sendMessage(
            {
                type: 'COMMAND',
                subtype: 'OBJECTUPDATED',
                content: data,
            },
            deName,
        );
    }

    /**
     * 指令-删除
     *
     * @param {*} data 数据
     * @param {string} [deName] 数据所属实体
     * @memberof ControlBase
     */
    public remove(data: any, deName?: string): void {
        this.acc.sendMessage(
            {
                type: 'COMMAND',
                subtype: 'OBJECTREMOVED',
                content: data,
            },
            deName,
        );
    }

    /**
     * 指令-新建
     *
     * @param {*} data 数据
     * @param {string} [deName] 数据所属实体
     * @memberof ControlBase
     */
    public create(data: any, deName?: string): void {
        this.acc.sendMessage(
            {
                type: 'COMMAND',
                subtype: 'OBJECTCREATED',
                content: data,
            },
            deName,
        );
    }

    /**
     * 用户消息
     *
     * @param {string} message
     * @param {('success' | 'error' | 'warning' | 'info')} [type='info']
     * @memberof AccSendUtil
     */
    // public userInfo(message: string, type: 'success' | 'error' | 'warning' | 'info' = 'info', userName: string): void {
    //     let content: string = `${this.getLocalDate()} ${userName}: `;
    //     switch (type) {
    //         case 'success':
    //             content += `\x1b[32m${message} \x1b[0m`;
    //             break;
    //         case 'error':
    //             content += `\x1b[31m${message} \x1b[0m`;
    //             break;
    //         case 'warning':
    //             content += `\x1b[33m${message} \x1b[0m`;
    //             break;
    //         default:
    //             content += `\x1b[34m${message} \x1b[0m`;
    //     }
    //     this.acc.sendUserMessage(content);
    // }

    /**
     * 向console区发送消息
     *
     * @param {string} message 消息内容
     * @param {('success' | 'error' | 'warning' | 'info')} [type='info'] 消息类型
     * @param {string} [subtype] 消息子类型用于分类
     * @returns {void}
     * @memberof AccSendUtil
     */
    // public console(message: string, type: 'success' | 'error' | 'warning' | 'info' = 'info', subtype?: string): void {
    //     if (message) {
    //         switch (type) {
    //             case 'success':
    //                 return this.consoleSuccess(message, subtype);
    //             case 'error':
    //                 return this.consoleError(message, subtype);
    //             case 'warning':
    //                 return this.consoleWarning(message, subtype);
    //             case 'info':
    //                 return this.consoleInfo(message, subtype);
    //         }
    //     }
    // }

    /**
     * 发送成功消息
     *
     * @protected
     * @param {string} message
     * @memberof AccSendUtil
     */
    // protected consoleSuccess(message: string, subtype?: string): void {
    //     this.acc.sendMessage({
    //         type: 'CONSOLE',
    //         subtype,
    //         content: `${this.getLocalDate()} \x1b[32m${message} \x1b[0m`,
    //     });
    // }

    /**
     * 发送错误消息
     *
     * @protected
     * @param {string} message
     * @memberof AccSendUtil
     */
    // protected consoleError(message: string, subtype?: string): void {
    //     this.acc.sendMessage({
    //         type: 'CONSOLE',
    //         subtype,
    //         content: `${this.getLocalDate()} \x1b[31m${message} \x1b[0m`,
    //     });
    // }

    /**
     * 发送警告消息
     *
     * @protected
     * @param {string} message
     * @memberof AccSendUtil
     */
    // protected consoleWarning(message: string, subtype?: string): void {
    //     this.acc.sendMessage({
    //         type: 'CONSOLE',
    //         subtype,
    //         content: `${this.getLocalDate()} \x1b[33m${message} \x1b[0m`,
    //     });
    // }

    /**
     * 发送消息
     *
     * @protected
     * @param {string} message
     * @memberof AccSendUtil
     */
    // protected consoleInfo(message: string, subtype?: string): void {
    //     this.acc.sendMessage({
    //         type: 'CONSOLE',
    //         subtype,
    //         content: message,
    //     });
    // }

    /**
     * 获取当前时间
     *
     * @protected
     * @returns {string}
     * @memberof AccSendUtil
     */
    // protected getLocalDate(): string {
    //     return dayjs().format('MM-DD HH:mm:ss');
    // }
}
