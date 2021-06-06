import { Notice } from 'view-design';
import { FooterItemsService } from '../app-service/common-service/footer-items-service';
import { UIStateService } from '../app-service/common-service/ui-state-service';
import { TopItemsService } from '../app-service/common-service/top-items-service';
import { ElMessage } from 'element-ui/types/message';

declare module 'vue/types/vue' {
    interface Vue {
        $Notice: Notice,
        $throw: Function,
        $success: Function,
        $warning: Function,
        $info: Function,
        $store: any,
        $viewTool: any,
        $route:any,
        $router:any,
        $util: any,
        $t: any,
        $tl: Function,
        $message:ElMessage,
        $appmodal: any,
        $footerRenderService: FooterItemsService;
        $topRenderService: TopItemsService;
        $uiState: UIStateService;
    }
}

