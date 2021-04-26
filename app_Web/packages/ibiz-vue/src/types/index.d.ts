import { Notice } from 'view-design';
import { FooterItemsService } from '../app-service/common-service/footer-items-service';
import { UIStateService } from '../app-service/common-service/ui-state-service';
import { TopItemsService } from '../app-service/common-service/top-items-service';

declare module 'vue/types/vue' {
    interface Vue {
        $Notice: Notice,
        $throw: Function,
        $store: any,
        $viewTool: any,
        $route:any,
        $router:any,
        $util: any,
        $t: any,
        $message:any,
        $appmodal: any,
        $footerRenderService: FooterItemsService;
        $topRenderService: TopItemsService;
        $uiState: UIStateService;
    }
}

