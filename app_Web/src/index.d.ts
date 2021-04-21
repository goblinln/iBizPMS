import { ElMessage } from 'element-ui/types/message';
import { UIServiceRegister, AuthServiceRegister, UtilServiceRegister, CounterServiceRegister, CodeListRegister, MessageServiceRegister} from 'ibiz-service';
import { FooterItemsService, TopItemsService, UIStateService } from 'ibiz-vue';
declare global {
    interface Window {
        uiServiceRegister: UIServiceRegister,
        authServiceRegister: AuthServiceRegister,
        utilServiceRegister: UtilServiceRegister,
        counterServiceRegister: CounterServiceRegister,
        codeListRegister:CodeListRegister,
        messageServiceRegister:MessageServiceRegister
    }
}

declare module "vue/types/vue" {
    interface Vue {
        $http:any,
        $util:any,
        $verify:any,
        $viewTool:any,
        $uiActionTool:any,
        $message:ElMessage,
        $appmodal:any,
        $appdrawer:any,
        $apppopover:any,
        $footerRenderService:FooterItemsService;
        $topRenderService:TopItemsService;
        $uiState:UIStateService;
    }
}
declare global {
    interface Object {
        /**
         * 清除所有属性，不改变内存地址
         *
         * @memberof Object
         */
        clearAll(): void;
    }

    /**
     * 判断对象是否存在，判断是否为undefined或null，避免数值型0误判
     *
     * @param {*} obj
     * @returns {boolean}
     */
    function isExist(obj: any): boolean;

    /**
     * 判断字符串是否为空
     *
     * @param {string | undefined | null} str
     * @returns {boolean}
     */
    function isEmpty(str: string | undefined | null): boolean;

    /**
     * 判断字符串，存在并且不为空
     *
     * @param {string | undefined | null} str
     * @returns {boolean}
     */
    function isExistAndNotEmpty(str: string | undefined | null): boolean;
}