import { Subject } from "rxjs";
import { AppMessageBox } from "../../utils/app-message-box/app-message-box";
import { MessageBoxOptions } from "../../utils/app-message-box/interface/message-box-options";

/**
 * 对话框服务类
 *
 * @export
 * @class AppMessageBoxService
 */
 export class AppMessageBoxService {

    /**
     * 唯一实例
     * 
     * @private
     * @static
     * @memberof AppMessageBoxService
     */
    private static readonly instance = new AppMessageBoxService();

    /**
     * 获取唯一实例
     *
     * @static
     * @return {*}  {AppMessageBoxService}
     * @memberof AppMessageBoxService
     */
    public static getInstance(): AppMessageBoxService {
        return AppMessageBoxService.instance;
    }

    /**
     * 打开信息提示
     *
     * @param {MessageBoxOptions} options
     * @memberof AppMessageBoxService
     */
    public open(options: MessageBoxOptions):Subject<any>|null{
        if(!options){
            return null;
        }
        return AppMessageBox.getInstance().open(options);
    }
}

