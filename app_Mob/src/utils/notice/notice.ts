import { toastController, alertController } from '@ionic/core';
import store from '../../store';
/**
 * 消息提示
 *
 * @export
 * @class Notice
 */
export class Notice {

    /**
     * store
     */
    public store:any = null;

    /**
     * 唯一实例
     *
     * @private
     * @static
     * @type {Notice}
     * @memberof Notice
     */
    private static readonly instance: Notice = new Notice();

    /**
     * Creates an instance of Notice.
     * @memberof Notice
     */
    constructor() {
        this.store = store;
        if (Notice.instance) {
            return Notice.instance;
        }
    }

    /**
     * 消息提示
     *
     * @param {string} message
     * @param {number} [time]
     * @returns {Promise<any>}
     * @memberof Notice
     */
    public async info(message: string, time?: number): Promise<any> {
        const type = 'secondary';
        return this.createToast(type, message, time);
    }

    /**
     * 成功提示
     *
     * @param {string} message
     * @param {number} [time]
     * @returns {Promise<any>}
     * @memberof Notice
     */
    public async success(message: string, time?: number): Promise<any> {
        const type = 'success';
        return this.createToast(type, message, time);
    }

    /**
     * 警告提示
     *
     * @param {string} message
     * @param {number} [time]
     * @returns {Promise<any>}
     * @memberof Notice
     */
    public async warning(message: string, time?: number): Promise<any> {
        const type = 'warning';
        return this.createToast(type, message, time);
    }

    /**
     * 错误提示
     *
     * @param {string} message
     * @param {number} [time]
     * @returns {Promise<any>}
     * @memberof Notice
     */
    public async error(message: string, time?: number): Promise<any> {
        const type = 'danger';
        return this.createToast(type, message, time);
    }

    /**
     * 确认操作
     *
     * @param {string} message
     * @returns {Promise<any>}
     * @memberof Notice
     */
    public async confirm(title: string, message: string): Promise<boolean> {
      return new Promise(async (resolve, reject) => {
          const alert = await alertController.create({
              header: title ? title : '标题',
              message: message,
              buttons: [
                  {
                      text: '取消',
                      role: 'cancel',
                      handler: () => {
                          if (this.store && this.store.commit) {
                            this.store.commit('setNoticeStatus',true);
                          }
                          resolve(false);
                      }
                  },
                  {
                      text: '确认',
                      cssClass: 'secondary',
                      handler: () => {
                          if (this.store && this.store.commit) {
                            this.store.commit('setNoticeStatus',true);
                          }
                          resolve(true);
                      }
                  },
              ],
          });
          if (this.store && this.store.state && this.store.state.noticeStatus) {
            await alert.present();
            if (this.store && this.store.commit) {
              this.store.commit('setNoticeStatus',false);
            }
          }
      });
    }

    /**
     * 创建对象
     *
     * @private
     * @param {string} type
     * @param {string} message
     * @param {number} [time]
     * @memberof Notice
     */
    private async createToast(type: string, message: string, time?: number) {
        const toast = await toastController.create({
            position: 'top',
            color: type ? type : 'primary',
            duration: time ? time : 2000,
            message: message,
        });
        await toast.present();
    }

    /**
     * 获取实例
     *
     * @static
     * @returns {Notice}
     * @memberof Notice
     */
    public static getInstance(): Notice {
        return this.instance;
    }

}